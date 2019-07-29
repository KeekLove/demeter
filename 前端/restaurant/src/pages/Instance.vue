<template>
    <div class="content">
        <div class="md-layout">
            <div
                    class="md-layout-item md-medium-size-100 md-xsmall-size-100 md-size-100"
            >
                <md-card>
                    <md-card-header data-background-color="green">
                        <h4 class="title">所有餐馆</h4>
                        <p class="category">包含有效和无效餐馆</p>
                    </md-card-header>
                    <md-card-content>
                        <md-table v-model="resList.list">
                            <md-table-row slot="md-table-row" slot-scope="{ item }" @click="alert(id)">
                                <md-table-cell  md-label="餐馆ID">{{ item.id }}</md-table-cell>
                                <md-table-cell md-label="餐馆图标">{{ item.icon }}</md-table-cell>
                                <md-table-cell md-label="营业执照">{{ item.registerId }}</md-table-cell>
                                <md-table-cell md-label="餐馆名">{{item.name}}</md-table-cell>
                                <md-table-cell md-label="用户电话">{{ item.phone }}</md-table-cell>
                                <md-table-cell md-label="关于">{{ item.about }}</md-table-cell>
                                <md-table-cell md-label="省">{{ item.province }}</md-table-cell>
                                <md-table-cell md-label="城市">{{ item.city }}</md-table-cell>
                                <md-table-cell md-label="街道">{{ item.address }}</md-table-cell>
                                <md-table-cell md-label="失败理由">{{ item.failReason }}</md-table-cell>
                                <md-table-cell md-label="更新时间">{{new Date(item.updateTime).toLocaleDateString()}}</md-table-cell>
                                <md-button @click="selectRes(item.id,item.card)">选择</md-button>
                            </md-table-row>
                        </md-table>
                    </md-card-content>
                    <md-card-actions class="md-layout">
                        <md-button class="md-info disabled">页码：{{orderList.pageNum}}</md-button>
                        <md-button class="md-layout-item md-size-49 md-warning" v-if="resList.pageNum != 1" @click="preResList">上一页</md-button>
                        <md-button class="md-layout-item md-size-49 md-primary" v-if="resList.hasNextPage" @click="nextResList">下一页</md-button>
                    </md-card-actions>
                </md-card>
            </div>
            <form class="md-layout-item md-size-100">
                <md-card>
                    <md-card-header :data-background-color="dataBackgroundColor">
                        <h4 class="title">更新餐馆信息</h4>
                        <p class="category">已选择{{aimedRes}}号餐厅</p>
                    </md-card-header>

                    <md-card-content>
                        <div class="md-layout" v-if="aimedRes>0">
                            <div class="md-layout-item md-small-size-100 md-size-100">
                                <md-label>修改授权码截止日期</md-label>
                                <md-datepicker v-model="selectedDate"/>
                                <md-button @click="updateDate">确定修改日期</md-button>
                            </div>
                            <div class="md-layout-item md-small-size-100 md-size-50">
                                <md-label>{{isActivate}}</md-label>
                                <br>
                                <md-button @click="genCard">生成授权码</md-button>
                                <md-button @click="freeze">冻结授权码</md-button>
                            </div>
                        </div>
                    </md-card-content>
                </md-card>
            </form>
        </div>
    </div>
</template>

<script>
import {
    StatsCard,
    ChartCard,
    NavTabsCard,
    NavTabsTable,
    OrderedTable
} from "@/components";

export default {
    components: {
        StatsCard,
        ChartCard,
        NavTabsCard,
        NavTabsTable,
        OrderedTable
    },
    methods:{
        nextOrderList(){
            this.changeOrderList(this.orderList.nextPage)
            this.aimedRes = 0;
        },
        prevOrderList(){
            this.changeOrderList(this.orderList.prePage)
            this.aimedRes = 0;
        },
        changeOrderList(pageNum){
            const token = this.$store.state.token;
            this.$axios.get("api/operationArea/packagesPage",{
                params: {
                    token:token,
                    pageNum:pageNum
                }
            }).then(res=>{
                console.log(res.data);
                this.order = res.data.list;
                this.orderList = res.data;
            })
        },

        nextResList(){
            this.changeResList(this.resList.nextPage)
            this.aimedRes = 0;
        },
        preResList(){
            this.changeResList(this.resList.prePage)
            this.aimedRes = 0;
        },
        changeResList(pageNum){
            const token = this.$store.state.token;
            this.$axios.get("api/operationArea/restaurantsPage",{
                params: {
                    token:token,
                    pageNum:pageNum
                }
            }).then(res=>{
                console.log(res.data);
                this.resList = res.data;
            })
        },

        selectRes(id,card){
            this.aimedRes = id;
            this.isActivate = card == null ? '此餐厅无授权码' : '此餐厅有激活码';
        },
        genCard(){
            this.$axios.put("api/operationArea/codeCard?token="+this.$store.state.token+"&resId="+this.aimedRes).then(res=>{
                if (res.data.affected>0){
                    /**
                     * TODO 弹出通知
                     */
                    console.log(res.data);
                    this.isActivate = "此餐厅有授权码"
                }
            })
        },
        freeze(){
            const token = this.$store.state.token;
            this.$axios.put("api/operationArea/nonCode?token="+this.$store.state.token+"&resId="+this.aimedRes).then(res=>{
                if (res.data.affected>0){
                    /**
                     * TODO 弹出通知
                     */
                    console.log(res.data);
                    this.isActivate = "此餐厅无授权码"
                    //更新列表
                    this.$axios.get("api/operationArea/restaurantsPage",{
                        params: {
                            token:token,
                            pageNum:1
                        }
                    }).then(res=>{
                        console.log(res.data)
                        this.resList = res.data
                    })
                }
            })
        },
        updateDate(){
            const token = this.$store.state.token;
            const date = this.selectedDate.toISOString().substr(0,10)
            console.log(token);
            console.log(this.aimedRes)
            this.$axios.put("api/operationArea/newDate?token="+this.$store.state.token+"&resId="+this.aimedRes+"&date="+date).then(res=>{
                if (res.data.affected>0){
                    /**
                     * TODO 弹出通知
                     */
                    console.log(res.data);
                    /**
                     * TODO 更新列表
                     */
                }
            })
        }
    },
    mounted() {
        const token = this.$store.state.token;
        this.$axios.get("api/card/listCard?token=token",{
            params: {
                pageNum:"1",
                pageSize:"3"
            }
        }).then(res=>{
            console.log(res.data)
            this.resList = res.data
        })

    }
    ,
    data() {
        return {
            orderList:{},
            order: [],

            resList:{},

            selectedDate:null,
            aimedRes:0,
            isActivate:'',
        };
    }
};
</script>
