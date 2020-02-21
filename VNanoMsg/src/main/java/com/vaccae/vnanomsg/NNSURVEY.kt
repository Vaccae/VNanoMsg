package com.vaccae.vnanomsg

import com.vaccae.vnanomsg.utils.NanoMsgJNI

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-17 15:30
 * 功能模块说明：
 */
class NNSURVEY {
    private var nnjni: NanoMsgJNI =
        NanoMsgJNI()
    private var surveysocket: Int = -1

    init {
        if (surveysocket == -1) {
            surveysocket = nnjni.init("SURVEY")
        }
    }

    //绑定
    fun bind(ipadr: String): Boolean {
        return nnjni.bind(surveysocket, ipadr) >= 0
    }

    //连接
    fun connect(ipadr: String): Boolean {
        val res = nnjni.connect(surveysocket, ipadr)
        return res >= 0
    }

    //发送数据
    fun send(sendmsg: String): Int {
        return nnjni.send(surveysocket, sendmsg);
    }
    fun send(sendbyte: ByteArray): Int {
        return nnjni.sendbyte(surveysocket, sendbyte);
    }

    //接收数据
    fun recv(): String {
        return nnjni.recv(surveysocket)
    }
    fun recvbyte(): ByteArray{
        return nnjni.recvbyte(surveysocket)
    }
}