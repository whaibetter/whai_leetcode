package cn.whaifree.leetCode.BackTracking;

import org.junit.Test;

import java.util.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/2/20 10:29
 * @注释
 */
public class LeetCode332difficult {
    @Test
    public void test() {
        List<List<String>> tickets = new ArrayList<>();
        // 加上这一串  [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//        tickets.add(new ArrayList<String>() {{
//            add("JFK");
//            add("SFO");
//        }});
//        tickets.add(new ArrayList<String>() {{
//            add("JFK");
//            add("ATL");
//        }});
//        tickets.add(new ArrayList<String>() {{
//            add("SFO");
//            add("ATL");
//        }});
//        tickets.add(new ArrayList<String>() {{
//            add("ATL");
//            add("JFK");
//        }});
//        tickets.add(new ArrayList<String>() {{
//            add("ATL");
//            add("SFO");
//        }});

        String[][] routes = {
                {"JFK", "SFO"},
                {"JFK", "ATL"},
                {"SFO", "JFK"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"},
                {"ATL", "AAA"},
                {"AAA", "BBB"},
                {"BBB", "ATL"}
        };


        for (String[] route : routes) {
            tickets.add(Arrays.asList(route));
        }

        Solution1 solution = new Solution1();
        solution.findItinerary(tickets).forEach(
                list -> {
                    System.out.println(list);
                }
        );
    }

    /**
     * 超时
     */
    class Solution {

        List<String> res = new ArrayList<>();
        private LinkedList<String> path = new LinkedList<>();
        boolean[] used = null;
        public List<String> findItinerary(List<List<String>> tickets) {
            Collections.sort(tickets, new Comparator<List<String>>() {
                @Override
                public int compare(List<String> a, List<String> b) {
                    // 两个字符串比较
                    return a.get(1).compareTo(a.get(1));
                }
            });
            // 最后，会按照目的地进行排序["JFK","SFO"],["JFK","ATL"],会变成["JFK","ATL"],["JFK","SFO"]

            path.add("JFK");
            used = new boolean[tickets.size()];

            // 1. 字典排序选择
            // 2. 已经走过的标记
            backTracking(tickets);
            return res;
        }

        public void backTracking(List<List<String>> tickets) {
            if (path.size() == 1 + tickets.size()) {
                res = new LinkedList(path);
                return;
            }

            int size = tickets.size();
            for (int i = 0; i < size; i++) {
                if (!used[i] && path.getLast().equals(tickets.get(i).get(0))) {
                    String target = tickets.get(i).get(1);
                    path.add(target);
                    used[i] = true;

                    backTracking(tickets);

                    used[i] = false;
                    path.removeLast();

                }

            }

        }

//        /**
//         * 在tickets返回未被use的索引，多个索引进行比较
//         *
//         * @param tickets
//         * @return 在tickets中的索引
//         */
//        public int compareAndUnUsed(List<List<String>> tickets, String start) {
//            int res = -1;
//            for (int i = 0; i < tickets.size(); i++) {
//                List<String> aPath = tickets.get(i);
//                String st = aPath.get(0);
//                if (start.equals(st) && used[i] == false) {
//                    if (res != -1) {
//                        String target = aPath.get(1);
//                        String beforeRes = tickets.get(res).get(1);
//                        if (!compareBeforeIsBetter(beforeRes, target)) {
//                            res = i;
//                        }
//                    }else {
//                        res = i;
//                    }
//                }
//            }
//            return res;
//        }
//
//        public boolean compareBeforeIsBetter(String before,String after) {
//            for (int i = 0; i < 3; i++) {
//                char be = before.charAt(i);
//                char af = after.charAt(i);
//                if (be > af) {
//                    return false;
//                } else if (be < af) {
//                    return true;
//                }
//                // 如果字母相同，比较下一个字母
//            }
//            return true;
//        }


    }


    class Solution1 {

        List<String> res = new ArrayList<>();
        private LinkedList<String> path = new LinkedList<>();
        boolean[] used = null;
        public List<String> findItinerary(List<List<String>> tickets) {
            Collections.sort(tickets, new Comparator<List<String>>() {
                @Override
                public int compare(List<String> a, List<String> b) {
                    // 两个字符串比较
                    return a.get(1).compareTo(b.get(1));
                }
            });
            // 最后，会按照目的地进行排序["JFK","SFO"],["JFK","ATL"],会变成["JFK","ATL"],["JFK","SFO"]

            path.add("JFK");
            used = new boolean[tickets.size()];

            // 1. 字典排序选择
            // 2. 已经走过的标记
            backTracking(tickets);
            return res;
        }

        public boolean backTracking(List<List<String>> tickets) {
            if (path.size() == 1 + tickets.size()) {
                res = new LinkedList(path);
                return true;
            }

            for (int i = 0; i < tickets.size(); i++) {
                if (!used[i] && path.getLast().equals(tickets.get(i).get(0))) {
                    String target = tickets.get(i).get(1);
                    path.add(target);
                    used[i] = true;

                    if (backTracking(tickets)) {
                        return true;
                    }


                    used[i] = false;
                    path.removeLast();

                }

            }
            return false;

        }
    }
}
