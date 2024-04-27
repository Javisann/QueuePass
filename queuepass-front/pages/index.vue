<template>
  <div class="flex justify-center items-center h-screen">
    <div class="w-full max-w-sm mx-auto mb-40 p-6 bg-white rounded-lg shadow-lg ">
    <h1 class="text-3xl font-bold text-center mb-4">¡Bienvenido!</h1>
    <h2 class="text-xl text-center mb-4">¡Coge sitio en la cola!</h2>
    <h3 class="text-sm text-center text-gray-500 mb-6">12 personas/grupos en cola</h3>
    <div class="flex justify-center items-center mb-6">
        <GeneralInputNumber></GeneralInputNumber>
    </div>
        <GeneralQueueButton></GeneralQueueButton>
    </div>
  </div>
</template>