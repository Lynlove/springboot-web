<template>
    <div id="app">
        <div class="container">
            <AutoComplete
                v-model="model"
                :data="data"
                @on-search="onChange"
                placeholder="输入待查询专业"
                style="width:300px; margin-bottom: 10px" />
            <Button 
                style="margin-bottom: 10px" 
                type="primary" 
                @click="search">
                查询
            </Button>
            <Card 
                v-if="result != null"
                style="width:400px; margin-top: 10px; transition: 0.3s">
                <p slot="title">查询结果</p>
                <div>
                    <p class="card-item">学院：{{result.name}}</p>
                    <p class="card-item">QQ群号：{{result.QQ_Group}}</p>
                    <p class="card-item">辅导员：{{result.teacher}}</p>
                    <p class="card-item">辅导员联系方式：{{result.tel}}</p>
                    <p class="card-item">专业：{{result.sub}}</p>
                </div>
            </Card>
        </div>
        <div class="footer">
            如查询有误可私聊
            QQ：3156508910
        </div>
    </div>
</template>

<script>

export default {
    name: 'App',
    data() {
        return {
            data: [],
            model: "",
            result: null
        }
    },
    methods: {
        onChange() {
            this.data = []
            let colleges = this.$store.state.college
            colleges.forEach(item => {
                let subjects = item.subject;
                subjects.forEach(sub => {
                    if (sub.indexOf(this.model) != -1) {
                        this.data.push(sub)
                    }
                })
            });
        },
        search() {
            let colleges = this.$store.state.college
            colleges.forEach(item => {
                let subjects = item.subject;
                subjects.forEach(sub => {
                    if (sub === this.model) {
                        this.result = item
                        this.result.sub = sub
                        return
                    }
                })
            });
        }
    }
}
</script>

<style lang='css'>
@import "./App.css";
</style>