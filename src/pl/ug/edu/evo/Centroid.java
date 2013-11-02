package pl.ug.edu.evo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a centroid in k-means algorithm
 * @author jfk
 */
public class Centroid extends Point {

  private Set<Point> pointsInCluster = new HashSet<>();
  
  public Centroid(List<Double> _position) {
    super(_position);
  }
  
  public void clusterPoint(Point p) {
    pointsInCluster.add(p);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Centroid{ ");
    sb.append(super.toString());
    sb.append(" with ");
    sb.append(pointsInCluster.toString());
    sb.append("}");
    return sb.toString();
  }
}
