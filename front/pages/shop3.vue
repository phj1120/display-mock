<script setup lang="ts">
import DynamicComponentType from "~/types/DynamicComponentType";

const shopModel = ref({
  shopNo: '1',
  shopNm: 'shop1',
  template: {
    templateNo: '1',
    templateNm: '동적 템플릿1',
    templateId: 'dynamic-template1',
  },
  corners: [
    {
      cornerNo: '1',
      cornerNm: '동적 코너1',
      cornerId: 'dynamic-corner1',
    },
    {
      cornerNo: '2',
      cornerNm: '동적 코너2',
      cornerId: 'dynamic-corner2',
    }
  ]
});
</script>

<template>
  <h1>shop3</h1>
  <DynamicComponent :shop-model="shopModel"
                    :dynamic-component-type="DynamicComponentType.TEMPLATE"
                    :template="shopModel.template"
  ></DynamicComponent>
</template>

<style scoped>

</style>