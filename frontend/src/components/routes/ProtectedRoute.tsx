import { FC } from 'react'
import { Navigate, Outlet, useLocation } from 'react-router-dom'
import { useAppSelector } from '@hooks/redux'
import { selectIsAuthenticated, selectUser } from '@store/slices/authSlice'

interface ProtectedRouteProps {
  requiredRole?: 'USER' | 'ADMIN'
  redirectTo?: string
}

const ProtectedRoute: FC<ProtectedRouteProps> = ({ 
  requiredRole,
  redirectTo = '/login'
}) => {
  const isAuthenticated = useAppSelector(selectIsAuthenticated)
  const user = useAppSelector(selectUser)
  const location = useLocation()

  // Not authenticated
  if (!isAuthenticated) {
    return (
      <Navigate 
        to={redirectTo} 
        state={{ from: location }} 
        replace 
      />
    )
  }

  // Check role requirement
  if (requiredRole && user?.role !== requiredRole) {
    return (
      <Navigate 
        to="/dashboard" 
        state={{ error: 'Acesso negado: privilégios insuficientes' }}
        replace 
      />
    )
  }

  return <Outlet />
}

export default ProtectedRoute
