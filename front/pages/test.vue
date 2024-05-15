<template>
  <div>
    test.vue
  </div>
  <button @click="callApi">API CALL</button>
  response: {{testResponse}}
</template>
<script lang="ts" setup>
const testResponse = ref({});
const callApi = async () => {
  const result = await useFetch('http://localhost:8080/display/test')
  testResponse.value = result.data;
}
</script>
<style></style>