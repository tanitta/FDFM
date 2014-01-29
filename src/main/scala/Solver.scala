package trit.fdfm.calculation

import processing.core._
import Jama.Matrix
import trit.fdfm._

class Calculater(var ps: PApplet){		
	var cmaes = new CMAES(ps)
	var armIns = new arm.Arm(ps)
	
	def setup = {
		// PTPSolver(47.0/60.0)
		// FK(theta(1),theta(2),theta(3),theta(4),getL1((Data.stepMax-1)),getL2((Data.stepMax-1)))
		armIns.setup();
		cmaes.Solve()
		armIns.SetGene(cmaes.GetGene())
	}
	
	def update = {
		armIns.update();
	}

	def draw = {
		// Arm(データ)
		// var i = 0;
		for(a <- 0 to 46){
			var i = a 
			ps.stroke(i.toFloat/Data.dataElbow.length.toFloat*100f, 0, 50)
			
			ps.strokeWeight(10)
			ps.fill(i.toFloat/Data.dataElbow.length.toFloat*100f, 0, 50)
			ps.point(Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat)
			
			ps.noFill()
			ps.strokeWeight(15)
			ps.point(Data.dataHand(i)(0).toFloat, -Data.dataHand(i)(2).toFloat, Data.dataHand(i)(1).toFloat)
			
			// if(i%4 == 0 ){
				ps.strokeWeight(1)	
				ps.line(0,0,0,Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat)
				ps.line(Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat,Data.dataHand(i)(0).toFloat, -Data.dataHand(i)(2).toFloat, Data.dataHand(i)(1).toFloat)
			// }
			
			ps.stroke(i.toFloat/Data.dataElbow.length.toFloat*100f, 0, 100)
			ps.strokeWeight(6)
			ps.point(Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat)
			
			ps.strokeWeight(11)
			ps.point(Data.dataHand(i)(0).toFloat, -Data.dataHand(i)(2).toFloat, Data.dataHand(i)(1).toFloat)
			
			// ps.stroke(i.toFloat/Data.dataElbow.length.toFloat*100f, 0, 50)
			// ps.strokeWeight(16)
			// ps.line(0,0,0,Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat)
			// ps.line(Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat,Data.dataHand(i)(0).toFloat, -Data.dataHand(i)(2).toFloat, Data.dataHand(i)(1).toFloat)
		}
		armIns.draw();
		
	}
	// var theta = new Array[Double](5)
	
	// var Td0 = new Jama.Matrix(4,4)
	// var T01 = new Jama.Matrix(4,4)
	// var T12 = new Jama.Matrix(4,4)
	// var T23 = new Jama.Matrix(4,4)
	// var T34 = new Jama.Matrix(4,4)
	
	// var T02 = new Jama.Matrix(4,4)
	// var T03 = new Jama.Matrix(4,4)
	// var T04 = new Jama.Matrix(4,4)
	
	// var collumVector = new Jama.Matrix(Array(
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(1.0)
	// ),4,1)
	
	// var posVectorHand = new Jama.Matrix(Array(
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(1.0)
	// ),4,1)
	
	// var posVectorElbow = new Jama.Matrix(Array(
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(1.0)
	// ),4,1)
	
	// def getL1(index:Int):Double = {
	// 	math.pow(math.pow(Data.dataElbow(index)(0),2.0)+math.pow(Data.dataElbow(index)(1),2.0)+math.pow(Data.dataElbow(index)(2),2.0),0.5)
	// }
	
	// def getL2(index:Int):Double = {
	// 	math.pow(math.pow(Data.dataElbow(index)(0)-Data.dataHand(index)(0),2.0)+math.pow(Data.dataElbow(index)(1)-Data.dataHand(index)(1),2.0)+math.pow(Data.dataElbow(index)(2)-Data.dataHand(index)(2),2.0),0.5)
	// }
	
	//FK
	//順運動学により各関節角度と長さから関節角度を計算する
	//parameter: t1~t4:各関節角, l1,l2:腕の長さ
	//return: Unit
	// def FK(t1:Double,t2:Double,t3:Double,t4:Double,l1:Double,l2:Double) = {	
	// 	var Td0 = TransMat(0.0, 0.0, -math.Pi/2.0, 0.0)
	// 	var T01 = TransMat(0.0, 0.0, -math.Pi/2.0, t1)
	// 	var T12 = TransMat(0.0, 0.0, math.Pi/2.0, t2)
	// 	var T23 = TransMat(0.0, -l1, math.Pi/2.0, t3)
	// 	var T34 = TransMat(l2, 0.0, 0.0, t4)
		
	// 	var T02 = T01.times(T12)
	// 	var T03 = T01.times(T12.times(T23))//Elbow
	// 	var T04 = T01.times(T12.times(T23.times(T34)))//Hand
		
	// 	posVectorElbow = T03.times(collumVector)	
	// 	posVectorHand = T04.times(collumVector)
	// }
	
	//IK: 逆運動学によりDataの実測座標値から各関節角度を計算する
	//parameter: Data.data index
	//return: Unit
	// def IK(index:Int) = {	
	// 	posVectorElbow = new Jama.Matrix(Array(
	// 		Array(Data.dataElbow(index)(0)),
	// 		Array(-Data.dataElbow(index)(2)),
	// 		Array(Data.dataElbow(index)(1)),
	// 		Array(1.0)
	// 	),4,1)
		
	// 	posVectorHand = new Jama.Matrix(Array(
	// 		Array(Data.dataHand(index)(0)),
	// 		Array(-Data.dataHand(index)(2)),
	// 		Array(Data.dataHand(index)(1)),
	// 		Array(1.0)
	// 	),4,1)
		
	// 	theta(1) = math.atan(posVectorElbow.get(1,0)/posVectorElbow.get(0,0))
		
	// 	var rXY0 = math.pow(math.pow(posVectorElbow.get(0,0),2.0)+math.pow(posVectorElbow.get(1,0),2.0),0.5)
	// 	theta(2) = math.atan2(rXY0,posVectorElbow.get(2,0))+math.Pi
		
	// 	T01 = TransMat(0.0, 0.0, -math.Pi/2.0, theta(1))
	// 	T12 = TransMat(0.0, 0.0, math.Pi/2.0, theta(2))
	// 	T02 = T01.times(T12)
	// 	var T20 = T02.inverse()
	// 	var posVectorHand2 = T20.times(posVectorHand)
	// 	theta(3) = math.atan(posVectorHand2.get(1,0)/posVectorHand2.get(0,0))
		
	// 	var rXY2 = math.pow(math.pow(posVectorHand2.get(0,0),2.0)+math.pow(posVectorHand2.get(1,0),2.0),0.5)
	// 	theta(4) = math.atan2(posVectorHand2.get(2,0)+getL1(index),rXY2)
	// }
	
	//任意時間でのPTP制御での関節角を返す
	// def PTPSolver(t:Double) = {
	// 	for( i <- 1 to 4) {
	// 		theta(i) = Data.PTPParameters(i).getAng(t)
	// 	}
	// }
	
}

class ArmDrawer(var armIns:arm.Arm, var ps: PApplet){
	def DrawAxis1 = {
		var vec = armIns.T01.times(new Jama.Matrix(Array(
			Array(0.1),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(0, 100, 100)
		ps.line(0,0,0,vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T01.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.1),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(100/3, 100, 100)
		ps.line(0,0,0,vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
		vec = armIns.T01.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.1),
			Array(1.0)
		),4,1))
		ps.stroke(100/3*2, 100, 100)
		ps.line(0,0,0,vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	}
	
	def DrawAxis2 = {
		var vec0 = armIns.T02.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		
		var vec = armIns.T02.times(new Jama.Matrix(Array(
			Array(0.1),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(0, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T02.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.1),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(100/3, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T02.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.1),
			Array(1.0)
		),4,1))
		ps.stroke(100/3*2, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	}
	
	def DrawAxis3 = {
		var vec0 = armIns.T03.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		
		var vec = armIns.T03.times(new Jama.Matrix(Array(
			Array(0.1),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(0, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T03.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.1),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(100/3, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T03.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.1),
			Array(1.0)
		),4,1))
		ps.stroke(100/3*2, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	}
	
	def DrawAxis4 = {
		var vec0 = armIns.T04.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		
		var vec = armIns.T04.times(new Jama.Matrix(Array(
			Array(0.1),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(0, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T04.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.1),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.stroke(100/3, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	
		vec = armIns.T04.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.1),
			Array(1.0)
		),4,1))
		ps.stroke(100/3*2, 100, 100)
		ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
		vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
	}
	
	def DrawV = {

		ps.strokeWeight(5)
		ps.stroke(0, 0, 0)
		ps.line(0,0,0,
		armIns.P0Ve.get(0,0).toFloat*0.42f,
		armIns.P0Ve.get(1,0).toFloat*0.42f,
		armIns.P0Ve.get(2,0).toFloat*0.42f)
	
	
		var vec = armIns.T0v.times(new Jama.Matrix(Array(
			Array(0.0),
			Array(0.1),
			Array(0.0),
			Array(1.0)
		),4,1))
		ps.strokeWeight(10)
		ps.stroke(50, 50, 50)
		ps.line(0,0,0,
		vec.get(0,0).toFloat*4f,
		vec.get(1,0).toFloat*4f,
		vec.get(2,0).toFloat*4f)
	}
	
	def DrawAxis() = {
		DrawAxis1
		DrawAxis2
		DrawAxis3
		DrawAxis4
		DrawV
	}
	
	def DrawArm() = {
		//Arm(計算結果)
		
		// PTPSolver(i.toDouble/60.0)
		// armIns.FK(armIns.theta(1),armIns.theta(2),armIns.theta(3),armIns.theta(4),armIns.l(1),armIns.l(2))

		ps.stroke(0, 0, 0)	  
		ps.strokeWeight(10)
		ps.point(armIns.P0cElbow.get(0,0).toFloat,armIns.P0cElbow.get(1,0).toFloat,armIns.P0cElbow.get(2,0).toFloat)
		ps.strokeWeight(15)
		ps.point(armIns.P0cHand.get(0,0).toFloat,armIns.P0cHand.get(1,0).toFloat,armIns.P0cHand.get(2,0).toFloat)
		
		
		ps.strokeWeight(1)	
		ps.line(0,0,0,armIns.P0cElbow.get(0,0).toFloat,armIns.P0cElbow.get(1,0).toFloat,armIns.P0cElbow.get(2,0).toFloat)
		ps.line(armIns.P0cElbow.get(0,0).toFloat,armIns.P0cElbow.get(1,0).toFloat,armIns.P0cElbow.get(2,0).toFloat,
		armIns.P0cHand.get(0,0).toFloat,armIns.P0cHand.get(1,0).toFloat,armIns.P0cHand.get(2,0).toFloat)
		
		
		ps.stroke(0, 0, 100)	  
		ps.strokeWeight(6)
		ps.point(armIns.P0cElbow.get(0,0).toFloat,armIns.P0cElbow.get(1,0).toFloat,armIns.P0cElbow.get(2,0).toFloat)
		ps.strokeWeight(11)
		ps.point(armIns.P0cHand.get(0,0).toFloat,armIns.P0cHand.get(1,0).toFloat,armIns.P0cHand.get(2,0).toFloat)
	}
}
