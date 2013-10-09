import tanitta.matrix._
import Jama.Matrix


object Main{
	def set4x4(in: Array[Double]): Array[Array[Double]] = {
		var a = Array.ofDim[Double](4, 4)
		for(i <- 0 to a.size-1; j <- 0 to a(0).size-1){
			a(i)(j) = in((4*i)+j)
		}
		return a
	}
	
	// var a = Array.ofDim[Double](4, 4)
	// var a = set4x4(Array(
	// 	1.,	2.,	3.,	4.,
	// 	5.,	6.,	7.,	8.,
	// 	9.,	10.,	11.,	12.,
	// 	13.,	14.,	15.,	16.	
	// )) 
	var a = new Matrix(Array(
		Array(1.0,2.0,3.0),
		Array(4.0,5.0,6.0),
		Array(7.0,8.0,9.0)
	),3,3)
	
	var b: Matrix = Matrix.random(3,1)
	// var c: Matrix = a.solve(b)
	
	def main(args: Array[String]): Unit = {

	}
}