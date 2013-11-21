package trit.fdfm
import processing.core._
import trit.fdfm._
object DrawerField{
	class Camera(var ps:PApplet){
		var fov = 0.04f		
		var rotate = 0.0
		
		var ax = 0.0f
		var ay = 0.0f
		var az = 0.0f
		
		var bx = 0.0f
		var by = 0.0f
		var bz = 0.0f
	
		def setup = {
				
		}
		
		def update = {
			rotate += (ps.pmouseX-ps.mouseX).toDouble*0.01
	 		var x:Double = 15.0*math.cos(rotate)
	 		var z:Double = 15.0*math.sin(rotate)
			ps.camera( x.toFloat , -10.0f, z.toFloat, // 視点X, 視点Y, 視点Z
			0, 0, 0, // 中心点X, 中心点Y, 中心点Z
			0.0f, 1.0f, 0.0f); // 天地X, 天地Y, 天地Z
	 		ps.perspective(fov, -Env.General.sizScreenX.toFloat/Env.General.sizScreenY.toFloat, 0.01f, 400.00f)			
		}	
	}
	
	class Drawer(var ps: PApplet){
		var camera = new Camera(ps)
		
		def setup = {
			ps.size(Env.General.sizScreenX,Env.General.sizScreenY,PConstants.OPENGL)
			ps.colorMode(PConstants.HSB, 100)
			ps.background(0,0,0)
			ps.smooth()
		}
		def update = {}
		def draw = {
			ps.strokeWeight(1)
			ps.background(0,0,0);
			this.camera.update
			ps.stroke(50)
			//ps.box(1);
			var size = 10
			ps.stroke(0,0,100,30)
			for(i <- -size to size){
				ps.line(i.toFloat/size.toFloat,0,-size.toFloat/size.toFloat,i.toFloat/size.toFloat, 0,size.toFloat/size.toFloat)
				ps.line(-size.toFloat/size.toFloat,0,i.toFloat/size.toFloat, size.toFloat/size.toFloat,0,i.toFloat/size.toFloat)
				
			}
			ps.strokeWeight(4)	  
			ps.stroke(0, 100, 100,32)
			ps.line(0,0,0,1,0,0)
			
			ps.stroke(100/3, 100, 100,32)
			ps.line(0,0,0,0,1,0)
			
			ps.stroke(100/3*2, 100, 100, 32)
			ps.line(0,0,0,0,0,1)
		}
	}
}