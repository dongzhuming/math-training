<template>
  <q-page class="flex flex-center">
    <!--<q-btn to="/settings">重新设置</q-btn>-->
    <q-btn @click.native="startExercise()">准备好了，开始吧</q-btn>
  </q-page>
</template>

<style>
</style>

<script>
  import axios from 'axios'

  export default {
  name: 'PageExercise',
  data () {
    return {
      questions: [],
      current: 0,
      remain: 10
    }
  },
  methods: {
    checkAnswer (val) {
      if (val === this.questions[this.current]['answer']) {
        // 答案正确，减1道
        this.remain--
        this.current++
        if (this.remain === 0) {
          this.$q.dialog({
            title: '恭喜',
            message: `总共完成${this.current}道题目`
          })
        } else {
          this.popupQuestion()
        }
      } else {
        // 答案错误，加10道
        this.$q.notify(`正确答案是: "${val}"`)
        axios.get(`/api/exercise?count=10`).then(response => {
          response.data.forEach(question => {
            this.questions.push(question)
          })
          this.remain += response.data.length
          this.current++
          this.popupQuestion()
        })
      }
    },
    startExercise () {
      axios.get(`/api/exercise?count=20`).then(response => {
        this.questions = []
        response.data.forEach(question => {
          this.questions.push(question)
        })
        this.remain = response.data.length
        this.current = 0
        this.popupQuestion()
      })
    },
    popupQuestion () {
      this.$q.dialog({
        title: `还有${this.remain}道题`,
        message: this.questions[this.current].title,
        prompt: {
          model: '',
          type: 'number'
        }
      }).then(answer => {
        this.checkAnswer(answer + '')
      }).catch(() => {})
    }
  }
}
</script>
