/**
 * @author Jiri Velek
 * calculates the min-max box of point array
 */
public class MinMaxBox {
    private double minX, maxX;
    private double minY, maxY;

    public MinMaxBox(Point[] points) {
        for (Point p : points) {
            if (p.getX() < minX) {
                minX = p.getX();
            }

            if(p.getX() > maxX) {
                maxX = p.getX();
            }

            if(p.getY() < minY) {
                minY = p.getY();
            }

            if(p.getY() > maxY) {
                maxY = p.getY();
            }
        }
    }

    /**
     * @return max X value
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * @return max Y value
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * @return min X value
     */
    public double getMinX() {
        return minX;
    }

    /**
     * @return min Y value
     */
    public double getMinY() {
        return minY;
    }
}
