import { FC } from 'react'
import { Outlet } from 'react-router-dom'
import { useAppSelector } from '@hooks/redux'
import { selectSidebarCollapsed } from '@store/slices/uiSlice'
import Header from './Header'
import Sidebar from './Sidebar'

const MainLayout: FC = () => {
  const sidebarCollapsed = useAppSelector(selectSidebarCollapsed)

  return (
    <div className="min-h-screen bg-gray-50 dark:bg-gray-900">
      <Header />
      
      <div className="flex">
        <Sidebar />
        
        <main 
          className={`flex-1 transition-all duration-300 ease-in-out ${
            sidebarCollapsed ? 'ml-16' : 'ml-64'
          }`}
        >
          <div className="p-6">
            <Outlet />
          </div>
        </main>
      </div>
    </div>
  )
}

export default MainLayout
