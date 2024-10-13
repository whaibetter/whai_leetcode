package cn.whaifree.redo.redo_all_240924;

import org.junit.Test;

import java.util.Arrays;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/12 12:01
 * @注释
 */
public class LeetCode289 {

    @Test
    public void test() {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        new Solution().gameOfLife(board);
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    class Solution {
        /**
         * 为了保证当前修改后的状态不会影响下一个状态的判定，设置另外的状态
         * 如题所示，只有三种：
         * 1. 如果当前是活细胞，但是变成了死细胞，那么设置为-1  math.abs为1
         * 2. 如果当前是活细胞，仍然是活细胞，那么不变仍为1 math.abs还是1
         * 3. 如果当前是死细胞，但是变成了活细胞，那么设置为2
         * 那么最后遍历修改完状态之后，将-1修改回为0，2修改回为1
         *
         * @param board
         */
        public void gameOfLife(int[][] board) {
            // 设置方向来遍历某个节点周围的另外几个节点
            int[] ner = new int[] { -1, 0, 1 };
            // 获取行和列
            int rows = board.length;
            int cols = board[0].length;
            // 遍历每一个节点格子
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    // 设置当前节点周围的存活细胞的数量
                    int liveNer = 0;
                    /**
                     * 当前节点是[ i , j ]
                     * [i-1,j-1] [i-1,j] [i-1,j+1]
                     * [ i ,j-1] [ i ,j] [ i ,j+1]
                     * [i+1,j-1] [i+1,j] [i+1,j+1]
                     * 那么以当前节点为中心，要求周围的节点，则最多是3*3形式
                     * 并且所有的行和列都是用当前节点+1或者-1或者不变构成
                     * 所以我们设置 ner = {-1,0,1} 来形成遍历
                     */
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            // 必须保证不计算当前节点(不计算自己)
                            if (!(ner[i] == 0 && ner[j] == 0)) {
                                // 当前节点的相邻节点坐标
                                int r = row + ner[i];
                                int c = col + ner[j];
                                /**
                                 * 判断当前周围节点的存活状态，要求满足两个状态
                                 * 1. 必须保证要在 board 矩阵中
                                 * 2. 并且起始状态要是存活，则当前状态为1或者-1都可以(因为这两个状态都表示起始状态为活细胞)
                                 **/
                                if ((r >= 0 && r < rows) && (c >= 0 && c < cols) && (Math.abs(board[r][c]) == 1)) {
                                    liveNer++;
                                }
                            }
                        }
                    }
                    /**
                     * 开始判断当前节点的存活状态
                     * 因为遍历到当前节点的时候，还没有开始修改细胞状态，所以还是0和1的细胞状态
                     * 那么只需要判断状态变化的即可，否则状态不变
                     **/
                    if ((board[row][col] == 1) && (liveNer > 3 || liveNer < 2)) {
                        // -1 代表这个细胞过去是活的现在死了
                        board[row][col] = -1;
                    }
                    if (board[row][col] == 0 && (liveNer == 3)) {
                        // 2 代表这个细胞过去是死的现在活了
                        board[row][col] = 2;
                    }
                }
            }
            // 再把额外的状态修改回去
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (board[row][col] == 2) {
                        board[row][col] = 1;
                    }
                    if (board[row][col] == -1) {
                        board[row][col] = 0;
                    }
                }
            }

        }

    }
}
