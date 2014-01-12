package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListSet;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.base.Centroid;

/**
 * Obiekt zawierający wiele potencjalnych rozwiązań zadanego 
 * problemu klastrowania.
 * Population prezentuje na zewnątrz najlepsze rozwiązanie, ale 
 * wewnątrz operuje na wszystkich potencjalnych rozwiązaniach celem 
 * ich ewolucyjnego ulepszania.
 * @author jfk
 */
public class Population implements IterationSolution {

  protected ConcurrentSkipListSet<GeneticClusteringSolution> solutions;

  public Population(List<GeneticClusteringSolution> _solutions) {
    solutions = new ConcurrentSkipListSet<>(); 
    for(GeneticClusteringSolution gcs : _solutions) {
     solutions.add(gcs);
    }
  }

  public Population nextPopulation() {
    ConcurrentSkipListSet<GeneticClusteringSolution> population = new ConcurrentSkipListSet<>(solutions);

    //1. create descendants and add them to parents
    for(GeneticClusteringSolution sol : solutions) {
      population.add(sol.mutate());
    }

    //2. group them
    Random r = new Random();
    int splitFactor = 2;
    int groupsCount = population.size() / splitFactor;
    List<List<GeneticClusteringSolution>> groups = new ArrayList<>(groupsCount);
    for(int i=0; i<groupsCount; i++) groups.add(new ArrayList<GeneticClusteringSolution>());
    for(GeneticClusteringSolution gcs : population) {
      int index = r.nextInt(groupsCount);
      while(groups.get(index).size() >= splitFactor) index = r.nextInt(groupsCount);
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
    for(int i=0; i<take; i++) taken.add(group.get(i));
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
    return "Population[size:" + solutions.size() + "]";
  }
  
  public ConcurrentSkipListSet<GeneticClusteringSolution> getSolutions() {
    return solutions;
  }
}
