package pl.ug.edu.pso;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

public class ParticleSwarmSolution implements IterationSolution{
	
	//pojedy�cze rozwi�zanie
	public List<Particle> centroids;
	
	public ParticleSwarmSolution() {
		centroids = new ArrayList<Particle>();
		// TODO Auto-generated constructor stub
	}
	
	public void rearrangeCluster(List<Point> points) {
		for(Particle pt : centroids) {
			pt.flushCluster();
		}
		for(Point point: points){
			 Particle closest = Particle.getNearest(centroids, point);
		      closest.clusterPoint(point);
		}
	}

	@Override
	public double score() {
		 double score = 0;
		    for(Centroid c : centroids){
		    	if(score < Double.MAX_VALUE) {
			      double partialResult = c.calculateClusterInternalDistance();
			      if(partialResult == Double.MAX_VALUE){
			        score = Double.MAX_VALUE;
			        return score;
			      }else{
			        score += (partialResult/*/c.getPointsInCluster().size()*/);
			      }
		    	}
		    }
		    return score;
	}

	@Override
	public String asJValue() {
		return centroids.toString();
	}
	
}
