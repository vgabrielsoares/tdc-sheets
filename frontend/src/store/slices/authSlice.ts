import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import type { RootState } from '../index'

// Types
interface User {
  id: string
  name: string
  email: string
  role: 'USER' | 'ADMIN'
  createdAt: string
  updatedAt: string
}

interface AuthState {
  isAuthenticated: boolean
  user: User | null
  token: string | null
  refreshToken: string | null
  loading: boolean
  error: string | null
}

// Initial state
const initialState: AuthState = {
  isAuthenticated: false,
  user: null,
  token: null,
  refreshToken: null,
  loading: false,
  error: null
}

// Slice
const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    // Authentication actions
    loginStart: (state) => {
      state.loading = true
      state.error = null
    },
    loginSuccess: (state, action: PayloadAction<{
      user: User
      token: string
      refreshToken: string
    }>) => {
      state.loading = false
      state.isAuthenticated = true
      state.user = action.payload.user
      state.token = action.payload.token
      state.refreshToken = action.payload.refreshToken
      state.error = null
    },
    loginFailure: (state, action: PayloadAction<string>) => {
      state.loading = false
      state.isAuthenticated = false
      state.user = null
      state.token = null
      state.refreshToken = null
      state.error = action.payload
    },
    
    // Logout
    logout: (state) => {
      state.isAuthenticated = false
      state.user = null
      state.token = null
      state.refreshToken = null
      state.loading = false
      state.error = null
    },
    
    // Token refresh
    refreshTokenSuccess: (state, action: PayloadAction<{
      token: string
      refreshToken: string
    }>) => {
      state.token = action.payload.token
      state.refreshToken = action.payload.refreshToken
    },
    
    // User profile update
    updateUserProfile: (state, action: PayloadAction<Partial<User>>) => {
      if (state.user) {
        state.user = { ...state.user, ...action.payload }
      }
    },
    
    // Clear error
    clearError: (state) => {
      state.error = null
    }
  }
})

// Actions
export const {
  loginStart,
  loginSuccess,
  loginFailure,
  logout,
  refreshTokenSuccess,
  updateUserProfile,
  clearError
} = authSlice.actions

// Selectors
export const selectAuth = (state: RootState) => state.auth
export const selectIsAuthenticated = (state: RootState) => state.auth.isAuthenticated
export const selectUser = (state: RootState) => state.auth.user
export const selectToken = (state: RootState) => state.auth.token
export const selectAuthLoading = (state: RootState) => state.auth.loading
export const selectAuthError = (state: RootState) => state.auth.error

// Export reducer
export default authSlice.reducer
