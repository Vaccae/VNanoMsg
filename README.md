# VNanoMsg
**Android下的Socket通讯库，对NanoMsg的二次封装，仿照NNanoMsg的第三方开源库**

## 公众号：微卡智享

[详细的介绍说明](http://mp.weixin.qq.com/mp/homepage?__biz=MzA4Nzk0NTU0Nw==&hid=6&sn=9c965c60c6d80e0e8c60ca627225cdb2&scene=18#wechat_redirect)

## 使用方法
### 添加依赖项

##### 在build.gradle中加入VNanoMsg的引用<br>

``` kotlin
	android { 
	  allprojects {
	     repositories {
	       maven { url 'https://jitpack.io' }
	     }
	  }
	} 

	dependencies {
	    implementation 'com.github.Vaccae:VNanoMsg:1.0.4'
	}
```




#### 核心函数

##### 创建对应的通讯模式


``` kotlin
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

//PipeLine中的Push模式
var nn - NNPIPEPUSH()

//PipeLine中的Pull模式
var nn - NNPIPEPULL()
	
```
##### 绑定地址（服务端）

``` kotlin
//绑定地址  地址要写全如上  tcp://加地址
//返回是 bool  ture是成功  false是失败
var res = nn.bind("tcp://*:8080")
```

##### 连接服务端

``` kotlin
//连接服务端  地址要写全如上  tcp://加地址
//返回是 bool  ture是成功  false是失败
var res = nn.connect("tcp://localhost:8080")
```

##### 发送数据

``` kotlin
//发送数据  String
//返回值：发送的字节数 Int
var res = nn.send("你好")

//发送数据  ByteArray
//返回值：发送的字节数
val msg = "你好"
val bytes = msg.toByteArray()
var res = nn.send(bytes)
```

##### 接收数据

``` kotlin
//接慢数据
//返回值：接收到字符串 String
var res = nn.recv()

//接收数据  
//返回值：接收的数据 ByteArray
var res = nn.recvbyte()
val msg = res.toString(charset = Charsets.UTF_8)
```

##### 设置/取消订阅前缀

==订阅消息只针对PUBSUB模式才有的参数==

``` kotlin
//设置订阅前缀
//返回值：大于0成功，小于0失败 Int
var res = nn.subscribe("输入要订阅的主题字符串"）
    
//取消订阅前缀
//返回值：大于0成功，小于0失败 Int
var res = nn.unsubscribe("输入要取消订阅的主题字符串")
```

### 特别说明

  ==执行连接、发送、接收数据时需要加上try catch防止程序崩溃，因为在NDK中接收失败或是发送失败时我都会直接抛出异常来<br>==

``` kotlin
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
```


### 简单例子

##### 这里用的是REQREP的模式，别的模式就是在创建时设置对应的类即可。

``` kotlin
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
```

## 版本更新记录

### 1.0.4版本
加入了nn.shundownbind()和nn.shundownconnect()两个函数，用于从套接字中删除端点。

### 1.0.3版本
修复了nn.close()中总是提示关闭套接字失败的问题。

### 1.0.2版本
1.把Nanomsg中原来改的SurVey的参数改了回来，用于解决通讯时接收不全的问题。
2.SurVey例子中原来接收后开启的线程，改为了kotlin的协程方式，对协程的一个简单的学习。

### 1.0.1版本

 1. JNI层的重新封装，新增加了一个NNBaseInf的接口，别的模块调用时都是引用它来实现，减少了很多不必要的代码。
 2. 增加了NNPIPEPULL和NNPIPEPUSH两个通讯类，主要是针对PIPELINE的通讯模式。
 3. C++中加入了通过JNI里用JAVA的方式把char指针转换为UTF-8，防止因为乱字符的问题导航NewStringUTF的函数出错，程序崩溃，并且把相关的工具类改放到utils的类中，不是都写在了native-lib.cpp了。
 4. recv的接收函数里重新修改了一下截取的方式，防止出现接收数据时随机增加了字符BUG。