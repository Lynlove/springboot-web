import Vuex from 'vuex'
import state from './state'
import Vue from 'vue'

Vue.use(Vuex)

export default new Vuex.Store({
    state: state
})