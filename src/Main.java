import generator.DataSet;
import generator.Generator;
import generator.Point;

public class Main {
    public static void main(String... args) {
        Generator gen = new Generator(DataSet.UNIFORM);

        Point[] points = gen.generate(9);

        UniformGrid grid = new UniformGrid(points);

        NearestNeighbors nbs = new NearestNeighbors(points, grid);

        int[] jset = new int[]{4, 3};
        int k = 4;

        int[][] res = nbs.nearestNeighbors(k, jset);

        for (int[] re : res) {
            for (int i : re) {
                System.out.println(i);
            }

            System.out.println();
        }
    }
}
