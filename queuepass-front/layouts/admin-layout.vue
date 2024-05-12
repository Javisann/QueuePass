<template>
    <div class="flex">
        <LayoutsSidebar class="sidebar"></LayoutsSidebar>
        <slot class="page"></slot>
    </div>
</template>

<style>

.sidebar{
    position: relative;
    z-index: 0;
}

.page{
    position: relative;
    z-index: 1;
}

</style>