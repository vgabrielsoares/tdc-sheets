import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import type { RootState } from '../index'

// Types
export interface FichaPersonagem {
  id: string
  nome: string
  arquetipos: string[]
  nivel: number
  pontos_vida: number
  pontos_poder: number
  defesa: number
  
  // Atributos
  forca: number
  agilidade: number
  constituicao: number
  mente: number
  presenca: number
  influencia: number
  
  // Status
  created_at: string
  updated_at: string
  user_id: string
}

interface FichaState {
  fichas: FichaPersonagem[]
  currentFicha: FichaPersonagem | null
  loading: boolean
  error: string | null
  
  // Filters and pagination
  filters: {
    search: string
    arquetipos: string[]
    nivel_min: number | null
    nivel_max: number | null
  }
  pagination: {
    page: number
    limit: number
    total: number
    totalPages: number
  }
  
  // UI state
  viewMode: 'grid' | 'list'
  sortBy: 'nome' | 'nivel' | 'updated_at'
  sortOrder: 'asc' | 'desc'
}

// Initial state
const initialState: FichaState = {
  fichas: [],
  currentFicha: null,
  loading: false,
  error: null,
  
  filters: {
    search: '',
    arquetipos: [],
    nivel_min: null,
    nivel_max: null
  },
  pagination: {
    page: 1,
    limit: 12,
    total: 0,
    totalPages: 0
  },
  
  viewMode: 'grid',
  sortBy: 'updated_at',
  sortOrder: 'desc'
}

// Slice
const fichaSlice = createSlice({
  name: 'ficha',
  initialState,
  reducers: {
    // Fetch fichas
    fetchFichasStart: (state) => {
      state.loading = true
      state.error = null
    },
    fetchFichasSuccess: (state, action: PayloadAction<{
      fichas: FichaPersonagem[]
      pagination: typeof initialState.pagination
    }>) => {
      state.loading = false
      state.fichas = action.payload.fichas
      state.pagination = action.payload.pagination
      state.error = null
    },
    fetchFichasFailure: (state, action: PayloadAction<string>) => {
      state.loading = false
      state.error = action.payload
    },
    
    // Current ficha
    setCurrentFicha: (state, action: PayloadAction<FichaPersonagem | null>) => {
      state.currentFicha = action.payload
    },
    
    // CRUD operations
    addFicha: (state, action: PayloadAction<FichaPersonagem>) => {
      state.fichas.unshift(action.payload)
    },
    updateFicha: (state, action: PayloadAction<FichaPersonagem>) => {
      const index = state.fichas.findIndex(f => f.id === action.payload.id)
      if (index !== -1) {
        state.fichas[index] = action.payload
      }
      if (state.currentFicha?.id === action.payload.id) {
        state.currentFicha = action.payload
      }
    },
    removeFicha: (state, action: PayloadAction<string>) => {
      state.fichas = state.fichas.filter(f => f.id !== action.payload)
      if (state.currentFicha?.id === action.payload) {
        state.currentFicha = null
      }
    },
    
    // Filters
    setFilters: (state, action: PayloadAction<Partial<typeof initialState.filters>>) => {
      state.filters = { ...state.filters, ...action.payload }
      state.pagination.page = 1 // Reset to first page when filtering
    },
    clearFilters: (state) => {
      state.filters = initialState.filters
      state.pagination.page = 1
    },
    
    // Pagination
    setPagination: (state, action: PayloadAction<Partial<typeof initialState.pagination>>) => {
      state.pagination = { ...state.pagination, ...action.payload }
    },
    
    // UI preferences
    setViewMode: (state, action: PayloadAction<'grid' | 'list'>) => {
      state.viewMode = action.payload
    },
    setSorting: (state, action: PayloadAction<{
      sortBy: typeof initialState.sortBy
      sortOrder: typeof initialState.sortOrder
    }>) => {
      state.sortBy = action.payload.sortBy
      state.sortOrder = action.payload.sortOrder
    },
    
    // Clear error
    clearError: (state) => {
      state.error = null
    }
  }
})

// Actions
export const {
  fetchFichasStart,
  fetchFichasSuccess,
  fetchFichasFailure,
  setCurrentFicha,
  addFicha,
  updateFicha,
  removeFicha,
  setFilters,
  clearFilters,
  setPagination,
  setViewMode,
  setSorting,
  clearError
} = fichaSlice.actions

// Selectors
export const selectFichas = (state: RootState) => state.ficha.fichas
export const selectCurrentFicha = (state: RootState) => state.ficha.currentFicha
export const selectFichaLoading = (state: RootState) => state.ficha.loading
export const selectFichaError = (state: RootState) => state.ficha.error
export const selectFichaFilters = (state: RootState) => state.ficha.filters
export const selectFichaPagination = (state: RootState) => state.ficha.pagination
export const selectFichaViewMode = (state: RootState) => state.ficha.viewMode
export const selectFichaSorting = (state: RootState) => ({
  sortBy: state.ficha.sortBy,
  sortOrder: state.ficha.sortOrder
})

// Complex selectors
export const selectFilteredFichas = (state: RootState) => {
  const { fichas, filters, sortBy, sortOrder } = state.ficha
  
  let filtered = fichas.filter(ficha => {
    // Search filter
    if (filters.search && !ficha.nome.toLowerCase().includes(filters.search.toLowerCase())) {
      return false
    }
    
    // Arquetipos filter
    if (filters.arquetipos.length > 0 && !filters.arquetipos.some(arq => ficha.arquetipos.includes(arq))) {
      return false
    }
    
    // Level range filter
    if (filters.nivel_min !== null && ficha.nivel < filters.nivel_min) {
      return false
    }
    if (filters.nivel_max !== null && ficha.nivel > filters.nivel_max) {
      return false
    }
    
    return true
  })
  
  // Sort
  filtered.sort((a, b) => {
    let comparison = 0
    
    switch (sortBy) {
      case 'nome':
        comparison = a.nome.localeCompare(b.nome)
        break
      case 'nivel':
        comparison = a.nivel - b.nivel
        break
      case 'updated_at':
        comparison = new Date(a.updated_at).getTime() - new Date(b.updated_at).getTime()
        break
    }
    
    return sortOrder === 'asc' ? comparison : -comparison
  })
  
  return filtered
}

// Export reducer
export default fichaSlice.reducer
