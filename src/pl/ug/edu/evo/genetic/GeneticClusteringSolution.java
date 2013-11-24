package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.grid.Centroid;
import pl.ug.edu.evo.grid.Point;

/**
 * Reprezentuje pojedyncze rozwiązanie
 * Zawiera zestaw centroidow pokrywajacych wszystkie punkty zadania
 * Podlega ocenie tzn. dwa obiekty tego typu można porównaó
 * @author jfk
 */
public class GeneticClusteringSolution 
  implements IterationSolution, Comparable<GeneticClusteringSolution> {

  protected List<Centroid> centroids = new ArrayList<>();

  public GeneticClusteringSolution(List<Centroid> _centroids) {
    centroids = _centroids;
  }

@Override
  public double score() {
    return 0;
  }

  @Override
  public String asJValue() {
    //TODO: replace toString with asJValue implementation
    return centroids.toString();
  }

//  @Override
//  public int compare(GeneticClusteringSolution arg0, GeneticClusteringSolution arg1) {
//    double score0 = arg0.score();
//    double score1 = arg1.score();
//    
//    return score0 < score1? 1 : 0;
//  }

  @Override
  public int compareTo(GeneticClusteringSolution o) {
    double score0 = this.score();
    double score1 = o.score();

    return score0 < score1? 1 : 0;
  }
}
