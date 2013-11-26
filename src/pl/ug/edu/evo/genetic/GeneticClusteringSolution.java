package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
public class GeneticClusteringSolution 
  implements IterationSolution, Comparable<GeneticClusteringSolution>, GeneticBeing {

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

  public List<Centroid> getCentroids() {
	  return this.centroids;
  }
  


@Override
public Centroid breed(Centroid a, Centroid b) {
	// TODO Auto-generated method stub
	return null;
}



}
