package trit.fdfm
import processing.core._
import trit.fdfm._

object Main extends processing.core.PApplet {

	var drawer = new DrawerField.Drawer(this)
	var solver = new SolverField.Solver(this)	
	override def setup(){
		solver.setup
		drawer.setup
	}
	
	def update(){
		solver.update
		drawer.update
	}
	
	override def draw(){
		update
		
		solver.draw
		drawer.draw
	}
	
	def main(args: Array[String]){
		runSketch()
	}
	
}