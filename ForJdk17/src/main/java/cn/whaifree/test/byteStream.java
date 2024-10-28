package cn.whaifree.test;



import cn.whaifree.redo.redoAll.LeetCode11;

import java.io.*;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/1 22:08
 * @注释
 */
public class byteStream {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(out);
        objectOutputStream1.writeObject(new LeetCode11());

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(out.toByteArray());
        //转为对象
        ObjectInputStream objectInputStream1 = new ObjectInputStream(byteArrayInputStream);
        LeetCode11 o1 = (LeetCode11)objectInputStream1.readObject();
        o1.test();




        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("cc.txt"));
        LeetCode11 obj = new LeetCode11();
        obj.test();
        objectOutputStream.writeObject(obj);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("cc.txt"));
        LeetCode11 o = (LeetCode11) objectInputStream.readObject();
        o.test();
        objectOutputStream.close();
    }
}
