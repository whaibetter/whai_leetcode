package cn.whaifree.designPattern.kama.CreateType.BuildPattern;

import java.util.Scanner;

/**
 *
 * 构造器模式，
 *         public BCycle build() {
 *             return new BCycle(this);
 *         }
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/4 18:18
 * @注释
 */
public class BuildPattern {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        for (int i1 = 0; i1 < i; i1++) {
            String next = scanner.next();
            BCycle bCycle = RoadType.getBCycle(next);
            System.out.println(bCycle);
        }
    }
}

enum RoadType{
    MOUNTAIN("mountain",BCycle.newBuilder().setFrame("Aluminum Frame").setTries("Knobby Tires").build()),
    ROAD("road",BCycle.newBuilder().setFrame("Carbon Frame").setTries("Slim Tires").build());

    String key;
    BCycle bCycle;

    RoadType(String road, BCycle build) {
        this.key = road;
        this.bCycle = build;
    }
    public static BCycle getBCycle(String key){
        for (RoadType roadType : RoadType.values()) {
            if(roadType.key.equals(key)){
                return roadType.bCycle;
            }
        }
        return null;
    }
}



class BCycle{
    private String frame;
    private String tires;

    public BCycle(Builder builder) {
        this.frame = builder.frame;
        this.tires = builder.tires;
    }

    static Builder newBuilder(){
        return new Builder();
    }


    static class Builder{

        private String frame;
        private String tires;

        public BCycle build() {
            return new BCycle(this);
        }

        public Builder setFrame(String frame) {
            this.frame = frame;
            return this;
        }

        public Builder setTries(String tries) {
            this.tires = tries;
            return this;
        }
    }

    @Override
    public String toString() {
        return frame + " " + tires;
    }
}



