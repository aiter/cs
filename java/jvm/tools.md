### tools
[jcmd-jps-jstat](https://docs.oracle.com/javase/8/docs/technotes/guides/troubleshoot/tooldescr025.html)

[jps](https://docs.oracle.com/javase/8/docs/technotes/tools/unix/jps.html#CHDGHCGB)  查看运行的java程序
jstat  查看old、new等内存情况
java  java启动参数，常用的heap大小
jmap 查看内存对象个数及大小
jhat 结合jmap的dump文件
jstack 查看运行的线程
jconsole 
 java -Dcom.sun.management.jmxremote \
 -Dcom.sun.management.jmxremote.port=9010 \
 -Dcom.sun.management.jmxremote.local.only=false \
 -Dcom.sun.management.jmxremote.authenticate=false \
 -Dcom.sun.management.jmxremote.ssl=false \
[hprof](https://docs.oracle.com/javase/8/docs/technotes/samples/hprof.html) HPROF: A Heap/CPU Profiling Tool
