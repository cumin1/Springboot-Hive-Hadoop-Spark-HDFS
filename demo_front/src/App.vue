<template>
  <div class="bigbox">
    <div class="box">
      <h1>image list</h1>
      <ul class="file-list">
        <li v-for="image in images" :key="image" @click="handleClick(image)">
          {{ image }}
        </li>
      </ul>
    </div>

    <div class="box">
      <h1>csv list</h1>
      <ul class="file-list">
        <li v-for="csv in csvs" :key="csv" @click="handleClick(csv)">
          {{ csv }}
        </li>
      </ul>
    </div>

    <div class="box">
      <h1>txt list</h1>
      <ul class="file-list">
        <li v-for="txt in txts" :key="txt" @click="handleClick(txt)">
          {{ txt }}
        </li>
      </ul>
    </div>

    <div class="box">
      <h1>video list</h1>
      <ul class="file-list">
        <li v-for="video in videos" :key="video" @click="handleClick(video)">
          {{ video }}
        </li>
      </ul>
    </div>

  </div>
</template>

<script>
import { ref, onMounted } from 'vue';

export default {
  name: 'FileList',
  setup() {
    const images = ref([]); // 照片列表
    const csvs = ref([]); // csv列表
    const txts = ref([]); // txt列表
    const videos = ref([]); // 视频列表

    // 模拟从后端获取数据
    async function fetchImages() {
      // 这里使用setTimeout模拟异步请求
      await new Promise(resolve => setTimeout(resolve, 100)); // 延迟1秒
      // 假设这是从后端API获取到的文件列表
      const mockFiles = ['image1.jpg', 'image2.jpg', 'image3.jpg'];
      images.value = mockFiles; // 更新文件列表
    }

    async function fetchCsv() {
      await new Promise(resolve => setTimeout(resolve, 100));
      const mockFiles = ['csv1.csv', 'csv2.csv', 'csv3.csv'];
      csvs.value = mockFiles; // 更新文件列表
    }

    async function fetchTxt() {
      await new Promise(resolve => setTimeout(resolve, 100));
      const mockFiles = ['txt1.txt', 'txt2.txt', 'txt3.txt'];
      txts.value = mockFiles; // 更新文件列表
    }

    async function fetchVideo() {
      await new Promise(resolve => setTimeout(resolve, 100));
      const mockFiles = ['video1.mp4', 'video2.mp4', 'video3.mp4'];
      videos.value = mockFiles; // 更新文件列表
    }

    // 组件挂载时获取文件列表
    onMounted(() => {
      fetchImages();
      fetchCsv();
      fetchVideo();
      fetchTxt();
    });

    // 文件点击处理函数（可选）
    function handleClick(file) {
      console.log(`点击了文件: ${file}`);
      // 这里可以添加更多的处理逻辑，比如打开文件预览等
    }

    // 暴露给模板的响应式数据和函数
    return {
      images,
      csvs,
      txts,
      videos,
      handleClick
    };
  }
};
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

