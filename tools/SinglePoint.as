package  {
	
	import flash.display.MovieClip;
	import flash.geom.ColorTransform;

	
	
	public class SinglePoint extends MovieClip {
		
		public var multip:Number = 1;
		public var centroid:int = 0;
		
		public function SinglePoint() {
			// constructor code
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
			trace(clr);
			pointSprite.transform.colorTransform = transf;
			
			
		}
		

	}
	
}
