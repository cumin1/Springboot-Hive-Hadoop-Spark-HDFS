<template>
  <div class="data-manager">
    <h2>文件管理</h2>
    <div class="file-sections">
      <div class="file-section" v-for="section in sections" :key="section.title">
        <h3>{{ section.title }}</h3>
        <div class="file-list-container">
          <ul class="file-list">
            <li v-for="file in section.files" :key="file">
              {{ file }}
              <button @click="downloadFile(file, section.getpoint)">下载</button>
              <button @click="deleteFile(file, section.delpoint)">删除</button>
            </li>
          </ul>
        </div>
        <input type="file" @change="uploadFile($event, section.uploadpoint)" />
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Data_manager',
  data() {
    return {
      sections: [
        {title: '图片', files: [], endpoint: '/hdfs/file/findImage',delpoint: '/hdfs/file/deleteImage/',getpoint: '/hdfs/file/getFile/',uploadpoint: '/hdfs/file/uploadImage'},
        {title: 'TXT 文件', files: [], endpoint: '/hdfs/file/findTxt',delpoint: '/hdfs/file/deleteTxt/',getpoint: '/hdfs/file/getFile/',uploadpoint: '/hdfs/file/uploadTxt'},
        {title: 'CSV 文件', files: [], endpoint: '/hdfs/file/findCsv',delpoint: '/hdfs/file/deleteCsv/',getpoint: '/hdfs/file/getFile/',uploadpoint: '/hdfs/file/uploadCsv'},
        {title: '视频', files: [], endpoint: '/hdfs/file/findVideo',delpoint: '/hdfs/file/deleteVideo/',getpoint: '/hdfs/file/getFile/',uploadpoint: '/hdfs/file/uploadVideo'},
      ],
    };
  },
  methods: {
    downloadFile(file, getpoint) {
      console.log(`Downloading ${file} from ${getpoint}`);
      // 实现文件下载逻辑
      axios({
        method: 'get',
        url: 'http://localhost:8080'+ getpoint + file,
        responseType: 'blob' // 告诉axios我们期望得到一个blob
      }).then(function (response) {
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
    },
    deleteFile(file, delpoint) {
      console.log(`Deleting ${file} from ${delpoint}`);
      // 实现文件删除逻辑
      axios.get('http://localhost:8080'+ delpoint + file.toString())
          .then(response => {
            console.log(response.data)
          })
      // 删除文件成功后，更新前端文件列表
      this.sections.forEach(section => {
        if (section.delpoint === delpoint) {
          section.files = section.files.filter(f => f !== file);
        }
      });
    },
    uploadFile(event, uploadpoint) {
      const file = event.target.files[0];
      console.log(`Uploading ${file.name} to ${uploadpoint}`);
      // 实现文件上传逻辑
      const formData = new FormData();
      formData.append('file', file);
      // 发送POST请求
      fetch('http://localhost:8080'+uploadpoint, {
        method: 'POST',
        body: formData,
      })
          .then(response => {
            if (!response.ok) {
              throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json(); // 假设服务器返回JSON格式的数据
          })
          .then(data => {
            console.log('Upload successful:', data);
            // 处理上传成功后的逻辑
            this.fetchFiles(); // 更新文件列表
          })
          .catch(error => {
            console.error('There was a problem with your upload:', error);
            // 处理上传失败后的逻辑
          });
      event.target.value = '';

    },
    fetchFiles() {
      this.sections.forEach(section => {
        axios({
          method: 'get',
          url: 'http://localhost:8080' + section.endpoint
        })
            .then(response => {
              section.files = response.data.data; // 更新文件列表
            })
            .catch(error => {
              console.log('There was a problem with your fetch operation:', error);
            })
      });
    },
  },
  mounted() {
    this.fetchFiles();
  },
};
</script>

<style scoped>
.data-manager {
  flex: 1;
  padding: 20px;
  background-color: #ffffff;
  border: 1px solid #dcdcdc;
  border-radius: 8px;
  margin: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
  font-size: 24px;
  color: #333333;
  margin-bottom: 20px;
}

.file-sections {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.file-section {
  width: 45%;
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  border: 1px solid #e5e5e5;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
}

.file-section h3 {
  font-size: 18px;
  margin-bottom: 10px;
  color: #1d1d1f;
}

/* 固定高度和滚动条 */
.file-list-container {
  max-height: 300px; /* 固定高度 */
  overflow-y: auto; /* 允许垂直滚动 */
}

.file-list {
  list-style: none;
  padding: 10;
  margin: 0;
}

.file-list li {
  display: flex;
  width: 100%;
  justify-content: space-between;
  align-items: center;
  padding: 5px 0;
  font-size: 16px;
}

.file-list button {
  background-color: #007aff;
  color: white;
  border: none;
  border-radius: 5px;
  padding: 5px 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.file-list button:hover {
  background-color: #0056b3;
}

.file-section input[type="file"] {
  margin-top: 10px;
  padding: 5px;
}
</style>
