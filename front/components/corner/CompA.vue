<script setup>
import { ref } from 'vue'

const count = ref(0)

onMounted(() =>{
  console.log('omMounted CompA')
})
</script>

<template>
  <p>Current component: A</p>
  <span>count: {{ count }}</span>
  <button @click="count++">+</button>
</template>
