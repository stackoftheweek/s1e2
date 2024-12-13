<script setup lang="ts">
import { Avatar, AvatarFallback, AvatarImage } from '../../components/ui/Avatar'
</script>

<template>
    <Avatar>
    <AvatarImage src="https://github.com/wfknowles.png?size=200" alt="@radix-vue" />
    <AvatarFallback>CN</AvatarFallback>
  </Avatar>
</template>