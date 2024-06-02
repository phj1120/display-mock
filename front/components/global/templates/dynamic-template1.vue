<script setup lang="ts">
import DynamicComponentType from "~/types/DynamicComponentType";

const props = defineProps(['shopModel', 'template']);
const shopModel = props?.shopModel;
const template = props?.template;
</script>

<template>
  <h1>Dynamic Template1</h1>
  <h2>templateNo: {{ template.templateNo }}</h2>
  <h2>templateNm: {{ template.templateNm }}</h2>
  <h2>templateId: {{ template.templateId }}</h2>
  <DynamicComponent v-for="corner in props.shopModel.corners"
                    :shop-model="shopModel"
                    :dynamic-component-type="DynamicComponentType.CORNER"
                    :corner="corner"
  >
  </DynamicComponent>
</template>

<style scoped>

</style>