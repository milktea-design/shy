
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderOrderManager from "./components/listers/OrderOrderCards"
import OrderOrderDetail from "./components/listers/OrderOrderDetail"

import BillBillManager from "./components/listers/BillBillCards"
import BillBillDetail from "./components/listers/BillBillDetail"

import PayPayManager from "./components/listers/PayPayCards"
import PayPayDetail from "./components/listers/PayPayDetail"

import MypageMyPageManager from "./components/listers/MypageMyPageCards"
import MypageMyPageDetail from "./components/listers/MypageMyPageDetail"


export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders/orders',
                name: 'OrderOrderManager',
                component: OrderOrderManager
            },
            {
                path: '/orders/orders/:id',
                name: 'OrderOrderDetail',
                component: OrderOrderDetail
            },

            {
                path: '/bills/bills',
                name: 'BillBillManager',
                component: BillBillManager
            },
            {
                path: '/bills/bills/:id',
                name: 'BillBillDetail',
                component: BillBillDetail
            },

            {
                path: '/pays/pays',
                name: 'PayPayManager',
                component: PayPayManager
            },
            {
                path: '/pays/pays/:id',
                name: 'PayPayDetail',
                component: PayPayDetail
            },

            {
                path: '/mypages/myPages',
                name: 'MypageMyPageManager',
                component: MypageMyPageManager
            },
            {
                path: '/mypages/myPages/:id',
                name: 'MypageMyPageDetail',
                component: MypageMyPageDetail
            },



    ]
})
