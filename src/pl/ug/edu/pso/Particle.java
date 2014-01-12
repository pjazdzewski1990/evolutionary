package pl.ug.edu.pso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;


public class Particle extends Centroid {
	
	private List<Double> speed;
	private Double speedFactor = 0.1;
	private SwarmInterface swarm;
	private int dir = 1;
	private Boolean pbest_unset = true;
	private Double pbest = Double.MAX_VALUE; //best known fitness of current particle
	private Point p; // best known position of current particle
	public Particle(List<Double> _position, SwarmInterface _swarm) {
		super(_position);												
		this.swarm = _swarm;
		// TODO Auto-generated constructor stub
	}
	
	public void arrangeRandomSpeeds() {
		this.speed = new ArrayList<Double>();
		Random rand = new Random();
		for (int i=0; i<_dims; i++) {
			speed.add(rand.nextDouble());
		} 
	}
	
	public void refreshSpeed() {
		
		List<Double> coords = getCoordinateList();
		System.out.println("--------------------");
		for (int i=0; i<coords.size(); i++) {

			
			
			speed.set(i, speed.get(i) + swarm.getNewSpeedVector().get(i)*(p.getCoordinate(i) - getCoordinate(i)) + swarm.getNewSpeedVector().get(i) *(swarm.getBestPoint().getCoordinate(i) - getCoordinate(i)));
			System.out.println(speed.get(i));
			
			/*if(coords.get(i) + (speed.get(i) * dir) >_ranges.get(i) ) {
				dir = -1;
				
				Double overspeed = (coords.get(i) + speed.get(i))%_ranges.get(i);
				
				speed.set(i, -overspeed);
			}
			
			if(coords.get(i) + (speed.get(i) * dir)<-_ranges.get(i) ) {
				dir = 1;
				Double overspeed = (coords.get(i) + speed.get(i))%_ranges.get(i);
				speed.set(i, -overspeed);
			}*/
			
			coords.set(i, coords.get(i) + (speed.get(i) * dir));
			
		}
	}
	
	public static Particle randomParticle(SwarmInterface sw, int dims, List<Double> ranges) {
		Centroid ct = Centroid.randomCentroid(dims, ranges);
		Particle pt = new Particle(ct.getCoordinateList(), sw);
		
		return pt;
	}
	
	public void recountFitness() {
		
		Double fit = calculateClusterInternalDistance();
	
			
			if(pbest_unset)  {
				pbest_unset = false;
				pbest = fit;
				p = new Point(this.getCoordinateList());
				if(swarm.best_unset()) {
					swarm.setBestFitness(fit);
					swarm.setBestPoint(new Point(this.getCoordinateList()));
					
				}
			} else {
	
				if(fit < pbest) {
					
					pbest = fit;
					p = new Point(this.getCoordinateList());
				}
				if(fit < swarm.getBestFitness()) {
					swarm.setBestFitness(fit);
					swarm.setBestPoint(new Point(this.getCoordinateList()));
				}
			}


	}
	
	public static Particle getNearest(List<Particle> particles, Point point) {
		
		//System.out.println(particles);
		
	    Particle closestCentroid = null;
	    Double minDistance = 0d;
	    for(Particle particle: particles){
	      Double distance = Point.distance(particle, point);
	      if(closestCentroid == null || minDistance > distance){
	        minDistance = distance;
	        closestCentroid = particle;
	      }
	    }
	    
	    return closestCentroid;
	  }
	  
	  public static Particle getNearestParticle(List<Particle> particles, Point point) {
		    List<Particle> centroidsAsPoints = new ArrayList<>(particles.size());
		    for(Particle particle: particles){
		      centroidsAsPoints.add(particle);
		    }
		    return getNearest(centroidsAsPoints, point);
	  }
	
	

}
