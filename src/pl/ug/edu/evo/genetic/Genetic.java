package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;

public class Genetic implements IterativeAlgorithm {

  @Override
  public IterationSolution nextRound(IterationSolution previousPopulation) {
    //selection, mutation, breeding comes here
    return previousPopulation; //TODO: implement
  }

  @Override
  public IterationSolution initialSolution(int solutionsNum) {
    List<GeneticCentroid> random = new ArrayList<>(solutionsNum);
    for(int i=0; i<solutionsNum; i++){
      random.add(randomGeneticCentroid());
    }
    return new Population(random);
  }

  private GeneticCentroid randomGeneticCentroid() {
    return null; //TODO: implement
  }

}
