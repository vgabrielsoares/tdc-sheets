// Re-export all service types for easy importing
export type { 
  ApiResponse, 
  ApiError 
} from './api'

export type {
  LoginRequest,
  RegisterRequest,
  LoginResponse,
  RefreshTokenRequest,
  RefreshTokenResponse,
  ForgotPasswordRequest,
  ResetPasswordRequest,
  ChangePasswordRequest,
  UpdateProfileRequest
} from './authService'

export type {
  FichaPersonagem,
  CreateFichaRequest,
  UpdateFichaRequest,
  FichaListItem,
  FichaSearchParams,
  FichaSearchResponse,
  CompartilhamentoFicha,
  CreateCompartilhamentoRequest
} from './fichaService'

// Common pagination types
export interface PaginationParams {
  page?: number
  limit?: number
}

export interface PaginatedResponse<T> {
  data: T[]
  total: number
  page: number
  limit: number
  totalPages: number
}

// Common search/filter types
export interface SearchParams {
  search?: string
  orderBy?: string
  order?: 'asc' | 'desc'
}

// Error handling types
export interface ValidationError {
  field: string
  message: string
  code?: string
}

export interface ServiceError {
  message: string
  code?: string
  validationErrors?: ValidationError[]
  details?: any
}
