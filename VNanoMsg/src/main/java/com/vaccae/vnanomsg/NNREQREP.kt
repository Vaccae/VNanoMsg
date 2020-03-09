package com.vaccae.vnanomsg

import com.vaccae.vnanomsg.utils.NanoMsgJNI

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-16 19:44
 * 功能模块说明：
 */
class NNREQREP:NNBaseInf {

    override var socketid: Int = nnjni.init("REQREP")

}