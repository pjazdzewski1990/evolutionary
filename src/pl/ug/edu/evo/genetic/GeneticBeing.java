package pl.ug.edu.evo.genetic;

//TODO: needs sensible name
public interface GeneticBeing {
  GeneticBeing mutate(GeneticBeing _);
  GeneticBeing breed(GeneticBeing a, GeneticBeing b);
}
