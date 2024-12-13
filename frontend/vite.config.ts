import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from 'node:url';

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: [
      { find: '@/lib', replacement: fileURLToPath(new URL('./src/lib/', import.meta.url)) },
      { find: '@/stores', replacement: fileURLToPath(new URL('./src/stores/', import.meta.url)) },
    ]
  }
})
