<script setup lang="ts">
const shopModel = ref({
  shopNo: '1',
  shopNm: 'shop1',
  template: {
    templateNo: '1',
    templateNm: '템플릿1',
    templateId: 'template1',
  },
  corners: [
    {
      cornerNo: '1',
      cornerNm: '코너1',
      cornerId: 'corner1',
    },
    {
      cornerNo: '2',
      cornerNm: '코너2',
      cornerId: 'corner2',
    }
  ]
});
const templateId = computed(() => {
  return 'templates-' + shopModel.value.template.templateId;
})
</script>

<template>
  <h1>shop1</h1>
  <component :is="templateId"
             :shopModel="shopModel">
  </component>
</template>

<style scoped>

</style>