package pl.ug.edu.pso;

import java.util.ArrayList;
import java.util.List;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.base.Point;

public class ParticleSwarmSolution implements IterationSolution{
	
	//pojedyñcze rozwi¹zanie
	List<Particle> centroids;
	
	public ParticleSwarmSolution() {
		centroids = new ArrayList<Particle>();
		// TODO Auto-generated constructor stub
	}
	
	public void rearrangeCluster(List<Point> points) {
		for(Point point: points){
			 Particle closest = Particle.getNearest(centroids, point);
		      closest.clusterPoint(point);
		}
	}

	@Override
	public double score() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String asJValue() {
		return centroids.toString();
	}
	
}
