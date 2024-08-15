<script>
import ShowImage from '@/display/ShowImage'
import ShowTxt from '@/display/ShowTxt'
import ShowCsv from '@/display/ShowCsv'
import ShowVideo from '@/display/ShowVideo'

export default {
  name: 'Main_display',
  components: {
    ShowImage,
    ShowTxt,
    ShowCsv,
    ShowVideo,
  },
  props: {
    section: {
      type: String,
      required: true
    }
  },

}
</script>

<template>
  <main class="content">
    <ShowImage v-if="section === 'images'"></ShowImage>
    <ShowTxt v-if="section === 'txt'"></ShowTxt>
    <ShowCsv v-if="section === 'csv'"></ShowCsv>
    <ShowVideo v-if="section === 'videos'"></ShowVideo>
  </main>
</template>

<style>
.box {
  display: flex;
  flex-direction: column; /* 垂直排列 */
  justify-content: flex-start; /* 从顶部开始排列 */
  align-items: flex-start; /* 左对齐 */
  height: 50vh; /* 固定高度 */
  width: 90%; /* 固定宽度 */
  overflow-x: auto; /* 如果内容超出高度，出现滚动条 */
  border: 1px solid #ccc; /* 添加边框以示例效果 */
  padding: 5px; /* 内边距 */
}

.file-list {
  list-style-type: none;
  padding: 0;
}

.file-list li {
  width: 30%;
  padding: 25px;
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