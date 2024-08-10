<template>
  <div>
    <textarea
        v-model="codeInput"
        placeholder="请输入你的代码..."
        rows="10"
        cols="50"
        style="width: 100%; height: 100%; resize: vertical;"
    ></textarea>
    <button @click="executeCode">执行代码</button>
    <div v-if="output">{{ output }}</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      codeInput: '',
      output: ''
    };
  },
  methods: {
    async executeCode() {
      try {
        const response = await fetch('http://localhost:8080/python/execute', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            code: this.codeInput
          })
        });

        if (!response.ok) {
          throw new Error('Network response was not ok');
        }

        const result = await response.text(); // 假设后端返回的是文本格式的结果
        this.output = result;
      } catch (error) {
        console.error('There was a problem with your fetch operation:', error);
        this.output = '执行失败，请检查网络连接或服务器状态。';
      }
    }
  }
}
</script>

<style>

</style>