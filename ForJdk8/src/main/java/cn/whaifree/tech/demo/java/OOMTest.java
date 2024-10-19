package cn.whaifree.tech.demo.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/18 21:40
 * @注释
 */
public class OOMTest {

    // 如果起了 2 个线程，当其中一个 OOM 了，另一个线程会受影响吗?

    // -Xms10m -Xmx20m  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
    static List<int[]> list = new ArrayList<>();
    public static void main(String[] args) {
        // 启动一个线程来模拟内存泄漏
        Thread leakThread = new Thread(() -> {
            try {
                // 使用一个大数组来耗尽内存
                while (true) {
                    // 创建一个大的对象数组
                    int[] largeArray = new int[10_000_00]; // 5M
                    list.add(largeArray);
                    Thread.sleep(1000); // 暂停一段时间，观察 GC
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        leakThread.start();

        // 启动一个监控线程，用于输出 GC 的信息
        Thread monitorThread = new Thread(() -> {
            while (true) {
                try {

                    // 输出当前的内存使用情况
                    long totalMemory = Runtime.getRuntime().totalMemory();
                    long freeMemory = Runtime.getRuntime().freeMemory();
                    System.out.println("Total Memory: " + (totalMemory / (1024 * 1024)) + " MB");
                    System.out.println("Free Memory: " + (freeMemory / (1024 * 1024)) + " MB");
                    System.out.println("Used Memory: " + ((totalMemory - freeMemory) / (1024 * 1024)) + " MB");
                    // 暂停一段时间，获取内存使用信息
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        monitorThread.start();
    }
    /**
     * "C:\Program Files\Java\jdk1.8.0_202\bin\java.exe" -Xms10m -Xmx20m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Dhxl.spring.invoke.port=33333 "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2022.3.3\lib\idea_rt.jar=61275:C:\Program Files\JetBrains\IntelliJ IDEA 2022.3.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_202\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_202\jre\lib\rt.jar;D:\project\LeetCode\ForJdk8\target\classes;D:\Program Files\apache-maven-3.9.2\repository\javax\persistence\javax.persistence-api\2.2\javax.persistence-api-2.2.jar;D:\Program Files\apache-maven-3.9.2\repository\com\github\phantomthief\simple-pool\0.1.17\simple-pool-0.1.17.jar;D:\Program Files\apache-maven-3.9.2\repository\org\slf4j\slf4j-api\1.7.21\slf4j-api-1.7.21.jar;D:\Program Files\apache-maven-3.9.2\repository\com\google\guava\guava\20.0\guava-20.0.jar;D:\Program Files\apache-maven-3.9.2\repository\com\github\phantomthief\more-lambdas\0.1.27\more-lambdas-0.1.27.jar;D:\Program Files\apache-maven-3.9.2\repository\org\springframework\spring-context\5.3.18\spring-context-5.3.18.jar;D:\Program Files\apache-maven-3.9.2\repository\org\springframework\spring-aop\5.3.18\spring-aop-5.3.18.jar;D:\Program Files\apache-maven-3.9.2\repository\org\springframework\spring-beans\5.3.18\spring-beans-5.3.18.jar;D:\Program Files\apache-maven-3.9.2\repository\org\springframework\spring-core\5.3.18\spring-core-5.3.18.jar;D:\Program Files\apache-maven-3.9.2\repository\org\springframework\spring-jcl\5.3.18\spring-jcl-5.3.18.jar;D:\Program Files\apache-maven-3.9.2\repository\org\springframework\spring-expression\5.3.18\spring-expression-5.3.18.jar;D:\Program Files\apache-maven-3.9.2\repository\cglib\cglib\3.3.0\cglib-3.3.0.jar;D:\Program Files\apache-maven-3.9.2\repository\org\ow2\asm\asm\7.1\asm-7.1.jar;D:\Program Files\apache-maven-3.9.2\repository\cn\hutool\hutool-http\5.8.18\hutool-http-5.8.18.jar;D:\Program Files\apache-maven-3.9.2\repository\cn\hutool\hutool-core\5.8.18\hutool-core-5.8.18.jar;D:\Program Files\apache-maven-3.9.2\repository\junit\junit\4.12\junit-4.12.jar;D:\Program Files\apache-maven-3.9.2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;C:\Users\wenhai\.config\.cool-request\request\lib\spring-invoke-starter.jar" cn.whaifree.tech.demo.java.OOMTest
     * 0.117: [GC (Allocation Failure) [PSYoungGen: 2048K->504K(2560K)] 2048K->832K(9728K), 0.0010010 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 0.157: [GC (Allocation Failure) [PSYoungGen: 2552K->504K(2560K)] 2880K->1072K(9728K), 0.0008499 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Total Memory: 9 MB
     * Free Memory: 4 MB
     * Used Memory: 4 MB
     * Total Memory: 13 MB
     * Free Memory: 3 MB
     * Used Memory: 9 MB
     * 3.187: [GC (Allocation Failure) [PSYoungGen: 1647K->512K(2560K)] 13934K->13274K(16384K), 0.0007103 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 3.187: [GC (Allocation Failure) [PSYoungGen: 512K->512K(2560K)] 13274K->13386K(16384K), 0.0005327 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 3.188: [Full GC (Allocation Failure) [PSYoungGen: 512K->0K(2560K)] [ParOldGen: 12874K->12989K(13824K)] 13386K->12989K(16384K), [Metaspace: 4673K->4673K(1056768K)], 0.0115695 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
     * 3.200: [GC (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] 12989K->12989K(16384K), 0.0010701 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * 3.201: [Full GC (Allocation Failure) [PSYoungGen: 0K->0K(2560K)] [ParOldGen: 12989K->12899K(13824K)] 12989K->12899K(16384K), [Metaspace: 4673K->4666K(1056768K)], 0.0113301 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
     * java.lang.OutOfMemoryError: Java heap space
     * 	at cn.whaifree.tech.demo.java.OOMTest.lambda$main$0(OOMTest.java:25)
     * 	at cn.whaifree.tech.demo.java.OOMTest$$Lambda$1/495053715.run(Unknown Source)
     * 	at java.lang.Thread.run(Thread.java:748)
     * Total Memory: 16 MB
     * Free Memory: 3 MB
     * Used Memory: 12 MB
     * Total Memory: 16 MB
     * Free Memory: 3 MB
     * Used Memory: 12 MB
     * Heap
     *  PSYoungGen      total 2560K, used 96K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 2048K, 4% used [0x00000000ff980000,0x00000000ff9982b8,0x00000000ffb80000)
     *   from space 512K, 0% used [0x00000000ffb80000,0x00000000ffb80000,0x00000000ffc00000)
     *   to   space 1536K, 0% used [0x00000000ffe80000,0x00000000ffe80000,0x0000000100000000)
     *  ParOldGen       total 13824K, used 12899K [0x00000000fec00000, 0x00000000ff980000, 0x00000000ff980000)
     *   object space 13824K, 93% used [0x00000000fec00000,0x00000000ff898f90,0x00000000ff980000)
     *  Metaspace       used 4701K, capacity 4886K, committed 4992K, reserved 1056768K
     *   class space    used 518K, capacity 561K, committed 640K, reserved 1048576K
     *
     * Process finished with exit code 130
     */
}
