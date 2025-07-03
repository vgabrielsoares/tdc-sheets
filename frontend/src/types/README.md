# Types - Definições TypeScript

## Visão Geral
Definições de tipos TypeScript centralizadas para garantir type safety e consistência em toda a aplicação TDC Sheets.

## Estrutura de Types

```
types/
├── api/                   # Tipos relacionados à API
│   ├── auth.ts           # Autenticação e usuários
│   ├── ficha.ts          # Fichas e personagens
│   ├── game.ts           # Regras do jogo
│   └── common.ts         # Tipos comuns de API
├── ui/                   # Tipos de interface
│   ├── components.ts     # Props de componentes
│   ├── forms.ts          # Formulários e validação
│   └── navigation.ts     # Roteamento e navegação
├── store/                # Tipos do Redux
│   ├── auth.ts          # Estado de autenticação
│   ├── fichas.ts        # Estado das fichas
│   └── ui.ts            # Estado da UI
├── utils/                # Tipos utilitários
│   ├── helpers.ts       # Funções auxiliares
│   └── constants.ts     # Constantes tipadas
└── index.ts             # Exportações principais
```

## Tipos de API

### Autenticação e Usuários
```typescript
// api/auth.ts
export interface User {
  id: number;
  username: string;
  email: string;
  role: UserRole;
  createdAt: string;
  updatedAt: string;
  profile?: UserProfile;
}

export interface UserProfile {
  firstName?: string;
  lastName?: string;
  avatar?: string;
  bio?: string;
  preferences: UserPreferences;
}

export interface UserPreferences {
  theme: 'light' | 'dark' | 'system';
  language: 'pt-BR' | 'en-US';
  notifications: NotificationSettings;
}

export interface NotificationSettings {
  email: boolean;
  push: boolean;
  fichaUpdates: boolean;
  sharing: boolean;
}

export type UserRole = 'USER' | 'MASTER' | 'ADMIN';

// Login/Register
export interface LoginCredentials {
  email: string;
  password: string;
  rememberMe?: boolean;
}

export interface RegisterData {
  username: string;
  email: string;
  password: string;
  confirmPassword: string;
  acceptTerms: boolean;
}

export interface AuthResponse {
  user: User;
  token: string;
  refreshToken: string;
  expiresIn: number;
}

export interface RefreshTokenRequest {
  refreshToken: string;
}
```

### Fichas e Personagens
```typescript
// api/ficha.ts
export interface FichaPersonagem {
  id: number;
  nomePersonagem: string;
  userId: number;
  background?: string;
  experiencia: number;
  nivelSorte: number;
  tamanho: TipoTamanho;
  createdAt: string;
  updatedAt: string;
  versao: number;

  // Relacionamentos
  origem: Origem;
  linhagem: Linhagem;
  arquetipos: ArquetipoPersonagem[];
  classes: ClassePersonagem[];
  atributos: AtributoPersonagem[];
  habilidades: HabilidadePersonagem[];
  equipamentos: InventarioPersonagem[];
  feiticos: FeiticoPersonagem[];
  
  // Dados calculados
  pvPp: PvPpPersonagem;
  defesa: Defesa;
  resistencias: ResistenciaPersonagem[];
  deslocamentos: DeslocamentoPersonagem[];
}

export interface AtributoPersonagem {
  id: number;
  fichaPersonagemId: number;
  atributo: TipoAtributo;
  valor: number;
  valorTemp?: number;
  modificadorRacial: number;
  modificadorEquipamento: number;
  modificadorTemporario: number;
}

export type TipoAtributo = 
  | 'AGILIDADE' 
  | 'CONSTITUICAO' 
  | 'FORCA' 
  | 'INFLUENCIA' 
  | 'MENTE' 
  | 'PRESENCA';

export interface HabilidadePersonagem {
  id: number;
  fichaPersonagemId: number;
  habilidadeId: number;
  grauHabilidade: GrauPericia;
  atributoPersonagemId: number;
  pontos: number;
  graduado: boolean;
  habilidade: Habilidade;
}

export type GrauPericia = 
  | 'LEIGO' 
  | 'ADEPTO' 
  | 'VERSADO' 
  | 'MESTRE' 
  | 'APEX_HUMANO';

export interface ArquetipoPersonagem {
  id: number;
  fichaPersonagemId: number;
  arquetipoId: number;
  nivelArquetipo: number;
  arquetipo: Arquetipo;
}

export interface PvPpPersonagem {
  id: number;
  fichaPersonagemId: number;
  pvMaximo: number;
  pvAtual: number;
  pvTemporario?: number;
  ppMaximo: number;
  ppAtual: number;
  ppTemporario?: number;
  limitePp?: number;
  limitePpBonus?: number;
}

export interface Defesa {
  id: number;
  fichaPersonagemId: number;
  defesaPersonagem: number;
  armadura: number;
  vestindoArmadura: boolean;
  limiteAgilidade?: number;
  outroMod?: number;
}
```

### Regras do Jogo
```typescript
// api/game.ts
export interface Arquetipo {
  id: number;
  nome: TipoArquetipo;
  pvInicial: number;
  ppInicial: number;
  pvNivel: number;
  ppNivel: number;
  defesa5: number;
  defesa10: number;
  defesa15: number;
  descricao?: string;
  habilidades: HabilidadeArquetipo[];
  proficiencias: ProficienciaArquetipo[];
  caracteristicas: CaracteristicaArquetipo[];
}

export type TipoArquetipo = 
  | 'ACADEMICO'
  | 'ACOLITO'
  | 'COMBATENTE'
  | 'FEITICEIRO'
  | 'LADINO'
  | 'NATURAL';

export interface Linhagem {
  id: number;
  nome: string;
  descricao: string;
  tamanho: TipoTamanho;
  atributos: AtributoLinhagem[];
  idiomas: IdiomaLinhagem[];
  deslocamentos: DeslocamentoLinhagem[];
  sentidos: SentidoLinhagem[];
  caracteristicas: CaracteristicaAncestralidade[];
}

export type TipoTamanho = 
  | 'MINUSCULO'
  | 'PEQUENO'
  | 'MEDIO'
  | 'GRANDE'
  | 'ENORME'
  | 'COLOSSAL';

export interface Origem {
  id: number;
  nome: string;
  descricao: string;
  habilidadeEspecial: string;
  atributos: AtributoOrigem[];
  habilidades: HabilidadeOrigem[];
  itens: ItemOrigem[];
}

export interface Feitico {
  id: number;
  nome: string;
  descricao: string;
  circulo: number;
  matriz: MatrizFeitico;
  classe: ClasseFeitico;
  alcance: TipoAlcance;
  duracao: string;
  tempoConjuracao: TempoAcao;
  componentes: string;
  resistencia: TipoResistencia;
  aprimoramento?: string;
}

export type MatrizFeitico = 
  | 'ADIAFANA'
  | 'ANA'
  | 'ARCANA'
  | 'ELFICA'
  | 'GNOMICA'
  | 'INFERNAL'
  | 'LUZIDIA'
  | 'MUNDANA'
  | 'NATURAL'
  | 'PRIMORDIAL';

export type ClasseFeitico = 
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
  | 'TRANSMUTACAO';

export interface Equipamento {
  id: number;
  nome: string;
  descricao: string;
  tipo: TipoEquipamento;
  peso: number;
  preco: number;
  raridade: Raridade;
  propriedades?: EquipamentoPropriedades;
}

export type TipoEquipamento = 
  | 'ARMAS_SIMPLES'
  | 'ARMAS_MARCIAIS'
  | 'ARMAS_COMPLEXAS'
  | 'ARMAS_PESADAS'
  | 'PROTECOES_LEVES'
  | 'PROTECOES_MEDIAS'
  | 'PROTECOES_PESADAS'
  | 'ESCUDOS'
  | 'FERRAMENTAS_HABILIDADE'
  | 'FERRAMENTAS_OFICIO'
  | 'AVENTURA_EXPLORACAO_VIAGEM'
  | 'COMIDA_BEBIDA'
  | 'FEITICARIA_CONDUITES'
  | 'FEITICARIA_POCOES'
  | 'UTILITARIOS'
  | 'VESTIMENTAS'
  | 'TRALHA';

export type Raridade = 
  | 'COMUM'
  | 'ATIPICO'
  | 'RARO'
  | 'MUITO_RARO'
  | 'LENDARIO';
```

## Tipos de Interface

### Componentes
```typescript
// ui/components.ts
export interface BaseComponentProps {
  className?: string;
  children?: React.ReactNode;
  testId?: string;
}

export interface ButtonProps extends BaseComponentProps {
  variant?: 'primary' | 'secondary' | 'outline' | 'ghost';
  size?: 'sm' | 'md' | 'lg';
  disabled?: boolean;
  loading?: boolean;
  icon?: React.ReactNode;
  onClick?: (event: React.MouseEvent<HTMLButtonElement>) => void;
  type?: 'button' | 'submit' | 'reset';
}

export interface InputProps extends BaseComponentProps {
  type?: React.HTMLInputTypeAttribute;
  value?: string;
  placeholder?: string;
  disabled?: boolean;
  error?: string;
  label?: string;
  helpText?: string;
  required?: boolean;
  onChange?: (event: React.ChangeEvent<HTMLInputElement>) => void;
  onBlur?: (event: React.FocusEvent<HTMLInputElement>) => void;
  onFocus?: (event: React.FocusEvent<HTMLInputElement>) => void;
}

export interface ModalProps extends BaseComponentProps {
  isOpen: boolean;
  onClose: () => void;
  title?: string;
  size?: 'sm' | 'md' | 'lg' | 'xl';
  closeOnBackdrop?: boolean;
  closeOnEscape?: boolean;
}

export interface ToastProps {
  id: string;
  message: string;
  type: ToastType;
  duration?: number;
  action?: ToastAction;
}

export type ToastType = 'success' | 'error' | 'warning' | 'info';

export interface ToastAction {
  label: string;
  onClick: () => void;
}
```

### Formulários
```typescript
// ui/forms.ts
export interface FormField<T = any> {
  name: string;
  label: string;
  type: FieldType;
  value: T;
  error?: string;
  touched: boolean;
  required?: boolean;
  disabled?: boolean;
  placeholder?: string;
  helpText?: string;
  validation?: ValidationRule[];
}

export type FieldType = 
  | 'text'
  | 'email'
  | 'password'
  | 'number'
  | 'select'
  | 'checkbox'
  | 'radio'
  | 'textarea'
  | 'file';

export interface ValidationRule {
  type: ValidationType;
  value?: any;
  message: string;
}

export type ValidationType = 
  | 'required'
  | 'minLength'
  | 'maxLength'
  | 'min'
  | 'max'
  | 'pattern'
  | 'custom';

export interface FormState<T = Record<string, any>> {
  values: T;
  errors: Partial<Record<keyof T, string>>;
  touched: Partial<Record<keyof T, boolean>>;
  isValid: boolean;
  isSubmitting: boolean;
  isDirty: boolean;
}

export interface UseFormOptions<T> {
  initialValues: T;
  validationSchema?: ValidationSchema<T>;
  onSubmit: (values: T) => void | Promise<void>;
  validateOnChange?: boolean;
  validateOnBlur?: boolean;
}

export type ValidationSchema<T> = {
  [K in keyof T]?: (value: T[K]) => string | undefined;
};
```

## Tipos do Store

### Estado de Autenticação
```typescript
// store/auth.ts
export interface AuthState {
  user: User | null;
  token: string | null;
  refreshToken: string | null;
  isLoading: boolean;
  isAuthenticated: boolean;
  error: string | null;
  loginAttempts: number;
  lastLoginAt: string | null;
}

export interface LoginAction {
  type: 'auth/login';
  payload: {
    credentials: LoginCredentials;
  };
}

export interface LogoutAction {
  type: 'auth/logout';
}

export interface SetUserAction {
  type: 'auth/setUser';
  payload: User;
}

export type AuthAction = 
  | LoginAction 
  | LogoutAction 
  | SetUserAction;
```

### Estado das Fichas
```typescript
// store/fichas.ts
export interface FichasState {
  list: FichaPersonagem[];
  current: FichaPersonagem | null;
  isLoading: boolean;
  error: string | null;
  filters: FichaFilters;
  pagination: PaginationState;
  sharing: SharingState;
}

export interface FichaFilters {
  search: string;
  arquetipo: TipoArquetipo | null;
  linhagem: string | null;
  nivel: {
    min: number;
    max: number;
  };
  sortBy: FichaSortField;
  sortOrder: 'asc' | 'desc';
}

export type FichaSortField = 
  | 'nome'
  | 'nivel'
  | 'arquetipo'
  | 'createdAt'
  | 'updatedAt';

export interface PaginationState {
  page: number;
  pageSize: number;
  total: number;
  totalPages: number;
}

export interface SharingState {
  shareLinks: ShareLink[];
  isGeneratingLink: boolean;
  shareError: string | null;
}

export interface ShareLink {
  id: string;
  fichaId: number;
  token: string;
  permissions: SharePermissions;
  expiresAt: string;
  createdAt: string;
}

export interface SharePermissions {
  canView: boolean;
  canEdit: boolean;
  canComment: boolean;
}
```

## Tipos Utilitários

### Helpers
```typescript
// utils/helpers.ts
export type Nullable<T> = T | null;
export type Optional<T> = T | undefined;
export type Maybe<T> = T | null | undefined;

export type DeepPartial<T> = {
  [P in keyof T]?: T[P] extends object ? DeepPartial<T[P]> : T[P];
};

export type RequiredFields<T, K extends keyof T> = T & Required<Pick<T, K>>;

export type OmitFields<T, K extends keyof T> = Omit<T, K>;

export type PickFields<T, K extends keyof T> = Pick<T, K>;

export type KeysOfType<T, U> = {
  [K in keyof T]: T[K] extends U ? K : never;
}[keyof T];

export type ValuesOf<T> = T[keyof T];

export type ArrayElement<T> = T extends (infer U)[] ? U : never;

export type PromiseType<T> = T extends Promise<infer U> ? U : never;

// Utility para criar tipos de eventos
export type EventMap = Record<string, any>;

export type EventKey<T extends EventMap> = string & keyof T;

export type EventReceiver<T> = (params: T) => void;

// Utility para APIs
export interface ApiResponse<T = any> {
  data: T;
  message?: string;
  success: boolean;
  timestamp: string;
}

export interface ApiError {
  error: string;
  message: string;
  statusCode: number;
  timestamp: string;
  path?: string;
}

export interface PaginatedResponse<T> {
  data: T[];
  pagination: {
    page: number;
    pageSize: number;
    total: number;
    totalPages: number;
    hasNext: boolean;
    hasPrev: boolean;
  };
}

// Query Parameters
export interface QueryParams {
  page?: number;
  pageSize?: number;
  search?: string;
  sort?: string;
  order?: 'asc' | 'desc';
  filters?: Record<string, any>;
}
```

### Constantes
```typescript
// utils/constants.ts
export const API_ENDPOINTS = {
  AUTH: {
    LOGIN: '/api/auth/login',
    LOGOUT: '/api/auth/logout',
    REGISTER: '/api/auth/register',
    REFRESH: '/api/auth/refresh',
    PROFILE: '/api/auth/profile'
  },
  FICHAS: {
    LIST: '/api/fichas',
    CREATE: '/api/fichas',
    GET: (id: number) => `/api/fichas/${id}`,
    UPDATE: (id: number) => `/api/fichas/${id}`,
    DELETE: (id: number) => `/api/fichas/${id}`,
    SHARE: (id: number) => `/api/fichas/${id}/share`
  },
  GAME_DATA: {
    ARQUETIPOS: '/api/game/arquetipos',
    LINHAGENS: '/api/game/linhagens',
    ORIGENS: '/api/game/origens',
    FEITICOS: '/api/game/feiticos',
    EQUIPAMENTOS: '/api/game/equipamentos'
  }
} as const;

export const VALIDATION_MESSAGES = {
  REQUIRED: 'Este campo é obrigatório',
  MIN_LENGTH: (min: number) => `Mínimo ${min} caracteres`,
  MAX_LENGTH: (max: number) => `Máximo ${max} caracteres`,
  EMAIL: 'Email inválido',
  PASSWORD_WEAK: 'Senha deve ter pelo menos 8 caracteres, incluindo maiúscula, minúscula e número',
  PASSWORDS_NOT_MATCH: 'Senhas não coincidem'
} as const;

export const APP_CONFIG = {
  NAME: 'TDC Sheets',
  VERSION: '1.0.0',
  API_BASE_URL: process.env.VITE_API_URL || 'http://localhost:8080',
  DEFAULT_PAGE_SIZE: 20,
  MAX_FILE_SIZE: 5 * 1024 * 1024, // 5MB
  SUPPORTED_IMAGE_TYPES: ['image/jpeg', 'image/png', 'image/webp'],
  SESSION_TIMEOUT: 30 * 60 * 1000, // 30 minutos
  TOAST_DURATION: 5000
} as const;
```

## Exports Principais

```typescript
// index.ts
// API Types
export * from './api/auth';
export * from './api/ficha';
export * from './api/game';
export * from './api/common';

// UI Types
export * from './ui/components';
export * from './ui/forms';
export * from './ui/navigation';

// Store Types
export * from './store/auth';
export * from './store/fichas';
export * from './store/ui';

// Utils
export * from './utils/helpers';
export * from './utils/constants';

// Re-exports comuns
export type { ReactNode, ComponentProps, FC } from 'react';
export type { RouteObject } from 'react-router-dom';
```

## Convenções

### Nomenclatura
- **Interfaces**: PascalCase (ex: `UserProfile`)
- **Types**: PascalCase (ex: `TipoAtributo`)
- **Enums**: PascalCase com valores UPPER_CASE
- **Constantes**: UPPER_SNAKE_CASE

### Organização
- Um arquivo por entidade principal
- Tipos relacionados agrupados
- Imports/exports organizados
- Documentação TSDoc quando necessário

### Validação
```typescript
// Exemplo de tipo com validação runtime usando Zod
import { z } from 'zod';

export const FichaSchema = z.object({
  nomePersonagem: z.string().min(1).max(100),
  experiencia: z.number().min(0),
  nivelSorte: z.number().min(0).max(10)
});

export type FichaInput = z.infer<typeof FichaSchema>;
```
