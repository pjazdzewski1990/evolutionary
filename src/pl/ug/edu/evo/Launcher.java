package pl.ug.edu.evo;

import java.util.List;

import pl.ug.edu.evo.kmeans.KMeans;

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
    int MAX_ROUNDS = 4;
    int CLUSTER_NUM = 3;
    System.out.println("{ \"json\" : {");
    
    List<Point> environment = Point.readPointsFromFile("datasets/data_2_2.txt");
    
    System.out.println("\"points\": " + environment + ",");
    
    IterativeAlgorithm kmeans = new KMeans(environment);
    IterationSolution solution = kmeans.initialSolution(CLUSTER_NUM);
    
    System.out.println("\"round\": [");

    for(int round=1; round<MAX_ROUNDS; round++){
      System.out.println("{ \"solution\" : " + solution + "}");
      if(round<MAX_ROUNDS-1) System.out.println(",");
      solution = kmeans.nextRound(solution);
    }
    System.out.println("],");
    
    System.out.println("\"solution\":  " + solution +"}}");
  }
}
