import React from 'react'
import ReactDOM from 'react-dom/client'
import { Provider } from 'react-redux'
import { PersistGate } from 'redux-persist/integration/react'
import { BrowserRouter } from 'react-router-dom'
import { Toaster } from 'react-hot-toast'

import App from './App.tsx'
import { store, persistor } from './store'
import Loading from '@components/common/Loading'

import '@styles/index.css'

// TODO: Adicionar configurações de performance - PERF-001
// - React.StrictMode apenas em desenvolvimento
// - React Profiler para monitoramento de performance
// - Configuração de service worker para cache

// TODO: Implementar configurações de monitoramento - DEPLOY-001
// - Sentry para error tracking
// - Analytics (GA, Hotjar, etc.)
// - Performance monitoring (Web Vitals)

// TODO: Configurar internacionalização - UX-001
// - i18next setup
// - Context de idioma
// - Detecção automática de idioma do browser

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
)

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <PersistGate loading={<Loading />} persistor={persistor}>
        <BrowserRouter>
          <App />
          <Toaster
            position="top-right"
            toastOptions={{
              duration: 4000,
              style: {
                background: '#363636',
                color: '#fff',
              },
              success: {
                duration: 3000,
              },
              error: {
                duration: 5000,
              },
            }}
          />
        </BrowserRouter>
      </PersistGate>
    </Provider>
  </React.StrictMode>
)

// TODO: Adicionar configurações globais:
// - Error boundary global
// - Theme provider
// - Toast provider
// - Modal provider
// - Loading provider
