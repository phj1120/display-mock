<script setup>
import { shallowRef } from 'vue'
import {CornerCompA, CornerCompB} from '#components'

const current = shallowRef(CornerCompA)
</script>

<template>
  <div class="demo">
    <label><input type="radio" v-model="current" :value="CornerCompA" /> A</label>
    <label><input type="radio" v-model="current" :value="CornerCompB" /> B</label>
    <KeepAlive>
      <component :is="current"></component>
    </KeepAlive>
  </div>
</template>
