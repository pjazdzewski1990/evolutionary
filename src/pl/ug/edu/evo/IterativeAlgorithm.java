package pl.ug.edu.evo;

import java.util.List;

public interface IterativeAlgorithm {
  /**
   * Returns computation result for inserted data (points List), being another step in algorithm
   * @param points
   * @return List<Point>
   */
  List<Point> nextRound(List<Point> points);
}
