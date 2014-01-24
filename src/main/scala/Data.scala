package trit.fdfm
object Data{
	var at:Double = 0.05
	var unitTime:Double = 1.0/60.0
	var tf:Double = unitTime*47.0
	var stepMax:Int = (tf/unitTime).toInt
	println("stepMax" + stepMax)
	
	class RUDC(xs:Double, xf:Double, itf:Double){
		var tUPerTF = 0.001
		var tUnit = itf*tUPerTF //単位時間
		
		var tA:Double = 0.05 //加減速時間const
		var tF:Double = itf //動作時間
		
		var xStart:Double = xs //初期値
		var xFinish:Double = xf //終了値
		
		var defMax:Double = 0.0 //最大値
		
		def SetTUnit(itu:Double) = {
			tUnit = itu
		}
		
		def SetTA(it: Double) = {
			tA = it
		}
		
		def TotalDef():Double = {
			return xFinish - xStart 
		}
		
		def DefMax():Double = {
			return TotalDef/(tF-tA)	
		}
		
		def Def(t: Double):Double = {
			var d:Double = 0.0
			if(0 <= t && t < tA){
				d = DefMax/tA*t
			}
			if(tA <= t && t < tF-tA){
				d = DefMax
			}
			if(tF-tA <= t && t <= tF){
				d = -DefMax/tA*t + DefMax/tA*tF
			}
			return d
		}
			
		def CurrentVal(t: Double):Double = {
			var step:Double = t/tUnit
			var counter:Double = xStart //値のカウント
			for( i <- 0 to step.toInt) {
				counter += Def(i.toDouble*tUnit)*tUnit
			}
			return counter 
		}
		
		
	}
	
	class PTPParameter(var start:Double, var stop:Double, var tf:Double){
		var rudc = new Data.RUDC(start,stop,tf)
		
		def getAng(t:Double) = {
			rudc.CurrentVal(t)
		}
	} 
	var PTPParameters = new Array[PTPParameter](5)
	PTPParameters(1) = new PTPParameter(1.4498909296037423,		0.19040526232806004,	47.0/60.0)
	PTPParameters(2) = new PTPParameter(4.821497153141927,		4.301304120297498,		47.0/60.0)
	PTPParameters(3) = new PTPParameter(-1.1761145836049476,	-0.8457694650137504,	47.0/60.0)
	PTPParameters(4) = new PTPParameter(0.6498283709082613,		-0.6833951511814054,	47.0/60.0)

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
}