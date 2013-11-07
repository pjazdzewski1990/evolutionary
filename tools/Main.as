package  {
	
	import flash.display.MovieClip;
	import flash.events.Event;
	import flash.display.Sprite;
	import flash.geom.Point;
	import flash.events.MouseEvent;
	import fl.data.DataProvider;
	
	
	public class Main extends MovieClip {
		

		public var lines:Array = new Array();
		public var points:Array = new Array();
		var items:Array;
		public var json:*;
		public var minX:Number;
		public var minY:Number;
		public var maxX:Number;
		public var maxY:Number;
		public var bgScaleX:Number;
		public var bgScaleY:Number;
		public var scaleSet:Boolean = false;
		public function Main() {
			this.addEventListener(Event.ADDED_TO_STAGE, ready);
		}
		
		public function ready(e:Event) {
			
			flush(lines);
			flush(points);


			drawBtn.addEventListener(MouseEvent.CLICK, tracePoints);
			phaseList.addEventListener(Event.CHANGE, selectItem);
			

			
			
		}
		
		public function tracePoints(e:Event) {
			flush(lines);
			flush(points);

			json = JSON.parse(dataTxt.text);
			//trace(JSON.parse(dataTxt.text));

			//help.json.points[i].coords[1]
			drawPoints(json.json.points);
			items = null;
			items = [{label:"Loaded data", data:0} ]; 
			var i:int = 0;
			for(i=0; i<json.json.round.length; i++){
				items.push({label: "Round " + String(i+1), data: (i+1)});
			}
			items.push({label:"Solution", data:i+1});

			phaseList.dataProvider = new DataProvider(items); 

		}
		
		public function selectItem(e:Event) {

			var num:int = 0;
			
			num = ComboBox(e.target).selectedItem.data;
			trace(num);
			if(num==0) {
				drawPoints(json.json.points);
				
			} else if(num==items.length-1) {
				drawPoints(json.json.solution, true);
			} else {
				drawPoints(json.json.round[num-1].solution, true);
			}

			
			
		}
		
		public function drawPoints(list:*, cent:Boolean=false) {
			flush(lines);
			flush(points);
			
			if(!cent) {
				if(!scaleSet) {
						scaleSet=true;
					
					minX= list[0].coords[0];
					minY= list[0].coords[1];
					maxX= list[0].coords[0];
					maxY= list[0].coords[1];


					for(var i:int=0; i<list.length; i++){

						if(minY>list[i].coords[1]) minY = list[i].coords[1];
						
						if(maxY<list[i].coords[1]) maxY = list[i].coords[1];
						
						if(minX>list[i].coords[0]) minX = list[i].coords[0];
						
						if(maxX<list[i].coords[0]) maxX = list[i].coords[0];
					}
					
					bgScaleX = (bg.width/(maxX-minX)) * 0.9;
					bgScaleY = (bg.height/(maxY-minY))  * 0.9;

				}
					
				for(var i:int=0; i<list.length; i++){
					addPoint(10 + ((list[i].coords[0])*bgScaleX) -(minX * bgScaleX), 10 +((list[i].coords[1])*bgScaleY)-(minY * bgScaleY), list[i].coords[0], list[i].coords[1], 0);
				}
			} else {

				var pX:Number;
				var pY:Number;
				var cpX:Number;
				var cpY:Number;

				for(var i:int=0; i<list.length; i++){

					cpX = list[i].Centroid[0].coords[0];
					cpY = list[i].Centroid[0].coords[1];

					addPoint(10 + (cpX* bgScaleX) - (minX * bgScaleX) , 10 + (cpY* bgScaleY) - (minY * bgScaleY),cpX, cpY,i,"CENTROID ");
					
					for(var j:int=0; j<list[i].Centroid[1].length; j++){

						pX = list[i].Centroid[1][j].coords[0];
						pY = list[i].Centroid[1][j].coords[1];
						drawLine(10 + (cpX* bgScaleX) - (minX * bgScaleX) ,10 + (cpY* bgScaleY) - (minY * bgScaleY),10 + (pX* bgScaleX) - (minX * bgScaleX), 10 + (pY* bgScaleY) - (minY * bgScaleY));
						addPoint(10 + (pX* bgScaleX) - (minX * bgScaleX) , 10 + (pY* bgScaleY) - (minY * bgScaleY),pX, pY,i);
					}
					
				}
				
			}
			
		}
		
		public function addPoint(x:Number, y:Number, x0, y0,c,etyk:String="") {
			
			var help = new SinglePoint();
			help.x = x;
			help.y = y;
		//	trace(x);
			help.centroid =c;
			help.setColor();
			help.etykieta.text=etyk + "X: " + x0 + ", Y: " + y0;
			
			points.push(help);
			bg.addChild(points[points.length-1]);
			
			
		}
		
		public function flush(ob:*) {
			for(var i:int = 0; i<bg.numChildren-1; i++) {
					bg.removeChildAt(1);
				}
			if(ob!=null&&ob.length>0) {
				
				for(var i:int = 0; i<ob.length; i++) {
					

					ob[i] = null;
					
				}
				ob = null;
				ob = new Array();
			}
			
		}
		

		
		public function drawLine(centroidX, centroidY, ptX, ptY) {

			var line:Sprite = new Sprite();
			line.graphics.lineStyle(1,0x00ff00,0.3);
			line.graphics.moveTo(centroidX, centroidY);
			line.graphics.lineTo(ptX, ptY);
			lines.push(line);
			bg.addChild(line);
			
			
			
		}
		
		
	}
	
}
