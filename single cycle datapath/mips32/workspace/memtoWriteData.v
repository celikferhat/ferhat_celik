module memtoWriteData(out,in,load_select);
input [1:0] load_select;
input [31:0] in;
output[31:0] out ;
wire [31:0] out1 , out2 , out3;

buf b0(out1[0],in[0]),b1(out1[1],in[1]),b2(out1[2],in[2]),b3(out1[3],in[3]),
	b4(out1[4],in[4]),b5(out1[5],in[5]),b6(out1[6],in[6]),b7(out1[7],in[7]),
	b8(out1[8],0),b9(out1[9],0),b10(out1[10],0),b11(out1[11],0),
	b12(out1[12],0),b13(out1[13],0),b14(out1[14],0),b15(out1[15],0),
	b16(out1[16],0),b17(out1[17],0),b18(out1[18],0),b19(out1[19],0),
	b20(out1[20],0),b21(out1[21],0),b22(out1[22],0),b23(out1[23],0),
	b24(out1[24],0),b25(out1[25],0),b26(out1[26],0),b27(out1[27],0),
	b28(out1[28],0),b29(out1[29],0),b30(out1[30],0),b31(out1[31],0);
	
	
	
buf c0(out2[0],in[0]),c1(out2[1],in[1]),c2(out2[2],in[2]),c3(out2[3],in[3]),	
	c4(out2[4],in[4]),c5(out2[5],in[5]),c6(out2[6],in[6]),c7(out2[7],in[7]),
	c8(out2[8],in[8]),c9(out2[9],in[9]),c10(out2[10],in[10]),c11(out2[11],in[11]),
	c12(out2[12],in[12]),c13(out2[13],in[13]),c14(out2[14],in[14]),c15(out2[15],in[15]),
	c16(out2[16],0),c17(out2[17],0),c18(out2[18],0),c19(out2[19],0),
	c20(out2[20],0),c21(out2[21],0),c22(out2[22],0),c23(out2[23],0),
	c24(out2[24],0),c25(out2[25],0),c26(out2[26],0),c27(out2[27],0),
	c28(out2[28],0),c29(out2[29],0),c30(out2[30],0),c31(out2[31],0);

	
	

buf d0(out3[0],0),d1(out3[1],0),d2(out3[2],0),d3(out3[3],0),
	d4(out3[4],0),d5(out3[5],0),d6(out3[6],0),d7(out3[7],0),
	d8(out3[8],0),d9(out3[9],0),d10(out3[10],0),d11(out3[11],0),
	d12(out3[12],0),d13(out3[13],0),d14(out3[14],0),d15(out3[15],0),
	d16(out3[16],in[16]),d17(out3[17],in[17]),d18(out3[18],in[18]),d19(out3[19],in[19]),
	d20(out3[20],in[20]),d21(out3[21],in[21]),d22(out3[22],in[22]),d23(out3[23],in[23]),
	d24(out3[24],in[24]),d25(out3[25],in[25]),d26(out3[26],in[26]),d27(out3[27],in[27]),
	d28(out3[28],in[28]),d29(out3[29],in[29]),d30(out3[30],in[30]),d31(out3[31],in[31]);




	
	
_32bit_4mux o1(out,in,out1,out2,out3,load_select[0],load_select[1]);





endmodule