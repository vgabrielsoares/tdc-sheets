import { FC } from 'react'
import { Outlet } from 'react-router-dom'

const AuthLayout: FC = () => {
  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 dark:from-gray-900 dark:to-gray-800">
      <div className="flex min-h-screen">
        {/* Left side - Branding */}
        <div className="hidden lg:flex lg:w-1/2 bg-gradient-to-br from-blue-600 to-indigo-700 dark:from-blue-800 dark:to-indigo-900">
          <div className="flex flex-col justify-center items-center text-white p-12">
            <div className="text-center">
              <h1 className="text-4xl font-bold mb-4">
                TDC Sheets
              </h1>
              <p className="text-xl mb-8 text-blue-100">
                Sistema completo para fichas do Tabuleiro do Caos RPG
              </p>
              <div className="space-y-4 text-blue-100">
                <div className="flex items-center">
                  <div className="w-2 h-2 bg-blue-300 rounded-full mr-3"></div>
                  <span>Criação e gerenciamento de personagens</span>
                </div>
                <div className="flex items-center">
                  <div className="w-2 h-2 bg-blue-300 rounded-full mr-3"></div>
                  <span>Cálculos automáticos de atributos</span>
                </div>
                <div className="flex items-center">
                  <div className="w-2 h-2 bg-blue-300 rounded-full mr-3"></div>
                  <span>Compartilhamento em tempo real</span>
                </div>
                <div className="flex items-center">
                  <div className="w-2 h-2 bg-blue-300 rounded-full mr-3"></div>
                  <span>Integração completa com as regras</span>
                </div>
              </div>
            </div>
          </div>
        </div>

        {/* Right side - Auth Forms */}
        <div className="flex-1 flex flex-col justify-center items-center p-8">
          <div className="w-full max-w-md">
            {/* Mobile logo */}
            <div className="lg:hidden text-center mb-8">
              <h1 className="text-3xl font-bold text-gray-900 dark:text-white">
                TDC Sheets
              </h1>
              <p className="text-gray-600 dark:text-gray-400 mt-2">
                Fichas para Tabuleiro do Caos RPG
              </p>
            </div>

            {/* Form container */}
            <div className="bg-white dark:bg-gray-800 shadow-xl rounded-lg p-8">
              <Outlet />
            </div>

            {/* Footer */}
            <div className="text-center mt-8 text-sm text-gray-500 dark:text-gray-400">
              <p>
                © 2025 TDC Sheets. Todos os direitos reservados.
              </p>
              <div className="mt-2 space-x-4">
                <a href="#" className="hover:text-gray-700 dark:hover:text-gray-300">
                  Termos de Uso
                </a>
                <a href="#" className="hover:text-gray-700 dark:hover:text-gray-300">
                  Privacidade
                </a>
                <a href="#" className="hover:text-gray-700 dark:hover:text-gray-300">
                  Suporte
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AuthLayout
