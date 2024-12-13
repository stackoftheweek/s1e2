<script setup lang="ts">
interface Props {
  textContent?: String
  createdAt?: Date;
  source?: String
}

defineProps<Props>()

</script>

<template>
  <div class="flex-row my-2" :class="{
    'justify-items-end': source === 'user',
    'justify-items-start': source === 'client'
    }">
    <div 
    class="p-3 rounded-md shadow-md"
    :class="{
    'bg-teal-400 ml-6': source === 'user',
    'bg-slate-100 mr-6': source === 'client'
    }"
    >
      {{ textContent }}
    </div>
  </div>
</template>