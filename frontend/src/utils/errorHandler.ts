import { ApiError, ServiceError, ValidationError } from '@services/types'

/**
 * Standardized error handling utility
 */
export class ErrorHandler {
  /**
   * Transform API error to user-friendly message
   */
  static getErrorMessage(error: ApiError): string {
    // Map common error codes to user-friendly messages
    const errorMessages: Record<string, string> = {
      'NETWORK_ERROR': 'Erro de conexão. Verifique sua internet e tente novamente.',
      'UNAUTHORIZED': 'Você não tem permissão para realizar esta ação.',
      'FORBIDDEN': 'Acesso negado. Faça login novamente.',
      'NOT_FOUND': 'Recurso não encontrado.',
      'VALIDATION_ERROR': 'Dados inválidos. Verifique os campos e tente novamente.',
      'CONFLICT': 'Esta ação não pode ser realizada no momento.',
      'RATE_LIMIT': 'Muitas tentativas. Aguarde alguns minutos e tente novamente.',
      'SERVER_ERROR': 'Erro interno do servidor. Tente novamente em alguns minutos.',
    }

    // Return specific message or fallback to generic
    return errorMessages[error.code || ''] || error.message || 'Erro inesperado'
  }

  /**
   * Extract validation errors from API response
   */
  static getValidationErrors(error: ApiError): ValidationError[] {
    if (error.details?.validationErrors) {
      return error.details.validationErrors
    }

    // Try to extract from different response structures
    if (error.details?.errors) {
      return Object.entries(error.details.errors).map(([field, message]) => ({
        field,
        message: Array.isArray(message) ? message[0] : message as string
      }))
    }

    return []
  }

  /**
   * Check if error is a network/connection error
   */
  static isNetworkError(error: ApiError): boolean {
    return error.code === 'NETWORK_ERROR' || 
           error.message.includes('Network Error') ||
           error.message.includes('conexão')
  }

  /**
   * Check if error requires user to login again
   */
  static isAuthenticationError(error: ApiError): boolean {
    return error.code === 'UNAUTHORIZED' || 
           error.code === 'FORBIDDEN' ||
           error.message.includes('token')
  }

  /**
   * Format error for logging purposes
   */
  static formatForLogging(error: ApiError, context?: string): string {
    const timestamp = new Date().toISOString()
    const contextInfo = context ? ` [${context}]` : ''
    
    return `${timestamp}${contextInfo} Error: ${error.message} (Code: ${error.code || 'UNKNOWN'})`
  }

  /**
   * Create standardized service error
   */
  static createServiceError(
    message: string, 
    code?: string, 
    validationErrors?: ValidationError[]
  ): ServiceError {
    return {
      message,
      code,
      validationErrors
    }
  }

  /**
   * Handle common HTTP status codes
   */
  static handleHttpStatus(status: number, defaultMessage?: string): string {
    const statusMessages: Record<number, string> = {
      400: 'Requisição inválida. Verifique os dados enviados.',
      401: 'Não autorizado. Faça login novamente.',
      403: 'Acesso negado. Você não tem permissão para esta ação.',
      404: 'Recurso não encontrado.',
      409: 'Conflito. Esta ação não pode ser realizada.',
      422: 'Dados inválidos. Verifique os campos obrigatórios.',
      429: 'Muitas tentativas. Aguarde alguns minutos.',
      500: 'Erro interno do servidor. Tente novamente mais tarde.',
      502: 'Serviço temporariamente indisponível.',
      503: 'Serviço em manutenção. Tente novamente mais tarde.',
    }

    return statusMessages[status] || defaultMessage || 'Erro inesperado'
  }

  /**
   * Show user-friendly error notification
   * This should integrate with your notification system
   */
  static showUserError(error: ApiError, context?: string): void {
    const message = this.getErrorMessage(error)
    
    // Log for debugging
    console.error(this.formatForLogging(error, context))
    
    // TODO: Replace with your notification system
    // Examples: toast, modal, banner, etc.
    console.warn('User Error:', message)
    
    // For development - you can remove this
    if (process.env.NODE_ENV === 'development') {
      console.error('Full error details:', error)
    }
  }

  /**
   * Handle async operation errors consistently
   */
  static async handleAsyncError<T>(
    operation: () => Promise<T>,
    context?: string,
    fallbackValue?: T
  ): Promise<T | undefined> {
    try {
      return await operation()
    } catch (error) {
      const apiError = error as ApiError
      this.showUserError(apiError, context)
      
      if (fallbackValue !== undefined) {
        return fallbackValue
      }
      
      return undefined
    }
  }

  /**
   * Retry failed operations with exponential backoff
   */
  static async retryOperation<T>(
    operation: () => Promise<T>,
    maxRetries: number = 3,
    baseDelay: number = 1000
  ): Promise<T> {
    let lastError: ApiError

    for (let attempt = 1; attempt <= maxRetries; attempt++) {
      try {
        return await operation()
      } catch (error) {
        lastError = error as ApiError
        
        // Don't retry client errors (4xx)
        if (lastError.code && lastError.code.startsWith('4')) {
          throw lastError
        }

        // If this was the last attempt, throw the error
        if (attempt === maxRetries) {
          throw lastError
        }

        // Wait before retrying (exponential backoff)
        const delay = baseDelay * Math.pow(2, attempt - 1)
        await new Promise(resolve => setTimeout(resolve, delay))
      }
    }

    throw lastError!
  }
}

// Convenience exports
export const { 
  getErrorMessage, 
  getValidationErrors, 
  isNetworkError, 
  isAuthenticationError,
  showUserError,
  handleAsyncError,
  retryOperation
} = ErrorHandler

export default ErrorHandler
