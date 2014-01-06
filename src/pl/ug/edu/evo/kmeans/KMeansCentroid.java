package pl.ug.edu.evo.kmeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.ug.edu.evo.base.Centroid;
import pl.ug.edu.evo.base.Point;

public class KMeansCentroid extends Centroid{

	public KMeansCentroid(List<Double> _position) {
		super(_position);
		// TODO Auto-generated constructor stub
	}
	public KMeansCentroid(Point pt) {
		super(pt);
	}

	 public KMeansCentroid findNewPosition(){
		    Random generator = new Random();
		    List<Double> pos = new ArrayList<>();
		    double number = pointsInCluster.size();
		    for(int dim=0; dim<getCoordinateList().size(); dim++){
		      if(number > 0){
		        double sum = 0;
		        for(Point p : pointsInCluster){
		          sum += p.getCoordinateList().get(dim);
		        }
		        pos.add(sum/number);
		      }else{
		        pos.add(generator.nextDouble());
		      }
		    }
		    return new KMeansCentroid(pos);
		  }

	 
}
