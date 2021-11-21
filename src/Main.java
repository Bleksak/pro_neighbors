
public class Main {
    public static void main(String... args) {
        double radius = 10000;
        int pointCount = 50000;

        Generator gen = new Generator(radius);

        Point[] points = gen.generate(pointCount);

        UniformGrid grid = new UniformGrid(points);

        NearestNeighbors nbs = new NearestNeighbors(points, grid);

        int[] jset = new int[]{221};
        int k = 250;

        long startTime = System.nanoTime();

        int[][] res = nbs.nearestNeighbors(k, jset);

        long endTime = System.nanoTime();

        for (int[] re : res) {
            for (int i : re) {
                System.out.println(i);
            }

            System.out.println();
        }

        System.out.printf("Runtime: %fs", (endTime - startTime)/1_000_000_000.0);
    }
}
