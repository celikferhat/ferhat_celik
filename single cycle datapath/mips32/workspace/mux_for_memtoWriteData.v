module mux_for_memtoWriteData(out,a,b,select);

input select;
input [31:0] a;
input [15:0] b;
output[31:0] out;


wire [31:0] extend_b;

buf d0(extend_b[0],0),d1(extend_b[1],0),d2(extend_b[2],0),d3(extend_b[3],0),
	d4(extend_b[4],0),d5(extend_b[5],0),d6(extend_b[6],0),d7(extend_b[7],0),
	d8(extend_b[8],0),d9(extend_b[9],0),d10(extend_b[10],0),d11(extend_b[11],0),
	d12(extend_b[12],0),d13(extend_b[13],0),d14(extend_b[14],0),d15(extend_b[15],0),
	d16(extend_b[16],b[0]),d17(extend_b[17],b[1]),d18(extend_b[18],b[2]),d19(extend_b[19],b[3]),
	d20(extend_b[20],b[4]),d21(extend_b[21],b[5]),d22(extend_b[22],b[6]),d23(extend_b[23],b[7]),
	d24(extend_b[24],b[8]),d25(extend_b[25],b[9]),d26(extend_b[26],b[10]),d27(extend_b[27],b[11]),
	d28(extend_b[28],b[12]),d29(extend_b[29],b[13]),d30(extend_b[30],b[14]),d31(extend_b[31],b[15]);


_32bit_2mux m1(out,a,extend_b,select);



endmodule