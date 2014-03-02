package trit.fdfm
import processing.core._
import trit.fdfm._

object Main extends processing.core.PApplet {
	var drawer = new drawing.Drawer(this)
	var calculater = new calculation.Calculater(this)	
	override def setup(){
		frameRate(60)
		calculater.setup
		drawer.setup
	}
	
	// def update(){
	// 	calculater.update
	// 	drawer.update
	// }
	
	override def draw(){
		// update
		
		// drawer.draw
		// calculater.draw
	}
	
	def main(args: Array[String]){
		runSketch()
	}
	
 	override def sketchFullScreen():Boolean = {
 		return false
		// return true
 	}

	
}