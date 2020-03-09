package com.vaccae.nanomsg.mvp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.ScrollView
import com.vaccae.nanomsg.R
import com.vaccae.nanomsg.bean.NanoTrans
import com.vaccae.nanomsg.mvp.contract.TransContract
import com.vaccae.nanomsg.mvp.prsenter.PipePullPresenter
import kotlinx.android.synthetic.main.activity_pipepull.*

class PipePullActivity : AppCompatActivity(), TransContract.View {

    private lateinit var presenter: PipePullPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pipepull)

        edtipadr.text = Editable.Factory.getInstance().newEditable(
            "tcp://*:8006"
        )

        presenter = PipePullPresenter(this)
        btnConnent.setOnClickListener { presenter.ConnectNano(edtipadr.text.toString()) }

    }

    //更新UI
    override fun handlerResult(t: String) {
        runOnUiThread(Runnable {
            tvmsg.append(t)
            Thread.sleep(50)
            svmsg.fullScroll(ScrollView.FOCUS_DOWN)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.threadstat = false
        System.gc()
    }
}
