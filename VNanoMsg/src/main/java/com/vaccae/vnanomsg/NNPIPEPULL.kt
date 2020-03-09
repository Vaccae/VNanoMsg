package com.vaccae.vnanomsg

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-25 16:59
 * 功能模块说明：
 */
class NNPIPEPULL : NNBaseInf {
    override var socketid = nnjni.init("PIPEPULL")

    override fun send(sendmsg: String): Int {
        return -1;
    }

    override fun send(sendbyte: ByteArray): Int {
        return -1;
    }
}