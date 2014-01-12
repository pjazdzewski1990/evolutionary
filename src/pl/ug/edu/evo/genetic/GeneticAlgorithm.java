package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.differential.DifferentialPopulation;

public class GeneticAlgorithm implements IterativeAlgorithm {

  List<Point> environment = new ArrayList<>();
  List<Double> ranges;
  int dimensions;

  public GeneticAlgorithm(List<Point> _environment){
    ranges = Point.calculateRanges(_environment);
    dimensions = ranges.size();
    environment = _environment;
  }

  @Override
  public IterationSolution nextRound(IterationSolution previousPopulation) {
    //selection, mutation, breeding comes here
    if(previousPopulation instanceof DifferentialPopulation) {
    	System.out.println("DIFF");
      DifferentialPopulation pop = (DifferentialPopulation)previousPopulation;
      return pop.nextPopulation();
    }
    if(previousPopulation instanceof Population) {
      Population pop = (Population)previousPopulation;
      return pop.nextPopulation();
    }
    
    return previousPopulation;
  }

  @Override
  public IterationSolution initialSolution(int solutionsNum) {
    List<GeneticClusteringSolution> random = new ArrayList<>(solutionsNum);
    for(int i=0; i<solutionsNum; i++){
      random.add(randomClusterSolution(solutionsNum));
    }
    return new Population(random);
  }

  protected GeneticClusteringSolution randomClusterSolution(int solutionsNum) {
    List<Centroid> randomCentroids = GeneticCentroid.generateRandom(solutionsNum, dimensions, ranges);
    for(Point point: environment){
      Centroid closest = Centroid.getNearestPoint(randomCentroids, point);
      closest.clusterPoint(point);
    }
    return new GeneticClusteringSolution(randomCentroids); 
  }
}
