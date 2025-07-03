import { FC } from 'react'
import { useNavigate } from 'react-router-dom'
import { ArrowLeft, Save } from 'lucide-react'

// TODO: Implementar página de criação de ficha completa - FRONT-005
// - Wizard step-by-step para criação
// - Validação em tempo real dos campos
// - Preview da ficha durante criação
// - Auto-save como rascunho
// - Templates de fichas pré-definidas
// - Importação de fichas de outras fontes

// TODO: Implementar integração com regras TDC - RPG-001 até RPG-004
// - Seleção de origem com bonus automáticos
// - Distribuição de pontos de atributo com limites
// - Cálculos automáticos de PV, PP, defesa
// - Validação de pré-requisitos para habilidades
// - Sistema de pontos de experiência
// - Validação de equipamentos por classe

const CreateFichaPage: FC = () => {
  const navigate = useNavigate()

  const handleSave = () => {
    // TODO: Implement ficha creation logic
    console.log('Creating new ficha...')
    navigate('/fichas')
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
              Nova Ficha
            </h1>
            <p className="text-gray-600 dark:text-gray-400 mt-2">
              Crie um novo personagem para suas aventuras
            </p>
          </div>
        </div>
        <button
          onClick={handleSave}
          className="inline-flex items-center px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition-colors font-medium"
        >
          <Save className="w-4 h-4 mr-2" />
          Salvar Ficha
        </button>
      </div>

      {/* Form Content */}
      <div className="bg-white dark:bg-gray-800 rounded-lg shadow border border-gray-200 dark:border-gray-700 p-8">
        <div className="text-center py-12">
          <h3 className="text-lg font-medium text-gray-900 dark:text-white mb-2">
            Formulário de Criação de Ficha
          </h3>
          <p className="text-gray-600 dark:text-gray-400">
            Esta página será implementada na próxima fase do desenvolvimento.
          </p>
          <p className="text-sm text-gray-500 dark:text-gray-400 mt-4">
            Funcionalidades previstas: Informações básicas, seleção de arquétipo, 
            distribuição de atributos, seleção de habilidades e equipamentos iniciais.
          </p>
        </div>
      </div>

      {/* TODO: Implementar estado do formulário:
      // - currentStep: number (para wizard)
      // - fichaData: Partial<FichaPersonagem>
      // - validationErrors: Record<string, string>
      // - isSubmitting: boolean
      // - isDraft: boolean */}

      {/* TODO: Implementar lógica de steps:
      // 1. Informações básicas (nome, origem, linhagem)
      // 2. Arquetipos e classes
      // 3. Distribuição de atributos
      // 4. Seleção de habilidades
      // 5. Equipamentos iniciais
      // 6. Feitiços (se aplicável)
      // 7. Revisão final */}
    </div>
  )
}

export default CreateFichaPage

// TODO: Implementar funcionalidades avançadas:
// - Auto-complete para nomes baseado em linhagem
// - Gerador aleatório de personagem
// - Import de conceito de personagem (texto livre)
// - Sistema de templates favoritos
// - Validação de regras em tempo real
// - Export de PDF já na criação
