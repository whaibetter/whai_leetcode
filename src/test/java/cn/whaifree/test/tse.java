package cn.whaifree.test;

public class tse {

    final static Object o = new Object();
    volatile static int num = 0;
    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        synchronized (o) {
                            while (num % 3 != 0) {
                                o.wait();
                            }
                            num++;
                            System.out.println("A");
                            o.notifyAll();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        synchronized (o) {
                            while (num % 3 != 1) {
                                o.wait();
                            }
                            num++;
                            System.out.println("B");
                            o.notifyAll();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        synchronized (o) {
                            while (num % 3 != 2) {
                                o.wait();
                            }
                            num++;
                            System.out.println("C");
                            o.notifyAll();
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
