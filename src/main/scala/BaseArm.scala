package trit.fdfm.arms

class BaseArm(var degreeOfFreedom : Int){
	var angle = new Array[Double](degreeOfFreedom)
	def FK(){}
	def IK(){}
}

class Arm1(var degreeOfFreedom : Int) extends BaseArm {
	var angle = new Array[Double](degreeOfFreedom)
	override def FK(theta1:Double, theta2:Double, theta3:Double, theta4:Double){
		
	}
	
	override def IK(Xe:Double,Ye:Double,Ze:Double,Xh:Double,Yh:Double,Zh:Double){
		
	}
}