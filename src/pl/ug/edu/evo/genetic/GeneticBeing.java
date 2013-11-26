package pl.ug.edu.evo.genetic;

import pl.ug.edu.evo.grid.Centroid;

//TODO: needs sensible name
/**
 * Interfejs definiuje podstawowe operatory geentyczne:
 * mutacje i krzyżowanie 
 * @author jfk
 */
public interface GeneticBeing {
  //Centroid mutate(Centroid parent);
  Centroid breed(Centroid a, Centroid b);
}
