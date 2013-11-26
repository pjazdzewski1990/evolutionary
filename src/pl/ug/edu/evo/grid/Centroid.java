package pl.ug.edu.evo.grid;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pl.ug.edu.evo.genetic.GeneticCentroid;

/**
 * Represents a centroid in k-means algorithm
 * @author jfk
 */
public class Centroid extends Point implements GeneticCentroid {

  private Set<Point> pointsInCluster = new HashSet<>();
  
  public Centroid(List<Double> _position) {
    super(_position);
  }
  

  
  public void clusterPoint(Point p) {
    pointsInCluster.add(p);
  }
  
  public Set<Point> getPointsInCluster() {
	  
	  return pointsInCluster;
  }
  
  public void setPointsInCluster(Set<Point> _pointsInCluster) {
	  
	  this.pointsInCluster = _pointsInCluster;
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
  
  /**
   * BEWARE OVERFLOW ERRORS! Might return Double.MAX_VALUE
   * @return double 
   */
  public double calculateClusterInternalDistance(){
    if(pointsInCluster.size()>0){
      double sum = 0;
      Point center = this;
      for(Point p: pointsInCluster) {
        sum += Point.distance(p, center);
      }
      return sum;
    }else{
      return Double.MAX_VALUE;
    }
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
  
  public static Centroid getNearestCentroid(List<Point> centroids, Point point) {
    Point closestCentroid = null;
    Double minDistance = 0d;
    for(Point centroid: centroids){
      Double distance = Point.distance(centroid, point);
      if(closestCentroid == null || minDistance > distance){
        minDistance = distance;
        closestCentroid = centroid;
      }
    }
    
    return (Centroid)closestCentroid;
  }



@Override
public Centroid mutate() {
	List<Double> coords = new ArrayList<Double>();

	for(Double d : this.getCoordinateList()) {
		double rand = (Math.random()*4)-2;
		coords.add(d*rand);/////////////////////////////////////////////////////
		
	}
		

	Centroid breed = new Centroid(coords);
	return breed;
}
}
