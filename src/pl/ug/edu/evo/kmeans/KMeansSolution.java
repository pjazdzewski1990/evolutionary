package pl.ug.edu.evo.kmeans;

import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

public class KMeansSolution implements IterationSolution {

  private List<Point> points;

  public KMeansSolution(List<Point> _points) {
    points = _points;
  }

  @Override
  public double score() {
    double score = 0;
    for(Point p : points) {
      if(p instanceof Centroid) {
        Centroid c = (Centroid)p;
        double partialResult = c.calculateClusterInternalDistance();
        if(partialResult == Double.MAX_VALUE){
          score = Double.MAX_VALUE;
        }else{
          score += partialResult;
        }
      }
    }
    return score;
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
