/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        // TODO: Refinar paleta de cores baseada no design do TDC - UX-001
        primary: {
          50: '#f0f9ff',
          100: '#e0f2fe',
          200: '#bae6fd',
          300: '#7dd3fc',
          400: '#38bdf8',
          500: '#0ea5e9',
          600: '#0284c7',
          700: '#0369a1',
          800: '#075985',
          900: '#0c4a6e',
          950: '#082f49',
        },
        secondary: {
          50: '#faf5ff',
          100: '#f3e8ff',
          200: '#e9d5ff',
          300: '#d8b4fe',
          400: '#c084fc',
          500: '#a855f7',
          600: '#9333ea',
          700: '#7c3aed',
          800: '#6b21a8',
          900: '#581c87',
          950: '#3b0764',
        },
        // TODO: Adicionar cores específicas do TDC:
        // - Cores por arquétipo (vermelho para combatente, azul para feiticeiro, etc.)
        // - Cores para diferentes tipos de dados (sucesso, falha, crítico)
        // - Cores para elementos mágicos (fogo, água, ar, terra, etc.)
        // - Cores para indicadores de status (ferido, envenenado, etc.)
      },
      // TODO: Implementar tipografia customizada - UX-001
      fontFamily: {
        sans: ['Inter', 'sans-serif'],
        heading: ['Poppins', 'sans-serif'],

        // sans: ['Inter', 'system-ui', 'sans-serif'],
        // serif: ['Merriweather', 'serif'],
        // mono: ['JetBrains Mono', 'monospace'],
        // TODO: Fonte temática para títulos (medieval/fantasia)
        // display: ['Cinzel', 'serif'], // Para títulos e elementos decorativos
      },
      // TODO: Configurar espaçamentos específicos do design system
      spacing: {
        // '18': '4.5rem',
        // '88': '22rem',
        // TODO: Espaçamentos específicos para componentes de ficha
      },
      // TODO: Implementar breakpoints customizados para fichas
      screens: {
        // 'xs': '475px',
        // 'sm': '640px',
        // 'md': '768px',
        // 'lg': '1024px',
        // 'xl': '1280px',
        // '2xl': '1536px',
        // TODO: Breakpoint específico para tablets em modo landscape
        // 'tablet-landscape': '1024px',
      },
      // TODO: Adicionar animações temáticas - UX-001
      animation: {
        // 'fade-in': 'fadeIn 0.5s ease-in-out',
        // 'slide-up': 'slideUp 0.3s ease-out',
        // 'roll-dice': 'rollDice 1s ease-in-out',
        // 'magic-sparkle': 'sparkle 2s infinite',
      },
      // TODO: Configurar sombras para profundidade
      boxShadow: {
        // 'card': '0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06)',
        // 'card-hover': '0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px rgba(0, 0, 0, 0.05)',
        // TODO: Sombras específicas para diferentes estados
      },
      // TODO: Implementar gradientes temáticos
      backgroundImage: {
        // 'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        // 'magic-gradient': 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)',
        // TODO: Gradientes por arquétipo e elemento mágico
      },
    },
  },
  plugins: [
    // TODO: Adicionar plugins úteis - UX-001
    // require('@tailwindcss/forms'), // Para estilização de formulários
    // require('@tailwindcss/typography'), // Para conteúdo rich text
    // require('@tailwindcss/aspect-ratio'), // Para manter proporções
    // require('@tailwindcss/container-queries'), // Para queries de container
    
    // TODO: Plugin customizado para componentes TDC
    // function({ addComponents, theme }) {
    //   addComponents({
    //     '.ficha-card': {
    //       // Estilos base para cards de ficha
    //     },
    //     '.dice-button': {
    //       // Estilos para botões de rolagem de dados
    //     },
    //     '.attribute-input': {
    //       // Estilos para inputs de atributos
    //     },
    //   })
    // }
  ],
  // TODO: Configurar modo escuro - UX-001
  darkMode: 'class', // ou 'media' para automático
  
  // TODO: Configurar prefix se necessário para evitar conflitos
  // prefix: 'tdc-',
  
  // TODO: Configurar important para override de estilos quando necessário
  // important: true,
}

// TODO: Implementar variantes customizadas:
// - group-hover para interações em cards
// - focus-visible para acessibilidade
// - disabled para estados desabilitados
// - dark mode variants para todos os componentes

// TODO: Configurar purge/safelist para classes dinâmicas:
// - Classes geradas baseadas em dados (cor por arquétipo)
// - Classes de animação por tipo de ação
// - Classes de estado por condição de personagem
