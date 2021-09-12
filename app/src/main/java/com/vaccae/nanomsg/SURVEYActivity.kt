package com.vaccae.nanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.vaccae.vnanomsg.NNSURVEY
import kotlinx.android.synthetic.main.activity_survey.*
import kotlinx.coroutines.*

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
//                        RecvSURVEY()
                        lifecycleScope.launch {
                            RecvSurVeyasync()
                        }
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
            nnsurvey?.let {
                try {
                    val input = edtinput.text.toString()
                    val bytes = input.toByteArray()
                    var recvmsg: String? = null
                    //发送数据
                    var sendqty = it.send(bytes)
                    tvmsg.post {
                        tvmsg.append("发送数据$sendqty\r\n")
                    }

                    //延时50毫秒
                    Thread.sleep(50)
                    //接收数据
                    val recvbyte = it?.recvbyte()
                    recvmsg = recvbyte?.toString(charset = Charsets.UTF_8)
                    tvmsg.post {
                        tvmsg.append("$recvmsg\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }
    }


    //协程的使用
    private suspend fun RecvSurVeyasync() {
        var recvcount = 0;
        //设置在什么线程中开启
        withContext(Dispatchers.IO) {
            while (true) {
                delay(1000)
                nnsurvey?.let {
                    try {
                        it.recvbyte()?.also { data ->
                            val recvmsg = data.toString(charset = Charsets.UTF_8)
                            //主线程同步
                            MainScope().launch {
                                tvmsg.append(recvmsg + "\r\n")
                            }

                            nnsurvey?.send("Android已收到！");
                        }
                    } catch (e: IllegalArgumentException) {
                        recvcount++;
                        //主线程同步
                        MainScope().launch {
                            tvmsg.text = recvcount.toString()
                        }
                    }
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
                            val recvmsg = byteArray.toString(charset = Charsets.UTF_8)
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
