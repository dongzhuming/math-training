<template>
  <q-page class="flex flex-center">
    <div class="row">
      <p class="caption">
        <span class="chip-container">
            <q-chip square color="primary">
              一共做 {{ initialCount }} 道
            </q-chip>
          </span>
      </p>
      <q-slider v-model="initialCount" :min="5" :max="50" :step="5" color="blue" />
      <p class="caption">
        <span class="chip-container">
          <q-chip square color="secondary">
            错一道加 {{ penaltyCount }} 道
          </q-chip>
        </span>
      </p>
      <q-slider v-model="penaltyCount" :min="1" :max="10" :step="1" color="green" />
      <q-btn @click.native="popupModal()">准备好了，开始吧</q-btn>
    </div>
    <q-modal v-model="exerciseModal" :content-css="{minWidth: '30vw', minHeight: '30vh'}"
             no-esc-dismiss no-backdrop-dismiss>
      <q-modal-layout>
        <q-toolbar slot="header">
          <q-btn flat round dense @click.native="exitExercise()" icon="keyboard_arrow_left"></q-btn>
          <q-toolbar-title>
            剩余{{this.remain}}道题目
            <span slot="subtitle">
              已完成{{this.current}}道题目
            </span>
          </q-toolbar-title>
          <q-btn flat @click.native="checkAnswer()" >下一题</q-btn>
        </q-toolbar>
        <div class="row layout-padding">
          <div class="title">
            <span>{{inputPrefix}}</span></div>
          <div>
            <q-input type="number" v-model="answer"
                     @keyup.enter="checkAnswer()"/>
          </div>
          <div class="title">
            <span>{{inputSuffix}}</span>
          </div>
        </div>
      </q-modal-layout>
    </q-modal>
  </q-page>
</template>
<style lang="stylus">
.q-input
  width 100px
input[type="number"]
  font-size 2em
  height 40px
.title
  padding-top 7px
  padding-bottom 7px
  font-size 2em

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
      initialCount: 10,
      errorCount: 0
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
        // 答案错误
        this.$q.notify(`${this.questions[this.current].title} 的正确答案是: "${this.answers[this.current]}"`)
        axios.get(`/api/exercise?count=${this.penaltyCount + 1}&code=${this.questions[this.current].code}`)
          .then(response => {
            response.data.forEach(question => {
              this.questions.push(question)
              this.answers.push(question['answer'])
              console.log(`增加一道${question['title']}, 答案${question['answer']}`)
            })
            this.remain += this.penaltyCount
            this.current++
            this.errorCount++
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
        this.answers = []
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
        cancel: '继续做完',
        color: 'primary'
      }).then(() => {
        this.exerciseModal = false
      }).catch(() => {
      })
    },
    accomplish () {
      this.$q.dialog({
        title: '全部完成',
        message: `总共完成${this.current}道题目，其中有${this.errorCount}道错题`,
        ok: '知道了'
      }).then(() => {
        this.exerciseModal = false
      })
    }
  }
}
</script>
