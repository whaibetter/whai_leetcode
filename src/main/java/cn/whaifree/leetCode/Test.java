package cn.whaifree.leetCode;

import cn.whaifree.leetCode.Tree.LeetCode94;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * <p>
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/13 20:54
 */
public class Test {

    private static int number;

    private int getNumber;


    public int minus() {
        localVarl();
        int i = 1;
        double d = 2d;
        char c = 'd';
        return (int) (i + d);
    }

    public void localVarl() {
        int a = 0;
        System.out.println(a);
        int b = 0;
    }

    public void localVar2() {
        {
            int a = 0;
            System.out.println(a);
        }
        //此时的就会复用a的槽位
        int b = 0;
    }


    public static void main(String[] args) {

        //第1类问题
        int i1 = 10;
        i1++;
        int i2 = 10;
        ++i2;


        //第2类问题:
        int i3 = 10;
        int i4 = i3++;
        int i5 = 10;
        int i6 = ++i5;


        //第3类问题:
        int i7 = 10;
        i7 = i7++;
        int i8 = 10;
        i8 = ++i8;

        // 第四类问题
        int i9 = 10;
        int i10 = i9++ + ++i9;

//        // 挨个输出i1-i10
//        System.out.println(i1);
//        System.out.println(i2);
//        System.out.println(i3);
//        System.out.println(i4);
//        System.out.println(i5);
//        System.out.println(i6);
//        System.out.println(i7);
//        System.out.println(i8);
//        System.out.println(i9);
//        System.out.println(i10);
//
//
//
//        System.out.println(new Test().getNumber);
//
//        System.out.println(Test.number);
//        // 递归删除某个目录下所有文件名为(1)的文件
//
//
//        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(systemClassLoader);
//
//        ClassLoader extClassLoader = systemClassLoader.getParent();
//        System.out.println(extClassLoader);
//
//        // 获取不到引导类类加载器
//        ClassLoader bootClassLoader = extClassLoader.getParent();
//        System.out.println(bootClassLoader);
//
//        ClassLoader classLoader = Test.class.getClassLoader();
//        System.out.println(classLoader);
//
//
//        // 系统核心类库都是使用引导类（BootStrapClassLoader）进行加载，都为null
//        ClassLoader stringLoader = String.class.getClassLoader();
//        System.out.println(stringLoader);
//
//
//        PriorityQueue<Integer> objects = new PriorityQueue<>();
//        objects.add(1);
//        objects.add(2);
//        objects.add(3);
//        objects.add(4);
//        System.out.println(objects.poll());
//        System.out.println(objects.poll());


    }
}
