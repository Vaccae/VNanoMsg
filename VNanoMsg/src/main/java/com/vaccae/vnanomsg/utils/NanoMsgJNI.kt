package com.vaccae.vnanomsg.utils

/**
 * 作者：Vaccae
 * 邮箱：3657447@qq.com
 * 创建时间：2020-02-14 09:00
 * 功能模块说明：
 */

class NanoMsgJNI {
    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("vnanomsg")
        }
    }

    //初始化Socket
    external fun init(ntype: String): Int

    //绑定地址
    external fun bind(connectsocket: Int, ipadr: String): Int

    //关闭套接了
    external fun close(connectsocket: Int): Int

    //连接数据库
    external fun connect(connectsocket: Int, ipadr: String): Int

    //发送数据
    external fun send(connectsocket: Int, sendmsg: String): Int

    external fun sendbyte(connectsocket: Int, sendbytes: ByteArray): Int

    //接收数据
    external fun recv(connectsocket: Int): String?

    external fun recvbyte(connectsocket: Int): ByteArray?

    //设置订单主题前缀
    external fun subscribe(connectsocket: Int, subs: String, itype: Int): Int

    external fun subscribebyte(connectsocket: Int, subs: ByteArray, itype: Int): Int
}
