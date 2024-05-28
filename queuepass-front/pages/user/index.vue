<template>
  <div v-if="!inQueue" class="flex flex-col justify-between items-center min-h-screen bg-gradient-to-r from-blue-200 to-red-200">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 w-full max-w-md mx-auto my-10 p-8">
      <div class="border-b-2 border-black mb-6">
        <h1 class="text-5xl font-bold text-center mb-4 hind-regular"><b>¡Bienvenido!</b></h1>
      </div>
      <h2 class="text-2xl text-center mb-4 font-opensans text-red-600 hind-regular"><b>¡Coge sitio en la cola!</b></h2>
      <h3 class="text-base text-center text-gray-600 mb-6">
        {{ peopleInQueue }} personas/grupos en cola
      </h3>
      <div class="flex justify-center items-center mb-6">
        <GeneralInputNumber @changeView="joinQueue"></GeneralInputNumber>
      </div>
    </div>
  </div>

  <div v-else class="flex flex-col justify-between items-center min-h-screen bg-gradient-to-r from-blue-200 to-red-200">
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
      <p class="border-t border-black pt-4 text-lg text-center text-gray-600 mt-7">Esperando mesa para <b>{{ people }} personas</b>
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

let username = null;
if (typeof window !== "undefined") { 
  username = localStorage.getItem("username"); 
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
@import url('https://fonts.googleapis.com/css2?family=Edu+TAS+Beginner:wght@400..700&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Edu+TAS+Beginner:wght@400..700&family=Hind&display=swap');

.edu-tas-beginner {
  font-family: "Edu TAS Beginner", cursive;
  font-optical-sizing: auto;
  font-weight: 600;
  font-style: normal;
}

.hind-regular {
  font-family: "Hind", sans-serif;
  font-weight: 400;
  font-style: normal;
}

.font-opensans {
  font-family: 'Open Sans', sans-serif;
}
</style>
