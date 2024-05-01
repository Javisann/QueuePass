<template>
  <div v-if="inQueue == false" class="flex flex-col justify-between items-center h-screen">
    <GeneralLogos></GeneralLogos>
    <div class="flex-1 w-full max-w-sm mx-auto mb-20 p-6 bg-white rounded-lg shadow-lg">
      <h1 class="text-3xl font-bold text-center mb-4">¡Bienvenido!</h1>
      <h2 class="text-xl text-center mb-4">¡Coge sitio en la cola!</h2>
      <h3 class="text-sm text-center text-gray-500 mb-6">
        12 personas/grupos en cola
      </h3>
      <div class="flex justify-center items-center mb-6">
        <GeneralInputNumber @changeView="joinQueue"></GeneralInputNumber> <!--Escucha el evento del hijo, y cuando suceda llama a la funcion joinQueue-->
      </div>
    </div>
  </div>

  <div v-else>
    <p>{{ people }}</p>
  </div>
</template>

<script setup>
const inQueue = ref(false); // definimos boolean para saber el estado
const people = ref(1); // Definimos variable para las personas
function joinQueue(newPeople){ 
  	people.value = newPeople; // Coge parametros del evento y se los asigna a la variable anterior
    inQueue.value = !inQueue.value; // Cambia el estado al contrario al que este
};
</script>
