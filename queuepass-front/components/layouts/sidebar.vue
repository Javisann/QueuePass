<template>

   <aside id="default-sidebar"
      class="fixed top-0 left-0 z-40 w-64 h-screen transition-transform -translate-x-full sm:translate-x-0"
      aria-label="Sidebar">
      <div class="h-full px-3 py-4 overflow-y-auto bg-gray-50 dark:bg-gray-800">
         <!-- Primera parte del sidebar con Dashboard y Menu -->
         <div>
            <ul class="space-y-2 font-medium">
               <li>
                  <NuxtLink to="/admin" active-class="bg-blue-400 hover:bg-blue-500 text-white" type="button"
                     class="flex items-center p-2 text-gray-900 rounded-lg group">
                     <svg xmlns="http://www.w3.org/2000/svg" class="flex-shrink-0 w-5 h-5" viewBox="0 0 24 24">
                        <path fill="currentColor"
                           d="M12 3a1 1 0 0 1 1 1v6a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1zm8 0a1 1 0 0 1 1 1v6a1 1 0 0 1-1 1h-4a1 1 0 0 1-1-1V4a1 1 0 0 1 1-1zm0 10a1 1 0 0 1 1 1v6a1 1 0 0 1-1 1h-8a1 1 0 0 1-1-1v-6a1 1 0 0 1 1-1zM3 14a1 1 0 0 1 1-1h4a1 1 0 0 1 1 1v6a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1z" />
                     </svg> <span class="ms-3">Queue Dashboard</span>
                  </NuxtLink>
               </li>
               <li>
                  <NuxtLink to="/admin/menu" active-class="bg-blue-400 hover:bg-blue-500 text-white" type="button"
                     class="flex items-center p-2 text-gray-900 rounded-lg group">
                     <svg xmlns="http://www.w3.org/2000/svg" class="flex-shrink-0 w-5 h-5" viewBox="0 0 24 24">
                        <path fill="currentColor"
                           d="M13 17.05q1.1-.525 2.213-.788T17.5 16q.9 0 1.763.15T21 16.6V6.7q-.825-.35-1.713-.525T17.5 6q-1.175 0-2.325.3T13 7.2zm-1 2.425q-.35 0-.663-.088t-.587-.237q-.975-.575-2.05-.862T6.5 18q-1.05 0-2.062.275T2.5 19.05q-.525.275-1.012-.025T1 18.15V6.1q0-.275.138-.525T1.55 5.2q1.175-.575 2.413-.888T6.5 4q1.45 0 2.838.375T12 5.5q1.275-.75 2.663-1.125T17.5 4q1.3 0 2.538.313t2.412.887q.275.125.413.375T23 6.1v12.05q0 .575-.487.875t-1.013.025q-.925-.5-1.937-.775T17.5 18q-1.125 0-2.2.288t-2.05.862q-.275.15-.587.238t-.663.087m2-10.7q0-.225.163-.462T14.525 8q.725-.25 1.45-.375T17.5 7.5q.5 0 .988.063t.962.162q.225.05.388.25t.162.45q0 .425-.275.625t-.7.1q-.35-.075-.737-.112T17.5 9q-.65 0-1.275.125t-1.2.325q-.45.175-.737-.025T14 8.775m0 5.5q0-.225.163-.462t.362-.313q.725-.25 1.45-.375T17.5 13q.5 0 .988.063t.962.162q.225.05.388.25t.162.45q0 .425-.275.625t-.7.1q-.35-.075-.737-.112T17.5 14.5q-.65 0-1.275.113t-1.2.312q-.45.175-.737-.012T14 14.275m0-2.75q0-.225.163-.462t.362-.313q.725-.25 1.45-.375t1.525-.125q.5 0 .988.063t.962.162q.225.05.388.25t.162.45q0 .425-.275.625t-.7.1q-.35-.075-.737-.112t-.788-.038q-.65 0-1.275.125t-1.2.325q-.45.175-.737-.025t-.288-.65" />
                     </svg>
                     <span class="flex-1 ms-3 whitespace-nowrap">Menu</span>
                  </NuxtLink>
               </li>
            </ul>
         </div>
         <!--Añadimos borde de separacion-->
         <div class="mt-4 border-t pt-4">
            <ul class="space-y-2 font-medium">
               <li>
                  <NuxtLink to="/admin/settings" active-class="bg-blue-400 hover:bg-blue-500 text-white" type="button"
                     class="flex items-center p-2 text-gray-900 rounded-lg group">
                     <svg xmlns="http://www.w3.org/2000/svg" class="flex-shrink-0 w-5 h-5" viewBox="0 0 24 24">
                        <path fill="currentColor"
                           d="m9.25 22l-.4-3.2q-.325-.125-.612-.3t-.563-.375L4.7 19.375l-2.75-4.75l2.575-1.95Q4.5 12.5 4.5 12.338v-.675q0-.163.025-.338L1.95 9.375l2.75-4.75l2.975 1.25q.275-.2.575-.375t.6-.3l.4-3.2h5.5l.4 3.2q.325.125.613.3t.562.375l2.975-1.25l2.75 4.75l-2.575 1.95q.025.175.025.338v.674q0 .163-.05.338l2.575 1.95l-2.75 4.75l-2.95-1.25q-.275.2-.575.375t-.6.3l-.4 3.2zm2.8-6.5q1.45 0 2.475-1.025T15.55 12t-1.025-2.475T12.05 8.5q-1.475 0-2.488 1.025T8.55 12t1.013 2.475T12.05 15.5" />
                     </svg>
                     <span class="flex-1 ms-3 whitespace-nowrap">Settings</span>
                  </NuxtLink>
               </li>
            </ul>
         </div>
      </div>
   </aside>
</template>