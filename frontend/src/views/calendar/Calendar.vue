<template>
<div class="calendar">
    <div>
        <b-modal size="lg" ref="graph-modal" hide-footer title="몸무게 변화량">
            <CalendarGraph v-bind:diaryList=diaryList v-bind:days=days v-bind:weights=weights v-bind:min_weight=min_weight ></CalendarGraph>
        </b-modal>
    </div>
    <br>
    <div style="text-align:right; margin:0 auto; width:95%">
        몸무게 변화량 확인 ▷ 
        <span class="graph" @click="showGraph">
            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-graph-up" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                <path d="M0 0h1v16H0V0zm1 15h15v1H1v-1z" />
                <path fill-rule="evenodd" d="M14.39 4.312L10.041 9.75 7 6.707l-3.646 3.647-.708-.708L7 5.293 9.959 8.25l3.65-4.563.781.624z" />
                <path fill-rule="evenodd" d="M10 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 .5.5v4a.5.5 0 0 1-1 0V4h-3.5a.5.5 0 0 1-.5-.5z" />
            </svg>
        </span>
    </div>
    <h2>
        <a href="#" v-on:click="onClickPrev(currentMonth)">◀</a>
        {{currentYear}}년 {{currentMonth}}월
        <a href="#" v-on:click="onClickNext(currentMonth)">▶</a>
    </h2>
    <table class="table" style="table-layout:fixed;">
        <thead>
            <tr>
                <td v-for="(weekName, index) in weekNames" v-bind:key="index">
                    {{weekName}}
                </td>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(row, index) in currentCalendarMatrix" :key="index" style="height:100px;">
                <td class="border hover" v-for="(day, index2) in row" :key="index2" style="padding:5px; text-align:right;" v-on:click="onClickDay(currentYear,currentMonth,day)">
                    <span>
                        {{day}}
                    </span>
                    <!-- <span v-if="isToday(currentYear, currentMonth, day)" class="rounded">
                        {{day}}
                    </span>
                    <span v-else>
                        {{day}}
                    </span> -->
                    <div style="text-align:left;" v-if="exercises[day] != null">
                        <p class="time" v-if="time[day] != null && time[day] != 0">
                            <span class="time-pre">'</span> {{time[day]}}분
                        </p>
                        <p class="exercise" v-for="(row, index) in exercises[day]" :key="index">
                            <span class="exercise-pre">'</span> {{row.exercise_name}} {{row.exercise_raps}}
                        </p>
                    </div>
                </td>
            </tr>
        </tbody>
    </table>
    <!-- 모달 창 -->
    <b-modal size="lg" ref="detail-modal" hide-footer>
        <CalendarInput v-bind:date=date v-bind:diary=diary @getMonth="getMonthDiary"></CalendarInput>
    </b-modal>
</div>
</template>

<script>
import CalendarInput from './CalendarInput.vue'
import CalendarGraph from './CalendarGraph.vue'
import axios from "axios";
import constants from '../../lib/constants.js'
import moment from "moment"
import VueClockPicker from 'vue-clock-picker'
const resourceHost = constants.baseUrl

import {
    Line
} from 'vue-chartjs'

export default {
    extends: Line,
    props: ['data', 'options'],
    name: 'Calendar',
    components: {
        CalendarInput,
        CalendarGraph
    },
    data() {
        return {
            weekNames: ["월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"],
            rootYear: 1904,
            rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
            currentYear: new Date().getFullYear(),
            currentMonth: new Date().getMonth() + 1,
            currentDay: new Date().getDate(),
            currentMonthStartWeekIndex: null,
            currentCalendarMatrix: [],
            endOfDay: null,
            memoDatas: [],
            date: {
                year: String,
                month: String,
                day: String,
            },
            diary: {},
            exercises: {},
            time: {},
            diaryList: {},
            days: [],
            weights: [],
            min_weight: 100,
        }
    },
    mounted: function () {
        this.init();
        this.getMonthDiary();
        var sendDate = 1 + '/' + 1 + '/'
        axios.get(`${resourceHost}diary/` + sendDate + 'ListLatest', {
                params: {
                    nickname: sessionStorage.getItem("user_nickname")
                }
            })
            .then(({
                data
            }) => {
                //console.log("diary latest success")
                data.forEach(element => {
                    this.days.unshift(element.diary_month + "월 " + element.diary_day + "일")
                    this.weights.unshift(element.weight)
                    if (this.min_weight > element.weight) {
                        this.min_weight = element.weight
                    }
                });
            })
            .catch(
                //console.log("diary latest error")
                )

    },
    created: function () {
        if (sessionStorage.getItem("user_nickname") == "") {
            alert("로그인 해주세요")
            this.$router.push("/user/loginjoin/");
        }
    },
    updated: function () {

    },
    methods: {
        showGraph() {
            this.$refs['graph-modal'].show()
        },
        init: function () {
            this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
            this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
            this.initCalendar();
        },
        getMonthDiary: function () {
            this.$data.exercises = {}
            this.$data.time = {}
            let sendDate = this.$data.currentYear + '/' + this.$data.currentMonth + '/';
            axios.get(`${resourceHost}diary/` + sendDate, {
                    params: {
                        nickname: sessionStorage.getItem("user_nickname")
                    }
                })
                .then(({
                    data
                }) => {
                    //console.log("diary month success")
                    this.$data.diary = {};
                    data.forEach(element => {
                        this.$data.diary[element.diary_day] = element
                        var t1 = moment(element.exercise_start + "", 'HH:mm')
                        var t2 = moment(element.exercise_end + "", 'HH:mm')
                        var time = moment.duration(t2.diff(t1)).asMinutes()
                        if (!isNaN(time)) {
                            this.$data.time[element.diary_day] = time
                        }
                        axios.get(`${resourceHost}diary/exerciseList/` + sendDate + element.diary_day + '/', {
                                params: {
                                    nickname: sessionStorage.getItem("user_nickname")
                                }
                            })
                            .then(({
                                data
                            }) => {
                                //console.log("exercise month sucess")
                                this.$set(this.$data.exercises, element.diary_day, data)
                            })
                            .catch(
                                //console.log("exercise month error")
                                )
                    });
                    //console.log(this.$data.diary)
                })
                .catch(
                    //console.log("diary month error")
                    )
        },
        initCalendar: function () {
            this.currentCalendarMatrix = [];
            let day = 1;
            for (let i = 0; i < 6; i++) {
                let calendarRow = [];
                for (let j = 0; j < 7; j++) {
                    if (i == 0 && j < this.currentMonthStartWeekIndex) {
                        calendarRow.push("");
                    } else if (day <= this.endOfDay) {
                        calendarRow.push(day);
                        day++;
                    } else {
                        calendarRow.push("");
                    }
                }
                this.currentCalendarMatrix.push(calendarRow);
            }
        },
        getEndOfDay: function (year, month) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    return 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    return 30;
                    break;
                case 2:
                    if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                        return 29;
                    } else {
                        return 28;
                    }
                    break;
                default:
                    //console.log("unknown month " + month);
                    return 0;
                    break;
            }
        },
        getStartWeek: function (targetYear, targetMonth) {
            let year = this.rootYear;
            let month = 1;
            let sumOfDay = this.rootDayOfWeekIndex;
            while (true) {
                if (targetYear > year) {
                    for (let i = 0; i < 12; i++) {
                        sumOfDay += this.getEndOfDay(year, month + i);
                    }
                    year++;
                } else if (targetYear == year) {
                    if (targetMonth > month) {
                        sumOfDay += this.getEndOfDay(year, month);
                        month++;
                    } else if (targetMonth == month) {
                        return (sumOfDay) % 7;
                    }
                }
            }
        },
        onClickPrev: function (month) {
            month--;
            if (month <= 0) {
                this.currentMonth = 12;
                this.currentYear -= 1;
            } else {
                this.currentMonth -= 1;
            }
            this.init();
            this.getMonthDiary();
        },
        onClickNext: function (month) {
            month++;
            if (month > 12) {
                this.currentMonth = 1;
                this.currentYear += 1;
            } else {
                this.currentMonth += 1;
            }
            this.init();
            this.getMonthDiary();
        },
        onClickDay: function (currentYear, currentMonth, day) {
            if(day == "") {
                return
            }
            this.$data.date.year = currentYear
            this.$data.date.month = currentMonth
            this.$data.date.day = day
            this.$refs['detail-modal'].title = currentYear + "/" + currentMonth + "/" + day
            this.$refs['detail-modal'].show()
        },
        isToday: function (year, month, day) {
            let date = new Date();
            return year == date.getFullYear() && month == date.getMonth() + 1 && day == date.getDate();
        },
    }
}
</script>

<style>
.hover:hover {
    background: silver;
}

.graph {
    background: #FFEA99;
    font-size: 30px;
    cursor: pointer;
}

.time {
    background: #9AECEA;
    margin: 0 auto;
}

.time-pre {
    background: blue;
    color: blue;
}

.exercise {
    background: #DCEEF2;
    margin: 0 auto;
}

.exercise-pre {
    background: red;
    color: red;
}

.calendar {
    background: #ffffff;
    width: 80%;
    margin: 0 auto;
}

.rounded {
    -moz-border-radius: 20px 20px 20px 20px;
    border-radius: 20px 20px 20px 20px;
    border: solid 1px #ffffff;
    background-color: #2b6bd1;
    padding: 10px;
    color: #ffffff;
}
</style>
