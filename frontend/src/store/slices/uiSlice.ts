import { createSlice, PayloadAction } from '@reduxjs/toolkit'
import type { RootState } from '../index'

// TODO: Expandir interface de UI state conforme necessidade - FRONT-003
// Types
export interface UIPreferences {
  language: 'pt-BR' | 'en-US'
  dateFormat: 'dd/MM/yyyy' | 'MM/dd/yyyy' | 'yyyy-MM-dd'
  timeFormat: '24h' | '12h'
  numberFormat: 'decimal' | 'thousand'
}

interface UIState {
  // Theme
  theme: 'light' | 'dark' | 'system'
  
  // Layout
  sidebarCollapsed: boolean
  sidebarMobile: boolean
  
  // Loading states
  globalLoading: boolean
  
  // Notifications
  notifications: Array<{
    id: string
    type: 'success' | 'error' | 'warning' | 'info'
    title: string
    message: string
    timestamp: number
    read: boolean
  }>
  
  // Modals
  modals: {
    createFicha: boolean
    editFicha: boolean
    deleteFicha: boolean
    shareModal: boolean
    settingsModal: boolean
    helpModal: boolean
  }
  
  // User preferences
  preferences: UIPreferences
  
  // Error handling
  error: string | null
}

// Initial state
const initialState: UIState = {
  theme: 'system',
  sidebarCollapsed: false,
  sidebarMobile: false,
  globalLoading: false,
  notifications: [],
  modals: {
    createFicha: false,
    editFicha: false,
    deleteFicha: false,
    shareModal: false,
    settingsModal: false,
    helpModal: false
  },
  preferences: {
    language: 'pt-BR',
    dateFormat: 'dd/MM/yyyy',
    timeFormat: '24h',
    numberFormat: 'decimal'
  },
  error: null
}

// TODO: Implementar actions completas de UI - FRONT-003
// Slice
const uiSlice = createSlice({
  name: 'ui',
  initialState,
  reducers: {
    // Theme actions
    setTheme: (state, action: PayloadAction<'light' | 'dark' | 'system'>) => {
      state.theme = action.payload
    },
    
    // Sidebar actions
    toggleSidebar: (state) => {
      state.sidebarCollapsed = !state.sidebarCollapsed
    },
    setSidebarCollapsed: (state, action: PayloadAction<boolean>) => {
      state.sidebarCollapsed = action.payload
    },
    toggleMobileSidebar: (state) => {
      state.sidebarMobile = !state.sidebarMobile
    },
    setMobileSidebar: (state, action: PayloadAction<boolean>) => {
      state.sidebarMobile = action.payload
    },
    
    // Loading actions
    setGlobalLoading: (state, action: PayloadAction<boolean>) => {
      state.globalLoading = action.payload
    },
    
    // Notification actions
    addNotification: (state, action: PayloadAction<Omit<UIState['notifications'][0], 'id' | 'timestamp' | 'read'>>) => {
      const notification = {
        ...action.payload,
        id: Date.now().toString(),
        timestamp: Date.now(),
        read: false
      }
      state.notifications.unshift(notification)
    },
    markNotificationAsRead: (state, action: PayloadAction<string>) => {
      const notification = state.notifications.find(n => n.id === action.payload)
      if (notification) {
        notification.read = true
      }
    },
    removeNotification: (state, action: PayloadAction<string>) => {
      state.notifications = state.notifications.filter(n => n.id !== action.payload)
    },
    clearNotifications: (state) => {
      state.notifications = []
    },
    markAllNotificationsAsRead: (state) => {
      state.notifications.forEach(notification => {
        notification.read = true
      })
    },
    
    // Modal actions
    openModal: (state, action: PayloadAction<keyof UIState['modals']>) => {
      state.modals[action.payload] = true
    },
    closeModal: (state, action: PayloadAction<keyof UIState['modals']>) => {
      state.modals[action.payload] = false
    },
    closeAllModals: (state) => {
      Object.keys(state.modals).forEach(key => {
        state.modals[key as keyof UIState['modals']] = false
      })
    },
    
    // Preferences actions
    setPreferences: (state, action: PayloadAction<Partial<UIPreferences>>) => {
      state.preferences = { ...state.preferences, ...action.payload }
    },
    resetPreferences: (state) => {
      state.preferences = initialState.preferences
    },
    
    // Error actions
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload
    },
    clearError: (state) => {
      state.error = null
    }
  }
})

// Actions
export const {
  setTheme,
  toggleSidebar,
  setSidebarCollapsed,
  toggleMobileSidebar,
  setMobileSidebar,
  setGlobalLoading,
  addNotification,
  markNotificationAsRead,
  removeNotification,
  clearNotifications,
  markAllNotificationsAsRead,
  openModal,
  closeModal,
  closeAllModals,
  setPreferences,
  resetPreferences,
  setError,
  clearError
} = uiSlice.actions

// TODO: Implementar selectors de UI:
// - selectTheme
// - selectSidebarOpen
// - selectGlobalLoading
// - selectModals
// - selectNotifications
// - selectIsMobile

// Selectors
export const selectTheme = (state: RootState) => state.ui.theme
export const selectSidebarCollapsed = (state: RootState) => state.ui.sidebarCollapsed
export const selectMobileSidebar = (state: RootState) => state.ui.sidebarMobile
export const selectGlobalLoading = (state: RootState) => state.ui.globalLoading
export const selectNotifications = (state: RootState) => state.ui.notifications
export const selectUnreadNotifications = (state: RootState) => 
  state.ui.notifications.filter(n => !n.read)
export const selectModals = (state: RootState) => state.ui.modals
export const selectPreferences = (state: RootState) => state.ui.preferences
export const selectUIError = (state: RootState) => state.ui.error

// Complex selectors
export const selectIsModalOpen = (modalName: keyof UIState['modals']) => 
  (state: RootState) => state.ui.modals[modalName]

export const selectNotificationCount = (state: RootState) => state.ui.notifications.length
export const selectUnreadNotificationCount = (state: RootState) => 
  state.ui.notifications.filter(n => !n.read).length

// Export reducer
export default uiSlice.reducer

// TODO: Implementar middleware para UI:
// - Theme persistence
// - Responsive breakpoint detection
// - Notification auto-dismiss
// - Modal z-index management
