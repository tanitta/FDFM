package trit.fdfm.calculation

import processing.core._
import Jama.Matrix
import trit.fdfm._

import fr.inria.optimization.cmaes.CMAEvolutionStrategy;
import fr.inria.optimization.cmaes.fitness.IObjectiveFunction;

class EvalByDirection(var ps: PApplet) extends IObjectiveFunction{
	
	var armObj = new arm.Arm(ps)
	
	override def valueOf(x:Array[Double]):Double = {
		// var res:Double = 0;
		// var a:Double = x(0)-10
		// var b:Double = x(1)-6
		// res = a*a + b*b + 2
		// println(x(1)+"\t"+x(0)+"\t"+res);
		// res
		
		
		var res = armObj.Eval(x)
		armObj.SetGene(x)
		// println(x(0)+"\t"+x(1)+"\t"+x(2)+"\t"+x(3)+"\t"+res);
		res
	};
	override def isFeasible(x:Array[Double]):Boolean = {
		//計算可能ならtrue
		var flag = true
		
		for( i <- 2 until 4) {
			if(x(i) < 0.0){
				flag = false
			}
			if(x(i) > (47.0/60.0)){
				flag = false
			}
			
		}
		
		for( i <- 0 until 2) {
			var tD = x(i+2)
			if(x(i) < 0.0){
				flag = false
			}
			if(x(i) > (47.0/60.0-tD)*0.5){
				flag = false
			}
			
		}
		
		flag
	}
	
	def GetGene() = {
		armObj.gene
	}
}

class CMAES(var ps: PApplet) {
	var fitfun = new EvalByDirection(ps);
	var cma = new CMAEvolutionStrategy();
	cma.readProperties(); // read options, see file CMAEvolutionStrategy.properties
	cma.setDimension(4); // overwrite some loaded properties
	cma.setInitialX(0.05); // in each dimension, also setTypicalX can be used
	cma.setInitialStandardDeviation(0.5); // also a mandatory setting 
	cma.options.stopFitness = 1e-9;       // optional setting
	
	var fitness:Array[Double] = cma.init();  // new double[cma.parameters.getPopulationSize()];
	
	cma.writeToDefaultFilesHeaders(0); // 0 == overwrites old files
	
	
	
	def Solve() = {
		println("----------------CMAES Solve----------------")
		
		while(cma.stopConditions.getNumber() == 0){
			// var pop = cma.samplePopulation(); // get a new population of solutions
			var pop:Array[Array[Double]] = cma.samplePopulation(); // get a new population of solutions
			
			for( i <- 0 until pop.length) {
				while(!fitfun.isFeasible(pop(i))){//    test whether solution is feasible,  
					pop(i) = cma.resampleSingle(i);  //       re-sample solution until it is feasible  
				}
				fitness(i) = fitfun.valueOf(pop(i)); //    compute fitness value, where fitfun	
				// println(fitness(i))
			}
			cma.updateDistribution(fitness);         // pass fitness array to update search distribution
			// var outmod:Int = 150;
			// if (cma.getCountIter() % (15*outmod) == 1)
			// 	cma.printlnAnnotation(); // might write file as well
			// if (cma.getCountIter() % outmod == 1)
			// 	cma.println(); 
		}
		
		
		
	};
	def GetGene() = {
		fitfun.GetGene
	}
}