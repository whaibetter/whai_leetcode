package cn.whaifree.tech.demo.java;

import org.junit.Test;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/19 22:42
 * @注释
 */
public class ExceptionDemo {

    @Test
    public void testException() {
        try {
            int a = 1 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
            throw new RuntimeException();
        } finally {
            System.out.println("finally");
        }
    }

    class CustomRuntimeException extends RuntimeException {

    }
    public void testRuntimeException() {
        throw new CustomRuntimeException();
    }


    @Test
    public void testCustomException() throws CustomCheckException {
        method();
    }
    public void method() throws CustomCheckException {
        throw new CustomCheckException("自定义异常");
    }
    class CustomCheckException extends Exception {
        public CustomCheckException(String message) {
            super(message);
        }
    }

}
