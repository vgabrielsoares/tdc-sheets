import { FC } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { ArrowLeft, Edit, Share, Download } from 'lucide-react'

const ViewFichaPage: FC = () => {
  const { id } = useParams<{ id: string }>()
  const navigate = useNavigate()

  // Mock data - will be replaced with real data from API
  const ficha = {
    id: id || '1',
    nome: 'Thorin Pedrapunho',
    arquetipo: 'Combatente',
    nivel: 5,
    experiencia: 2500,
    updatedAt: '2024-01-15T10:30:00Z'
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div className="flex items-center">
          <button
            onClick={() => navigate('/fichas')}
            className="mr-4 p-2 text-gray-600 dark:text-gray-400 hover:text-gray-900 dark:hover:text-white"
          >
            <ArrowLeft className="w-5 h-5" />
          </button>
          <div>
            <h1 className="text-3xl font-bold text-gray-900 dark:text-white">
              {ficha.nome}
            </h1>
            <p className="text-gray-600 dark:text-gray-400 mt-2">
              {ficha.arquetipo} • Nível {ficha.nivel}
            </p>
          </div>
        </div>
        <div className="flex items-center space-x-3">
          <button className="inline-flex items-center px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-600 transition-colors">
            <Share className="w-4 h-4 mr-2" />
            Compartilhar
          </button>
          <button className="inline-flex items-center px-3 py-2 border border-gray-300 dark:border-gray-600 rounded-md bg-white dark:bg-gray-700 text-gray-700 dark:text-gray-300 hover:bg-gray-50 dark:hover:bg-gray-600 transition-colors">
            <Download className="w-4 h-4 mr-2" />
            Exportar
          </button>
          <button
            onClick={() => navigate(`/fichas/${id}/edit`)}
            className="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors font-medium"
          >
            <Edit className="w-4 h-4 mr-2" />
            Editar
          </button>
        </div>
      </div>

      {/* Ficha Content */}
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-8">
        <div className="text-center py-12">
          <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">
            Visualização da Ficha
          </h3>
          <p className="text-gray-600 dark:text-gray-400">
            Esta página será implementada na próxima fase do desenvolvimento.
          </p>
          <p className="text-sm text-gray-500 dark:text-gray-400 mt-4">
            Funcionalidades previstas: Visualização completa dos atributos, habilidades, 
            equipamentos, feitiços e todas as informações do personagem.
          </p>
        </div>
      </div>
    </div>
  )
}

export default ViewFichaPage
