# TDC Sheets - Sistema de Fichas RPG

## Visão Geral
Sistema completo para criação e gerenciamento de fichas de personagem do RPG "Tabuleiro do Caos". Projeto full-stack com arquitetura moderna e escalável.

## Arquitetura do Projeto

### Estrutura Principal
```
tdc-sheets/
├── backend/                 # API Spring Boot
├── frontend/                # Interface React
├── docker/                  # Containerização
├── docs/                    # Documentação
├── scripts/                 # Automação
├── database/                # Scripts e schemas SQL
└── tests/                   # Testes end-to-end
```

## Stack Tecnológica

### Backend
- **Java 17** - Linguagem principal
- **Spring Boot 3.x** - Framework web
- **PostgreSQL** - Banco de dados relacional
- **JPA/Hibernate** - ORM
- **Spring Security + JWT** - Autenticação
- **Liquibase** - Migrations
- **Gradle** - Build tool

### Frontend
- **React 18** - Biblioteca UI
- **TypeScript** - Tipagem estática
- **Redux Toolkit** - Gerenciamento de estado
- **React Router** - Roteamento
- **Tailwind CSS** - Framework CSS utility-first
- **Vite** - Build tool

### DevOps
- **Docker + Docker Compose** - Containerização
- **GitHub Actions** - CI/CD
- **PostgreSQL** - Banco de produção

## Funcionalidades Principais

### 🧙‍♂️ Gerenciamento de Fichas
- Criação de personagens com base nas regras do TDC
- Cálculos automáticos de atributos (PV, PP, defesa)
- Sistema de níveis e progressão
- Gestão de equipamentos e inventário

### 👥 Sistema de Usuários
- Autenticação segura com JWT
- Perfis de usuário personalizáveis
- Controle de acesso baseado em roles
- Sistema de convites e grupos

### 🔗 Compartilhamento Inteligente
- Compartilhamento de fichas por link
- Permissões granulares (leitura/escrita)
- Versionamento de fichas
- Histórico de alterações

### 📊 Calculadora de Atributos
- Cálculos em tempo real
- Validação de regras do sistema
- Suporte a modificadores temporários
- Integração com equipamentos

### 🎮 Recursos para Mesa
- Modo mestre para visualização de grupo
- Compartilhamento em tempo real
- Sistema de notas e anotações
- Backup automático

<!-- TODO: Implementar funcionalidades avançadas planejadas -->
<!--
TODOs Principais por Milestone:

MILESTONE 2 - Backend Funcional:
- TODO: Implementar Spring Security com JWT (BACK-005)
- TODO: Criar sistema completo de entidades JPA (BACK-002)
- TODO: Configurar Liquibase e migrations (BACK-004)
- TODO: Implementar CRUD completo de fichas (BACK-006)
- TODO: Adicionar Swagger/OpenAPI para documentação

MILESTONE 3 - Frontend Base:
- TODO: Implementar autenticação frontend com JWT (FRONT-004)
- TODO: Criar páginas principais de gerenciamento de fichas (FRONT-005)
- TODO: Desenvolver hooks customizados para fichas (FRONT-006)
- TODO: Integrar serviços HTTP reais com backend

MILESTONE 4 - Sistema RPG Completo:
- TODO: Implementar sistema completo de atributos TDC (RPG-001)
- TODO: Criar sistema de habilidades e perícias (RPG-002)
- TODO: Desenvolver sistema de equipamentos e inventário (RPG-003)
- TODO: Implementar sistema de magia e feitiços (RPG-004)

MILESTONE 5 - Funcionalidades Avançadas:
- TODO: Sistema de compartilhamento de fichas (ADV-001)
- TODO: Validações complexas das regras TDC (ADV-002)
- TODO: Sistema de versionamento de fichas
- TODO: Modo mestre para visualização de grupo

MILESTONE 6 - Qualidade e Deploy:
- TODO: Implementar testes automatizados (TEST-001, TEST-002)
- TODO: Configurar CI/CD com GitHub Actions (DEPLOY-001)
- TODO: Otimizações de performance (PERF-001)
- TODO: Design system e acessibilidade (UX-001)
-->

## Regras do Sistema TDC

### Atributos Base
- **Força** - Poder físico e capacidade de carga
- **Agilidade** - Velocidade e destreza
- **Constituição** - Resistência e vitalidade
- **Mente** - Inteligência e conhecimento
- **Presença** - Carisma e liderança
- **Influência** - Poder social e conexões

### Arquetipos Disponíveis
- **Acadêmico** - Especialista em conhecimento
- **Acólito** - Devoto religioso
- **Combatente** - Guerreiro especializado
- **Feiticeiro** - Manipulador de magia
- **Ladino** - Especialista em subterfúgio
- **Natural** - Conectado com a natureza

### Sistema de Magia
- 10 matrizes mágicas diferentes
- Classes de feitiços (Evocação/Manipulação)
- Círculos de 1 a 8
- Pontos de poder (PP) para conjuração

## Quick Start

## Como Executar o Projeto

### Pré-requisitos
- **Java 17** ou superior
- **Node.js 18** ou superior  
- **Docker & Docker Compose**
- **PostgreSQL** (opcional, pode usar Docker)

### Opção 1: Desenvolvimento com Docker (Recomendado)
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/tdc-sheets.git
cd tdc-sheets

# Inicie todos os serviços com Docker
docker-compose up -d

# Os serviços estarão disponíveis em:
# - Frontend: http://localhost:3000
# - Backend API: http://localhost:8080
# - Database: localhost:5432
```

### Opção 2: Desenvolvimento Local

#### 1. Setup do Backend
```bash
cd backend

# Construir o projeto
./gradlew build

# Executar em modo desenvolvimento
./gradlew bootRun

# Ou executar com perfil específico
./gradlew bootRun --args='--spring.profiles.active=dev'
```

#### 2. Setup do Frontend
```bash
cd frontend

# Instalar dependências
npm install

# Executar em modo desenvolvimento
npm run dev

# Build para produção
npm run build

# Preview da build de produção
npm run preview
```

### Opção 3: Scripts de Automação
```bash
# Setup inicial do ambiente
./scripts/setup-local.sh

# Iniciar todos os serviços
./scripts/start-services.sh

# Parar todos os serviços
./scripts/stop-services.sh
```

## Comandos de Build

### Backend (Spring Boot + Gradle)
```bash
cd backend

# Build completo
./gradlew build

# Build sem testes
./gradlew build -x test

# Limpar build anterior
./gradlew clean

# Executar testes
./gradlew test

# Gerar JAR executável
./gradlew bootJar
```

### Frontend (React + Vite)
```bash
cd frontend

# Instalar dependências
npm install

# Build de desenvolvimento
npm run dev

# Build de produção
npm run build

# Verificação de tipos TypeScript
npm run type-check

# Linting
npm run lint

# Formatação de código
npm run format

# Testes
npm run test
```

## Estrutura de Comandos por Ambiente

### Desenvolvimento Local
- **Backend**: `cd backend && ./gradlew bootRun`
- **Frontend**: `cd frontend && npm run dev`
- **Database**: Docker ou PostgreSQL local

### Build de Produção
- **Backend**: `cd backend && ./gradlew build`
- **Frontend**: `cd frontend && npm run build`

### Testes
- **Backend**: `cd backend && ./gradlew test`
- **Frontend**: `cd frontend && npm run test`

### Docker (Todos os Serviços)
- **Desenvolvimento**: `docker-compose up -d`
- **Build**: `docker-compose build`
- **Parar**: `docker-compose down`

### Acesso
- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Database**: localhost:5432 (tdc_sheets)

## Estrutura de Dados

### Arquitetura do Monorepo
```
tdc-sheets/
├── backend/                 # API Spring Boot (Java 17)
│   ├── build.gradle        # Configuração de build Gradle
│   ├── src/main/java/      # Código fonte Java
│   └── src/main/resources/ # Configurações e migrations
├── frontend/                # Interface React (TypeScript)
│   ├── package.json        # Dependências Node.js
│   ├── src/                # Código fonte React/TypeScript
│   ├── tailwind.config.js  # Configuração Tailwind CSS
│   └── vite.config.ts      # Configuração Vite
├── docker/                  # Arquivos Docker
│   ├── docker-compose.yml  # Orquestração de serviços
│   └── Dockerfile.*        # Imagens customizadas
├── scripts/                 # Scripts de automação
│   ├── setup-local.sh      # Setup ambiente local
│   ├── start-services.sh   # Iniciar serviços
│   └── stop-services.sh    # Parar serviços
└── docs/                   # Documentação do projeto
```

### Tecnologias por Módulo

| Módulo | Tecnologias | Comandos |
|--------|-------------|----------|
| **Backend** | Java 17, Spring Boot, PostgreSQL, Gradle | `cd backend && ./gradlew bootRun` |
| **Frontend** | React 18, TypeScript, Tailwind CSS, Vite | `cd frontend && npm run dev` |
| **Docker** | Docker Compose, PostgreSQL, Nginx | `docker-compose up -d` |
| **Database** | PostgreSQL 15, Liquibase migrations | Gerenciado pelo backend |

### Entidades Principais
- **Ficha Personagem** - Dados centrais do personagem
- **Atributos** - Valores dos 6 atributos base
- **Habilidades** - Perícias e especializações
- **Equipamentos** - Itens e arsenal
- **Feitiços** - Magias conhecidas

### Relacionamentos
- Usuário → Fichas (1:N)
- Ficha → Atributos (1:6)
- Ficha → Arquetipos (N:M)
- Ficha → Equipamentos (1:N)

## Contribuição

### Guidelines
1. Siga os padrões de código estabelecidos
2. Escreva testes para novas funcionalidades
3. Use conventional commits
4. Mantenha o README atualizado

### Processo de Desenvolvimento
1. **Fork** do repositório
2. **Branch** para feature/bugfix
3. **Desenvolvimento** com testes
4. **Pull Request** com descrição detalhada
5. **Code Review** pela equipe
6. **Merge** após aprovação

## Licença
MIT License - veja [LICENSE](LICENSE) para detalhes.

## Contato
- **Projeto**: Tabuleiro do Caos RPG Fichas
- **Versão**: 0.0.1
- **Maintainer**: Victor Gabriel Soares
- **Issues**: GitHub Issues
- **Discord**: [Link do servidor](https://discord.gg/KDz8rdBh6g)