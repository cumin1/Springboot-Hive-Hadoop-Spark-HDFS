<template>
  <div class="box">
    <h1>csv list</h1>
    <ul class="file-list">
      <li class="list-item" v-for="csv in csvs" :key="csv" @click="handleClick(csv)">
        {{ csv }} <button class="delete-btn" :data-image-name="image" @click.stop="handleDelete(csv)">Delete</button>
      </li>
    </ul>
  </div>
</template>

<script>
import {onMounted, ref} from "vue";
import axios from 'axios';

export default {
  name: 'CsvList',

  setup(){
    const csvs = ref([]); // csv列表

    async function fetchCsv() {
      await new Promise(resolve => setTimeout(resolve, 100));

      try {
        const response = await fetch('http://localhost:8080/hdfs/file/findCsv');
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        const data = await response.json(); // 假设服务器返回的是JSON格式的数据
        csvs.value = data; // 更新文件列表

      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
      }

    }

    onMounted(() => {
      fetchCsv();
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
      csvs,
      handleClick,
      selectedFile: null,
    }

  },
  methods: {
    fresh_page(){
      location.reload();
    },
    handleDelete(csv) {
      console.log(`点击了删除按钮: ${csv}`);

      // 删除文件
      axios.get('http://localhost:8080/hdfs/file/deleteCsv/' + csv)
          .then(response => {
            console.log('/a', response.data)
          })
      this.fresh_page();
    }
  }

}
</script>

<style scoped>
.box {

}

.file-list {
  list-style-type: none;
  padding: 0;
}

.file-list li {
  width: 50%;
  padding: 10px;
  margin: 5px;
  background-color: #f4f4f4;
  border-radius: 5px;
  cursor: pointer;
}

/* 样式化按钮 */
.delete-btn {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  color: #fff;
  background-color: #007BFF; /* 蓝色背景 */
  border: none;
  border-radius: 4px; /* 圆角 */
  cursor: pointer;
  transition: background-color 0.3s ease; /* 过渡效果 */

  float: right;
}

.delete-btn:hover {
  background-color: #0056b3; /* 鼠标悬停时改变背景色 */
}

.delete-btn:active {
  background-color: #004085; /* 鼠标点击时改变背景色 */
}

.list-item {
  /* 给 li 标签添加一个类名，以便我们可以定位其中的按钮 */
  position: relative; /* 这将允许内部的绝对定位元素基于 li 进行定位 */
  /* 其他样式... */
}

.delete-btn {
  position: absolute; /* 这将使按钮相对于最近的已定位父元素（在这里是 .list-item）进行定位 */
  top: -2px; /* 向上移动 3px */
  right: -100px; /* 向右移动 100px */
  /* 其他样式，如背景、边框、字体大小等... */
}

</style>