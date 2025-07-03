import { FC } from 'react'
import { useParams, useNavigate } from 'react-router-dom'
import { ArrowLeft, Save, Trash2 } from 'lucide-react'

const EditFichaPage: FC = () => {
  const { id } = useParams<{ id: string }>()
  const navigate = useNavigate()

  // Mock data - will be replaced with real data from API
  const ficha = {
    id: id || '1',
    nome: 'Thorin Pedrapunho',
    arquetipo: 'Combatente',
    nivel: 5,
    experiencia: 2500
  }

  const handleSave = () => {
    // TODO: Implement ficha update logic
    console.log('Updating ficha...')
    navigate(`/fichas/${id}`)
  }

  const handleDelete = () => {
    // TODO: Implement ficha deletion logic
    if (window.confirm('Tem certeza que deseja excluir esta ficha?')) {
      console.log('Deleting ficha...')
      navigate('/fichas')
    }
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div className="flex items-center">
          <button
            onClick={() => navigate(`/fichas/${id}`)}
            className="mr-4 p-2 text-gray-600 dark:text-gray-400 hover:text-gray-900 dark:hover:text-white"
          >
            <ArrowLeft className="w-5 h-5" />
          </button>
          <div>
            <h1 className="text-3xl font-bold text-gray-900 dark:text-white">
              Editar {ficha.nome}
            </h1>
            <p className="text-gray-600 dark:text-gray-400 mt-2">
              Modifique os dados do seu personagem
            </p>
          </div>
        </div>
        <div className="flex items-center space-x-3">
          <button
            onClick={handleDelete}
            className="inline-flex items-center px-3 py-2 border border-red-300 dark:border-red-600 rounded-md bg-white dark:bg-gray-700 text-red-700 dark:text-red-400 hover:bg-red-50 dark:hover:bg-red-900/20 transition-colors"
          >
            <Trash2 className="w-4 h-4 mr-2" />
            Excluir
          </button>
          <button
            onClick={handleSave}
            className="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors font-medium"
          >
            <Save className="w-4 h-4 mr-2" />
            Salvar Alterações
          </button>
        </div>
      </div>

      {/* Edit Form */}
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-8">
        <div className="text-center py-12">
          <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">
            Formulário de Edição de Ficha
          </h3>
          <p className="text-gray-600 dark:text-gray-400">
            Esta página será implementada na próxima fase do desenvolvimento.
          </p>
          <p className="text-sm text-gray-500 dark:text-gray-400 mt-4">
            Funcionalidades previstas: Edição completa de todos os campos da ficha, 
            validações em tempo real e histórico de alterações.
          </p>
        </div>
      </div>
    </div>
  )
}

export default EditFichaPage
