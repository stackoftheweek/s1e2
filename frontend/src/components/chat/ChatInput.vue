<script setup lang="ts">
import { ref } from 'vue'
import { Textarea } from '../ui/Textarea'
import { Button } from '../ui/Button'
import Plane from '../../assets/Plane.vue'

const textInput = ref<string>('');

const emit = defineEmits<{
  submit: [chat: string]
}>()

const handleClick = () => {
  if (textInput.value === '') return;
  emit('submit', textInput.value)
  textInput.value = ''
}
</script>

<template>
   <div class="flex">
      <Textarea v-model="textInput" class="mr-4">
      </Textarea>
      <Button size="icon" v-on:click="handleClick">
        <Plane class="fill-white"/>
      </Button>
    </div>
</template>