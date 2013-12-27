package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.grid.Centroid;
import pl.ug.edu.evo.grid.Point;

/**
 * Reprezentuje pojedyncze rozwiązanie
 * Zawiera zestaw centroidow pokrywajacych wszystkie punkty zadania
 * Podlega ocenie tzn. dwa obiekty tego typu można porównaó
 * @author jfk
 */
public class GeneticClusteringSolution implements IterationSolution, Comparable<GeneticClusteringSolution> {

  protected List<Centroid> centroids = new ArrayList<>();

  public GeneticClusteringSolution(List<Centroid> _centroids) {
    centroids = _centroids;
  }

  @Override
  public double score() {
    double score = 0;
    for(Centroid c : centroids){
      double partialResult = c.calculateClusterInternalDistance();
      if(partialResult == Double.MAX_VALUE){
        score = Double.MAX_VALUE;
      }else{
        score += partialResult;
      }
    }
    return score;
  }

  @Override
  public String asJValue() {
    //TODO: replace toString with asJValue implementation
    return centroids.toString();
  }

  @Override
  public int compareTo(GeneticClusteringSolution o) {
    double score0 = this.score();
    double score1 = o.score();

//    return score0 < score1? 1 : 0;
    if(score0 != score1) {
      return score0 < score1? 1 : -1;
    } else {
      if(centroids.equals(o.centroids)){
        return 0;
      } else {
        return 1; //they both are as good, take any
      }
    }
  }

  public List<Centroid> getCentroids() {
    return this.centroids;
  }

//  public GeneticClusteringSolution breed(Centroid a, Centroid b) {
//    throw new Exception("GeneticClusteringSolution doesn't support breeding");
//  }

  public GeneticClusteringSolution mutate() {
    //1. perform gravity mutation
    List<Centroid> mutatedCentroids = new ArrayList<>();
    for(Centroid c : centroids){
      mutatedCentroids.add(gravitate(c));
    }

    //2. re-assign points to centroids
    List<Point> allPointsInSolution = getAllPointsFromClustes();
    for(Point point : allPointsInSolution){
      Centroid closest = Centroid.getNearestCentroid(mutatedCentroids, point);
      closest.clusterPoint(point);
    }

    return new GeneticClusteringSolution(mutatedCentroids);
  }

  private Centroid gravitate(Centroid c) {
    Random r = new Random();
    Set<Point> pointsInCentroid = c.getPointsInCluster();
    
    //1. choose the points to form the "cluster mass", on average 50% of all
    List<Point> choosenPoints = new ArrayList<>();
    for(Point p : pointsInCentroid) {
      if(r.nextBoolean()) choosenPoints.add(p);
    }
    
    //TODO: only for 2D
    double sumX = 0.0;
    double sumY = 0.0;
    //2. create a average point for "cluster mass"
    for(Point choosen : choosenPoints){
      sumX += choosen.getCoordinate('X');
      sumY += choosen.getCoordinate('Y');
    }
    int massSize = choosenPoints.size();
    double avgX = sumX/massSize;
    double avgY = sumY/massSize;
    
    //3. create a new cluster that is closer by a random factor to the average point
    double newX = c.getCoordinate('X') + avgX/(r.nextInt(9)+1);
    double newY = c.getCoordinate('X') + avgY/(r.nextInt(9)+1);
    
    
    //return the new cluster without points specified
    return new Centroid(Arrays.asList(new Double[]{newX, newY}));
  }

  private List<Point> getAllPointsFromClustes() {
    List<Point> all = new ArrayList<>();
    for(Centroid c : centroids){
      all.addAll(c.getPointsInCluster());
    }
    return all;
  }
}
