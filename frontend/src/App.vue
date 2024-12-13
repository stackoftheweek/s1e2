<script setup lang="ts">
import { Chat } from './components/chat'
import { UserAvatar } from './components/user'
import { Button } from './components/ui/Button'
</script>

<template>
   <div class="w-full flex justify-end p-3">
      <Button size="lg-round" variant="ghost">
         <UserAvatar />
      </Button>
   </div>
   <div class="w-full mx-auto md:max-w-4xl">
      <Chat/>
   </div>
   
</template>

<style scoped>

</style>
