<script setup lang="ts">
  import ChatBubble from './ChatBubble.vue'
  import { Message } from '../../lib/interfaces.ts'
  
  interface Props {
    messages: Message[];
  }

  defineProps<Props>();

</script>

<template>
   <div class="">
    <ChatBubble 
      v-for="message of messages" 
      :key="`${message.createdAt}`" 
      :text-content="message.textContent" 
      :source="message.source"
    />
  </div>
</template>