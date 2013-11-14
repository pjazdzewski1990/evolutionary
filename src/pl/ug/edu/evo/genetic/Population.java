package pl.ug.edu.evo.genetic;

import java.util.List;

import pl.ug.edu.evo.IterationSolution;

public class Population implements IterationSolution {

  private List<GeneticCentroid> centroids;

public Population(List<GeneticCentroid> _centroids) {
    centroids = _centroids;
  }

  @Override
  public double bestScore() {
    return 0;
  }

  @Override
  public String asJValue() {
    return null;
  }

}
