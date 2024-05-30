<template>
  <div id="list" class="p-10 flex flex-col items-center mb-5 bg-gradient-to-bl from-white to-blue-200 min-h-screen">
    <div class="w-full mb-5">
      <div class="flex justify-center border-b border-black pb-4 w-full">
        <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl">
          Carta del restaurante
        </h1>
      </div>
      <form class="mt-4 max-w-sm mx-auto">
        <select id="tipos"
          class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="selectedType" @change="fetchData">
          <option value="hamburguesa" selected>Hamburguesas</option>
          <option value="entrante">Entrantes</option>
          <option value="ensalada">Ensaladas</option>
          <option value="sandwich">Sandwiches</option>
          <option value="postre">Postres</option>
          <option value="bebida">Bebidas</option>
        </select>
      </form>
    </div>
    <div v-if="loading" class="flex items-center justify-center dark:bg-gray-800 dark:border-gray-700">
      <div role="status">
        <svg aria-hidden="true" class="w-8 h-8 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600"
          viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path
            d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
            fill="currentColor" />
          <path
            d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
            fill="currentFill" />
        </svg>
        <span class="sr-only">Loading...</span>
      </div>
    </div>
    <!--Por cada objeto dentro del response hace un card de estos-->
    <div v-for="item in data" :id="item.id">
      <div
        class="flex flex-col my-5 max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <a href="#">
          <img class="rounded-t-lg" :src="item.image" />
        </a>
        <div class="p-5">
          <a href="#">
            <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">
              {{ item.name }}
            </h5>
          </a>
          <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">
            {{ item.description }}
          </p>
          <div class="flex justify-end">
            <p>
              <b>{{ item.price }}€</b>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import BACK_URL from "../../../config/variables.js";

definePageMeta({
  layout: "user",
});
export default {
  setup() {
    const selectedType = ref("hamburguesa");
    const data = ref(null);
    const loading = ref(false);
    const selectedItem = ref();

    //
    let token = null;
    if (typeof window !== "undefined") {
      // Para que cuando recargue la pagina se mantenga el token
      token = localStorage.getItem("token"); // Obtiene el token del local storage
    }

    // Llamada a la API para mostrar los platos por tipo
    const fetchData = async () => {
      if (selectedType.value) {
        loading.value = true;
        try {
          const response = await axios.get(
            `${BACK_URL}/api/plate/type/${selectedType.value}`,
            {
              headers: {
                "ngrok-skip-browser-warning": "69420",
                Authorization: `Bearer ${token}`,
              },
            }
          );
          // Para que las url de las imagenes guardadas en la base de datos sirvan si usamos ngrok o localhost
          data.value = await Promise.all(
            response.data.map(async (item) => {
              if (item.image != null) {
                const urlParts = item.image.split("/").slice(3).join("/"); // Pasa las 3 primeras "/" y coge lo que esta despues
                const imageUrl = `${BACK_URL}/${urlParts}`; // Une esa parte con la URL que se coja del Back-End

                // Petición para obtener el contenido de la imagen
                try {
                  const imageResponse = await axios.get(imageUrl, {
                    headers: { "ngrok-skip-browser-warning": "69420" },
                    responseType: "arraybuffer", // Tipo de respuesta arraybuffer
                  });

                  // Convierte el array buffer a un blob
                  const blob = new Blob([imageResponse.data], {
                    type: "image/jpeg",
                  });

                  // Utiliza FileReader para leer el blob y obtener la URL de datos
                  const reader = new FileReader();
                  reader.readAsDataURL(blob);

                  return new Promise((resolve) => {
                    reader.onloadend = () => {
                      const imageDataUrl = reader.result;
                      item.image = imageDataUrl;
                      resolve(item);
                    };
                  });
                } catch (e) {
                  console.error("Error fetching image:", e);
                  item.image = null;
                  return new Promise((resolve) => {
                    resolve(item);
                  });
                }
              }
              return new Promise((resolve) => {
                resolve(item);
              });
            })
          );
        } catch (error) {
          data.value = null;
          console.error("Error fetching data:", error);
        } finally {
          loading.value = false;
        }
      }
    };

    onMounted(fetchData);

    return {
      selectedType,
      data,
      loading,
      fetchData,
      selectedItem,
    };
  },
};
</script>

<!--Efecto para la lista-->
<style scoped>
@keyframes show {
  from {
    opacity: 0;
    scale: 15%;
  }

  to {
    opacity: 1;
    scale: 100%;
  }
}

#list>div {
  view-timeline-name: --component;
  view-timeline-inset: block;

  animation-timeline: --component;
  animation-name: show;

  animation-range: entry 0% cover 25%;
  animation-fill-mode: both;
}
</style>