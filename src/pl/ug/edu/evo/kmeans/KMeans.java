package pl.ug.edu.evo.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;
import pl.ug.edu.evo.grid.Centroid;
import pl.ug.edu.evo.grid.Point;

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
    //TODO: SIMPLIFY & restrict area
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
    return envRanges;
  }

  @Override
  public IterationSolution nextRound(IterationSolution previousStep) {
    //TODO: what a shitty solution. I don't like this cast and the idea that sending other subtype of IterationSolution will blow the system up 
    List<Point> points = ((KMeansSolution)previousStep).asPoints();
    
    List<Point> newSolution = new ArrayList<>();
    for(Point point: points){
      Centroid centroid = (Centroid)point;
      newSolution.add(centroid.findNewPosition());
    }
    //add points to centroids
    for(Point point: environment){
      Centroid closest = (Centroid)getNearest(newSolution, point);
      closest.clusterPoint(point);
    }
    return new KMeansSolution(newSolution);
  }

  @Override
  public IterationSolution initialSolution(int solutionsNum) {
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
    
    return new KMeansSolution(initial);
  }

  private Centroid getNearest(List<Point> centroids, Point point) {
    Point closestCentroid = null;
    Double minDistance = 0d;
    for(Point centroid: centroids){
      Double distance = distance(centroid, point);
      if(closestCentroid == null || minDistance > distance){
        minDistance = distance;
        closestCentroid = centroid;
      }
    }
    
    return (Centroid)closestCentroid;
  }

  private Double distance(Point pointA, Point pointB) {

    int len = pointA.getCoordinateList().size();
    double total = 0;
    List<Double> posPointA = pointA.getCoordinateList();
    List<Double> posPointB = pointB.getCoordinateList();

    for(int i=0; i<len; i++) {
      double max = Math.max(posPointA.get(i), posPointB.get(i));
      double min = Math.min(posPointA.get(i), posPointB.get(i));
      total += Math.pow(max - min,2);
    }

    posPointA = null;
    posPointB = null;
    return Math.sqrt(total);
  }
}
