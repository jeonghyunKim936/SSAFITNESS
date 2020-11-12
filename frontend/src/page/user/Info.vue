<template>
    <div class="container" id="join">
        <div class="form-container">
            <div class="middle">
                <h1>회원 정보</h1>
                <div class="form-wrap">
                    <div class="input-wrap">
                        <label for="inputNicknameIcon"><v-icon class="signup-icon">mdi-account</v-icon></label>
                            <input v-model="nickName"
                                id="nickname" 
                                placeholder="Your nickname" type="text" required readonly/>
                        
                    </div>
                    
                    <div class="input-wrap">
                        <label for="inputEmailIcon"><v-icon class="signup-icon">mdi-email</v-icon></label>
                        <input v-model="email"
                            id="email"
                            placeholder="Your email"
                            type="text" required readonly/>
                    </div>
                    <div class="input-wrap password-wrap">
                        <label for="inputPasswordIcon"><v-icon class="signup-icon">mdi-lock-outline</v-icon></label>
                        <input v-model="password"
                            id="password" 
                            :type="passwordType"
                            placeholder="Your password" required/>
                    </div>
                    <div class="input-wrap password-wrap">
                        <label for="inputPasswordCheckIcon"><v-icon class="signup-icon">mdi-lock</v-icon></label>
                        <input v-model="passwordConfirm"
                            id="passwordConfirm" 
                            :type="passwordType"
                            placeholder="check password" required/>
                    </div>

                    <div class="input-wrap">
                        <label for="inputHeightIcon"><v-icon class="signup-icon">mdi-human-male-height</v-icon></label>
                        <input v-model="height" 
                            id="height"
                            type="text"
                            placeholder="Your height (only Number)" required/>
                    </div>
                    <div class="input-wrap">
                        <label for="inputWeightIcon"><v-icon class="signup-icon">mdi-scale-bathroom</v-icon></label>
                        <input v-model="weight" 
                            id="weight"
                            type="text"
                            placeholder="Your weight (only Number)" required/>
                    </div>
                    <div class="input-wrap" >
                        <label for="genderMale">Male</label>
                        <input type="radio" id="genderMale" name="gender" value="m" v-model="gender" checked="checked" required>
                        <label for="genderFemale">Female</label>
                        <input type="radio" id="genderFemale" name="gender" value="f" v-model="gender">
                    </div>
                </div>

                <button class="btn btn--back btn--login" v-on:click="editUserInfo()">수정하기</button>
                <button class="btn btn--back btn--login" v-on:click="deleteAccount()">탈퇴</button>
            </div>
        </div> 
        
    </div>

    <!-- <div class="user" id="join">
        <div class="wrapC table">
            <div class="middle">
                <h1>회원 정보</h1>
                <div class="form-wrap">
                    dsds
                    <div class="input-wrap">
                        Nickname:
                            <input v-model="nickName"
                                id="nickname" 
                                placeholder="Your nickname" type="text" required readonly/>
                        
                    </div>
                    
                    <div class="input-wrap">
                        E-mail:
                        <input v-model="email"
                            id="email"
                            placeholder="Your email"
                            type="text" required readonly/>
                    </div>
                    <div class="input-wrap password-wrap">
                        Password:
                        <input v-model="password"
                            id="password" 
                            :type="passwordType"
                            placeholder="Your password" required readonly/>
                    </div>
                    <div class="input-wrap">
                        <input v-model="height" 
                            id="height"
                            type="text"
                            placeholder="Your height (only Number)" required/>
                    </div>
                    <div class="input-wrap">
                        <input v-model="weight" 
                            id="weight"
                            type="text"
                            placeholder="Your weight (only Number)" required/>
                    </div>
                    <div class="input-wrap" >
                        <label for="genderMale">Male</label>
                        <input type="radio" id="genderMale" name="gender" value="m" v-model="gender" checked="checked" required>
                        <label for="genderFemale">Female</label>
                        <input type="radio" id="genderFemale" name="gender" value="f" v-model="gender">
                    </div>
                </div>

                <button class="btn btn--back btn--login" v-on:click="editUserInfo()">수정하기</button>
                <button class="btn btn--back btn--login" v-on:click="deleteAccount()">탈퇴</button>
            </div>
        </div> 
        
    </div> -->

</template>



<script>
import axios from "axios";
import {mapState} from 'vuex'

    export default {
        data(){
        return {
            user_id: this.$store.state.user_id,
            email: this.$store.state.email,
            nickName: this.$store.state.nickname,
            password: this.$store.state.password,
            passwordConfirm: '',
            height: this.$store.state.height,
            weight: this.$store.state.weight,
            gender: this.$store.state.gender,
            
            isTerm: false,
            passwordType:"password",
            passwordConfirmType:"password",
            
            }
        },
        components: {
        },
        created(){       
            this.$store.watch(
            (state)=>{
                return this.$store.getters.getUser // could also put a Getter here
            },
            (newValue,oldValue)=>{
                //something changed do something
                //console.log(newValue)
                this.user_id = newValue[0];
                this.email = newValue[1];
                this.nickName = newValue[2];
                this.password = newValue[3];
                this.height = newValue[4];
                this.weight = newValue[5];
                this.gender = newValue[6];
            },
            )
            if (sessionStorage.getItem("user_nickname") == "") {
            alert("로그인 해주세요")
            this.$router.push("/user/loginjoin/");
            }
        },  
        methods: {
            editUserInfo() {
                if(this.password !== this.passwordConfirm){
                    alert('비밀 번호가 다릅니다!')
                }else{
                    this.$store.dispatch('UPDATE',{
                    user_id: this.user_id,
                    email: this.email,
                    nickname: this.nickName,
                    password: this.password,
                    height: this.height,
                    weight: this.weight,
                    gender: this.gender,
                })
                .then(() => {
                    this.$router.push('/')
                    location.reload();
                })
                }
                
            },
            deleteAccount() {
                //console.log(this.$store.state.user_id);
                this.$store.dispatch('DELETE',
                    {user_id: this.$store.state.user_id,
                    })
                .then(() => {
                    this.$router.push('/')
                })
            }
        },
        watch: {
        },

    }

</script>
<style scoped>

body {
	background: #f6f5f7;
	/* display: flex; */
	/* justify-content: center; */
	/* align-items: center; */
	/* flex-direction: column; */
	font-family: 'Montserrat', sans-serif;
	/* height: 100vh; */
	margin: -20px 0 50px;
}

h1 {
	font-weight: bold;
	margin: 20px 0 30px;

}

h2 {
	text-align: center;
}

p {
	font-size: 14px;
	font-weight: 100;
	line-height: 20px;
	letter-spacing: 0.5px;
	margin: 20px 0 30px;
}

span {
	font-size: 12px;
}

a {
	color: #333;
	font-size: 14px;
	text-decoration: none;
	margin: 15px 0;
}

.form-wrap {
	background-color: #FFFFFF;
	/* display: flex; */
	/* align-items: center; */
	/* justify-content: center; */
	/* flex-direction: column; */
	padding: 0 100px;
	/* height: 100%; */
	text-align: center;
}

input {
	background-color: #eee;
	border: none;
	padding: 12px 15px;
	margin: 8px 0;
	width: 90%;
}

#genderMale, #genderFemale{
    width: 10%;
}
.container {
	background-color: #fff;
	border-radius: 10px;
  	box-shadow: 0 14px 28px rgba(0,0,0,0.25), 
			0 10px 10px rgba(0,0,0,0.22);
	position: relative;
	overflow: hidden;
	width: 768px;
	max-width: 100%;
	min-height: 600px;
	margin-top: 50px;
}

.form-container {
	/* position: absolute; */
	top: 0;
	height: 100%;
	transition: all 0.6s ease-in-out;
}

.sign-up-container {
	left: 0;
	width: 50%;
	opacity: 0;
  	z-index: 1;
  	padding-top: 45px;
}
.signup-icon {
  margin-right: 10px;
}

.gender-input {
  width: auto;
  margin-left: 10px;
  margin-right: 10px;
}

</style>