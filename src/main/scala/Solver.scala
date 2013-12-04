package trit.fdfm

import processing.core._
import Jama.Matrix
import trit.fdfm._


object SolverField{
	var L1:Double = 0.29
	var L2:Double = 0.3
	var counter:Int = 0
	var time:Double = 0.0
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
		
		var T02 = new Jama.Matrix(4,4)
		var T03 = new Jama.Matrix(4,4)
		var T04 = new Jama.Matrix(4,4)
		
		var collumVector = new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1)
		
		var posVectorHand = new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1)
		
		var posVectorElbow = new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
		),4,1)
		
		def getL1(index:Int):Double = {
			math.pow(math.pow(Data.dataElbow(index)(0),2.0)+math.pow(Data.dataElbow(index)(1),2.0)+math.pow(Data.dataElbow(index)(2),2.0),0.5)
		}
		
		def getL2(index:Int):Double = {
			math.pow(math.pow(Data.dataElbow(index)(0)-Data.dataHand(index)(0),2.0)+math.pow(Data.dataElbow(index)(1)-Data.dataHand(index)(1),2.0)+math.pow(Data.dataElbow(index)(2)-Data.dataHand(index)(2),2.0),0.5)
		}
		
		//solve
		//順運動学により各関節角度と長さから関節角度を計算する
		//parameter: t1~t4:各関節角, l1,l2:腕の長さ
		//return: Unit
		def solve(t1:Double,t2:Double,t3:Double,t4:Double,l1:Double,l2:Double) = {
			
			
			
			Td0 = TransMat(0.0, 0.0, -math.Pi/2.0, 0.0)
			T01 = TransMat(0.0, 0.0, -math.Pi/2.0, t1)
			T12 = TransMat(0.0, 0.0, math.Pi/2.0, t2)
			T23 = TransMat(0.0, -l1, math.Pi/2.0, t3)
			T34 = TransMat(l2, 0.0, 0.0, t4)
			
			T02 = T01.times(T12)
			T03 = T01.times(T12.times(T23))//Elbow
			T04 = T01.times(T12.times(T23.times(T34)))//Hand
			
			posVectorElbow = T03.times(collumVector)	
			posVectorHand = T04.times(collumVector)
		}
		
		//IK4: 逆運動学によりDataの実測座標値から各関節角度を計算する
		//parameter: Data.data index
		//return: Unit
		def IK4(index:Int) = {
			// var T0d = Td0.inverse()
			
			posVectorElbow = new Jama.Matrix(Array(
				Array(Data.dataElbow(index)(0)),
				Array(-Data.dataElbow(index)(2)),
				Array(Data.dataElbow(index)(1)),
				Array(1.0)
			),4,1)
			
			posVectorHand = new Jama.Matrix(Array(
				Array(Data.dataHand(index)(0)),
				Array(-Data.dataHand(index)(2)),
				Array(Data.dataHand(index)(1)),
				Array(1.0)
			),4,1)
			
			theta(1) = math.atan(posVectorElbow.get(1,0)/posVectorElbow.get(0,0))
			
			var rXY0 = math.pow(math.pow(posVectorElbow.get(0,0),2.0)+math.pow(posVectorElbow.get(1,0),2.0),0.5)
			theta(2) = math.atan2(rXY0,posVectorElbow.get(2,0))+math.Pi
			
			T01 = TransMat(0.0, 0.0, -math.Pi/2.0, theta(1))
			T12 = TransMat(0.0, 0.0, math.Pi/2.0, theta(2))
			T02 = T01.times(T12)
			var T20 = T02.inverse()
			var posVectorHand2 = T20.times(posVectorHand)
			theta(3) = math.atan(posVectorHand2.get(1,0)/posVectorHand2.get(0,0))
			
			var rXY2 = math.pow(math.pow(posVectorHand2.get(0,0),2.0)+math.pow(posVectorHand2.get(1,0),2.0),0.5)
			theta(4) = math.atan2(posVectorHand2.get(2,0)+getL1(index),rXY2)
		}
		
		def PTPSolver(t:Double) = {
			for( i <- 1 to 4) {
				theta(i) = -Data.PTPParameters(i).getAng(t)
			}
		}
		
		def setup = {
			// IK4(Data.dataElbow.length-1)
			// // PTPSolver(10.0)
			// solve(theta(1),theta(2),theta(3),theta(4),L1,L2)
			// println("theta1:" + theta(1))
			// println("theta2:" + theta(2))
			// println("theta3:" + theta(3))
			// println("theta4:" + theta(4))
		}
		
		def update = {
			
			if(ps.keyPressed){
				if(ps.key == 'a' && counter < Data.stepMax-1){
					counter += 1 
				}else if(ps.key == 'z' && counter > 0){
					counter -= 1 
				}
			}
			// IK4(counter)
			PTPSolver(counter.toDouble*Data.unitTime)
			solve(theta(1),theta(2),theta(3),theta(4),L1,L2)
			
		}
				
		def drawAxis1 = {
			var vec = T01.times(new Jama.Matrix(Array(
				Array(0.1),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(0, 100, 100)
			ps.line(0,0,0,vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T01.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.1),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(100/3, 100, 100)
			ps.line(0,0,0,vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
			
			vec = T01.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.1),
				Array(1.0)
			),4,1))
			ps.stroke(100/3*2, 100, 100)
			ps.line(0,0,0,vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		}
		
		def drawAxis2 = {
			var vec0 = T02.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			
			var vec = T02.times(new Jama.Matrix(Array(
				Array(0.1),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(0, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T02.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.1),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(100/3, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T02.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.1),
				Array(1.0)
			),4,1))
			ps.stroke(100/3*2, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		}
		
		def drawAxis3 = {
			var vec0 = T03.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			
			var vec = T03.times(new Jama.Matrix(Array(
				Array(0.1),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(0, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T03.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.1),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(100/3, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T03.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.1),
				Array(1.0)
			),4,1))
			ps.stroke(100/3*2, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		}
		
		def drawAxis4 = {
			var vec0 = T04.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			
			var vec = T04.times(new Jama.Matrix(Array(
				Array(0.1),
				Array(0.0),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(0, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T04.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.1),
				Array(0.0),
				Array(1.0)
			),4,1))
			ps.stroke(100/3, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		
			vec = T04.times(new Jama.Matrix(Array(
				Array(0.0),
				Array(0.0),
				Array(0.1),
				Array(1.0)
			),4,1))
			ps.stroke(100/3*2, 100, 100)
			ps.line(vec0.get(0,0).toFloat,vec0.get(1,0).toFloat,vec0.get(2,0).toFloat,
			vec.get(0,0).toFloat,vec.get(1,0).toFloat,vec.get(2,0).toFloat)
		}
		
		def draw = {
			ps.strokeWeight(4)	 
			// for(i <- 0 to Data.dataElbow.length-1){
			// 	ps.stroke(i.toFloat/Data.dataElbow.length.toFloat*100f, 100, 100)
			// 	ps.point(Data.dataElbow(i)(0).toFloat, -Data.dataElbow(i)(2).toFloat, Data.dataElbow(i)(1).toFloat)
			// 	ps.point(Data.dataHand(i)(0).toFloat, -Data.dataHand(i)(2).toFloat, Data.dataHand(i)(1).toFloat)
				
			// }
			ps.strokeWeight(2)	  
			ps.stroke(0, 0, 100)
			ps.line(0,0,0,posVectorElbow.get(0,0).toFloat,posVectorElbow.get(1,0).toFloat,posVectorElbow.get(2,0).toFloat)
			ps.line(posVectorElbow.get(0,0).toFloat,posVectorElbow.get(1,0).toFloat,posVectorElbow.get(2,0).toFloat,
			posVectorHand.get(0,0).toFloat,posVectorHand.get(1,0).toFloat,posVectorHand.get(2,0).toFloat)
			
			drawAxis1
			drawAxis2
			drawAxis3
			drawAxis4
			
			ps.strokeWeight(8)
			ps.stroke(0, 0, 100)	  
			ps.point(posVectorElbow.get(0,0).toFloat,posVectorElbow.get(1,0).toFloat,posVectorElbow.get(2,0).toFloat)
			ps.point(posVectorHand.get(0,0).toFloat,posVectorHand.get(1,0).toFloat,posVectorHand.get(2,0).toFloat)
			
			
		}
	}
}