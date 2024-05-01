<template>
  <form class="max-w-xs mx-auto">
    <div class="flex flex-col items-center">
      <label for="quantity-input" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Cuantas
        personas:</label>
      <div class="flex justify-center items-center mb-6">
        <button type="button" id="decrement-button" @click="people > 0 ? people-- : null" :disabled="people === 0"
          data-input-counter-decrement="quantity-input"
          class="bg-gray-100 dark:bg-gray-700 dark:hover:bg-gray-600 dark:border-gray-600 hover:bg-gray-200 border border-gray-300 rounded-s-lg p-3 h-11 focus:ring-gray-100 dark:focus:ring-gray-700 focus:ring-2 focus:outline-none">
          <svg class="w-3 h-3 text-gray-900 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
            fill="none" viewBox="0 0 18 2">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M1 1h16" />
          </svg>
        </button>

        <input type="text" id="quantity-input" v-model="people" data-input-counter
          aria-describedby="helper-text-explanation"
          class="w-20 bg-gray-50 border-x-0 border-gray-300 h-11 text-center text-gray-900 text-sm focus:ring-blue-500 focus:border-blue-500 block w-full py-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          placeholder="0" required disabled />
        <button type="button" id="increment-button" @click="people <= 10 ? people++ : null" :disabled="people === 10"
          data-input-counter-increment="quantity-input"
          class="bg-gray-100 dark:bg-gray-700 dark:hover:bg-gray-600 dark:border-gray-600 hover:bg-gray-200 border border-gray-300 rounded-e-lg p-3 h-11 focus:ring-gray-100 dark:focus:ring-gray-700 focus:ring-2 focus:outline-none">
          <svg class="w-3 h-3 text-gray-900 dark:text-white" aria-hidden="true" xmlns="http://www.w3.org/2000/svg"
            fill="none" viewBox="0 0 18 18">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
              d="M9 1v16M1 9h16" />
          </svg>
        </button>
      </div>

      <button @click="joinQueue" type="button"
        class="block w-full text-white bg-gradient-to-r from-cyan-500 to-blue-500 hover:bg-gradient-to-bl focus:ring-4 focus:outline-none focus:ring-cyan-300 dark:focus:ring-cyan-800 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 mb-2">
        Unirse a la cola
      </button>
    </div>
  </form>
</template>

<script setup>
let people = ref(1); // varaible reactiva de las personas seleccionadas
const emits = defineEmits(["changeView"]); // Definir un emit para pasarselo al padre

function joinQueue() { // hacer funcion que llama al emit y emite el evento
  emits("changeView", people); // parametro people que le pasas al darle al boton
}
</script>
