package pl.ug.edu.evo;

import java.util.List;

import pl.ug.edu.evo.base.Centroid;

public interface IterativeAlgorithm {
  /**
   * Returns computation result for inserted data, being another step in algorithm
   * @param IterationSolution Solution after n steps
   * @return IterationSolution Solution after n+1 steps
   */
  IterationSolution nextRound(IterationSolution points);
  /**
   * Gets a basic solutions which will evolve over time
   * @param solutionsNum How many solutions we expect at start
   * @return
   */
  IterationSolution initialSolution(int solutionsNum);
  
  
}
