package cn.whaifree.designPattern.kama.StructureType.ProxyPattern;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 16:30
 * @注释
 */
public class ProxyPattern {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        Integer[] sizes = new Integer[i];
        for (int i1 = 0; i1 < i; i1++) {
            sizes[i1] = scanner.nextInt();
        }

        User user = new User();
        Proxy proxy = new Proxy(sizes);
        proxy.rent(user);
    }
    @Data
    static class House{
        int size;

        public House(int size) {
            this.size = size;
        }
    }

    static class Proxy {

        List<House> housee;


        // 所有房源
        public Proxy(Integer... integers) {
            housee = Arrays.stream(integers).map(House::new).collect(Collectors.toList());
        }

        public void rent(User user) {
            housee.forEach(
                    house -> {
                        if (house.size < 100) {
                            System.out.println("No");
                        }else {
                            user.rent();
                        }
                    }
            );
        }

    }

    static class User{
        public void rent() {
            System.out.println("YES");
        }
    }
}
