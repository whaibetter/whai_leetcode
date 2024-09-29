package cn.whaifree.designPattern;

public class AbstractFactoryPattern {

    // https://kamacoder.com/problempage.php?pid=1077
    public static void main(String[] args) {
        ModernFactory factory = new ModernFactory();
        Sofa sofa = factory.generateSofa();
        sofa.proSofa();
        Chair chair = factory.generateChair();
        chair.proChair();
        ClassicFactory classicFactory = new ClassicFactory();
        Sofa sofa1 = classicFactory.generateSofa();
        sofa1.proSofa();
        Chair chair1 = classicFactory.generateChair();
        chair1.proChair();
    }
}


interface Sofa{
    void proSofa();
}

interface Chair{
    void proChair();
}

class ClassSofa implements Sofa{

    @Override
    public void proSofa() {
        System.out.println("class sofa");
    }
}

class ModernSofa implements Sofa{

    @Override
    public void proSofa() {
        System.out.println("modern sofa");
    }
}

class ClassChair implements Chair{

    @Override
    public void proChair() {
        System.out.println("class chair");
    }
}

class ModernChair implements Chair{

    @Override
    public void proChair() {
        System.out.println("modern chair");
    }
}




interface AbstractFactory{
    Sofa generateSofa();
    Chair generateChair();
}

class ModernFactory implements AbstractFactory {

    @Override
    public Sofa generateSofa() {
        return new ModernSofa();
    }

    @Override
    public Chair generateChair() {

        return new ModernChair();
    }

}

class ClassicFactory implements AbstractFactory {

    @Override
    public Sofa generateSofa() {

        return new ClassSofa();
    }

    @Override
    public Chair generateChair() {

        return new ClassChair();
    }

}




