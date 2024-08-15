<script>
import CsvList from '@/components/getList/CsvList'

export default {
  name: 'ShowCsv',
  components: {
    CsvList,
  },
  data(){
    return {
      activeSection: 'csv',
      csvHeaders: ['列1', '列2'],
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
  <section v-show="activeSection === 'csv'" class="section">
    <h2>CSV 文件展示</h2>
    <div class="csv-table">
      <CsvList></CsvList>
    </div>
  </section>
</template>

<style scoped>

</style>