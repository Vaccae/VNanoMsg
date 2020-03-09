package com.vaccae.nanomsg.mvp.model

import android.util.Log
import com.vaccae.nanomsg.bean.VaccaeShare
import com.vaccae.nanomsg.mvp.contract.TransContract
import com.vaccae.vnanomsg.NNPIPEPULL

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-28 13:44
 * 功能模块说明：
 */
class PipePullModel : TransContract.Model {
    val TAG = "PipePull";
    private var nnpipepull: NNPIPEPULL? = null
    private var vaccae = VaccaeShare()

    //连接NanoMsgPull模式
    override fun connect(ipadr: String): String? {
        if (nnpipepull == null) {
            nnpipepull = NNPIPEPULL()
        }
        var status = ""
        if (nnpipepull?.bind(ipadr)!!) {
            status = "PIPEPULL连接成功！"
        } else {
            status = "PIPEPULL连接失败！"
        }
        vaccae.sendmsg = status
        print()
        return status
    }

    //PULL模式不允许发送数据
    override fun send(msg: String): String? {
        return "Pull模式不允许发送数据"
    }

    //PULL模式接收数据
    override fun recv(): String? {
        var recvstr: String? = null
        if (nnpipepull != null) {
//            recvstr = nnpipepull?.recv()
            val recvbyte = nnpipepull?.recvbyte()
            if (recvbyte != null) {
                recvstr = recvbyte.toString(charset = Charsets.UTF_8)
            }
            if (recvstr != null) {
                vaccae.recvmsg = recvstr
//                print()
            }
        }
        return recvstr
    }

    fun print() {
        Log.i(
            TAG, "消息：" + vaccae.recvmsg + " 字节数:"
                    + vaccae.recvbytes
        )
    }
}