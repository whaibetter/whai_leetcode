package cn.whaifree.designPattern.ChainPattern;

import lombok.Setter;

import java.util.Scanner;

/**
 *
 * <a href="https://kamacoder.com/problempage.php?pid=1100">...</a>
 *
 * @version 1.0
 * @Author whai文海
 * @Date 2024/9/12 23:37
 * @注释
 */
public class ChainPattern {





}

class Main{
    public static void main(String[] args) {
        Audit.Builder builder = new Audit.Builder();
        AbstractAudit build = builder.setAudit(new Supervisor())
                .setAudit(new Manager())
                .setAudit(new Director())
                .build();


        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        for (int j = 0; j < i; j++) {
            build.process(scanner.next(), scanner.nextInt());
        }
    }


}

interface Audit{
    default void def() {

    }

    static void main() {

    }

    void process(String name,int day);

    public static class Builder{
        AbstractAudit head;

        public Builder setAudit(AbstractAudit audit) {
            if (head == null) {
                head = audit;
            }else {
                AbstractAudit index = head;
                index.setNext(audit);
            }
            return this;
        }

        public AbstractAudit build(){
            return head;
        }

    }

}

@Setter
abstract class AbstractAudit implements  Audit{
    Audit next;

    @Override
    public void def() {
        Audit.super.def();
    }
}

class Supervisor extends AbstractAudit {

    @Override
    public void process(String name,int day) {
        if (day <= 3) {
            System.out.println(name + " Approved by Supervisor");
        }
        else {
            this.next.process(name, day);
        }
    }
}

class Manager extends AbstractAudit {

    @Override
    public void process(String name,int day) {
        if (day > 3 && day <= 7) {
            System.out.println(name + " Approved by Manager");
        }else {
            this.next.process(name, day);
        }
    }
}

class Director extends AbstractAudit {

    @Override
    public void process(String name,int day) {
        if (day<=10&&day > 7) {
            System.out.println(name + " Approved by Director");
        }else {
            System.out.println(name + " Denied by Director.");
        }
    }
}

