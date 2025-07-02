# TDC Sheets Frontend

Interface web do sistema de fichas para o RPG Tabuleiro do Caos.

## Stack Tecnológica

- **React 18** - Biblioteca de UI
- **TypeScript** - Tipagem estática
- **Vite** - Build tool e dev server
- **Redux Toolkit** - Gerenciamento de estado
- **React Router** - Roteamento
- **Tailwind CSS** - Framework CSS
- **React Hook Form** - Gerenciamento de formulários
- **Axios** - Cliente HTTP
- **React Query** - Cache e sincronização de dados
- **Vite** - Build tool moderna com HMR extremamente rápido

### Gerenciamento de Estado
- **Redux Toolkit** - Gerenciamento de estado previsível
- **React Redux** - Bindings oficiais do Redux para React
- **Redux DevTools** - Ferramentas de debug para desenvolvimento

### Roteamento e Navegação
- **React Router v6** - Roteamento declarativo para SPAs
- **Lazy Loading** - Carregamento sob demanda de componentes

### Estilização
- **Tailwind CSS** - Framework CSS utility-first
- **PostCSS** - Processamento automático de CSS
- **Tema personalizado** - Design system consistente
- **Modo escuro** - Suporte nativo a dark mode

### Ferramentas de Desenvolvimento
- **ESLint** - Linting para código JavaScript/TypeScript
- **Prettier** - Formatação automática de código
- **Husky** - Git hooks para qualidade de código

## Estrutura de Diretórios

```
frontend/
├── src/
│   ├── components/      # Componentes React
│   │   ├── common/      # Componentes reutilizáveis
│   │   ├── ficha/       # Componentes específicos de fichas
│   │   └── auth/        # Componentes de autenticação
│   ├── pages/          # Páginas da aplicação
│   ├── store/          # Redux store
│   │   └── slices/     # Redux slices
│   ├── services/       # Chamadas para APIs
│   ├── types/          # Definições TypeScript
│   ├── hooks/          # Custom hooks React
│   ├── utils/          # Funções utilitárias
│   └── styles/         # Arquivos CSS globais
├── public/             # Arquivos estáticos
└── dist/              # Build de produção
```

## Funcionalidades Principais

### Gerenciamento de Fichas
- Criação e edição de fichas de personagem
- Interface intuitiva para inserção de dados
- Cálculos automáticos em tempo real
- Validação de campos no frontend

### Sistema de Usuários
- Login e cadastro de usuários
- Perfil de usuário
- Gerenciamento de sessão com JWT

### Compartilhamento
- Compartilhamento de fichas entre usuários
- Controle de permissões (visualização/edição)
- Links temporários para acesso

### Interface Responsiva
- Design adaptável para desktop e mobile
- Componentes acessíveis (a11y)
- Tema claro/escuro (futuro)

## Componentes Principais

### Layout
- **Header** - Navegação principal e menu do usuário
- **Sidebar** - Menu lateral com navegação
- **Footer** - Informações e links úteis

### Fichas
- **FichaForm** - Formulário de criação/edição
- **FichaList** - Lista de fichas do usuário
- **FichaViewer** - Visualização de ficha
- **AtributosCalculator** - Cálculos automáticos

### Autenticação
- **LoginForm** - Formulário de login
- **RegisterForm** - Formulário de cadastro
- **ProtectedRoute** - Proteção de rotas

## Configuração

### Pré-requisitos
- Node.js 18+
- npm ou yarn

### Instalação
```bash
npm install
# ou
yarn install
```

### Desenvolvimento
```bash
npm run dev
# ou
yarn dev
```

### Build de Produção
```bash
npm run build
# ou
yarn build
```

## Estado da Aplicação (Redux)

### Slices Principais
- **authSlice** - Autenticação e usuário logado
- **fichaSlice** - Estado das fichas
- **uiSlice** - Estado da interface (loading, modais)
- **calculosSlice** - Cálculos de atributos em tempo real

### Middleware
- **RTK Query** - Cache e sincronização de dados da API
- **Redux Persist** - Persistência do estado no localStorage

## Padrões e Convenções

### Estrutura de Componentes
```typescript
// Componente funcional com TypeScript
interface ComponentProps {
  // Props tipadas
}

const Component: React.FC<ComponentProps> = ({ prop }) => {
  // Hooks
  // Handlers
  // Render
};

export default Component;
```

### Custom Hooks
- **useAuth** - Gerenciamento de autenticação
- **useFicha** - Operações com fichas
- **useCalculos** - Cálculos de atributos
- **useLocalStorage** - Persistência local

## Performance

### Otimizações
- **React.memo** - Prevenção de re-renders desnecessários
- **useMemo/useCallback** - Memoização de valores e funções
- **Lazy Loading** - Carregamento sob demanda
- **Code Splitting** - Divisão do bundle por rotas

### Bundle Optimization
- **Tree Shaking** - Eliminação de código não utilizado
- **Minificação** - Compressão do código
- **Compression** - Gzip/Brotli para assets
