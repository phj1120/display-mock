<script setup lang="ts">
const shopModel = ref({
  shopNo: '1',
  shopNm: 'shop1',
  template: {
    templateNo: '1',
    templateNm: 'template1',
    templateId: 'Template1',
  },
  corners: [
    {
      cornerNo: '1',
      cornerNm: 'corner1',
      cornerId: 'Corner1',
    },
    {
      cornerNo: '2',
      cornerNm: 'corner2',
      cornerId: 'Corner2',
    }
  ]
});
const templateId = computed(() => {
  return 'templates-' + shopModel.value.template.templateId;
})
</script>

<template>
  <h1>shop</h1>
  <component :is="templateId"
             :shopModel="shopModel">
  </component>
</template>

<style scoped>

</style>