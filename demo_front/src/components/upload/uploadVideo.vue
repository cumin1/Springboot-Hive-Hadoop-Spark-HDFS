<template>
  <div>
    <h1>上传视频</h1>
    <input type="file" @change="handleVideoUpload" />
    <button @click="submitVideo">上传</button>
  </div>
</template>

<script>
export default {
  name: 'uploadVideo',
  selectedFile:null,

  methods: {
    fresh_page(){
      location.reload();
    },
    handleVideoUpload(event) {
      this.selectedFile = event.target.files[0]; // 获取用户选择的第一个文件
    },
    submitVideo() {
      if (!this.selectedFile) {
        alert('请先选择一个文件！');
        return;
      }
      const formData = new FormData();
      formData.append('file', this.selectedFile); // 将文件添加到FormData对象中，键名为'file'

      // 发送POST请求
      fetch('http://localhost:8080/hdfs/file/uploadVideo', {
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

</style>