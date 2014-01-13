package pl.ug.edu.evo.differential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.genetic.GeneticClusteringSolution;
import pl.ug.edu.evo.genetic.Population;

public class DifferentialPopulation extends Population {

  private Random rand = new Random();
  private double F = 0.5;

  public DifferentialPopulation(List<GeneticClusteringSolution> _solutions) {
    super(_solutions);
  }
  
  @Override
  public Population nextPopulation() {
    //set's snapshot
    GeneticClusteringSolution[] solArray = solutions.toArray(new GeneticClusteringSolution[0]); 
    List<Point> allPointsInTask = solArray[0].getAllPointsFromClustes();
    
    //1. create mutants
    GeneticClusteringSolution[] mutants = createMutants(solArray);
    //2. combine them with their parents
    GeneticClusteringSolution[] combined = combineWithParents(solArray, mutants);
    //3. choose the best to form the new population
    List<GeneticClusteringSolution> all = new ArrayList<>();
    all.addAll(Arrays.asList(solArray));
    all.addAll(Arrays.asList(combined));
    
    for(GeneticClusteringSolution gcs : all) 
      GeneticClusteringSolution.updatePointsInCentroids(allPointsInTask, gcs.getCentroids()); 
    Collections.sort(all);
    return new DifferentialPopulation(all.subList(0, solArray.length+1));
  }

  private GeneticClusteringSolution[] combineWithParents(
    GeneticClusteringSolution[] parents, GeneticClusteringSolution[] mutants) {
    
    GeneticClusteringSolution[] combined = new GeneticClusteringSolution[parents.length];
    for(int i=0; i<parents.length; i++){
      
      combined[i] = GeneticClusteringSolution.randomlyCombine(parents[i], mutants[i]);
    }
    return combined;
}

  private GeneticClusteringSolution[] createMutants(GeneticClusteringSolution[] solArray) {
    GeneticClusteringSolution[] mutants = new GeneticClusteringSolution[solutions.size()];
    
    for(int i=0; i<mutants.length;i++){
      int rand1 = rand.nextInt(mutants.length);
      int rand2 = 0;
      while(rand2==rand1) rand2 = rand.nextInt(mutants.length);
      int rand3 = 0;
      while(rand3==rand1 || rand3==rand2) rand3 = rand.nextInt(mutants.length);
      
      mutants[i] = mutate(solArray[rand1], solArray[rand2], solArray[rand3]);
    }
    
    return mutants;
  }

  private GeneticClusteringSolution mutate(
      GeneticClusteringSolution base,
      GeneticClusteringSolution diff1,
      GeneticClusteringSolution diff2) {

    List<Centroid> diff1Centroid = diff1.getCentroids();
    List<Centroid> diff2Centroid = diff2.getCentroids();
    
    List<Centroid> mutant = new ArrayList<>();
    //substract
    for(int i=0; i<diff1Centroid.size(); i++){
      mutant.add(diff1Centroid.get(i).minus(diff2Centroid.get(i)).times(this.F));
    }
    
    return new GeneticClusteringSolution(mutant);
  }
}
