package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

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
    
    
    //tutaj jeszcze troche nar�bane, ale potem to poprawie
    //TODO: only for 2D
    Double[] sumAll = new Double[choosenPoints.get(0).getCoordinateList().size()];

    //2. create a average point for "cluster mass"
    if(choosenPoints.size()>0) {
    	for(int i=0; i<choosenPoints.get(0).getCoordinateList().size(); i++) {
    		sumAll[i] = 0.0;
    	}
    	
    	
    for(int j=0; j<choosenPoints.size(); j++) {
    	
    	for(int i=0; i<choosenPoints.get(0).getCoordinateList().size(); i++) {
    		
    		sumAll[i]+=choosenPoints.get(j).getCoordinate(i);
    	}
    }

    int massSize = choosenPoints.size();
    for(int i = 0; i<choosenPoints.get(0).getCoordinateList().size(); i++) {
    	sumAll[i]= c.getCoordinate(i) + (sumAll[i]/massSize) / (r.nextInt(9)+1) ;
    }

    //3. create a new cluster that is closer by a random factor to the average point

    
    
    //return the new cluster without points specified
    }
    return new Centroid(Arrays.asList(sumAll));
  }

  private List<Point> getAllPointsFromClustes() {
    List<Point> all = new ArrayList<>();
    for(Centroid c : centroids){
      all.addAll(c.getPointsInCluster());
    }
    return all;
  }
}
