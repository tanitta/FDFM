package trit.fdfm.arms
import Jama.Matrix
import processing.core._
import trit.fdfm._


class Arm(var ps: PApplet){
	var angle = new Array[Double](4)
	var l = new Array[Double](2)

	//単位列ベクトル
	var collumVector = new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
	),4,1)
	//手先位置列ベクトル	
	var posVectorHand = new Jama.Matrix(Array(
		Array(0.0),
		Array(0.0),
		Array(0.0),
		Array(1.0)
	),4,1)
	//肘位置列ベクトル
	var posVectorElbow = new Jama.Matrix(Array(
		Array(0.0),
		Array(0.0),
		Array(0.0),
		Array(1.0)
	),4,1)

	var solver = new SolverField.Solver(ps)	

	//Gene
	var gene = new Array[Double](4)
	
	def SetGene(t0:Double, t1:Double, t2:Double, t3:Double) = {
		gene(0) = t0
		gene(1) = t1
		gene(2) = t2
		gene(3) = t3
	}
	
	def SetGeneRand() = {
		gene(0) = 0
		gene(1) = 0
		gene(2) = 0
		gene(3) = 0
	}
	
	def setParson(p1 : Arm, p2 : Arm) = {
		// p1.angle(1) = 0
		// angle(1) = 0
	}
	
	def setup() = {
		
	}
	def update() = {
		
	}
	def draw() = {}
}