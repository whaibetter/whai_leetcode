package cn.whaifree.leetCode.Array;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/20 12:41
 * @注释
 */
public class LeetCode989 {

    @Test
    public void test() throws IOException {

        // 创建一个RandomAccessFile对象，用于读取指定路径下的文件
        RandomAccessFile reader = new RandomAccessFile("D:\\Downloads\\a8d88f60-603e-405f-b67b-6774dd14a507.jpg", "r");
        // 获取文件通道
        FileChannel channel = reader.getChannel();
        // 分配读取指定文件大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate((int) reader.length());
        // 从文件通道中读取数据到缓冲区
        channel.read(buffer);


        // 创建一个RandomAccessFile对象，用于将缓冲区的数据写入到新的文件
        RandomAccessFile writer = new RandomAccessFile("D:\\Downloads\\new_file.jpg", "rw");
        // 获取文件通道
        FileChannel newChannel = writer.getChannel();
        // 将缓冲区的数据写入到新的文件
        newChannel.write(buffer);
        // 关闭文件通道
        channel.close();
        newChannel.close();

        byte[] array = ByteBuffer.allocate(8).putDouble(1.0).array();
        System.out.println(Arrays.toString(array));

        int[] num = {9,9,9,9,9,9,9,9,9,9};
        int k = 1;
        Solution solution = new Solution();
        List<Integer> res = solution.addToArrayForm(num, k);
        System.out.println(res);
    }

    class Solution {
        public List<Integer> addToArrayForm(int[] num, int k) {
            int indexA = num.length - 1;
            int m = k;
            boolean flag = false;
            LinkedList<Integer> res = new LinkedList<>();
            while (indexA >= 0 && m > 0) {
                int sum = num[indexA] + m % 10;
                sum = flag ? sum + 1 : sum;
                flag = sum >= 10;

                res.addFirst(sum % 10);

                indexA--;
                m /= 10;
            }

            if (indexA < 0 && m <= 0 && flag) {
                res.addFirst(1);
                return res;
            }

            while (indexA >= 0) {
                int sum = num[indexA];
                sum = flag ? sum + 1 : sum;
                flag = sum >= 10;
                res.addFirst(sum % 10);
                indexA--;
            }

            while (m>0) {
                int sum = m % 10;
                sum = flag ? sum + 1 : sum;
                flag = sum >= 10;
                res.addFirst(sum % 10);
                m /= 10;
            }
            if (flag) {
                res.addFirst(1);
            }
            return res;
        }
    }

}
