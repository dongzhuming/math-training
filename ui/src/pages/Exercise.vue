<template>
  <q-page class="flex flex-center">
    <q-btn @click.native="popupModal()">准备好了，开始吧</q-btn>
    <q-modal v-model="exerciseModal" :content-css="{minWidth: '30vw', minHeight: '30vh'}"
             no-esc-dismiss no-backdrop-dismiss>
      <q-modal-layout>
        <q-toolbar slot="header">
          <q-btn flat round dense @click.native="exitExercise()" icon="keyboard_arrow_left"/>
          <q-toolbar-title>
            剩余{{this.remain}}道题目
            <span slot="subtitle">
              已完成{{this.current}}道题目
            </span>
          </q-toolbar-title>
          <q-btn flat @click.native="checkAnswer()" >下一道 ></q-btn>
        </q-toolbar>

        <div class="layout-padding">
          <q-input type="number" v-model="answer" :prefix="inputPrefix" :suffix="inputSuffix"
                   @keyup.enter="checkAnswer()"></q-input>
        </div>
      </q-modal-layout>
    </q-modal>
  </q-page>
</template>
<style lang="stylus">
.q-input
  font-size 2em
  height 2em
input[placeholder]
  text-overflow ellipsis
</style>
<script>
import axios from 'axios'
export default {
  name: 'PageExercise',
  data () {
    return {
      exerciseModal: false,
      questions: [],
      answers: [],
      answer: '',
      current: 0,
      remain: 10,
      inputPrefix: '',
      inputSuffix: '',
      penaltyCount: 5,
      initialCount: 10
    }
  },
  methods: {
    checkAnswer () {
      if (this.answer === '') {
        this.$q.notify('请填写答案')
        return
      }
      if (this.answer === parseInt(this.answers[this.current])) {
        // 答案正确，减1道
        this.remain--
        this.current++
        if (this.remain === 0) {
          this.accomplish()
        } else {
          this.displayQuestion()
        }
      } else {
        // 答案错误，加10道
        this.$q.notify(`${this.questions[this.current].title} 的正确答案是: "${this.answers[this.current]}"`)
        axios.get(`/api/exercise?count=${this.penaltyCount}`).then(response => {
          response.data.forEach(question => {
            this.questions.push(question)
            this.answers.push(question['answer'])
          })
          this.remain += response.data.length
          this.current++
          this.displayQuestion()
        })
      }
    },
    displayQuestion () {
      this.answer = ''
      let prefix = '', suffix = ''
      let index = this.questions[this.current].items.findIndex(function (item) {
        return item === '____'
      })
      this.questions[this.current].items.forEach(function (x, i) {
        if (i < index) {
          prefix = prefix.concat(x)
        } else if (i > index) {
          suffix = suffix.concat(x)
        }
      })
      this.inputPrefix = prefix
      this.inputSuffix = suffix
    },
    popupModal () {
      axios.get(`/api/exercise?count=${this.initialCount}`).then(response => {
        this.questions = []
        response.data.forEach(question => {
          this.questions.push(question)
          this.answers.push(question['answer'])
        })
        this.remain = response.data.length
        this.current = 0
        this.displayQuestion()
        this.exerciseModal = true
      })
    },
    exitExercise () {
      this.$q.dialog({
        title: '确定退出吗？',
        message: '您还没有完成任务哦',
        ok: '仍然退出',
        cancel: '继续做完'
      }).then(() => {
        this.exerciseModal = false
      }).catch(() => {
      })
    },
    accomplish () {
      this.$q.dialog({
        title: '全部完成',
        message: `总共完成${this.current}道题目，其中有道错题`,
        ok: '知道了'
      }).then(() => {
        this.exerciseModal = false
      })
    }
  }
}
</script>
