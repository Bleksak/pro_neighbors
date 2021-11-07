/**
 * @author Jiri Velek
 * structure for finding k nearest neighbors
 * stores point index and its distance from another point
 */
public class DistanceIndex implements Comparable<DistanceIndex> {
    public double distance;
    public int index;

    public DistanceIndex(double distance, int index) {
        this.distance = distance;
        this.index = index;
    }

    @Override
    public int compareTo(DistanceIndex o) {
        return Double.compare(distance, o.distance);
    }
}
