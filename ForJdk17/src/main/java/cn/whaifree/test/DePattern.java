package cn.whaifree.test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/4/20 16:31
 * @注释
 */
public class DePattern {
    /**
     *
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        // 创建一个 FileInputStream 对象，用于读取文件
        FileInputStream fileInputStream = new FileInputStream("file");
        // BufferedInputStream 装饰器类
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);


        new RobotDecorator(new FirstRobot()).doMoreThing();
    }
}
interface Robot {
    public void doMoreThing();
}

class FirstRobot implements Robot {
    public void doMoreThing()
    {
        System.out.println("初始机器人的功能：唱歌");
    }
}

class RobotDecorator {

    private Robot robot;
    public RobotDecorator(Robot robot)
    {
        this.robot = robot;
    }

    public void doMoreThing()
    {
        System.out.println("装饰器增加功能：跳舞");
        robot.doMoreThing();
    }

}
