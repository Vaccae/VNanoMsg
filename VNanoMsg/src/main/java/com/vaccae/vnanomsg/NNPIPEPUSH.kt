package com.vaccae.vnanomsg

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-25 16:58
 * 功能模块说明：
 */
class NNPIPEPUSH : NNBaseInf {
    override var socketid = nnjni.init("PIPEPUSH")

    override fun recv(): String {
        return ""
    }

    override fun recvbyte(): ByteArray {
        return ByteArray(0)
    }
}