package com.vaccae.vnanomsg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vaccae.vnanomsg.utils.NNREQREP
import kotlinx.android.synthetic.main.activity_reqrep.*

class REQREPActivity : AppCompatActivity() {

    private var nnreqrep: NNREQREP? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reqrep)

        //连接按钮
        btnConnent.setOnClickListener {
            if (nnreqrep == null) {
                nnreqrep = NNREQREP()
            }
            nnreqrep.let {
                try {
                    if (it?.connect(edtipadr.text.toString())!!) {
                        tvmsg.append("REQREP连接成功！\r\n")
                    } else {
                        tvmsg.append("REQREP连接失败！\r\n")
                    }
                } catch (e: IllegalArgumentException) {
                    tvmsg.append(e.message.toString() + "\r\n")
                }
            }
        }

        //发送按钮
        btnSend.setOnClickListener {
            nnreqrep.let {
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
}
