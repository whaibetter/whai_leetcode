package cn.whaifree.leetCode.Hash;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/16 19:18
 * @注释
 */
public class LeetCode380 {
    @Test
    public void test()
    {
        // ["RandomizedSet","remove","remove","insert","getRandom","remove","insert"]
        // [[],[0],[0],[0],[],[0],[0]]
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.remove(0);
        randomizedSet.remove(0);
        randomizedSet.insert(0);
        randomizedSet.getRandom();
        randomizedSet.remove(0);
        randomizedSet.insert(0);
    }

    class RandomizedSet {
        List<Integer> list;
        Map<Integer, Integer> map;
        Random random;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            // 存在返回false
            if (map.containsKey(val)) {
                return false;
            }
            int size = list.size();
            list.add(size, val);
            map.put(val, size);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            Integer idx = map.get(val); // 删除的元素index


            Integer lastElement = list.get(list.size() - 1); // 最后一个元素的值


            // 更新最后一个元素，覆盖原来的list
            list.set(idx, lastElement);
            map.put(lastElement, idx);

            // 删除元素放最后
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }


        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

}
