package trit.fdfm

import processing.core._
import Jama.Matrix


object SolverField{
	var L1:Double = 0.3
	var L2:Double = 0.35
	
	// def TransMat(a:Double,d:Double,alpha:Double,theta:Double): MatrixField.Matrix[Double] = {
	// 	var tmz = new MatrixField.Matrix[Double](List(
	// 		math.cos(theta)::	-math.sin(theta)::	0.0::	0.0::	Nil,
	// 		math.sin(theta)::	math.cos(theta)::	0.0::	0.0::	Nil,
	// 		0.0::				0.0::				1.0::	0.0::	Nil,
	// 		0.0::				0.0::				0.0::	1.0::	Nil
	// 	))
		
	// 	var tmx = new MatrixField.Matrix[Double](List(
	// 		1.0::	0.0::				0.0::				a::		Nil,
	// 		0.0::	math.cos(alpha)::	-math.sin(alpha)::	0.0::	Nil,
	// 		0.0::	math.sin(alpha)::	math.cos(alpha)::	d::		Nil,
	// 		0.0::	0.0::				0.0::				1.0::	Nil
	// 	))
		
	// 	var tm: MatrixField.Matrix[Double] = tmz*tmx
	// 	tm
	// }
	
	def TransMat(a:Double,d:Double,alpha:Double,theta:Double): Jama.Matrix = {

		var tmz = new Matrix(Array(
			Array(math.cos(theta),	-math.sin(theta),	0.0,	0.0),
			Array(math.sin(theta),	math.cos(theta),	0.0,	0.0),
			Array(0.0,				0.0,				1.0,	0.0),
			Array(0.0,				0.0,				0.0,	1.0)
		),4,4)
		
		var tmx = new Matrix(Array(
			Array(1.0,	0.0,				0.0,				a),
			Array(0.0,	math.cos(alpha),	-math.sin(alpha),	0.0),
			Array(0.0,	math.sin(alpha),	math.cos(alpha),	d),
			Array(0.0,	0.0,				0.0,				1.0)
		),4,4)
		
		// get(int, int) - Method in class Jama.Matrix
		// Get a single element.
		tmz.times(tmx) //tmz*tmx
	}
	
	class Solver(var ps: PApplet){
		var theta = new Array[Double](5)
		
		var Td0 = new Jama.Matrix(4,4)
		var T01 = new Jama.Matrix(4,4)
		var T12 = new Jama.Matrix(4,4)
		var T23 = new Jama.Matrix(4,4)
		var T34 = new Jama.Matrix(4,4)
		
		def setup = {}
		
		def update = {
			theta(1) = 0.0
			theta(2) = 0.0
			theta(3) = 0.0
			theta(4) = 0.0
			
			
			Td0 = TransMat(0.0, 0.0, -math.Pi/2.0, 0.0)
			T01 = TransMat(0.0, 0.0, -math.Pi/2.0, theta(1))
			T12 = TransMat(0.0, 0.0, math.Pi/2.0, theta(2))
			T23 = TransMat(0.0, L1, math.Pi/2.0, theta(3))
			T34 = TransMat(L2, 0.0, 0.0, theta(4))
		}
		
		def draw = {}
	}
}