import { Component, ErrorInfo, ReactNode } from 'react'
import { AlertTriangle, RefreshCw } from 'lucide-react'

interface Props {
  children: ReactNode
  fallback?: ReactNode
}

interface State {
  hasError: boolean
  error?: Error
  errorInfo?: ErrorInfo
}

class ErrorBoundary extends Component<Props, State> {
  constructor(props: Props) {
    super(props)
    this.state = {
      hasError: false
    }
  }

  static getDerivedStateFromError(error: Error): State {
    return {
      hasError: true,
      error
    }
  }

  componentDidCatch(error: Error, errorInfo: ErrorInfo) {
    console.error('ErrorBoundary caught an error:', error, errorInfo)
    this.setState({
      error,
      errorInfo
    })
  }

  handleReload = () => {
    window.location.reload()
  }

  handleReset = () => {
    this.setState({
      hasError: false,
      error: undefined,
      errorInfo: undefined
    })
  }

  render() {
    if (this.state.hasError) {
      if (this.props.fallback) {
        return this.props.fallback
      }

      return (
        <div className="min-h-screen bg-gray-50 dark:bg-gray-900 flex items-center justify-center p-4">
          <div className="max-w-md w-full bg-white dark:bg-gray-800 rounded-lg shadow-lg p-6">
            <div className="flex items-center mb-4">
              <AlertTriangle className="w-8 h-8 text-red-500 mr-3" />
              <h1 className="text-xl font-bold text-gray-900 dark:text-white">
                Oops! Algo deu errado
              </h1>
            </div>
            
            <p className="text-gray-600 dark:text-gray-400 mb-6">
              Ocorreu um erro inesperado na aplicação. Tente recarregar a página ou entre em contato com o suporte se o problema persistir.
            </p>

            {process.env.NODE_ENV === 'development' && this.state.error && (
              <details className="mb-6 p-4 bg-gray-100 dark:bg-gray-700 rounded border">
                <summary className="font-medium cursor-pointer text-sm">
                  Detalhes do erro (desenvolvimento)
                </summary>
                <pre className="mt-2 text-xs text-red-600 dark:text-red-400 overflow-auto">
                  {this.state.error.toString()}
                  {this.state.errorInfo?.componentStack}
                </pre>
              </details>
            )}

            <div className="flex space-x-3">
              <button
                onClick={this.handleReset}
                className="flex-1 flex items-center justify-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors"
              >
                Tentar Novamente
              </button>
              <button
                onClick={this.handleReload}
                className="flex-1 flex items-center justify-center px-4 py-2 bg-gray-600 text-white rounded-md hover:bg-gray-700 transition-colors"
              >
                <RefreshCw className="w-4 h-4 mr-2" />
                Recarregar
              </button>
            </div>

            <div className="mt-6 text-center">
              <a
                href="mailto:support@tdcsheets.com"
                className="text-sm text-blue-600 dark:text-blue-400 hover:underline"
              >
                Relatar este problema
              </a>
            </div>
          </div>
        </div>
      )
    }

    return this.props.children
  }
}

export default ErrorBoundary
