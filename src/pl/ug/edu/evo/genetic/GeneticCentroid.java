package pl.ug.edu.evo.genetic;

import java.util.List;
import pl.ug.edu.evo.grid.Centroid;

public class GeneticCentroid extends Centroid implements GeneticBeing{

  public GeneticCentroid(List<Double> _position) {
    super(_position);
  }

  @Override
  public GeneticBeing mutate(GeneticBeing parent) {
    //TODO: implement
    return parent;
  }

  @Override
  public GeneticBeing breed(GeneticBeing father, GeneticBeing mother) {
    //TODO: implement
    return father;
  }
}
