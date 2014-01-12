package pl.ug.edu.pso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.ug.edu.evo.IterationSolution;
import pl.ug.edu.evo.IterativeAlgorithm;
import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

public class ParticleSwarm implements SwarmInterface, IterationSolution{
	
	
	public static int SWARM_SIZE = 30;
	
	
	List<Particle> particles;
	List<Double> speedVector;
	
	
	Boolean gbest_unset = true;
	Point g; //best position of all particles in swarm
	Double gbest = Double.MAX_VALUE; // best fitness
	
	public ParticleSwarm() {
		initialSetup();
	}
	
	public void initialSetup() {
		randomizePositions();

		randomizeSpeeds();
	}
	

	
	public void randomizePositions() {
		particles = new ArrayList<Particle>(SWARM_SIZE);
	
		for(int i=0; i<SWARM_SIZE; i++) {
			particles.add(Particle.randomParticle(this,ParticleSwarmAlgorithm._dims, ParticleSwarmAlgorithm._ranges));
			particles.get(i).arrangeRandomSpeeds();
		}
	}
	
	public void randomizeSpeeds() {
		speedVector = new ArrayList<Double>();
		Random rand = new Random();
		for(int i=0; i<Centroid._dims; i++) {
			speedVector.add(rand.nextDouble());			
		}
		
		
	}
	
	public void recountVelocity() {
		
		
		for(Particle pr : particles) {

			pr.recountFitness();
			
		}
		
		
		for(Particle pr : particles) {
			pr.refreshSpeed();
			
		}		
		
	}

	@Override
	public Point getBestPoint() {
		return g;
	}

	@Override
	public Double getBestFitness() {
		return gbest;
	}

	@Override
	public List<Double> getNewSpeedVector() {
		return speedVector;
	}

	@Override
	public void setBestFitness(Double val) {
		gbest_unset = false;
		this.gbest = val;
		
	}

	@Override
	public void setBestPoint(Point pt) {
		this.g= pt;
		
	}


	@Override
	public double score() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public String asJValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean best_unset() {
		return gbest_unset;
	}
	
}
