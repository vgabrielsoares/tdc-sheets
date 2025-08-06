# Main - Código Principal do Backend

Esta é a pasta principal do código-fonte do backend da aplicação TDC Sheets.

## 📁 Estrutura

```
src/main/
├── java/                       # Código-fonte Java
│   └── com/tdc/sheets/        # Pacote principal da aplicação
│       ├── TdcSheetsApplication.java  # Classe principal Spring Boot
│       ├── config/            # Configurações
│       ├── controller/        # Controllers REST
│       ├── dto/              # Data Transfer Objects
│       ├── entity/           # Entidades JPA
│       ├── repository/       # Repositórios
│       ├── service/          # Lógica de negócio
│       ├── security/         # Configurações de segurança
│       ├── exception/        # Tratamento de exceções
│       └── validation/       # Validações customizadas
└── resources/                 # Recursos não-Java
    ├── application.yml       # Configurações da aplicação
    ├── application-dev.yml   # Configurações de desenvolvimento
    ├── application-prod.yml  # Configurações de produção
    ├── db/                   # Scripts de banco de dados
    │   └── changelog/        # Migrations Liquibase
    ├── static/               # Arquivos estáticos
    └── templates/            # Templates (se necessário)
```

## 🔧 Configurações Principais

### application.yml
Contém as configurações principais da aplicação:
- Configurações do banco de dados
- Configurações de segurança (JWT)
- Configurações do Liquibase
- Configurações de logging
- Configurações de CORS

### Perfis de Ambiente
- **dev**: Desenvolvimento local
- **test**: Execução de testes
- **prod**: Ambiente de produção

## 🏗️ Arquitetura das Camadas

### Controller Layer (REST)
- Recebe requisições HTTP
- Valida parâmetros de entrada
- Chama serviços apropriados
- Retorna respostas HTTP

### Service Layer (Business Logic)
- Contém regras de negócio
- Orquestra operações complexas
- Gerencia transações
- Implementa validações de domínio

### Repository Layer (Data Access)
- Abstrai acesso aos dados
- Implementa queries customizadas
- Gerencia persistência
- Otimiza consultas ao banco

### Entity Layer (Domain Model)
- Representa o modelo de domínio
- Mapeia tabelas do banco
- Define relacionamentos
- Contém validações básicas

## 🛡️ Segurança

### JWT Authentication
- Tokens stateless
- Refresh tokens
- Expiração configurável
- Claims customizados

### Authorization
- Role-based access control
- Method-level security
- Resource-based permissions
- Filtros de segurança

## 📊 Banco de Dados

### Liquibase Migrations
Localização: `src/main/resources/db/changelog/`
- Versionamento de esquema
- Rollback automático
- Controle de mudanças
- Ambientes múltiplos

### JPA/Hibernate
- Lazy loading otimizado
- Query optimization
- Connection pooling
- Transaction management

## 🔍 Monitoramento

### Logging
- Structured logging (JSON)
- Level-based configuration
- Performance metrics
- Error tracking

### Health Checks
- Database connectivity
- External services
- Memory usage
- Disk space

## 🚀 Build e Deploy

### Gradle Tasks
```bash
# Compilar projeto
./gradlew build

# Executar testes
./gradlew test

# Gerar JAR
./gradlew bootJar

# Executar aplicação
./gradlew bootRun
```

### Docker
```bash
# Build da imagem
docker build -t tdc-sheets-backend .

# Executar container
docker run -p 8080:8080 tdc-sheets-backend
```

## 📋 Convenções

### Nomenclatura de Packages
- `config`: Configurações do Spring
- `controller`: REST controllers
- `dto`: Data Transfer Objects
- `entity`: Entidades JPA
- `repository`: Repositórios de dados
- `service`: Serviços de negócio
- `security`: Configurações de segurança
- `exception`: Tratamento de exceções
- `validation`: Validações customizadas

### Nomenclatura de Classes
- Controllers: `{Entity}Controller`
- Services: `{Entity}Service`
- Repositories: `{Entity}Repository`
- DTOs: `{Entity}{Type}DTO` (ex: UserCreateDTO)
- Entities: `{EntityName}` (ex: User, FichaPersonagem)

### Nomenclatura de Métodos
- Controllers: HTTP verbs (`getUser`, `createUser`, `updateUser`, `deleteUser`)
- Services: Business operations (`calculateAttributes`, `validateFicha`)
- Repositories: Data operations (`findByUserId`, `findActiveCharacters`)

## 📚 Documentação Adicional

Para documentação específica de cada camada, consulte os READMEs nas respectivas pastas:
- [Camadas da Aplicação](java/com/tdc/sheets/README.md)
- [Migrations do Banco](resources/db/changelog/README.md)
