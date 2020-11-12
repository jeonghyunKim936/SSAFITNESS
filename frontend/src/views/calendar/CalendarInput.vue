<template>
<div class="d-block text-center">
    <table style="width:100%;">
        <tr>
            <td>운동시간</td>
            <td>
                시작 시간
                <vue-clock-picker mode="24" :defaultHour="startHour" :defaultMinute="startMinute" :onTimeChange="startTimeChangeHandler">
                </vue-clock-picker>
                <!-- <b-form-input v-model="start" type="time"></b-form-input> -->
            </td>
            <td style="width:100%;">~</td>
            <td style="width:100%;">
                끝 시간
                <vue-clock-picker mode="24" :defaultHour="endHour" :defaultMinute="endMinute" :onTimeChange="endTimeChangeHandler">
                </vue-clock-picker>
                <!-- <b-form-input v-model="end" type="time"></b-form-input> -->
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <hr>
            </td>
        </tr>
        <tr>
            <td>운동요약</td>
            <td class="border" colspan="3">
                <table style="width:100%;">
                    <tr style="text-align: left;">
                        <th class="t-1">
                            운동
                        </th>
                        <th>
                            무게
                        </th>
                        <th>
                            횟수
                        </th>
                        <th>
                            세트
                        </th>
                        <th class="t-button">

                        </th>
                    </tr>
                    <tr>
                        <td>
                            <b-form-select style="width:50%; display:inline;" v-model="part" :options="options"></b-form-select>
                            <b-form-select v-if="part!='직접입력'" style="width:50%; display:inline;" v-model="name" :options="options_detail"></b-form-select>
                            <b-form-input v-if="part=='직접입력'" style="width:50%; display:inline;" v-model="name" placeholder="운동명"></b-form-input>
                        </td>
                        <td>
                            <b-form-input v-model="weight" type="text" placeholder="무게" style="max-width:70px;"></b-form-input>
                        </td>
                        <td>
                            <b-form-input v-model="raps" type="text" placeholder="횟수" style="max-width:70px;"></b-form-input>
                        </td>
                        <td>
                            <b-form-input v-model="set" type="text" placeholder="세트" style="max-width:70px;"></b-form-input>
                        </td>
                        <td>
                            <b-button lass="t-button" variant="outline-success" v-on:click="clickExPlusButton()">+</b-button>
                        </td>
                    </tr>

                </table>
                <div v-for="(exercise,index) in exercises" v-bind:key="index" style="width:100%;">
        <tr>
            <td class="t-1">
                <b-form-input v-model="exercise.exercise_name" type="text" style="border-color:green" disabled></b-form-input>
            </td>
            <td>
                <b-form-input v-model="exercise.exercise_weight" type="text" style="max-width:70px;" disabled></b-form-input>
            </td>
            <td>
                <b-form-input v-model="exercise.exercise_raps" type="text" style="max-width:70px;" disabled></b-form-input>
            </td>
            <td>
                <b-form-input v-model="exercise.exercise_set" type="text" style="max-width:70px;" disabled></b-form-input>
            </td>
            <td class="t-button">
                <b-button variant="outline-danger" @click="clickExMinusButton(index)">-</b-button>
            </td>
        </tr>
</div>
</td>
</tr>
<tr>
    <td colspan="4">
        <hr>
    </td>
</tr>
<tr>
    <td>몸무게</td>
    <td colspan="3">
        <b-form-input type="text" v-model="myWeight"></b-form-input>
    </td>
</tr>
<tr>
    <td colspan="4">
        <hr>
    </td>
</tr>
<tr>
    <td>만족도</td>
    <td colspan="3">
        <b-form-rating v-model="star"></b-form-rating>
    </td>
</tr>
<tr>
    <td colspan="4">
        <hr>
    </td>
</tr>
<tr>
    <td>comment</td>
    <td colspan="3">
        <b-form-textarea v-model="text" placeholder="Enter something..." rows="3" max-rows="6"></b-form-textarea>
    </td>
</tr>
<tr>
    <td><br></td>
</tr>
<tr>
    <td></td>
    <td colspan="3">
        <b-button v-on:click="clickUpdate()">수정</b-button>
    </td>
</tr>
</table>
</div>
</template>

<script>
import axios from "axios";
import constants from '../../lib/constants.js'
import VueClockPicker from 'vue-clock-picker'

const resourceHost = constants.baseUrl

export default {
    components: {
        VueClockPicker
    },
    props: ['date', 'diary'],
    data() {
        return {
            startHour: new Date().getHours(),
            startMinute: new Date().getMinutes(),
            endHour: new Date().getHours(),
            endMinute: new Date().getMinutes(),
            star: null,
            text: '',
            start: null,
            end: null,
            part: null,
            name: null,
            weight: null,
            raps: null,
            set: null,
            myWeight: null,
            options: [{
                    value: null,
                    text: '운동 부위'
                },
                {
                    value: '가슴',
                    text: '가슴'
                },
                {
                    value: '하체',
                    text: '하체'
                },
                {
                    value: '등',
                    text: '등'
                },
                {
                    value: '어깨',
                    text: '어깨',
                },
                {
                    value: '팔',
                    text: '팔',
                },
                {
                    value: '직접입력',
                    text: '직접입력',
                }
            ],
            options_detail: [{
                value: null,
                text: '종류'
            }, ],
            exercises: [],
        }
    },
    created: function () {
        // 기존 데이터 입력
        let temp = []
        temp = this.diary[this.date.day]
        if (temp !== undefined) {
            this.$data.startHour = temp.exercise_start.split(':')[0] * 1
            this.$data.startMinute = temp.exercise_start.split(':')[1] * 1
            this.$data.endHour = temp.exercise_end.split(':')[0] * 1
            this.$data.endMinute = temp.exercise_end.split(':')[1] * 1
            this.$data.star = temp.diary_like
            this.$data.text = temp.diary_comment
            this.$data.myWeight = temp.weight
        }

        let sendDate = this.date.year + '/' + this.date.month + '/' + this.date.day + '/';
        axios.get(`${resourceHost}diary/exerciseList/` + sendDate, {
                params: {
                    nickname: sessionStorage.getItem("user_nickname")
                }
            })
            .then(({
                data
            }) => {
                //console.log("exercise select success")
                if (data == "") {
                    return
                }
                data.forEach(element => {
                    this.$data.exercises.push(element)
                });

            })
            .catch(
                //console.log("exercise select error")
                )
    },
    watch: {
        part: function (newVal, oldVal) {
            this.name = null
            if (newVal == "가슴") {
                this.options_detail = [{
                        value: null,
                        text: '종류'
                    },
                    {
                        value: "벤치프레스",
                        text: "벤치프레스"
                    },
                    {
                        value: "딥스",
                        text: "딥스"
                    },
                    {
                        value: "인클라인벤치프레스",
                        text: "인클라인벤치프레스"
                    },
                    {
                        value: "펙덱플라이_덤벨플라이",
                        text: "펙덱플라이_덤벨플라이"
                    },
                    {
                        value: "푸쉬업",
                        text: "푸쉬업"
                    },
                    {
                        value: "케이블크로스오버",
                        text: "케이블크로스오버"
                    },
                    {
                        value: "덤벨프레스",
                        text: "덤벨프레스"
                    },
                    {
                        value: "인클라인덤벨프레스",
                        text: "인클라인덤벨프레스"
                    },
                    {
                        value: "디클라인벤치프레스",
                        text: "디클라인벤치프레스"
                    },
                    {
                        value: "디클라인덤벨프레스",
                        text: "디클라인덤벨프레스"
                    },
                    {
                        value: "체스트프레스머신",
                        text: "체스트프레스머신"
                    },
                ]
            } else if (newVal == "하체") {
                this.options_detail = [{
                        value: null,
                        text: '종류'
                    },
                    {
                        value: "스쿼트",
                        text: "스쿼트"
                    },
                    {
                        value: "레그프레스",
                        text: "레그프레스"
                    },
                    {
                        value: "레그익스텐션",
                        text: "레그익스텐션"
                    },
                    {
                        value: "런지",
                        text: "런지"
                    },
                    {
                        value: "레그컬",
                        text: "레그컬"
                    },
                    {
                        value: "카프레이즈",
                        text: "카프레이즈"
                    },
                    {
                        value: "이너싸이",
                        text: "이너싸이"
                    },
                    {
                        value: "브릿지",
                        text: "브릿지"
                    },
                    {
                        value: "힙쓰러스트",
                        text: "힙쓰러스트"
                    },
                    {
                        value: "프론트스쿼트",
                        text: "프론트스쿼트"
                    },
                    {
                        value: "컨벤셔널데드리프트",
                        text: "컨벤셔널데드리프트"
                    },
                    {
                        value: "버피테스트",
                        text: "버피테스트"
                    },
                    {
                        value: "클린저크",
                        text: "클린저크"
                    },
                    {
                        value: "핵스쿼트",
                        text: "핵스쿼트"
                    },
                    {
                        value: "플러터킥",
                        text: "플러터킥"
                    },
                    {
                        value: "랙풀",
                        text: "랙풀"
                    },
                ]
            } else if (newVal == "등") {
                this.options_detail = [{
                        value: null,
                        text: '종류'
                    },
                    {
                        value: "데드리프트",
                        text: "데드리프트"
                    },
                    {
                        value: "원암덤벨로우",
                        text: "원암덤벨로우"
                    },
                    {
                        value: "바벨로우",
                        text: "바벨로우"
                    },
                    {
                        value: "풀업",
                        text: "풀업"
                    },
                    {
                        value: "티바로우머신",
                        text: "티바로우머신"
                    },
                    {
                        value: "티바로우바벨",
                        text: "티바로우바벨"
                    },
                    {
                        value: "랫풀다운",
                        text: "랫풀다운"
                    },
                    {
                        value: "시티드로우",
                        text: "시티드로우"
                    },
                    {
                        value: "백익스텐션",
                        text: "백익스텐션"
                    },
                    {
                        value: "펜들레이로우",
                        text: "펜들레이로우"
                    },
                    {
                        value: "친업",
                        text: "친업"
                    },
                ]
            } else if (newVal == "어깨") {
                this.options_detail = [{
                        value: null,
                        text: '종류'
                    },
                    {
                        value: "밀리터리프레스",
                        text: "밀리터리프레스"
                    },
                    {
                        value: "사이드래터럴레이즈",
                        text: "사이드래터럴레이즈"
                    },
                    {
                        value: "벤트오버바벨로우",
                        text: "벤트오버바벨로우"
                    },
                    {
                        value: "덤벨숄더프레스",
                        text: "덤벨숄더프레스"
                    },
                    {
                        value: "페이스풀",
                        text: "페이스풀"
                    },
                    {
                        value: "프론트레이즈",
                        text: "프론트레이즈"
                    },
                    {
                        value: "벤트오버레이즈",
                        text: "벤트오버레이즈"
                    },
                    {
                        value: "리버스펙덱플라이",
                        text: "리버스펙덱플라이"
                    },
                    {
                        value: "비하인드넥프레스",
                        text: "비하인드넥프레스"
                    },
                    {
                        value: "아놀드프레스",
                        text: "아놀드프레스"
                    },
                    {
                        value: "업라이트로우",
                        text: "업라이트로우"
                    },
                    {
                        value: "슈러그",
                        text: "슈러그"
                    },
                ]
            } else if (newVal == "팔") {
                this.options_detail = [{
                        value: null,
                        text: '종류'
                    },
                    {
                        value: "바벨컬",
                        text: "바벨컬"
                    },
                    {
                        value: "라잉트라이셉스익스텐션",
                        text: "라잉트라이셉스익스텐션"
                    },
                    {
                        value: "케이블푸쉬다운",
                        text: "케이블푸쉬다운"
                    },
                    {
                        value: "이두근_덤벨컬",
                        text: "이두근_덤벨컬"
                    },
                    {
                        value: "이두근_라잉케이블컬",
                        text: "이두근_라잉케이블컬"
                    },
                    {
                        value: "삼두근_클로즈그립프레스",
                        text: "삼두근_클로즈그립프레스"
                    },
                    {
                        value: "삼두근_케이블프레스다운",
                        text: "삼두근_케이블프레스다운"
                    },
                    {
                        value: "삼두근_덤벨오버헤드익스텐션",
                        text: "삼두근_덤벨오버헤드익스텐션"
                    },
                    {
                        value: "해머컬",
                        text: "해머컬"
                    },
                    {
                        value: "덤벨킥백",
                        text: "덤벨킥백"
                    },
                    {
                        value: "컨센트레이션컬",
                        text: "컨센트레이션컬"
                    },
                ]
            }
        }
    },
    methods: {
        startTimeChangeHandler(time) {
            let startHour = time.hour
            let startMinute = time.minute
            let endHour = this.$data.endHour
            let endMinute = this.$data.endMinute
            this.$data.startHour = time.hour
            this.$data.startMinute = time.minute
            if (startHour > endHour) {
                alert("시간이 적절하지 않습니다")
            }
            if (startHour == endHour && startMinute > endMinute) {
                alert("시간이 적절하지 않습니다")
            }
        },
        endTimeChangeHandler(time) {
            let startHour = this.$data.startHour
            let startMinute = this.$data.startMinute
            let endHour = time.hour
            let endMinute = time.minute
            this.$data.endHour = time.hour
            this.$data.endMinute = time.minute
            if (startHour > endHour) {
                alert("시간이 적절하지 않습니다")
            }
            if (startHour == endHour && startMinute > endMinute) {
                alert("시간이 적절하지 않습니다")
            }
        },
        clickExPlusButton: function () {
            let name = this.$data.name;
            let weight = this.$data.weight;
            let raps = this.$data.raps;
            let set = this.$data.set;
            // 운동종류
            if (name == null) {
                alert("운동을 선택해주세요");
                return;
            }
            this.$data.exercises.push({
                exercise_name: name,
                exercise_weight: weight,
                exercise_raps: raps,
                exercise_set: set,
            });

            this.$data.name = null;
            this.$data.weight = null;
            this.$data.raps = null;
            this.$data.set = null;
        },
        clickExMinusButton: function (index) {
            this.$data.exercises.splice(index, 1)
        },
        clickUpdate: function () {
            if (this.$data.myWeight == null) {
                alert("몸무게를 입력해주세요")
                return
            }
            //console.log(this.$data.startHour + " " + this.$data.endHour)
            if (this.$data.startHour > this.$data.endHour) {
                alert("시간이 적절하지 않습니다")
                return
            }
            if (this.$data.startHour == this.$data.endHour && this.$data.startMinute > this.$data.endMinute) {
                alert("시간이 적절하지 않습니다")
                return
            }

            let start_time = this.$data.startHour + ":" + this.$data.startMinute
            let end_time = this.$data.endHour + ":" + this.$data.endMinute

            axios.post(`${resourceHost}diary/`, {
                    diary_year: this.date.year,
                    diary_month: this.date.month,
                    diary_day: this.date.day,
                    exercise_start: start_time,
                    exercise_end: end_time,
                    diary_like: this.$data.star,
                    diary_comment: this.$data.text,
                    nickname: sessionStorage.getItem("user_nickname"),
                    weight: this.$data.myWeight,
                })
                .then(({
                    data
                }) => {
                    //console.log("diary success")
                    // exercise 처리
                    let sendDate = this.date.year + '/' + this.date.month + '/' + this.date.day + '/' + sessionStorage.getItem("user_nickname");
                    axios.post(`${resourceHost}diary/exercise/` + sendDate, this.$data.exercises)
                        .then(({
                            data
                        }) => {
                            //console.log("exercise success")
                            this.$emit('getMonth')
                        })
                        .catch(
                            //console.log("exercise error")
                            )
                        .finally(
                            alert("수정되었습니다.")
                        )
                })
                .catch(
                    //console.log("diary error")
                    )
        },
    },
}
</script>

<style>
.time-picker-modal-container {
    width: 100%;
    margin: 0 auto;
}

.outside-click-container {
    height: 0px;
}

th,
td {
    padding-left: 10px;
}

.btn-circle.btn-xl {
    width: 70px;
    height: 70px;
    padding: 10px 16px;
    border-radius: 35px;
    font-size: 24px;
    line-height: 1.33;
}

.btn-circle.btn-md {
    width: 50px;
    height: 50px;
    padding: 7px 10px;
    border-radius: 25px;
    font-size: 10px;
    text-align: center;
}

.btn-circle {
    width: 30px;
    height: 30px;
    padding: 6px 0px;
    border-radius: 15px;
    text-align: center;
    font-size: 12px;
    line-height: 1.42857;
}

.t-1 {
    width: 250px;
}

.t-button {
    width: 200px;
}
</style>
