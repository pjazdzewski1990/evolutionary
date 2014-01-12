package pl.ug.edu.pso;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;
import pl.ug.edu.evo.genetic.GeneticCentroid;
import pl.ug.edu.evo.genetic.GeneticClusteringSolution;

public class ParticleSwarmSolution implements IterativeAlgorithm{

	
	List<Point> points;
	public static int _dims;
	public static List<Double> _ranges;
	List<ParticleSwarm> swarms;
	
	public ParticleSwarmSolution(List<Point> _points) {
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
		// kilka swarmow dziala na wszystkich punktach czyli bêd¹ szukaæ centroidu dla wszystkich punktow..cholera
		
		
		swarms = new ArrayList<ParticleSwarm>();
		for(int i=0; i<solutionsNum; i++) {
			swarms.add(new ParticleSwarm());
		}
			
			rearrangeCluster();
		    
		    
		   return null; 
	}
	
	public void rearrangeCluster() {
		for(Point point: points){
	    	for(ParticleSwarm swarm : swarms) {
	    		  Particle closest = Particle.getNearest(swarm.particles, point);
			      closest.clusterPoint(point);
	    	}
	    
	    }
		
	}

}
