package pl.ug.edu.evo;

import java.util.List;

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
    System.out.println("Starting Evolutionary");
    
    List<Point> readPoints = Point.readPointsFromFile("datasets/data_2_2.txt");
    System.out.println("Read " + readPoints);
    
    IterativeAlgorithm kmeans = new KMeans();
    System.out.println(kmeans.nextRound(null));
  }
}
