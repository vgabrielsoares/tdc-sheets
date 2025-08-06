# Docker Configuration - TDC Sheets

Este diretório contém toda a configuração Docker para o projeto TDC Sheets.

## Estrutura

```
docker/
├── docker-compose.yml          # Orquestração dos serviços
├── Dockerfile.backend          # Imagem do backend Spring Boot
├── Dockerfile.frontend         # Imagem do frontend React
├── nginx/                      # Configurações do Nginx
│   ├── nginx.conf             # Configuração principal
│   └── default.conf           # Virtual host padrão
└── database/                   # Configurações do PostgreSQL
    └── init-scripts/          # Scripts de inicialização
        └── 00-init-database.sh # Setup inicial do banco
```

## Serviços

### Database (PostgreSQL 15)
- **Port**: 5432
- **Database**: tdc_sheets
- **User**: tdc_user
- **Volumes**: postgres_data (persistente)
- **Extensions**: uuid-ossp, pg_trgm, unaccent
- **Timezone**: America/Sao_Paulo

### Backend (Spring Boot)
- **Port**: 8080
- **Profile**: dev/prod (configurável via .env)
- **Health Check**: /actuator/health
- **Volumes**: backend_logs
- **Dependencies**: Database

### Frontend (React + Nginx)
- **Port**: 3000 (dev) / 80 (prod)
- **Health Check**: /health
- **Proxy**: /api/* → backend:8080
- **Dependencies**: Backend

### Adminer (Dev only)
- **Port**: 8081
- **Profile**: dev only
- **Usage**: Interface web para gerenciar banco

## Uso

### Desenvolvimento

```bash
# Setup inicial (primeira vez)
./scripts/setup.sh

# Iniciar todos os serviços
./scripts/start-services.sh

# Iniciar em background
./scripts/start-services.sh -d

# Ver logs
docker-compose logs -f [service]

# Parar serviços
./scripts/stop-services.sh
```

### Produção

```bash
# Configurar .env para produção
cp .env.example .env
# Editar .env com valores de produção

# Iniciar em modo produção
./scripts/start-services.sh --prod -d
```

### Comandos Úteis

```bash
# Status dos serviços
docker-compose ps

# Logs de um serviço específico
docker-compose logs -f backend

# Restart de um serviço
docker-compose restart backend

# Rebuild das imagens
./scripts/start-services.sh --rebuild

# Limpeza completa (remove dados!)
./scripts/stop-services.sh --clean-all
```

## Configuração

### Variáveis de Ambiente

Todas as configurações são feitas através do arquivo `.env` na raiz do projeto:

```bash
# Database
DB_NAME=tdc_sheets
DB_USERNAME=tdc_user
DB_PASSWORD=sua_senha_segura

# Backend
JWT_SECRET=seu_jwt_secret_de_32_chars
SPRING_PROFILES_ACTIVE=dev

# Frontend
VITE_API_URL=http://localhost:8080
```

### Profiles Docker Compose

- **default**: Database + Backend + Frontend
- **dev**: Inclui Adminer para desenvolvimento

```bash
# Apenas serviços principais
docker-compose up

# Com ferramentas de desenvolvimento
docker-compose --profile dev up
```

## Rede e Volumes

### Rede
- **tdc-network**: Bridge network isolada
- **Subnet**: 172.20.0.0/16

### Volumes Persistentes
- **postgres_data**: Dados do PostgreSQL
- **backend_logs**: Logs da aplicação

## Segurança

### Usuários não-root
- Backend: usuário `spring` (UID 1001)
- Frontend: usuário `nginx` (UID 1001)

### Health Checks
- Database: `pg_isready`
- Backend: `curl /actuator/health`
- Frontend: `curl /health`

### Rate Limiting (Nginx)
- General: 10 req/s
- API: 30 req/s

## Troubleshooting

### Banco não conecta
```bash
# Verificar se o banco está saudável
docker exec tdc-database pg_isready -U tdc_user -d tdc_sheets

# Ver logs do banco
docker-compose logs database
```

### Backend não inicia
```bash
# Ver logs detalhados
docker-compose logs backend

# Verificar se o banco está disponível
docker-compose exec backend wget -qO- http://database:5432 || echo "DB not reachable"
```

### Frontend não carrega
```bash
# Verificar se o Nginx está rodando
docker-compose exec frontend nginx -t

# Testar proxy para backend
curl -I http://localhost:3000/api/health
```

### Rebuild completo
```bash
# Parar tudo e limpar
./scripts/stop-services.sh --clean-all

# Setup novamente
./scripts/setup.sh

# Iniciar
./scripts/start-services.sh --rebuild
```

## Monitoramento

### Logs Centralizados
```bash
# Todos os serviços
docker-compose logs -f

# Apenas erros
docker-compose logs -f | grep ERROR

# Últimas 100 linhas
docker-compose logs --tail=100 -f
```

### Métricas de Recursos
```bash
# Uso de CPU/Memória
docker stats

# Espaço em disco
docker system df
```

### Health Status
```bash
# Status de todos os containers
docker-compose ps

# Health checks específicos
curl http://localhost:8080/actuator/health
curl http://localhost:3000/health
```
- Variáveis de ambiente para configuração

### docker-compose.prod.yml
Configuração otimizada para produção:
- Imagens otimizadas e multi-stage builds
- Configurações de segurança
- Health checks
- Restart policies

### Dockerfiles
- **Dockerfile.backend** - Container Java/Spring Boot
- **Dockerfile.frontend** - Container Nginx + React build
- **Dockerfile.dev** - Ambiente de desenvolvimento

## Configuração

### Pré-requisitos
- Docker 20.10+
- Docker Compose 2.0+

### Desenvolvimento
```bash
# Subir ambiente completo
docker-compose up -d

# Logs em tempo real
docker-compose logs -f

# Parar serviços
docker-compose down
```

### Produção
```bash
# Build e deploy
docker-compose -f docker-compose.prod.yml up -d

# Escalar serviços
docker-compose -f docker-compose.prod.yml up -d --scale backend=3
```

## Volumes

### Desenvolvimento
- `./backend:/app` - Sincronização do código backend
- `./frontend:/app` - Sincronização do código frontend
- `postgres-data` - Persistência do banco de dados

### Produção
- `tdc-data` - Dados do PostgreSQL
- `tdc-logs` - Logs da aplicação
- `tdc-uploads` - Arquivos enviados pelos usuários

## Redes

### Interna (tdc-network)
- Comunicação entre containers
- Isolamento de segurança
- Resolução DNS automática

### Externa
- Exposição apenas das portas necessárias
- Proxy reverso com Nginx

## Variáveis de Ambiente

### Backend
```env
SPRING_PROFILES_ACTIVE=docker
DB_HOST=postgres
DB_PORT=5432
DB_NAME=tdc_sheets
DB_USERNAME=tdc_user
DB_PASSWORD=secure_password
JWT_SECRET=jwt_secret_key
REDIS_HOST=redis
REDIS_PORT=6379
```

### Frontend
```env
VITE_API_URL=http://localhost:8080/api
VITE_APP_TITLE=TDC Sheets
VITE_APP_VERSION=1.0.0
```

### Database
```env
POSTGRES_DB=tdc_sheets
POSTGRES_USER=tdc_user
POSTGRES_PASSWORD=secure_password
POSTGRES_INITDB_ARGS=--encoding=UTF-8 --lc-collate=C --lc-ctype=C
```

## Health Checks

### Backend
```yaml
healthcheck:
  test: ["CMD", "curl", "-f", "http://localhost:8080/actuator/health"]
  interval: 30s
  timeout: 10s
  retries: 3
  start_period: 40s
```

### Frontend
```yaml
healthcheck:
  test: ["CMD", "curl", "-f", "http://localhost:80"]
  interval: 30s
  timeout: 5s
  retries: 3
```

### Database
```yaml
healthcheck:
  test: ["CMD-SHELL", "pg_isready -U $POSTGRES_USER -d $POSTGRES_DB"]
  interval: 30s
  timeout: 5s
  retries: 5
```

## Otimizações

### Multi-stage Builds
- **Stage 1**: Build da aplicação
- **Stage 2**: Runtime otimizado
- Redução significativa do tamanho das imagens

### Cache Layers
- Otimização da ordem das instruções
- Aproveitamento do cache do Docker
- Builds mais rápidos

### Security
- Usuários não-root nos containers
- Secrets via Docker secrets ou volumes
- Scan de vulnerabilidades com Docker Scout

## Comandos Úteis

### Desenvolvimento
```bash
# Rebuild específico
docker-compose build backend

# Executar comandos no container
docker-compose exec backend bash
docker-compose exec postgres psql -U tdc_user -d tdc_sheets

# Limpar recursos
docker-compose down -v --remove-orphans
```

### Produção
```bash
# Deploy com zero downtime
docker-compose -f docker-compose.prod.yml up -d --no-deps backend

# Backup do banco
docker-compose exec postgres pg_dump -U tdc_user tdc_sheets > backup.sql

# Monitoramento
docker stats
docker-compose ps
```

## Monitoramento

### Logs Centralizados
- Configuração para envio de logs para ELK Stack
- Rotação automática de logs
- Filtragem por nível de log

### Métricas
- Exposição de métricas Prometheus
- Dashboards Grafana pré-configurados
- Alertas para situações críticas
