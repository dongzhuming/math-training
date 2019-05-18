<template>
  <q-page class="flex flex-center">
    <q-btn @click.native="popupModal()">准备好了，开始吧</q-btn>
    <q-modal v-model="maximizedModal" :content-css="{minWidth: '30vw', minHeight: '30vh'}" no-esc-dismiss no-backdrop-dismiss>
      <q-modal-layout>
        <q-toolbar slot="header">
          <q-btn flat round dense v-close-overlay icon="keyboard_arrow_left"/>
          <q-toolbar-title>
            剩余{{this.remain}}道题目
            <span slot="subtitle">
              已完成{{this.current}}道题目
            </span>
          </q-toolbar-title>
          <q-btn flat @click.native="checkAnswer()">确定</q-btn>
        </q-toolbar>

        <div class="layout-padding">
          <q-input type="number" v-model="answer" :prefix="inputPrefix" :suffix="inputSuffix"
                   @keyup.enter="checkAnswer()"></q-input>
        </div>
      </q-modal-layout>
    </q-modal>
  </q-page>
</template>
<style>
  .q-if {
    font-size: 2rem
  }
</style>
<script>
import axios from 'axios'
export default {
  name: 'PageExercise',
  data () {
    return {
      maximizedModal: false,
      questions: [],
      answers: [],
      answer: '',
      current: 0,
      remain: 10,
      inputPrefix: '',
      inputSuffix: ''
    }
  },
  methods: {
    checkAnswer () {
      if (this.answer === '') {
        this.$q.notify('请输出答案')
        return
      }
      if (this.answer === parseInt(this.answers[this.current])) {
        // 答案正确，减1道
        this.remain--
        this.current++
        if (this.remain === 0) {
          // TODO close modal
        } else {
          this.displayQuestion()
        }
      } else {
        // 答案错误，加10道
        this.$q.notify(`${this.questions[this.current].title} 的正确答案是: "${this.answers[this.current]}"`)
        axios.get(`/api/exercise?count=10`).then(response => {
          response.data.forEach(question => {
            this.questions.push(question)
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
      axios.get(`/api/exercise?count=20`).then(response => {
        this.questions = []
        response.data.forEach(question => {
          this.questions.push(question)
          this.answers.push(question['answer'])
        })
        this.remain = response.data.length
        this.current = 0
        this.displayQuestion()
        this.maximizedModal = true
      })
    }
  }
}
</script>
