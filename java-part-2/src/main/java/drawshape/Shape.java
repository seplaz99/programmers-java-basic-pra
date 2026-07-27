package drawshape;

public class Shape {

    private String color = "black";

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void draw() {
        System.out.println("Color is " + color);
    }
}
