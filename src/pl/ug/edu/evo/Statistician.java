package pl.ug.edu.evo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.text.Position;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.differential.DifferentialAlgorithm;
import pl.ug.edu.evo.differential.DifferentialPopulation;
import pl.ug.edu.evo.genetic.GeneticClusteringSolution;

public class Statistician {

  static int MAX_ROUNDS = 540;
  static int CLUSTER_NUM = 5;

  public static void main(String[] args) {
    String[] dataSets = {"datasets/data_5_2.txt"};
    
//    for(String dataSet : dataSets) {
//      List<Point> environment = Point.readPointsFromFile(dataSet); 
//      
//      System.out.println("KMEANS  Set: " + dataSet);
//      KMeansSolution finalResult = (KMeansSolution)testAlgorithm(new KMeans(environment));
//      
//      countCorrectnes(finalResult.getPoints(), dataSet);
//    }
    
//    for(String dataSet : dataSets) {
//      List<Point> environment = Point.readPointsFromFile(dataSet); 
//      
//      System.out.println("EVOLUTIONARY  Set: " + dataSet);
//      Population pop = (Population)testAlgorithm(new GeneticAlgorithm(environment));
//      GeneticClusteringSolution finalResult = pop.getSolutions().first();
//      
//      countCorrectnes(finalResult.getCentroids(), dataSet);
//    }
    
    for(int ff=0; ff<10; ff++){
    for(String dataSet : dataSets) {
        List<Point> environment = Point.readPointsFromFile(dataSet); 
        
        System.out.println("DIFFERENTIAL  Set: " + dataSet);
        DifferentialPopulation pop = (DifferentialPopulation)testAlgorithm(new DifferentialAlgorithm(environment));
        GeneticClusteringSolution finalResult = pop.getSolutions().first();
        
        countCorrectnes(finalResult.getCentroids(), dataSet);
      }
    }
  }

  private static IterationSolution testAlgorithm(IterativeAlgorithm alg) {
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
    
    return solution;
  }

  private static void countCorrectnes(List<Centroid> centroids, String path) {
    List<Double[]> pointsData = parseDatasetFile(path);
    
//    System.out.println("PointsData gathered " + pointsData.size());
    
    int maxFit = 0;
    int centroidsSize = centroids.size();
    
    for(int fitting=0; fitting<centroidsSize; fitting++){//każde możliwe dopasowanie 
      int localFit = 0;
      for(int i=0; i<centroidsSize; i++){//każdy centroid
        int choosen = (i+fitting)%centroidsSize;
        //System.out.println("Trying " + choosen);
        
        Set<Point> points = centroids.get(choosen).getPointsInCluster();
        //dla każdego punkty z pliku z decelowym klastrem i
        // sprawdz, czy obecny zestaw punktów z centroida zawiera ten punkt
        for(Double[] coordinates : pointsData) {
          if(coordinates[coordinates.length - 1] == i+1) { //jeżeli to ten klaster
            for(Point p : points) {
              List<Double> pointCoordinates = p.getCoordinateList();
              if(Math.abs(coordinates[0]-pointCoordinates.get(0))<=0.001 && Math.abs(coordinates[1]-pointCoordinates.get(1))<=0.001) {
                localFit++;
              }
            }
          }
        }
        //System.out.println("Local " + localFit);
        //przejrzeliśmy cały plik
        if(localFit > maxFit) maxFit = localFit;
      }
    }
    
    double all = (double)pointsData.size();
    System.out.println("MaxFit: " + maxFit + " " + (100 * maxFit/all) + "%");
  }

  private static List<Double[]> parseDatasetFile(String path) {
    List<Double[]> pointsData = new ArrayList<>();
    
    try {
      List<String> data = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
      for(String point: data){
        String[] strings = point.split(" ");
        Double[] doublesFromStrings = new Double[strings.length];
        for(int i=0; i<strings.length; i++){
          doublesFromStrings[i] = Double.parseDouble(strings[i]);
        }
        pointsData.add(doublesFromStrings);
      }
    } catch(IOException ioe) {
     System.out.println("Error occured: " + ioe);
    }
    return pointsData;
  }
}
