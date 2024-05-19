<template>
  <div class="p-10 flex flex-col items-center mb-5">
      <form class=" mt-4 max-w-sm mx-auto">
        <label for="tipos" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Selecciona un
          tipo</label>
        <select id="tipos"
          class="bg-gray-50 border w-56 border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          v-model="selectedType" @change="fetchData">
          <option value="" disabled selected>Selecciona un tipo</option>
          <option value="hamburguesa">Hamburguesas</option>
          <option value="entrante">Entrantes</option>
          <option value="ensalada">Ensaladas</option>
          <option value="sandwich">Sandwiches</option>
          <option value="postre">Postres</option>
          <option value="bebida">Bebidas</option>
        </select>
      </form>

    <div v-if="loading">Cargando...</div>

    <div v-for=" item in data" :id="item.id">
      <div
        class="flex flex-col my-5 max-w-sm bg-white border border-gray-200 rounded-lg shadow dark:bg-gray-800 dark:border-gray-700">
        <a href="#">
          <img class="rounded-t-lg" :src="item.image" />
        </a>
        <div class="p-5">
          <a href="#">
            <h5 class="mb-2 text-2xl font-bold tracking-tight text-gray-900 dark:text-white">{{ item.name }}</h5>
          </a>
          <p class="mb-3 font-normal text-gray-700 dark:text-gray-400">{{ item.description }}</p>
          <div class="flex justify-end">
            <p><b>{{ item.price }}€</b></p>
            <!-- Precio a la derecha -->
          </div>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import BACK_URL from "../../../config/variables.js"

definePageMeta({
  layout: "user",
});
export default {
  setup() {
    const selectedType = ref("");
    const data = ref(null);
    const loading = ref(false);
    const selectedItem = ref();


    //Le pasas el token del usuario regisstrado para pasarselo a la cabecera del request
    let token = null;
    if (typeof window !== "undefined") {
      token = localStorage.getItem("token");
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
          data.value = await Promise.all(response.data.map(async (item) => {
            if (item.image != null) {
              const urlParts = item.image.split("/").slice(3).join("/");
              const imageUrl = `${BACK_URL}/${urlParts}`;

              // Petición para obtener el contenido de la imagen
              try {
                const imageResponse = await axios.get(imageUrl, {
                  headers: { "ngrok-skip-browser-warning": "69420" },
                  responseType: 'arraybuffer' // Establece el tipo de respuesta como arraybuffer
                });

                // Convierte el array buffer a un blob
                const blob = new Blob([imageResponse.data], { type: 'image/jpeg' });

                // Utiliza FileReader para leer el blob y obtener la URL de datos
                const reader = new FileReader();
                reader.readAsDataURL(blob);

                // Devuelve una promesa para esperar la finalización de la lectura del archivo
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
          }));
        } catch (error) {
          data.value = null;
          console.error("Error fetching data:", error);
        } finally {
          loading.value = false;
        }
      }
    };

    return {
      selectedType,
      data,
      loading,
      fetchData,
      selectedItem
    };
  },
};
</script>

<style>
.table-container {
  max-height: 100%;
  /* Ajusta la altura según sea necesario */
  overflow-y: auto;
  position: relative;
}

thead {
  position: sticky;
  top: 0;
  z-index: 10;
}

thead tr {
  background: inherit;
  /* Mantiene el estilo de fondo del encabezado */
}

tbody {
  display: block;
  max-height: 55vh;
  /* Ajusta la altura según sea necesario */
  overflow-y: auto;
}

thead,
tbody tr {
  display: table;
  width: 100%;
  table-layout: fixed;
}

.absolute {
  z-index: 20;
}
</style>