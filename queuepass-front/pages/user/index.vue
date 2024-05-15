<template>
  <div v-if="inQueue == false" class="flex flex-col justify-between items-center h-full">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 w-full max-w-sm mx-auto mb-20 p-6">
      <div class="border-b mb-2">
        <h1 class="text-3xl font-bold text-center mb-4">¡Bienvenido!</h1>
      </div>
      <h2 class="text-xl text-center mb-4">¡Coge sitio en la cola!</h2>
      <h3 class="text-sm text-center text-gray-500 mb-6">
        11 personas/grupos en cola
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
      <p class="text-9xl text-center mb-4">12</p>
      <p class="text-sm text-center text-gray-500 mb-6">
        Tu puesto en la cora se ira actualizando al momento
      </p>
      <button @click="joinQueue" type="button"
        class="block w-full text-white bg-gradient-to-r from-red-500 to-red-700 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
        Salirse de la cola
      </button>
      <p class="border-t pt-5 text-lg text-center text-gray-500 mt-10">Esperando mesa para <b>{{ people }} personas</b></p>
    </div>

  </div>
</template>

<script setup>
const inQueue = ref(false); // definimos boolean para saber el estado
const people = ref(1); // Definimos variable para las personas
function joinQueue(newPeople) {
  people.value = newPeople; // Coge parametros del evento y se los asigna a la variable anterior
  inQueue.value = !inQueue.value; // Cambia el estado al contrario al que este
}


definePageMeta({
    layout:"user"
})

</script>
