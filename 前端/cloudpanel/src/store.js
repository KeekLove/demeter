import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const state = {
    token:null,
    privilege:null,
};

const mutations  = {
    saveToken(state,token){

        return (state.token = token);
    },
    savePrivilege(state,privilege){
        return (state.privilege = privilege);
    }

};

export default new Vuex.Store({
    state,
    mutations,
});