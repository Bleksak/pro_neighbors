import java.util.Arrays;

/**
 * @author Jiri Velek
 * algorithm to find k nearest neighbors
 */
public record NearestNeighbors(Point[] points, UniformGrid grid) {

    /**
     * @param k    how many neighbors to find
     * @param jset for which points should we find k neighbors
     * @return set c[r][s] where r is from jset and s is in range <0; k)
     */
    public int[][] nearestNeighbors(int k, int[] jset) {
        int[][] c = new int[jset.length][k];
        DistanceIndex[] distanceIndices = new DistanceIndex[k];

        for (int r = 0; r < jset.length; ++r) {
            boolean[][] visited = new boolean[grid.getWidth()][grid.getHeight()];
            int l = 1;

            Point p = points[jset[r]];
            int ic = grid.getX(p);
            int jc = grid.getY(p);

            for (int i = 0; i < k; ++i) {
                distanceIndices[i] = new DistanceIndex(Double.POSITIVE_INFINITY, i);
            }

            double dmin = Double.POSITIVE_INFINITY;
            double dsh = grid.getMinDist(p);

            while (dmin > dsh) {
                int il = Math.max(0, jc - l);
                int ih = Math.min(grid.getWidth() - 1, ic + l);

                int jl = Math.max(0, jc - l);
                int jh = Math.min(grid.getHeight() - 1, jc + l);

                for (int i = il; i <= ih; ++i) {
                    int ji;
                    if (i == il || i == ih) ji = 1;
                    else ji = jh - jl;

                    for (int j = jl; j <= jh; j += ji) {
                        if (!visited[i][j] && !grid.isEmpty(i, j)) {

                            for (Point current : grid.get(i, j)) {
                                if (current == p) continue;

                                double diffX = p.getX() - current.getX();
                                double diffY = p.getY() - current.getY();

                                double dis = Math.sqrt(diffX * diffX + diffY * diffY);
                                if (dis < distanceIndices[k - 1].distance) {
                                    distanceIndices[k - 1].distance = dis;
                                    distanceIndices[k - 1].index = current.getIndex();

                                    Arrays.sort(distanceIndices);
                                }
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
