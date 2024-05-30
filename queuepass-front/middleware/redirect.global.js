// Redirigir a todas las peticiones /
export default defineNuxtRouteMiddleware((to, from) => {
    if (to.path === '/') {
      return navigateTo('/log-in')
    }
  })