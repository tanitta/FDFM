package trit.fdfm

import processing.core._
import Jama.Matrix


object SolverField{
	var L1:Double = 0.5
	var L2:Double = 0.5
	var counter:Int = 0

	var dataElbow = Array(
		Array(0.0425,-0.0386,-0.3498),
		Array(0.0434,-0.0346,-0.3499),
		Array(0.0468,-0.0393,-0.3491),
		Array(0.0477,-0.0353,-0.3477),
		Array(0.0519,-0.0359,-0.3462),
		Array(0.0574,-0.0409,-0.3454),
		Array(0.0616,-0.0415,-0.3439),
		Array(0.0655,-0.0337,-0.3411),
		Array(0.0719,-0.0346,-0.3404),
		Array(0.0790,-0.0310,-0.3400),
		Array(0.0854,-0.0322,-0.3377),
		Array(0.0948,-0.0292,-0.3341),
		Array(0.1055,-0.0311,-0.3304),
		Array(0.1149,-0.0283,-0.3260),
		Array(0.1252,-0.0213,-0.3202),
		Array(0.1378,-0.0147,-0.3144),
		Array(0.1525,-0.0086,-0.3071),
		Array(0.1643,-0.0067,-0.2987),
		Array(0.1769,-0.0001,-0.2921),
		Array(0.1899,-0.0029,-0.2828),
		Array(0.2036,0.0077,-0.2716),
		Array(0.2143,0.0140,-0.2617),
		Array(0.2271,0.0200,-0.2510),
		Array(0.2369,0.0222,-0.2410),
		Array(0.2467,0.0240,-0.2301),
		Array(0.2575,0.0304,-0.2185),
		Array(0.2661,0.0460,-0.2061),
		Array(0.2728,0.0439,-0.1958),
		Array(0.2805,0.0552,-0.1848),
		Array(0.2850,0.0624,-0.1730),
		Array(0.2894,0.0700,-0.1642),
		Array(0.2950,0.0726,-0.1532),
		Array(0.2986,0.0754,-0.1427),
		Array(0.3009,0.0830,-0.1322),
		Array(0.3044,0.0952,-0.1225),
		Array(0.3068,0.0938,-0.1129),
		Array(0.3080,0.1065,-0.1038),
		Array(0.3094,0.1006,-0.0950),
		Array(0.3095,0.1090,-0.0876),
		Array(0.3107,0.1222,-0.0816),
		Array(0.3108,0.1217,-0.0776),
		Array(0.3110,0.1303,-0.0717),
		Array(0.3100,0.1347,-0.0683),
		Array(0.3101,0.1343,-0.0658),
		Array(0.3090,0.1390,-0.0641),
		Array(0.3080,0.1435,-0.0614),
		Array(0.3113,0.1382,-0.0600)
	)

	var dataHand = Array(
		Array(0.2375, 0.0706, -0.1573),
		Array(0.2396, 0.0705, -0.1574),
		Array(0.2407, 0.0747, -0.1566),
		Array(0.2429, 0.0653, -0.1552),
		Array(0.2440, 0.0694, -0.1527),
		Array(0.2451, 0.0736, -0.1503),
		Array(0.2461, 0.0779, -0.1487),
		Array(0.2483, 0.0862, -0.1438),
		Array(0.2526, 0.0853, -0.1415),
		Array(0.2549, 0.0844, -0.1360),
		Array(0.2572, 0.0836, -0.1312),
		Array(0.2616, 0.0915, -0.1247),
		Array(0.2659, 0.0994, -0.1183),
		Array(0.2703, 0.1073, -0.1110),
		Array(0.2748, 0.1060, -0.1038),
		Array(0.2803, 0.1183, -0.0964),
		Array(0.2859, 0.1212, -0.0876),
		Array(0.2904, 0.1289, -0.0777),
		Array(0.2960, 0.1410, -0.0669),
		Array(0.3038, 0.1435, -0.0565),
		Array(0.3094, 0.1558, -0.0464),
		Array(0.3184, 0.1600, -0.0329),
		Array(0.3252, 0.1604, -0.0213),
		Array(0.3298, 0.1871, -0.0104),
		Array(0.3388, 0.1943, 0.0004),
		Array(0.3478, 0.2112, 0.0109),
		Array(0.3535, 0.2145, 0.0204),
		Array(0.3615, 0.2167, 0.0315),
		Array(0.3684, 0.2343, 0.0406),
		Array(0.3753, 0.2520, 0.0490),
		Array(0.3821, 0.2600, 0.0568),
		Array(0.3900, 0.2632, 0.0635),
		Array(0.3968, 0.2716, 0.0680),
		Array(0.4028, 0.2950, 0.0743),
		Array(0.4119, 0.3031, 0.0797),
		Array(0.4211, 0.3215, 0.0840),
		Array(0.4268, 0.3254, 0.0875),
		Array(0.4347, 0.3290, 0.0901),
		Array(0.4406, 0.3431, 0.0934),
		Array(0.4463, 0.3473, 0.0944),
		Array(0.4520, 0.3514, 0.0962),
		Array(0.4533, 0.3562, 0.0974),
		Array(0.4557, 0.3659, 0.0979),
		Array(0.4590, 0.3605, 0.0975),
		Array(0.4602, 0.3653, 0.0986),
		Array(0.4614, 0.3702, 0.0989),
		Array(0.4637, 0.3697, 0.0997)
	)

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
		
		
		def setup = {}
		
		def getL1(index:Int):Double = {
			math.pow(math.pow(dataElbow(index)(0),2.0)+math.pow(dataElbow(index)(1),2.0)+math.pow(dataElbow(index)(2),2.0),0.5)
		}
		
		def getL2(index:Int):Double = {
			math.pow(math.pow(dataElbow(index)(0)-dataHand(index)(0),2.0)+math.pow(dataElbow(index)(1)-dataHand(index)(1),2.0)+math.pow(dataElbow(index)(2)-dataHand(index)(2),2.0),0.5)
		}
		
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
		
		def IK4(index:Int) = {
			// var T0d = Td0.inverse()
			
			posVectorElbow = new Jama.Matrix(Array(
				Array(dataElbow(index)(0)),
				Array(-dataElbow(index)(2)),
				Array(dataElbow(index)(1)),
				Array(1.0)
			),4,1)
			
			posVectorHand = new Jama.Matrix(Array(
				Array(dataHand(index)(0)),
				Array(-dataHand(index)(2)),
				Array(dataHand(index)(1)),
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
			theta(4) = -math.atan2(-posVectorHand2.get(2,0)-getL2(index),rXY2)
		}
		
		def update = {
			
			if(ps.keyPressed){
				if(ps.key == 'a' && counter < dataElbow.length-1){
					counter += 1 
				}else if(ps.key == 'z' && counter > 0){
					counter -= 1 
				}
			}
			println(ps.key)
			IK4(counter)
			solve(theta(1),theta(2),theta(3),theta(4),getL1(counter),getL2(counter))
			
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
			for(i <- 0 to dataElbow.length-1){
				ps.stroke(i.toFloat/dataElbow.length.toFloat*100f, 100, 100, 32)
				ps.point(dataElbow(i)(0).toFloat, -dataElbow(i)(2).toFloat, dataElbow(i)(1).toFloat)
				ps.point(dataHand(i)(0).toFloat, -dataHand(i)(2).toFloat, dataHand(i)(1).toFloat)
				
			}
			ps.strokeWeight(2)	  
			ps.stroke(0, 0, 0)
			ps.line(0,0,0,posVectorElbow.get(0,0).toFloat,posVectorElbow.get(1,0).toFloat,posVectorElbow.get(2,0).toFloat)
			ps.line(posVectorElbow.get(0,0).toFloat,posVectorElbow.get(1,0).toFloat,posVectorElbow.get(2,0).toFloat,
			posVectorHand.get(0,0).toFloat,posVectorHand.get(1,0).toFloat,posVectorHand.get(2,0).toFloat)
			
			drawAxis1
			drawAxis2
			drawAxis3
			drawAxis4
			
			ps.strokeWeight(8)
			ps.stroke(0, 0, 0)	  
			ps.point(posVectorElbow.get(0,0).toFloat,posVectorElbow.get(1,0).toFloat,posVectorElbow.get(2,0).toFloat)
			ps.point(posVectorHand.get(0,0).toFloat,posVectorHand.get(1,0).toFloat,posVectorHand.get(2,0).toFloat)
			
			
		}
	}
}