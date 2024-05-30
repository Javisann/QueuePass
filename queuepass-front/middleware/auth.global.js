import { jwtDecode } from 'jwt-decode';

// middleware que redirige si no encuentra token
export default defineNuxtRouteMiddleware((to, from) => {
  // Verificar si se está ejecutando en el cliente
  if (process.client) {
    const token = localStorage.getItem('token');
    
    // Saltar la verificación del token en la página de inicio de sesión
    if (to.path === '/log-in' || to.path === '/log-in/register') {
      return; // No hacer nada y dejar que el usuario acceda a la página de inicio de sesión
    }
    
    // Si no hay token y la ruta actual no es '/log-in', redirigir a '/log-in'
    if (!token) {
      // Redirigir sin establecer ningún layout
      window.location.href = '/log-in';
      return;
    }

    try {
      // Decodificar el token JWT
      const decodedToken = jwtDecode(token); // Llamar a la función 'decode'
      
      // Verificar si el usuario tiene el rol ROLE_USER
      if (decodedToken.authorities.split(',').includes('ROLE_USER')) {
        // Redirigir a la página de usuario
        if (!to.path.startsWith('/user')) {
            window.location.href = '/user';
            return;
        }
      }

      // Verificar la validez del token
      if (!isValidToken(decodedToken)) {
        // El token no es válido o ha expirado
        // Redirigir a la página de inicio de sesión
        window.location.href = '/log-in';
        return;
      }

    } catch (error) {
      console.error('Error al decodificar el token:', error);
      // En caso de error, redirigir a la página de inicio de sesión
      window.location.href = '/log-in';
      return;
    }
  }
});

// Función para verificar la validez del token
function isValidToken(decodedToken) {
  // Verificar si el token ha expirado
  if (decodedToken.exp * 1000 < Date.now()) {
    return false;
  }
  return true;
}