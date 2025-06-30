#!/bin/bash
# =================================
# TDC Sheets - Script para Iniciar Serviços
# =================================

set -e

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Funções auxiliares
log_info() {
    echo -e "${BLUE}ℹ️  $1${NC}"
}

log_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

log_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

log_error() {
    echo -e "${RED}❌ $1${NC}"
}

# Banner
echo -e "${BLUE}"
echo "╔══════════════════════════════════════════════════════════════╗"
echo "║                     TDC Sheets RPG                          ║"
echo "║                   Inicializando Serviços                    ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo -e "${NC}"

# Verificar se estamos no diretório correto
if [ ! -f "docker/docker-compose.yml" ]; then
    log_error "Execute este script a partir do diretório raiz do projeto!"
    exit 1
fi

# Verificar se o arquivo .env existe
if [ ! -f ".env" ]; then
    log_error "Arquivo .env não encontrado!"
    echo "Execute primeiro: ./scripts/setup.sh"
    exit 1
fi

# Verificar se Docker está rodando
if ! docker info &> /dev/null; then
    log_error "Docker não está rodando!"
    echo "Por favor, inicie o Docker e tente novamente."
    exit 1
fi

# Processar argumentos
PROFILE="dev"
DETACHED=""
REBUILD=""
LOGS=""

while [[ $# -gt 0 ]]; do
    case $1 in
        --prod)
            PROFILE="prod"
            shift
            ;;
        -d|--detached)
            DETACHED="-d"
            shift
            ;;
        --rebuild)
            REBUILD="--build"
            shift
            ;;
        --logs)
            LOGS="true"
            shift
            ;;
        -h|--help)
            echo "Uso: $0 [opções]"
            echo ""
            echo "Opções:"
            echo "  --prod        Executar em modo produção"
            echo "  -d, --detached Executar em background"
            echo "  --rebuild     Rebuildar imagens antes de iniciar"
            echo "  --logs        Mostrar logs após inicialização"
            echo "  -h, --help    Mostrar esta ajuda"
            exit 0
            ;;
        *)
            log_error "Opção desconhecida: $1"
            exit 1
            ;;
    esac
done

# Ir para o diretório docker
cd docker

# Parar serviços existentes
log_info "Parando serviços existentes..."
docker-compose down --remove-orphans 2>/dev/null || true

# Rebuild se solicitado
if [ ! -z "$REBUILD" ]; then
    log_info "Rebuilding imagens..."
    docker-compose build --no-cache
fi

# Configurar profile
COMPOSE_ARGS=""
if [ "$PROFILE" = "prod" ]; then
    log_info "Iniciando em modo PRODUÇÃO..."
    export SPRING_PROFILES_ACTIVE=prod
else
    log_info "Iniciando em modo DESENVOLVIMENTO..."
    COMPOSE_ARGS="--profile dev"
fi

# Iniciar serviços
log_info "Iniciando serviços do TDC Sheets..."

if [ ! -z "$DETACHED" ]; then
    docker-compose up $DETACHED $REBUILD $COMPOSE_ARGS
    
    # Aguardar serviços ficarem saudáveis
    log_info "Aguardando serviços ficarem saudáveis..."
    sleep 10
    
    # Verificar status dos serviços
    log_info "Status dos serviços:"
    docker-compose ps
    
    # Verificar health dos serviços
    log_info "Verificando saúde dos serviços..."
    
    # Aguardar database
    max_attempts=30
    attempt=1
    while [ $attempt -le $max_attempts ]; do
        if docker exec tdc-database pg_isready -U tdc_user -d tdc_sheets > /dev/null 2>&1; then
            log_success "✅ Database: Saudável"
            break
        fi
        if [ $attempt -eq $max_attempts ]; then
            log_error "Database: Timeout!"
        fi
        sleep 2
        ((attempt++))
    done
    
    # Aguardar backend
    max_attempts=60
    attempt=1
    while [ $attempt -le $max_attempts ]; do
        if curl -f http://localhost:8080/actuator/health > /dev/null 2>&1; then
            log_success "✅ Backend: Saudável"
            break
        fi
        if [ $attempt -eq $max_attempts ]; then
            log_warning "Backend: Ainda inicializando (normal na primeira execução)"
        fi
        sleep 2
        ((attempt++))
    done
    
    # Aguardar frontend
    if curl -f http://localhost:3000/health > /dev/null 2>&1; then
        log_success "✅ Frontend: Saudável"
    else
        log_warning "Frontend: Ainda inicializando"
    fi
    
    # Mostrar informações de acesso
    echo ""
    echo -e "${GREEN}🎉 Serviços iniciados com sucesso!${NC}"
    echo ""
    echo -e "${BLUE}🔗 URLs de acesso:${NC}"
    echo "• Frontend: http://localhost:3000"
    echo "• Backend API: http://localhost:8080"
    echo "• Swagger UI: http://localhost:8080/swagger-ui.html"
    if [ "$PROFILE" = "dev" ]; then
        echo "• Adminer (DB): http://localhost:8081"
    fi
    echo ""
    
    if [ ! -z "$LOGS" ]; then
        log_info "Exibindo logs (Ctrl+C para sair)..."
        docker-compose logs -f
    else
        echo -e "${YELLOW}💡 Para ver logs: docker-compose logs -f [service]${NC}"
        echo -e "${YELLOW}💡 Para parar: docker-compose down${NC}"
    fi
else
    # Modo interativo (com logs)
    log_info "Iniciando em modo interativo (Ctrl+C para parar)..."
    docker-compose up $REBUILD $COMPOSE_ARGS
fi

cd ..
