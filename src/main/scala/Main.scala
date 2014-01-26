package trit.fdfm
import processing.core._
import trit.fdfm._

object Main extends processing.core.PApplet {

	var drawer = new drawing.Drawer(this)
	var solver = new calculation.Calculater(this)	
	override def setup(){
		frameRate(60)
		solver.setup
		drawer.setup
	}
	
	def update(){
		solver.update
		drawer.update
	}
	
	override def draw(){
		update
		
		drawer.draw
		solver.draw
	}
	
	def main(args: Array[String]){
		runSketch()
	}
	
 	override def sketchFullScreen():Boolean = {
 		return false
		// return true
 	}

	
}