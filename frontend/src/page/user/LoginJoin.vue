<template>
  <div class="container" id="container" style="z-index:0;">
    <div class="form-container sign-up-container">
      <div class="form-wrap">
        <h1>회원가입</h1>
        <label for="inputNicknameIcon"><v-icon class="signup-icon">mdi-account</v-icon></label>
        <input class="square" v-model="userData.nickname"
          id="inputNicknameIcon"
          placeholder="별명" type="text" required/> <br>
        <label for="inputEmailIcon"><v-icon class="signup-icon">mdi-email</v-icon></label>
        <input class="square" v-model="userData.email" 
          id="inputEmailIcon"
          placeholder="이메일"
          type="text" required/> <br>
        <label for="inputPasswordIcon"><v-icon class="signup-icon">mdi-lock-outline</v-icon></label>
        <input class="square" v-model="userData.password"
          id="inputPasswordIcon" 
          type="password"
          placeholder="비밀번호" required/> <br>
        <span class="input-sub-placeholeder">비밀번호는 대,소문자,특수문자,숫자를 포함한 8~12자리로 입력해주세요</span> <br>
        <label for="inputPasswordCheckIcon"><v-icon class="signup-icon">mdi-lock</v-icon></label>
        <input class="square" v-model="userData.passwordConfirm" 
          id="inputPasswordCheckIcon"
          type="password"
          placeholder="비밀번호 확인" required/> <br>
        <label for="inputHeightIcon"><v-icon class="signup-icon">mdi-human-male-height</v-icon></label>
        <input class="square input-number" v-model="userData.height" 
          id="inputHeightIcon"
          type="number"
          placeholder="키" min="1" required/> <br>
        <label for="inputWeightIcon"><v-icon class="signup-icon">mdi-scale-bathroom</v-icon></label>
        <input class="square input-number" v-model="userData.weight" 
          id="inputWeightIcon"
          type="number"
          placeholder="몸무게" min="1" required/> <br>
        <label for="genderMale">남</label>
        <input type="radio" id="genderMale" class="gender-input" name="gender" value="m" v-model="userData.gender" required>
        <label for="genderFemale">여</label>
        <input type="radio" id="genderFemale" class="gender-input" name="gender" value="f" v-model="userData.gender"> <br>
        <button class="signup-button" @click="onSignup">회원가입</button>
      </div>
    </div>
    <div class="form-container sign-in-container">
      <div class="form-wrap">
        <h1>로그인</h1>
        <label for="inputEmail"><v-icon class="signup-icon">mdi-email</v-icon></label>
        <input class="square" v-model="userData.email" 
          id="inputEmail"
          placeholder="이메일"
          type="text" required/> <br>
        <label for="inputPassword"><v-icon class="signup-icon">mdi-lock-outline</v-icon></label>
        <input class="square" v-model="userData.password"
          id="inputPassword" 
          type="password"
          placeholder="비밀번호" required/> <br>
        <a href="#">비밀번호를 잊으셨나요?</a> <br>
        <button class="signin-button" @click="onSignin">로그인</button>
      </div>
    </div>
    <div class="overlay-container">
      <div class="overlay">
        <div class="overlay-panel overlay-left">
          <h1>회원님!</h1>
          <p>이미 가입하신 정보가 있다면 로그인 해주세요</p>
          <button @click="onSigninButtonGhost" class="ghost" id="signIn">로그인</button>
        </div>
        <div class="overlay-panel overlay-right">
          <h1>어서오세요!</h1>
          <p>지금 회원가입하여 SSAFITNESS와 함께 즐겁게 운동해요</p>
          <button @click="onSignupButtonGhost" class="ghost" id="signUp">회원가입</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import constants from '../../lib/constants'


export default {
  name: 'LoginJoin',
  data() {
    return {
      userData: {
        email: '',
        nickname: '',
        password: '',
        passwordConfirm: '',
        height: '',
        weight: '',
        gender: '',
      },
      isTerm: false,
      constants,
      msg: '',
    }
  },
  methods: {
    onSignupButtonGhost() {
      const container = document.getElementById('container');
      container.classList.add("right-panel-active");
    },
    onSigninButtonGhost() {
      const container = document.getElementById('container');
      container.classList.remove("right-panel-active");
	  },
	  onSignup() {
      if(this.userData.password !== this.userData.passwordConfirm){
        alert('비밀 번호가 틀립니다!')
      }else{
        this.$store.dispatch('JOIN', {
        nickname: this.userData.nickname,
        email: this.userData.email,
        password: this.userData.password,
        passwordConfirm: this.userData.passwordConfirm,
        height: this.userData.height,
        weight: this.userData.weight,
        gender: this.userData.gender,
      })
        .then(() => {
          this.runSignin();
        })
        .catch(() =>{
          //console.log("test");
        })
      }
	  },
    onSignin() {
      this.$store.dispatch('LOGIN', {
        email: this.userData.email,
        password: this.userData.password,
      })
        .then(() => {
          this.userData.email = ''
          this.userData.password = ''
          this.$router.push({name:constants.URL_TYPE.POST.MAIN})
        })
        .catch(({message}) => {
          this.msg = message;
          alert("이메일 및 비밀번호를 확인해주세요");
        })
    },
    // store의 MadeJoin 변수가 True가 되엇을 때(join을 마쳣을때)를 watch해서 로그인 실행
    runSignin(){
      this.$store.watch(
        (state)=>{
            return this.$store.getters.getMadejoin 
        },
        ()=>{
            // 로그인 실행
            this.onSignin();
            //location.reload;
        },
        )
    },
  }
}
</script>

<style scoped>

@import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');

* {
	box-sizing: border-box;
}

body {
	background: #f6f5f7;
	font-family: 'Montserrat', sans-serif;
	margin: -20px 0 50px;
}

h1 {
	font-weight: bold;
	margin: 0;
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

button {
	border-radius: 20px;
  border: 1px solid #FFBD07;
	background-color: #FFBD07;
	color: #FFFFFF;
	font-size: 12px;
	font-weight: bold;
	padding: 12px 45px;
	letter-spacing: 1px;
	text-transform: uppercase;
	transition: transform 80ms ease-in;
}

button:active {
	transform: scale(0.95);
}

button:focus {
	outline: none;
}

button.ghost {
	background-color: transparent;
	border-color: #FFBD07;
}

button.ghost:hover {
  color:#FFBD07;
}

.form-wrap {
	background-color: #FFFFFF;
	padding: 0 50px;
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
	position: absolute;
	top: 0;
	height: 100%;
	transition: all 0.6s ease-in-out;
}

.sign-in-container {
	left: 0;
	width: 50%;
  z-index: 2;
  padding-top: 160px;
}

.container.right-panel-active .sign-in-container {
	transform: translateX(100%);
}

.sign-up-container {
	left: 0;
	width: 50%;
	opacity: 0;
  z-index: 1;
  padding-top: 45px;
}

.container.right-panel-active .sign-up-container {
	transform: translateX(100%);
	opacity: 1;
	z-index: 5;
	animation: show 0.6s;
}

@keyframes show {
	0%, 49.99% {
		opacity: 0;
		z-index: 1;
	}
	
	50%, 100% {
		opacity: 1;
		z-index: 5;
	}
}

.overlay-container {
	position: absolute;
	top: 0;
	left: 50%;
	width: 50%;
	height: 100%;
	overflow: hidden;
	transition: transform 0.6s ease-in-out;
	z-index: 100;
}

.container.right-panel-active .overlay-container{
	transform: translateX(-100%);
}

.overlay {
  background: #000000;
	background: -webkit-linear-gradient(to right, #5e5e5e, #232323);
	background: linear-gradient(to right, #5e5e5e, #232323);
	background-repeat: no-repeat;
	background-size: cover;
	background-position: 0 0;
	color: #FFFFFF;
	position: relative;
	left: -100%;
	height: 100%;
	width: 200%;
  transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay-panel {
	position: absolute;
	display: flex;
	align-items: center;
	justify-content: center;
	flex-direction: column;
	padding: 0 40px;
	text-align: center;
	top: 0;
	height: 100%;
	width: 50%;
	transform: translateX(0);
	transition: transform 0.6s ease-in-out;
}

.overlay-left {
	transform: translateX(-20%);
}

.container.right-panel-active .overlay-left {
	transform: translateX(0);
}

.overlay-right {
	right: 0;
	transform: translateX(0);
}

.container.right-panel-active .overlay-right {
	transform: translateX(20%);
}

.gender-input {
  width: auto;
  margin-left: 10px;
  margin-right: 10px;
}

.signup-icon {
  margin-right: 10px;
}

.signin-button {
	margin: 20px;
}
.input-sub-placeholeder {
  font-size: 9px;
}
</style>