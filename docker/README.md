# Docker - Containerização do TDC Sheets

## Visão Geral
Configurações Docker para containerização completa do ambiente de desenvolvimento e produção do sistema TDC Sheets.

## Estrutura de Containers

### Serviços
- **tdc-backend** - API Spring Boot
- **tdc-frontend** - Interface React (Nginx)
- **tdc-database** - PostgreSQL 15
- **tdc-redis** - Cache e sessões (opcional)

## Arquivos

### docker-compose.yml
Orquestração completa para desenvolvimento local com:
- Hot reload habilitado
- Volumes para sincronização de código
- Redes internas para comunicação entre serviços
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
