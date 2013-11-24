package pl.ug.edu.evo.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
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
  
  public Centroid findNewPosition(){
    Random generator = new Random();
    List<Double> pos = new ArrayList<>();
    double number = pointsInCluster.size();
    for(int dim=0; dim<getCoordinateList().size(); dim++){
      if(number > 0){
        double sum = 0;
        for(Point p : pointsInCluster){
          sum += p.getCoordinateList().get(dim);
        }
        pos.add(sum/number);
      }else{
        pos.add(generator.nextDouble());
      }
    }
    return new Centroid(pos);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{ \"Centroid\" : [ ");
    sb.append(super.toString());
    sb.append(",");
    sb.append(pointsInCluster.toString());
    sb.append("]}");
    return sb.toString();
  }
  
  public static List<Point> generateRandom(int solutionsNum, int dimensions, List<Double> ranges){
    Random generator = new Random();
    List<Point> initial = new ArrayList<>();
    
    for(int i=0; i<solutionsNum; i++){
      List<Double> center = new ArrayList<>();
      for(int j=0; j<dimensions; j++){
        double range = ranges.get(j);
        double randomPoint = (generator.nextDouble() * 2 * range) - range;
        center.add(randomPoint);
      }
      initial.add(new Centroid(center));
    }
    return initial;
  }
}
