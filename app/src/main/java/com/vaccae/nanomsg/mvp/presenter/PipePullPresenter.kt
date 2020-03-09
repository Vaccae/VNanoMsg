package com.vaccae.nanomsg.mvp.prsenter

import android.app.Activity
import android.content.Context
import com.vaccae.nanomsg.bean.VaccaeShare
import com.vaccae.nanomsg.mvp.contract.TransContract
import com.vaccae.nanomsg.mvp.model.PipePullModel
import com.vaccae.nanomsg.mvp.view.PipePullActivity
import java.lang.Exception
import java.util.concurrent.ExecutionException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-28 14:03
 * 功能模块说明：
 */
class PipePullPresenter(pipePullActivity: PipePullActivity) : TransContract.Presenter {

    private var context = pipePullActivity
    private var pipePullModel = PipePullModel()

    //线程状态
    var threadstat = true

    //连接
    override fun ConnectNano(ipadr: String) {
        try {
            val status = pipePullModel.connect(ipadr)
            context.handlerResult(status + "\r\n")
            TransMsg("开启线程接收数据" + "\r\n")
        } catch (e: IllegalArgumentException) {
            context.handlerResult(e.toString() + "\r\n")
        }
    }

    //数据通讯
    override fun TransMsg(msg: String) {
        context.handlerResult(msg)

        Thread {
            while (threadstat) {
                try {
                    Thread.sleep(1000)
                    val recvmsg = pipePullModel.recv()
                    if (recvmsg != null) {
                        context.handlerResult(recvmsg + "\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    context.handlerResult(e.message.toString() + "\r\n")
                } catch (e: Exception) {
                    context.handlerResult(e.message.toString() + "\r\n")
                }
            }
        }.start()
    }

}