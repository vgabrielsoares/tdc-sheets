import { FC } from 'react'

interface LoadingProps {
  size?: 'sm' | 'md' | 'lg'
  text?: string
  fullScreen?: boolean
}

const Loading: FC<LoadingProps> = ({ 
  size = 'md', 
  text = 'Carregando...', 
  fullScreen = false 
}) => {
  const sizeClasses = {
    sm: 'w-4 h-4',
    md: 'w-8 h-8',
    lg: 'w-12 h-12'
  }

  const LoadingSpinner = () => (
    <div className={`${sizeClasses[size]} animate-spin`}>
      <div className="w-full h-full border-4 border-blue-200 border-t-blue-600 rounded-full"></div>
    </div>
  )

  const LoadingContent = () => (
    <div className="flex flex-col items-center justify-center space-y-3">
      <LoadingSpinner />
      {text && (
        <p className="text-gray-600 text-sm font-medium">{text}</p>
      )}
    </div>
  )

  if (fullScreen) {
    return (
      <div className="fixed inset-0 bg-white bg-opacity-80 flex items-center justify-center z-50">
        <LoadingContent />
      </div>
    )
  }

  return (
    <div className="flex items-center justify-center p-8">
      <LoadingContent />
    </div>
  )
}

export default Loading
