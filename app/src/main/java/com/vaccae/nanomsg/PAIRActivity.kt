package com.vaccae.nanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vaccae.vnanomsg.NNPAIR
import kotlinx.android.synthetic.main.activity_pair.*

class PAIRActivity : AppCompatActivity() {

    private var nnpair: NNPAIR? = null

    override fun onDestroy() {
        super.onDestroy()
        if (nnpair != null) {
            nnpair.let {
                it?.close()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pair)

        //连接按钮
        btnConnent.setOnClickListener {
            if (nnpair == null) {
                nnpair = NNPAIR()
            }
            nnpair.let {
                try {
                    if (it?.connect(edtipadr.text.toString())!!) {
                        tvmsg.append("PAIR连接成功！\r\n")
                    } else {
                        tvmsg.append("PAIR连接失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

        //发送按钮
        btnSend.setOnClickListener {
            nnpair.let {
                try {
                    //发送数据
                    it?.send(edtinput.text.toString())
                    //延时50毫秒
                    Thread.sleep(50)
                    //接收数据
                    val recvmsg = it?.recv()
                    tvmsg.append(recvmsg + "\r\n")
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }
    }
}
