package pl.ug.edu.evo.genetic;

//TODO: needs sensible name
/**
 * Interfejs definiuje podstawowe operatory geentyczne:
 * mutacje i krzyżowanie 
 * @author jfk
 */
public interface GeneticBeing {
  GeneticBeing mutate(GeneticBeing _);
  GeneticBeing breed(GeneticBeing a, GeneticBeing b);
}
