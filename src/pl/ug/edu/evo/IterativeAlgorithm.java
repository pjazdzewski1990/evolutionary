package pl.ug.edu.evo;

import java.util.List;

public interface IterativeAlgorithm {
  /**
   * Returns computation result for inserted data (points List), being another step in algorithm
   * @param points
   * @return List<Point>
   */
  List<Point> nextRound(List<Point> points);
  /**
   * Gets a basic solutions which will evolve over time
   * @param solutionsNum How many solutions we expect at start
   * @return
   */
  List<Point> initialSolution(int solutionsNum);
  
  /**
   * Return double indicating how good the solution is
   * @return
   */
  double evaluateSolution();
}
