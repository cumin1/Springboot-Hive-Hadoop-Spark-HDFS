<script>
import ImageList from '@/components/getList/ImageList'

export default {
  name: 'ShowImage',
  components: {
    ImageList,
  },
  data(){
    return {
      activeSection: 'images',
      images: [
        { src: 'image1.jpg', alt: 'Image 1' },
        { src: 'image2.jpg', alt: 'Image 2' }
      ],
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
  <section v-show="activeSection === 'images'" class="section">
    <h2>图片展示</h2>
    <div class="gallery">
      <ImageList></ImageList>
    </div>
  </section>
</template>

<style scoped>

</style>