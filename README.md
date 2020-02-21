# VNanoMsg
Android下的NanoMsg，仿NNanoMsg

## 公众号：微卡智享

### 添加依赖项

##### 在build.gradle中加入VNanoMsg的引用<br>
	android { 
	  allprojects {
	     repositories {
	       maven { url 'https://jitpack.io' }
	     }
	  }
	}

	dependencies {
	    implementation 'com.github.Vaccae:VNanoMsg:1.0'
	}




#### 核心函数

##### 01创建对应的通讯模式

	//PAIR通讯模式
	var nn = NNPAIR()
	
	//BUS通讯模式
	var nn = NNBUS()
	
	//REQREP模式
	var nn = NNREQREP()
	
	//PUBSUB模式
	var nn = NNPUBSUB()
	
	//SURVEY模式
	var nn = NNSURVEY()
	

##### 02连接

	//通讯地址格式 tcp://地址:端口号
	//返回值：rue连妆成功，false连接失败
	fun connect(ipadress:String):Boolean

##### 03发送数据

	//发送数据  String
	//返回值：发送的字节数
	fun send(str:String):Int
	
	//发送数据  ByteArray
	//返回值：发送的字节数
	fun send(bytes:ByteArray):Int

##### 04接收数据

	//接收数据
	//返回值：String
	fun recv(): String
	
	//接收数据
	//返回值：ByteArray
	fun recvbyte(): ByteArray

##### 05设置/取消订阅前缀

#####订阅消息只针对PUBSUB模式才有的参数
	    //设置订阅前缀
	    //返回值：大于0成功，小于0失败
	    fun subscribe(string: String): Int
 	   
	    //取消订阅前缀
	    //返回值：大于0成功，小于0失败
	    fun unsubscribe(string: String): Int

### 特别说明

  执行连接、发送、接收数据时需要加上try catch防止程序崩溃，因为在NDK中接收失败或是发送失败时我都会直接抛出异常来<br>
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


### 简单例子

##### 这里用的是REQREP的模式，别的模式就是在创建时设置对应的类即可。
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


