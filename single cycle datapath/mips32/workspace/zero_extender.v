module zero_extender(immediate, extended);

input [15:0] immediate;
output [31:0] extended;

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
	 b16(extended[16], 	0),
	 b17(extended[17], 	0),
	 b18(extended[18], 	0),
	 b19(extended[19], 	0),
	 b20(extended[20], 	0),
	 b21(extended[21], 	0),
	 b22(extended[22], 	0),
	 b23(extended[23], 	0),
	 b24(extended[24], 	0),
	 b25(extended[25], 	0),
	 b26(extended[26], 	0),
	 b27(extended[27], 	0),
	 b28(extended[28], 	0),
	 b29(extended[29], 	0),
	 b30(extended[30], 	0),
	 b31(extended[31], 	0);
	 
endmodule
	 