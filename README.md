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

### Desenvolvimento Local
```bash
# Clone o repositório
git clone https://github.com/seu-usuario/tdc-sheets.git
cd tdc-sheets

# Configure o ambiente
./scripts/setup-dev.sh

# Inicie os serviços
docker-compose up -d

# Backend
cd backend
./gradlew bootRun

# Frontend
cd frontend
npm run dev
```

### Acesso
- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **Database**: localhost:5432 (tdc_sheets)

## Estrutura de Dados

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

## Roadmap

### v1.0 - MVP ✅
- [x] CRUD básico de fichas
- [x] Sistema de autenticação
- [x] Cálculos de atributos
- [x] Interface responsiva

### v1.1 - Compartilhamento
- [ ] Sistema de compartilhamento
- [ ] Permissões granulares
- [ ] Links temporários
- [ ] Notificações

### v1.2 - Recursos Avançados
- [ ] Sistema de grupos/mesas
- [ ] Chat integrado
- [ ] Backup na nuvem
- [ ] Aplicativo mobile

### v2.0 - Automação
- [ ] Integração com dados do livro
- [ ] IA para sugestões
- [ ] Sistema de campanhas
- [ ] Marketplace de conteúdo

## Licença
MIT License - veja [LICENSE](LICENSE) para detalhes.

## Contato
- **Projeto**: Tabuleiro do Caos RPG Fichas
- **Versão**: 0.0.1
- **Maintainer**: Victor Gabriel Soares
- **Issues**: GitHub Issues
- **Discord**: [Link do servidor](https://discord.gg/KDz8rdBh6g)