package com.vaccae.vnanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vaccae.vnanomsg.utils.NNBUS
import kotlinx.android.synthetic.main.activity_bus.*

class BUSActivity : AppCompatActivity() {

    private var nnbus: NNBUS? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus)

        //连接按钮
        btnConnent.setOnClickListener {
            if (nnbus == null) {
                nnbus = NNBUS()
            }
            nnbus.let {
                try {
                    it?.bind("tcp://*:8002")
                    if (it?.connect(edtipadr.text.toString())!!) {
                        tvmsg.append("BUS连接成功！\r\n")
                    } else {
                        tvmsg.append("BUS连接失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

        //发送按钮
        btnSend.setOnClickListener {
            nnbus.let {
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
