package com.vaccae.nanomsg.mvp.model

import android.util.Log
import com.vaccae.nanomsg.bean.VaccaeShare
import com.vaccae.nanomsg.mvp.contract.TransContract
import com.vaccae.vnanomsg.NNREQREP
import java.lang.ref.WeakReference

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-03-02 13:24
 * 功能模块说明：
 */
class ReqRepModel : TransContract.Model {
    val TAG = "reqrepdebug";
    private var nnreqrep: NNREQREP? = null
    private var vaccae = VaccaeShare()

    override fun connect(ipadr: String): String {
        if (nnreqrep == null) {
            nnreqrep = NNREQREP()
        }
        var status = ""
        if (nnreqrep?.connect(ipadr)!!) {
            status = "REQREP连接成功！"
        } else {
            status = "REQREP连接失败！"
        }
        vaccae.sendmsg = status
        return status
    }

    override fun send(msg: String): String? {
        var sendstr: String? = null
        if (nnreqrep != null) {
            val recvbyte = nnreqrep?.send(msg)
            if (recvbyte != null) {
                vaccae.sendmsg = msg
                vaccae.sendbytes = recvbyte
                sendstr = "发送消息：" + vaccae.sendmsg + " 字节数:" + vaccae.sendbytes
                print(sendstr)
            }
        }
        return sendstr
    }

    override fun recv(): String? {
        var recvstr: String? = null
        if (nnreqrep != null) {
            recvstr = nnreqrep?.recv()
            if (recvstr != null) {
                vaccae.recvmsg = recvstr
                print("接收消息：" + vaccae.recvmsg)
            }
        }
        return recvstr
    }

    fun print(msg: String) {
        Log.d(TAG, msg)
    }
}