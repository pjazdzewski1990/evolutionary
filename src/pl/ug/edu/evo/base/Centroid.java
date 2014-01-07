package pl.ug.edu.evo.base;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import pl.ug.edu.evo.genetic.GeneticCentroid;
import pl.ug.edu.evo.kmeans.KMeansCentroid;

/**
 * Represents a centroid in k-means algorithm
 * @author jfk
 */
public class Centroid extends Point{

  protected Set<Point> pointsInCluster = new HashSet<>();
  public static int _dims = 2;
  public static List<Double> _ranges = new ArrayList<Double>();
  
  public Centroid(List<Double> _position) {
    super(_position);
  }
  public Centroid(Point pt) {
	  super(pt.getCoordinateList());
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
  

  
  /**
   * BEWARE OVERFLOW ERRORS! Might return Double.MAX_VALUE
   * @return double 
   */
  public double calculateClusterInternalDistance(){
    if(pointsInCluster.size()>0){
      double sum = 0;
      Point center = this;
      for(Point p: pointsInCluster) {
    	  //System.out.println("-------------");
    	 // System.out.println(center);
        sum += Point.distance(p, center);
      }
      return sum;
    }else{
      return 0;
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
  
  public static List<Centroid> generateRandom(int solutionsNum, int dimensions, List<Double> ranges){
	 Centroid._dims = dimensions;
	 Centroid._ranges = ranges;
    Random generator = new Random();
    List<Centroid> initial = new ArrayList<>();
    
    for(int i=0; i<solutionsNum; i++){
     
      initial.add(new Centroid(randomCentroid(dimensions, ranges)));
    }
    return initial;
  }
  
  public static Centroid randomCentroid(int dimensions, List<Double> ranges) {
	  Random generator = new Random();
	  List<Double> center = new ArrayList<>();
	  for(int j=0; j<dimensions; j++){
	        double range = ranges.get(j);
	        double randomPoint = (generator.nextDouble() * 2 * range) - range;
	        center.add(randomPoint);
	      }
	  return new Centroid(center);
  }
  
 
  
  public static Centroid getNearestPoint(List<Centroid> centroids, Point point) {
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
  
  public static Centroid getNearestCentroid(List<Centroid> centroids, Point point) {
	    List<Centroid> centroidsAsPoints = new ArrayList<>(centroids.size());
	    for(Centroid centroid: centroids){
	      centroidsAsPoints.add(centroid);
	    }
	    return getNearestPoint(centroidsAsPoints, point);
	  }


  






}
