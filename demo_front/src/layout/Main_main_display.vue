<script>

import Main_display from "@/layout/Main_display.vue";
import Side_bar from "@/layout/Side_bar.vue";

export default {
  name: 'FileList',
  components: {
    Main_display,
    Side_bar
  },
  data() {
    return {
      section: 'images'
    };
  },
  methods: {
    showSection(section) {
      this.section = section;
      console.log("App:",section);
    }
  },
};
</script>

<template>
  <!-- 主体布局 -->
  <div class="dashboard">
    <Side_bar @update-section="showSection"></Side_bar>
    <Main_display :section="section"></Main_display>
  </div>
</template>

<style>

</style>