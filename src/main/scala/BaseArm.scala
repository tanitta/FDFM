package trit.fdfm.arm

import Jama.Matrix
import processing.core._
import trit.fdfm._

class Arm(ps: PApplet){
	var this.ps = ps
	var theta = new Array[Double](5)
	var l = new Array[Double](3)
	l(1) = 0.35;
	l(2) = 0.37;
	
	//単位列ベクトル
	var collumVector = new Jama.Matrix(Array(
			Array(0.0),
			Array(0.0),
			Array(0.0),
			Array(1.0)
	),4,1)
	// //手先位置列ベクトル	
	// var P0cHand = new Jama.Matrix(Array(
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(1.0)
	// ),4,1)
	// //肘位置列ベクトル
	// var P0cElbow = new Jama.Matrix(Array(
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(0.0),
	// 	Array(1.0)
	// ),4,1)
	
	var P0eElbow = new Jama.Matrix(4,1)
	var P0eHand = new Jama.Matrix(4,1)
	var PveElbow = new Jama.Matrix(4,1)
	var PveHand = new Jama.Matrix(4,1)

	var P0cElbow = new Jama.Matrix(4,1)
	var P0cHand = new Jama.Matrix(4,1)
	var PvcElbow = new Jama.Matrix(4,1)
	var PvcHand = new Jama.Matrix(4,1)
	
	var P0V = new Jama.Matrix(4,1)
	var P0Ve = new Jama.Matrix(4,1)

	
	def GetP0eElbow(i: Int) = {
		var posVector = new Jama.Matrix(Array(
			Array(Data.dataElbow(i)(0)),
			Array(-Data.dataElbow(i)(2)),
			Array(Data.dataElbow(i)(1)),
			Array(1.0)
		),4,1)
		posVector
	}
	
	def GetP0eHand(i: Int) = {
		var posVector = new Jama.Matrix(Array(
			Array(Data.dataHand(i)(0)),
			Array(-Data.dataHand(i)(2)),
			Array(Data.dataHand(i)(1)),
			Array(1.0)
		),4,1)
		posVector
	}
	
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
	
	var Tv0 = new Jama.Matrix(4,4)
	
	var T0v = new Jama.Matrix(4,4)
	
	//mov1
	// var gene = Array(0.05,0.05,0,0)
	// var gene = Array(0.4499100010092542,0.4499099975292252,0,0)
	// var gene = Array(0.4498200000117409,	0.4499099999620027,0,0)
	
	//mov2(突き出し)
	// Arm1
	// var gene = Array(0.05,0.05,0,0)
	// Arm2
	// var gene = Array(0.3916,0.2800,0,0)
	// Arm3
	// var gene = Array(0.05,0.05,0.03569666637974663,0.2618683295046492)
	// Arm4
	var gene = Array(0.2482,0.1653,0.0513,0.2260)
	
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
		
		vectorC;
	}
	
	def UnitVector(vectorA:Jama.Matrix):Jama.Matrix = {
		var abs:Double = math.pow(math.pow(vectorA.get(0,0),2.0) + math.pow(vectorA.get(1,0),2.0) + math.pow(vectorA.get(2,0),2.0), 0.5)
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
		t(1) = math.atan2(inPosVectorElbow.get(1,0),inPosVectorElbow.get(0,0))
		
		var rXY0 = math.pow(math.pow(inPosVectorElbow.get(0,0),2.0)+math.pow(inPosVectorElbow.get(1,0),2.0),0.5)
		t(2) = -math.atan2(rXY0,-inPosVectorElbow.get(2,0))//+math.Pi
		
		var T01 = TransMat(0.0, 0.0, -math.Pi/2.0, t(1))
		var T12 = TransMat(0.0, 0.0, math.Pi/2.0, t(2))
		var T02 = T01.times(T12)
		var T20 = T02.inverse()
		var posVectorHand2 = T20.times(inPosVectorHand)
		t(3) = math.atan(posVectorHand2.get(1,0)/posVectorHand2.get(0,0))
		
		var rXY2 = math.pow(math.pow(posVectorHand2.get(0,0),2.0)+math.pow(posVectorHand2.get(1,0),2.0),0.5)
		t(4) = math.atan2(posVectorHand2.get(2,0)+l(1),rXY2)
		
		t(1) = t(1)%(2.0*math.Pi)
		t(2) = t(2)%(2.0*math.Pi)
		t(3) = t(3)%(2.0*math.Pi)
		t(4) = t(4)%(2.0*math.Pi)
		
		t		
	}

	// var solver = new SolverField.Solver(ps)	

	var armDrawer = new calculation.ArmDrawer(this, ps);
	
	//ExpToCalc
	def ExpToCalc(i:Int)	= {
		P0eElbow = GetP0eElbow(i)
		P0eHand = GetP0eHand(i)
		
		P0Ve = UnitVector(VectorProduct(P0eHand,P0eElbow))
		var thetaVx = -Math.asin(P0Ve.get(2,0));
		var thetaVz = Math.atan2(P0Ve.get(0,0),P0Ve.get(1,0));

		Tv0 = TransMat(0, 0, thetaVx, 0).times(TransMat(0, 0, 0, thetaVz));
	
		PveElbow = Tv0.times(P0eElbow)
		PveHand = Tv0.times(P0eHand)
			
		PvcHand = PveHand
		
		var ld:Double = math.pow(math.pow(PveHand.get(2,0),2.0)+math.pow(PveHand.get(0,0),2.0),0.5)
		var tmpAngle1 = math.atan2(PvcHand.get(2,0),PvcHand.get(0,0))
		var tmpAngle2 = tmpAngle1 - math.acos((math.pow(l(1),2.0)-math.pow(l(2),2.0)+math.pow(ld,2.0))/(2.0*l(1)*ld))
		// println(" :" + math.atan2(1,0))
		PvcElbow = new Jama.Matrix(Array(
			Array(math.cos(tmpAngle2)*l(1)),
			Array(0.0),
			Array(math.sin(tmpAngle2)*l(1)),
			Array(1.0)
		),4,1)
		
		// PvcElbow = new Jama.Matrix(Array(
		// 	Array(0.0),
		// 	Array(0.0),
		// 	Array(1.0),
		// 	Array(1.0)
		// ),4,1)
		
		// println()
		// println("ld" + ld)
		// println("tmpAngle1" + tmpAngle1)
		// println("tmpAngle2" + tmpAngle2)
		// println("math.cos(tmpAngle2)" + math.cos(tmpAngle2))
		// println("math.sin(tmpAngle2)" + math.sin(tmpAngle2))
		// println("PvcElbow.x : " + PvcElbow.get(0,0))
		// println("PvcElbow.y : " + PvcElbow.get(1,0))
		// println("PvcElbow.z : " + PvcElbow.get(2,0))
		
		T0v = Tv0.inverse()
		
		P0cElbow = T0v.times(PvcElbow)
		P0cHand = T0v.times(PvcHand)
	}
	
	def SetGene(x:Array[Double]){
		gene = x
	}
	
	def Eval(x:Array[Double]):Double = {
		// println("----------------Eval----------------")
		//遺伝子データの上限下限の設定
		// for( i <- 0 until 3) {
		// 	if(x(i) < 0.0){
		// 		x(i) = 0.0
		// 	}
			
		// 	if(x(i) > 47.0/60.0*0.5){
		// 		x(i) = 47.0/60.0*0.5
		// 	}
		// }
		
		
		
		//開始，終了時刻での肘，手先ベクトルから関節角を計算しておく
		//start
		ExpToCalc(0)
		var P0cElbowStart = P0cElbow
		var P0cHandStart = P0cHand
		var thetaStart = IK(P0cElbowStart,P0cHandStart)
		
		//finish
		ExpToCalc(Data.stepMax-1)
		var P0cElbowFinish = P0cElbow
		var P0cHandFinish = P0cHand
		var thetaFinish = IK(P0cElbowFinish,P0cHandFinish)
		
		// P0cHand = FK(thetaFinish,l(1),l(2))._2	
		
		
		var rudc = new Array[Data.RUDC](4)
		
		//台形速度制御の初期化
		for( i <- 0 until 4) {
			rudc(i) = new Data.RUDC(thetaStart(i+1),thetaFinish(i+1),Data.stepMax/60.0)
		}
		
		rudc(0).SetTA(x(0))
		rudc(1).SetTA(x(0))
		rudc(2).SetTA(x(0))
		rudc(3).SetTA(x(1))
		
		rudc(0).SetTD(x(2))
		rudc(1).SetTD(x(2))
		rudc(2).SetTD(x(2))
		rudc(3).SetTD(x(3))
		
		
		
		//計測値と計算値の手先の距離
		var dx:Double = 0
		var dy:Double = 0
		var dz:Double = 0
		var d:Double = 0
		var d2:Double = 0
		for( c <- 0 until Data.stepMax) {
		// var c = 0
			var tmpTheta = new Array[Double](5)
			
			// 各関節の角度を設定
			for( i <- 0 until 4) {
				tmpTheta(i+1) = rudc(i).CurrentVal(c.toDouble*Data.unitTime)
			}
			
			// tmpTheta(0) = rudc(0).tA//CurrentVal(c.toDouble*Data.unitTime)

			
			
			P0cElbow = FK(tmpTheta, l(1), l(2))._1
			P0cHand = FK(tmpTheta, l(1), l(2))._2
			
			dx = math.abs(P0cHand.get(0,0) - GetP0eHand(c).get(0,0))
			dy = math.abs(P0cHand.get(1,0) - GetP0eHand(c).get(1,0))
			dz = math.abs(P0cHand.get(2,0) - GetP0eHand(c).get(2,0))
			d = math.pow(dx,2.0) + math.pow(dy,2.0) + math.pow(dz,2.0)
			println("s:\t" + math.pow(d,0.5))
			
			if(c%4 == 0 ){
			// if(c == 0 || c == Data.stepMax-1){
				armDrawer.DrawArmLine();
			}
			armDrawer.DrawArmPoint();
			
		}
		
		// math.pow(dx,2.0) + math.pow(dy,2.0) + math.pow(dz,2.0)
		// println(x(0)+"\t"+x(1)+"\t"+x(2)+"\t"+x(3)+"\t"+d);
		
		d
	}
	
	def setup() = {	
		// Eval(gene)
		// var testArray = Array(0.39,0.39,0.39,0.39)
		// // // println("Eval : " + Eval(testArray))
		// Eval(testArray)
		// println("Eval : " + Eval(testArray))
		// P0cElbow = new Jama.Matrix(Array(
		// 	Array(0.0),
		// 	Array(0.0),
		// 	Array(0.3),
		// 	Array(1.0)
		// ),4,1)
		
		// P0cHand = new Jama.Matrix(Array(
		// 	Array(0.0),
		// 	Array(0.32),
		// 	Array(0.3),
		// 	Array(1.0)
		// ),4,1)
		
		// var thetaStart = IK(P0cElbow,P0cHand)
		// println("theta1 : " + thetaStart(1))
		// println("theta2 : " + thetaStart(2))
		// println("theta3 : " + thetaStart(3))
		// println("theta4 : " + thetaStart(4))
		// P0cElbow = FK(thetaStart,l(1),l(2))._1
		// P0cHand = FK(thetaStart,l(1),l(2))._2
		
			
		// println("P0cElbow x: " + P0cElbow.get(0,0));
		// println("P0cElbow y: " + P0cElbow.get(1,0));
		// println("P0cElbow z: " + P0cElbow.get(2,0));
	
		
		// P0cElbow = FK(Array(0.0, +0.4,-0.4,-0.2,0),l(1),l(2))._1;
		// P0cHand = FK(Array(0.0,  +0.4,-0.4,-0.2,0),l(1),l(2))._2;
		
		// P0V = UnitVector(VectorProduct(P0cHand,P0cElbow))
		
		
		// // var thetaVx = Math.atan2(UnitVector(VectorProduct(P0cHand,P0cElbow)).get(2,0),UnitVector(VectorProduct(P0cHand,P0cElbow)).get(1,0));
		// // var thetaVz = Math.asin(UnitVector(VectorProduct(P0cHand,P0cElbow)).get(0,0));
		
		// var thetaVx = Math.asin(P0V.get(2,0));
		// var thetaVz = -Math.atan2(P0V.get(0,0),P0V.get(1,0));
				
		// if(P0V.get(1,0) < 0){
		// 	// thetaVx = thetaVx+math.Pi;
			
		// 	if(P0V.get(2,0) < 0){
		// 		// thetaVx = thetaVx+math.Pi;
		// 	}
		// };
		 
		// // Tv0 = TransMat(0, 0, 0, thetaVz).times(TransMat(0, 0, thetaVx, 0));
		// Tv0 = TransMat(0, 0, thetaVx, thetaVz)
		
		// // Tv0 = TransMat(0, 0, thetaVx, 0).times(TransMat(0, 0, 0, thetaVz));
		
		// println("z : " + thetaVz);
		// println("x : " + thetaVx);
		
		// println("Vx : "+P0V.get(0,0))
		// println("Vy : "+P0V.get(1,0))
		// println("Vz : "+P0V.get(2,0))
		
		// PvcElbow = Tv0.times(P0cElbow)
		// PvcHand = Tv0.times(P0cHand)
		
		// println("P0cElbow x: " + P0cElbow.get(0,0));
		// println("P0cElbow y: " + P0cElbow.get(1,0));
		// println("P0cElbow z: " + P0cElbow.get(2,0));
		// println("P0cHand x: " + P0cHand.get(0,0));
		// println("P0cHand y: " + P0cHand.get(1,0));
		// println("P0cHand z: " + P0cHand.get(2,0));
		
		// println("PvcHand x: " + PvcHand.get(0,0));
		// println("PvcHand y: " + PvcHand.get(1,0));
		// println("PvcHand z: " + PvcHand.get(2,0));
		// println("--------setup--------")		
		// Eval(gene)
		
	}
	def update() = {		
	}
	def draw() = {
		Eval(gene)
		// ExpToCalc(10)
		// armDrawer.DrawArm();
		
		
		// for( i <- 0 to 46){
		// 	ExpToCalc(i)
			
		// 	var testArray = Array(0.05,0.05,0.05,0.05)
		// 	// println("Eval : " + Eval(testArray))
		// 	Eval(testArray)
			
			
		// 	var P0cElbowStart = P0cElbow
		// 	var P0cHandStart = P0cHand
		// 	var thetaStart = IK(P0cElbowStart,P0cHandStart)
			
		// 	P0cElbow = FK(thetaStart,l(1),l(2))._1
		// 	P0cHand = FK(thetaStart,l(1),l(2))._2	
			
			
		// 	// sarmDrawer.DrawAxis();	
		// }
	}
}

