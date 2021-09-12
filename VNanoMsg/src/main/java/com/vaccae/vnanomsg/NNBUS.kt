package com.vaccae.vnanomsg

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-25 14:36
 * 功能模块说明：
 */
class NNBUS : NNBaseInf {
    override var socketid: Int = nnjni.init("BUS")

    override var bindid: Int = -1

    override var connectid: Int = -1
}