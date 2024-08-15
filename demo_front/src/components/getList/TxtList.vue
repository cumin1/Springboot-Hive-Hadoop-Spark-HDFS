<template>
  <div class="box">
    <ul class="file-list">
      <li class="list-item" v-for="txt in txts" :key="txt">
        {{ txt }}
<!--        <button class="delete-btn" :data-image-name="image" @click.stop="handleDelete(txt)">Delete</button>-->
      </li>
    </ul>
  </div>
</template>

<script>
import {onMounted, ref} from "vue";
import axios from 'axios';

export default {
  name: 'TxtList',

  setup() {
    const txts = ref([]); // txt列表

    async function fetchTxt() {
      await new Promise(resolve => setTimeout(resolve, 100));
      try {
        const response = await fetch('http://localhost:8080/hdfs/file/findTxt');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json(); // 假设服务器返回的是JSON格式的数据
        txts.value = data.data; // 更新文件列表

      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
      }

    }

    onMounted(() => {
      fetchTxt();
    });

    // 文件点击处理函数（可选）
    function handleClick(file) {
      console.log(`点击了文件: ${file}`);
      // 这里可以添加更多的处理逻辑，比如打开文件预览等
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
      txts,
      handleClick,
      selectedFile: null,
    }

  },

  methods: {
    fresh_page(){
      location.reload();
    },
    handleDelete(txt) {
      console.log(`点击了删除按钮: ${txt}`);

      // 删除文件
      axios.get('http://localhost:8080/hdfs/file/deleteTxt/' + txt)
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