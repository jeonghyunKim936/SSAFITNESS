<template>
<div class="board">
    <br><br>
    <h1>자유 게시판</h1>
    <br>
    <div class="editor">
        <b-form-input v-model="title" type="text" placeholder="제목을 입력해주세요."></b-form-input>
        <br>
        <ckeditor :editor="editor" v-model="editorData" :config="editorConfig"></ckeditor>
    </div>
    <br>
    <h4>태그</h4>
    <b-form-input style="width:80%; margin: 0 auto;" type="text" placeholder="쉼표(,)를 입력하여 복수 등록"></b-form-input>
    <br>
    <div style="text-align:right; width:80%; margin: 0 auto;">
        <b-button pill variant="outline-secondary">임시저장</b-button>
        &nbsp;&nbsp;
        <b-button pill variant="outline-secondary">불러오기</b-button>
        &nbsp;&nbsp;
        <b-button pill variant="primary" @click="clickPostButton()">등록</b-button>
    </div>
    <br><br>
</div>
</template>

<script>
import axios from 'axios'
import Vue from 'vue';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import CKEditor from '@ckeditor/ckeditor5-vue';
import constants from '../../lib/constants.js'

const resourceHost = constants.baseUrl

Vue.use(CKEditor);
export default {
    name: 'simple-pagination',
    components: {
    },
    data() {
        return {
            link: document.location.href,
            pageArray: [],
            editor: ClassicEditor,
            editorData: '<p>Content of the editor.</p>',
            editorConfig: {
                filebrowserUploadUrl: 'localhost:8080/ckeditor/fileupload',
            },
            title: null,
        }
    },
    created: function () {
        // axios.get('http://sample.bmaster.kro.kr/contacts')
        // .then(response => {
        //   console.log(response);
        //   this.pageArray = response.data.contacts;
        // })
        // .catch(err => {
        //   console.log(err);
        // });
    },
    methods: {
        clickPostButton: function () {
            let title = this.$data.title;
            let name = this.$store.state.nickname;
            let content = this.$data.editorData;

            if (title == null) {
                alert("제목을 입력해주세요")
                return
            }
            axios.post(`${resourceHost}qna/add`, {
                    qnaTitle : title,
                    qnaName : name,
                    qnaContent : content,
                })
                .then(({
                    data
                }) => {
                    alert("등록되었습니다.");
                    this.$router.push('/notice'); //성공 했다면 리스트 페이지로
                })
                .catch(
                    //console.log("notice input error")
                    )
        }
    }
}
</script>

<style scoped>
.editor {
    width: 80%;
    margin: 0 auto;
}

.id {
    border: 1px solid #ADD9D1;
    background-color: #ADD9D1;
    padding: 10px;
    border-radius: 5px;
}

.text {
    padding-left: 10px;
}

.comment {
    width: 80%;
    margin: 0 auto;
    text-align: left;
    line-height: 0.7em
}

.info {
    padding-left: 0px;
    width: 80%;
    border-top: 3px ridge #44A69C;
    background-color: #ADD9D1;
    height: 0px;
}

.board {
    margin: 0 auto;
    width: 80%;
    height: 100%;
    background-color: white;
}

.url {
    margin: 0 auto;
    width: 80%;
    text-align: left;
}

.content {
    margin: 0 auto;
    width: 80%;
    min-height: 100px;
}

.content-footer {
    margin: 0 auto;
    width: 80%;
    text-align: left;
}

hr {
    margin: 0 auto;
    width: 80%;
}

h1 {
    font-weight: bold;
    color: #44A69C;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h3 {
    font-weight: bold;
    color: #000000;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h4 {
    font-weight: bold;
    color: #44A69C;
    width: 80%;
    margin: 0 auto;
    text-align: left;
}

h5:hover {
    color: #000000;
    cursor: pointer;
}

.ck.ck-editor {
    width: 80%;
    margin: 0 auto;
}

.ck-editor__editable {
    min-height: 300px;
}
</style>
