#!/bin/bash
# =================================
# TDC Sheets - Script de Setup Inicial
# =================================

set -e  # Exit on error

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

# Banner do projeto
echo -e "${BLUE}"
echo "╔══════════════════════════════════════════════════════════════╗"
echo "║                     TDC Sheets RPG                           ║"
echo "║                  Setup de Desenvolvimento                    ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo -e "${NC}"

# Verificar se estamos no diretório correto
if [ ! -f "docker/docker-compose.yml" ]; then
    log_error "Execute este script a partir do diretório raiz do projeto!"
    exit 1
fi

# Verificar pré-requisitos
log_info "Verificando pré-requisitos..."

# Verificar Docker
if ! command -v docker &> /dev/null; then
    log_error "Docker não encontrado!"
    echo "Por favor, instale o Docker: https://docs.docker.com/get-docker/"
    exit 1
fi

# Verificar Docker Compose
if ! command -v docker-compose &> /dev/null && ! docker compose version &> /dev/null; then
    log_error "Docker Compose não encontrado!"
    echo "Por favor, instale o Docker Compose: https://docs.docker.com/compose/install/"
    exit 1
fi

# Verificar se Docker está rodando
if ! docker info &> /dev/null; then
    # Tentar com sudo se falhar
    if sudo docker info &> /dev/null 2>&1; then
        log_warning "Docker está rodando mas requer sudo."
        echo "Para usar Docker sem sudo, execute:"
        echo "  sudo usermod -aG docker \$USER"
        echo "  newgrp docker  # ou faça logout/login"
        echo ""
        read -p "Continuar com sudo? (y/N): " -n 1 -r
        echo
        if [[ ! $REPLY =~ ^[Yy]$ ]]; then
            exit 1
        fi
        # Definir alias para usar sudo com docker
        DOCKER_CMD="sudo docker"
        DOCKER_COMPOSE_CMD="sudo docker-compose"
    else
        log_error "Docker não está rodando ou não foi encontrado!"
        echo "Verifique se o Docker está instalado e rodando:"
        echo "  sudo systemctl status docker"
        echo "  sudo systemctl start docker"
        exit 1
    fi
else
    DOCKER_CMD="docker"
    DOCKER_COMPOSE_CMD="docker-compose"
fi

# Verificar Node.js (para desenvolvimento local)
if command -v node &> /dev/null; then
    NODE_VERSION=$(node --version)
    log_success "Node.js encontrado: $NODE_VERSION"
else
    log_warning "Node.js não encontrado. Recomendado para desenvolvimento local."
fi

# Verificar Java (para desenvolvimento local)
if command -v java &> /dev/null; then
    JAVA_VERSION=$(java -version 2>&1 | head -1)
    log_success "Java encontrado: $JAVA_VERSION"
else
    log_warning "Java não encontrado. Recomendado para desenvolvimento local."
fi

log_success "Pré-requisitos verificados!"

# Configurar arquivo .env
log_info "Configurando variáveis de ambiente..."

if [ ! -f ".env" ]; then
    log_info "Criando arquivo .env a partir do template..."
    cp .env.example .env
    
    # Gerar JWT secret mais seguro
    if command -v openssl &> /dev/null; then
        JWT_SECRET=$(openssl rand -hex 32)
        sed -i "s/your-super-secret-jwt-key-minimum-32-characters-change-in-production/$JWT_SECRET/g" .env
        log_success "JWT secret gerado automaticamente!"
    fi
    
    log_success "Arquivo .env criado!"
    log_warning "Revise as configurações em .env antes de continuar."
else
    log_info "Arquivo .env já existe."
fi

# Criar diretórios necessários
log_info "Criando estrutura de diretórios..."
mkdir -p logs
mkdir -p uploads
mkdir -p backups
chmod +x docker/database/init-scripts/*.sh
log_success "Diretórios criados!"

# Limpar containers antigos (se existirem)
log_info "Limpando containers antigos..."
$DOCKER_COMPOSE_CMD -f docker/docker-compose.yml down --remove-orphans 2>/dev/null || true
$DOCKER_CMD system prune -f --volumes 2>/dev/null || true

# Fazer build das imagens
log_info "Fazendo build das imagens Docker..."
cd docker
$DOCKER_COMPOSE_CMD build --no-cache
cd ..
log_success "Build das imagens concluído!"

# Iniciar serviços
log_info "Iniciando serviços..."
cd docker
$DOCKER_COMPOSE_CMD up -d database
cd ..

# Aguardar banco de dados ficar disponível
log_info "Aguardando banco de dados ficar disponível..."
max_attempts=30
attempt=1

while [ $attempt -le $max_attempts ]; do
    if $DOCKER_CMD exec tdc-database pg_isready -U tdc_user -d tdc_sheets > /dev/null 2>&1; then
        log_success "Banco de dados está disponível!"
        break
    fi
    
    if [ $attempt -eq $max_attempts ]; then
        log_error "Timeout aguardando banco de dados!"
        exit 1
    fi
    
    echo -n "."
    sleep 2
    ((attempt++))
done

# Verificar se o banco foi inicializado corretamente
log_info "Verificando inicialização do banco..."
if $DOCKER_CMD exec tdc-database psql -U tdc_user -d tdc_sheets -c "SELECT version();" > /dev/null 2>&1; then
    log_success "Banco de dados inicializado com sucesso!"
else
    log_error "Erro na inicialização do banco de dados!"
    exit 1
fi

# Mostrar status dos serviços
log_info "Status dos serviços:"
cd docker
$DOCKER_COMPOSE_CMD ps
cd ..

# Informações de acesso
echo ""
echo -e "${GREEN}🎉 Setup concluído com sucesso!${NC}"
echo ""
echo -e "${BLUE}📋 Próximos passos:${NC}"
echo "1. Para desenvolvimento completo: ./scripts/start-services.sh"
echo "2. Para desenvolvimento backend: cd backend && ./gradlew bootRun"
echo "3. Para desenvolvimento frontend: cd frontend && npm run dev"
echo ""
echo -e "${BLUE}🔗 URLs de acesso:${NC}"
echo "• Frontend: http://localhost:3000"
echo "• Backend API: http://localhost:8080"
echo "• Swagger UI: http://localhost:8080/swagger-ui.html"
echo "• Adminer (DB): http://localhost:8081"
echo ""
echo -e "${YELLOW}💡 Dicas:${NC}"
echo "• Use 'docker-compose logs -f [service]' para ver logs"
echo "• Use 'docker-compose down' para parar todos os serviços"
echo "• Configure suas credenciais no arquivo .env"
echo ""
