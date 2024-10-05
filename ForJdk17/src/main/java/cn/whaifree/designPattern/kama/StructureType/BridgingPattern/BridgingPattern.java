package cn.whaifree.designPattern.kama.StructureType.BridgingPattern;

import java.util.Scanner;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 18:36
 * @注释
 */
public class BridgingPattern {

    interface TEL{
        public void showBrand();
    }
    static class Sony implements TEL{
        public void showBrand(){
            System.out.print("Sony TV");
        }
    }
    static class TCL implements TEL{
        public void showBrand(){
            System.out.print("TCL TV");
        }
    }
    static abstract class Operation{
        protected TEL tel;

        public void setTel(TEL tel){
            this.tel = tel;
        }

        public abstract void operation();
    }
    static class Open extends Operation{
        public void operation(){
            tel.showBrand();
            System.out.println(" is ON");
        }
    }
    static class Close extends Operation{
        public void operation(){
            tel.showBrand();
            System.out.println(" is OFF");
        }
    }
    static class Switch extends Operation{
        public void operation(){
            System.out.print("Switching ");
            tel.showBrand();
            System.out.println(" channel");
        }
    }
    class Main{
        public static void main (String[] args) {
            Scanner sc = new Scanner(System.in);
            int nums = sc.nextInt();
            TEL tel;
            Operation opera;
            while(nums-- > 0){
                int type = sc.nextInt();
                int move = sc.nextInt();
                if(type == 0){
                    tel = new Sony();
                }else{
                    tel = new TCL();
                }
                if(move == 2){
                    opera = new Open();
                    opera.setTel(tel);
                    opera.operation();
                }else if(move == 3){
                    opera = new Close();
                    opera.setTel(tel);
                    opera.operation();
                }else{
                    opera = new Switch();
                    opera.setTel(tel);
                    opera.operation();
                }
            }
        }
    }
}
