package cn.whaifree.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/3/14 14:58
 * @注释
 */
public class LambdaClass {
    public static void main(String[] args) {





//        new LambdaClass().lambdaInterfaceDemo(()-> System.out.println("自定义函数式接口"));

    }
    //函数式接口参数
    void lambdaInterfaceDemo(Custom i){
        i.f();
    }
}


class User {
    int age;
    String name;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }


        User other = (User) obj;
        if (age == other.age && other.name.equals(name)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

}

class CustomSet extends HashSet {




    @Override
    public boolean equals(Object o)
    {

        if (o == this) // 同一个对象
            return true;
        if (!(o instanceof Set))  // 不是Set接口的实现
            return false;

        Collection<?> c = (Collection<?>) o;
        if (c.size() != size()) // 大小不一样
            return false;
        try {
            //挨个遍历，是否存在e
            for (Object e : c){
                if (!contains(e))
                    return false;
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
