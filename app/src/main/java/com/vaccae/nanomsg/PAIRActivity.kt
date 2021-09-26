package com.vaccae.nanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vaccae.vnanomsg.NNPAIR
import kotlinx.android.synthetic.main.activity_pair.*

class PAIRActivity : AppCompatActivity() {

    private var nnpair: NNPAIR? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pair)

        //连接按钮
        btnConnent.setOnClickListener {
            //nnpair = nnpair ?: NNPAIR().also {
            nnpair = NNPAIR().also {
                try {
//                    if (it.connect(edtipadr.text.toString())) {
//                        tvmsg.append("PAIR连接成功！\r\n")
//                    } else {
//                        tvmsg.append("PAIR连接失败！\r\n")
//                    }
                    if (it.bind("tcp:192.168.10.151:8157")) {
                        tvmsg.append("PAIR绑定成功！\r\n")
                    } else {
                        tvmsg.append("PAIR绑定失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

        //发送按钮
        btnSend.setOnClickListener {
            nnpair ?: tvmsg.append("nnpair为空！")
            nnpair?.also {
                try {
                    //发送数据
                    it.send(edtinput.text.toString())
                    //延时50毫秒
                    Thread.sleep(50)
                    //接收数据
                    val recvmsg = it.recv()
                    tvmsg.append(recvmsg + "\r\n")
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

        //关闭按钮
        btnClose.setOnClickListener {
            nnpair?.also {
                try {
                    if (it.close()) {
                        tvmsg.append("PAIR关闭成功！\r\n")
                    } else {
                        tvmsg.append("PAIR关闭失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
            nnpair=null;
        }
    }
}
