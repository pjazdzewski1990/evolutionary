package pl.ug.edu.pso;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.genetic.GeneticCentroid;
import pl.ug.edu.evo.genetic.GeneticClusteringSolution;

public class ParticleSwarmAlgorithm implements IterativeAlgorithm{

	
	List<Point> points;
	public static int _dims;
	public static List<Double> _ranges;
	List<ParticleSwarm> swarms;
	List<ParticleSwarmSolution> solutions;
	
	public ParticleSwarmAlgorithm(List<Point> _points) {
		_ranges = Point.calculateRanges(_points);
	    _dims = _ranges.size();
	    points = _points;
	}
	
	@Override
	public IterationSolution nextRound(IterationSolution points) {
		// TODO Auto-generated method stub
		for(ParticleSwarm swarm : swarms) {
			swarm.recountVelocity();
		}
		rearrangeCluster();
		
		return null;
	}

	@Override
	public IterationSolution initialSolution(int solutionsNum) {
		// TODO Auto-generated method stub
		
		//JAK TO PODZIELIÆ??????
		//nie teges z nadawaniem podleg³ych punktów po zmianach pozycji centroidów
		
		swarms = new ArrayList<ParticleSwarm>();
		solutions = new ArrayList<ParticleSwarmSolution>();
		for(int i=0; i<solutionsNum; i++) {
			swarms.add(new ParticleSwarm());
	
		}
		
		for(int i=0; i<ParticleSwarm.SWARM_SIZE; i++) {
			solutions.add(new ParticleSwarmSolution());
			for(int j=0; j<solutionsNum; j++) {
				solutions.get(i).centroids.add(swarms.get(j).particles.get(i));
			}
		}

			
			rearrangeCluster();
			
		    
		   return null; 
	}
	
	public void rearrangeCluster() {
		
	    	for(ParticleSwarmSolution solution : solutions) {
	    		 solution.rearrangeCluster(points);
	    	}

		
	}

}
