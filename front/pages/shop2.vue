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
const getTemplateComponent = (templateId: string) => {
  return 'templates-' + templateId;
};

// 파라미터가 문자열이어야만 함.
// const testComponent = resolveComponent('template-template1');
</script>

<template>
  <h1>shop2</h1>
  {{shopModel.template.templateId}}
  <component :is="getTemplateComponent(shopModel.template.templateId)"
             :shopModel="shopModel">
  </component>
</template>

<style scoped>

</style>