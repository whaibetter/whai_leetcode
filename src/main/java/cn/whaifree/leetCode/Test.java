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
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 *
 * @author whaifree
 * @package cn.whaifree.leetCode
 * @Date: 2022/9/13 20:54
 */
public class Test  {


    static {
        num = 20;
        // 防止在初始化前程序中访问默认值
    }
    private static int num = 10;

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        // 获取不到引导类类加载器
        ClassLoader bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader);

        ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);


        // 系统核心类库都是使用引导类（BootStrapClassLoader）进行加载，都为null
        ClassLoader stringLoader = String.class.getClassLoader();
        System.out.println(stringLoader);


        PriorityQueue<Integer> objects = new PriorityQueue<>();
        objects.add(1);
        objects.add(2);
        objects.add(3);
        objects.add(4);
        System.out.println(objects.poll());
        System.out.println(objects.poll());


    }

}
