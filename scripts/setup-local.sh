#!/bin/bash
# =================================
# TDC Sheets - Setup Local (Sem Docker)
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
echo "║               Setup Local (Sem Docker)                      ║"
echo "╚══════════════════════════════════════════════════════════════╝"
echo -e "${NC}"

# Verificar pré-requisitos
log_info "Verificando pré-requisitos..."

# Verificar Java
if ! command -v java &> /dev/null; then
    log_error "Java não encontrado!"
    echo "Instale Java 17+: https://adoptium.net/"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | cut -d'.' -f1)
if [ "$JAVA_VERSION" -lt 17 ]; then
    log_error "Java 17+ é necessário. Versão encontrada: $JAVA_VERSION"
    exit 1
fi
log_success "Java encontrado: $(java -version 2>&1 | head -1)"

# Verificar Node.js
if ! command -v node &> /dev/null; then
    log_error "Node.js não encontrado!"
    echo "Instale Node.js 18+: https://nodejs.org/"
    exit 1
fi

NODE_VERSION=$(node --version | cut -d'v' -f2 | cut -d'.' -f1)
if [ "$NODE_VERSION" -lt 18 ]; then
    log_error "Node.js 18+ é necessário. Versão encontrada: $NODE_VERSION"
    exit 1
fi
log_success "Node.js encontrado: $(node --version)"

# Verificar PostgreSQL
if ! command -v psql &> /dev/null; then
    log_warning "PostgreSQL cliente não encontrado!"
    echo "Instale PostgreSQL: https://www.postgresql.org/download/"
    echo "Ou use Docker apenas para o banco: docker run -p 5432:5432 -e POSTGRES_DB=tdc_sheets -e POSTGRES_USER=tdc_user -e POSTGRES_PASSWORD=tdc_password postgres:15"
    read -p "Continuar mesmo assim? (y/N): " -n 1 -r
    echo
    if [[ ! $REPLY =~ ^[Yy]$ ]]; then
        exit 1
    fi
else
    log_success "PostgreSQL encontrado!"
fi

# Configurar arquivo .env para desenvolvimento local
log_info "Configurando ambiente para desenvolvimento local..."

if [ ! -f ".env" ]; then
    cp .env.example .env
    
    # Ajustar para desenvolvimento local
    sed -i 's/DB_HOST=database/DB_HOST=localhost/g' .env
    sed -i 's/VITE_API_URL=http:\/\/localhost:8080/VITE_API_URL=http:\/\/localhost:8080/g' .env
    
    # Gerar JWT secret
    if command -v openssl &> /dev/null; then
        JWT_SECRET=$(openssl rand -hex 32)
        sed -i "s/your-super-secret-jwt-key-minimum-32-characters-change-in-production/$JWT_SECRET/g" .env
    fi
    
    log_success "Arquivo .env configurado para desenvolvimento local!"
else
    log_info "Arquivo .env já existe."
fi

# Setup do Backend
log_info "Configurando backend..."
cd backend

# Verificar se Gradle wrapper existe
if [ ! -f "gradlew" ]; then
    log_info "Inicializando projeto Gradle..."
    gradle wrapper
fi

# Download das dependências
log_info "Baixando dependências do backend..."
./gradlew dependencies

cd ..
log_success "Backend configurado!"

# Setup do Frontend
log_info "Configurando frontend..."
cd frontend

if [ ! -f "package.json" ]; then
    log_info "Inicializando projeto React..."
    npm create vite@latest . -- --template react-ts
fi

# Instalar dependências
log_info "Instalando dependências do frontend..."
npm install

cd ..
log_success "Frontend configurado!"

# Criar diretórios necessários
mkdir -p logs uploads backups

echo ""
log_success "🎉 Setup local concluído!"
echo ""
echo -e "${BLUE}📋 Para desenvolver localmente:${NC}"
echo ""
echo "1. Iniciar banco PostgreSQL (local ou Docker):"
echo "   docker run -d -p 5432:5432 --name tdc-db -e POSTGRES_DB=tdc_sheets -e POSTGRES_USER=tdc_user -e POSTGRES_PASSWORD=tdc_password postgres:15"
echo ""
echo "2. Iniciar backend:"
echo "   cd backend && ./gradlew bootRun"
echo ""
echo "3. Iniciar frontend (em outro terminal):"
echo "   cd frontend && npm run dev"
echo ""
echo -e "${BLUE}🔗 URLs:${NC}"
echo "• Frontend: http://localhost:5173"
echo "• Backend: http://localhost:8080"
echo ""
