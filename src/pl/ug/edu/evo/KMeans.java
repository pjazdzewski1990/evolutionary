package pl.ug.edu.evo;

import java.util.ArrayList;
import java.util.List;

public class KMeans implements IterativeAlgorithm {

  @Override
  public List<Point> nextRound(List<Point> points) {
    List<Double> pos = new ArrayList<>();
    pos.add(0.0);
    pos.add(0.0);
    
    List<Double> member = new ArrayList<>();
    member.add(1.0);
    member.add(2.0);
    
    Centroid c = new Centroid(pos);
    c.clusterPoint(new Point(member));
    
    List<Point> cc = new ArrayList<>();
    cc.add(c);
    return cc;
  }

}
