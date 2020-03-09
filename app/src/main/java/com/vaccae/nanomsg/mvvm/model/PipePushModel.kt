package com.vaccae.nanomsg.mvvm.model

import android.util.Log
import com.vaccae.nanomsg.bean.VaccaeShare
import com.vaccae.vnanomsg.NNPIPEPUSH

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-28 13:23
 * 功能模块说明：
 */
class PipePushModel {
    val TAG = "PipePush";
    private var nnpipepush: NNPIPEPUSH? = null
    private var vaccae = VaccaeShare()

    //连接NanoMsg
    fun connectPush(pushurl: String): String {
        if (nnpipepush == null) {
            nnpipepush = NNPIPEPUSH()
        }
        var status = ""
        if (nnpipepush?.connect(pushurl)!!) {
            status = "PIPEPUSH连接成功！"
        } else {
            status = "PIPEPUSH连接失败！"
        }
        vaccae.sendmsg = status
        print()
        return status
    }

    //发送数据
    fun senddata(msg: String): String {
        if (nnpipepush != null) {
            val bytes = msg?.toByteArray()
            //发送数据
            val sendbytes = nnpipepush?.send(bytes!!)
            //赋值并显示打印
            vaccae.sendmsg = msg!!
            vaccae.sendbytes = sendbytes!!
            print()
        }
        return vaccae.sendmsg + " " + vaccae.sendbytes
    }

    fun print() {
        Log.i(
            TAG, "消息：" + vaccae.sendmsg + " 字节数:"
                    + vaccae.sendbytes
        )
    }
}