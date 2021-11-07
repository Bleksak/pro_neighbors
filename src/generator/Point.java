package generator;

/**
 * @author Jiri Velek
 * point in 2D space
 */
public record Point(double x, double y, int initialIndex) {

    /**
     * @return x coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * @return y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * @return index of a point in a point array
     */
    public int getIndex() {
        return initialIndex;
    }

    @Override
    public String toString() {
        return String.format("[%f; %f]", getX(), getY());
    }
}
