package trit.fdfm.arm

import Jama.Matrix
import processing.core._
import trit.fdfm._

class Arm(ps: PApplet){
	var this.ps = ps
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

	def VectorProduct(vectorA:Jama.Matrix, vectorB:Jama.Matrix):Jama.Matrix = {
		var vectorC = new Jama.Matrix(Array(
			Array(vectorA.get(1,0)*vectorB.get(2,0) - vectorA.get(2,0)*vectorB.get(1,0)),
			Array(vectorA.get(2,0)*vectorB.get(0,0) - vectorA.get(0,0)*vectorB.get(2,0)),
			Array(vectorA.get(0,0)*vectorB.get(1,0) - vectorA.get(1,0)*vectorB.get(0,0)),
			Array(1.0)
		),4,1);
		
		ps.stroke(0, 0, 0)	  
		ps.strokeWeight(10)
		ps.line(0,0,0,
			vectorC.get(0,0).toFloat,
			vectorC.get(1,0).toFloat,
			vectorC.get(2,0).toFloat);
		
		vectorC;
	}
	
	def UnitVector(vectorA:Jama.Matrix):Jama.Matrix = {
		var abs = math.pow(math.pow(vectorA.get(0,0),2) + math.pow(vectorA.get(1,0),2) + math.pow(vectorA.get(2,0),2), 0.5)
		var vectorB = new Jama.Matrix(Array(
			Array(vectorA.get(0,0)/abs),
			Array(vectorA.get(1,0)/abs),
			Array(vectorA.get(2,0)/abs),
			Array(1.0)
		),4,1);
		vectorB
	}

	// FK
	// 順運動学により各関節角度と長さから関節角度を計算する
	// parameter: t1~t4:各関節角, l1,l2:腕の長さ
	// return: Unit
	def FK(t:Array[Double],l1:Double,l2:Double) = {	
		Td0 = TransMat(0.0, 0.0, -math.Pi/2.0, 0.0)
		T01 = TransMat(0.0, 0.0, -math.Pi/2.0, t(1))
		T12 = TransMat(0.0, 0.0, math.Pi/2.0, t(2))
		T23 = TransMat(0.0, -l1, math.Pi/2.0, t(3))
		T34 = TransMat(l2, 0.0, 0.0, t(4))
		
		T02 = T01.times(T12)
		T03 = T01.times(T12.times(T23))//Elbow
		T04 = T01.times(T12.times(T23.times(T34)))//Hand
		
		var posVectorElbow = T03.times(collumVector)	
		var posVectorHand = T04.times(collumVector)
		(posVectorElbow,posVectorHand)
	}

	//IK: 逆運動学により座標値から各関節角度を計算する
	//parameter: Data.data index
	//return: Array[Double](5)
	def IK(inPosVectorElbow:Jama.Matrix, inPosVectorHand:Jama.Matrix) = {
		var t = new Array[Double](5)
		t(1) = math.atan(inPosVectorElbow.get(1,0)/inPosVectorElbow.get(0,0))
		
		var rXY0 = math.pow(math.pow(inPosVectorElbow.get(0,0),2.0)+math.pow(inPosVectorElbow.get(1,0),2.0),0.5)
		t(2) = math.atan2(rXY0,inPosVectorElbow.get(2,0))+math.Pi
		
		var T01 = TransMat(0.0, 0.0, -math.Pi/2.0, t(1))
		var T12 = TransMat(0.0, 0.0, math.Pi/2.0, t(2))
		var T02 = T01.times(T12)
		var T20 = T02.inverse()
		var posVectorHand2 = T20.times(inPosVectorHand)
		t(3) = math.atan(posVectorHand2.get(1,0)/posVectorHand2.get(0,0))
		
		var rXY2 = math.pow(math.pow(posVectorHand2.get(0,0),2.0)+math.pow(posVectorHand2.get(1,0),2.0),0.5)
		t(4) = math.atan2(posVectorHand2.get(2,0)+l(1),rXY2)
		
		t		
	}

	// var solver = new SolverField.Solver(ps)	

	var armDrawer = new calculation.ArmDrawer(this, ps);
	
	def setup() = {		
		// theta = IK(pElbow,pHand);
		// println("theta1"+theta(1)+"\n");
		// println("theta2"+theta(2)+"\n");
		// println("theta3"+theta(3)+"\n");
		// println("theta4"+theta(4)+"\n");
		
		posVectorElbow = FK(Array(0.0,1.5,0.5,0.5,0),l(1),l(2))._1;
		posVectorHand = FK(Array(0.0,1.5,0.5,0.5,0),l(1),l(2))._2;
		
		var thetaVz = Math.atan2(-UnitVector(VectorProduct(posVectorHand,posVectorElbow)).get(0,0),UnitVector(VectorProduct(posVectorHand,posVectorElbow)).get(1,0));
		var thetaVx = Math.atan2(-UnitVector(VectorProduct(posVectorHand,posVectorElbow)).get(2,0),UnitVector(VectorProduct(posVectorHand,posVectorElbow)).get(1,0));
		
		var Tv0 = TransMat(0, 0, thetaVx, thetaVz);
		
		println("z : " + thetaVz);
		println("x : " + thetaVx);
		
	}
	def update() = {		
	}
	def draw() = {
		armDrawer.DrawArm();
		armDrawer.DrawAxis();
		
	}
}

class GArm(ps: PApplet) extends Arm(ps) {
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
}