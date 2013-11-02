package pl.ug.edu.evo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    List<Point> solution = new ArrayList<>();
    return points;
  }

  @Override
  public List<Point> initialSolution(int solutionsNum) {
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
    //add points to centroids
    for(Point point: environment){
      Centroid closest = (Centroid)getNearest(initial, point);
      closest.clusterPoint(point);
    }
    
    return initial;
  }

  private Centroid getNearest(List<Point> centroids, Point point) {
    Point closestCentroid = null;
    Double minDistance = 0d;
    for(Point centroid: centroids){
      Double distance = distance(centroid, point);
      if(closestCentroid==null || minDistance<distance){
        minDistance = distance;
        closestCentroid = centroid;
      }
    }
    
    return (Centroid)closestCentroid;
  }

  private Double distance(Point pointA, Point pointB) {
    //TODO: many dimensions
    double xA = pointA.getCoordinate('x');
    double xB = pointB.getCoordinate('x');
    double xDiff = Math.max(xA, xB) - Math.min(xA, xB);
    
    double yA = pointA.getCoordinate('y');
    double yB = pointB.getCoordinate('y');
    double yDiff = Math.max(yA, yB) - Math.min(yA, yB);
    
//    System.out.println("Distance between " + pointA + " and " + pointB + " is " + Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
    return Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
  }
}
