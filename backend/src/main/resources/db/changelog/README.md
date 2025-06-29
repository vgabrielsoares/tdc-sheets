# Database Changelog - Liquibase Migrations

## Visão Geral
Controle de versão do banco de dados utilizando Liquibase para migrations seguras e rastreáveis.

## Estrutura de Changelogs

```
db/changelog/
├── db.changelog-master.xml          # Arquivo principal
├── v1.0/                           # Versão 1.0 - Schema inicial
│   ├── 001-create-users.xml        # Tabelas de usuários
│   ├── 002-create-fichas.xml       # Tabelas de fichas
│   ├── 003-create-game-data.xml    # Dados do jogo (arquetipos, etc)
│   ├── 004-create-indexes.xml      # Índices de performance
│   └── 005-seed-data.xml           # Dados iniciais
├── v1.1/                           # Versão 1.1 - Compartilhamento
│   ├── 006-create-sharing.xml      # Sistema de compartilhamento
│   └── 007-update-permissions.xml  # Permissões granulares
└── v1.2/                           # Versão 1.2 - Recursos avançados
    ├── 008-create-notifications.xml # Sistema de notificações
    └── 009-add-audit-logs.xml      # Logs de auditoria
```

## Arquivo Master

### db.changelog-master.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog
    xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
    http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.20.xsd">

    <!-- Versão 1.0 - Schema Inicial -->
    <include file="db/changelog/v1.0/001-create-users.xml"/>
    <include file="db/changelog/v1.0/002-create-fichas.xml"/>
    <include file="db/changelog/v1.0/003-create-game-data.xml"/>
    <include file="db/changelog/v1.0/004-create-indexes.xml"/>
    <include file="db/changelog/v1.0/005-seed-data.xml"/>

    <!-- Versão 1.1 - Sistema de Compartilhamento -->
    <include file="db/changelog/v1.1/006-create-sharing.xml"/>
    <include file="db/changelog/v1.1/007-update-permissions.xml"/>

    <!-- Versão 1.2 - Recursos Avançados -->
    <include file="db/changelog/v1.2/008-create-notifications.xml"/>
    <include file="db/changelog/v1.2/009-add-audit-logs.xml"/>

</databaseChangeLog>
```

## Migrations da Versão 1.0

### 001-create-users.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                   http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.20.xsd">

    <changeSet id="001-001" author="tdc-team">
        <comment>Criar tabela de usuários</comment>
        
        <createTable tableName="user">
            <column name="id" type="BIGINT" autoIncrement="true">
                <constraints primaryKey="true" primaryKeyName="pk_user"/>
            </column>
            <column name="username" type="VARCHAR(50)">
                <constraints nullable="false" unique="true"/>
            </column>
            <column name="email" type="VARCHAR(255)">
                <constraints nullable="false" unique="true"/>
            </column>
            <column name="password_hash" type="VARCHAR(255)">
                <constraints nullable="false"/>
            </column>
            <column name="role" type="VARCHAR(20)" defaultValue="USER">
                <constraints nullable="false"/>
            </column>
            <column name="active" type="BOOLEAN" defaultValueBoolean="true">
                <constraints nullable="false"/>
            </column>
            <column name="created_at" type="TIMESTAMP" defaultValueComputed="CURRENT_TIMESTAMP">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="TIMESTAMP"/>
        </createTable>

        <addCheckConstraint tableName="user" constraintName="chk_user_role"
                           checkCondition="role IN ('USER', 'MASTER', 'ADMIN')"/>
    </changeSet>

    <changeSet id="001-002" author="tdc-team">
        <comment>Criar tabela de authorities</comment>
        
        <createTable tableName="authorities">
            <column name="id" type="BIGINT" autoIncrement="true">
                <constraints primaryKey="true" primaryKeyName="pk_authorities"/>
            </column>
            <column name="authority" type="VARCHAR(50)">
                <constraints nullable="false" unique="true"/>
            </column>
        </createTable>
    </changeSet>

    <changeSet id="001-003" author="tdc-team">
        <comment>Criar tabela de associação user_authority</comment>
        
        <createTable tableName="user_authority">
            <column name="user_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
            <column name="authority_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
        </createTable>

        <addPrimaryKey tableName="user_authority" 
                      columnNames="user_id,authority_id" 
                      constraintName="pk_user_authority"/>

        <addForeignKeyConstraint baseTableName="user_authority" 
                               baseColumnNames="user_id"
                               referencedTableName="user" 
                               referencedColumnNames="id"
                               constraintName="fk_user_authority_user"
                               onDelete="CASCADE"/>

        <addForeignKeyConstraint baseTableName="user_authority" 
                               baseColumnNames="authority_id"
                               referencedTableName="authorities" 
                               referencedColumnNames="id"
                               constraintName="fk_user_authority_authority"
                               onDelete="CASCADE"/>
    </changeSet>

</databaseChangeLog>
```

### 002-create-fichas.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
                   http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-4.20.xsd">

    <changeSet id="002-001" author="tdc-team">
        <comment>Criar tabela principal de fichas</comment>
        
        <createTable tableName="ficha_personagem">
            <column name="id" type="BIGINT" autoIncrement="true">
                <constraints primaryKey="true" primaryKeyName="pk_ficha_personagem"/>
            </column>
            <column name="nome_personagem" type="VARCHAR(100)">
                <constraints nullable="false"/>
            </column>
            <column name="user_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
            <column name="background" type="TEXT"/>
            <column name="origem_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
            <column name="linhagem_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
            <column name="experiencia" type="INTEGER" defaultValueNumeric="0">
                <constraints nullable="false"/>
            </column>
            <column name="habilidade_assinatura" type="BIGINT">
                <constraints nullable="false"/>
            </column>
            <column name="nivel_sorte" type="INTEGER" defaultValueNumeric="0">
                <constraints nullable="false"/>
            </column>
            <column name="tamanho" type="VARCHAR(20)"/>
            <column name="created_at" type="TIMESTAMP" defaultValueComputed="CURRENT_TIMESTAMP">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="TIMESTAMP"/>
            <column name="versao" type="INTEGER" defaultValueNumeric="1"/>
        </createTable>

        <addForeignKeyConstraint baseTableName="ficha_personagem" 
                               baseColumnNames="user_id"
                               referencedTableName="user" 
                               referencedColumnNames="id"
                               constraintName="fk_ficha_user"/>

        <addCheckConstraint tableName="ficha_personagem" 
                           constraintName="chk_experiencia_positiva"
                           checkCondition="experiencia >= 0"/>

        <addCheckConstraint tableName="ficha_personagem" 
                           constraintName="chk_nivel_sorte_range"
                           checkCondition="nivel_sorte >= 0 AND nivel_sorte <= 10"/>
    </changeSet>

    <changeSet id="002-002" author="tdc-team">
        <comment>Criar tabela de atributos do personagem</comment>
        
        <createTable tableName="atributos_personagem">
            <column name="id" type="BIGINT" autoIncrement="true">
                <constraints primaryKey="true" primaryKeyName="pk_atributos_personagem"/>
            </column>
            <column name="ficha_personagem_id" type="BIGINT">
                <constraints nullable="false"/>
            </column>
            <column name="atributo" type="VARCHAR(20)">
                <constraints nullable="false"/>
            </column>
            <column name="valor" type="INTEGER">
                <constraints nullable="false"/>
            </column>
            <column name="valor_temp" type="INTEGER"/>
            <column name="modificador_racial" type="INTEGER" defaultValueNumeric="0"/>
            <column name="modificador_equipamento" type="INTEGER" defaultValueNumeric="0"/>
            <column name="modificador_temporario" type="INTEGER" defaultValueNumeric="0"/>
            <column name="created_at" type="TIMESTAMP" defaultValueComputed="CURRENT_TIMESTAMP">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="TIMESTAMP"/>
        </createTable>

        <addForeignKeyConstraint baseTableName="atributos_personagem" 
                               baseColumnNames="ficha_personagem_id"
                               referencedTableName="ficha_personagem" 
                               referencedColumnNames="id"
                               constraintName="fk_atributos_ficha"
                               onDelete="CASCADE"/>

        <addUniqueConstraint tableName="atributos_personagem"
                           columnNames="ficha_personagem_id,atributo"
                           constraintName="uk_ficha_atributo"/>

        <addCheckConstraint tableName="atributos_personagem" 
                           constraintName="chk_atributos_range"
                           checkCondition="valor BETWEEN 1 AND 20"/>

        <addCheckConstraint tableName="atributos_personagem" 
                           constraintName="chk_valor_temp_range"
                           checkCondition="valor_temp IS NULL OR valor_temp BETWEEN 1 AND 20"/>

        <addCheckConstraint tableName="atributos_personagem" 
                           constraintName="chk_atributo_tipo"
                           checkCondition="atributo IN ('AGILIDADE', 'CONSTITUICAO', 'FORCA', 'INFLUENCIA', 'MENTE', 'PRESENCA')"/>
    </changeSet>

    <changeSet id="002-003" author="tdc-team">
        <comment>Criar tabela de PV e PP do personagem</comment>
        
        <createTable tableName="pv_pp_personagem">
            <column name="id" type="BIGINT" autoIncrement="true">
                <constraints primaryKey="true" primaryKeyName="pk_pv_pp_personagem"/>
            </column>
            <column name="ficha_personagem_id" type="BIGINT">
                <constraints nullable="false" unique="true"/>
            </column>
            <column name="pv_maximo" type="INTEGER" defaultValueNumeric="15">
                <constraints nullable="false"/>
            </column>
            <column name="pv_atual" type="INTEGER" defaultValueNumeric="15">
                <constraints nullable="false"/>
            </column>
            <column name="pv_temporario" type="INTEGER"/>
            <column name="pp_maximo" type="INTEGER" defaultValueNumeric="2">
                <constraints nullable="false"/>
            </column>
            <column name="pp_atual" type="INTEGER" defaultValueNumeric="2">
                <constraints nullable="false"/>
            </column>
            <column name="pp_temporario" type="INTEGER"/>
            <column name="limite_pp" type="INTEGER"/>
            <column name="limite_pp_bonus" type="INTEGER"/>
            <column name="created_at" type="TIMESTAMP" defaultValueComputed="CURRENT_TIMESTAMP">
                <constraints nullable="false"/>
            </column>
            <column name="updated_at" type="TIMESTAMP"/>
        </createTable>

        <addForeignKeyConstraint baseTableName="pv_pp_personagem" 
                               baseColumnNames="ficha_personagem_id"
                               referencedTableName="ficha_personagem" 
                               referencedColumnNames="id"
                               constraintName="fk_pv_pp_ficha"
                               onDelete="CASCADE"/>

        <addCheckConstraint tableName="pv_pp_personagem" 
                           constraintName="chk_pv_positivo"
                           checkCondition="pv_maximo > 0 AND pv_atual >= 0"/>

        <addCheckConstraint tableName="pv_pp_personagem" 
                           constraintName="chk_pp_positivo"
                           checkCondition="pp_maximo >= 0 AND pp_atual >= 0"/>
    </changeSet>

</databaseChangeLog>
```

## Convenções de Naming

### ChangeSet IDs
- Formato: `{version}-{sequence}` (ex: `001-001`, `002-003`)
- Version: Número da versão principal (001, 002, etc.)
- Sequence: Sequência dentro da versão

### Arquivos
- Prefixo numérico para ordem: `001-`, `002-`, etc.
- Descrição clara da funcionalidade
- Extensão `.xml` para changelogs Liquibase

### Constraints
- **Primary Key**: `pk_{table_name}`
- **Foreign Key**: `fk_{table_name}_{referenced_table}`
- **Unique**: `uk_{table_name}_{column}`
- **Check**: `chk_{table_name}_{description}`
- **Index**: `idx_{table_name}_{column}`

## Rollback Strategies

### Automatic Rollback
```xml
<changeSet id="002-004" author="tdc-team">
    <comment>Adicionar coluna email_verified</comment>
    
    <addColumn tableName="user">
        <column name="email_verified" type="BOOLEAN" defaultValueBoolean="false">
            <constraints nullable="false"/>
        </column>
    </addColumn>
    
    <rollback>
        <dropColumn tableName="user" columnName="email_verified"/>
    </rollback>
</changeSet>
```

### Custom Rollback
```xml
<changeSet id="002-005" author="tdc-team">
    <comment>Migrar dados de configuração</comment>
    
    <sql>
        UPDATE user SET role = 'ADMIN' WHERE username = 'admin';
        UPDATE user SET role = 'MASTER' WHERE email LIKE '%@gm.%';
    </sql>
    
    <rollback>
        <sql>
            UPDATE user SET role = 'USER' WHERE role IN ('ADMIN', 'MASTER');
        </sql>
    </rollback>
</changeSet>
```

## Contextos e Profiles

### Contextos por Ambiente
```xml
<changeSet id="005-001" author="tdc-team" context="dev">
    <comment>Dados de teste - apenas desenvolvimento</comment>
    
    <insert tableName="user">
        <column name="username" value="test_user"/>
        <column name="email" value="test@example.com"/>
        <column name="password_hash" value="$2a$10$example_hash"/>
        <column name="role" value="USER"/>
    </insert>
</changeSet>

<changeSet id="005-002" author="tdc-team" context="!prod">
    <comment>Dados de desenvolvimento e teste</comment>
    
    <loadData file="db/changelog/data/dev-users.csv" tableName="user"/>
</changeSet>
```

## Validação e Testes

### Pre-conditions
```xml
<changeSet id="006-001" author="tdc-team">
    <preConditions onFail="MARK_RAN">
        <not>
            <tableExists tableName="compartilhamento_ficha"/>
        </not>
    </preConditions>
    
    <comment>Criar tabela de compartilhamento</comment>
    
    <createTable tableName="compartilhamento_ficha">
        <!-- definição da tabela -->
    </createTable>
</changeSet>
```

### Validação de Dados
```xml
<changeSet id="007-001" author="tdc-team">
    <comment>Validar integridade de dados antes da migração</comment>
    
    <preConditions onFail="HALT">
        <sqlCheck expectedResult="0">
            SELECT COUNT(*) FROM ficha_personagem 
            WHERE user_id NOT IN (SELECT id FROM user)
        </sqlCheck>
    </preConditions>
    
    <!-- Migration steps -->
</changeSet>
```

## Melhores Práticas

### Performance
- Criar índices após inserções em massa
- Usar `runInTransaction="false"` para operações grandes
- Quebrar alterações grandes em changesets menores

### Segurança
- Nunca incluir senhas em plain text
- Usar contextos para dados sensíveis
- Validar permissões antes de migrations

### Manutenibilidade
- Comentários descritivos em todos os changesets
- Rollbacks testados para mudanças críticas
- Versionamento semântico para changelogs

### Monitoramento
```bash
# Verificar status das migrations
./gradlew liquibaseStatus

# Gerar SQL de preview
./gradlew liquibaseUpdateSQL

# Executar migrations
./gradlew liquibaseUpdate

# Rollback para tag específica
./gradlew liquibaseRollback -PliquibaseCommandValue=v1.0
```
