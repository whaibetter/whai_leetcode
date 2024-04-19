/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/10 12:49
 * @注释
 */
public class TestInteger {

    public static void main(String[] args) {


        /**
         *   0 new #2 <java/lang/Integer>
         *   3 dup
         *   4 bipush 100
         *   6 invokespecial #3 <java/lang/Integer.<init> : (I)V>
         *   9 astore_1
         *  10 new #2 <java/lang/Integer>
         *  13 dup
         *  14 bipush 100
         *  16 invokespecial #3 <java/lang/Integer.<init> : (I)V>
         *  19 astore_2
         *  20 getstatic #4 <java/lang/System.out : Ljava/io/PrintStream;>
         *  23 aload_1
         *  24 aload_2
         *  25 if_acmpne 32 (+7)
         *  28 iconst_1
         *  29 goto 33 (+4)
         *  32 iconst_0
         *  33 invokevirtual #5 <java/io/PrintStream.println : (Z)V>
         */


        Integer c = new Integer(100);
        int d = 100;
        System.out.println(c == d); //true
        /**
         *  36 new #2 <java/lang/Integer>
         *  39 dup
         *  40 bipush 100
         *  42 invokespecial #3 <java/lang/Integer.<init> : (I)V>
         *  45 astore_3
         *  46 bipush 100
         *  48 istore 4
         *  50 getstatic #4 <java/lang/System.out : Ljava/io/PrintStream;>
         *  53 aload_3
         *  54 invokevirtual #6 <java/lang/Integer.intValue : ()I> // Integer自动拆箱为int
         *  57 iload 4
         *  59 if_icmpne 66 (+7)
         *  62 iconst_1
         *  63 goto 67 (+4)
         *  66 iconst_0
         *  67 invokevirtual #5 <java/io/PrintStream.println : (Z)V>
         */


        Integer e = new Integer(100);
        Integer f = 100;
        System.out.println(e == f); //false
        /**
         * 70 new #2 <java/lang/Integer>
         *  73 dup
         *  74 bipush 100
         *  76 invokespecial #3 <java/lang/Integer.<init> : (I)V>
         *  79 astore 5
         *  81 bipush 100
         *  83 invokestatic #7 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;> //自动装箱，之后其实是一个new Integer(100)
         *  86 astore 6
         *  88 getstatic #4 <java/lang/System.out : Ljava/io/PrintStream;>
         *  91 aload 5
         *  93 aload 6
         *  95 if_acmpne 102 (+7)
         *  98 iconst_1
         *  99 goto 103 (+4)
         * 102 iconst_0
         * 103 invokevirtual #5 <java/io/PrintStream.println : (Z)V>
         */

        Integer g = 100; // Integer i = Integer.valueOf(100) -128-127 会缓存，如果超过这个范围就是new Integer
        Integer h = 100; // Integer i = Integer.valueOf(100)
        System.out.println(g == h); //true
        /**
         * 106 bipush 100
         * 108 invokestatic #7 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>
         * 111 astore 7
         * 113 bipush 100
         * 115 invokestatic #7 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>
         * 118 astore 8
         * 120 getstatic #4 <java/lang/System.out : Ljava/io/PrintStream;>
         * 123 aload 7
         * 125 aload 8
         * 127 if_acmpne 134 (+7)
         * 130 iconst_1
         * 131 goto 135 (+4)
         * 134 iconst_0
         * 135 invokevirtual #5 <java/io/PrintStream.println : (Z)V>
         */
        // java API中对Integer类型的valueOf的定义如下，对于-128到127之间的数，会进行缓存
        // sipush指令用于加载-32768到32767之间的整数常量到操作数栈中，而bipush指令则用于加载-128到127之间的整数常量到操作数栈中
        Integer j = 128;
        Integer i = 128;
        System.out.println(i == j); //false
        /**
         * 138 sipush 128
         * 141 invokestatic #7 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>
         * 144 astore 9
         * 146 sipush 128
         * 149 invokestatic #7 <java/lang/Integer.valueOf : (I)Ljava/lang/Integer;>
         * 152 astore 10
         * 154 getstatic #4 <java/lang/System.out : Ljava/io/PrintStream;>
         * 157 aload 10
         * 159 aload 9
         * 161 if_acmpne 168 (+7)
         * 164 iconst_1
         * 165 goto 169 (+4)
         * 168 iconst_0
         * 169 invokevirtual #5 <java/io/PrintStream.println : (Z)V>
         * 172 return
         */
    }
}


class gg {
    private static final int _1MB = 1024 * 1024;
    public static void testAllocation() {
        byte [] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 *_1MB];
        allocation2 = new byte[2 *_1MB];
        allocation3 = new byte[2 *_1MB];
        allocation4 = new byte[4 *_1MB];
    }
    public static void main(String[] args) {
        testAllocation();
    }
}
