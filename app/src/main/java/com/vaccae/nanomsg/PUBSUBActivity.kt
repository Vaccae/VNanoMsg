package com.vaccae.nanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vaccae.vnanomsg.NNPUBSUB
import kotlinx.android.synthetic.main.activity_pubsub.*

class PUBSUBActivity : AppCompatActivity() {

    private var nnpubsub: NNPUBSUB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pubsub)

        //连接按钮
        btnConnent.setOnClickListener {
            if (nnpubsub == null) {
                nnpubsub = NNPUBSUB()
            }
            nnpubsub?.subscribe("PUBSUB")
            nnpubsub.let {
                try {
                    if (it?.connect(edtipadr.text.toString())!!) {
                        tvmsg.append("PUBSUB连接成功！\r\n")
                        RecvPub()
                    } else {
                        tvmsg.append("PUBSUB连接失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

    }


    private fun RecvPub() {
        var recvcount = 0;
        Thread {
            while (true) {
                Thread.sleep(1000)
                nnpubsub.let {
                    try {
                        val byteArray = it?.recvbyte()
                        if (byteArray != null) {

                            val recvmsg = byteArray?.toString(charset = Charsets.UTF_8)
                            runOnUiThread {
                                tvmsg.append(recvmsg + "\r\n")
                            }
                            Log.i("pubsubinfo", recvmsg)
                        } else {
                            Log.i("pubsubinfo", "null")
                        }
                    } catch (e: IllegalArgumentException) {
                        recvcount++;
                        runOnUiThread {
                            tvrecvcount.text = recvcount.toString()
                        }
                    }
                }

            }
        }.start()
    }
}
