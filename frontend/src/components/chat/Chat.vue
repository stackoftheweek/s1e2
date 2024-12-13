<script setup lang="ts">
import ChatInput from './ChatInput.vue';
import { ref } from 'vue';
import type { Ref } from 'vue';
import ChatMessages from './ChatMessages.vue';
import { Message } from '../../lib/interfaces';

const messages = ref<Message[]>([]);
const isLoading = ref<boolean>(false);


async function fetchResponse(textContent: string) {
  const url = `/api/questionGet?message=${ encodeURI(textContent) }`;

  try {
    const response = await fetch(url, {
      method: "GET",
      headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      },
    });

    if (!response) {
      return "I'm sorry, I cannot provide a response."
    }

    if (response.ok) {
      const json = await response.json();
      return json.answer;
    }
    

  } catch (error) {
    console.error(error.message);
  }
}

const handleSubmit = async (textContent: string) => {
  // input message
  messages.value.push({
    textContent,
    createdAt: `${Date.now()}`,
    source: 'user',
  });

  // fetching chatbot response
  isLoading.value = true;
  const responseText = await fetchResponse(textContent);
  isLoading.value = false;

  // chatbot response
  messages.value.push({
    textContent: responseText,
    createdAt: `${Date.now()}`,
    source: 'client',
  });
}

</script>

<template>
  <div class="w-full">
    <ChatMessages v-if="messages.length > 0" :messages class="p-4 border-2 border-solid border-gray-200 rounded-t-lg bg-white"/>
    <ChatInput class="p-4 bg-gray-200 rounded-b-lg" :class="{'rounded-lg': messages.length === 0}" @submit="handleSubmit"/>
  </div>
</template>