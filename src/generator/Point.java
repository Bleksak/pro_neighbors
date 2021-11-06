package generator;

public record Point(double x, double y, int initialIndex) {
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public int getIndex() {
        return initialIndex;
    }

    public String toString() {
        return String.format("[%f; %f]", getX(), getY());
    }
}
