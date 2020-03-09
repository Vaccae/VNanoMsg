package com.vaccae.nanomsg.mvvm.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.vaccae.nanomsg.bean.NanoTrans
import com.vaccae.nanomsg.bean.VaccaeShare
import com.vaccae.nanomsg.mvvm.model.PipePushModel
import com.vaccae.vnanomsg.NNPIPEPUSH
import java.lang.ref.WeakReference

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-26 11:10
 * 功能模块说明：
 */
class PipePushViewModel {

    var pipePushModel = PipePushModel()
    //线程是闭
    var threadrun = true
    var count = 0

    var pushurl = ObservableField<String>(NanoTrans.transurl + ":8007")
    var pushsend = ObservableField<String>("PUSH消息，请查收！次数：")
    var pushshowmsg = ObservableField<String>()

    //连接数据库
    fun connect() {
        try {
            val status = pipePushModel.connectPush(pushurl.get().toString())
            pushshowmsg.set(pushshowmsg.get() + status + "\r\n")
        } catch (e: IllegalArgumentException) {
            pushshowmsg.set(pushshowmsg.get() + e.message.toString() + "\r\n")
        }
    }

    //发送数据
    fun send() {
        Thread {
            while (threadrun) {
                Thread.sleep(1000)
                val sendmsg = pushsend.get() + count
                try {
                    val resmsg = pipePushModel.senddata(sendmsg)
                    count++
                    pushshowmsg.set(resmsg)
                } catch (e: IllegalArgumentException) {
                    pushshowmsg.set(pushshowmsg.get() + e.message.toString() + "\r\n")
                }
            }
        }.start()
    }

}