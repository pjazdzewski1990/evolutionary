package pl.ug.edu.evo.base;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.omg.CORBA._PolicyStub;

/**
 * Represents point in n dimensional space
 * @author jfk
 */
public class Point {
  private List<Double> position;

  public Point(List<Double> _position) {
    position = _position;
  }
  
  /**
   * Get coordinate for input dimension. Dimension is repesented as char (x,y,z...)
   * @param dimension
   * @return
   */
  public Double getCoordinate(int i) {
	  return position.get(i);
  }
  public Double getCoordinate(Character dimension) {
    switch(Character.toUpperCase(dimension)){
      case 'X': return position.get(0);
      case 'Y': return position.get(1);
      case 'Z': return position.get(2);
      default: throw new IllegalArgumentException("No dimension specified for " + dimension);
    }
  }
  
  public List<Double> getCoordinateList() {
    return position;
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("{ \"coords\" : [");
      for (Double pos : position) {
        sb.append("\"" + pos + "\"");
        sb.append(",");
      }
    sb.deleteCharAt(sb.length()-1);
    sb.append("]}");
    return sb.toString();
  }
  
  /**
   * Reads points data from file. Each line represents one point, every value separated by space is a dimension
   * @param path
   * @return
   */
  public static List<Point> readPointsFromFile(String path){
    List<Point> points = new ArrayList<>();
    
    try {
      List<String> data = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
      for(String point: data){
        Point p = Point.fromStrings(point.split(" "));
        points.add(p);
      }
    } catch(IOException ioe) {
     System.out.println("Error occured: " + ioe);
    }
    
    return points;
  }
  
  /**
   * Helper for constructing Points
   * @param strings
   * @return
   */
  public static Point fromStrings(String[] strings) {
    List<Double> pos = new ArrayList<>();
    for(String strPos: strings){
      pos.add(Double.parseDouble(strPos));
    }
    pos.remove(pos.size()-1);//the last one is the choosen cluster
    return new Point(pos);
  }
  
  /**
   * Gets max (in absolute sense) values for each dimension for current environemnt
   * @param environment
   * @return
   */
  public static List<Double> calculateRanges(List<Point> environment){
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
    Centroid._ranges = envRanges;
    return envRanges;
  }
  
  public static Double distance(Point pointA, Point pointB) {
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
