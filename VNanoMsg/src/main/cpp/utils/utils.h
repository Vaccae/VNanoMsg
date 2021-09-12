//
// Created by 36574 on 2020-02-29.
//
#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <malloc.h>

#ifndef NANOMSG_UTILS_H
#define NANOMSG_UTILS_H


class utils {
public:
    //JByteArray转为PChar
    static char *jbyteArrTopChar(JNIEnv *env, jbyteArray array, int len);

    //pchar转为jbyteArray
    static jbyteArray pCharTojbyteArr(JNIEnv *env, char *buf, int len);

    //jstring转为char* NewStringUTF所需要的内容位char*格式
    static char *jstringTochar(JNIEnv *env, jstring jstr);

    //char * 转为jstring
    static jstring chartoJstring(JNIEnv *env, const char *pat);

    //jstring 转为jbyte*
    static jbyteArray JstringtoJbyte(JNIEnv *env, jstring jstr);

    //char*拼接
    static char* CharCat(const char* char1, const char* char2);

    //抛异常函数
    static void throwByName(JNIEnv *env, const char *name, const char *msg);
};


#endif //NANOMSG_UTILS_H
