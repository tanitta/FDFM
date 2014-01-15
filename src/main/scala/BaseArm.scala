package trit.fdfm.arms


class BaseArm(var degreeOfFreedom : Int){
	var angle = new Array[Double](degreeOfFreedom)
	def FK(){}
	def IK(){}
}

class Arm1(var degreeOfFreedom : Int) extends BaseArm {
	var angle = new Array[Double](degreeOfFreedom)
	
	var L1:Double
	var L2:Double 
	
	var Td0 = new Jama.Matrix(4,4)
	var T01 = new Jama.Matrix(4,4)
	var T12 = new Jama.Matrix(4,4)
	var T23 = new Jama.Matrix(4,4)
	var T34 = new Jama.Matrix(4,4)
	
	var T02 = new Jama.Matrix(4,4)
	var T03 = new Jama.Matrix(4,4)
	var T04 = new Jama.Matrix(4,4)
	
	override def FK(theta1:Double, theta2:Double, theta3:Double, theta4:Double){
		
	}
	
	override def IK(Xe:Double,Ye:Double,Ze:Double,Xh:Double,Yh:Double,Zh:Double){
		
	}

class Arm(){
	var angle = new Array[Double](4)
	var pos = new Array[Double](2)
	var l = new Array[Double](2)
	
	def setParson(p1 : Arm, p2 : Arm) = {
		// p1.angle(1) = 0
		// angle(1) = 0
	}
	
	def setup() = {}
	def update() = {}
	def draw() = {}
}