<script>
import VideoList from '@/components/getList/VideoList'

export default {
  name: 'ShowVideo',
  components: {
    VideoList
  },
  data(){
    return {
      activeSection: 'videos',
      videos: [
        { src: 'movie.mp4' }
      ]
    }
  },
  methods: {
    showSection(section) {
      this.activeSection = section;
    }
  }
}
</script>

<template>
  <section v-show="activeSection === 'videos'" class="section">
    <h2>视频展示</h2>
    <div class="video-gallery">
      <VideoList></VideoList>
    </div>
  </section>
</template>

<style scoped>

</style>