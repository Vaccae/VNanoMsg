package com.vaccae.vnanomsg.utils

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-16 19:44
 * 功能模块说明：
 */
class NNREQREP {
    private var nnjni: NanoMsgJNI = NanoMsgJNI()
    private var reqrepsocket: Int = -1

    init {
        if (reqrepsocket == -1) {
            reqrepsocket = nnjni.init("REQREP")
        }
    }

    //绑定
    fun bind(ipadr: String): Boolean {
        return nnjni.bind(reqrepsocket, ipadr) >= 0
    }

    //连接
    fun connect(ipadr: String): Boolean {
        val res = nnjni.connect(reqrepsocket, ipadr)
        return res >= 0
    }

    //发送数据
    fun send(sendmsg: String): Int {
        return nnjni.send(reqrepsocket, sendmsg);
    }
    fun send(sendbyte: ByteArray): Int {
        return nnjni.sendbyte(reqrepsocket, sendbyte);
    }

    //接收数据
    fun recv(): String {
        return nnjni.recv(reqrepsocket)
    }
    fun recvbyte(): ByteArray{
        return nnjni.recvbyte(reqrepsocket)
    }
}