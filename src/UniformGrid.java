import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class UniformGrid {

    private int width, height;
    private double size;

    private static final double DEFAULT_ALPHA = 1.5;
    private MinMaxBox box;
    private List<Point>[] grid;

    private double calculateSize(double alpha, double xl, double xr, double yb, double yt, int count) {
        return alpha * Math.sqrt(((xr - xl) * (yt - yb)) / count);
    }

    private int calculateRes(double max, double min) {
        return (int) ((max - min) / size);
    }

    public UniformGrid(Point[] points, double alpha) {
        box = new MinMaxBox(points);
        size = calculateSize(alpha, box.getMinX(), box.getMaxX(), box.getMinY(), box.getMaxY(), points.length);
        width = calculateRes(box.getMaxX(), box.getMinX());
        height = calculateRes(box.getMaxY(), box.getMinY());

        grid = (ArrayList<Point>[]) new ArrayList[width * height];

        for(Point p : points) {
            int x = getX(p);
            int y = getY(p);

            int index = y * width + x;

            if(grid[index] == null) {
                grid[index] = new ArrayList<>(32);
            }

            grid[index].add(p);
        }
    }

    public UniformGrid(Point[] points) {
        this(points, DEFAULT_ALPHA);
    }

    public List<Point> get(int x, int y) {
        return grid[y * width + x];
    }

    public int getX(Point p) {
        return (int) ((p.getX() - box.getMinX()) / width);
    }

    public int getY(Point p) {
        return (int) ((p.getY() - box.getMinY()) / height);
    }

    public double getMaxDist(Point p) {
        int x = getX(p);
        int y = getY(p);

        double xCellPos = (p.getX() - box.getMinX()) / width;
        double yCellPos = (p.getY() - box.getMinY()) / height;

        double xLDist = xCellPos - x;
        double xRDist = (x+1) - xCellPos;

        double yTDist = yCellPos - y;
        double yBDist = (y+1) - yCellPos;

        double xDist = Math.max(xLDist, xRDist);
        double yDist = Math.max(yTDist, yBDist);

        return Math.max(xDist, yDist);
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
