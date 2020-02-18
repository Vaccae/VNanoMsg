package com.vaccae.vnanomsg.utils

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-15 16:48
 * 功能模块说明：
 */
class NNBUS {
    private var nnjni: NanoMsgJNI = NanoMsgJNI()
    private var bussocket: Int = -1

    init {
        if (bussocket == -1) {
            bussocket = nnjni.init("BUS")
        }
    }

    //绑定
    fun bind(ipadr: String): Boolean {
        return nnjni.bind(bussocket, ipadr) >= 0
    }

    //连接
    fun connect(ipadr: String): Boolean {
        val res = nnjni.connect(bussocket, ipadr)
        return res >= 0
    }

    //发送数据
    fun send(sendmsg: String): Int {
        return nnjni.send(bussocket, sendmsg);
    }
    fun send(sendbyte: ByteArray): Int {
        return nnjni.sendbyte(bussocket, sendbyte);
    }

    //接收数据
    fun recv(): String {
        return nnjni.recv(bussocket)
    }
    fun recvbyte(): ByteArray{
        return nnjni.recvbyte(bussocket)
    }
}