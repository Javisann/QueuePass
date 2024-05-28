<template>
  <section class="bg-gray-50 dark:bg-gray-900 absolute h-full w-full">
    <div class="flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0 h-full w-full">
      <a href="#" class="flex items-center mb-6 text-2xl font-semibold text-gray-900 dark:text-white">
        <img class="w-40 mr-2" src="../../../assets/images/queue-pass-logo-fondo.png" alt="logo" />
      </a>
      <div
        class="w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700">
        <div class="p-6 space-y-4 md:space-y-6 sm:p-8">
          <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
            Regístrate
          </h1>
          <form class="max-w-sm mx-auto">
            <div class="mb-5">
              <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</label>
              <input v-model="username" type="email" id="email"
                class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
                placeholder="example@gmail.com" required />
            </div>
            <div class="mb-5">
              <label for="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Nombre Completo</label>
              <input v-model="name" type="text" id="name"
                class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
                placeholder="Alvaro Perez" required />
            </div>
            <div class="mb-5">
              <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Tu
                contraseña</label>
              <input v-model="password"type="password" id="password"
                class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
                required />
            </div>
            <div class="mb-5">
              <label for="repeat-password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Repite
                la
                contraseña</label>
              <input v-model="secondPassword" type="password" id="repeat-password"
                class="shadow-sm bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500 dark:shadow-sm-light"
                required />
            </div>
            <div class="flex items-start mb-5">
              <div class="flex items-center h-5">
                <input id="terms" type="checkbox" value=""
                  class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800"
                  required />
              </div>
              <label for="terms" class="ms-2 text-sm font-medium text-gray-900 dark:text-gray-300">Estoy de acuerdo con
                los <a href="#" class="text-blue-400 hover:underline dark:text-blue-500">terminos y
                  condiciones</a></label>
            </div>
            <button @click.prevent="postData()" type="submit"
              class="w-full text-white bg-blue-400 hover:bg-blue-500 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Registrar
              cuenta nueva</button>

            <p class="mt-4 text-sm font-light text-gray-500 dark:text-gray-400">
              ¿Ya tienes cuenta?
              <a href="/log-in" class="font-medium text-primary-600 hover:underline dark:text-primary-500">Inicia
                sesión</a>
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
          <span class="font-medium">Error!</span> Las contraseñas no coinciden.
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
    const name = ref("");
    const password = ref("");
    const secondPassword = ref("");
    const alert = ref(false);

    //Llamada a la API para registrar un usuario
    const postData = async () => {
      try {
        if (password.value !== secondPassword.value) { //Si la contraseña1 no coincide con la contraseña2 sale una alerta
          alert.value = true;
          return;
        }

        const response = await axios.post(`${BACK_URL}/auth/sign-up`, {
          username: username.value,
          name: name.value,
          password: password.value,
        });
        
        //Guardamos en el localstorage el token
        localStorage.setItem("token", response.data.token);
        // Guardar el nombre en el localStorage
        localStorage.setItem("name", response.data.name);

        //Redirigimos a la pagina usuario
        window.location.href = '/user';
      } catch (error) {
        alert.value = true;
        console.error("Error in register data:", error);
      }
    };
    return {
      username,
      name,
      password,
      postData,
      alert,
      secondPassword
    };
  },
};
</script>