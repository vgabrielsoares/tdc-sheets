# Páginas - Frontend TDC Sheets

## Visão Geral
Páginas principais da aplicação organizadas por funcionalidade e fluxo de usuário.

## Estrutura de Páginas

### Autenticação
- **LoginPage** - Página de login
- **RegisterPage** - Cadastro de usuários
- **ForgotPasswordPage** - Recuperação de senha
- **ResetPasswordPage** - Redefinição de senha

### Dashboard
- **HomePage** - Dashboard principal com resumo
- **ProfilePage** - Perfil do usuário

### Fichas
- **FichasListPage** - Lista de fichas do usuário
- **FichaCreatePage** - Criação de nova ficha
- **FichaEditPage** - Edição de ficha existente
- **FichaViewPage** - Visualização de ficha
- **FichaSharePage** - Compartilhamento de fichas

### Sistema
- **NotFoundPage** - Página 404
- **ErrorPage** - Página de erro genérica
- **MaintenancePage** - Página de manutenção

## Roteamento

### Estrutura de Rotas
```typescript
const routes = [
  { path: '/', component: HomePage, protected: true },
  { path: '/login', component: LoginPage, protected: false },
  { path: '/fichas', component: FichasListPage, protected: true },
  { path: '/fichas/:id', component: FichaViewPage, protected: true },
  { path: '/fichas/:id/edit', component: FichaEditPage, protected: true },
];
```

### Proteção de Rotas
- **ProtectedRoute** - Wrapper para rotas autenticadas
- **PublicRoute** - Rotas públicas (login, registro)
- **AdminRoute** - Rotas administrativas

## Layout e Navegação

### Estrutura Padrão
```typescript
const PageLayout: React.FC = ({ children }) => (
  <div className="app-layout">
    <Header />
    <div className="main-content">
      <Sidebar />
      <main className="page-content">
        {children}
      </main>
    </div>
    <Footer />
  </div>
);
```

### Breadcrumbs
- Navegação contextual
- Histórico de páginas visitadas
- Links de retorno rápido

## SEO e Meta Tags

### React Helmet
```typescript
const PageHead: React.FC<{ title: string; description: string }> = ({
  title,
  description
}) => (
  <Helmet>
    <title>{title} | TDC Sheets</title>
    <meta name="description" content={description} />
    <meta property="og:title" content={title} />
    <meta property="og:description" content={description} />
  </Helmet>
);
```

## Responsividade

### Breakpoints
```css
/* Mobile First */
@media (min-width: 640px) { /* sm */ }
@media (min-width: 768px) { /* md */ }
@media (min-width: 1024px) { /* lg */ }
@media (min-width: 1280px) { /* xl */ }
```

### Adaptações Mobile
- Menu lateral colapsável
- Navegação por tabs em fichas
- Formulários otimizados para touch
