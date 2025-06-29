# Styles - Sistema de Estilos CSS

## Visão Geral
Sistema de estilos centralizado utilizando CSS puro com metodologia BEM, design tokens e arquitetura ITCSS (Inverted Triangle CSS).

## Arquitetura ITCSS

```
styles/
├── settings/              # Variáveis e configurações
│   ├── _colors.css       # Paleta de cores
│   ├── _typography.css   # Configurações tipográficas
│   ├── _spacing.css      # Sistema de espaçamento
│   └── _breakpoints.css  # Media queries
├── tools/                # Mixins e funções CSS
│   ├── _mixins.css      # Mixins reutilizáveis
│   └── _functions.css   # Funções CSS customizadas
├── generic/              # Reset e normalize
│   ├── _reset.css       # CSS reset
│   └── _normalize.css   # Normalize cross-browser
├── elements/             # Estilos base para elementos HTML
│   ├── _typography.css  # Estilos de texto base
│   ├── _forms.css       # Elementos de formulário
│   └── _tables.css      # Tabelas base
├── objects/              # Padrões de layout
│   ├── _container.css   # Containers e wrappers
│   ├── _grid.css        # Sistema de grid
│   └── _layout.css      # Layouts principais
├── components/           # Componentes específicos
│   ├── _button.css      # Botões
│   ├── _modal.css       # Modais
│   ├── _form.css        # Formulários
│   └── _ficha.css       # Componentes de ficha
├── trumps/              # Utilities e overrides
│   ├── _utilities.css   # Classes utilitárias
│   └── _shame.css       # Hacks temporários
└── main.css             # Arquivo principal
```

## Design Tokens

### Cores
```css
/* settings/_colors.css */
:root {
  /* Cores Primárias */
  --color-primary-50: #eff6ff;
  --color-primary-100: #dbeafe;
  --color-primary-200: #bfdbfe;
  --color-primary-300: #93c5fd;
  --color-primary-400: #60a5fa;
  --color-primary-500: #3b82f6;  /* Cor principal */
  --color-primary-600: #2563eb;
  --color-primary-700: #1d4ed8;
  --color-primary-800: #1e40af;
  --color-primary-900: #1e3a8a;

  /* Cores Secundárias */
  --color-secondary-50: #f8fafc;
  --color-secondary-100: #f1f5f9;
  --color-secondary-200: #e2e8f0;
  --color-secondary-300: #cbd5e1;
  --color-secondary-400: #94a3b8;
  --color-secondary-500: #64748b;
  --color-secondary-600: #475569;
  --color-secondary-700: #334155;
  --color-secondary-800: #1e293b;
  --color-secondary-900: #0f172a;

  /* Estados */
  --color-success: #059669;
  --color-warning: #d97706;
  --color-error: #dc2626;
  --color-info: #0284c7;

  /* Superfícies */
  --color-background: #ffffff;
  --color-surface: #f8fafc;
  --color-surface-elevated: #ffffff;
  
  /* Texto */
  --color-text-primary: #1e293b;
  --color-text-secondary: #64748b;
  --color-text-tertiary: #94a3b8;
  --color-text-inverse: #ffffff;

  /* Bordas */
  --color-border: #e2e8f0;
  --color-border-focus: #3b82f6;
  --color-border-error: #dc2626;
}
```

### Tipografia
```css
/* settings/_typography.css */
:root {
  /* Font Families */
  --font-primary: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', sans-serif;
  --font-code: 'JetBrains Mono', 'Fira Code', 'Consolas', monospace;

  /* Font Sizes */
  --font-size-xs: 0.75rem;    /* 12px */
  --font-size-sm: 0.875rem;   /* 14px */
  --font-size-base: 1rem;     /* 16px */
  --font-size-lg: 1.125rem;   /* 18px */
  --font-size-xl: 1.25rem;    /* 20px */
  --font-size-2xl: 1.5rem;    /* 24px */
  --font-size-3xl: 1.875rem;  /* 30px */
  --font-size-4xl: 2.25rem;   /* 36px */

  /* Font Weights */
  --font-weight-light: 300;
  --font-weight-normal: 400;
  --font-weight-medium: 500;
  --font-weight-semibold: 600;
  --font-weight-bold: 700;

  /* Line Heights */
  --line-height-tight: 1.25;
  --line-height-normal: 1.5;
  --line-height-relaxed: 1.75;

  /* Letter Spacing */
  --letter-spacing-tight: -0.025em;
  --letter-spacing-normal: 0;
  --letter-spacing-wide: 0.025em;
}
```

### Espaçamento
```css
/* settings/_spacing.css */
:root {
  /* Spacing Scale */
  --space-px: 1px;
  --space-0: 0;
  --space-1: 0.25rem;   /* 4px */
  --space-2: 0.5rem;    /* 8px */
  --space-3: 0.75rem;   /* 12px */
  --space-4: 1rem;      /* 16px */
  --space-5: 1.25rem;   /* 20px */
  --space-6: 1.5rem;    /* 24px */
  --space-8: 2rem;      /* 32px */
  --space-10: 2.5rem;   /* 40px */
  --space-12: 3rem;     /* 48px */
  --space-16: 4rem;     /* 64px */
  --space-20: 5rem;     /* 80px */
  --space-24: 6rem;     /* 96px */

  /* Radius */
  --radius-sm: 0.125rem;  /* 2px */
  --radius-md: 0.25rem;   /* 4px */
  --radius-lg: 0.5rem;    /* 8px */
  --radius-xl: 0.75rem;   /* 12px */
  --radius-2xl: 1rem;     /* 16px */
  --radius-full: 9999px;

  /* Shadows */
  --shadow-sm: 0 1px 2px 0 rgb(0 0 0 / 0.05);
  --shadow-md: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
  --shadow-lg: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
  --shadow-xl: 0 20px 25px -5px rgb(0 0 0 / 0.1), 0 8px 10px -6px rgb(0 0 0 / 0.1);
}
```

### Breakpoints
```css
/* settings/_breakpoints.css */
:root {
  /* Breakpoints */
  --breakpoint-sm: 640px;
  --breakpoint-md: 768px;
  --breakpoint-lg: 1024px;
  --breakpoint-xl: 1280px;
  --breakpoint-2xl: 1536px;
}

/* Media Query Mixins (PostCSS Custom Media) */
@custom-media --screen-sm (min-width: 640px);
@custom-media --screen-md (min-width: 768px);
@custom-media --screen-lg (min-width: 1024px);
@custom-media --screen-xl (min-width: 1280px);
@custom-media --screen-2xl (min-width: 1536px);
```

## Componentes Base

### Botões
```css
/* components/_button.css */
.btn {
  /* Base */
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: var(--space-2) var(--space-4);
  border: 1px solid transparent;
  border-radius: var(--radius-md);
  font-family: var(--font-primary);
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
  line-height: var(--line-height-tight);
  text-decoration: none;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  user-select: none;

  /* Estados */
  &:focus {
    outline: 2px solid var(--color-primary-500);
    outline-offset: 2px;
  }

  &:disabled {
    opacity: 0.5;
    cursor: not-allowed;
  }
}

/* Variações */
.btn--primary {
  background-color: var(--color-primary-600);
  color: var(--color-text-inverse);

  &:hover:not(:disabled) {
    background-color: var(--color-primary-700);
  }
}

.btn--secondary {
  background-color: var(--color-secondary-100);
  color: var(--color-secondary-900);

  &:hover:not(:disabled) {
    background-color: var(--color-secondary-200);
  }
}

.btn--outline {
  background-color: transparent;
  border-color: var(--color-border);
  color: var(--color-text-primary);

  &:hover:not(:disabled) {
    background-color: var(--color-surface);
  }
}

/* Tamanhos */
.btn--sm {
  padding: var(--space-1) var(--space-3);
  font-size: var(--font-size-xs);
}

.btn--lg {
  padding: var(--space-3) var(--space-6);
  font-size: var(--font-size-base);
}
```

### Formulários
```css
/* components/_form.css */
.form-group {
  margin-bottom: var(--space-4);
}

.form-label {
  display: block;
  margin-bottom: var(--space-2);
  font-size: var(--font-size-sm);
  font-weight: var(--font-weight-medium);
  color: var(--color-text-primary);
}

.form-input {
  width: 100%;
  padding: var(--space-3);
  border: 1px solid var(--color-border);
  border-radius: var(--radius-md);
  font-family: var(--font-primary);
  font-size: var(--font-size-base);
  background-color: var(--color-background);
  transition: border-color 0.2s ease-in-out, box-shadow 0.2s ease-in-out;

  &:focus {
    outline: none;
    border-color: var(--color-border-focus);
    box-shadow: 0 0 0 3px rgb(59 130 246 / 0.1);
  }

  &:invalid {
    border-color: var(--color-border-error);
  }

  &::placeholder {
    color: var(--color-text-tertiary);
  }
}

.form-error {
  margin-top: var(--space-1);
  font-size: var(--font-size-sm);
  color: var(--color-error);
}

.form-help {
  margin-top: var(--space-1);
  font-size: var(--font-size-sm);
  color: var(--color-text-secondary);
}
```

### Fichas
```css
/* components/_ficha.css */
.ficha-card {
  background: var(--color-surface-elevated);
  border: 1px solid var(--color-border);
  border-radius: var(--radius-lg);
  padding: var(--space-6);
  box-shadow: var(--shadow-sm);
  transition: box-shadow 0.2s ease-in-out;

  &:hover {
    box-shadow: var(--shadow-md);
  }
}

.ficha-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: var(--space-4);
  padding-bottom: var(--space-4);
  border-bottom: 1px solid var(--color-border);
}

.ficha-title {
  font-size: var(--font-size-xl);
  font-weight: var(--font-weight-semibold);
  color: var(--color-text-primary);
  margin: 0;
}

.ficha-subtitle {
  font-size: var(--font-size-sm);
  color: var(--color-text-secondary);
  margin: var(--space-1) 0 0 0;
}

.atributos-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
  gap: var(--space-4);
  margin-bottom: var(--space-6);
}

.atributo-card {
  text-align: center;
  padding: var(--space-4);
  background: var(--color-surface);
  border-radius: var(--radius-md);
  border: 1px solid var(--color-border);
}

.atributo-nome {
  font-size: var(--font-size-xs);
  font-weight: var(--font-weight-medium);
  color: var(--color-text-secondary);
  text-transform: uppercase;
  letter-spacing: var(--letter-spacing-wide);
  margin-bottom: var(--space-2);
}

.atributo-valor {
  font-size: var(--font-size-2xl);
  font-weight: var(--font-weight-bold);
  color: var(--color-text-primary);
  margin-bottom: var(--space-1);
}

.atributo-modificador {
  font-size: var(--font-size-sm);
  color: var(--color-text-secondary);
}
```

## Layout System

### Container
```css
/* objects/_container.css */
.container {
  width: 100%;
  max-width: 1280px;
  margin: 0 auto;
  padding: 0 var(--space-4);

  @media (--screen-sm) {
    padding: 0 var(--space-6);
  }

  @media (--screen-lg) {
    padding: 0 var(--space-8);
  }
}

.container--narrow {
  max-width: 768px;
}

.container--wide {
  max-width: 1536px;
}
```

### Grid System
```css
/* objects/_grid.css */
.grid {
  display: grid;
  gap: var(--space-4);
}

.grid--2 { grid-template-columns: repeat(2, 1fr); }
.grid--3 { grid-template-columns: repeat(3, 1fr); }
.grid--4 { grid-template-columns: repeat(4, 1fr); }

.grid--responsive {
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
}

@media (--screen-md) {
  .grid--md-2 { grid-template-columns: repeat(2, 1fr); }
  .grid--md-3 { grid-template-columns: repeat(3, 1fr); }
  .grid--md-4 { grid-template-columns: repeat(4, 1fr); }
}
```

## Utilities

### Spacing
```css
/* trumps/_utilities.css */
/* Margin */
.m-0 { margin: 0; }
.m-1 { margin: var(--space-1); }
.m-2 { margin: var(--space-2); }
.m-3 { margin: var(--space-3); }
.m-4 { margin: var(--space-4); }

.mt-0 { margin-top: 0; }
.mt-1 { margin-top: var(--space-1); }
.mt-2 { margin-top: var(--space-2); }

.mr-0 { margin-right: 0; }
.mr-1 { margin-right: var(--space-1); }

.mb-0 { margin-bottom: 0; }
.mb-1 { margin-bottom: var(--space-1); }

.ml-0 { margin-left: 0; }
.ml-1 { margin-left: var(--space-1); }

/* Padding */
.p-0 { padding: 0; }
.p-1 { padding: var(--space-1); }
.p-2 { padding: var(--space-2); }

/* Display */
.block { display: block; }
.inline-block { display: inline-block; }
.inline { display: inline; }
.flex { display: flex; }
.grid { display: grid; }
.hidden { display: none; }

/* Flexbox */
.items-center { align-items: center; }
.items-start { align-items: flex-start; }
.items-end { align-items: flex-end; }

.justify-center { justify-content: center; }
.justify-between { justify-content: space-between; }
.justify-around { justify-content: space-around; }

/* Text */
.text-left { text-align: left; }
.text-center { text-align: center; }
.text-right { text-align: right; }

.text-xs { font-size: var(--font-size-xs); }
.text-sm { font-size: var(--font-size-sm); }
.text-base { font-size: var(--font-size-base); }
.text-lg { font-size: var(--font-size-lg); }

.font-light { font-weight: var(--font-weight-light); }
.font-normal { font-weight: var(--font-weight-normal); }
.font-medium { font-weight: var(--font-weight-medium); }
.font-semibold { font-weight: var(--font-weight-semibold); }
.font-bold { font-weight: var(--font-weight-bold); }

/* Colors */
.text-primary { color: var(--color-text-primary); }
.text-secondary { color: var(--color-text-secondary); }
.text-success { color: var(--color-success); }
.text-error { color: var(--color-error); }

.bg-primary { background-color: var(--color-primary-500); }
.bg-surface { background-color: var(--color-surface); }
.bg-success { background-color: var(--color-success); }
.bg-error { background-color: var(--color-error); }
```

## Dark Mode Support

### CSS Custom Properties
```css
/* settings/_colors.css */
@media (prefers-color-scheme: dark) {
  :root {
    --color-background: #0f172a;
    --color-surface: #1e293b;
    --color-surface-elevated: #334155;
    
    --color-text-primary: #f1f5f9;
    --color-text-secondary: #cbd5e1;
    --color-text-tertiary: #64748b;
    
    --color-border: #334155;
  }
}

[data-theme="dark"] {
  /* Forçar tema escuro */
  --color-background: #0f172a;
  --color-surface: #1e293b;
  /* ... outras variáveis */
}
```

## Performance

### CSS Optimization
- **Critical CSS**: Inline de estilos críticos
- **CSS Splitting**: Separação por componentes
- **Purging**: Remoção de CSS não utilizado
- **Minification**: Compressão para produção

### Loading Strategy
```css
/* Carregamento otimizado */
@import url('settings/index.css');
@import url('tools/index.css');
@import url('generic/index.css');
@import url('elements/index.css');
@import url('objects/index.css');
@import url('components/index.css') layer(components);
@import url('trumps/index.css') layer(utilities);
```

## Manutenção

### Linting
```json
// .stylelintrc.json
{
  "extends": [
    "stylelint-config-standard",
    "stylelint-config-css-modules"
  ],
  "rules": {
    "custom-property-pattern": "^[a-z][a-z0-9]*(-[a-z0-9]+)*$",
    "selector-class-pattern": "^[a-z][a-z0-9]*(-[a-z0-9]+)*(__[a-z0-9]+(-[a-z0-9]+)*)?(--[a-z0-9]+(-[a-z0-9]+)*)?$"
  }
}
```

### Documentation
- Comentários descritivos nos componentes
- Exemplos de uso em Storybook
- Guia de estilo visual atualizado
