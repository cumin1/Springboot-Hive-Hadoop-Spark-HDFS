<template>
  <div class="bigbox">

    <ImageList></ImageList>

    <uploadImage></uploadImage>

    <CsvList></CsvList>

    <uploadCsv></uploadCsv>

    <TxtList></TxtList>

    <uploadTxt></uploadTxt>

    <VideoList></VideoList>

    <uploadVideo></uploadVideo>

  </div>
</template>

<script>

import {defineComponent} from "vue";
import uploadVideo from "@/components/upload/uploadVideo.vue";
import uploadTxt from "@/components/upload/uploadTxt.vue";
import uploadImage from "@/components/upload/uploadImage.vue";
import VideoList from "@/components/getList/VideoList.vue";
import CsvList from "@/components/getList/CsvList.vue";
import TxtList from "@/components/getList/TxtList.vue";
import uploadCsv from "@/components/upload/uploadCsv.vue";
import ImageList from "@/components/getList/ImageList.vue";

export default defineComponent({
  components: {ImageList, uploadCsv, TxtList, CsvList, VideoList, uploadImage, uploadTxt, uploadVideo}
})
</script>

<style>
.bigbox{
  display: flex;
  flex-wrap: wrap;
}

.box {
  width: 50%;
}

.file-list {
  list-style-type: none;
  padding: 0;
}

.file-list li {
  width: 20%;
  padding: 10px;
  margin: 5px;
  background-color: #f4f4f4;
  border-radius: 5px;
  cursor: pointer;
}

.file-list li:hover {
  background-color: #ddd;
}
</style>