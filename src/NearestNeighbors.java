import java.util.Arrays;

public record NearestNeighbors(Point[] points, UniformGrid grid) {

    public int[][] nearestNeighbors(int k, int[] jset) {
        boolean[][] visited = new boolean[grid.getWidth()][grid.getHeight()];
        int[][] c = new int[jset.length][k];

        DistanceIndex[] distanceIndices = new DistanceIndex[k];
        int l = 0;

        for (int r = 0; r < jset.length; ++r) {
            Point p = points[jset[r]];
            int ic = grid.getX(p);
            int jc = grid.getY(p);

            for (int i = 0; i < k; ++i) {
                distanceIndices[i].distance = Double.POSITIVE_INFINITY;
            }

            double dmin = Double.POSITIVE_INFINITY;
            double dsh = grid.getMaxDist(p);

            while (dmin > dsh) {
                int il = Math.max(1, jc - l);
                int jl = Math.max(1, jc - l);
                int ih = Math.min(grid.getWidth(), ic + l);
                int jh = Math.min(grid.getHeight(), jc + l);

                for (int i = il; i <= ih; ++i) {
                    int ji = 1;
                    if (i != il && i != ih) {
                        ji = jh - jl;
                    }

                    for (int j = jl; j < jh; j += ji) {
                        if (!visited[i][j] && !grid.get(i, j).isEmpty()) {
                            Point first = grid.get(i, j).get(0);
                            double diffX = p.getX() - first.getX();
                            double diffY = p.getY() - first.getY();

                            double disSq = diffX * diffX + diffY * diffY;
                            if (disSq < distanceIndices[k - 1].distance) {
                                distanceIndices[k - 1].distance = disSq;
                                distanceIndices[k - 1].index = r;

                                Arrays.sort(distanceIndices);
                            }

                            visited[i][j] = true;
                        }
                    }
                }

                dmin = distanceIndices[k - 1].distance;
                dsh += grid.getSize();
                l += 1;
            }

            for (int s = 0; s < k; ++s) {
                c[r][s] = distanceIndices[s].index;
            }

        }

        return c;
    }
}
