package pl.ug.edu.evo;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.genetic.GeneticAlgorithm;
import pl.ug.edu.evo.kmeans.KMeans;
import pl.ug.edu.pso.ParticleSwarmAlgorithm;
import pl.ug.edu.evo.differential.DifferentialAlgorithm;

/* Using data from http://www.isical.ac.in/~sanghami/data.html
   S. Bandyopadhyay and U. Maulik, ``An Evolutionary Technique Based on K-Means for
   Optimal Clustering in R^N'', Information Sciences, vol. 146, pp. 221-237, 2002
                           
   U. Maulik and S. Bandyopadhyay, ``Genetic Algorithm-based Clustering Technique"
   Pattern Recognition, vol.32, pp. 1455-1465, 2000

   S. Bandyopadhyay and S. K. Pal, ``Classification and Learning Using Genetic
   Algorithms: Applications in Bioinformatics and Web Intelligence", Springer,

   Heidelberg, 2007
 */
public class Launcher {
  
  public static void main(String[] args) {
    List<String> jsonBuffer = new ArrayList<>();
    
    int MAX_ROUNDS = 7;
    int CLUSTER_NUM = 3;
    
    List<Point> environment = Point.readPointsFromFile("datasets/data_2_2.txt");
    
//    IterativeAlgorithm alg = new KMeans(environment);
//    IterativeAlgorithm alg = new GeneticAlgorithm(environment);
    IterativeAlgorithm alg = new DifferentialAlgorithm(environment);
//    IterativeAlgorithm alg = new ParticleSwarmAlgorithm(environment);
    IterationSolution solution = alg.initialSolution(CLUSTER_NUM);
    
    for(int round=1; round<MAX_ROUNDS; round++){
      //System.out.println(round + " " + solution.score() + " " + solution.asJValue());
      jsonBuffer.add(solution.asJValue());
      solution = alg.nextRound(solution);
    }
    
    System.out.println("\n" + historyAsJson(environment, jsonBuffer));
  }

  private static String historyAsJson(List<Point> environment, List<String> jsonBuffer) {
    StringBuffer sb = new StringBuffer("{ \"json\" : {");
    
    sb.append("\"points\": " + environment + ",");
    
    sb.append("\"round\": [");
    int len = jsonBuffer.size();
    for(int i=0; i<len; i++) {
      String roundJson = jsonBuffer.get(i);
      sb.append("{ \"solution\" : " + roundJson + "}");
      if(i < len-1) sb.append(",");
    }
    sb.append("],");
    
    sb.append("\"solution\":  " + jsonBuffer.get(jsonBuffer.size()-1) +"}}");
    
    return sb.toString();
  }
}
