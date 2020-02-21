package com.vaccae.nanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vaccae.vnanomsg.NNSURVEY
import kotlinx.android.synthetic.main.activity_survey.*

class SURVEYActivity : AppCompatActivity() {

    private var nnsurvey: NNSURVEY? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        //连接按钮
        btnConnent.setOnClickListener {
            if (nnsurvey == null) {
                nnsurvey = NNSURVEY()
            }
            nnsurvey.let {
                try {
                    if (it?.connect(edtipadr.text.toString())!!) {
                        tvmsg.append("SURVEY连接成功！\r\n")
                        RecvSURVEY()
                    } else {
                        tvmsg.append("SURVEY连接失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

        //发送按钮
        btnSend.setOnClickListener {
            nnsurvey.let {
                try {
                    val input = edtinput.text.toString()
                    val bytes = input.toByteArray()
                    //发送数据
                    it?.send(bytes)
                    //延时50毫秒
                    Thread.sleep(50)
                    //接收数据
                    val recvbyte = it?.recvbyte()
                    val recvmsg = recvbyte?.toString(charset = Charsets.UTF_8)

                    tvmsg.append(recvmsg + "\r\n")
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

    }

    private fun RecvSURVEY() {
        var recvcount = 0;
        Thread {
            while (true) {
                Thread.sleep(1000)
                nnsurvey.let {
                    try {
                        val byteArray = it?.recvbyte()
                        if (byteArray != null) {
                            val recvmsg = byteArray?.toString(charset = Charsets.UTF_8)
                            runOnUiThread {
                                tvmsg.append(recvmsg + "\r\n")
                            }
                        }
                    } catch (e: IllegalArgumentException) {
                        recvcount++;
                        runOnUiThread {
                            tvmsg.text = recvcount.toString()
                        }
                    }
                }
            }
        }.start()
    }
}
