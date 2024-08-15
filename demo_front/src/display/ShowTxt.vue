<script >
import TxtList from '@/components/getList/TxtList'

export default {
  name: 'ShowTxt',
  components: {
    TxtList,
  },
  data(){
    return {
      activeSection: 'txt',
      txtFiles: ['文件1.txt', '文件2.txt'],
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
  <section v-show="activeSection === 'txt'" class="section">
    <h2>TXT 文件展示</h2>
    <div class="file-list">
      <TxtList></TxtList>
    </div>
  </section>
</template>

<style scoped>

</style>