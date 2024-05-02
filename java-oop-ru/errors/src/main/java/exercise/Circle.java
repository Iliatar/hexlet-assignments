package exercise;

// BEGIN
public class Circle {
    Point center;
    int radius;

    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare()  throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException();
        }
        return radius * radius * Math.PI;
    }
}
// END
