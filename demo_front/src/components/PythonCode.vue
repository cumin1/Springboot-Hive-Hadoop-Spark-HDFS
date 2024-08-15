<template>
  <div class="code-executor">
    <textarea
        v-model="codeInput"
        placeholder="请输入你的代码..."
        rows="10"
        cols="50"
        class="code-input"
    ></textarea>
    <button @click="executeCode" class="execute-btn">执行代码</button>
    <div v-if="output" class="output">{{ output }}</div>
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

<style scoped>
.code-executor {
  width: 100%;
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.code-input {
  width: 100%;
  height: 200px;
  padding: 15px;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-family: "Menlo", "Monaco", "Courier New", monospace;
  font-size: 16px;
  background-color: #fff;
  color: #333;
  resize: vertical;
  outline: none;
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  transition: border 0.3s ease;
}

.code-input:focus {
  border-color: #007aff;
}

.execute-btn {
  display: inline-block;
  margin-top: 10px;
  padding: 10px 20px;
  background-color: #007aff;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.3s ease, box-shadow 0.3s ease;
  box-shadow: 0 2px 4px rgba(0, 122, 255, 0.4);
}

.execute-btn:hover {
  background-color: #005bb5;
  box-shadow: 0 4px 8px rgba(0, 91, 181, 0.5);
}

.output {
  margin-top: 20px;
  padding: 15px;
  background-color: #e5e5e5;
  border-radius: 8px;
  font-family: "Menlo", "Monaco", "Courier New", monospace;
  font-size: 16px;
  color: #333;
  white-space: pre-wrap;
}
</style>
