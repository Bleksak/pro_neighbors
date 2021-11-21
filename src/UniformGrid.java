import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiri Velek
 * Uniform grid for easier data manipulation
 */
public class UniformGrid {

    private static final double DEFAULT_ALPHA = 1.5;
    private final int width, height;
    private final double size;
    private final MinMaxBox box;
    private final List<Point>[] grid;

    public UniformGrid(Point[] points, double alpha) {
        box = new MinMaxBox(points);
        size = calculateSize(alpha, points.length);
        width = calculateRes(box.getMaxX(), box.getMinX());
        height = calculateRes(box.getMaxY(), box.getMinY());

        //noinspection unchecked
        grid = (ArrayList<Point>[]) new ArrayList[width * height];

        for (Point p : points) {
            int x = getX(p);
            int y = getY(p);

            int index = y * width + x;

            if (grid[index] == null) {
                grid[index] = new ArrayList<>(32);
            }

            grid[index].add(p);
        }
    }

    public UniformGrid(Point[] points) {
        this(points, DEFAULT_ALPHA);
    }

    private double calculateSize(double alpha, int count) {
        return alpha * Math.sqrt(((box.getMaxX() - box.getMinX()) * (box.getMaxY() - box.getMinY())) / count);
    }

    private int calculateRes(double max, double min) {
        int res = (int) ((max - min) / size);

        return res == 0 ? 1 : res;
    }

    public List<Point> get(int x, int y) {
        return grid[y * width + x];
    }

    public boolean isEmpty(int x, int y) {
        List<Point> list = get(x, y);
        if (list == null) {
            return true;
        }

        return list.isEmpty();
    }

    public double getXAsDouble(Point p) {
        double cellWidth = (box.getMaxX() - box.getMinX()) / width;
        return ((p.getX() - box.getMinX()) / cellWidth);
    }

    public double getYAsDouble(Point p) {
        double cellHeight = (box.getMaxY() - box.getMinY()) / height;
        return ((p.getY() - box.getMinY()) / cellHeight);
    }

    public int getX(Point p) {
        int x = (int) getXAsDouble(p);

        return x >= width ? x - 1 : x;
    }

    public int getY(Point p) {
        int y = (int) getYAsDouble(p);

        return y >= height ? y - 1 : y;
    }

    public double getMinDist(Point p) {
        int x = getX(p);
        int y = getY(p);

        double xCellPos = getXAsDouble(p);
        double yCellPos = getYAsDouble(p);

        double xLDist = xCellPos - x;
        double xRDist = (x + 1) - xCellPos;

        double yTDist = yCellPos - y;
        double yBDist = (y + 1) - yCellPos;

        double xDist = Math.min(xLDist, xRDist);
        double yDist = Math.min(yTDist, yBDist);

        return Math.min(xDist, yDist);
    }

    public double getSize() {
        return size;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
