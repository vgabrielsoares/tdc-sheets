#!/bin/bash
# =================================
# TDC Sheets - Script para Parar Serviços
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
echo "║                    Parando Serviços                         ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo -e "${NC}"

# Verificar se estamos no diretório correto
if [ ! -f "docker/docker-compose.yml" ]; then
    log_error "Execute este script a partir do diretório raiz do projeto!"
    exit 1
fi

# Processar argumentos
REMOVE_VOLUMES=""
REMOVE_IMAGES=""
CLEAN_ALL=""

while [[ $# -gt 0 ]]; do
    case $1 in
        --volumes)
            REMOVE_VOLUMES="--volumes"
            shift
            ;;
        --images)
            REMOVE_IMAGES="true"
            shift
            ;;
        --clean-all)
            CLEAN_ALL="true"
            REMOVE_VOLUMES="--volumes"
            REMOVE_IMAGES="true"
            shift
            ;;
        -h|--help)
            echo "Uso: $0 [opções]"
            echo ""
            echo "Opções:"
            echo "  --volumes     Remove volumes (ATENÇÃO: apaga dados do banco)"
            echo "  --images      Remove imagens Docker criadas"
            echo "  --clean-all   Remove tudo (volumes + imagens)"
            echo "  -h, --help    Mostrar esta ajuda"
            echo ""
            echo -e "${YELLOW}⚠️  ATENÇÃO: --volumes apagará todos os dados do banco!${NC}"
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

# Verificar se há serviços rodando
if ! docker-compose ps --services --filter "status=running" | grep -q .; then
    log_info "Nenhum serviço está rodando."
else
    log_info "Parando serviços do TDC Sheets..."
    
    # Parar serviços graciosamente
    docker-compose stop
    
    log_success "Serviços parados!"
fi

# Remover containers
log_info "Removendo containers..."
docker-compose down --remove-orphans $REMOVE_VOLUMES

# Remover imagens se solicitado
if [ ! -z "$REMOVE_IMAGES" ]; then
    log_info "Removendo imagens Docker..."
    
    # Remover imagens do projeto
    docker images --format "table {{.Repository}}\t{{.Tag}}\t{{.ID}}" | grep "tdc-sheets" | awk '{print $3}' | xargs -r docker rmi -f
    
    # Remover imagens órfãs
    docker image prune -f
    
    log_success "Imagens removidas!"
fi

# Limpeza completa se solicitado
if [ ! -z "$CLEAN_ALL" ]; then
    log_info "Executando limpeza completa..."
    
    # Remover volumes órfãos
    docker volume prune -f
    
    # Remover redes órfãs
    docker network prune -f
    
    # Remover cache de build
    docker builder prune -f
    
    log_success "Limpeza completa concluída!"
fi

cd ..

# Mostrar status final
log_info "Status final:"
cd docker
if docker-compose ps --services --filter "status=running" | grep -q .; then
    docker-compose ps
else
    echo "Nenhum serviço rodando."
fi
cd ..

echo ""
if [ ! -z "$REMOVE_VOLUMES" ]; then
    log_warning "Os dados do banco foram removidos!"
    echo -e "${YELLOW}Para recriar o ambiente: ./scripts/setup.sh${NC}"
else
    log_success "Serviços parados! Os dados do banco foram preservados."
    echo -e "${GREEN}Para reiniciar: ./scripts/start-services.sh${NC}"
fi
echo ""
