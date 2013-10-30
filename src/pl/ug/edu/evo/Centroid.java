package pl.ug.edu.evo;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a centroid in k-means algorithm
 * @author jfk
 */
public class Centroid {

  private Point position;
  private Set<Point> pointsInCluster = new HashSet<>();
  
  public Centroid(Point _position) {
    position = _position;
  }
  
  public void clusterPoint(Point p) {
    pointsInCluster.add(p);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Centroid<");
    sb.append(position.toString());
    sb.append(" with ");
    sb.append(pointsInCluster.toString());
    sb.append(">");
    return sb.toString();
  }
}
