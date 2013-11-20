package trit.fdfm
import processing.core._
import trit.matrix._
import trit.fdfm._

object Main extends processing.core.PApplet {

	var drawer = new DrawerField.Drawer(this)
		
	override def setup(){
		drawer.setup
	}
	
	def update(){
		drawer.update
	}
	
	override def draw(){
		update

		drawer.draw
	}
	
	def main(args: Array[String]){
		runSketch()
	}
	
}