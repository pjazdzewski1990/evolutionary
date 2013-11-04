package  {
	
	import flash.display.MovieClip;
	import flash.events.Event;
	import flash.display.Sprite;
	import flash.geom.Point;
	import flash.events.MouseEvent;
	
	
	public class Main extends MovieClip {
		

		public var lines:Array = new Array();
		public var points:Array = new Array();
		public function Main() {
			this.addEventListener(Event.ADDED_TO_STAGE, ready);
		}
		
		public function ready(e:Event) {
			
			flush(lines);
			flush(points);


			drawBtn.addEventListener(MouseEvent.CLICK, tracePoints);
			

			
			
		}
		
		public function tracePoints(e:Event) {
			var help:* = JSON.parse(dataTxt.text);
			trace(JSON.parse(dataTxt.text));

			var minX:Number = help.json.points[0].coords[0];
			var minY:Number = help.json.points[0].coords[1];
			var maxX:Number = help.json.points[0].coords[0];
			var maxY:Number = help.json.points[0].coords[1];


			for(var i:int=0; i<help.json.points.length; i++){

				if(minY>help.json.points[i].coords[1]) minY = help.json.points[i].coords[1];
				
				if(maxY<help.json.points[i].coords[1]) maxY = help.json.points[i].coords[1];
				
				if(minX>help.json.points[i].coords[0]) minX = help.json.points[i].coords[0];
				
				if(maxX<help.json.points[i].coords[0]) maxX = help.json.points[i].coords[0];
			}
			
			var scaleX:Number = (bg.width/(maxX-minX)) * 0.9;
			var scaleY:Number = (bg.height/(maxY-minY))  * 0.9;


				
			for(var i:int=0; i<help.json.points.length; i++){
				trace(- minX * scaleX);
				addPoint(10 + ((help.json.points[i].coords[0])*scaleX) -(minX * scaleX), 10 +((help.json.points[i].coords[1])*scaleY)-(minY * scaleY), help.json.points[i].coords[0], help.json.points[i].coords[1]);
			}
			
		}
		
		
		public function addPoint(x:Number, y:Number, x0, y0) {
			
			var help = new SinglePoint();
			help.x = x;
			help.y = y;
			trace(x);
			help.etykieta.text="X: " + x0 + ", Y: " + y0;
			
			points.push(help);
			bg.addChild(points[points.length-1]);
			
			
		}
		
		public function flush(ob:*) {
			for(var i:int = 0; i<ob.length; i++) {
				
				bg.removeChild(ob[i]);
				ob[i] = null;
				
			}
			ob = null;
			ob = new Array();
			
		}
		

		
		public function drawLine(centroid:*, pt:*) {
			
			
		}
		
		
	}
	
}
