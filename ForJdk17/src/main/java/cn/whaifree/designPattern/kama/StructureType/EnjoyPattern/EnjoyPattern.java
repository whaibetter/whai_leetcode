package cn.whaifree.designPattern.kama.StructureType.EnjoyPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author whai文海
 * @Date 2024/10/5 19:17
 * @注释
 */
public class EnjoyPattern {


    class Shape{
        private String id;
        private int x;
        private int y;


        public Shape(String id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    class ShareFactory{

        Map<String, Shape> map;

        public ShareFactory() {
            this.map = new HashMap<>();
        }

        public Shape setAndGetShape(String id,int x,int y){
            Shape shape = map.get(id);
            if (shape == null) {
                shape = new Shape(id, x, y);
            }
            return shape;
        }
    }

}
