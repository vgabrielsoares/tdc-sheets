import { FC } from 'react'
import { Link, useLocation } from 'react-router-dom'
import { 
  Home, 
  FileText, 
  Plus, 
  Users, 
  Settings, 
  HelpCircle,
  Sword,
  Book,
  Shield
} from 'lucide-react'
import { useAppSelector } from '@hooks/redux'
import { selectSidebarCollapsed } from '@store/slices/uiSlice'
import clsx from 'clsx'

interface NavItem {
  label: string
  path: string
  icon: React.ComponentType<{ className?: string }>
  children?: NavItem[]
}

const navigationItems: NavItem[] = [
  {
    label: 'Dashboard',
    path: '/dashboard',
    icon: Home
  },
  {
    label: 'Fichas',
    path: '/fichas',
    icon: FileText,
    children: [
      {
        label: 'Minhas Fichas',
        path: '/fichas',
        icon: FileText
      },
      {
        label: 'Nova Ficha',
        path: '/fichas/new',
        icon: Plus
      }
    ]
  },
  {
    label: 'Referências',
    path: '/referencias',
    icon: Book,
    children: [
      {
        label: 'Arquetipos',
        path: '/referencias/arquetipos',
        icon: Shield
      },
      {
        label: 'Habilidades',
        path: '/referencias/habilidades',
        icon: Sword
      },
      {
        label: 'Feitiços',
        path: '/referencias/feiticos',
        icon: Book
      }
    ]
  },
  {
    label: 'Grupos',
    path: '/grupos',
    icon: Users
  },
  {
    label: 'Configurações',
    path: '/settings',
    icon: Settings
  },
  {
    label: 'Ajuda',
    path: '/help',
    icon: HelpCircle
  }
]

const Sidebar: FC = () => {
  const location = useLocation()
  const collapsed = useAppSelector(selectSidebarCollapsed)

  const isActiveRoute = (path: string): boolean => {
    if (path === '/dashboard') {
      return location.pathname === path
    }
    return location.pathname.startsWith(path)
  }

  const NavItem: FC<{ item: NavItem; level?: number }> = ({ item, level = 0 }) => {
    const active = isActiveRoute(item.path)
    const hasChildren = item.children && item.children.length > 0
    const IconComponent = item.icon

    return (
      <div className="mb-1">
        <Link
          to={item.path}
          className={clsx(
            'flex items-center px-3 py-2 text-sm font-medium rounded-md transition-colors duration-200',
            {
              'bg-blue-100 text-blue-700 dark:bg-blue-900 dark:text-blue-200': active,
              'text-gray-700 hover:bg-gray-100 dark:text-gray-300 dark:hover:bg-gray-700': !active,
              'ml-4': level > 0 && !collapsed,
              'justify-center': collapsed
            }
          )}
          title={collapsed ? item.label : undefined}
        >
          <IconComponent className={clsx('flex-shrink-0', {
            'w-5 h-5': true,
            'mr-3': !collapsed,
          })} />
          {!collapsed && (
            <span className="truncate">{item.label}</span>
          )}
        </Link>

        {/* Children items - only show if not collapsed */}
        {hasChildren && !collapsed && (
          <div className="ml-2 mt-1">
            {item.children!.map((child) => (
              <NavItem key={child.path} item={child} level={level + 1} />
            ))}
          </div>
        )}
      </div>
    )
  }

  return (
    <aside
      className={clsx(
        'fixed left-0 top-16 h-[calc(100vh-4rem)] bg-white dark:bg-gray-800 shadow-sm border-r border-gray-200 dark:border-gray-700 transition-all duration-300 ease-in-out z-40',
        {
          'w-64': !collapsed,
          'w-16': collapsed
        }
      )}
    >
      <nav className="p-4 space-y-2">
        {navigationItems.map((item) => (
          <NavItem key={item.path} item={item} />
        ))}
      </nav>

      {/* Collapse indicator */}
      <div className={clsx(
        'absolute bottom-4 left-1/2 transform -translate-x-1/2',
        {
          'hidden': !collapsed
        }
      )}>
        <div className="w-8 h-1 bg-gray-300 dark:bg-gray-600 rounded-full"></div>
      </div>
    </aside>
  )
}

export default Sidebar
