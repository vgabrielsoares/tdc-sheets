# Main - Camadas da Aplicação Spring Boot

## Visão Geral
Camadas principais da aplicação backend seguindo os padrões de arquitetura limpa e design patterns do Spring Boot.

## Estrutura das Camadas

```
src/main/java/com/tdc/sheets/
├── TdcSheetsApplication.java    # Classe principal da aplicação
├── config/                      # Configurações do Spring
│   ├── SecurityConfig.java     # Configuração de segurança
│   ├── DatabaseConfig.java     # Configuração do banco
│   ├── WebConfig.java          # Configuração web (CORS, etc)
│   ├── SwaggerConfig.java      # Documentação API
│   └── RedisConfig.java        # Cache Redis
├── controller/                  # Controladores REST
│   ├── AuthController.java     # Autenticação e registro
│   ├── FichaController.java    # CRUD de fichas
│   ├── UserController.java     # Gerenciamento de usuários
│   ├── GameDataController.java # Dados do jogo (arquetipos, etc)
│   └── ShareController.java    # Compartilhamento de fichas
├── dto/                        # Data Transfer Objects
│   ├── request/                # DTOs de requisição
│   ├── response/               # DTOs de resposta
│   └── mapper/                 # Mapeadores DTO ↔ Entity
├── entity/                     # Entidades JPA
│   ├── User.java              # Usuário do sistema
│   ├── FichaPersonagem.java   # Ficha principal
│   ├── AtributoPersonagem.java # Atributos do personagem
│   ├── Arquetipo.java         # Arquetipos disponíveis
│   └── Linhagem.java          # Linhagens disponíveis
├── repository/                 # Repositórios JPA
│   ├── UserRepository.java    # Operações de usuário
│   ├── FichaRepository.java   # Operações de ficha
│   └── custom/                # Repositórios customizados
├── service/                    # Lógica de negócio
│   ├── AuthService.java       # Serviços de autenticação
│   ├── FichaService.java      # Serviços de ficha
│   ├── CalculoService.java    # Cálculos do RPG
│   └── EmailService.java      # Envio de emails
├── security/                   # Segurança e JWT
│   ├── JwtAuthenticationFilter.java
│   ├── JwtTokenProvider.java
│   ├── UserPrincipal.java
│   └── SecurityUtils.java
├── exception/                  # Tratamento de exceções
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   ├── BadRequestException.java
│   └── custom/                # Exceções específicas
└── validation/                 # Validadores customizados
    ├── ValidArquetipo.java    # Validação de arquétipo
    ├── ValidAtributos.java    # Validação de atributos
    └── CustomValidators.java  # Outros validadores
```

## Camada de Controller

### Responsabilidades
- Receber requisições HTTP
- Validar dados de entrada
- Chamar serviços apropriados
- Retornar respostas formatadas
- Documentação OpenAPI

### Exemplo de Controller
```java
@RestController
@RequestMapping("/api/fichas")
@Tag(name = "Fichas", description = "Operações com fichas de personagem")
@Validated
public class FichaController {

    private final FichaService fichaService;
    private final FichaMapper fichaMapper;

    @GetMapping
    @Operation(summary = "Lista fichas do usuário")
    public ResponseEntity<PagedResponse<FichaResponse>> listarFichas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search,
            Principal principal) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<FichaPersonagem> fichas = fichaService.listarPorUsuario(
            principal.getName(), search, pageable);
        
        return ResponseEntity.ok(fichaMapper.toPagedResponse(fichas));
    }

    @PostMapping
    @Operation(summary = "Cria nova ficha")
    public ResponseEntity<FichaResponse> criarFicha(
            @Valid @RequestBody CriarFichaRequest request,
            Principal principal) {
        
        FichaPersonagem ficha = fichaService.criar(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(fichaMapper.toResponse(ficha));
    }
}
```

## Camada de Service

### Responsabilidades
- Implementar regras de negócio
- Coordenar operações entre repositórios
- Aplicar validações complexas
- Gerenciar transações
- Integrar com serviços externos

### Exemplo de Service
```java
@Service
@Transactional
public class FichaService {

    private final FichaRepository fichaRepository;
    private final UserRepository userRepository;
    private final CalculoService calculoService;
    private final EventPublisher eventPublisher;

    public FichaPersonagem criar(CriarFichaRequest request, String username) {
        User usuario = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // Validar regras de negócio
        validarCriacaoFicha(request, usuario);

        // Criar ficha
        FichaPersonagem ficha = new FichaPersonagem();
        ficha.setNomePersonagem(request.getNome());
        ficha.setUser(usuario);
        ficha.setOrigem(request.getOrigem());
        ficha.setLinhagem(request.getLinhagem());

        // Aplicar cálculos automáticos
        calculoService.aplicarCalculosIniciais(ficha);

        // Salvar
        ficha = fichaRepository.save(ficha);

        // Publicar evento
        eventPublisher.publishEvent(new FichaCriadaEvent(ficha));

        return ficha;
    }

    private void validarCriacaoFicha(CriarFichaRequest request, User usuario) {
        // Verificar limite de fichas por usuário
        long totalFichas = fichaRepository.countByUser(usuario);
        if (totalFichas >= usuario.getLimiteFichas()) {
            throw new BadRequestException("Limite de fichas atingido");
        }

        // Outras validações...
    }
}
```

## Camada de Repository

### Responsabilidades
- Acesso aos dados
- Queries customizadas
- Operações de persistência
- Relacionamentos entre entidades

### Exemplo de Repository
```java
@Repository
public interface FichaRepository extends JpaRepository<FichaPersonagem, Long> {

    @Query("SELECT f FROM FichaPersonagem f WHERE f.user.username = :username")
    Page<FichaPersonagem> findByUserUsername(
        @Param("username") String username, 
        Pageable pageable);

    @Query("SELECT f FROM FichaPersonagem f WHERE f.user.username = :username " +
           "AND f.nomePersonagem ILIKE %:search%")
    Page<FichaPersonagem> findByUserUsernameAndNomeContaining(
        @Param("username") String username,
        @Param("search") String search,
        Pageable pageable);

    @Modifying
    @Query("UPDATE FichaPersonagem f SET f.experiencia = f.experiencia + :exp " +
           "WHERE f.id = :fichaId")
    void adicionarExperiencia(@Param("fichaId") Long fichaId, @Param("exp") int experiencia);

    long countByUser(User user);

    boolean existsByUserAndNomePersonagem(User user, String nome);
}
```

## Camada de Entity

### Responsabilidades
- Mapeamento objeto-relacional
- Definir relacionamentos
- Validações básicas
- Ciclo de vida das entidades

### Exemplo de Entity
```java
@Entity
@Table(name = "ficha_personagem")
@EntityListeners(AuditingEntityListener.class)
public class FichaPersonagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_personagem", nullable = false, length = 100)
    @NotBlank(message = "Nome do personagem é obrigatório")
    @Size(max = 100, message = "Nome não pode exceder 100 caracteres")
    private String nomePersonagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "experiencia")
    @Min(value = 0, message = "Experiência não pode ser negativa")
    private Integer experiencia = 0;

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AtributoPersonagem> atributos = new ArrayList<>();

    @OneToMany(mappedBy = "fichaPersonagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabilidadePersonagem> habilidades = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    private Integer versao;

    // Getters, setters, equals, hashCode...
}
```

## Camada de DTO

### Responsabilidades
- Transferir dados entre camadas
- Validações de entrada
- Formatação de saída
- Versionamento de API

### Exemplo de DTOs
```java
// Request DTO
public class CriarFichaRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome muito longo")
    private String nome;

    @NotNull(message = "Origem é obrigatória")
    private Long origemId;

    @NotNull(message = "Linhagem é obrigatória")
    private Long linhagemId;

    @Valid
    @NotNull(message = "Atributos são obrigatórios")
    private AtributosRequest atributos;

    // Getters e setters...
}

// Response DTO
public class FichaResponse {
    
    private Long id;
    private String nome;
    private String nomeJogador;
    private Integer experiencia;
    private Integer nivel;
    private OrigemResponse origem;
    private LinhagemResponse linhagem;
    private List<AtributoResponse> atributos;
    private PvPpResponse pvPp;
    private DefesaResponse defesa;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Getters e setters...
}
```

## Configurações

### SecurityConfig
- Configuração de autenticação JWT
- Autorização baseada em roles
- CORS e CSRF
- Endpoints públicos/privados

### DatabaseConfig
- Pool de conexões
- Configurações de performance
- Auditoria automática
- Multi-tenancy (futuro)

### SwaggerConfig
- Documentação automática da API
- Esquemas de autenticação
- Exemplos de requisições/respostas
- Agrupamento por tags

## Validações Customizadas

### Validadores de Regras do RPG
```java
@Component
public class AtributosValidator implements ConstraintValidator<ValidAtributos, AtributosRequest> {

    @Override
    public boolean isValid(AtributosRequest atributos, ConstraintValidatorContext context) {
        if (atributos == null) return false;

        // Validar soma total de pontos
        int total = atributos.getForca() + atributos.getAgilidade() + 
                   atributos.getConstituicao() + atributos.getMente() +
                   atributos.getPresenca() + atributos.getInfluencia();

        if (total > 72) { // Limite padrão
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                "Total de pontos não pode exceder 72")
                .addConstraintViolation();
            return false;
        }

        // Validar valores individuais
        return Stream.of(atributos.getForca(), atributos.getAgilidade(),
                        atributos.getConstituicao(), atributos.getMente(),
                        atributos.getPresenca(), atributos.getInfluencia())
                .allMatch(valor -> valor >= 1 && valor <= 20);
    }
}
```

## Tratamento de Exceções

### GlobalExceptionHandler
- Tratamento centralizado de exceções
- Respostas padronizadas
- Logging de erros
- Diferentes tipos de erro (400, 401, 403, 404, 500)

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.NOT_FOUND.value())
            .error("Resource Not Found")
            .message(ex.getMessage())
            .path(getRequestPath())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                FieldError::getField,
                FieldError::getDefaultMessage));

        ValidationErrorResponse response = ValidationErrorResponse.builder()
            .timestamp(LocalDateTime.now())
            .status(HttpStatus.BAD_REQUEST.value())
            .error("Validation Failed")
            .message("Dados de entrada inválidos")
            .fieldErrors(errors)
            .path(getRequestPath())
            .build();

        return ResponseEntity.badRequest().body(response);
    }
}
```

## Melhores Práticas

### Transações
- `@Transactional` nos métodos de serviço
- Rollback em exceções não-checked
- Isolamento apropriado para operações concorrentes

### Performance
- Lazy/Eager loading configurado corretamente
- Queries otimizadas com JPQL/Criteria API
- Cache em operações frequentes (Redis)
- Paginação em listas grandes

### Segurança
- Validação de entrada em todos os controllers
- Autorização baseada em recursos
- Logs de auditoria para operações sensíveis
- Rate limiting para endpoints públicos

### Testabilidade
- Injeção de dependência
- Interfaces para serviços
- Mocks para testes unitários
- TestContainers para testes de integração
