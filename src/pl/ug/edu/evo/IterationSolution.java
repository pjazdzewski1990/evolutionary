package pl.ug.edu.evo;

public interface IterationSolution {
  /**
   * Return double indicating how good the solution is
   * @return Score as double
   */
  double bestScore();
  
  /**
   * Get a json representation
   * @return
   */
  String asJValue();
}
