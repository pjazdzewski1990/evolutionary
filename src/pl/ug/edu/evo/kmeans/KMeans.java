package pl.ug.edu.evo.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

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
    List<Centroid> points = ((KMeansSolution)previousStep).getPoints();
    System.out.println("------------------");
    System.out.println(points);
    System.out.println("------------------");
    List<Centroid> newSolution = new ArrayList<>();
    for(Centroid point: points){
    	 
    	  
    KMeansCentroid centroid = new KMeansCentroid(point);
   centroid.setPointsInCluster(point.getPointsInCluster());
      newSolution.add(centroid.findNewPosition());
      System.out.println("------------------");
    }
   

    attachToCentroids(newSolution);
    
    return new KMeansSolution(newSolution);
  }

  @Override
  public IterationSolution initialSolution(int solutionsNum) {
    List<Centroid> initial = KMeansCentroid.generateRandom(solutionsNum, dimensions, ranges);

    attachToCentroids(initial);

  
    return new KMeansSolution(initial);
  }
  
  private void attachToCentroids(List<Centroid> initial) {
	  
	  for(Point point: environment){
	      Centroid closest = Centroid.getNearestPoint(initial, point);
	      closest.clusterPoint(point);
	    }
	  
  }
}
