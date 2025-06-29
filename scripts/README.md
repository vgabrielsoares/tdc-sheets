# Scripts - Automação TDC Sheets

## Visão Geral
Scripts de automação para desenvolvimento, build, deploy e manutenção do sistema TDC Sheets.

## Categorias de Scripts

### Desenvolvimento
- **setup-dev.sh** - Configuração inicial do ambiente de desenvolvimento
- **install-deps.sh** - Instalação de dependências completas
- **reset-db.sh** - Reset e recrição do banco de dados local
- **generate-data.sh** - Geração de dados de teste

### Build e Deploy
- **build-all.sh** - Build completo do projeto (backend + frontend)
- **deploy-staging.sh** - Deploy para ambiente de staging
- **deploy-prod.sh** - Deploy para produção
- **rollback.sh** - Rollback para versão anterior

### Banco de Dados
- **backup-db.sh** - Backup automatizado do PostgreSQL
- **restore-db.sh** - Restauração de backup
- **migrate-db.sh** - Execução de migrations Liquibase
- **seed-db.sh** - Povoamento com dados iniciais

### Qualidade e Testes
- **run-tests.sh** - Execução de todos os testes
- **code-quality.sh** - Análise de qualidade de código
- **security-scan.sh** - Scan de vulnerabilidades
- **performance-test.sh** - Testes de performance

### Monitoramento
- **health-check.sh** - Verificação de saúde dos serviços
- **log-analysis.sh** - Análise de logs
- **metrics-report.sh** - Relatório de métricas
- **alert-check.sh** - Verificação de alertas

## Scripts de Desenvolvimento

### setup-dev.sh
```bash
#!/bin/bash
# Configuração inicial do ambiente de desenvolvimento

echo "🚀 Configurando ambiente TDC Sheets..."

# Verificar pré-requisitos
check_prerequisites() {
    command -v java >/dev/null 2>&1 || { echo "Java 17+ necessário"; exit 1; }
    command -v node >/dev/null 2>&1 || { echo "Node.js 18+ necessário"; exit 1; }
    command -v docker >/dev/null 2>&1 || { echo "Docker necessário"; exit 1; }
}

# Configurar variáveis de ambiente
setup_env() {
    cp .env.example .env
    echo "📝 Configure as variáveis em .env"
}

# Subir dependências
start_services() {
    docker-compose up -d postgres redis
    echo "🗄️ Banco de dados iniciado"
}

# Instalar dependências
install_deps() {
    cd backend && ./gradlew build
    cd ../frontend && npm install
    echo "📦 Dependências instaladas"
}

main() {
    check_prerequisites
    setup_env
    start_services
    install_deps
    echo "✅ Ambiente configurado com sucesso!"
}

main "$@"
```

### build-all.sh
```bash
#!/bin/bash
# Build completo do projeto

set -e

echo "🔨 Iniciando build completo..."

# Build backend
echo "📦 Building backend..."
cd backend
./gradlew clean build -x test
cd ..

# Build frontend
echo "🎨 Building frontend..."
cd frontend
npm run build
cd ..

# Gerar documentação
echo "📚 Gerando documentação..."
./scripts/generate-docs.sh

echo "✅ Build completo finalizado!"
```

## Scripts de Banco de Dados

### backup-db.sh
```bash
#!/bin/bash
# Backup automatizado do PostgreSQL

DATE=$(date +%Y%m%d_%H%M%S)
BACKUP_DIR="./backups"
DB_NAME="tdc_sheets"
DB_USER="tdc_user"

# Criar diretório de backup
mkdir -p $BACKUP_DIR

# Executar backup
docker-compose exec postgres pg_dump \
    -U $DB_USER \
    -d $DB_NAME \
    --clean \
    --if-exists \
    --no-owner \
    --no-privileges > "$BACKUP_DIR/backup_$DATE.sql"

# Compactar backup
gzip "$BACKUP_DIR/backup_$DATE.sql"

echo "✅ Backup criado: backup_$DATE.sql.gz"

# Manter apenas últimos 30 backups
find $BACKUP_DIR -name "backup_*.sql.gz" -mtime +30 -delete
```

### migrate-db.sh
```bash
#!/bin/bash
# Execução de migrations Liquibase

echo "🔄 Executando migrations..."

cd backend

# Validar changelog
./gradlew liquibaseValidate

# Aplicar migrations
./gradlew liquibaseUpdate

# Gerar documentação do schema
./gradlew liquibaseDbDoc

echo "✅ Migrations aplicadas com sucesso!"
```

## Scripts de Qualidade

### code-quality.sh
```bash
#!/bin/bash
# Análise de qualidade de código

echo "🔍 Analisando qualidade do código..."

# Backend - CheckStyle, SpotBugs, PMD
cd backend
./gradlew check
cd ..

# Frontend - ESLint, TypeScript check
cd frontend
npm run lint
npm run type-check
cd ..

# Testes de cobertura
./scripts/coverage-report.sh

echo "✅ Análise de qualidade finalizada!"
```

### security-scan.sh
```bash
#!/bin/bash
# Scan de vulnerabilidades

echo "🔒 Executando scan de segurança..."

# Dependências Java
cd backend
./gradlew dependencyCheckAnalyze
cd ..

# Dependências Node.js
cd frontend
npm audit --audit-level=high
cd ..

# Docker images
docker scan tdc-backend:latest
docker scan tdc-frontend:latest

echo "✅ Scan de segurança finalizado!"
```

## Scripts de Deploy

### deploy-staging.sh
```bash
#!/bin/bash
# Deploy para staging

set -e

echo "🚀 Iniciando deploy para staging..."

# Build e tag das imagens
docker build -t tdc-backend:staging ./backend
docker build -t tdc-frontend:staging ./frontend

# Deploy com docker-compose
docker-compose -f docker-compose.staging.yml up -d

# Health check
./scripts/health-check.sh staging

echo "✅ Deploy para staging finalizado!"
```

## Scripts de Monitoramento

### health-check.sh
```bash
#!/bin/bash
# Verificação de saúde dos serviços

ENVIRONMENT=${1:-local}
API_URL="http://localhost:8080"

case $ENVIRONMENT in
    "staging") API_URL="https://staging.tdc-sheets.com" ;;
    "prod") API_URL="https://tdc-sheets.com" ;;
esac

echo "🏥 Verificando saúde dos serviços ($ENVIRONMENT)..."

# Check API
response=$(curl -s -o /dev/null -w "%{http_code}" $API_URL/actuator/health)
if [ $response -eq 200 ]; then
    echo "✅ API: OK"
else
    echo "❌ API: FAIL ($response)"
    exit 1
fi

# Check Database
docker-compose exec postgres pg_isready -U tdc_user -d tdc_sheets
if [ $? -eq 0 ]; then
    echo "✅ Database: OK"
else
    echo "❌ Database: FAIL"
    exit 1
fi

echo "✅ Todos os serviços estão saudáveis!"
```

## Configuração

### Permissões
```bash
# Tornar scripts executáveis
chmod +x scripts/*.sh
```

### Variáveis Globais
```bash
# .env
TDC_ENV=development
DB_HOST=localhost
DB_PORT=5432
API_PORT=8080
FRONTEND_PORT=3000
```

## Uso

### Desenvolvimento Local
```bash
# Setup inicial
./scripts/setup-dev.sh

# Reset ambiente
./scripts/reset-env.sh

# Executar testes
./scripts/run-tests.sh
```

### CI/CD
```bash
# Pipeline completo
./scripts/ci-pipeline.sh

# Deploy staging
./scripts/deploy-staging.sh

# Deploy produção
./scripts/deploy-prod.sh
```
