<script setup lang="ts">
import { Avatar, AvatarFallback, AvatarImage } from '../../components/ui/Avatar'
import { ref, onMounted } from "vue";

const src_url = ref('');

onMounted(async () => {
  const res = await fetch("/api/avatar_url");
  src_url.value = await res.text();
});

</script>
<template>
    <Avatar>
    <AvatarImage :src="src_url" alt="@radix-vue" />
      <AvatarFallback>CN</AvatarFallback>
  </Avatar>
</template>