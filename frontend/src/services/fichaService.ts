import { apiClient, ApiResponse } from './api'

// Types
export interface FichaPersonagem {
  id: string
  nome: string
  nivel: number
  experiencia: number
  origem: {
    id: string
    nome: string
  }
  linhagem: {
    id: string
    nome: string
  }
  arquetipos: Array<{
    id: string
    nome: string
    nivel: number
  }>
  classes: Array<{
    id: string
    nome: string
    nivel: number
  }>
  atributos: {
    forca: number
    agilidade: number
    constituicao: number
    mente: number
    presenca: number
    influencia: number
  }
  // Campos calculados
  pv: number
  pp: number
  defesa: number
  capacidadeCarga: number
  deslocamento: number
  // Metadados
  userId: string
  versao: number
  publica: boolean
  compartilhada: boolean
  createdAt: string
  updatedAt: string
}

export interface CreateFichaRequest {
  nome: string
  origemId: string
  linhagemId: string
  arquetipoInicialId: string
  atributosDistribuidos: {
    forca: number
    agilidade: number
    constituicao: number
    mente: number
    presenca: number
    influencia: number
  }
}

export interface UpdateFichaRequest {
  nome?: string
  nivel?: number
  experiencia?: number
  publica?: boolean
  // Partial updates for nested objects
  atributos?: Partial<FichaPersonagem['atributos']>
}

export interface FichaListItem {
  id: string
  nome: string
  nivel: number
  origem: string
  linhagem: string
  arquetipos: string[]
  updatedAt: string
  publica: boolean
}

export interface FichaSearchParams {
  search?: string
  nivel?: number
  origem?: string
  linhagem?: string
  arquetipo?: string
  orderBy?: 'nome' | 'nivel' | 'updatedAt'
  order?: 'asc' | 'desc'
  page?: number
  limit?: number
}

export interface FichaSearchResponse {
  fichas: FichaListItem[]
  total: number
  page: number
  limit: number
  totalPages: number
}

export interface CompartilhamentoFicha {
  id: string
  fichaId: string
  nomeUsuario: string
  permissao: 'leitura' | 'escrita'
  linkToken?: string
  expiresAt?: string
  createdAt: string
}

export interface CreateCompartilhamentoRequest {
  fichaId: string
  email?: string
  permissao: 'leitura' | 'escrita'
  expiresAt?: string
  criarLink?: boolean
}

class FichaService {
  private readonly baseUrl = '/fichas'

  /**
   * Get list of user's fichas with optional search and filters
   */
  async getFichas(params: FichaSearchParams = {}): Promise<FichaSearchResponse> {
    const queryParams = new URLSearchParams()
    
    Object.entries(params).forEach(([key, value]) => {
      if (value !== undefined && value !== null) {
        queryParams.append(key, value.toString())
      }
    })

    const response = await apiClient.get<FichaSearchResponse>(
      `${this.baseUrl}?${queryParams.toString()}`
    )
    return response.data
  }

  /**
   * Get specific ficha by ID
   */
  async getFicha(id: string): Promise<FichaPersonagem> {
    const response = await apiClient.get<FichaPersonagem>(`${this.baseUrl}/${id}`)
    return response.data
  }

  /**
   * Create new ficha
   */
  async createFicha(fichaData: CreateFichaRequest): Promise<FichaPersonagem> {
    const response = await apiClient.post<FichaPersonagem>(this.baseUrl, fichaData)
    return response.data
  }

  /**
   * Update existing ficha
   */
  async updateFicha(id: string, updates: UpdateFichaRequest): Promise<FichaPersonagem> {
    const response = await apiClient.put<FichaPersonagem>(`${this.baseUrl}/${id}`, updates)
    return response.data
  }

  /**
   * Delete ficha
   */
  async deleteFicha(id: string): Promise<ApiResponse<null>> {
    return apiClient.delete(`${this.baseUrl}/${id}`)
  }

  /**
   * Duplicate ficha
   */
  async duplicateFicha(id: string, newName?: string): Promise<FichaPersonagem> {
    const response = await apiClient.post<FichaPersonagem>(
      `${this.baseUrl}/${id}/duplicate`,
      { nome: newName }
    )
    return response.data
  }

  /**
   * Get ficha by share token (public access)
   */
  async getFichaByToken(token: string): Promise<FichaPersonagem> {
    const response = await apiClient.get<FichaPersonagem>(`/public/fichas/${token}`)
    return response.data
  }

  /**
   * Search public fichas
   */
  async searchPublicFichas(params: Omit<FichaSearchParams, 'page'> & { page?: number }): Promise<FichaSearchResponse> {
    const queryParams = new URLSearchParams()
    
    Object.entries(params).forEach(([key, value]) => {
      if (value !== undefined && value !== null) {
        queryParams.append(key, value.toString())
      }
    })

    const response = await apiClient.get<FichaSearchResponse>(
      `/public/fichas?${queryParams.toString()}`
    )
    return response.data
  }

  // Compartilhamento methods
  /**
   * Get sharing settings for a ficha
   */
  async getCompartilhamentos(fichaId: string): Promise<CompartilhamentoFicha[]> {
    const response = await apiClient.get<CompartilhamentoFicha[]>(
      `${this.baseUrl}/${fichaId}/compartilhamentos`
    )
    return response.data
  }

  /**
   * Create new sharing
   */
  async createCompartilhamento(data: CreateCompartilhamentoRequest): Promise<CompartilhamentoFicha> {
    const response = await apiClient.post<CompartilhamentoFicha>(
      `${this.baseUrl}/${data.fichaId}/compartilhamentos`,
      data
    )
    return response.data
  }

  /**
   * Update sharing permissions
   */
  async updateCompartilhamento(
    fichaId: string, 
    compartilhamentoId: string, 
    updates: { permissao?: 'leitura' | 'escrita', expiresAt?: string }
  ): Promise<CompartilhamentoFicha> {
    const response = await apiClient.put<CompartilhamentoFicha>(
      `${this.baseUrl}/${fichaId}/compartilhamentos/${compartilhamentoId}`,
      updates
    )
    return response.data
  }

  /**
   * Remove sharing
   */
  async removeCompartilhamento(fichaId: string, compartilhamentoId: string): Promise<ApiResponse<null>> {
    return apiClient.delete(`${this.baseUrl}/${fichaId}/compartilhamentos/${compartilhamentoId}`)
  }

  // Export/Import methods
  /**
   * Export ficha as JSON
   */
  async exportFicha(id: string, format: 'json' | 'pdf' = 'json'): Promise<Blob> {
    const response = await apiClient.getClient().get(`${this.baseUrl}/${id}/export`, {
      params: { format },
      responseType: 'blob'
    })
    return response.data
  }

  /**
   * Import ficha from JSON
   */
  async importFicha(file: File): Promise<FichaPersonagem> {
    const response = await apiClient.upload<FichaPersonagem>(`${this.baseUrl}/import`, file)
    return response.data
  }

  // Statistics methods
  /**
   * Get user's ficha statistics
   */
  async getFichaStats(): Promise<{
    totalFichas: number
    nivelMedio: number
    arquetiposMaisUsados: Array<{ nome: string, count: number }>
    origensDistribuicao: Array<{ nome: string, count: number }>
  }> {
    const response = await apiClient.get<{
      totalFichas: number
      nivelMedio: number
      arquetiposMaisUsados: Array<{ nome: string, count: number }>
      origensDistribuicao: Array<{ nome: string, count: number }>
    }>(`${this.baseUrl}/stats`)
    return response.data
  }
}

// Export singleton instance
export const fichaService = new FichaService()
export default fichaService
