<template>
    <grade-info v-if="seeVisible" ref="gradeInfo"></grade-info>
</template>

<script>
import GradeInfo from './grade-info'
export default {
  components: {GradeInfo},
  data () {
    return {
      seeVisible: false,
      studentId: this.$route.params.studentId
    }
  },
  mounted () {
    this.seeHandle(this.$route.query.id)
  },
  methods: {
    seeHandle (id) {
      this.seeVisible = true
      this.$nextTick(() => {
        this.$refs.gradeInfo.dataForm.id = id
        this.$refs.gradeInfo.init()
      })
    }
  }
}
</script>

<style scoped>

</style>
