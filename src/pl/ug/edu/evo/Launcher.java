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

	public static Boolean json = true;
	
  public static void main(String[] args) {
	  if(!json) {
    System.out.println("Starting Evolutionary");
	  } else {
		  System.out.println("{ \"json\" : {");  
	  }
    
    List<Point> environment = Point.readPointsFromFile("datasets/data_2_2.txt");
    
    if(!json) {
    System.out.println("Read from file ");
    	System.out.println(environment);
    } else {
    	System.out.println("\"points\": " + environment + ",");
    }
    
    IterativeAlgorithm kmeans = new KMeans(environment);
    List<Point> solution = kmeans.initialSolution(3);
    
    if(json)   System.out.println("\"round\": [");

    for(int round=1; round<3; round++){
    	if(!json) {
      System.out.println("Solution in round " + round + " is " + solution);
    	} else {
    		 System.out.println("{ \"solution\" : " + solution + "}");
    		 if(round<2) System.out.println(",");
    		 
    	}
      solution = kmeans.nextRound(solution);
      
    }
    if(json)   System.out.println("],");
    
    if(!json) {
    System.out.println("Solution " + solution);
    } else {
    	 System.out.println("\"solution\":  " + solution +"}}");
    }
  }
}
