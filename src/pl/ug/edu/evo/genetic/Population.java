package pl.ug.edu.evo.genetic;

import java.util.List;

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

  private List<ClusteringSolution> solutions;

  public Population(List<ClusteringSolution> _solutions) {}

@Override
  public double bestScore() {
    return 0;
  }

  @Override
  public String asJValue() {
    return "";
  }

}
