package com.vaccae.vnanomsg

import com.vaccae.vnanomsg.utils.NanoMsgJNI

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-25 14:30
 * 功能模块说明：
 */
interface NNBaseInf {
    //创建NanoMsg的调用实例
    val nnjni: NanoMsgJNI
        get() = NanoMsgJNI()

    //对应的返回实列
    var socketid: Int

    //绑定
    fun bind(ipadr: String): Boolean {
        return nnjni.bind(socketid, ipadr) >= 0
    }

    //连接
    fun connect(ipadr: String): Boolean {
        val res = nnjni.connect(socketid, ipadr)
        return res >= 0
    }

    //关闭
    fun close(): Boolean {
        return nnjni.close(socketid) == 0
    }

    //发送数据
    fun send(sendmsg: String): Int {
        return nnjni.send(socketid, sendmsg);
    }

    fun send(sendbyte: ByteArray): Int {
        return nnjni.sendbyte(socketid, sendbyte);
    }

    //接收数据
    fun recv(): String? {
        return nnjni.recv(socketid)
    }

    fun recvbyte(): ByteArray? {
        return nnjni.recvbyte(socketid)
    }
}