import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import { createAliases } from './config/aliases'

// TODO: Configurar plugins adicionais conforme necessidade - PERF-001
// import { visualizer } from 'rollup-plugin-visualizer' // Para análise de bundle
// import { defineConfig, splitVendorChunkPlugin } from 'vite' // Para chunk splitting avançado

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    // TODO: Adicionar plugins de desenvolvimento:
    // splitVendorChunkPlugin(), // Para otimização de chunks
    // TODO: Plugin para PWA (service worker)
    // TODO: Plugin para análise de bundle em desenvolvimento
  ],
  resolve: {
    alias: createAliases(__dirname)
  },
  server: {
    port: 3000,
    host: true,
    // TODO: Configurar HTTPS para desenvolvimento se necessário
    // https: true,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false
        // TODO: Configurar proxy para diferentes ambientes
        // rewrite: (path) => path.replace(/^\/api/, '')
      }
    },
    // TODO: Configurar headers de desenvolvimento
    // headers: {
    //   'Cross-Origin-Embedder-Policy': 'require-corp',
    //   'Cross-Origin-Opener-Policy': 'same-origin',
    // }
  },
  preview: {
    port: 3000,
    host: true
    // TODO: Configurar preview server para staging
  },
  build: {
    outDir: 'dist',
    sourcemap: false, // TODO: true para desenvolvimento, false para produção
    rollupOptions: {
      output: {
        manualChunks: {
          // TODO: Otimizar chunks baseado no uso real - PERF-001
          vendor: ['react', 'react-dom'],
          router: ['react-router-dom'],
          redux: ['@reduxjs/toolkit', 'react-redux', 'redux-persist'],
          forms: ['react-hook-form', '@hookform/resolvers', 'yup'],
          ui: ['framer-motion', 'lucide-react', 'clsx'],
          charts: ['recharts'],
          utils: ['axios', 'date-fns']
          // TODO: Chunk específico para componentes de RPG quando crescer
          // rpg: ['@components/rpg/...']
        }
      }
    },
    chunkSizeWarningLimit: 1000, // TODO: Ajustar limite baseado no tamanho real
    // TODO: Configurar target para suporte de browsers
    // target: 'es2015'
  },
  define: {
    __APP_VERSION__: JSON.stringify(process.env.npm_package_version)
    // TODO: Adicionar outras variáveis de build:
    // __BUILD_DATE__: JSON.stringify(new Date().toISOString()),
    // __GIT_COMMIT__: JSON.stringify(process.env.GIT_COMMIT),
    // __API_URL__: JSON.stringify(process.env.VITE_API_URL)
  },
  // TODO: Configurar CSS preprocessor se necessário
  css: {
    // preprocessorOptions: {
    //   scss: {
    //     additionalData: `@import "@/styles/variables.scss";`
    //   }
    // }
  },
  // TODO: Configurar otimizações de dependências
  optimizeDeps: {
    // include: ['react', 'react-dom'], // Para forçar pre-bundling
    // exclude: ['some-large-dep'] // Para excluir do pre-bundling
  },
  // TODO: Configurar worker para web workers se necessário
  // worker: {
  //   format: 'es'
  // }
})

// TODO: Configuração específica por ambiente:
// - Development: Hot reload, source maps, debugging
// - Staging: Similar à produção mas com debugging
// - Production: Otimizações máximas, minificação, tree shaking

// TODO: Implementar configurações condicionais:
// if (process.env.NODE_ENV === 'development') {
//   // Configurações específicas de desenvolvimento
// }

// TODO: Adicionar plugins condicionais por ambiente:
// - Bundle analyzer apenas em desenvolvimento
// - Service worker apenas em produção
// - Mock service worker para testes
