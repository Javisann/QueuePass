<template>
    <div class="flex">
        <!--Le aplicamos el componente SideBar a este layout-->
        <LayoutsSidebar class="sidebar"></LayoutsSidebar>
        <!--Slot: pagina asociada. La pagina la cual en el DefinePageMeta tenga este layout sera el que aparezca-->
        <slot class="page"></slot>
    </div>
</template>

<style>
.sidebar{
    position: sticky;
    z-index: 0;
}
.page{
    position: relative;
    z-index: 1;
}
</style>