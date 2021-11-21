import java.util.Random;

/**
 * Data generator
 */
public class Generator {
    private static final double CENTER_X = 0;
    private static final double CENTER_Y = 0;
    private static final int BOUND = 5000;
    private Random rnd = new Random();
    private double radius;

    /**
     * creates a new generator with specified radius for a circle
     *
     * @param radius radius for a circle
     */
    public Generator(double radius) {
        this.radius = radius;
    }

    /**
     * generates a random point within a circle
     *
     * @return new random position in circle
     */
    public Point randomPoint(int index) {
        double theta = 2 * Math.PI * rnd.nextDouble();
        double r = Math.sqrt(rnd.nextDouble());

        return new Point(
                CENTER_X + r * radius * Math.cos(theta),
                CENTER_Y + r * radius * Math.sin(theta),
                index
        );
    }

    /**
     * Generates random data to a file
     *
     * @param count number of points
     * @return generated points
     */
    public Point[] generate(int count) {
        Point[] points = new Point[count];

        for (int i = 0; i < count; ++i) {
            points[i] = randomPoint(i);
        }

        return points;
    }
}
