
import UserLayout from './layouts/userLayout.vue';
<template>
  <div>
    <NuxtLayout :name="userLayout">
      <NuxtPage></NuxtPage>
    </NuxtLayout>
  </div>
</template>


<script>

const userLayout = "user";

</script>

