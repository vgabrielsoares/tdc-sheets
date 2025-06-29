# Componentes - Frontend TDC Sheets

## Visão Geral
Biblioteca de componentes React reutilizáveis para a interface do sistema de fichas do RPG "Tabuleiro do Caos".

## Estrutura

### Common Components
Componentes base reutilizáveis em toda a aplicação:

- **Button** - Botões com variações de estilo e tamanho
- **Input** - Campos de entrada com validação
- **Modal** - Modais para ações e confirmações
- **Loading** - Indicadores de carregamento
- **ErrorBoundary** - Tratamento de erros de componentes

### Layout Components
- **Header** - Cabeçalho com navegação e menu do usuário
- **Sidebar** - Menu lateral para navegação
- **Footer** - Rodapé com informações e links
- **PageContainer** - Container padrão para páginas

## Padrões de Desenvolvimento

### Estrutura de Componente
```typescript
interface ComponentProps {
  // Props tipadas
}

const Component: React.FC<ComponentProps> = ({ prop }) => {
  // Hooks e lógica
  
  return (
    // JSX
  );
};

export default Component;
```

### Styling
- CSS Modules para escopo local
- BEM methodology para nomenclatura
- Design tokens para consistência

### Accessibility
- Suporte a screen readers
- Navegação por teclado
- Contraste adequado de cores
- ARIA labels apropriados

## Design System

### Cores
```css
:root {
  --primary: #2563eb;
  --secondary: #64748b;
  --success: #059669;
  --warning: #d97706;
  --error: #dc2626;
  --background: #ffffff;
  --surface: #f8fafc;
  --text: #1e293b;
}
```

### Tipografia
```css
:root {
  --font-family: 'Inter', sans-serif;
  --font-size-xs: 0.75rem;
  --font-size-sm: 0.875rem;
  --font-size-base: 1rem;
  --font-size-lg: 1.125rem;
  --font-size-xl: 1.25rem;
}
```

### Espaçamento
```css
:root {
  --spacing-1: 0.25rem;
  --spacing-2: 0.5rem;
  --spacing-3: 0.75rem;
  --spacing-4: 1rem;
  --spacing-6: 1.5rem;
  --spacing-8: 2rem;
}
```

## Testes

### Jest + Testing Library
- Testes unitários para lógica de componentes
- Testes de integração para fluxos completos
- Snapshot tests para regressões visuais

### Exemplo de Teste
```typescript
import { render, screen } from '@testing-library/react';
import { Button } from './Button';

describe('Button', () => {
  it('renders with correct text', () => {
    render(<Button>Click me</Button>);
    expect(screen.getByText('Click me')).toBeInTheDocument();
  });
});
```
