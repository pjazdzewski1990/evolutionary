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
    ranges = Point.calculateRanges(_environment);
    dimensions = ranges.size();
    environment = _environment;
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
    List<Point> initial = Centroid.generateRandom(solutionsNum, dimensions, ranges);
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
