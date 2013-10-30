package pl.ug.edu.evo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents point in n dimensional space
 * @author jfk
 */
public class Point {
  private Map<Character, Double> position;

  public Point(Map<Character, Double> _position) {
    position = _position;
  }
  
  public Double getCoordinate(Character dimension) {
    return position.get(dimension);
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Point<");
    for (Character key : position.keySet()) {
      sb.append(" ");
      sb.append(key);
      sb.append("=");
      sb.append(position.get(key));
    }
    sb.append(">");
    return sb.toString();
  }
  
  public static List<Point> readPointsFromFile(){
    List<Point> points = new ArrayList<>();
    return points;
  }
}
