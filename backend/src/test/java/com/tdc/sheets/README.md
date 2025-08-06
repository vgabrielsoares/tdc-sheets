# Testes do Backend - TDC Sheets

Esta pasta contém todos os testes unitários e de integração do backend da aplicação TDC Sheets.

## 📁 Estrutura de Testes

```
src/test/java/com/tdc/sheets/
├── controller/          # Testes dos controllers REST
├── service/            # Testes da camada de serviço
├── repository/         # Testes dos repositórios JPA
├── security/           # Testes de segurança e autenticação
├── dto/               # Testes de DTOs e validações
├── entity/            # Testes das entidades JPA
├── config/            # Testes de configurações
├── exception/         # Testes de tratamento de exceções
├── validation/        # Testes de validações customizadas
└── integration/       # Testes de integração end-to-end
```

## 🧪 Tipos de Teste

### Testes Unitários
- **Finalidade**: Testar componentes isolados
- **Tecnologias**: JUnit 5, Mockito, AssertJ
- **Padrão**: Cada classe deve ter sua classe de teste correspondente
- **Nomenclatura**: `ClasseTesteTest.java`

### Testes de Integração
- **Finalidade**: Testar interação entre componentes
- **Tecnologias**: Spring Boot Test, TestContainers
- **Localização**: Pasta `integration/`
- **Nomenclatura**: `ClasseTesteIntegrationTest.java`

### Testes de Repository
- **Finalidade**: Testar operações de banco de dados
- **Tecnologias**: @DataJpaTest, H2 Database
- **Características**: 
  - Transações rollback automático
  - Carregamento apenas dos beans JPA

### Testes de Controller
- **Finalidade**: Testar endpoints REST
- **Tecnologias**: @WebMvcTest, MockMvc
- **Características**:
  - Mock da camada de serviço
  - Testes de serialização/deserialização JSON
  - Validação de status codes e responses

## 🛠️ Configuração de Testes

### Dependências Principais
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

### Perfil de Teste
```yaml
# application-test.yml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  liquibase:
    enabled: false
```

## 📝 Exemplos de Teste

### Teste de Service
```java
@ExtendWith(MockitoExtension.class)
class CharacterServiceTest {
    
    @Mock
    private CharacterRepository characterRepository;
    
    @InjectMocks
    private CharacterService characterService;
    
    @Test
    @DisplayName("Deve criar personagem com sucesso")
    void shouldCreateCharacterSuccessfully() {
        // Given
        CharacterCreateDTO dto = new CharacterCreateDTO("Aragorn", "Ranger");
        Character character = new Character("Aragorn", "Ranger");
        
        when(characterRepository.save(any(Character.class))).thenReturn(character);
        
        // When
        CharacterResponseDTO result = characterService.createCharacter(dto);
        
        // Then
        assertThat(result.getName()).isEqualTo("Aragorn");
        verify(characterRepository).save(any(Character.class));
    }
}
```

### Teste de Controller
```java
@WebMvcTest(CharacterController.class)
class CharacterControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private CharacterService characterService;
    
    @Test
    @DisplayName("Deve retornar personagem por ID")
    void shouldReturnCharacterById() throws Exception {
        // Given
        Long characterId = 1L;
        CharacterResponseDTO character = new CharacterResponseDTO(characterId, "Aragorn", "Ranger");
        
        when(characterService.findById(characterId)).thenReturn(character);
        
        // When & Then
        mockMvc.perform(get("/api/characters/{id}", characterId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(characterId))
            .andExpect(jsonPath("$.name").value("Aragorn"))
            .andExpect(jsonPath("$.characterClass").value("Ranger"));
    }
}
```

### Teste de Repository
```java
@DataJpaTest
class CharacterRepositoryTest {
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private CharacterRepository characterRepository;
    
    @Test
    @DisplayName("Deve encontrar personagem por usuário")
    void shouldFindCharactersByUser() {
        // Given
        User user = new User("player@example.com");
        entityManager.persistAndFlush(user);
        
        Character character = new Character("Legolas", "Archer");
        character.setUser(user);
        entityManager.persistAndFlush(character);
        
        // When
        List<Character> characters = characterRepository.findByUserId(user.getId());
        
        // Then
        assertThat(characters).hasSize(1);
        assertThat(characters.get(0).getName()).isEqualTo("Legolas");
    }
}
```

### Teste de Integração
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class CharacterIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    @DisplayName("Deve criar e buscar personagem via API")
    void shouldCreateAndRetrieveCharacterViaAPI() {
        // Given
        CharacterCreateDTO createDto = new CharacterCreateDTO("Gimli", "Warrior");
        
        // When - Criar personagem
        ResponseEntity<CharacterResponseDTO> createResponse = restTemplate.postForEntity(
            "/api/characters", createDto, CharacterResponseDTO.class);
        
        // Then
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long characterId = createResponse.getBody().getId();
        
        // When - Buscar personagem
        ResponseEntity<CharacterResponseDTO> getResponse = restTemplate.getForEntity(
            "/api/characters/" + characterId, CharacterResponseDTO.class);
        
        // Then
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().getName()).isEqualTo("Gimli");
    }
}
```

## 🎯 Convenções e Boas Práticas

### Nomenclatura
- **Classes de teste**: `{ClassName}Test.java`
- **Testes de integração**: `{ClassName}IntegrationTest.java`
- **Métodos de teste**: `should{ExpectedBehavior}When{StateUnderTest}`
- **Display names**: Descrições em português claro

### Estrutura de Teste (Given-When-Then)
```java
@Test
@DisplayName("Descrição clara do comportamento esperado")
void shouldDoSomethingWhenCondition() {
    // Given - Preparação dos dados de teste
    // When - Execução da ação a ser testada
    // Then - Verificação dos resultados
}
```

### Cobertura de Testes
- **Meta**: Mínimo 80% de cobertura de código
- **Foco**: Regras de negócio e casos críticos
- **Ferramenta**: JaCoCo para relatórios de cobertura

### Dados de Teste
- Usar **builders** ou **factories** para criar objetos de teste
- Evitar **dados hardcoded** repetitivos
- Preferir **@TestConfiguration** para configurações específicas

## 🚀 Execução dos Testes

### Via Maven
```bash
# Executar todos os testes
./mvnw test

# Executar apenas testes unitários
./mvnw test -Dtest="**/*Test"

# Executar apenas testes de integração
./mvnw test -Dtest="**/*IntegrationTest"

# Gerar relatório de cobertura
./mvnw jacoco:report
```

### Via IDE
- **IntelliJ IDEA**: Botão direito na pasta → "Run Tests in..."
- **Eclipse**: Botão direito na pasta → "Run As" → "JUnit Test"

## 📊 Relatórios

### JaCoCo (Cobertura)
- **Localização**: `target/site/jacoco/index.html`
- **Configuração**: `pom.xml` - plugin JaCoCo

### Surefire (Resultados)
- **Localização**: `target/surefire-reports/`
- **Formato**: XML e TXT

## 🔧 Configurações Específicas

### TestContainers
```java
@Testcontainers
class DatabaseIntegrationTest {
    
    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");
    
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
```

### Perfis de Teste
```java
@ActiveProfiles("test")
@SpringBootTest
class ServiceIntegrationTest {
    // Testes com perfil específico
}
```

## 📚 Recursos Adicionais

- [Spring Boot Testing Documentation](https://spring.io/guides/gs/testing-web/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
- [TestContainers Documentation](https://www.testcontainers.org/)
- [AssertJ Documentation](https://assertj.github.io/doc/)
