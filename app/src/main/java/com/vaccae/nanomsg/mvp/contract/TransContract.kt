package com.vaccae.nanomsg.mvp.contract

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-27 17:52
 * 功能模块说明：
 */
interface TransContract {

    interface Model {
        //连接
        fun connect(ipadr: String): String?

        //发送
        fun send(msg: String): String?

        //接收
        fun recv(): String?
    }

    interface View {
        //通讯结果与UI交互
        fun handlerResult(recvmsg: String)
    }

    interface Presenter {
        //连接程序
        fun ConnectNano(ipadr: String)

        //数据通讯
        fun TransMsg(msg: String)
    }
}