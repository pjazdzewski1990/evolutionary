package  {
	
	import flash.display.MovieClip;
	import flash.geom.ColorTransform;
	import flash.events.MouseEvent;
	

	
	
	public class SinglePoint extends MovieClip {
		
		public var multip:Number = 1;
		public var centroid:int = 0;
		
		
		public function SinglePoint() {
			// constructor code
			this.etykieta.visible = false;
			this.pointSprite.addEventListener(MouseEvent.MOUSE_OVER, mouseHandler);
			this.pointSprite.addEventListener(MouseEvent.MOUSE_OUT, mouseHandler);
		}

		public function isCentroid(p:Boolean) {
			if(p) {
				marker.gotoAndStop(2);
				
			} else {
				marker.gotoAndStop(1);
			}
		}
		
		public function mouseHandler(e:MouseEvent) {
			switch (e.type) {
				case "mouseOver":
					this.etykieta.visible = true;
				break;
				
				case "mouseOut":
					this.etykieta.visible = false;
				break;
	
			}
			
		}
		
		public function setColor() {

			var transf = new ColorTransform();
			var i:int = centroid;
			var clr:String =  "0x" + (128 * int(i/6)).toString(16);
			if(clr.length%2==1) clr+="0";
			clr+=(128 * int(i/2)).toString(16);
			if(clr.length%2==1) clr+="0";
			clr+=(128 * int(i%2)).toString(16);
			if(clr.length%2==1) clr+="0";
			
			
			transf.color = uint(clr);

			pointSprite.transform.colorTransform = transf;
			
			
		}
		

	}
	
}
