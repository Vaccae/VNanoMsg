package com.vaccae.nanomsg.mvp.presenter

import com.vaccae.nanomsg.mvp.view.REQREPActivity
import com.vaccae.nanomsg.mvp.contract.TransContract
import com.vaccae.nanomsg.mvp.model.ReqRepModel
import java.lang.ref.WeakReference

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-03-02 13:52
 * 功能模块说明：
 */
class ReqRepPresenter(reqrepActivity: REQREPActivity) : TransContract.Presenter {

    private var reqRepModel = ReqRepModel()
    private var context = reqrepActivity
    var threadstat = true

    override fun ConnectNano(ipadr: String) {
        try {
            val status = reqRepModel.connect(ipadr)
            context.handlerResult(status + "\r\n")
        } catch (e: IllegalArgumentException) {
            context.handlerResult(e.toString() + "\r\n")
        }
    }

    override fun TransMsg(msg: String) {
        var count = 1
        Thread {
            while (threadstat) {
                try {
                    Thread.sleep(1000)
                    val sendmsg = reqRepModel.send(msg + count)
                    if (sendmsg != null) {
                        context.handlerResult(sendmsg + "\r\n")
                    }
                    val recvmsg = reqRepModel.recv()
                    if (recvmsg != null) {
                        context.handlerResult(recvmsg + "\r\n")
                    }
                    count++
                } catch (e: IllegalArgumentException) {
                    context.handlerResult(e.message.toString() + "\r\n")
                } catch (e: Exception) {
                    context.handlerResult(e.message.toString() + "\r\n")
                }
            }
        }.start()
    }
}