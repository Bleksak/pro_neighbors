package generator;

public record Generator(DataSet set) {
    public Point[] generate(int n) {
        Point[] points = new Point[n];

        int width, height;

        switch (set) {
            case GAP -> {

            }

            case CORNERS -> {

            }

            case UNIFORM -> {
                int sq = (int) Math.sqrt(n);
                width = height = sq;

                for (int y = 0; y < height; ++y) {
                    for (int x = 0; x < width; ++x) {
                        int index = y * width + x;
                        points[index] = new Point(x, y, index);
                    }
                }

                if(width * height < n) {
                    int remaining = n - width * height;

                    for(int i = 0; i < remaining; ++i) {
                        int index = width * height + i;
                        double px = width + i * 0.1;
                        points[index] = new Point(px, height, index);
                    }
                }

            }
        }

        return points;
    }
}
