package pl.ug.edu.evo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeans implements IterativeAlgorithm {

  List<Point> environment = new ArrayList<>();
  List<Double> ranges;
  int dimensions;

  public KMeans(List<Point> _environment){
    ranges = calculateRanges(_environment);
    dimensions = ranges.size();
    environment = _environment;
  }

  /**
   * Gets max (in absolute sense) values for each dimension for current environemnt
   * @param environment
   * @return
   */
  private List<Double> calculateRanges(List<Point> environment){
    //SIMPLIFY
    List<Double> envRanges = environment.get(0).getCoordinateList();
    for(Point p : environment) {
      List<Double> position = p.getCoordinateList();
      for(int i=0; i<position.size(); i++){
        double absolutValue = Math.abs(position.get(i));
        if(envRanges.get(i) != null && Math.abs(envRanges.get(i)) <= absolutValue) {
          envRanges.set(i, absolutValue);
        }
      }
    }
    //System.out.println("Foo " + envRanges);
    return envRanges;
  }

  @Override
  public List<Point> nextRound(List<Point> points) {
    return null;
  }

  @Override
  public List<Point> initialSolution(int solutionsNum) {
    Random generator = new Random();
    List<Point> initial = new ArrayList<>();
    
    for(int i=0; i<solutionsNum; i++){
      List<Double> p = new ArrayList<>();
      for(int j=0; j<dimensions; j++){
        double range = ranges.get(j);
        double randomPoint = (generator.nextDouble() * 2 * range) - range;
        p.add(randomPoint);
      }
      initial.add(new Centroid(p));
    }
    
    return initial;
  }
}
