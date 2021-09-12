package com.vaccae.vnanomsg

import com.vaccae.vnanomsg.utils.NanoMsgJNI

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-17 15:30
 * 功能模块说明：
 */
class NNSURVEY : NNBaseInf {

    override var socketid = nnjni.init("SURVEY")

    override var bindid: Int = -1

    override var connectid: Int = -1

}