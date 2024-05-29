<template>
    <div class="p-10 flex-1 bg-gradient-to-bl from-white to-blue-100">
        <div class="flex items-center justify-between border-b pb-4">
            <h1 class="text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white">
                Cola actual
            </h1>
            <img src="../../assets/images/queue-pass-logo-fondo.png" alt="Imagen a la derecha"
                class="h-18 w-48 object-cover">
        </div>
        <div class="relative shadow-md sm:rounded-lg mt-5">
            <div class="table-container">
                <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                    <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
                        <tr>
                            <th scope="col" class="px-6 py-3">ID</th>
                            <th scope="col" class="px-6 py-3">Fecha</th>
                            <th scope="col" class="px-6 py-3">Nombre</th>
                            <th scope="col" class="px-6 py-3">Numero de personas</th>
                            <th scope="col" class="px-6 py-3">
                                <span>Acciones</span>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="item in data" :key="item.id"
                            class="bg-white border-b dark:bg-gray-800 dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-600">
                            <th scope="row"
                                class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                                {{ item.id }}
                            </th>
                            <td class="px-6 py-4">{{ item.name }}</td>
                            <td class="px-6 py-4">{{ item.date }}</td>
                            <td class="px-6 py-4">{{ item.num_personas }}</td>
                            <td class="px-6 py-4">
                                <button @click="openPopupDelete(item.id)" type="button"
                                    class="focus:outline-none text-white  bg-blue-400 hover:bg-blue-500 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-green-600 dark:hover:bg-green-700 dark:focus:ring-green-800">
                                    Pass
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- CENTER PARA MODAL DELETE-->
    <center v-if="showPopupDelete"
        class="absolute h-full w-full bg-black bg-opacity-20 flex items-center justify-center">
        <div class="relative p-4 w-full max-w-md max-h-full">
            <div class="bg-white rounded-lg shadow dark:bg-gray-700">
                <button @click="closePopup" type="button"
                    class="absolute top-3 end-2.5 text-gray-400 bg-transparent rounded-lg text-sm w-8 h-8 ms-auto inline-flex justify-center items-center dark:hover:bg-gray-600 dark:hover:text-white"
                    data-modal-hide="popup-modal">
                    <svg class="w-3 h-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none"
                        viewBox="0 0 14 14">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="m1 1 6 6m0 0 6 6M7 7l6-6M7 7l-6 6" />
                    </svg>
                    <span class="sr-only">Close modal</span>
                </button>
                <div class="p-4 md:p-5 text-center">
                    <svg class="mx-auto mb-4 text-gray-400 w-12 h-12 dark:text-gray-200" aria-hidden="true"
                        xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 20 20">
                        <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                            d="M10 11V6m0 8h.01M19 10a9 9 0 1 1-18 0 9 9 0 0 1 18 0Z" />
                    </svg>
                    <h3 class="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                        ¿Quieres pasar a esta persona?
                    </h3>
                    <button @click="
                        deleteData();
                    closePopup();
                    " type="button"
                        class="text-white bg-red-600 hover:bg-red-800 focus:ring-4 focus:outline-none focus:ring-red-300 dark:focus:ring-red-800 font-medium rounded-lg text-sm inline-flex items-center px-5 py-2.5 text-center">
                        Si, estoy seguro
                    </button>
                    <button @click="closePopup" type="button"
                        class="py-2.5 px-5 ms-3 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-100 dark:focus:ring-gray-700 dark:bg-gray-800 dark:text-gray-400 dark:border-gray-600 dark:hover:text-white dark:hover:bg-gray-700">
                        No, cancelar
                    </button>
                </div>
            </div>
        </div>
    </center>


</template>

<script>
import axios from 'axios';
import BACK_URL from '~/config/variables';



export default {
    setup() {

        const data = ref(null);
        const loading = ref(false);
        const showPopupDelete = ref(false);
        const selectedId = ref(0);

        const openPopupDelete = (id) => {
            showPopupDelete.value = true;
            selectedId.value = id;
        };
        const closePopup = () => {
            showPopupDelete.value = false;
            selectedId.value = null;
        };
        let token = null;
        if (typeof window !== "undefined") {
            token = localStorage.getItem("token");
        }

        const fetchData = async () => {
            try {
                const response = await axios.get(
                    `${BACK_URL}/api/queue`,
                    {
                        headers: {
                            "ngrok-skip-browser-warning": "69420",
                            Authorization: `Bearer ${token}`,
                        },
                    }
                );

                data.value = response.data;

                if (!data.value.length > 0) return;

                const items = response.data.map(item => {
                    const date = new Date(item.date);
                    const hour = date.toLocaleTimeString('es-ES', {
                        hour: '2-digit',
                        minute: '2-digit',
                        second: '2-digit'
                    });
                    return {
                        ...item, // los 3 puntos son para coger todas las propiedades de item
                        date: hour // este cambia la propidad date
                    };

                });
                
                data.value = items;
            } catch (error) {
                data.value = null;
                console.error("Error fetching data:", error);
            } finally {
                loading.value = false;
            }
        };

        const deleteData = async () => {

            const id = new Number(selectedId.value);

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
            } catch (error) {
                console.error("Error deleting data:", error);
            }
        };

        let intervalId;
        onMounted(() => {
            fetchData();
            intervalId = setInterval(() => {
                fetchData()
            }, 2000);
        });

        onUnmounted(() => {
            clearInterval(intervalId);
        });

        return {
            fetchData,
            deleteData,
            data,
            loading,
            openPopupDelete,
            closePopup,
            showPopupDelete,
            selectedId
        }

    }
};

definePageMeta({
    layout: "admin-layout"
})

</script>