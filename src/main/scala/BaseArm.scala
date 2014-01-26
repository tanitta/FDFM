package trit.fdfm.arm

import Jama.Matrix
import processing.core._
import trit.fdfm._


class Arm(var ps: PApplet){
	var theta = new Array[Double](5)
	var l = new Array[Double](3)
	l(1) = 0.29;
	l(2) = 0.3;
	
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
	
	//同次変換行列 
	//ex)T01 : X0 = T01 X1
	var Td0 = new Jama.Matrix(4,4)
	var T01 = new Jama.Matrix(4,4)
	var T12 = new Jama.Matrix(4,4)
	var T23 = new Jama.Matrix(4,4)
	var T34 = new Jama.Matrix(4,4)
	
	var T02 = new Jama.Matrix(4,4)
	var T03 = new Jama.Matrix(4,4)
	var T04 = new Jama.Matrix(4,4)

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
	
	tmz.times(tmx) //tmz*tmx
}

	//IK: 逆運動学により座標値から各関節角度を計算する
	//parameter: Data.data index
	//return: Unit
	def IK(inPosVectorElbow:Jama.Matrix, inPosVectorHand:Jama.Matrix) = {	
		// posVectorElbow = new Jama.Matrix(Array(
		// 	Array(Data.dataElbow(index)(0)),
		// 	Array(-Data.dataElbow(index)(2)),
		// 	Array(Data.dataElbow(index)(1)),
		// 	Array(1.0)
		// ),4,1)
		
		// inPosVectorHand = new Jama.Matrix(Array(
		// 	Array(Data.dataHand(index)(0)),
		// 	Array(-Data.dataHand(index)(2)),
		// 	Array(Data.dataHand(index)(1)),
		// 	Array(1.0)
		// ),4,1)
		
		theta(1) = math.atan(inPosVectorElbow.get(1,0)/inPosVectorElbow.get(0,0))
		
		var rXY0 = math.pow(math.pow(inPosVectorElbow.get(0,0),2.0)+math.pow(inPosVectorElbow.get(1,0),2.0),0.5)
		theta(2) = math.atan2(rXY0,inPosVectorElbow.get(2,0))+math.Pi
		
		var T01 = TransMat(0.0, 0.0, -math.Pi/2.0, theta(1))
		var T12 = TransMat(0.0, 0.0, math.Pi/2.0, theta(2))
		var T02 = T01.times(T12)
		var T20 = T02.inverse()
		var posVectorHand2 = T20.times(inPosVectorHand)
		theta(3) = math.atan(posVectorHand2.get(1,0)/posVectorHand2.get(0,0))
		
		var rXY2 = math.pow(math.pow(posVectorHand2.get(0,0),2.0)+math.pow(posVectorHand2.get(1,0),2.0),0.5)
		theta(4) = math.atan2(posVectorHand2.get(2,0)+l(1),rXY2)
	}

	// var solver = new SolverField.Solver(ps)	

	// //Gene
	// var gene = new Array[Double](4)
	
	// def SetGene(t0:Double, t1:Double, t2:Double, t3:Double) = {
	// 	gene(0) = t0
	// 	gene(1) = t1
	// 	gene(2) = t2
	// 	gene(3) = t3
	// }
	
	// def SetGeneRand() = {
	// 	gene(0) = 0
	// 	gene(1) = 0
	// 	gene(2) = 0
	// 	gene(3) = 0
	// }
	
	// def setParson(p1 : Arm, p2 : Arm) = {
	// 	// p1.angle(1) = 0
	// 	// angle(1) = 0
	// }
	
	def setup() = {
		
	}
	def update() = {
		
	}
	def draw() = {
		
	}
}