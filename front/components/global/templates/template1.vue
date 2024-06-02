<script setup lang="ts">
const props = defineProps(['shopModel']);
const shopModel = props.shopModel;
const convertCornerId = (cornerId: string) => {
  return `corners-${cornerId}`
};
</script>

<template>
  <h1>TEMPLATE_1</h1>
  <h2>templateNo: {{shopModel.template.templateNo}}</h2>
  <h2>templateNm: {{shopModel.template.templateNm}}</h2>
  <h2>templateId: {{shopModel.template.templateId}}</h2>
  <Component v-for="corner in props.shopModel.corners"
             :is="convertCornerId(corner.cornerId)"
             :corner="corner"
  >
  </Component>
</template>

<style scoped>

</style>