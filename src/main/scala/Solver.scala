package trit.fdfm

import processing.core._
import trit.matrix._


object SolverField{
	var L1:Double = 0.3
	var L2:Double = 0.35
	
	def TransMat(a:Double,d:Double,alpha:Double,theta:Double): MatrixField.Matrix[Double] = {
		var tmz = new MatrixField.Matrix[Double](List(
			math.cos(theta)::	-math.sin(theta)::	0.0::	0.0::	Nil,
			math.sin(theta)::	math.cos(theta)::	0.0::	0.0::	Nil,
			0.0::				0.0::				1.0::	0.0::	Nil,
			0.0::				0.0::				0.0::	1.0::	Nil
		))
		
		var tmx = new MatrixField.Matrix[Double](List(
			1.0::	0.0::				0.0::				a::		Nil,
			0.0::	math.cos(alpha)::	-math.sin(alpha)::	0.0::	Nil,
			0.0::	math.sin(alpha)::	math.cos(alpha)::	d::		Nil,
			0.0::	0.0::				0.0::				1.0::	Nil
		))
		
		var tm: MatrixField.Matrix[Double] = tmz*tmx
		tm
	}
	
	class Solver(var ps: PApplet){
		var theta = Array[Double](5)
		
		var Td0 = new MatrixField.Matrix[Double](Nil)
		var T01 = new MatrixField.Matrix[Double](Nil)
		var T12 = new MatrixField.Matrix[Double](Nil)
		var T23 = new MatrixField.Matrix[Double](Nil)
		var T34 = new MatrixField.Matrix[Double](Nil)
		
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