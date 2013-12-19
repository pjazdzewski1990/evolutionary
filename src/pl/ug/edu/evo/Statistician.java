package pl.ug.edu.evo;

import java.util.Date;
import java.util.List;

import pl.ug.edu.evo.grid.Point;
import pl.ug.edu.evo.kmeans.KMeans;

public class Statistician {

  static int MAX_ROUNDS = 10;
  static int CLUSTER_NUM = 3;

  public static void main(String[] args) {
    String[] dataSets = {"datasets/data_2_2.txt", "datasets/data_5_2.txt"};
    
    for(String dataSet : dataSets) {
      List<Point> environment = Point.readPointsFromFile(dataSet); 
      
      System.out.println("KMEANS  SET: " + dataSet);
      testAlgorithm(new KMeans(environment));
    }
  }

  private static void testAlgorithm(IterativeAlgorithm alg) {
    Date past = new Date();
    
    IterationSolution solution = alg.initialSolution(CLUSTER_NUM);
    
    IterationSolution best = solution;
    IterationSolution worst = solution;
    
    for(int round=1; round<MAX_ROUNDS; round++){
      solution = alg.nextRound(solution);
      
      if(best.score() > solution.score()) best = solution;
      if(worst.score() < solution.score() && round+1 >= MAX_ROUNDS) worst = solution;
    }
    
    Date future = new Date();
    long time = future.getTime() - past.getTime();
    System.out.println("  time:" + time + " best:" + best.score()+ " worst:" + worst.score() + " final:" + solution.score());
  }
}
