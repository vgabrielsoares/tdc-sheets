import { FC } from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'

import { useAppSelector } from '@hooks/redux'
import { selectIsAuthenticated } from '@store/slices/authSlice'
import { ThemeProvider } from '@components/theme/ThemeProvider'

// Layout Components
import MainLayout from '@components/layout/MainLayout'
import AuthLayout from '@components/layout/AuthLayout'

// Public Pages
import LoginPage from '@pages/auth/LoginPage'
import RegisterPage from '@pages/auth/RegisterPage'
import LandingPage from '@pages/public/LandingPage'

// Protected Pages
import DashboardPage from '@pages/dashboard/DashboardPage'
import FichasListPage from '@pages/fichas/FichasListPage'
import CreateFichaPage from '@pages/fichas/CreateFichaPage'
import EditFichaPage from '@pages/fichas/EditFichaPage'
import ViewFichaPage from '@pages/fichas/ViewFichaPage'

// Route Guards
import ProtectedRoute from '@components/routes/ProtectedRoute'
import PublicRoute from '@components/routes/PublicRoute'

// Error Pages
import NotFoundPage from '@pages/error/NotFoundPage'
import ErrorBoundary from '@components/error/ErrorBoundary'

const App: FC = () => {
  const isAuthenticated = useAppSelector(selectIsAuthenticated)

  return (
    <ThemeProvider>
      <ErrorBoundary>
        <Routes>
        {/* Public Routes */}
        <Route path="/" element={<LandingPage />} />
        
        {/* Auth Routes */}
        <Route element={<PublicRoute />}>
          <Route element={<AuthLayout />}>
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
          </Route>
        </Route>

        {/* Protected Routes */}
        <Route element={<ProtectedRoute />}>
          <Route element={<MainLayout />}>
            <Route path="/dashboard" element={<DashboardPage />} />
            <Route path="/fichas" element={<FichasListPage />} />
            <Route path="/fichas/new" element={<CreateFichaPage />} />
            <Route path="/fichas/:id" element={<ViewFichaPage />} />
            <Route path="/fichas/:id/edit" element={<EditFichaPage />} />
          </Route>
        </Route>

        {/* Redirects */}
        <Route 
          path="/app" 
          element={
            <Navigate 
              to={isAuthenticated ? "/dashboard" : "/login"} 
              replace 
            />
          } 
        />

        {/* 404 */}
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </ErrorBoundary>
    </ThemeProvider>
  )
}

export default App
