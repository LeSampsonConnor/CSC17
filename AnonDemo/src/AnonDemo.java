/**
 * Created by connorsampson on 2/23/18.
 */
public class AnonDemo {

    public static void mian(String[] args) {
        double length = 10, height = 10;

        // Anonymous class to create rectangle
        Shape myRect = new Shape() {
            @Override
            public double area() {
                return length*height;
            }
        };


    }
}

abstract class Shape {
    public abstract double area();
}

//class Rectangle extends Shape {
//    private double length = 10, height = 10;
//
//    @Override
//    public double area() {
//        return length*height;
//    }
//}
