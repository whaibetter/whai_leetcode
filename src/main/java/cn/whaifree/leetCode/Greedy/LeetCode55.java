package cn.whaifree.leetCode.Greedy;

import cn.whaifree.leetCode.Tree.LeetCode94;
import org.junit.Test;
import sun.misc.Launcher;

import java.net.URL;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/26 11:33
 * @注释
 */
public class LeetCode55 {

    @Test
    public void test() {
        System.out.println(new Solution().canJump(new int[]{2,0,0}));



    }





    class CustomClassLoader extends ClassLoader {
        public CustomClassLoader(ClassLoader parent) {
            super(parent);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] res = getClassFromCustomClass(name);
            if (res != null) {
                return defineClass(name, res, 0, res.length);
            } else {
                throw new ClassNotFoundException(name);
            }
        }

        private byte[] getClassFromCustomClass(String name) {
            //自定义路径加载指定类
            // 加密解密
            return null;
        }
    }

    class Solution {
        /**
         * 每个index都有一个cover覆盖范围，一旦这个范围可以覆盖nums，就能够返回
         * @param nums
         * @return
         */
        public boolean canJump(int[] nums) {
            if (nums.length == 1) {
                return true;
            }

            // cover表示最远能到哪里
            int cover = 0;
            // 注意这里cover表示能够覆盖的最远范围，不能超过他
            for (int i = 0; i <= cover; i++) {
                cover = Math.max(i + nums[i], cover);
                if (cover >= nums.length - 1) {
                    return true;
                }
            }
            return false;
        }

    }
}
