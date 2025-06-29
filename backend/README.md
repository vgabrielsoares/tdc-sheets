# Backend - Tabuleiro do Caos RPG Fichas

## Visão Geral
Backend desenvolvido em Java com Spring Boot para gerenciamento de fichas de personagem do sistema de RPG "Tabuleiro do Caos".

## Stack Tecnológica

### Core
- **Java 17** - Versão LTS com recursos modernos
- **Spring Boot 3.1** - Framework principal para desenvolvimento web
- **Gradle** - Ferramenta de build e gerenciamento de dependências

### Banco de Dados
- **PostgreSQL** - Banco de dados principal
- **JPA/Hibernate** - ORM para mapeamento objeto-relacional
- **Liquibase** - Controle de versão e migrations do banco

### Segurança
- **Spring Security** - Framework de segurança
- **JWT (JSON Web Tokens)** - Autenticação stateless
- **Bean Validation** - Validação declarativa de dados

### Resiliência e Monitoramento
- **Resilience4j** - Circuit breakers, retry, rate limiting
- **OpenAPI/Swagger** - Documentação automática da API
- **Spring Boot Actuator** - Métricas e health checks

## Estrutura de Diretórios

```
backend/
├── src/main/java/com/tdc/sheets/
│   ├── config/          # Configurações do Spring
│   ├── controller/      # Controllers REST
│   ├── dto/            # Data Transfer Objects
│   ├── entity/         # Entidades JPA
│   ├── repository/     # Repositórios de dados
│   ├── service/        # Lógica de negócio
│   ├── security/       # Configurações de segurança
│   ├── exception/      # Tratamento de exceções
│   └── validation/     # Validadores customizados
├── src/main/resources/
│   ├── db/changelog/   # Scripts Liquibase
│   └── application.yml # Configurações da aplicação
└── src/test/           # Testes unitários e integração
```

## Funcionalidades Principais

### Gerenciamento de Fichas
- CRUD completo para fichas de personagem
- Cálculos automáticos de atributos (PV, PP, defesa)
- Validação de regras do sistema RPG
- Versionamento de fichas

### Sistema de Usuários
- Cadastro e autenticação de usuários
- Controle de acesso baseado em papéis
- Compartilhamento de fichas (visualização/edição)
- Links temporários para compartilhamento

### API REST
- Endpoints RESTful seguindo padrões da indústria
- Documentação automática com Swagger
- Versionamento de API
- Tratamento padronizado de erros

## Configuração

### Pré-requisitos
- Java 17+
- PostgreSQL 13+
- Gradle 7+

### Variáveis de Ambiente
```bash
DB_USERNAME=tdc_user
DB_PASSWORD=tdc_pass
DB_URL=jdbc:postgresql://localhost:5432/tdc_sheets
JWT_SECRET=your_jwt_secret_key
```

### Executar
```bash
./gradlew bootRun
```

## Arquitetura

### Padrões Utilizados
- **Repository Pattern** - Abstração da camada de dados
- **Service Layer** - Centralização da lógica de negócio
- **DTO Pattern** - Transferência de dados entre camadas
- **Exception Handling** - Tratamento centralizado de erros

### Princípios SOLID
- **Single Responsibility** - Cada classe tem uma responsabilidade específica
- **Open/Closed** - Extensível sem modificação
- **Dependency Inversion** - Inversão de dependências com Spring IoC

## Testes
- Testes unitários com JUnit 5
- Testes de integração com TestContainers
- Testes de API com MockMvc
- Cobertura de código com JaCoCo
