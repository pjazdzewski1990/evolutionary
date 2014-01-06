package pl.ug.edu.evo.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

public class GeneticCentroid extends Centroid{

	public GeneticCentroid(List<Double> _position) {
		super(_position);
		// TODO Auto-generated constructor stub
	}
	
public Centroid mutate() {
		
		// Tworzy nowe punkty centroidu, przesuwa o iloczyn random.gaussa razy wa�ony sredni dystans pomi�dzy centroidem a najdalszym punktem
		
		
		//tl;dr 
		// Kalkuluje nowy punkt pomi�dzy centoridem a najdalszym punktem i przemnza jego coordy przez random
		
		//Point nearestPoint = null;
		Point furthestPoint = null;
		//double minVal = Double.NaN;
		double maxVal = Double.NaN;
		double dist = Double.NaN;

		for(Point d : pointsInCluster) {
			dist = Point.distance(d, this);

			if(Double.isNaN(maxVal)) {
				//minVal = dist;
				maxVal = dist;
				//nearestPoint = d;
				furthestPoint = d;
			//} else if(minVal>dist) {
			//	nearestPoint = d;
			//	minVal = dist;
			} else if(maxVal<dist) {
				furthestPoint = d;
				maxVal = dist;
			}
			
		}
			

		Centroid breed = new Centroid(calculateBreedCoords(furthestPoint));
		return breed;
	}
 

 	private List<Double> calculateBreedCoords(Point far) {
 		List<Double> coords = new ArrayList<Double>();
 		Random r = new Random(); 
 		
 		for(int i=0; i<this.getCoordinateList().size(); i++) {

 			coords.add(r.nextGaussian() * ((getPointsInCluster().size()-1) * (this.getCoordinateList().get(i)) + far.getCoordinateList().get(i))/getPointsInCluster().size());

 		}
 		
 		
 		return coords;
 	}


}
