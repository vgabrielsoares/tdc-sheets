import { FC } from 'react'
import { Link } from 'react-router-dom'
import { 
  Plus, 
  FileText, 
  Users, 
  TrendingUp,
  Clock,
  Star
} from 'lucide-react'
import { useAppSelector } from '@hooks/redux'
import { selectUser } from '@store/slices/authSlice'

const DashboardPage: FC = () => {
  const user = useAppSelector(selectUser)

  // Mock data - will be replaced with real data from API
  const stats = {
    totalFichas: 5,
    fichasCompartilhadas: 2,
    gruposAtivos: 1,
    ultimasEdicoes: 3
  }

  const recentFichas = [
    {
      id: '1',
      nome: 'Thorin Pedrapunho',
      arquetipo: 'Combatente',
      nivel: 5,
      updatedAt: '2024-01-15T10:30:00Z'
    },
    {
      id: '2',
      nome: 'Luna Luaverde',
      arquetipo: 'Feiticeiro',
      nivel: 3,
      updatedAt: '2024-01-14T16:45:00Z'
    },
    {
      id: '3',
      nome: 'Kael Ventolâmina',
      arquetipo: 'Ladino',
      nivel: 4,
      updatedAt: '2024-01-13T09:15:00Z'
    }
  ]

  const quickActions = [
    {
      title: 'Nova Ficha',
      description: 'Criar um novo personagem',
      icon: Plus,
      path: '/fichas/new',
      color: 'bg-blue-500'
    },
    {
      title: 'Minhas Fichas',
      description: 'Ver todas as suas fichas',
      icon: FileText,
      path: '/fichas',
      color: 'bg-green-500'
    },
    {
      title: 'Grupos',
      description: 'Gerenciar grupos e mesas',
      icon: Users,
      path: '/grupos',
      color: 'bg-purple-500'
    }
  ]

  return (
    <div className="space-y-8">
      {/* Header */}
      <div>
        <h1 className="text-3xl font-bold text-gray-900 dark:text-white">
          Bem-vindo, {user?.name}!
        </h1>
        <p className="text-gray-600 dark:text-gray-400 mt-2">
          Aqui está um resumo das suas atividades no TDC Sheets.
        </p>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
          <div className="flex items-center">
            <FileText className="w-8 h-8 text-blue-500" />
            <div className="ml-4">
              <p className="text-sm font-medium text-gray-500 dark:text-gray-400">
                Total de Fichas
              </p>
              <p className="text-2xl font-bold text-gray-900 dark:text-white">
                {stats.totalFichas}
              </p>
            </div>
          </div>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
          <div className="flex items-center">
            <Users className="w-8 h-8 text-green-500" />
            <div className="ml-4">
              <p className="text-sm font-medium text-gray-500 dark:text-gray-400">
                Fichas Compartilhadas
              </p>
              <p className="text-2xl font-bold text-gray-900 dark:text-white">
                {stats.fichasCompartilhadas}
              </p>
            </div>
          </div>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
          <div className="flex items-center">
            <TrendingUp className="w-8 h-8 text-purple-500" />
            <div className="ml-4">
              <p className="text-sm font-medium text-gray-500 dark:text-gray-400">
                Grupos Ativos
              </p>
              <p className="text-2xl font-bold text-gray-900 dark:text-white">
                {stats.gruposAtivos}
              </p>
            </div>
          </div>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
          <div className="flex items-center">
            <Clock className="w-8 h-8 text-orange-500" />
            <div className="ml-4">
              <p className="text-sm font-medium text-gray-500 dark:text-gray-400">
                Editadas Recentemente
              </p>
              <p className="text-2xl font-bold text-gray-900 dark:text-white">
                {stats.ultimasEdicoes}
              </p>
            </div>
          </div>
        </div>
      </div>

      {/* Quick Actions */}
      <div>
        <h2 className="text-xl font-bold text-gray-900 dark:text-white mb-4">
          Ações Rápidas
        </h2>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          {quickActions.map((action) => {
            const IconComponent = action.icon
            return (
              <Link
                key={action.path}
                to={action.path}
                className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6 hover:shadow-md transition-shadow"
              >
                <div className="flex items-center">
                  <div className={`${action.color} p-3 rounded-lg`}>
                    <IconComponent className="w-6 h-6 text-white" />
                  </div>
                  <div className="ml-4">
                    <h3 className="text-lg font-semibold text-gray-900 dark:text-white">
                      {action.title}
                    </h3>
                    <p className="text-sm text-gray-600 dark:text-gray-400">
                      {action.description}
                    </p>
                  </div>
                </div>
              </Link>
            )
          })}
        </div>
      </div>

      {/* Recent Fichas */}
      <div>
        <div className="flex items-center justify-between mb-4">
          <h2 className="text-xl font-bold text-gray-900 dark:text-white">
            Fichas Recentes
          </h2>
          <Link
            to="/fichas"
            className="text-blue-600 dark:text-blue-400 hover:underline text-sm font-medium"
          >
            Ver todas
          </Link>
        </div>
        
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700">
          <div className="p-6">
            <div className="space-y-4">
              {recentFichas.map((ficha) => (
                <div key={ficha.id} className="flex items-center justify-between p-4 bg-gray-50 dark:bg-gray-700 rounded-lg">
                  <div className="flex items-center">
                    <div className="bg-blue-100 dark:bg-blue-900 p-2 rounded-lg">
                      <FileText className="w-5 h-5 text-blue-600 dark:text-blue-400" />
                    </div>
                    <div className="ml-4">
                      <h3 className="text-lg font-semibold text-gray-900 dark:text-white">
                        {ficha.nome}
                      </h3>
                      <p className="text-sm text-gray-600 dark:text-gray-400">
                        {ficha.arquetipo} • Nível {ficha.nivel}
                      </p>
                    </div>
                  </div>
                  <div className="flex items-center space-x-2">
                    <span className="text-sm text-gray-500 dark:text-gray-400">
                      {new Date(ficha.updatedAt).toLocaleDateString('pt-BR')}
                    </span>
                    <Link
                      to={`/fichas/${ficha.id}`}
                      className="text-blue-600 dark:text-blue-400 hover:underline text-sm font-medium"
                    >
                      Ver
                    </Link>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>

      {/* Tips & Updates */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
          <div className="flex items-center mb-4">
            <Star className="w-6 h-6 text-yellow-500" />
            <h3 className="text-lg font-semibold text-gray-900 dark:text-white ml-2">
              Dica do Dia
            </h3>
          </div>
          <p className="text-gray-600 dark:text-gray-400">
            Você pode usar a tecla <kbd className="px-2 py-1 bg-gray-100 dark:bg-gray-700 rounded">Ctrl+N</kbd> para criar uma nova ficha rapidamente em qualquer página da aplicação.
          </p>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
          <div className="flex items-center mb-4">
            <TrendingUp className="w-6 h-6 text-green-500" />
            <h3 className="text-lg font-semibold text-gray-900 dark:text-white ml-2">
              Últimas Atualizações
            </h3>
          </div>
          <ul className="text-sm text-gray-600 dark:text-gray-400 space-y-2">
            <li>• Adicionado suporte a novos arquetipos</li>
            <li>• Melhorias na calculadora de atributos</li>
            <li>• Correções de bugs no sistema de compartilhamento</li>
          </ul>
        </div>
      </div>
    </div>
  )
}

export default DashboardPage
