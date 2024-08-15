<template>
  <div class="box">
    <ul class="file-list">
      <li class="list-item" v-for="image in images" :key="image">
        {{ image }}
<!--    <button class="delete-btn" :data-image-name="image" @click.stop="handleClick(image)">下载</button>-->
      </li>
    </ul>
  </div>

</template>

<script >
import { ref, onMounted } from 'vue';
import axios from 'axios';


export default {
  name: 'ImageList',
  setup() {
    const images = ref([]); // 照片列表

    // 模拟从后端获取数据
    async function fetchImages() {
      // 这里使用setTimeout模拟异步请求
      await new Promise(resolve => setTimeout(resolve, 100)); // 延迟1秒
      try {
        const response = await fetch('http://localhost:8080/hdfs/file/findImage');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json(); // 假设服务器返回的是JSON格式的数据
        images.value = data.data; // 更新文件列表

      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
      }
    }

    // 组件挂载时获取文件列表
    onMounted(() => {
      fetchImages();
    });

    // 文件点击处理函数（可选）  触发下载文件
    function handleClick(file) {
      console.log(`点击了文件: ${file}`);
      // http://localhost:8080/hdfs/file/getFile/{filename} 下载文件
      axios({
        method: 'get',
        url: 'http://localhost:8080/hdfs/file/getFile/' + file,
        responseType: 'blob' // 告诉axios我们期望得到一个blob
      })
          .then(function (response) {
            // 创建一个blob URL
            const url = window.URL.createObjectURL(new Blob([response.data]));

            // 创建一个新的a标签
            const link = document.createElement('a');
            link.href = url;
            // 设置文件名（可选，但推荐）
            link.setAttribute('download', file);

            // 触发下载
            document.body.appendChild(link);
            link.click();

            // 清理创建的a标签
            document.body.removeChild(link);

            // 释放URL对象
            window.URL.revokeObjectURL(url);
          })
          .catch(function (error) {
            console.error('Error downloading file:', error);
          });

    }

    return {
      images,
      handleClick,
    }

  },

  methods: {
    fresh_page(){
      location.reload();
    },
    handleDelete(image) {
      console.log(`点击了删除按钮: ${image}`);

      // 删除文件
      axios.get('http://localhost:8080/hdfs/file/deleteImage/' + image)
          .then(response => {
            console.log('/a', response.data)
          })

      this.fresh_page();
    }
  }


}


</script>
<style scoped>

</style>