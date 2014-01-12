package pl.ug.edu.evo.differential;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.genetic.GeneticAlgorithm;
import pl.ug.edu.evo.genetic.GeneticClusteringSolution;

public class DifferentialAlgorithm extends GeneticAlgorithm {

  public DifferentialAlgorithm(List<Point> _environment) {
    super(_environment);
  }
  
  @Override
  public IterationSolution initialSolution(int solutionsNum) {
    List<GeneticClusteringSolution> random = new ArrayList<>(solutionsNum);
    for(int i=0; i<solutionsNum; i++){
      random.add(this.randomClusterSolution(solutionsNum));
    }
    return new DifferentialPopulation(random);
  }
}
