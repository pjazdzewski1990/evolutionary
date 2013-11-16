package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;

public class GeneticAlgorithm implements IterativeAlgorithm {

  @Override
  public IterationSolution nextRound(IterationSolution previousPopulation) {
    //selection, mutation, breeding comes here
    return previousPopulation; //TODO: implement
  }

  @Override
  public IterationSolution initialSolution(int solutionsNum) {
    List<ClusteringSolution> random = new ArrayList<>(solutionsNum);
    for(int i=0; i<solutionsNum; i++){
      random.add(randomClusterSolution());
    }
    return new Population(random);
  }

  private ClusteringSolution randomClusterSolution() {
    return new ClusteringSolution(); //TODO: implement
  }
}
