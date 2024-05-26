<template>
  <div v-if="inQueue == false" class="flex flex-col justify-between items-center h-full">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 w-full max-w-sm mx-auto mb-20 p-6">
      <div class="border-b mb-2">
        <h1 class="text-3xl font-bold text-center mb-4">¡Bienvenido!</h1>
      </div>
      <h2 class="text-xl text-center mb-4">¡Coge sitio en la cola!</h2>
      <h3 class="text-sm text-center text-gray-500 mb-6">
        {{ peopleInQueue }} personas/grupos en cola
      </h3>
      <div class="flex justify-center items-center mb-6">
        <GeneralInputNumber @changeView="joinQueue"></GeneralInputNumber>
        <!--Escucha el evento del hijo, y cuando suceda llama a la funcion joinQueue-->
      </div>
    </div>
  </div>

  <div v-else class="flex flex-col justify-between items-center h-full">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 w-full max-w-sm mx-auto mb-20 p-6 ">
      <h1 class="text-3xl font-bold text-center mb-4">
        Estas en el puesto numero
      </h1>
      <p class="text-9xl text-center mb-4">{{ peopleCount }}</p>
      <p class="text-sm text-center text-gray-500 mb-6">
        Tu puesto en la cora se ira actualizando al momento
      </p>
      <button @click="deleteData" type="button"
        class="block w-full text-white bg-gradient-to-r from-red-500 to-red-700 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
        Salirse de la cola
      </button>
      <p class="border-t pt-5 text-lg text-center text-gray-500 mt-10">Esperando mesa para <b>{{ people }} personas</b>
      </p>
    </div>

  </div>
</template>

<script setup>

import axios from "axios";
import BACK_URL from "../../config/variables.js"

const inQueue = ref(false); // definimos boolean para saber el estado
const people = ref(1); // Definimos variable para las personas
const peopleInQueue = ref(); // Definimos variable para las personas en la cola
const peopleCount = ref(); // Definimos variable de tu posicion en la cola
function joinQueue(newPeople) {
  people.value = newPeople; // Coge parametros del evento y se los asigna a la variable anterior
  postData(); // Llama a la funcion postData
}

let token = null;
if (typeof window !== "undefined") { // Comprueba que la ventana esta definida
  token = localStorage.getItem("token"); // Obtiene el token del local storage
}
const fetchPeopleInQueue = async () => {
  try {
    const response = await axios.get(
      `${BACK_URL}/api/queue/people`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    peopleInQueue.value = response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const fetchPositionCount = async () => {

  const id = localStorage.getItem("id"); // Obtiene el id del local storage

  try {
    const response = await axios.get(
      `${BACK_URL}/api/queue/${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
    peopleCount.value = response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const postData = async () => {
  try {
    const response = await axios.post(
      `${BACK_URL}/api/queue`,
      {
        username: username,
        num_personas: people.value,
      },
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      },);

      localStorage.setItem("id", response.data.id); // Guarda el id en el local storage

    inQueue.value = !inQueue.value; // Cambia el estado al contrario al que este
    fetchPositionCount()
    
  } catch (error) {
    console.error("Error post data:", error);
  }
};

const deleteData = async () => {

  const id = localStorage.getItem("id"); // Obtiene el id del local storage

  try {
    const response = await axios.delete(
      `${BACK_URL}/api/queue/${id}`,
      {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

    inQueue.value = !inQueue.value; // Cambia el estado al contrario al que este

  } catch (error) {
    console.error("Error post data:", error);
  }
};

//Coge del local storage el nombre de usuario
let username = null;
if (typeof window !== "undefined") { // Comprueba que la ventana esta definida
  username = localStorage.getItem("username"); // Obtiene el token del local storage
}


onMounted(fetchPeopleInQueue);

definePageMeta({
  layout: "user" // Le asignamos al layout que queremos usar
})

</script>
