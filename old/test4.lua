--データのフォーマット

--3*3行列
--tab = {
-- 	num = {
-- 		{a,b,c},
-- 		{d,e,f},
-- 		{g,h,i}	
-- 	},
	
-- 	str = {
-- 		{"a","b","c"},
-- 		{"d","e","f"},
-- 		{"g","h","i"}
-- 	}
-- }

--列ベクトル
--tab = {
-- 	num = {
-- 		{a},
-- 		{b},
-- 		{c}	
-- 	},
	
-- 	str = {
-- 		{"a"},
-- 		{"b"},
-- 		{"c"}
-- 	}
-- }

posElbow = {
	{0.0425,-0.0386,-0.3498},
	{0.0434,-0.0346,-0.3499},
	{0.0468,-0.0393,-0.3491},
	{0.0477,-0.0353,-0.3477},
	{0.0519,-0.0359,-0.3462},
	{0.0574,-0.0409,-0.3454},
	{0.0616,-0.0415,-0.3439},
	{0.0655,-0.0337,-0.3411},
	{0.0719,-0.0346,-0.3404},
	{0.0790,-0.0310,-0.3400},
	{0.0854,-0.0322,-0.3377},
	{0.0948,-0.0292,-0.3341},
	{0.1055,-0.0311,-0.3304},
	{0.1149,-0.0283,-0.3260},
	{0.1252,-0.0213,-0.3202},
	{0.1378,-0.0147,-0.3144},
	{0.1525,-0.0086,-0.3071},
	{0.1643,-0.0067,-0.2987},
	{0.1769,-0.0001,-0.2921},
	{0.1899,-0.0029,-0.2828},
	{0.2036,0.0077,-0.2716},
	{0.2143,0.0140,-0.2617},
	{0.2271,0.0200,-0.2510},
	{0.2369,0.0222,-0.2410},
	{0.2467,0.0240,-0.2301},
	{0.2575,0.0304,-0.2185},
	{0.2661,0.0460,-0.2061},
	{0.2728,0.0439,-0.1958},
	{0.2805,0.0552,-0.1848},
	{0.2850,0.0624,-0.1730},
	{0.2894,0.0700,-0.1642},
	{0.2950,0.0726,-0.1532},
	{0.2986,0.0754,-0.1427},
	{0.3009,0.0830,-0.1322},
	{0.3044,0.0952,-0.1225},
	{0.3068,0.0938,-0.1129},
	{0.3080,0.1065,-0.1038},
	{0.3094,0.1006,-0.0950},
	{0.3095,0.1090,-0.0876},
	{0.3107,0.1222,-0.0816},
	{0.3108,0.1217,-0.0776},
	{0.3110,0.1303,-0.0717},
	{0.3100,0.1347,-0.0683},
	{0.3101,0.1343,-0.0658},
	{0.3090,0.1390,-0.0641},
	{0.3080,0.1435,-0.0614},
	{0.3113,0.1382,-0.0600}
}

posHand = {
	{0.2375, 0.0706, -0.1573},
	{0.2396, 0.0705, -0.1574},
	{0.2407, 0.0747, -0.1566},
	{0.2429, 0.0653, -0.1552},
	{0.2440, 0.0694, -0.1527},
	{0.2451, 0.0736, -0.1503},
	{0.2461, 0.0779, -0.1487},
	{0.2483, 0.0862, -0.1438},
	{0.2526, 0.0853, -0.1415},
	{0.2549, 0.0844, -0.1360},
	{0.2572, 0.0836, -0.1312},
	{0.2616, 0.0915, -0.1247},
	{0.2659, 0.0994, -0.1183},
	{0.2703, 0.1073, -0.1110},
	{0.2748, 0.1060, -0.1038},
	{0.2803, 0.1183, -0.0964},
	{0.2859, 0.1212, -0.0876},
	{0.2904, 0.1289, -0.0777},
	{0.2960, 0.1410, -0.0669},
	{0.3038, 0.1435, -0.0565},
	{0.3094, 0.1558, -0.0464},
	{0.3184, 0.1600, -0.0329},
	{0.3252, 0.1604, -0.0213},
	{0.3298, 0.1871, -0.0104},
	{0.3388, 0.1943, 0.0004},
	{0.3478, 0.2112, 0.0109},
	{0.3535, 0.2145, 0.0204},
	{0.3615, 0.2167, 0.0315},
	{0.3684, 0.2343, 0.0406},
	{0.3753, 0.2520, 0.0490},
	{0.3821, 0.2600, 0.0568},
	{0.3900, 0.2632, 0.0635},
	{0.3968, 0.2716, 0.0680},
	{0.4028, 0.2950, 0.0743},
	{0.4119, 0.3031, 0.0797},
	{0.4211, 0.3215, 0.0840},
	{0.4268, 0.3254, 0.0875},
	{0.4347, 0.3290, 0.0901},
	{0.4406, 0.3431, 0.0934},
	{0.4463, 0.3473, 0.0944},
	{0.4520, 0.3514, 0.0962},
	{0.4533, 0.3562, 0.0974},
	{0.4557, 0.3659, 0.0979},
	{0.4590, 0.3605, 0.0975},
	{0.4602, 0.3653, 0.0986},
	{0.4614, 0.3702, 0.0989},
	{0.4637, 0.3697, 0.0997}

}

posElbow = {
	{0.2*math.cos(math.pi*0),0.3*math.sin(math.pi*0),-0.1},
	{0.2*math.cos(math.pi*0.1),0.3*math.sin(math.pi*0.1),-0.1},
	{0.2*math.cos(math.pi*0.2),0.3*math.sin(math.pi*0.2),-0.1},
	{0.2*math.cos(math.pi*0.3),0.3*math.sin(math.pi*0.3),-0.1},
	{0.2*math.cos(math.pi*0.4),0.3*math.sin(math.pi*0.4),-0.1},
	{0.2*math.cos(math.pi*0.5),0.3*math.sin(math.pi*0.5),-0.1},
	{0.2*math.cos(math.pi*0.6),0.3*math.sin(math.pi*0.6),-0.1},
	{0.2*math.cos(math.pi*0.7),0.3*math.sin(math.pi*0.7),-0.1},
	{0.2*math.cos(math.pi*0.8),0.3*math.sin(math.pi*0.8),-0.1},
	{0.2*math.cos(math.pi*0.9),0.3*math.sin(math.pi*0.9),-0.1},
	{0.2*math.cos(math.pi*1.0),0.3*math.sin(math.pi*1.0),-0.1},
	{0.2*math.cos(math.pi*1.1),0.3*math.sin(math.pi*1.1),-0.1},
	{0.2*math.cos(math.pi*1.2),0.3*math.sin(math.pi*1.2),-0.1},
	{0.2*math.cos(math.pi*1.3),0.3*math.sin(math.pi*1.3),-0.1},
	{0.2*math.cos(math.pi*1.4),0.3*math.sin(math.pi*1.4),-0.1},
	{0.2*math.cos(math.pi*1.5),0.3*math.sin(math.pi*1.5),-0.1},
	{0.2*math.cos(math.pi*1.6),0.3*math.sin(math.pi*1.6),-0.1},
	{0.2*math.cos(math.pi*1.7),0.3*math.sin(math.pi*1.7),-0.1},
	{0.2*math.cos(math.pi*1.8),0.3*math.sin(math.pi*1.8),-0.1},
	{0.2*math.cos(math.pi*1.9),0.3*math.sin(math.pi*1.9),-0.1}
	
}

posHand = {
	{0.3*math.cos(math.pi*0),0.3*math.sin(math.pi*0),-0.3},
	{0.3*math.cos(math.pi*0.1),0.3*math.sin(math.pi*0.1),-0.3},
	{0.3*math.cos(math.pi*0.2),0.3*math.sin(math.pi*0.2),-0.3},
	{0.3*math.cos(math.pi*0.3),0.3*math.sin(math.pi*0.3),-0.3},
	{0.3*math.cos(math.pi*0.4),0.3*math.sin(math.pi*0.4),-0.3},
	{0.3*math.cos(math.pi*0.5),0.3*math.sin(math.pi*0.5),-0.3},
	{0.3*math.cos(math.pi*0.6),0.3*math.sin(math.pi*0.6),-0.3},
	{0.3*math.cos(math.pi*0.7),0.3*math.sin(math.pi*0.7),-0.3},
	{0.3*math.cos(math.pi*0.8),0.3*math.sin(math.pi*0.8),-0.3},
	{0.3*math.cos(math.pi*0.9),0.3*math.sin(math.pi*0.9),-0.3},
	{0.3*math.cos(math.pi*1.0),0.3*math.sin(math.pi*1.0),-0.3},
	{0.3*math.cos(math.pi*1.1),0.3*math.sin(math.pi*1.1),-0.3},
	{0.3*math.cos(math.pi*1.2),0.3*math.sin(math.pi*1.2),-0.3},
	{0.3*math.cos(math.pi*1.3),0.3*math.sin(math.pi*1.3),-0.3},
	{0.3*math.cos(math.pi*1.4),0.3*math.sin(math.pi*1.4),-0.3},
	{0.3*math.cos(math.pi*1.5),0.3*math.sin(math.pi*1.5),-0.3},
	{0.3*math.cos(math.pi*1.6),0.3*math.sin(math.pi*1.6),-0.3},
	{0.3*math.cos(math.pi*1.7),0.3*math.sin(math.pi*1.7),-0.3},
	{0.3*math.cos(math.pi*1.8),0.3*math.sin(math.pi*1.8),-0.3},
	{0.3*math.cos(math.pi*1.9),0.3*math.sin(math.pi*1.9),-0.3}
}

function a( ... )
	
end

posL = {}
for i=1,table.getn(posElbow) do
	local l1 = (posElbow[i][1]^2+posElbow[i][2]^2+posElbow[i][3]^2)^0.5
	local l2 = ((posElbow[i][1]-posHand[i][1])^2+(posElbow[i][2]-posHand[i][2])^2+(posElbow[i][3]-posHand[i][3])^2)^0.5
	posL[i] = {l1, l2}
end

function GetPosElbow(type,i)
	if type == "x" then
		return -posElbow[i][3]
	end
	 
	if type == "y" then
		return posElbow[i][1]	
	end
	
	if type == "z" then
		return -posElbow[i][2]
	end
end

function GetPosHand(type,i)
	if type == "x" then
		return -posHand[i][3]
	end
	 
	if type == "y" then
		return posHand[i][1]	
	end
	
	if type == "z" then
		return -posHand[i][2]
	end
end


--4自由度マニピュレータ(横から)
NUMBER_DIMENTSION = 4
if NUMBER_DIMENTSION == 4 then
	function  AffineTransformation(x,y,z,axis,rad)
		mtx = {x=UMBER_DIMENTSION,y=UMBER_DIMENTSION,str,num}
		local tabStr = {}
		local tabNum = {}
		if axis == "x" then
			tabStr = {
				{"1","0","0",tostring(x)},
				{"0","cos("..rad..")","-sin("..rad..")",tostring(y)},
				{"0","sin("..rad..")","cos("..rad..")",tostring(z)},
				{"0","0","0","1"}
			}
			
			tabNum = {
				{1,0,0,x},
				{0,math.cos(rad),-math.sin(rad),y},
				{0,math.sin(rad),math.cos(rad),z},
				{0,0,0,1}
			}
		elseif axis == "y" then
			tabStr = {
				{"cos("..rad..")",	"0", "sin("..rad..")",tostring(x)},
				{"0",	"1","0",tostring(y)},
				{"-sin("..rad..")","0","cos("..rad..")",tostring(z)},
				{"0",	"0","0","1"}
			}
			
			tabNum = {
				{math.cos(rad),	0, math.sin(rad),x},
				{0,1,0,y},
				{-math.sin(rad),0,math.cos(rad),z},
				{0,	0,0,1}
			}
		elseif axis == "z" then
			tabStr = {
				{"cos("..rad..")",	"-sin("..rad..")","0",tostring(x)},
				{"sin("..rad..")",	"cos("..rad..")","0",tostring(y)},
				{"0",			"0","1",tostring(z)},
				{"0",			"0","0","1"}
			}
			
			tabNum = {
				{math.cos(rad),	-math.sin(rad),0,x},
				{math.sin(rad),math.cos(rad),0,y},
				{0,	0,1,z},
				{0,	0,0,1}
			}
		end
		
		mtx.str = tabStr
		mtx.num = tabNum
		
		return mtx
	end
end

math.asin2 = function(x, y)
	rad1 = math.atan2(x, y)
	return 

end

function GetStrElement( tabMt,x,y )
	return tabMt.str[y][x]
end

function GetNumElement( tabMt,x,y )
	return tabMt.num[y][x]
end

function SetStrElement( tabMt, x, y, el )
	tabMt.str[y][x] = tostring(el)
end

function SetNumElement( tabMt, x, y, el )
	tabMt.num[y][x] = el
end

GSE = GetStrElement
GNE = GetNumElement

SSE = SetStrElement
SNE = SetNumElement

--MatProduct
--3*3正方行列を引数に取り，その積を返す．
function MatProduct( tabMt1, tabMt2 )
	local GSE = GetStrElement
	local GNE = GetNumElement
	local SSE = SetStrElement
	local SNE = SetNumElement	
	local tabRe = {}
	tabRe.str ={}
	tabRe.num ={}
	
	
	for i = 1, NUMBER_DIMENTSION do
		table.insert(tabRe.str,{})
		table.insert(tabRe.num,{}) 
	end
	
	for i = 1, NUMBER_DIMENTSION do
		for j=1, NUMBER_DIMENTSION do
			local strElement = ""
			local numElement = 0
			for k=1, NUMBER_DIMENTSION do
				--数値計算
				numElement = numElement + GNE(tabMt1,k,j)*GNE(tabMt2,i,k)
				--文字列
				--0の場合
				if tostring(GSE(tabMt1,k,j)) == "0" or tostring(GSE(tabMt2,i,k)) == "0" then
					strElement = strElement
					
				else	
					if(strElement ~= "") then
						strElement = strElement.."+"
					end
					
					--どちらかが1の場合
					if tostring(GSE(tabMt1,k,j)) == "1" and tostring(GSE(tabMt2,i,k)) ~= "1" then
						strElement = strElement..tostring(GSE(tabMt2,i,k))
					elseif tostring(GSE(tabMt2,i,k)) == "1" and tostring(GSE(tabMt1,k,j)) ~= "1" then
						strElement = strElement..tostring(GSE(tabMt1,k,j))
					elseif tostring(GSE(tabMt1,k,j)) == "1" and tostring(GSE(tabMt2,i,k)) == "1" then
						strElement = strElement .. "1"
					else
						strElement = strElement .. tostring(GSE(tabMt1,k,j)).."*"..tostring(GSE(tabMt2,i,k))
					end
					
				end
				
				if(k == NUMBER_DIMENTSION) and strElement == "" then
						strElement = strElement.."0"
				end
				
			end
			SSE(tabRe,i,j,strElement)
			SNE(tabRe,i,j,numElement)
		end
	end
	
	return tabRe
	
end
--]]

--EvaluteColumnVector
--4*4正方行列と列ベクトルの積（4*4正方行列を返す）
--(4*4行列,4*1行列)
--return 4*4行列
function EvaluteColumnVector(tabMt1,tabMt2)
	local tabRe = {num={},str={}}
	for i = 1, NUMBER_DIMENTSION do
		table.insert(tabRe.str,{})
		table.insert(tabRe.num,{}) 
	end
	
	for i = 1, 1 do
		for j=1, NUMBER_DIMENTSION do
			local strElement = ""
			local numElement = 0
			for k=1, NUMBER_DIMENTSION do
				--数値計算
				numElement = numElement + GNE(tabMt1,k,j)*GNE(tabMt2,i,k)
				--文字列
				--0の場合
				if tostring(GSE(tabMt1,k,j)) == "0" or tostring(GSE(tabMt2,i,k)) == "0" then
					strElement = strElement
					
				else	
					if(strElement ~= "") then
						strElement = strElement.."+"
					end
					
					--どちらかが1の場合
					if tostring(GSE(tabMt1,k,j)) == "1" and tostring(GSE(tabMt2,i,k)) ~= "1" then
						strElement = strElement..tostring(GSE(tabMt2,i,k))
					elseif tostring(GSE(tabMt2,i,k)) == "1" and tostring(GSE(tabMt1,k,j)) ~= "1" then
						strElement = strElement..tostring(GSE(tabMt1,k,j))
					elseif tostring(GSE(tabMt1,k,j)) == "1" and tostring(GSE(tabMt2,i,k)) == "1" then
						strElement = strElement .. "1"
					else
						strElement = strElement .. tostring(GSE(tabMt1,k,j)).."*"..tostring(GSE(tabMt2,i,k))
					end
					
				end
				
				if(k == NUMBER_DIMENTSION) and strElement == "" then
						strElement = strElement.."0"
				end
				
			end
			SSE(tabRe,i,j,strElement)
			SNE(tabRe,i,j,numElement)
		end
	end
	
	local tabMt = tabRe
	--output
	for i = 1, NUMBER_DIMENTSION do
		local str = "|\t"
		for j=1, 1 do
			str = str .. GSE(tabMt,j,i,element)
			if j ~= 1 then
				str = str .. "\t"
			end
		end
		print(str.."\t|")
	end
	print("\n")
	for i = 1, NUMBER_DIMENTSION do
		local str = "|\t"
		for j=1, 1 do
			str = str .. tostring(GNE(tabMt,j,i))
			if j ~= 1 then
				str = str .. "\t"
			end
		end
		print(str.."\t|")
	end
	return tabRe
end
	

function PrintStrMatrix(tabMt)
	for i = 1, NUMBER_DIMENTSION do
		local str = "|\t"
		for j=1, NUMBER_DIMENTSION do
			str = str .. GSE(tabMt,j,i,element)
			if j ~= NUMBER_DIMENTSION then
				str = str .. "\t"
			end
		end
		print(str.."\t|")
	end
end

function PrintNumMatrix(tabMt)
	for i = 1, NUMBER_DIMENTSION do
		local str = "|\t"
		for j=1, NUMBER_DIMENTSION do
			str = str .. tostring(GNE(tabMt,j,i))
			if j ~= NUMBER_DIMENTSION then
				str = str .. "\t"
			end
		end
		print(str.."\t|")
	end
end

function  ConvertNumToStr( tabMt )
	tabStr = {}
	for i = 1, NUMBER_DIMENTSION do
		table.insert(tabStr,{})
	end
	for i = 1, NUMBER_DIMENTSION do
		for j = 1, NUMBER_DIMENTSION do
			tabStr[i][j] = tostring(tabMt.num[i][j])
		end
	end
	tabMt.str = tabStr
end

AT = AffineTransformation
CNS = ConvertNumToStr


-- ローカルベクトル→ワールドベクトル変換
--ローカル列ベクトルtabClを姿勢行列tabRmに従って変換したグローバル列ベクトルを返す
function GetWVec(tabCl,tabRm)
	local wx=_XX(cn)*lx+_YX(cn)*ly+_ZX(cn)*lz
	local wy=_XY(cn)*lx+_YY(cn)*ly+_ZY(cn)*lz
	local wz=_XZ(cn)*lx+_YZ(cn)*ly+_ZZ(cn)*lz
	return wx,wy,wz
end

-- グローバルベクトル→ローカルベクトル変換 -------------------------------------
--グローバル列ベクトルtabClを姿勢行列tabRmに従って変換したローカル列ベクトルを返す

function GetLVec(wx,wy,wz,cn)
	local lx=_XX(cn)*wx+_XY(cn)*wy+_XZ(cn)*wz
	local ly=_YX(cn)*wx+_YY(cn)*wy+_YZ(cn)*wz
	local lz=_ZX(cn)*wx+_ZY(cn)*wy+_ZZ(cn)*wz
	return lx,ly,lz
end
 
 
 
 
function solve(l1,l2,th1,th2,th3,th4)
	local L1 = l1--0.29
	local L2 = l2--0.3		

	cv = {}
	cv.num = {
		{th1},
		{th2},
		{th3},
		{th4}
	}
	T0 = MatProduct(AffineTransformation(0,0,0,"z",-math.pi/2),AffineTransformation(0,0,0,"y",math.pi/2))			
	T0to1 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[1][1]),AffineTransformation(0,0,0,"x",-math.pi/2))
	T1to2 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[2][1]),AffineTransformation(0,0,0,"x",math.pi/2))
	T2to3 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[3][1]),AffineTransformation(0,0,L1,"x",math.pi/2))
	T3to4 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[4][1]),AffineTransformation(L2,0,0,"x",0))
	
	
	TAnsElbow = MatProduct(
		MatProduct(MatProduct(T0,T0to1),T1to2),
		T2to3
	)
	
	TAnsHand = MatProduct(TAnsElbow, T3to4)
	
	mtColumVector = {str,num}
	mtColumVector.num = {
		{0},
		{0},	
		{0},	
		{1},		
	}
	mtColumVector.str = {
		{"0"},
		{"0"},	
		{"0"},	
		{"1"},		
	}
	
	posNodeElbow = EvaluteColumnVector(TAnsElbow,mtColumVector)		
	posNodeHand = EvaluteColumnVector(TAnsHand,mtColumVector)		

	return posNodeElbow, posNodeHand
end


--IK4
--肘と手先の座標(Tg)を格納したインデックス（フレーム）を引数に取り各間接の角度を返す
function IK4(index)
	
	-- 間接角(返り値)
	local tabRadTh = {0,0,0,0}
	
	-- 肘位置(Tg)座標系
	local posTgElbow = {num,str}
	posTgElbow.num = {
		{posElbow[index][1]},
		{posElbow[index][2]},	
		{posElbow[index][3]},	
		{1},		
	}
	posTgElbow.str = {
		{posElbow[index][1]},
		{posElbow[index][2]},	
		{posElbow[index][3]},	
		{1},		
	}
	
	
	-- 肘位置（T0）座標系
	-- type = mat4_1
	local posT0Elbow = {}
	-- 肘位置をT0座標系に変換
	local T0 = MatProduct(AffineTransformation(0,0,0,"z",math.pi/2*1),AffineTransformation(0,0,0,"x",-math.pi/2*1))	
	
	posT0Elbow = EvaluteColumnVector(T0, posTgElbow) 
	
	--各間接の長さ
	local l1 = posL[index][1]
	local l2 = posL[index][2]
	
	-- th1	
	tabRadTh[1] = math.atan2(posT0Elbow.num[2][1],posT0Elbow.num[1][1])
	-- tabRadTh[1] = 0.7

	-- th2
	do 
		local lxy = (posT0Elbow.num[1][1]^2+posT0Elbow.num[2][1]^2)^0.5
		tabRadTh[2] = -math.atan2(lxy,posT0Elbow.num[3][1])
		-- tabRadTh[2] = math.pi/2-0.7--math.atan2(lxy,posT0Elbow.num[3][1])-math.pi/2.
	end
	
	--手位置(Tg)座標系
	local posTgHand = {num,str}
	posTgHand.num = {
		{posHand[index][1]},
		{posHand[index][2]},	
		{posHand[index][3]},	
		{1},		
	}
	posTgHand.str = {
		{posHand[index][1]},
		{posHand[index][2]},	
		{posHand[index][3]},	
		{1},		
	}
	
	-- 手位置（T0）座標系
	-- type = mat4_1
	local posT0Hand = {}
	-- 肘位置をT0座標系に変換
	posT0Hand = EvaluteColumnVector(T0, posTgHand)
	
	-- T0to1 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[1][1]),AffineTransformation(0,0,0,"x",-math.pi/2))
	-- T1to2 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[2][1]),AffineTransformation(0,0,0,"x",math.pi/2))
	-- T2to3 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[3][1]),AffineTransformation(0,0,L1,"x",math.pi/2))
	-- T3to4 = MatProduct(AffineTransformation(0,0,0,"z",cv.num[4][1]),AffineTransformation(L2,0,0,"x",0))	
	-- 肘位置(T0)をT2座標系に変換
	local T1to0 = MatProduct(AffineTransformation(0,0,0,"x",math.pi/2),AffineTransformation(0,0,0,"z",-tabRadTh[1]))
	local T2to1 = MatProduct(AffineTransformation(0,0,0,"x",-math.pi/2),AffineTransformation(0,0,0,"z",-tabRadTh[2]))	
	local T2to0 = MatProduct(T2to1,T1to0)

	--手を肘から見た相対座標系（傾きはT0座標系）
	local posT0HandL = {num, str}
	posT0HandL.num = {
		{posT0Hand.num[1][1]-posT0Elbow.num[1][1]},
		{posT0Hand.num[2][1]-posT0Elbow.num[2][1]},
		{posT0Hand.num[3][1]-posT0Elbow.num[3][1]},
		{1},		
	}
	posT0HandL.str = {
		{"0"},
		{"0"},	
		{"0"},	
		{1},		
	}
	posT2HandL = EvaluteColumnVector(T2to0, posT0HandL)
	
	-- th3	
	tabRadTh[3] = math.atan2(posT2HandL.num[1][1],posT2HandL.num[2][1])

	-- th4
	do 
		local lxy = (posT2HandL.num[2][1]^2+posT2HandL.num[1][1]^2)^0.5
		tabRadTh[4] = -math.atan2(posT2HandL.num[3][1],lxy)
	end

	return tabRadTh
end


--solve()

function draw()
	--(0) 準備
	package.path = "std/?.lua" --標準ライブラリパス指定
	require( "OpenGL" )        --OpenGL定数ファイル
	 
	--(1) 状態変更
	glDisable( GL_LIGHTING )   --シェーディングOFF
	glEnableClientState( GL_VERTEX_ARRAY ) --頂点配列ON
	glScale(5.0,-5.0,5.0)
	step = table.getn(posElbow)
	for i = 1, step do 

		local tmpTab = IK4(i)
		local th1, th2, th3, th4 = tmpTab[1],tmpTab[2],tmpTab[3],tmpTab[4]
		posNode1, posNode2 = solve(posL[i][1],posL[i][2],th1,th2,th3,th4)--角度から肘と手の座標を算出
		--ここから描画処理
		glLineWidth( 1.0 )
		glColor3( i/step, i/step, i/step )
		glBegin( GL_LINES )
			glVertex3( 0.0, 0.0, 0.0 )
			glVertex3( posNode1.num[1][1], posNode1.num[2][1], posNode1.num[3][1] )
			glVertex3( posNode1.num[1][1], posNode1.num[2][1], posNode1.num[3][1] )
			glVertex3( posNode2.num[1][1], posNode2.num[2][1], posNode2.num[3][1] )
		glEnd()
		
		glPointSize( 3.0 )
		glBegin( GL_POINTS )
			glColor3( i/step, i/step, i/step )
			glVertex3( posNode1.num[1][1], posNode1.num[2][1], posNode1.num[3][1] )
		glEnd()
		
		glPointSize( 5.0 )
		glBegin( GL_POINTS )
			glColor3( i/step, i/step, i/step )
			glVertex3( posNode2.num[1][1], posNode2.num[2][1], posNode2.num[3][1] )
		glEnd()
		
		-- glDisable( GL_LIGHTING )
			--glFontSize( 3.0 )
			glColor3( 1.0, 1.0, 1.0 )
			do 
				local str = i*step.."\n"
				str = str.."θ1 = "..cv.num[1][1]*180/math.pi.."°\n"
				str = str.."θ2 = "..cv.num[2][1]*180/math.pi.."°\n"
				str = str.."θ3 = "..cv.num[3][1]*180/math.pi.."°\n"
				
				-- glPrint( posNode2.num[1][1], posNode2.num[2][1], posNode2.num[3][1], str )
			end
		-- glEnable( GL_LIGHTING )
	end
	
	-- 肘座標データのプロット
	for i = 1, table.getn(posElbow) do
		glPointSize( 5.0 )
		glColor3( i/step, i/step, i/step )
		glBegin( GL_POINTS )
			glColor3( i/table.getn(posHand), 0, i/table.getn(posHand) )
			glVertex3( posElbow[i][1], posElbow[i][2], posElbow[i][3] )
		glEnd()
	end
	-- 手座標データのプロット
	for i = 1, table.getn(posHand) do
		glPointSize( 5.0 )
		glColor3( i/table.getn(posHand), 0, i/table.getn(posHand) )
		glBegin( GL_POINTS )
			glVertex3( posHand[i][1], posHand[i][2], posHand[i][3] )
		glEnd()
	end	
		 
	--(4) 状態復帰
	glDisableClientState( GL_VERTEX_ARRAY ) --頂点配列OFF
	glEnable( GL_LIGHTING )    --シェーディングON
	 
	--(5) トポスオブジェクト生成
	tnNewObject()	
end

draw()
