package cn.whaifree.interview.xiaotiancai;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/17 0:00
 * @注释
 */
public class P1 {

    static {
        System.out.println(1);
    }
    public static void main(String[] args) {
        System.out.println(1/0);
        System.out.println(3);
    }

    static {
        System.out.println(2);
    }

    class par{
        void test() {
            System.out.println(1/0);
        }
    }

    class sun extends par {
        public void test() {

        }

    }
}
