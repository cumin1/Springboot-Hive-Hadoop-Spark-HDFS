<script>
import axios from 'axios';

export default {
  name: 'Bottom_status_bar',
  data () {
    return {
      images_length: 0,
      txtFiles_length: 0,
      csvData_length: 0,
      videos_length: 0
    }
  },
  created() {
    this.fetchData(); // 组件创建时获取数据
  },
  methods: {
    async fetchData() {
      try {
        // 获取图片数量
        const imagesResponse = await axios.get('http://localhost:8080/hdfs/file/findImage');
        this.images_length = imagesResponse.data.data.length;

        // 获取 TXT 文件数量
        const txtFilesResponse = await axios.get('http://localhost:8080/hdfs/file/findTxt');
        this.txtFiles_length = txtFilesResponse.data.data.length;

        // 获取 CSV 文件数量
        const csvDataResponse = await axios.get('http://localhost:8080/hdfs/file/findCsv');
        this.csvData_length = csvDataResponse.data.data.length;

        // 获取视频数量
        const videosResponse = await axios.get('http://localhost:8080/hdfs/file/findVideo');
        this.videos_length = videosResponse.data.data.length;
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    },
  },
}

</script>

<template>
  <footer class="status-bar">
    <div class="status-content">
      <p>图片: {{ images_length }} | TXT 文件: {{ txtFiles_length }} | CSV 文件: {{ csvData_length }} | 视频: {{ videos_length }}</p>
      <p class="server-status">服务器状态: 正常运行</p>
    </div>
  </footer>
</template>

<style scoped>
/* 底部状态栏整体样式 */
.status-bar {
  position: fixed;
  bottom: 0;
  width: 100%;
  background: linear-gradient(to top, #f9f9f9, #ececec);
  border-top: 1px solid #dcdcdc;
  box-shadow: 0 -1px 5px rgba(0, 0, 0, 0.1);
  padding: 10px 20px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
}

/* 状态内容样式 */
.status-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

/* 文本样式 */
.status-bar p {
  margin: 0;
  font-size: 14px;
  color: #333;
}

/* 服务器状态的文本样式 */
.server-status {
  font-weight: 500;
  color: #007aff;
}

/* 响应式布局 */
@media (max-width: 768px) {
  .status-content {
    flex-direction: column;
    align-items: flex-start;
  }

  .server-status {
    margin-top: 10px;
  }
}
</style>
