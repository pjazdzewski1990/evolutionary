package pl.ug.edu.evo.kmeans;

import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.grid.Point;

public class KMeansSolution implements IterationSolution {

  private List<Point> points;

  public KMeansSolution(List<Point> _points) {
    points = _points;
  }

  @Override
  public double bestScore() {
    return 0;
  }

  @Override
  public String asJValue() {
    StringBuffer sb = new StringBuffer("[");
    for(Point p : points){
      sb.append(p.toString());
      sb.append(",");
    }
    sb.deleteCharAt(sb.length()-1);
    sb.append("]");
    return sb.toString();
  }

  public List<Point> asPoints() {
    return points;
  }
  
  @Override
  public String toString() {
    return asJValue();
  }
}
