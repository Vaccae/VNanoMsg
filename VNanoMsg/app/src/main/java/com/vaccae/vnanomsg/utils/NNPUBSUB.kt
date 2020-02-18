package com.vaccae.vnanomsg.utils

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-17 10:25
 * 功能模块说明：
 */
class NNPUBSUB {
    private var nnjni: NanoMsgJNI = NanoMsgJNI()
    private var pubsubsocket: Int = -1

    init {
        if (pubsubsocket == -1) {
            pubsubsocket = nnjni.init("PUBSUB")
        }
    }

    //绑定
    fun bind(ipadr: String): Boolean {
        return nnjni.bind(pubsubsocket, ipadr) >= 0
    }

    //连接
    fun connect(ipadr: String): Boolean {
        val res = nnjni.connect(pubsubsocket, ipadr)
        return res >= 0
    }

    //发送数据
    fun send(sendmsg: String): Int {
        return nnjni.send(pubsubsocket, sendmsg);
    }

    fun send(sendbyte: ByteArray): Int {
        return nnjni.sendbyte(pubsubsocket, sendbyte);
    }

    //接收数据
    fun recv(): String {
        return nnjni.recv(pubsubsocket)
    }

    fun recvbyte(): ByteArray {
        return nnjni.recvbyte(pubsubsocket)
    }

    //设置订阅前缀
    fun subscribe(string: String): Int {
        return nnjni.subscribe(pubsubsocket, string,0)
    }

    fun subscribe(byteArray: ByteArray): Int {
        return nnjni.subscribebyte(pubsubsocket, byteArray,0)
    }

    //取消订阅前缀
    fun unsubscribe(string: String): Int {
        return nnjni.subscribe(pubsubsocket, string,1)
    }

    fun unsubscribe(byteArray: ByteArray): Int {
        return nnjni.subscribebyte(pubsubsocket, byteArray,1)
    }
}