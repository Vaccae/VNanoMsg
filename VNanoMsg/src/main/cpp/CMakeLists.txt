# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(NN_SOURCES
        src/nn.h
        src/inproc.h
        src/ipc.h
        src/tcp.h
        src/ws.h
        src/pair.h
        src/pubsub.h
        src/reqrep.h
        src/pipeline.h
        src/survey.h
        src/bus.h

        src/core/ep.h
        src/core/ep.c
        src/core/global.h
        src/core/global.c
        src/core/pipe.c
        src/core/poll.c
        src/core/sock.h
        src/core/sock.c
        src/core/sockbase.c
        src/core/symbol.c

        src/aio/ctx.h
        src/aio/ctx.c
        src/aio/fsm.h
        src/aio/fsm.c
        src/aio/pool.h
        src/aio/pool.c
        src/aio/timer.h
        src/aio/timer.c
        src/aio/timerset.h
        src/aio/timerset.c
        src/aio/usock.h
        src/aio/usock.c
        src/aio/worker.h
        src/aio/worker.c

        src/utils/alloc.h
        src/utils/alloc.c
        src/utils/atomic.h
        src/utils/atomic.c
        src/utils/attr.h
        src/utils/chunk.h
        src/utils/chunk.c
        src/utils/chunkref.h
        src/utils/chunkref.c
        src/utils/clock.h
        src/utils/clock.c
        src/utils/closefd.h
        src/utils/closefd.c
        src/utils/cont.h
        src/utils/efd.h
        src/utils/efd.c
        src/utils/err.h
        src/utils/err.c
        src/utils/fast.h
        src/utils/fd.h
        src/utils/hash.h
        src/utils/hash.c
        src/utils/list.h
        src/utils/list.c
        src/utils/msg.h
        src/utils/msg.c
        src/utils/condvar.h
        src/utils/condvar.c
        src/utils/mutex.h
        src/utils/mutex.c
        src/utils/once.h
        src/utils/once.c
        src/utils/queue.h
        src/utils/queue.c
        src/utils/random.h
        src/utils/random.c
        src/utils/sem.h
        src/utils/sem.c
        src/utils/sleep.h
        src/utils/sleep.c
        src/utils/strcasecmp.c
        src/utils/strcasecmp.h
        src/utils/strcasestr.c
        src/utils/strcasestr.h
        src/utils/strncasecmp.c
        src/utils/strncasecmp.h
        src/utils/thread.h
        src/utils/thread.c
        src/utils/wire.h
        src/utils/wire.c

        src/devices/device.h
        src/devices/device.c

        src/protocols/utils/dist.h
        src/protocols/utils/dist.c
        src/protocols/utils/excl.h
        src/protocols/utils/excl.c
        src/protocols/utils/fq.h
        src/protocols/utils/fq.c
        src/protocols/utils/lb.h
        src/protocols/utils/lb.c
        src/protocols/utils/priolist.h
        src/protocols/utils/priolist.c

        src/protocols/bus/bus.c
        src/protocols/bus/xbus.h
        src/protocols/bus/xbus.c

        src/protocols/pipeline/push.c
        src/protocols/pipeline/pull.c
        src/protocols/pipeline/xpull.h
        src/protocols/pipeline/xpull.c
        src/protocols/pipeline/xpush.h
        src/protocols/pipeline/xpush.c

        src/protocols/pair/pair.c
        src/protocols/pair/xpair.h
        src/protocols/pair/xpair.c

        src/protocols/pubsub/pub.c
        src/protocols/pubsub/sub.c
        src/protocols/pubsub/trie.h
        src/protocols/pubsub/trie.c
        src/protocols/pubsub/xpub.h
        src/protocols/pubsub/xpub.c
        src/protocols/pubsub/xsub.h
        src/protocols/pubsub/xsub.c

        src/protocols/reqrep/req.h
        src/protocols/reqrep/req.c
        src/protocols/reqrep/rep.h
        src/protocols/reqrep/rep.c
        src/protocols/reqrep/task.h
        src/protocols/reqrep/task.c
        src/protocols/reqrep/xrep.h
        src/protocols/reqrep/xrep.c
        src/protocols/reqrep/xreq.h
        src/protocols/reqrep/xreq.c

        src/protocols/survey/respondent.c
        src/protocols/survey/surveyor.c
        src/protocols/survey/xrespondent.h
        src/protocols/survey/xrespondent.c
        src/protocols/survey/xsurveyor.h
        src/protocols/survey/xsurveyor.c

        src/transports/utils/backoff.h
        src/transports/utils/backoff.c
        src/transports/utils/dns.h
        src/transports/utils/dns.c
        src/transports/utils/dns_getaddrinfo.h
        src/transports/utils/dns_getaddrinfo.inc
        src/transports/utils/dns_getaddrinfo_a.h
        src/transports/utils/dns_getaddrinfo_a.inc
        src/transports/utils/iface.h
        src/transports/utils/iface.c
        src/transports/utils/literal.h
        src/transports/utils/literal.c
        src/transports/utils/port.h
        src/transports/utils/port.c
        src/transports/utils/streamhdr.h
        src/transports/utils/streamhdr.c
        src/transports/utils/base64.h
        src/transports/utils/base64.c

        src/transports/inproc/binproc.h
        src/transports/inproc/binproc.c
        src/transports/inproc/cinproc.h
        src/transports/inproc/cinproc.c
        src/transports/inproc/inproc.c
        src/transports/inproc/ins.h
        src/transports/inproc/ins.c
        src/transports/inproc/msgqueue.h
        src/transports/inproc/msgqueue.c
        src/transports/inproc/sinproc.h
        src/transports/inproc/sinproc.c

        src/transports/ipc/aipc.h
        src/transports/ipc/aipc.c
        src/transports/ipc/bipc.h
        src/transports/ipc/bipc.c
        src/transports/ipc/cipc.h
        src/transports/ipc/cipc.c
        src/transports/ipc/ipc.c
        src/transports/ipc/sipc.h
        src/transports/ipc/sipc.c

        src/transports/tcp/atcp.h
        src/transports/tcp/atcp.c
        src/transports/tcp/btcp.h
        src/transports/tcp/btcp.c
        src/transports/tcp/ctcp.h
        src/transports/tcp/ctcp.c
        src/transports/tcp/stcp.h
        src/transports/tcp/stcp.c
        src/transports/tcp/tcp.c

        src/transports/ws/aws.h
        src/transports/ws/aws.c
        src/transports/ws/bws.h
        src/transports/ws/bws.c
        src/transports/ws/cws.h
        src/transports/ws/cws.c
        src/transports/ws/sws.h
        src/transports/ws/sws.c
        src/transports/ws/ws.c
        src/transports/ws/ws_handshake.h
        src/transports/ws/ws_handshake.c
        src/transports/ws/sha1.h
        src/transports/ws/sha1.c
        )

#设置socket.h中的结构体定义，如果没有这个提cmake中一直会报错
add_definitions(-DNN_HAVE_MSG_CONTROL)

add_definitions(-DNN_HAVE_EPOLL)
add_definitions(-DNN_USE_EPOLL)
add_definitions(-DNN_HAVE_KQUEUE)
add_definitions(-DNN_USE_KQUEUE)
add_definitions(-DNN_HAVE_POLL)
add_definitions(-DNN_USE_POLL)
list(APPEND NN_SOURCES
        src/aio/poller.h
        src/aio/poller.c
        src/aio/poller_epoll.h
        src/aio/poller_epoll.inc
        src/aio/poller_kqueue.h
        src/aio/poller_kqueue.inc
        src/aio/poller_poll.h
        src/aio/poller_poll.inc
        )


add_definitions(-DNN_HAVE_EVENTFD)
add_definitions(-DNN_USE_EVENTFD)
list(APPEND NN_SOURCES
        src/utils/efd_eventfd.h
        src/utils/efd_eventfd.inc
        )

add_definitions(-DNN_HAVE_PIPE)
add_definitions(-DNN_USE_PIPE)
list(APPEND NN_SOURCES
        src/utils/efd_pipe.h
        src/utils/efd_pipe.inc
        )


add_definitions(-DNN_HAVE_SOCKETPAIR)
add_definitions(-DNN_USE_SOCKETPAIR)
list(APPEND NN_SOURCES
        src/utils/efd_socketpair.h
        src/utils/efd_socketpair.inc
        )

add_definitions(-DNN_HAVE_WINSOCK)
add_definitions(-DNN_USE_WINSOCK)
list(APPEND NN_SOURCES
        src/utils/efd_win.h
        src/utils/efd_win.inc
        )

#这里加入我们自己封装的的native-lib的cpp文件
list(APPEND NN_SOURCES
        native-lib.cpp
        )

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

add_library( # Sets the name of the library.
        vnanomsg

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        ${NN_SOURCES})

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        vnanomsg

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})