package drawshape;

public class Circle extends Shape {
    Point center; //원의 원점좌표
    int r; // 반지름

    public Circle() {
        this(new Point(0, 0), 100);
    }

    public Circle(Point center, int r) {
        this.center = center;
        this.r = r;
        setColor("red");
    }

    @Override
    public void draw() {
        System.out.println("Center is (" + center.getX() + ", " + center.getY() + "), r is " + r + ", color is " + getColor());
    }
}
