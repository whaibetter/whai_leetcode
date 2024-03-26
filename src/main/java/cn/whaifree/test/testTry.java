package cn.whaifree.test;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/24 22:42
 * @注释
 */
public class testTry {

    @Test
    public void test() {
        int i = tryTest();
        System.out.println(i);
    }

    public int tryTest() {

        try {
            return 1;
        } catch (Exception e) {

        }finally {
            return 2;
        }
    }

}
