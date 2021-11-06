import generator.Point;

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

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }
}
