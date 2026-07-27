package drawshape;

class Triangle extends Shape {
    Point[] p = new Point[3];

    public Triangle(Point[] p) {
        this.p = p;
    }

    @Override
    public void draw() {
        System.out.println("P0 is ( " + p[0].getX() + ", " + p[0].getY() + "), P1 is ( " + p[1].getX() + ", " + p[1].getY() + " ), P2 is ( " + p[2].getX() + ", " + p[2].getY() + " )");
    }
}
