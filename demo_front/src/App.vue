<template>
<!--  <PythonCode></PythonCode>-->
<!--  <DataList></DataList>-->
  <Navigation_bar></Navigation_bar>
  <router-view></router-view>
  <Bottom_status_bar></Bottom_status_bar>
</template>

<script>
import Navigation_bar from "@/layout/Navigation_bar.vue";
import Bottom_status_bar from "@/layout/Bottom_status_bar.vue";

export default {
  name: 'FileList',
  components: {
    Navigation_bar,
    Bottom_status_bar
  },
};
</script>

<style>
/* 全局样式 */
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #f4f4f4;
  color: #333;
}

header {
  padding: 15px;
  text-align: center;
}


.dashboard {
  display: flex;
  margin-top: 20px;
  height: calc(100vh - 40px);
}

/* 主内容样式 */
.content {
  flex: 1;
  padding: 20px;
  background-color: white;
  margin-left: 15px;
}

.section h2 {
  border-bottom: 2px solid #007BFF;
  padding-bottom: 10px;
  margin-bottom: 20px;
}

.gallery, .file-list, .csv-table, .video-gallery {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

</style>

