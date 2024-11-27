<script setup>
import { ref } from 'vue'

const count = ref(0)

onMounted(() =>{
  console.log('omMounted CompB')
})

</script>

<template>
  <p>Current component: B</p>
  <span>count: {{ count }}</span>
  <button @click="count++">+</button>
</template>
