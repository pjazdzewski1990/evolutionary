package pl.ug.edu.pso;

import java.util.List;

import pl.ug.edu.evo.base.Point;

public interface SwarmInterface {
	public Point getBestPoint();
	public Double getBestFitness();
	public List<Double> getNewSpeedVector();
	public void setBestFitness(Double val);
	public void setBestPoint(Point pt);
	
}
