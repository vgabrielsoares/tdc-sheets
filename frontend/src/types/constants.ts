// Constants and labels for TDC Sheets enums and types
import type {
  ArquetiposNome,
  MatrizFeiticos,
  ClassesFeiticos,
  GrauPericia,
  Atributos,
  TiposDano,
  Raridades,
  TiposSentidos,
  TiposDeslocamento,
  TiposAlcance,
  TiposProficiencia,
  TiposTamanho,
  HabilidadesResistencia,
  TempoAcoes,
  TiposResistencia,
  TiposEquipamentos,
  Idiomas,
  Alfabetos
} from './index'

// Arquetipos labels
export const ARQUETIPOS_LABELS: Record<ArquetiposNome, string> = {
  ACADEMICO: 'Acadêmico',
  ACOLITO: 'Acólito',
  COMBATENTE: 'Combatente',
  FEITICEIRO: 'Feiticeiro',
  LADINO: 'Ladino',
  NATURAL: 'Natural'
}

// Matriz de Feitiços labels
export const MATRIZ_FEITICOS_LABELS: Record<MatrizFeiticos, string> = {
  ADIAFANA: 'Adiáfana',
  ANA: 'Ana',
  ARCANA: 'Arcana',
  ELFICA: 'Élfica',
  GNOMICA: 'Gnômica',
  INFERNAL: 'Infernal',
  LUZIDIA: 'Luzídia',
  MUNDANA: 'Mundana',
  NATURAL: 'Natural',
  PRIMORDIAL: 'Primordial'
}

// Classes de Feitiços labels
export const CLASSES_FEITICOS_LABELS: Record<ClassesFeiticos, string> = {
  ABJURACAO: 'Abjuração',
  DIVINACAO: 'Divinação',
  ELEMENTAL: 'Elemental',
  ENCANTAMENTO: 'Encantamento',
  EVOCACAO: 'Evocação',
  ILUSAO: 'Ilusão',
  INVOCACAO: 'Invocação',
  MANIPULACAO: 'Manipulação',
  MISTICA: 'Mística',
  NATURAL: 'Natural',
  NECROMANCIA: 'Necromancia',
  PROFANA: 'Profana',
  SAGRADA: 'Sagrada',
  TRANSLOCACAO: 'Translocação',
  TRANSMUTACAO: 'Transmutação'
}

// Grau de Perícia labels
export const GRAU_PERICIA_LABELS: Record<GrauPericia, string> = {
  LEIGO: 'Leigo',
  ADEPTO: 'Adepto',
  VERSADO: 'Versado',
  MESTRE: 'Mestre',
  APEX_HUMANO: 'Ápex Humano'
}

// Atributos labels
export const ATRIBUTOS_LABELS: Record<Atributos, string> = {
  AGILIDADE: 'Agilidade',
  CONSTITUICAO: 'Constituição',
  FORCA: 'Força',
  INFLUENCIA: 'Influência',
  MENTE: 'Mente',
  PRESENCA: 'Presença'
}

// Tipos de Dano labels
export const TIPOS_DANO_LABELS: Record<TiposDano, string> = {
  ACIDO: 'Ácido',
  ELETRICO: 'Elétrico',
  CORTANTE: 'Cortante',
  PERFURANTE: 'Perfurante',
  IMPACTO: 'Impacto',
  FOGO: 'Fogo',
  FRIO: 'Frio',
  INTERNO: 'Interno',
  MENTAL: 'Mental',
  MISTICO: 'Místico',
  PROFANO: 'Profano',
  SAGRADO: 'Sagrado',
  SONORO: 'Sonoro',
  VENENO: 'Veneno'
}

// Raridades labels
export const RARIDADES_LABELS: Record<Raridades, string> = {
  COMUM: 'Comum',
  ATIPICO: 'Atípico',
  RARO: 'Raro',
  MUITO_RARO: 'Muito Raro',
  LENDARIO: 'Lendário'
}

// Tipos de Sentidos labels
export const TIPOS_SENTIDOS_LABELS: Record<TiposSentidos, string> = {
  COMUM: 'Comum',
  AGUCADO: 'Aguçado',
  CEGO: 'Cego',
  SURDO: 'Surdo'
}

// Tipos de Deslocamento labels
export const TIPOS_DESLOCAMENTO_LABELS: Record<TiposDeslocamento, string> = {
  ANDANDO: 'Andando',
  VOANDO: 'Voando',
  NADANDO: 'Nadando',
  ESCALANDO: 'Escalando',
  ESCAVANDO: 'Escavando'
}

// Tipos de Alcance labels
export const TIPOS_ALCANCE_LABELS: Record<TiposAlcance, string> = {
  PESSOAL: 'Pessoal',
  ADJACENTE: 'Adjacente',
  TOQUE: 'Toque',
  CURTO: 'Curto',
  MÉDIO: 'Médio',
  LONGO: 'Longo',
  MUITO_LONGO: 'Muito Longo',
  ILIMITADO: 'Ilimitado'
}

// Tipos de Tamanho labels
export const TIPOS_TAMANHO_LABELS: Record<TiposTamanho, string> = {
  MINUSCULO: 'Minúsculo',
  PEQUENO: 'Pequeno',
  MEDIO: 'Médio',
  GRANDE: 'Grande',
  ENORME: 'Enorme',
  COLOSSAL: 'Colossal'
}

// Habilidades de Resistência labels
export const HABILIDADES_RESISTENCIA_LABELS: Record<HabilidadesResistencia, string> = {
  DETERMINACAO: 'Determinação',
  REFLEXO: 'Reflexo',
  TENACIDADE: 'Tenacidade',
  VIGOR: 'Vigor'
}

// Tempo de Ações labels
export const TEMPO_ACOES_LABELS: Record<TempoAcoes, string> = {
  COMPLETA: 'Ação Completa',
  REACAO: 'Reação',
  MAIOR: 'Ação Maior',
  DUAS_MENORES: 'Duas Ações Menores',
  UMA_MENOR: 'Uma Ação Menor',
  LIVRE: 'Ação Livre'
}

// Tipos de Resistência labels
export const TIPOS_RESISTENCIA_LABELS: Record<TiposResistencia, string> = {
  ATAQUE: 'Ataque',
  RESISTENCIA: 'Resistência',
  RESISTENCIA_PARCIAL: 'Resistência Parcial',
  AUTOMATICO: 'Automático'
}

// Tipos de Proficiência labels
export const TIPOS_PROFICIENCIA_LABELS: Record<TiposProficiencia, string> = {
  ARMAS_SIMPLES: 'Armas Simples',
  ARMAS_COMPLEXAS: 'Armas Complexas',
  ARMAS_MARCIAIS: 'Armas Marciais',
  ARMAS_PESADAS: 'Armas Pesadas',
  IDIOMA_COMUM: 'Idioma Comum',
  IDIOMA_PRIMORDIAL: 'Idioma Primordial',
  IDIOMA_RUNICO: 'Idioma Rúnico',
  IDIOMA_ANAO: 'Idioma Anão',
  IDIOMA_AQUATICO: 'Idioma Aquático',
  IDIOMA_DRACONICO: 'Idioma Dracônico',
  IDIOMA_ELFICO: 'Idioma Élfico',
  IDIOMA_GIGANTE: 'Idioma Gigante',
  IDIOMA_GNOMICO: 'Idioma Gnômico',
  IDIOMA_INFERNAL: 'Idioma Infernal',
  IDIOMA_OOPARNEELA: 'Idioma Ooparneela',
  IDIOMA_ORC: 'Idioma Orc',
  IDIOMA_SILVESTRE: 'Idioma Silvestre',
  IDIOMA_URURIMI: 'Idioma Ururimi',
  PROTECAO_LEVE: 'Proteção Leve',
  PROTECAO_MEDIA: 'Proteção Média',
  PROTECAO_PESADA: 'Proteção Pesada',
  ESCUDOS: 'Escudos',
  INTRUMENTO_HABILIDADE: 'Instrumento de Habilidade',
  INTRUMENTO_OFICIO: 'Instrumento de Ofício'
}

// Tipos de Equipamentos labels
export const TIPOS_EQUIPAMENTOS_LABELS: Record<TiposEquipamentos, string> = {
  PEDRAS_PRECIOSAS: 'Pedras Preciosas',
  OBRAS_ARTES: 'Obras de Arte',
  MERCADORIAS_TECIDOS: 'Mercadorias - Tecidos',
  MERCADORIAS_METAIS: 'Mercadorias - Metais',
  MERCADORIAS_ANIMAIS: 'Mercadorias - Animais',
  MERCADORIAS_PROVISOES: 'Mercadorias - Provisões',
  AVENTURA_EXPLORACAO_VIAGEM: 'Aventura, Exploração e Viagem',
  COMIDA_BEBIDA: 'Comida e Bebida',
  FEITICARIA_CONDUITES: 'Feitiçaria - Conduítes',
  FEITICARIA_POCOES: 'Feitiçaria - Poções',
  FEITICARIA_OUTROS: 'Feitiçaria - Outros',
  FERRAMENTAS_HABILIDADE: 'Ferramentas de Habilidade',
  FERRAMENTAS_OFICIO: 'Ferramentas de Ofício',
  FONTES_LUZ: 'Fontes de Luz',
  HERBALISMO: 'Herbalismo',
  INSTRUMENTOS_MUSICAIS: 'Instrumentos Musicais',
  MUNICOES: 'Munições',
  PRODUTOS_ALQUIMICOS: 'Produtos Alquímicos',
  RECIPIENTES: 'Recipientes',
  UTILITARIOS: 'Utilitários',
  VENENOS: 'Venenos',
  VESTIMENTAS: 'Vestimentas',
  ARMAS_SIMPLES: 'Armas Simples',
  ARMAS_MARCIAIS: 'Armas Marciais',
  ARMAS_COMPLEXAS: 'Armas Complexas',
  ARMAS_PESADAS: 'Armas Pesadas',
  PROTECOES_LEVES: 'Proteções Leves',
  PROTECOES_MEDIAS: 'Proteções Médias',
  PROTECOES_PESADAS: 'Proteções Pesadas',
  ESCUDOS: 'Escudos',
  TRALHA: 'Tralha',
  MATERIAIS_INGREDIENTES: 'Materiais e Ingredientes',
  MATERIAIS_CRIACAO: 'Materiais de Criação'
}

// Idiomas labels
export const IDIOMAS_LABELS: Record<Idiomas, string> = {
  COMUM: 'Comum',
  PRIMORDIAL: 'Primordial',
  RUNICO: 'Rúnico',
  ANAO: 'Anão',
  AQUATICO: 'Aquático',
  DRACONICO: 'Dracônico',
  ELFICO: 'Élfico',
  GIGANTE: 'Gigante',
  GNOMICO: 'Gnômico',
  INFERNAL: 'Infernal',
  OOPARNEELA: 'Ooparneela',
  ORC: 'Orc',
  SILVESTRE: 'Silvestre',
  URURIMI: 'Ururimi'
}

// Alfabetos labels
export const ALFABETOS_LABELS: Record<Alfabetos, string> = {
  COMUM: 'Comum',
  PRIMORDIAL: 'Primordial',
  RUNICO: 'Rúnico',
  GNOMICO: 'Gnômico',
  AQUATICO: 'Aquático',
  DRACONICO: 'Dracônico',
  ELFICO: 'Élfico',
  GIGANTE: 'Gigante',
  GLASNEE: 'Glasnee',
  SILVESTRE: 'Silvestre'
}

// Utility functions
export const getArquetipoLabel = (arquetipo: ArquetiposNome): string => 
  ARQUETIPOS_LABELS[arquetipo] || arquetipo

export const getAtributoLabel = (atributo: Atributos): string => 
  ATRIBUTOS_LABELS[atributo] || atributo

export const getRaridadeLabel = (raridade: Raridades): string => 
  RARIDADES_LABELS[raridade] || raridade

export const getTamanhoLabel = (tamanho: TiposTamanho): string => 
  TIPOS_TAMANHO_LABELS[tamanho] || tamanho

export const getGrauPericiaLabel = (grau: GrauPericia): string => 
  GRAU_PERICIA_LABELS[grau] || grau

export const getClasseFeiticoLabel = (classe: ClassesFeiticos): string => 
  CLASSES_FEITICOS_LABELS[classe] || classe

export const getTipoDanoLabel = (tipo: TiposDano): string => 
  TIPOS_DANO_LABELS[tipo] || tipo

// Options arrays for selects
export const ARQUETIPOS_OPTIONS = Object.entries(ARQUETIPOS_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const ATRIBUTOS_OPTIONS = Object.entries(ATRIBUTOS_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const GRAU_PERICIA_OPTIONS = Object.entries(GRAU_PERICIA_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const RARIDADES_OPTIONS = Object.entries(RARIDADES_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const TIPOS_TAMANHO_OPTIONS = Object.entries(TIPOS_TAMANHO_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const MATRIZ_FEITICOS_OPTIONS = Object.entries(MATRIZ_FEITICOS_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const CLASSES_FEITICOS_OPTIONS = Object.entries(CLASSES_FEITICOS_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const TIPOS_DANO_OPTIONS = Object.entries(TIPOS_DANO_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const IDIOMAS_OPTIONS = Object.entries(IDIOMAS_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const TIPOS_PROFICIENCIA_OPTIONS = Object.entries(TIPOS_PROFICIENCIA_LABELS).map(([value, label]) => ({
  value,
  label
}))

export const TIPOS_EQUIPAMENTOS_OPTIONS = Object.entries(TIPOS_EQUIPAMENTOS_LABELS).map(([value, label]) => ({
  value,
  label
}))

// Common constants
export const NIVEL_MAXIMO = 20
export const EXPERIENCIA_POR_NIVEL: Record<number, number> = {
  1: 0,
  2: 100,
  3: 300,
  4: 600,
  5: 1000,
  6: 1500,
  7: 2100,
  8: 2800,
  9: 3600,
  10: 4500,
  11: 5500,
  12: 6600,
  13: 7800,
  14: 9100,
  15: 10500,
  16: 12000,
  17: 13600,
  18: 15300,
  19: 17100,
  20: 19000
}

export const CIRCULOS_FEITICO = [1, 2, 3, 4, 5, 6, 7, 8] as const
export type CirculoFeitico = typeof CIRCULOS_FEITICO[number]

// Attribute modifiers calculation
export const calcularModificador = (valorAtributo: number): number => {
  return Math.floor((valorAtributo - 10) / 2)
}

// Level experience calculations
export const calcularNivelPorExperiencia = (experiencia: number): number => {
  for (let nivel = NIVEL_MAXIMO; nivel >= 1; nivel--) {
    if (experiencia >= EXPERIENCIA_POR_NIVEL[nivel]) {
      return nivel
    }
  }
  return 1
}

export const calcularExperienciaParaProximoNivel = (experienciaAtual: number): number => {
  const nivelAtual = calcularNivelPorExperiencia(experienciaAtual)
  if (nivelAtual >= NIVEL_MAXIMO) return 0
  return EXPERIENCIA_POR_NIVEL[nivelAtual + 1] - experienciaAtual
}
