<script setup lang="ts">
  const props = defineProps(['corner'])
</script>

<template>
  <h1>Corner2</h1>
  <h2>cornerNo: {{props.corner.cornerNo}}</h2>
  <h2>cornerId: {{props.corner.cornerId}}</h2>
  <h2>cornerNm: {{props.corner.cornerNm}}</h2>
</template>

<style scoped>

</style>