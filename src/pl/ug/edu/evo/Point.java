package pl.ug.edu.evo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents point in n dimensional space
 * @author jfk
 */
public class Point {
  private List<Double> position;

  public Point(List<Double> _position) {
    position = _position;
  }
  
  public Double getCoordinate(Character dimension) {
    switch(Character.toLowerCase(dimension)){
      case 'X': position.get(0);
      case 'Y': position.get(1);
      case 'Z': position.get(2);
      default: throw new IllegalArgumentException("No dimension specified for " + dimension);
    }
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("Point<");
    for (Double pos : position) {
      sb.append(" ");
      sb.append(pos);
    }
    sb.append(">");
    return sb.toString();
  }
  
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
  
  public static Point fromStrings(String[] strings) {
    List<Double> pos = new ArrayList<>();
    for(String strPos: strings){
      pos.add(Double.parseDouble(strPos));
    }
    return new Point(pos);
  }
}
