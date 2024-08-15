<script>
export default {
  name: 'Side_bar',
  data() {
    return {
      activeSection: 'images'
    };
  },
  methods: {
    showSection(section) {
      this.activeSection = section;
      console.log("side_bar:",section);
      this.$emit('update-section', section); // 通过 $emit 将 section 传递给父组件
    }
  }
};
</script>

<template>
  <!-- 侧边栏 -->
  <aside class="sidebar">
    <ul>
      <li><a href="#" @click="showSection('images')">图片</a></li>
      <li><a href="#" @click="showSection('txt')">TXT</a></li>
      <li><a href="#" @click="showSection('csv')">CSV</a></li>
      <li><a href="#" @click="showSection('videos')">视频</a></li>
    </ul>
  </aside>
</template>

<style>
/* 侧边栏整体样式 */
.sidebar {
  width: 250px;
  height: 100vh;
  background: linear-gradient(to bottom, #f9f9f9, #ececec);
  border-right: 1px solid #dcdcdc;
  box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
  padding-top: 20px;
}

/* 列表项样式 */
.sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar ul li {
  margin-bottom: 20px;
}

.sidebar ul li a {
  display: block;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 18px;
  font-weight: 400;
  color: #007aff;
  text-decoration: none;
  padding: 10px 20px;
  border-radius: 8px;
  transition: background-color 0.3s ease, color 0.3s ease;
}

/* 鼠标悬停效果 */
.sidebar ul li a:hover {
  background-color: #e5f1ff;
  color: #0056b3;
}

/* 当前选中项的样式 */
.sidebar ul li a.active {
  background-color: #007aff;
  color: white;
  font-weight: 500;
}
</style>