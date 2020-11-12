import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const resourceHost = constants.baseUrl;


// 세션 스토리지
const storage = window.sessionStorage;

import axios from "axios";
import constants from '../lib/constants'

export default new Vuex.Store({
    state: {
        user_id:"",
        email:"",
        nickname:"",
        password:"",
        height:"",
        weight:"",
        gender:"",
        point:"",
        grade:"",
        guide: [],
        status:"",
        madejoin: false,
    },
    getters: {
        getUser(state){
            return [state.user_id,state.email,state.nickname,state.password, state.height, state.weight, state.gender];
        },
        // madeJoin getter
        getMadejoin(state){
            return state.madejoin;
        }
    },
    mutations: {
        MADEJOIN(state) {
            state.madejoin = true;
        },
        PART(state, data) {
            state.guide = data; 
            //console.log("guide");
            //console.log(state.guide);
        },
        LOGIN (state, res) {
            // 세션 스토리지에 토큰 저장
            //console.log(res.headers["jwt-auth-token"])
            storage.setItem("jwt-auth-token",res.headers["jwt-auth-token"]);
            // 세션 스토리지에 닉네임 저장
            storage.setItem("user_nickname",res.data.data.nickname);
            state.user_id = res.data.data.user_id;
            state.email = res.data.data.email;
            state.nickname = res.data.data.nickname;
            state.password = res.data.data.password;
            state.height = res.data.data.height;
            state.weight = res.data.data.weight;
            state.gender = res.data.data.gender;
            state.point = res.data.data.point;
            state.grade = res.data.data.grade;
            state.status = res.data.status;
        },
        LOGOUT (state) {
            state.user_id = "";
            state.email = "";
            state.nickname = "";
            state.password = "";
            state.height = "";
            state.weight = "";
            state.gender = "";
            state.point = "";
            state.status = "";
            state.grade = "";
            storage.setItem("jwt-auth-token","");
            storage.setItem("user_nickname","");
            //새로 고침
            location.reload();
        },
    },
    actions: {
        // 새로고침 등 페이지가 변하면 토큰을 서버에 넘겨 확인후 새로운 토큰 받아옴
        GETINFO(store,nickname){
            return axios.get(`${resourceHost}account/info`,{
                headers: {
                'jwt-auth-token' : storage.getItem("jwt-auth-token")
               },
               params: nickname

        })
           .then(res =>{
                //console.log(res)
                // 토큰 재생성이므로 mutation 재사용
                store.commit('LOGIN', res)
                
            })
            .catch(err =>{
                // 에러 나면, 세션만료처리
                storage.setItem("jwt-auth-token", "");
                storage.setItem("user_nickname","");
                alert("세션이 만료되었습니다. 로그아웃됩니다");
                location.reload()
                //console.log(err)
                
            }
            )
        },

        PART({commit}, part) {
            return axios.get(`${resourceHost}guide/`+part)
            .then(({data}) => {
                commit('PART', data)
                //console.log("actions success")
            })
            .catch(
                //console.log("actions error")
                )
        },
        UPDATE: (store,payload) =>{
            axios.put(`${resourceHost}account/user`, payload)
            .then(response =>{
                alert("회원 정보 수정 완료!")
                this.$router.push('/')
                location.reload();
            })
            .catch(err =>{
                alert(err.response.data)
                //console.log(err)
            })
        },

        DELETE:(store, {user_id}) =>{
            axios.delete(`${resourceHost}account/user/`+ user_id)
            .then(res =>{
                alert("회원을 탈퇴하셨습니다!")
                store.commit('LOGOUT')
            })
            .catch(err =>{
                alert(err)
                alert("삭제를 실패했습니다")
            })
        },
        JOIN: (store,payload) =>{
            axios.post(`${resourceHost}account/signup/`, payload)
            .then(response =>{
                //console.log("payload 확인" , payload)
                //alert("회원 가입 완료!")
                store.commit('MADEJOIN')
                
            })
            .catch(err =>{
                alert(err.response.data)
                //console.log(err)
            })
        },
        LOGIN ({commit}, {email, password}) {
            //console.log(email);
            return axios.get(`${resourceHost}account/login`, {params:{email, password}})
            .then(response => {
                // 로그인 하기전 storage 초기화
                storage.setItem("jwt-auth-token", "");
                storage.setItem("user_nickname","");
                commit('LOGIN', response)}
            )
        },
        LOGOUT({commit}) {
            commit('LOGOUT')
        }
    },
    modules: {
        
    }
})