import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const state = {
    token:null,
};

const mutations  = {
    saveToken(state,token){

        return (state.token = token);
    },
};

export default new Vuex.Store({
    state,
    mutations,
});