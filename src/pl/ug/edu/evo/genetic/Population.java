package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.grid.Centroid;

/**
 * Obiekt zawierający wiele potencjalnych rozwiązań zadanego 
 * problemu klastrowania.
 * Population prezentuje na zewnątrz najlepsze rozwiązanie, ale 
 * wewnątrz operuje na wszystkich potencjalnych rozwiązaniach celem 
 * ich ewolucyjnego ulepszania.
 * @author jfk
 */
public class Population implements IterationSolution {

  private ConcurrentSkipListSet<GeneticClusteringSolution> solutions;

  public Population(List<GeneticClusteringSolution> _solutions) {
    solutions = new ConcurrentSkipListSet<>(_solutions); 
  }

  public Population nextPopulation() {
    ConcurrentSkipListSet<GeneticClusteringSolution> population = new ConcurrentSkipListSet<>(solutions);
    //1. create descendents 
    for(GeneticClusteringSolution sol : solutions) {
      population.add(sol.mutate());
    }
    //2. group them
    Random r = new Random();
    int groupsCount = solutions.size() / 2;
    List<List<GeneticClusteringSolution>> groups = new ArrayList<>(groupsCount);
    for(int i=0; i<groupsCount; i++) groups.add(new ArrayList<GeneticClusteringSolution>());
    for(GeneticClusteringSolution gcs : population) {
      int index = r.nextInt(groupsCount);
      groups.get(index).add(gcs);
    }
    //3. choose winners
    List<GeneticClusteringSolution> newPopulation = new ArrayList<GeneticClusteringSolution>();
    for(List<GeneticClusteringSolution> group : groups){
      newPopulation.addAll( getBestInGroup(group, solutions.size() / groupsCount) );
    }
    
    return new Population(newPopulation);
  }

  private List<GeneticClusteringSolution> getBestInGroup(List<GeneticClusteringSolution> group, int take) {
    Collections.sort(group);
    List<GeneticClusteringSolution> taken = new ArrayList<>();
    return taken;
  }

  @Override
  public double score() {
    return solutions.first().score();
  }

  @Override
  public String asJValue() {
    return solutions.first().asJValue();
  }
  
  @Override
  public String toString() {
    return "Population: " + solutions.size();
  }
}
