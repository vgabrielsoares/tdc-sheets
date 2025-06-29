# Tabuleiro do Caos RPG - Fichas

## Sumário
- [Visão Geral do Projeto](#visão-geral-do-projeto)
- [Funcionalidades e Requisitos](#funcionalidades-e-requisitos)
  - [Cadastro de Fichas](#cadastro-de-fichas)
  - [Sistema de Usuários/Autenticação](#sistema-de-usuáriosautenticação)
  - [Gerenciamento de Fichas](#gerenciamento-de-fichas)
  - [Compartilhamento de Fichas](#compartilhamento-de-fichas)
  - [Cálculos Automáticos](#cálculos-automáticos)
- [Especificações Técnicas](#especificações-técnicas)
  - [Stack Tecnológica](#stack-tecnológica)
  - [Justificativas das Escolhas](#justificativas-das-escolhas)
  - [Arquitetura Backend](#arquitetura-backend)
  - [DevOps/Deploy](#devopsdeploy)

## Visão Geral do Projeto

Sistema para criar e gerenciar fichas de personagem para o sistema de RPG "Tabuleiro do Caos".

---

## Funcionalidades e Requisitos

### Cadastro de Fichas
- CRUD completo para fichas de personagem

### Sistema de Usuários/Autenticação
- Cadastro e gerenciamento de usuários
- Autenticação via JWT
- Controle de acesso baseado em papéis (admin, jogador, etc.)

### Gerenciamento de Fichas
- Listagem, busca e filtragem de fichas
- Edição de atributos e características

### Compartilhamento de Fichas
Controle granular:
- Compartilhamento somente visualização
- Compartilhamento com edição
- Compartilhamento via link temporário

### Cálculos Automáticos
- Cálculo automático de atributos (ex: pontos de vida, mana, etc.)
- Regras personalizáveis para cálculos
- Atualização em tempo real durante a edição da ficha
- Validação de regras de jogo (ex: limites de atributos, etc.)

---

## Especificações Técnicas

### Stack Tecnológica

#### Backend
| Componente | Tecnologia |
|------------|------------|
| Linguagem | Java |
| Framework | Spring Boot |
| Banco de Dados | PostgreSQL |
| ORM | JPA/Hibernate |
| Migrations | Liquibase |
| Build Tool | Gradle |

#### Frontend
| Componente | Tecnologia |
|------------|------------|
| Linguagem | TypeScript |
| Framework | React |
| Gerenciamento de Estado | Redux Toolkit |
| Roteamento | React Router |
| Estilização | CSS Puro |
| Build Tool | Vite |

### Justificativas das Escolhas

#### Backend
- **PostgreSQL**: Banco de dados robusto, com suporte a JSON e recursos avançados.
- **JPA/Hibernate**: Mapeamento objeto-relacional que aumenta a produtividade, oferece abstração do banco de dados e se integra bem com Spring Boot.
- **Gradle**: Sistema de build flexível com suporte a multi-projetos, caching inteligente e integração perfeita com Spring Boot, tornando o processo de build mais rápido e configurável.

#### Frontend
- **Redux Toolkit**: Gerenciamento de estado previsível, com ferramentas de debug excelentes e adequado para a complexidade esperada do projeto.
- **React Router**: Solução padrão da comunidade para roteamento, com suporte a code-splitting.
- **CSS Puro**: Máximo controle e performance, sem overhead de bibliotecas.
- **Vite**: Build tool moderna com Hot Module Replacement extremamente rápido.

### Arquitetura Backend

#### Segurança: Spring Security com JWT
- Autenticação stateless ideal para arquitetura frontend/backend separada
- Controle granular de permissões para gerenciamento de fichas
- Padrão da indústria com ampla documentação e suporte

#### Documentação: OpenAPI/Swagger
- Dashboard interativo para testar endpoints da API
- Autodocumentação via anotações no código
- Facilita integração entre equipes frontend e backend

#### Resiliência: Resilience4j
- Implementação de circuit breakers para prevenir falhas em cascata
- Retry automatizado para operações falhas
- Rate limiting para proteção de APIs
- Fallback handlers para degradação graciosa do sistema
- Bulkheads para isolamento de falhas

#### Validação: Bean Validation
- Sistema declarativo de validações via anotações
- Centralização das regras de validação para campos das fichas
- Mensagens de erro customizáveis para melhor experiência do usuário

#### Arquitetura API: REST
- Simplicidade e padrão estabelecido
- Facilidade de implementação e manutenção
- Adequada para a complexidade das operações CRUD de fichas

### DevOps/Deploy

#### Containerização: Docker + Docker Compose
- Ambiente de desenvolvimento consistente e reproduzível
- Simplifica o setup com todos os serviços (backend, frontend, banco de dados)
- Facilita integração entre componentes durante o desenvolvimento

#### Versionamento: GitHub + GitFlow
- Branches separadas para features, releases e hotfixes
- Proteção da branch principal com revisão de código
- Histórico de desenvolvimento claro e organizado