<script setup lang="ts">
import DynamicComponentType from "~/types/DynamicComponentType";
const props = defineProps(['dynamicComponentType', 'shopModel', 'template', 'corner']);

const getComponentPath = (id: string) => {
  if (DynamicComponentType.CORNER === props.dynamicComponentType) {
    return 'corners-' + id;
  }
  return 'templates-' + id;
};

</script>

<template>
  <component v-if="DynamicComponentType.TEMPLATE === dynamicComponentType"
             :is="getComponentPath(template.templateId)"
             :shopModel="shopModel"
             :template="template"
             >
  </component>
  <component v-if="DynamicComponentType.CORNER === dynamicComponentType"
             :is="getComponentPath(corner.cornerId)"
             :shopModel="shopModel"
             :corner="corner"
  >
  </component>
</template>

<style scoped>

</style>