package pl.ug.edu.evo;

import java.util.List;

import pl.ug.edu.evo.grid.Centroid;

public interface IterationSolution {
  /**
   * Return double indicating how good the solution is
   * @return Score as double
   */
  double score();
  
  /**
   * Get a json representation
   * @return
   */
  String asJValue();

}
