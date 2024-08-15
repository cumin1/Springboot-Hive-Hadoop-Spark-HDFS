<script>
export default {
  name: 'Navigation_bar',
}
</script>

<template>
<div>
  <!-- 顶部导航栏 -->
  <header class="navbar">
    <div class="logo">
      <h1>数据仪表盘</h1>
    </div>
    <nav>
      <ul>
        <li><a href="/">首页</a></li>
        <li><a href="/datamanager">数据管理</a></li>
        <li><a href="#">设置</a></li>
      </ul>
    </nav>
  </header>
</div>
</template>

<style>
/* 导航栏整体样式 */
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  background: linear-gradient(to bottom, #f9f9f9, #ececec);
  border-bottom: 1px solid #dcdcdc;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

/* Logo 部分 */
.logo h1 {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 22px;
  font-weight: 500;
  color: #1d1d1f;
  margin: 0;
}

/* 导航菜单样式 */
nav ul {
  list-style: none;
  display: flex;
  gap: 30px;
  margin: 0;
  padding: 0;
}

/* 菜单项样式 */
nav ul li {
  position: relative;
}

nav ul li a {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  font-size: 16px;
  font-weight: 400;
  color: #007aff;
  text-decoration: none;
  padding: 10px 0;
  transition: color 0.3s ease, border-bottom 0.3s ease;
}

/* 鼠标悬停效果 */
nav ul li a:hover {
  color: #0056b3;
  border-bottom: 2px solid #007aff;
}

/* 当前选中项的样式 */
nav ul li a.active {
  font-weight: 500;
  border-bottom: 2px solid #0056b3;
}
</style>