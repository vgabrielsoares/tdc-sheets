import { FC } from 'react'
import { Link } from 'react-router-dom'
import { Plus, Search, Filter, FileText, Edit, Eye, Trash2 } from 'lucide-react'

const FichasListPage: FC = () => {
  // Mock data - will be replaced with real data from API
  const fichas = [
    {
      id: '1',
      nome: 'Thorin Pedrapunho',
      arquetipo: 'Combatente',
      nivel: 5,
      experiencia: 2500,
      updatedAt: '2024-01-15T10:30:00Z',
      compartilhada: false
    },
    {
      id: '2',
      nome: 'Luna Luaverde',
      arquetipo: 'Feiticeiro',
      nivel: 3,
      experiencia: 1200,
      updatedAt: '2024-01-14T16:45:00Z',
      compartilhada: true
    },
    {
      id: '3',
      nome: 'Kael Ventolâmina',
      arquetipo: 'Ladino',
      nivel: 4,
      experiencia: 1800,
      updatedAt: '2024-01-13T09:15:00Z',
      compartilhada: false
    },
    {
      id: '4',
      nome: 'Aria Curaferidas',
      arquetipo: 'Acólito',
      nivel: 2,
      experiencia: 800,
      updatedAt: '2024-01-12T14:20:00Z',
      compartilhada: true
    }
  ]

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold text-gray-900 dark:text-white">
            Minhas Fichas
          </h1>
          <p className="text-gray-600 dark:text-gray-400 mt-2">
            Gerencie todas as suas fichas de personagem
          </p>
        </div>
        <Link
          to="/fichas/new"
          className="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors font-medium"
        >
          <Plus className="w-4 h-4 mr-2" />
          Nova Ficha
        </Link>
      </div>

      {/* Filters and Search */}
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-6">
        <div className="flex flex-col lg:flex-row lg:items-center gap-4">
          {/* Search */}
          <div className="flex-1 relative">
            <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
            <input
              type="text"
              placeholder="Buscar por nome, arquétipo ou nível..."
              className="w-full pl-10 pr-4 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-white placeholder-gray-500 dark:placeholder-gray-400 focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            />
          </div>

          {/* Filters */}
          <div className="flex items-center gap-4">
            <select className="px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-900 dark:text-white focus:ring-2 focus:ring-blue-500 focus:border-blue-500">
              <option value="">Todos os arquetipos</option>
              <option value="ACADEMICO">Acadêmico</option>
              <option value="ACOLITO">Acólito</option>
              <option value="COMBATENTE">Combatente</option>
              <option value="FEITICEIRO">Feiticeiro</option>
              <option value="LADINO">Ladino</option>
              <option value="NATURAL">Natural</option>
            </select>

            <button className="inline-flex items-center px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-600 transition-colors">
              <Filter className="w-4 h-4 mr-2" />
              Filtros
            </button>
          </div>
        </div>
      </div>

      {/* Stats */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-4">
          <div className="flex items-center">
            <FileText className="w-6 h-6 text-blue-500" />
            <div className="ml-3">
              <p className="text-sm text-gray-500 dark:text-gray-400">Total</p>
              <p className="text-lg font-semibold text-gray-900 dark:text-white">
                {fichas.length}
              </p>
            </div>
          </div>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-4">
          <div className="flex items-center">
            <Eye className="w-6 h-6 text-green-500" />
            <div className="ml-3">
              <p className="text-sm text-gray-500 dark:text-gray-400">Compartilhadas</p>
              <p className="text-lg font-semibold text-gray-900 dark:text-white">
                {fichas.filter(f => f.compartilhada).length}
              </p>
            </div>
          </div>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-4">
          <div className="flex items-center">
            <FileText className="w-6 h-6 text-purple-500" />
            <div className="ml-3">
              <p className="text-sm text-gray-500 dark:text-gray-400">Nível Médio</p>
              <p className="text-lg font-semibold text-gray-900 dark:text-white">
                {Math.round(fichas.reduce((acc, f) => acc + f.nivel, 0) / fichas.length)}
              </p>
            </div>
          </div>
        </div>

        <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-4">
          <div className="flex items-center">
            <FileText className="w-6 h-6 text-orange-500" />
            <div className="ml-3">
              <p className="text-sm text-gray-500 dark:text-gray-400">XP Total</p>
              <p className="text-lg font-semibold text-gray-900 dark:text-white">
                {fichas.reduce((acc, f) => acc + f.experiencia, 0).toLocaleString()}
              </p>
            </div>
          </div>
        </div>
      </div>

      {/* Fichas Grid */}
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {fichas.map((ficha) => (
          <div
            key={ficha.id}
            className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 hover:shadow-md transition-shadow"
          >
            <div className="p-6">
              <div className="flex items-start justify-between mb-4">
                <div>
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">
                    {ficha.nome}
                  </h3>
                  <p className="text-sm text-gray-600 dark:text-gray-400">
                    {ficha.arquetipo}
                  </p>
                </div>
                {ficha.compartilhada && (
                  <span className="inline-flex items-center px-2 py-1 rounded-full text-xs font-medium bg-green-100 dark:bg-green-900 text-green-800 dark:text-green-200">
                    Compartilhada
                  </span>
                )}
              </div>

              <div className="space-y-2 mb-4">
                <div className="flex justify-between text-sm">
                  <span className="text-gray-500 dark:text-gray-400">Nível:</span>
                  <span className="font-medium text-gray-900 dark:text-white">{ficha.nivel}</span>
                </div>
                <div className="flex justify-between text-sm">
                  <span className="text-gray-500 dark:text-gray-400">Experiência:</span>
                  <span className="font-medium text-gray-900 dark:text-white">
                    {ficha.experiencia.toLocaleString()} XP
                  </span>
                </div>
                <div className="flex justify-between text-sm">
                  <span className="text-gray-500 dark:text-gray-400">Atualizada:</span>
                  <span className="font-medium text-gray-900 dark:text-white">
                    {new Date(ficha.updatedAt).toLocaleDateString('pt-BR')}
                  </span>
                </div>
              </div>

              {/* Actions */}
              <div className="flex items-center justify-between pt-4 border-t border-gray-200 dark:border-gray-700">
                <Link
                  to={`/fichas/${ficha.id}`}
                  className="inline-flex items-center text-blue-600 dark:text-blue-400 hover:text-blue-700 dark:hover:text-blue-300 text-sm font-medium"
                >
                  <Eye className="w-4 h-4 mr-1" />
                  Ver
                </Link>

                <div className="flex items-center space-x-3">
                  <Link
                    to={`/fichas/${ficha.id}/edit`}
                    className="inline-flex items-center text-gray-600 dark:text-gray-400 hover:text-gray-700 dark:hover:text-gray-300 text-sm"
                  >
                    <Edit className="w-4 h-4" />
                  </Link>

                  <button className="inline-flex items-center text-red-600 dark:text-red-400 hover:text-red-700 dark:hover:text-red-300 text-sm">
                    <Trash2 className="w-4 h-4" />
                  </button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>

      {/* Empty State */}
      {fichas.length === 0 && (
        <div className="text-center py-12">
          <FileText className="w-16 h-16 text-gray-400 mx-auto mb-4" />
          <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">
            Nenhuma ficha encontrada
          </h3>
          <p className="text-gray-600 dark:text-gray-400 mb-6">
            Comece criando sua primeira ficha de personagem.
          </p>
          <Link
            to="/fichas/new"
            className="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors font-medium"
          >
            <Plus className="w-4 h-4 mr-2" />
            Criar primeira ficha
          </Link>
        </div>
      )}
    </div>
  )
}

export default FichasListPage
