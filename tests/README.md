# Tests - TDC Sheets E2E

## Visão Geral
Testes end-to-end (E2E) para validação completa dos fluxos de usuário e integração entre frontend e backend.

## Stack de Testes

### Frameworks
- **Playwright** - Framework principal para testes E2E
- **Jest** - Runner de testes e assertions
- **Docker Testcontainers** - Ambiente isolado para testes
- **Faker.js** - Geração de dados de teste

### Ferramentas Auxiliares
- **Allure** - Relatórios detalhados de testes
- **Percy** - Testes visuais e screenshots
- **Lighthouse CI** - Testes de performance
- **Pa11y** - Testes de acessibilidade

## Estrutura de Testes

```
tests/
├── e2e/                    # Testes end-to-end
│   ├── auth/              # Fluxos de autenticação
│   ├── fichas/            # Operações com fichas
│   ├── compartilhamento/  # Sistema de compartilhamento
│   └── admin/             # Funcionalidades administrativas
├── integration/           # Testes de integração
├── performance/           # Testes de performance
├── accessibility/         # Testes de acessibilidade
├── visual/               # Testes visuais
├── fixtures/             # Dados de teste
├── utils/                # Utilitários de teste
└── config/               # Configurações
```

## Configuração

### Pré-requisitos
```bash
# Instalar dependências
npm install

# Instalar browsers
npx playwright install

# Configurar ambiente
cp .env.test.example .env.test
```

### Variáveis de Ambiente
```env
# URLs de teste
TEST_BASE_URL=http://localhost:3000
TEST_API_URL=http://localhost:8080

# Banco de teste
TEST_DB_HOST=localhost
TEST_DB_PORT=5433
TEST_DB_NAME=tdc_sheets_test
TEST_DB_USER=test_user
TEST_DB_PASS=test_pass

# Configurações de teste
HEADLESS=true
TIMEOUT=30000
RETRIES=2
```

## Cenários de Teste

### Autenticação (auth/)
```typescript
// login.spec.ts
describe('Sistema de Login', () => {
  test('deve fazer login com credenciais válidas', async ({ page }) => {
    await page.goto('/login');
    await page.fill('[data-testid="email"]', 'user@test.com');
    await page.fill('[data-testid="password"]', 'password123');
    await page.click('[data-testid="login-button"]');
    
    await expect(page).toHaveURL('/dashboard');
    await expect(page.locator('[data-testid="user-menu"]')).toBeVisible();
  });

  test('deve mostrar erro com credenciais inválidas', async ({ page }) => {
    await page.goto('/login');
    await page.fill('[data-testid="email"]', 'invalid@test.com');
    await page.fill('[data-testid="password"]', 'wrongpass');
    await page.click('[data-testid="login-button"]');
    
    await expect(page.locator('[data-testid="error-message"]')).toBeVisible();
    await expect(page.locator('[data-testid="error-message"]')).toHaveText('Credenciais inválidas');
  });
});
```

### Fichas (fichas/)
```typescript
// criar-ficha.spec.ts
describe('Criação de Fichas', () => {
  test('deve criar ficha completa de Combatente', async ({ page }) => {
    await loginAsUser(page);
    await page.goto('/fichas/nova');
    
    // Preencher dados básicos
    await page.fill('[data-testid="nome-personagem"]', 'Thorin Escudodeferro');
    await page.selectOption('[data-testid="arquetipo"]', 'COMBATENTE');
    await page.selectOption('[data-testid="origem"]', 'URBANA');
    await page.selectOption('[data-testid="linhagem"]', 'ANAO');
    
    // Distribuir atributos
    await page.fill('[data-testid="forca"]', '16');
    await page.fill('[data-testid="agilidade"]', '12');
    await page.fill('[data-testid="constituicao"]', '15');
    await page.fill('[data-testid="mente"]', '10');
    await page.fill('[data-testid="presenca"]', '13');
    await page.fill('[data-testid="influencia"]', '8');
    
    // Verificar cálculos automáticos
    await expect(page.locator('[data-testid="pv-calculado"]')).toHaveText('23');
    await expect(page.locator('[data-testid="pp-calculado"]')).toHaveText('4');
    await expect(page.locator('[data-testid="defesa-calculada"]')).toHaveText('16');
    
    // Salvar ficha
    await page.click('[data-testid="salvar-ficha"]');
    
    await expect(page).toHaveURL(/\/fichas\/\d+/);
    await expect(page.locator('[data-testid="nome-personagem-display"]')).toHaveText('Thorin Escudodeferro');
  });
});
```

### Performance (performance/)
```typescript
// load-time.spec.ts
describe('Performance da Aplicação', () => {
  test('página inicial deve carregar em menos de 2s', async ({ page }) => {
    const startTime = Date.now();
    await page.goto('/');
    await page.waitForLoadState('networkidle');
    const loadTime = Date.now() - startTime;
    
    expect(loadTime).toBeLessThan(2000);
  });

  test('lista de fichas deve carregar rapidamente', async ({ page }) => {
    await loginAsUser(page);
    
    const startTime = Date.now();
    await page.goto('/fichas');
    await page.waitForSelector('[data-testid="fichas-list"]');
    const loadTime = Date.now() - startTime;
    
    expect(loadTime).toBeLessThan(1500);
  });
});
```

## Dados de Teste

### Fixtures
```typescript
// fixtures/usuarios.ts
export const usuarios = {
  admin: {
    email: 'admin@tdc-sheets.com',
    password: 'admin123',
    role: 'ADMIN'
  },
  jogador: {
    email: 'jogador@test.com',
    password: 'player123',
    role: 'USER'
  },
  mestre: {
    email: 'mestre@test.com',
    password: 'master123',
    role: 'MASTER'
  }
};

// fixtures/fichas.ts
export const fichasExemplo = {
  combatente: {
    nome: 'Thorin Escudodeferro',
    arquetipo: 'COMBATENTE',
    origem: 'URBANA',
    linhagem: 'ANAO',
    atributos: {
      forca: 16,
      agilidade: 12,
      constituicao: 15,
      mente: 10,
      presenca: 13,
      influencia: 8
    }
  },
  feiticeiro: {
    nome: 'Eldara Luaverde',
    arquetipo: 'FEITICEIRO',
    origem: 'ACADEMICA',
    linhagem: 'ELFO',
    atributos: {
      forca: 8,
      agilidade: 14,
      constituicao: 12,
      mente: 16,
      presenca: 15,
      influencia: 10
    }
  }
};
```

### Database Seeding
```typescript
// utils/database.ts
export async function seedTestDatabase() {
  const db = await connectToTestDB();
  
  // Limpar dados existentes
  await db.query('TRUNCATE TABLE ficha_personagem CASCADE');
  await db.query('TRUNCATE TABLE "user" CASCADE');
  
  // Inserir usuários de teste
  for (const usuario of Object.values(usuarios)) {
    await db.query(`
      INSERT INTO "user" (username, email, password_hash, role)
      VALUES ($1, $2, $3, $4)
    `, [usuario.email, usuario.email, hashPassword(usuario.password), usuario.role]);
  }
  
  await db.close();
}
```

## Utilities

### Page Objects
```typescript
// utils/page-objects/FichaPage.ts
export class FichaPage {
  constructor(private page: Page) {}

  async preencherDadosBasicos(dados: DadosBasicos) {
    await this.page.fill('[data-testid="nome-personagem"]', dados.nome);
    await this.page.selectOption('[data-testid="arquetipo"]', dados.arquetipo);
    await this.page.selectOption('[data-testid="origem"]', dados.origem);
    await this.page.selectOption('[data-testid="linhagem"]', dados.linhagem);
  }

  async distribuirAtributos(atributos: Atributos) {
    for (const [atributo, valor] of Object.entries(atributos)) {
      await this.page.fill(`[data-testid="${atributo}"]`, valor.toString());
    }
  }

  async salvarFicha() {
    await this.page.click('[data-testid="salvar-ficha"]');
    await this.page.waitForURL(/\/fichas\/\d+/);
  }
}
```

### Helpers
```typescript
// utils/helpers.ts
export async function loginAsUser(page: Page, userType: 'admin' | 'jogador' | 'mestre' = 'jogador') {
  const user = usuarios[userType];
  await page.goto('/login');
  await page.fill('[data-testid="email"]', user.email);
  await page.fill('[data-testid="password"]', user.password);
  await page.click('[data-testid="login-button"]');
  await page.waitForURL('/dashboard');
}

export async function criarFichaCompleta(page: Page, tipo: 'combatente' | 'feiticeiro') {
  const ficha = fichasExemplo[tipo];
  const fichaPage = new FichaPage(page);
  
  await page.goto('/fichas/nova');
  await fichaPage.preencherDadosBasicos(ficha);
  await fichaPage.distribuirAtributos(ficha.atributos);
  await fichaPage.salvarFicha();
}
```

## Executar Testes

### Comandos Principais
```bash
# Todos os testes
npm run test:e2e

# Testes específicos
npm run test:e2e -- --grep "Sistema de Login"

# Modo interativo
npm run test:e2e:ui

# Com relatório visual
npm run test:e2e:report
```

### CI/CD Pipeline
```bash
# Setup ambiente de teste
docker-compose -f docker-compose.test.yml up -d

# Executar testes
npm run test:e2e:ci

# Gerar relatórios
npm run test:report

# Cleanup
docker-compose -f docker-compose.test.yml down
```

## Relatórios

### Allure Reports
```bash
# Gerar relatório
npm run test:allure:generate

# Servir relatório
npm run test:allure:serve
```

### Coverage
```bash
# Relatório de cobertura E2E
npm run test:coverage

# Cobertura combinada (unit + e2e)
npm run test:coverage:merged
```

## Melhores Práticas

### Seletores
- Use `data-testid` para elementos de teste
- Evite seletores CSS dependentes de estilo
- Prefira seletores semânticos

### Organização
- Um arquivo por funcionalidade
- Testes independentes e isolados
- Setup e teardown adequados

### Performance
- Reutilize logins com storage state
- Paralelização quando possível
- Mock de APIs externas

### Manutenção
- Atualize testes com mudanças de UI
- Mantenha dados de teste atualizados
- Revise e remova testes obsoletos
