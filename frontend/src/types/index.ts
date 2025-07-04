// Global types and interfaces for TDC Sheets Frontend

// User types
export interface User {
  id: string
  name: string
  email: string
  username: string
  role: 'USER' | 'ADMIN'
  createdAt: string
  updatedAt: string
}

// Authentication types
export interface LoginRequest {
  email: string
  password: string
}

export interface RegisterRequest {
  name: string
  email: string
  username: string
  password: string
  confirmPassword: string
}

export interface AuthResponse {
  user: User
  token: string
  refreshToken: string
}

// Ficha types - Enums from backend
export type ArquetiposNome = 
  | 'ACADEMICO'
  | 'ACOLITO'
  | 'COMBATENTE'
  | 'FEITICEIRO'
  | 'LADINO'
  | 'NATURAL'

export type MatrizFeiticos = 
  | 'ADIAFANA'
  | 'ANA'
  | 'ARCANA'
  | 'ELFICA'
  | 'GNOMICA'
  | 'INFERNAL'
  | 'LUZIDIA'
  | 'MUNDANA'
  | 'NATURAL'
  | 'PRIMORDIAL'

export type ClassesFeiticos = 
  | 'ABJURACAO'
  | 'DIVINACAO'
  | 'ELEMENTAL'
  | 'ENCANTAMENTO'
  | 'EVOCACAO'
  | 'ILUSAO'
  | 'INVOCACAO'
  | 'MANIPULACAO'
  | 'MISTICA'
  | 'NATURAL'
  | 'NECROMANCIA'
  | 'PROFANA'
  | 'SAGRADA'
  | 'TRANSLOCACAO'
  | 'TRANSMUTACAO'

export type GrauPericia = 
  | 'LEIGO'
  | 'ADEPTO'
  | 'VERSADO'
  | 'MESTRE'
  | 'APEX_HUMANO'

export type Atributos = 
  | 'AGILIDADE'
  | 'CONSTITUICAO'
  | 'FORCA'
  | 'INFLUENCIA'
  | 'MENTE'
  | 'PRESENCA'

export type TiposDano = 
  | 'ACIDO'
  | 'ELETRICO'
  | 'CORTANTE'
  | 'PERFURANTE'
  | 'IMPACTO'
  | 'FOGO'
  | 'FRIO'
  | 'INTERNO'
  | 'MENTAL'
  | 'MISTICO'
  | 'PROFANO'
  | 'SAGRADO'
  | 'SONORO'
  | 'VENENO'

export type Raridades = 
  | 'COMUM'
  | 'ATIPICO'
  | 'RARO'
  | 'MUITO_RARO'
  | 'LENDARIO'

export type TiposSentidos = 
  | 'COMUM'
  | 'AGUCADO'
  | 'CEGO'
  | 'SURDO'

export type TiposDeslocamento = 
  | 'ANDANDO'
  | 'VOANDO'
  | 'NADANDO'
  | 'ESCALANDO'
  | 'ESCAVANDO'

export type TiposAlcance = 
  | 'PESSOAL'
  | 'ADJACENTE'
  | 'TOQUE'
  | 'CURTO'
  | 'MÉDIO'
  | 'LONGO'
  | 'MUITO_lONGO'
  | 'ILIMITADO'

export type TiposProficiencia = 
  | 'ARMAS_SIMPLES'
  | 'ARMAS_COMPLEXAS'
  | 'ARMAS_MARCIAIS'
  | 'ARMAS_PESADAS'
  | 'IDIOMA_COMUM'
  | 'IDIOMA_PRIMORDIAL'
  | 'IDIOMA_RUNICO'
  | 'IDIOMA_ANAO'
  | 'IDIOMA_AQUATICO'
  | 'IDIOMA_DRACONICO'
  | 'IDIOMA_ELFICO'
  | 'IDIOMA_GIGANTE'
  | 'IDIOMA_GNOMICO'
  | 'IDIOMA_INFERNAL'
  | 'IDIOMA_OOPARNEELA'
  | 'IDIOMA_ORC'
  | 'IDIOMA_SILVESTRE'
  | 'IDIOMA_URURIMI'
  | 'PROTECAO_LEVE'
  | 'PROTECAO_MEDIA'
  | 'PROTECAO_PESADA'
  | 'ESCUDOS'
  | 'INTRUMENTO_HABILIDADE'
  | 'INTRUMENTO_OFICIO'

export type TiposTamanho = 
  | 'MINUSCULO'
  | 'PEQUENO'
  | 'MEDIO'
  | 'GRANDE'
  | 'ENORME'
  | 'COLOSSAL'

export type HabilidadesResistencia = 
  | 'DETERMINACAO'
  | 'REFLEXO'
  | 'TENACIDADE'
  | 'VIGOR'

export type TempoAcoes = 
  | 'COMPLETA'
  | 'REACAO'
  | 'MAIOR'
  | 'DUAS_MENORES'
  | 'UMA_MENOR'
  | 'LIVRE'

export type TiposResistencia = 
  | 'ATAQUE'
  | 'RESISTENCIA'
  | 'RESISTENCIA_PARCIAL'
  | 'AUTOMATICO'

export type TiposEquipamentos = 
  | 'PEDRAS_PRECIOSAS'
  | 'OBRAS_ARTES'
  | 'MERCADORIAS_TECIDOS'
  | 'MERCADORIAS_METAIS'
  | 'MERCADORIAS_ANIMAIS'
  | 'MERCADORIAS_PROVISOES'
  | 'AVENTURA_EXPLORACAO_VIAGEM'
  | 'COMIDA_BEBIDA'
  | 'FEITICARIA_CONDUITES'
  | 'FEITICARIA_POCOES'
  | 'FEITICARIA_OUTROS'
  | 'FERRAMENTAS_HABILIDADE'
  | 'FERRAMENTAS_OFICIO'
  | 'FONTES_LUZ'
  | 'HERBALISMO'
  | 'INSTRUMENTOS_MUSICAIS'
  | 'MUNICOES'
  | 'PRODUTOS_ALQUIMICOS'
  | 'RECIPIENTES'
  | 'UTILITARIOS'
  | 'VENENOS'
  | 'VESTIMENTAS'
  | 'ARMAS_SIMPLES'
  | 'ARMAS_MARCIAIS'
  | 'ARMAS_COMPLEXAS'
  | 'ARMAS_PESADAS'
  | 'PROTECOES_LEVES'
  | 'PROTECOES_MEDIAS'
  | 'PROTECOES_PESADAS'
  | 'ESCUDOS'
  | 'TRALHA'
  | 'MATERIAIS_INGREDIENTES'
  | 'MATERIAIS_CRIACAO'

export type Idiomas = 
  | 'COMUM'
  | 'PRIMORDIAL'
  | 'RUNICO'
  | 'ANAO'
  | 'AQUATICO'
  | 'DRACONICO'
  | 'ELFICO'
  | 'GIGANTE'
  | 'GNOMICO'
  | 'INFERNAL'
  | 'OOPARNEELA'
  | 'ORC'
  | 'SILVESTRE'
  | 'URURIMI'

export type Alfabetos = 
  | 'COMUM'
  | 'PRIMORDIAL'
  | 'RUNICO'
  | 'GNOMICO'
  | 'AQUATICO'
  | 'DRACONICO'
  | 'ELFICO'
  | 'GIGANTE'
  | 'GLASNEE'
  | 'SILVESTRE'

// Ficha Personagem
export interface FichaPersonagem {
  id: string
  nome: string
  nivel: number
  experiencia: number
  userId: string
  origemId?: string
  linhgemId?: string
  versao: number
  tamanho?: TiposTamanho
  nivelSorte?: number
  background?: string
  createdAt: string
  updatedAt: string
  
  // Related entities
  atributos?: AtributosPersonagem[]
  habilidades?: HabilidadesPersonagem[]
  arquetipos?: ArquetiposPersonagem[]
  classes?: ClassesPersonagem[]
  equipamentos?: EquipamentosPersonagem[]
  feiticos?: FeiticosPersonagem[]
  deslocamentos?: DeslocamentoPersonagem[]
  sentidos?: SentidosPersonagem[]
}

export interface AtributosPersonagem {
  id: string
  fichaPersonagemId: string
  atributo: Atributos
  valor: number
  valorBase: number
  modificadorTemporario?: number
}

export interface HabilidadesPersonagem {
  id: string
  fichaPersonagemId: string
  habilidadeId: string
  grauHabilidade: GrauPericia
  graduacao: number
  treino: boolean
  updatedAt: string
}

export interface ArquetiposPersonagem {
  id: string
  fichaPersonagemId: string
  arquetipoId: string
  nivelArquetipo: number
  principal: boolean
}

export interface ClassesPersonagem {
  id: string
  fichaPersonagemId: string
  classeId: string
  nivel: number
}

export interface EquipamentosPersonagem {
  id: string
  fichaPersonagemId: string
  equipamentoId: string
  quantidade: number
  equipado: boolean
  localizacao?: string
}

export interface FeiticosPersonagem {
  id: string
  fichaPersonagemId: string
  feiticoId: string
  conhecido: boolean
  preparado: boolean
  updatedAt: string
}

export interface DeslocamentoPersonagem {
  id: string
  fichaPersonagemId: string
  tipo: TiposDeslocamento
  valor: number
  bonus?: number
}

export interface SentidosPersonagem {
  id: string
  fichaPersonagemId: string
  tipo: TiposSentidos
  alcance?: number
  modVisao?: number
}

export interface ResistenciasPersonagem {
  id: string
  fichaPersonagemId: string
  tipo: HabilidadesResistencia
  valor: number
  absorcao?: boolean
}

// Base entities (from backend database)
export interface Arquetipo {
  id: string
  nome: ArquetiposNome
  descricao: string
  defesa15: number
}

export interface Origem {
  id: string
  nome: string
  descricao: string
  habilidadeEspecial?: string
}

export interface Linhagem {
  id: string
  nome: string
  descricao: string
  tamanho: TiposTamanho
}

export interface Habilidade {
  id: string
  nome: string
  descricao: string
  atributo: Atributos
  combate: boolean
}

export interface Feitico {
  id: string
  nome: string
  descricao: string
  circulo: number
  matriz: MatrizFeiticos
  classe: ClassesFeiticos
  alcance: TiposAlcance
  duracao: string
  tempoAcao: TempoAcoes
  aprimoramento?: string
}

export interface Equipamento {
  id: string
  nome: string
  descricao: string
  tipo: TiposEquipamentos
  raridade: Raridades
  preco?: number
  peso?: number
}

// API Response types
export interface ApiResponse<T = any> {
  data: T
  message?: string
  status: number
}

export interface PaginatedResponse<T> {
  content: T[]
  totalElements: number
  totalPages: number
  size: number
  number: number
  first: boolean
  last: boolean
}

// Common UI types
export interface SelectOption {
  value: string | number
  label: string
  disabled?: boolean
}

export interface TableColumn<T = any> {
  key: keyof T | string
  label: string
  sortable?: boolean
  render?: (value: any, item: T) => React.ReactNode
}

// Form types
export interface FormError {
  field: string
  message: string
}

export interface ValidationResult {
  isValid: boolean
  errors: FormError[]
}

// Theme types
export type Theme = 'light' | 'dark' | 'auto'

// Loading states
export interface LoadingState {
  isLoading: boolean
  error: string | null
}

// Route types
export interface RouteConfig {
  path: string
  component: React.ComponentType
  exact?: boolean
  protected?: boolean
  roles?: string[]
}

// Export utility types
export type WithLoading<T> = T & LoadingState
export type Optional<T, K extends keyof T> = Omit<T, K> & Partial<Pick<T, K>>
export type RequiredFields<T, K extends keyof T> = T & Required<Pick<T, K>>
