import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import WindiCSS from 'vite-plugin-windicss'
import path from 'node:path'

// https://vitejs.dev/config/
export default defineConfig({
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    }
  },
  plugins: [vue(), WindiCSS()],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url)),
      // 指定 src 文件夹别名，以便快速定位到此文件夹
      // "~": path.resolve(__dirname, "src")
    }
  }
})
