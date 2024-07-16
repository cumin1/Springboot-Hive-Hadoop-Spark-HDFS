<template>
  <div>
    <h1>上传csv</h1>
    <input  type="file" @change="handleCsvUpload" />
    <button class="upload-button" @click="submitCsv">上传</button>
  </div>
</template>

<script>
export default {
  name: 'uploadImage',
  selectedFile: null,

  methods: {
    fresh_page(){
      location.reload();
    },
    handleCsvUpload(event) {
      this.selectedFile = event.target.files[0]; // 获取用户选择的第一个文件
    },
    submitCsv() {
      if (!this.selectedFile) {
        alert('请先选择一个文件！');
        return;
      }
      const formData = new FormData();
      formData.append('file', this.selectedFile); // 将文件添加到FormData对象中，键名为'file'

      // 发送POST请求
      fetch('http://localhost:8080/hdfs/file/uploadCsv', {
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
          })
          .catch(error => {
            console.error('There was a problem with your upload:', error);
            // 处理上传失败后的逻辑
          });

      this.fresh_page();
    },
  }

}
</script>

<style>

.upload-button {
  /* 样式化上传按钮 */
  padding: 8px 16px;
  background-color: #4CAF50; /* 绿色背景 */
  border: none;
  border-radius: 4px;
  color: white;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  transition: background-color 0.3s; /* 平滑过渡效果 */
}

.upload-button:hover {
  background-color: #45a049; /* 鼠标悬停时改变颜色 */
}
</style>