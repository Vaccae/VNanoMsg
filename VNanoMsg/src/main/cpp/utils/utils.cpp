//
// Created by 36574 on 2020-02-29.
// 工具类，把原来的抛异常移植到这里，因为UTF-8的转换问题
// 把NewStringUTF的改为这里的函数处理，防止在Java层直接崩溃
//

#include "utils.h"

//JByteArray转为PChar
char *utils::jbyteArrTopChar(JNIEnv *env, jbyteArray array, int len) {
    char *buf = new char[len];
    env->GetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte *>(buf));
    return buf;
}

//pchar转为jbyteArray
jbyteArray utils::pCharTojbyteArr(JNIEnv *env, char *buf, int len) {
    jbyteArray array = env->NewByteArray(len);
    env->SetByteArrayRegion(array, 0, len, reinterpret_cast<jbyte *>(buf));
    return array;
}

//jstring转为char* NewStringUTF所需要的内容位char*格式
char *utils::jstringTochar(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass cls = env->FindClass("java/lang/String");
    jstring encode = env->NewStringUTF("utf-8");
    jmethodID mid = env->GetMethodID(cls, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, encode);
    jsize len = env->GetArrayLength(barr);
    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (len > 0) {
        rtn = (char *) malloc(static_cast<size_t>(len + 1));
        memcpy(rtn, ba, static_cast<size_t>(len));
        rtn[len] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    return rtn;
}

//char * 转为jstring
jstring utils::chartoJstring(JNIEnv *env, const char *pat) {
    jclass cls = env->FindClass("java/lang/String");
    jmethodID methodID = env->GetMethodID(cls, "<init>", "([BLjava/lang/String;)V");
    jbyteArray bytes = env->NewByteArray((jsize) strlen(pat));
    env->SetByteArrayRegion(bytes, 0, (jsize) strlen(pat), (jbyte *) pat);
    jstring encoding = env->NewStringUTF("utf-8");
    return (jstring) env->NewObject(cls, methodID, bytes, encoding);
}

//jstring 转为jbyteArray
jbyteArray utils::JstringtoJbyte(JNIEnv *env, jstring jstr) {
    jclass cls = env->FindClass( "java/lang/String");
    jstring encode = env->NewStringUTF("UTF-8");
    jmethodID mid = env->GetMethodID(cls, "getBytes", "(Ljava/lang/String;)[B");

    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, encode); // String .getByte("UTF-8");
    return barr;
}




//抛异常
void utils::throwByName(JNIEnv *env, const char *name, const char *msg) {
    jclass cls = env->FindClass(name);
    if (cls != NULL) {
        //检测是否有异常发生
        if (0 != env->ExceptionOccurred()) {
            //清除异常堆栈
            env->ExceptionClear();
        }
        char errmsg[500];
        sprintf(errmsg, "throwByName：%d", msg);
        env->ThrowNew(cls, msg);
    }
    env->DeleteLocalRef(cls);
}

//char*拼接
char *utils::CharCat(const char *char1, const char *char2) {
    const size_t len = strlen(char1)+strlen(char2);
    char *res_str = new char(len + 1);
    strcpy(res_str,char1);
    strcat(res_str,char2);
    return res_str;
}
