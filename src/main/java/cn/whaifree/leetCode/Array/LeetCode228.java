package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/22 14:56
 * @注释
 */
public class LeetCode228 {

    public static void main(String[] args) throws IOException {
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(Paths.get("D:\\project\\LeetCode\\README.md"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Future<Integer> result = fileChannel.read(buffer, 0);
        while (!result.isDone()) {
            // do something
        }
    }

    @Test
    public void test() {
        int[] nums = {0, 2};
        System.out.println(new Solution().summaryRanges(nums));
    }

    class Solution {

//        public List<String> summaryRanges(int[] nums) {
//            List<String> path = new ArrayList<>();
//            List<String> res = new ArrayList<>();
//            for (int i = 1; i < nums.length; i++) {
//                if (nums[i] != nums[i - 1]) {
//                    StringBuilder str = new StringBuilder();
//                    for (int j = 0; j < path.size()-1; j++) {
//                        str.append(path.get(j));
//                        str.append("->");
//                    }
//                    str.append(path.get(path.size() - 1));
//                    path.clear();
//                }
//                path.add(String.valueOf(nums[i]));
//            }
//        }
        public List<String> summaryRanges(int[] nums) {


            List<String> res = new ArrayList<>();

            int left = 0;
            int right = 1;
            while (right < nums.length) {
                if (nums[right] != nums[right - 1] + 1) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(nums[left]);
                    if (left != right - 1) {
                        stringBuilder.append("->");
                        stringBuilder.append(nums[right - 1]);
                    }
                    res.add(stringBuilder.toString());
                    left = right;
                }
                right++;
            }
            if (left < nums.length) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(nums[left]);
                if (left != right - 1) {
                    stringBuilder.append("->");
                    stringBuilder.append(nums[right - 1]);
                }
                res.add(stringBuilder.toString());
            }
            return res;
        }
    }
}
