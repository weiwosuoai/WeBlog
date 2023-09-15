<template>
  <el-config-provider :locale="locale">
    <router-view></router-view>
  </el-config-provider>
</template>

<script setup>
import { defineComponent } from 'vue'

import zhCn from 'element-plus/lib/locale/lang/zh-cn'
let locale = zhCn

</script>

<style>
body {
  font-family: -apple-system-font, BlinkMacSystemFont, "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei UI", "Microsoft YaHei", Arial, sans-serif;
  color: #4c4e4d;
  font-size: 16px;
  background: #f4f4f4;
  line-height: 1.6;
}

#nprogress .bar {
   background: #409eff!important;
}
</style>
