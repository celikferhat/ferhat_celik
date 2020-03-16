module sign_extender(immediate, extended);

input [15:0] immediate;
output [31:0] extended;

wire sign;

and (sign,immediate[15],1);


buf b0(extended[0], 		immediate[0]),		
	 b1(extended[1], 		immediate[1]),
	 b2(extended[2], 		immediate[2]),
	 b3(extended[3], 		immediate[3]),
	 b4(extended[4], 		immediate[4]),
	 b5(extended[5], 		immediate[5]),
	 b6(extended[6], 		immediate[6]),
	 b7(extended[7], 		immediate[7]),
	 b8(extended[8], 		immediate[8]),
	 b9(extended[9], 		immediate[9]),
	 b10(extended[10], 	immediate[10]),
	 b11(extended[11], 	immediate[11]),
	 b12(extended[12], 	immediate[12]),
	 b13(extended[13], 	immediate[13]),
	 b14(extended[14], 	immediate[14]),
	 b15(extended[15], 	immediate[15]),
	 b16(extended[16], 	sign),
	 b17(extended[17], 	sign),
	 b18(extended[18], 	sign),
	 b19(extended[19], 	sign),
	 b20(extended[20], 	sign),
	 b21(extended[21], 	sign),
	 b22(extended[22], 	sign),
	 b23(extended[23], 	sign),
	 b24(extended[24], 	sign),
	 b25(extended[25], 	sign),
	 b26(extended[26], 	sign),
	 b27(extended[27], 	sign),
	 b28(extended[28], 	sign),
	 b29(extended[29], 	sign),
	 b30(extended[30], 	sign),
	 b31(extended[31], 	sign);
	 
endmodule
	 