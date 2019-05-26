import axios from 'axios'

export default ({ Vue }) => {
  Vue.prototype.$axios = axios.create({
    header: { 'Content-Type': 'application/x-www-form-urlencoded' }
  })
}
