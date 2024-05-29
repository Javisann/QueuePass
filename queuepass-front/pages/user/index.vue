<template>
  <div v-if="!inQueue"
    class="flex flex-col justify-between items-center min-h-screen bg-gradient-to-bl from-white to-blue-200">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 flex flex-col justify-start gap-5 w-full max-w-md mx-auto my-10 p-8">
      <div>
        <h1 class="text-5xl text-center mb-4 "><b>¡Bienvenido!</b></h1>
      </div>
      <p class="text-center text-blue-800 ">
        Con este sistema podrás coger sitio en la cola e irte a hacer cualquier otra cosa mientras esperas tu turno.
      </p>
      <div class="flex justify-center items-center ">
        <GeneralInputNumber @changeView="joinQueue"></GeneralInputNumber>
      </div>
      <h3 class="text-base text-center text-gray-600 mb-6">
        <strong>{{ peopleInQueue }} personas/grupos en cola</strong>
      </h3>
    </div>
  </div>

  <div v-else class="flex flex-col justify-between items-center min-h-screen bg-gradient-to-bl from-white to-blue-200">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 w-full max-w-md mx-auto my-10 p-8">
      <h1 class="text-4xl font-bold text-center mb-4 hind-regular"><b>
          Estás en el puesto número</b>
      </h1>
      <p class="text-9xl text-center mb-4 text-red-700">{{ peopleCount }}</p>
      <p class="text-base text-center text-gray-600 mb-6 font-opensans">
        Tu puesto en la cola se irá actualizando
      </p>
      <button @click="deleteData" type="button"
        class="block w-full text-white bg-gradient-to-r from-red-500 to-red-700 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-red-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center mb-2">
        Salirse de la cola
      </button>
      <p class="border-t border-black pt-4 text-lg text-center text-gray-600 mt-7">Esperando mesa para <b>{{ people }}
          personas</b>
      </p>
    </div>
  </div>

</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';
import axios from "axios";
import BACK_URL from "../../config/variables.js"

const inQueue = ref(false);
const people = ref(1);
const peopleInQueue = ref(0);
const peopleCount = ref(0);


function joinQueue(newPeople) {
  people.value = newPeople;
  postData();
}

let token = null;
if (typeof window !== "undefined") {
  token = localStorage.getItem("token");
}

let name = null;
if (typeof window !== "undefined") {
  name = localStorage.getItem("name");
}

const fetchPeopleInQueue = async () => {
  try {
    const response = await axios.get(
      `${BACK_URL}/api/queue/people`,
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
          Authorization: `Bearer ${token}`,
        },
      });
    peopleInQueue.value = response.data;
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const fetchPositionCount = async () => {
  const id = localStorage.getItem("id");

  try {
    const response = await axios.get(
      `${BACK_URL}/api/queue/${id}`,
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
          Authorization: `Bearer ${token}`,
        },
      });
    if (response.data != 0) {
      peopleCount.value = response.data;
    } else {
      peopleCount.value = 0;
      inQueue.value = false;
    }
  } catch (error) {
    console.error("Error fetching data:", error);
  }
};

const postData = async () => {
  try {
    const response = await axios.post(
      `${BACK_URL}/api/queue`,
      {
        name: name,
        num_personas: people.value,
      },
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
          Authorization: `Bearer ${token}`,
        },
      }
    );

    localStorage.setItem("id", response.data.id);

    inQueue.value = true;
    fetchPositionCount();

    if (inQueue.value) {
      intervalId = setInterval(() => {
        fetchPositionCount()
      }, 5000);
    }

  } catch (error) {
    console.error("Error post data:", error);
  }
};

const deleteData = async () => {
  const id = localStorage.getItem("id");

  try {
    await axios.delete(
      `${BACK_URL}/api/queue/${id}`,
      {
        headers: {
          "ngrok-skip-browser-warning": "69420",
          Authorization: `Bearer ${token}`,
        },
      }
    );

    inQueue.value = false;
    clearInterval(intervalId);
  } catch (error) {
    console.error("Error deleting data:", error);
  }
};

let intervalId;
onMounted(() => {
  fetchPeopleInQueue();
});

onUnmounted(() => {
  clearInterval(intervalId);
});

definePageMeta({
  layout: "user"
})
</script>

<style scoped>
.font-opensans {
  font-family: 'Open Sans', sans-serif;
}
</style>
