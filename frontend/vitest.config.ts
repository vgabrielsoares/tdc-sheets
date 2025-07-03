/// <reference types="vitest" />
import { defineConfig } from 'vitest/config'
import react from '@vitejs/plugin-react'
import { createAliases } from './config/aliases'

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: createAliases(__dirname)
  },
  test: {
    environment: 'jsdom',
    setupFiles: ['./src/test/setup.ts'],
    globals: true,
    css: true
  }
})
