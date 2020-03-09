package com.vaccae.vnanomsg

import com.vaccae.vnanomsg.utils.NanoMsgJNI

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-17 10:25
 * 功能模块说明：
 */
class NNPUBSUB :NNBaseInf {

    override var socketid: Int = nnjni.init("PUBSUB")

    //设置订阅前缀
    fun subscribe(string: String): Int {
        return nnjni.subscribe(socketid, string,0)
    }

    fun subscribe(byteArray: ByteArray): Int {
        return nnjni.subscribebyte(socketid, byteArray,0)
    }

    //取消订阅前缀
    fun unsubscribe(string: String): Int {
        return nnjni.subscribe(socketid, string,1)
    }

    fun unsubscribe(byteArray: ByteArray): Int {
        return nnjni.subscribebyte(socketid, byteArray,1)
    }
}