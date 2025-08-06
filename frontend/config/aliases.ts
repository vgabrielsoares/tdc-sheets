import { resolve } from 'path'

/**
 * Shared path aliases configuration for Vite and Vitest
 * This centralizes the alias definitions to avoid duplication between configs
 */
export const createAliases = (baseDir: string) => ({
  '@': resolve(baseDir, './src'),
  '@components': resolve(baseDir, './src/components'),
  '@pages': resolve(baseDir, './src/pages'),
  '@services': resolve(baseDir, './src/services'),
  '@store': resolve(baseDir, './src/store'),
  '@hooks': resolve(baseDir, './src/hooks'),
  '@utils': resolve(baseDir, './src/utils'),
  '@types': resolve(baseDir, './src/types'),
  '@styles': resolve(baseDir, './src/styles'),
  '@assets': resolve(baseDir, './src/assets')
})
