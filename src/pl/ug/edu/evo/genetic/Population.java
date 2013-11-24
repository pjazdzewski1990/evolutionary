package pl.ug.edu.evo.genetic;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

import pl.ug.edu.evo.IterationSolution;

/**
 * Obiekt zawierający wiele potencjalnych rozwiązań zadanego 
 * problemu klastrowania.
 * Population prezentuje na zewnątrz najlepsze rozwiązanie, ale 
 * wewnątrz operuje na wszystkich potencjalnych rozwiązaniach celem 
 * ich ewolucyjnego ulepszania.
 * @author jfk
 */
public class Population implements IterationSolution {

  private ConcurrentSkipListSet<GeneticClusteringSolution> solutions;

  public Population(List<GeneticClusteringSolution> _solutions) {
    solutions = new ConcurrentSkipListSet<>(_solutions); 
  }

  @Override
  public double score() {
    return solutions.first().score();
  }

  @Override
  public String asJValue() {
    return solutions.first().asJValue();
  }

}
