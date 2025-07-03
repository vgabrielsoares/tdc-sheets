import { FC } from 'react'
import { Navigate, Outlet } from 'react-router-dom'
import { useAppSelector } from '@hooks/redux'
import { selectIsAuthenticated } from '@store/slices/authSlice'

interface PublicRouteProps {
  redirectTo?: string
}

const PublicRoute: FC<PublicRouteProps> = ({ 
  redirectTo = '/dashboard' 
}) => {
  const isAuthenticated = useAppSelector(selectIsAuthenticated)

  // If authenticated, redirect to dashboard
  if (isAuthenticated) {
    return <Navigate to={redirectTo} replace />
  }

  return <Outlet />
}

export default PublicRoute
