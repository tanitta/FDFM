package trit.fdfm
object Data{
	var at:Double = 0.05
	var unitTime:Double = 1.0/60.0
	var tf:Double = unitTime*47.0//54.0
	var stepMax:Int = (tf/unitTime).toInt
	
	var vals:Int = 4
	
	
	// //線形補間速度制御
	// class LIC(xs:Double, xf:Double, itf:Double){
	// 	class Point(){
	// 		var xRaw = 0.0
	// 		var yRaw = 0.0
			
	// 		var xNormal = 0.0
	// 		var yNormal = 0.0
			
	// 		var a = 0;
	// 		var b = 0;
	// 	}
		
	// 	var tUPerTF = 0.0001
	// 	var tUnit = itf*tUPerTF //単位時間
		
	// 	var tp = new Array[Point](Data.vals+1)
		
	// 	var tF:Double = itf //動作時間
		
	// 	var xStart:Double = xs //初期値
	// 	var xFinish:Double = xf //終了値
		
	// 	var defMax:Double = 0.0 //最大値
		
	// 	def SetGene(p:Array[Double])={
	// 		// 最初の点
	// 		tp(0).xRaw = 0.0
	// 		for( i <- 0 until Data.vals) {
	// 			tp(i+1).xRaw = p(i)
	// 		}
	// 		// 最後の点
	// 		tp(Data.vals+1).xRaw = 0.0
	// 	}
		
	// 	def SetTUnit(itu:Double) = {
	// 		tUnit = itu
	// 	}
		
	// 	def TotalDef():Double = {
	// 		return xFinish - xStart 
	// 	}
		
	// 	def DefMax():Double = {
	// 		return TotalDef/tf
	// 	}
		
	// 	def Def(t: Double):Double = {
	// 		var d:Double = 0.0
			
	// 		d = DefMax
			
	// 		return d
	// 	}
			
	// 	def CurrentVal(t: Double):Double = {
	// 		var step:Double = t/tUnit
	// 		var counter:Double = xStart //値のカウント
	// 		for( i <- 0 to step.toInt) {
	// 			counter += Def(i.toDouble*tUnit)*tUnit
	// 		}
	// 		return counter 
	// 	}
		
		
	// }
	
	
	//台形速度制御
	class RUDC(xs:Double, xf:Double, itf:Double){
		var tUPerTF = 0.0001
		var tUnit = itf*tUPerTF //単位時間
		
		var tA:Double = 0.05 //加減速時間const
		var tD:Double = 0.0
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
		
		def SetTD(it: Double) = {
			tD = it
		}
		def TotalDef():Double = {
			return xFinish - xStart 
		}
		
		def DefMax():Double = {
			return TotalDef/(tF-tA-tD)	
		}
		
		def Def(t: Double):Double = {
			var d:Double = 0.0
			if(0+tD <= t && t < tA+tD){
				d = DefMax/tA*(t-tD)
			}
			if(tA+tD <= t && t < tF-tA){
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
	
	//台形制御のラッパ
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

	var dataElbow2 = Array(
		Array(0.0588 ,-0.0974 ,-0.3379 ),
		Array(0.0588 ,-0.0972 ,-0.3371 ),
		Array(0.0600 ,-0.1021 ,-0.3365 ),
		Array(0.0597 ,-0.0919 ,-0.3377 ),
		Array(0.0621 ,-0.1019 ,-0.3365 ),
		Array(0.0630 ,-0.0972 ,-0.3370 ),
		Array(0.0672 ,-0.0966 ,-0.3369 ),
		Array(0.0680 ,-0.0912 ,-0.3350 ),
		Array(0.0743 ,-0.0906 ,-0.3340 ),
		Array(0.0784 ,-0.0904 ,-0.3322 ),
		Array(0.0847 ,-0.0896 ,-0.3305 ),
		Array(0.0934 ,-0.0992 ,-0.3282 ),
		Array(0.0993 ,-0.0883 ,-0.3259 ),
		Array(0.1073 ,-0.0774 ,-0.3243 ),
		Array(0.1162 ,-0.0966 ,-0.3169 ),
		Array(0.1266 ,-0.0957 ,-0.3142 ),
		Array(0.1356 ,-0.0795 ,-0.3106 ),
		Array(0.1472 ,-0.0834 ,-0.3056 ),
		Array(0.1573 ,-0.0720 ,-0.2989 ),
		Array(0.1677 ,-0.0707 ,-0.2929 ),
		Array(0.1791 ,-0.0644 ,-0.2856 ),
		Array(0.1904 ,-0.0579 ,-0.2766 ),
		Array(0.1997 ,-0.0513 ,-0.2685 ),
		Array(0.2111 ,-0.0547 ,-0.2577 ),
		Array(0.2235 ,-0.0430 ,-0.2483 ),
		Array(0.2339 ,-0.0413 ,-0.2363 ),
		Array(0.2453 ,-0.0244 ,-0.2247 ),
		Array(0.2546 ,-0.0177 ,-0.2130 ),
		Array(0.2628 ,-0.0161 ,-0.1985 ),
		Array(0.2689 ,-0.0043 ,-0.1873 ),
		Array(0.2741 ,0.0019 ,-0.1766 ),
		Array(0.2791 ,0.0087 ,-0.1633 ),
		Array(0.2853 ,0.0098 ,-0.1529 ),
		Array(0.2894 ,0.0214 ,-0.1407 ),
		Array(0.2936 ,0.0325 ,-0.1327 ),
		Array(0.2945 ,0.0388 ,-0.1237 ),
		Array(0.2976 ,0.0448 ,-0.1154 ),
		Array(0.2976 ,0.0558 ,-0.1100 ),
		Array(0.2997 ,0.0667 ,-0.1054 ),
		Array(0.2995 ,0.0569 ,-0.0989 ),
		Array(0.2985 ,0.0726 ,-0.0963 ),
		Array(0.2985 ,0.0836 ,-0.0926 ),
		Array(0.3007 ,0.0838 ,-0.0917 ),
		Array(0.2984 ,0.0839 ,-0.0883 ),
		Array(0.2995 ,0.0894 ,-0.0860 ),
		Array(0.2984 ,0.0948 ,-0.0846 ),
		Array(0.2983 ,0.0949 ,-0.0819 ),
		Array(0.2994 ,0.0898 ,-0.0808 ),
		Array(0.2972 ,0.1005 ,-0.0779 ),
		Array(0.2972 ,0.1005 ,-0.0779 ),
		Array(0.2950 ,0.1005 ,-0.0763 ),
		Array(0.2950 ,0.1005 ,-0.0754 ),
		Array(0.2961 ,0.1060 ,-0.0748 ),
		Array(0.2938 ,0.1061 ,-0.0723 )
	)

	var dataHand2 = Array(
		Array(0.1853 ,-0.1450 ,-0.5995 ),
		Array(0.1853 ,-0.1452 ,-0.5987 ),
		Array(0.1874 ,-0.1443 ,-0.5980 ),
		Array(0.1915 ,-0.1442 ,-0.5970 ),
		Array(0.1947 ,-0.1490 ,-0.5952 ),
		Array(0.1967 ,-0.1488 ,-0.5952 ),
		Array(0.2028 ,-0.1384 ,-0.5935 ),
		Array(0.2090 ,-0.1474 ,-0.5884 ),
		Array(0.2193 ,-0.1461 ,-0.5842 ),
		Array(0.2328 ,-0.1398 ,-0.5807 ),
		Array(0.2472 ,-0.1287 ,-0.5763 ),
		Array(0.2636 ,-0.1270 ,-0.5669 ),
		Array(0.2832 ,-0.1195 ,-0.5551 ),
		Array(0.3039 ,-0.1170 ,-0.5415 ),
		Array(0.3266 ,-0.1139 ,-0.5255 ),
		Array(0.3484 ,-0.0960 ,-0.5075 ),
		Array(0.3732 ,-0.0927 ,-0.4872 ),
		Array(0.3983 ,-0.0790 ,-0.4641 ),
		Array(0.4204 ,-0.0604 ,-0.4391 ),
		Array(0.4413 ,-0.0467 ,-0.4091 ),
		Array(0.4619 ,-0.0175 ,-0.3782 ),
		Array(0.4766 ,-0.0037 ,-0.3454 ),
		Array(0.4906 ,0.0154 ,-0.3095 ),
		Array(0.4994 ,0.0394 ,-0.2731 ),
		Array(0.5070 ,0.0585 ,-0.2350 ),
		Array(0.5081 ,0.0774 ,-0.1977 ),
		Array(0.5076 ,0.1063 ,-0.1643 ),
		Array(0.5030 ,0.1194 ,-0.1287 ),
		Array(0.4959 ,0.1482 ,-0.0974 ),
		Array(0.4886 ,0.1503 ,-0.0661 ),
		Array(0.4766 ,0.1681 ,-0.0383 ),
		Array(0.4623 ,0.1855 ,-0.0139 ),
		Array(0.4479 ,0.2029 ,0.0099 ),
		Array(0.4322 ,0.2146 ,0.0294 ),
		Array(0.4188 ,0.2261 ,0.0465 ),
		Array(0.4008 ,0.2373 ,0.0608 ),
		Array(0.3873 ,0.2488 ,0.0745 ),
		Array(0.3716 ,0.2597 ,0.0836 ),
		Array(0.3580 ,0.2708 ,0.0929 ),
		Array(0.3433 ,0.2760 ,0.0977 ),
		Array(0.3295 ,0.2756 ,0.1036 ),
		Array(0.3159 ,0.2750 ,0.1067 ),
		Array(0.3058 ,0.2916 ,0.1089 ),
		Array(0.2944 ,0.2912 ,0.1131 ),
		Array(0.2830 ,0.2906 ,0.1136 ),
		Array(0.2727 ,0.2844 ,0.1142 ),
		Array(0.2648 ,0.3011 ,0.1165 ),
		Array(0.2591 ,0.2951 ,0.1164 ),
		Array(0.2522 ,0.2947 ,0.1170 ),
		Array(0.2465 ,0.3000 ,0.1168 ),
		Array(0.2420 ,0.2998 ,0.1176 ),
		Array(0.2374 ,0.2997 ,0.1183 ),
		Array(0.2351 ,0.2996 ,0.1191 ),
		Array(0.2306 ,0.2993 ,0.1199 )
	)
}