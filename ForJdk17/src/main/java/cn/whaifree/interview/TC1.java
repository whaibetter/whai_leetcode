package cn.whaifree.interview;

import java.util.*;

public class TC1 {
}

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int i = in.nextInt();
        int size = in.nextInt();
        String next = in.next();
        char[] charArray = next.toCharArray();
        int[] nums = new int[i];
        for (int i1 = 0; i1 < charArray.length; i1++) {
            nums[i1] = charArray[i1] - '0';
        }
        method(nums, size);

    }


    public static void method(int[] nums, int k) {

        int c = Integer.MAX_VALUE;

        for (int i = 0; i <= nums.length - k; i++) {
            int left = i;
            int right = i + k - 1;
            c = Math.min(c(nums, left, right, c), c);
        }
        System.out.println(c);
    }

    public static int c(int[] nums, int left, int right,int minNow) {
        int[] newNums = Arrays.copyOfRange(nums, left, right + 1);
        Arrays.sort(newNums);
        left = 0;
        right = newNums.length - 1;
        int ans = 0 ;
        while (left < right) {
            ans += newNums[right] - newNums[left];
            if (ans >= minNow) {
                return minNow;
            }
            left++;
            right--;
        }
        return ans;
    }
}

class p2{

        public static void main(String[] args) {
            Scanner in = new Scanner(System.in);
            // 注意 hasNext 和 hasNextLine 的区别
            int points = in.nextInt();
            int line = in.nextInt();
            int s = in.nextInt();
            int[][] map = new int[points][points];
            for (int i = 0; i < map.length; i++) {
                Arrays.fill(map[i], Integer.MIN_VALUE);
            }
            for (int i = 0; i < line; i++) {
                int a = in.nextInt();
                int b = in.nextInt();
                int c = in.nextInt();
                map[a-1][b-1] = c;
                map[b-1][a-1] = c;
            }

            for (int k = 0; k < points; k++) {
                for (int i = 0; i < points; i++) {
                    for (int j = 0; j < points; j++) {
                        if (map[i][k] != Integer.MIN_VALUE && map[k][j] != Integer.MIN_VALUE) {
                            map[i][j] = Math.max(map[i][j], map[i][k] + map[k][j]);
                        }
                    }
                }
            }

            System.out.println(map);

        }
}



class zgyd1{
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param nums1 int整型ArrayList
     * @param nums2 int整型ArrayList
     * @return int整型ArrayList
     */
    public ArrayList<Integer> intersection (ArrayList<Integer> nums1, ArrayList<Integer> nums2) {
        HashSet<Integer> set1 = new HashSet<>(nums1);
        HashSet<Integer> set2 = new HashSet<>(nums2);
        set1.retainAll(set2);
        return new ArrayList<>(set1);
    }
}

class zgdx1{


    public static void main(String[] args) {
        int[][] ints = {
                {1,0},
                {2,1}
        };
        zgdx1 zgdx1 = new zgdx1();
        System.out.println(Arrays.toString(zgdx1.findOrder(ints, 3)));
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * leetcode210
     *
     * @param prerequisites int整型二维数组
     * @param n int整型
     * @return int整型一维数组
     */
    public int[] findOrder (int[][] prerequisites, int n) {
        if (n==0) return new int[0];
        int[] inDegress = new int[n];
        for (int[] p : prerequisites) {
            inDegress[p[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegress.length; i++) {
            if (inDegress[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;
        int[] res = new int[n];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[count++] = cur;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == cur) {
                    inDegress[prerequisite[0]]--;
                    if (inDegress[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        if (count == n) {
            return res;
        }
        return new int[0];
    }
}

class t210{
    // 方法 1 最简单的 BFS
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) return new int[0];
        int[] inDegrees = new int[numCourses];


        // 建立入度表
        for (int[] p : prerequisites) {
            inDegrees[p[0]]++; // 记录每个节点的入度
        }
        // 入度为0的节点队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) queue.offer(i); // 入度为 0 的节点可以进行执行
        }
        int count = 0;  // 记录可以执行的任务数
        int[] res = new int[numCourses];  // 完整拓扑排序的执行过程

        // 根据提供的可以执行的任务（入度为 0），删除入度为 0 的节点
        while (!queue.isEmpty()){
            int curr = queue.poll(); // 拿到一个可以执行的任务
            res[count++] = curr;   //  这个任务可以执行，作为下一次执行的节点
            for (int[] p : prerequisites) {
                if (p[1] == curr){  //  {a,b} 表示 a 依赖 b b-->a
                    inDegrees[p[0]]--;
                    if (inDegrees[p[0]] == 0) queue.offer(p[0]);
                }
            }
        }
        if (count == numCourses) return res;
        return new int[0];
    }


}