<template>
  <section class="bg-gray-50 dark:bg-gray-900 absolute h-full w-full">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0 h-full w-full">
      <a href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
        <img class="w-40 mr-2" src="../../assets/images/queue-pass-logo-fondo.png" alt="logo" />
      </a>
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
            Inicia sesión con tu cuenta
          </h1>
          <form class="space-y-4 md:space-y-6" action="#">
            <div>
              <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
              <input v-model="username" type="email" name="email" id="email"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                placeholder="ejemplo@gmail.com" required="true" />
            </div>
            <div>
              <label for="password"
                class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Contraseña</label>
              <input v-model="password" type="password" name="password" id="password" placeholder="••••••••"
                class="bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                required="true" />
            </div>
            <div class="flex items-center justify-between">
              <div class="flex items-start">
                <div class="flex items-center h-5">
                  <input id="remember" aria-describedby="remember" type="checkbox"
                    class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-primary-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-primary-600 dark:ring-offset-gray-800"
                    required="false" />
                </div>
                <div class="ml-3 text-sm">
                  <label for="remember" class="text-gray-500 dark:text-gray-300">Recuérdame</label>
                </div>
              </div>
              <a href="#" class="text-xs font-medium text-primary-600 hover:underline dark:text-primary-500">¿Olvidaste
                la contraseña?</a>
            </div>
            <button @click.prevent="postData()"
              class="w-full bg-blue-400 hover:bg-blue-500 text-white bg-primary-600 hover:bg-primary-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-primary-600 dark:hover:bg-primary-700 dark:focus:ring-primary-800">
              Inicia sesión
            </button>
            <p class="text-sm font-light text-gray-500 dark:text-gray-400">
              ¿No tienes cuenta?
              <a href="/log-in/register"
                class="font-medium text-primary-600 hover:underline dark:text-primary-500">Regístrate</a>
            </p>
          </form>
        </div>
      </div>

      <!--Alerta-->
      <div v-if="alert == true"
        class="mt-4 flex items-center p-4 mb-4 text-sm text-red-800 border border-red-300 rounded-lg bg-red-50 dark:bg-gray-800 dark:text-red-400 dark:border-red-800"
        role="alert">
        <svg class="flex-shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
          fill="currentColor" viewBox="0 0 20 20">
          <path
            d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z" />
        </svg>
        <span class="sr-only">Info</span>
        <div>
          <span class="font-medium">Error!</span> Usuario o contraseña son
          incorrectos.
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import axios from "axios";
import BACK_URL from "../../config/variables.js"

export default {
  setup() {
    const username = ref("");
    const password = ref("");
    const alert = ref(false);

    //Llamada a la API para crear un plato
    const postData = async () => {
      try {
        console.log(BACK_URL);
        const response = await axios.post(`${BACK_URL}/auth/log-in`, {
          username: username.value,
          password: password.value,
        });
        console.log(response.data);

        // Sacar los nombres de los roles del array de objetos
        const userRoles = response.data.authorities.map(role => role.authority);

        // Filtrar los roles que comienzan por "ROLE_"
        const filteredRoles = userRoles.filter(role => role.startsWith('ROLE_'));

        // Redirigir a la página de admin
        if (filteredRoles.includes('ROLE_ADMIN')) {
          window.location.href = '/admin';
        } else if (filteredRoles.includes('ROLE_USER')) {
          // Redirigir a la página de usuario
          window.location.href = '/user';
        }
      } catch (error) {
        alert.value = true;
        console.error("Error in login data:", error);
      }
    };
    return {
      username,
      password,
      postData,
      alert,
    };
  },
};
</script>