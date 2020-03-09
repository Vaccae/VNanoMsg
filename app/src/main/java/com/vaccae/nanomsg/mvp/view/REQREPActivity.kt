package com.vaccae.nanomsg.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ScrollView
import com.vaccae.nanomsg.R
import com.vaccae.nanomsg.bean.NanoTrans
import com.vaccae.nanomsg.mvp.contract.TransContract
import com.vaccae.nanomsg.mvp.presenter.ReqRepPresenter
import kotlinx.android.synthetic.main.activity_reqrep.*

class REQREPActivity : AppCompatActivity(), TransContract.View {

    private lateinit var reqRepPresenter: ReqRepPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reqrep)

        edtipadr.text = Editable.Factory.getInstance().newEditable(
            NanoTrans.transurl + ":8003"
        )

        reqRepPresenter = ReqRepPresenter(this)

        //连接按钮
        btnConnent.setOnClickListener {
            reqRepPresenter.ConnectNano(edtipadr.text.toString())
        }

        //发送按钮
        btnSend.setOnClickListener {
            reqRepPresenter.TransMsg(edtinput.text.toString())
        }
    }

    override fun handlerResult(recvmsg: String) {
        runOnUiThread(Runnable {
            tvmsg.append(recvmsg)
            Thread.sleep(50)
            svmsg.fullScroll(ScrollView.FOCUS_DOWN)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        reqRepPresenter.threadstat = false
        System.gc()
    }
}
