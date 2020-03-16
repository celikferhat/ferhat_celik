module _32bit_2mux(out,a,b, select);
input [31:0] a,b;
input select;
output [31:0] out;

_2mux mx0(out[0], a[0], b[0], select),
		mx1(out[1], a[1], b[1], select),
		mx2(out[2], a[2], b[2], select),
		mx3(out[3], a[3], b[3], select),
		mx4(out[4], a[4], b[4], select),
		mx5(out[5], a[5], b[5], select),
		mx6(out[6], a[6], b[6], select),
		mx7(out[7], a[7], b[7], select),
		mx8(out[8], a[8], b[8], select),
		mx9(out[9], a[9], b[9], select),
		mx10(out[10], a[10], b[10], select),
		mx11(out[11], a[11], b[11], select),
		mx12(out[12], a[12], b[12], select),
		mx13(out[13], a[13], b[13], select),
		mx14(out[14], a[14], b[14], select),
		mx15(out[15], a[15], b[15], select),
		mx16(out[16], a[16], b[16], select),
		mx17(out[17], a[17], b[17], select),
		mx18(out[18], a[18], b[18], select),
		mx19(out[19], a[19], b[19], select),
		mx20(out[20], a[20], b[20], select),
		mx21(out[21], a[21], b[21], select),
		mx22(out[22], a[22], b[22], select),
		mx23(out[23], a[23], b[23], select),
		mx24(out[24], a[24], b[24], select),
		mx25(out[25], a[25], b[25], select),
		mx26(out[26], a[26], b[26], select),
		mx27(out[27], a[27], b[27], select),
		mx28(out[28], a[28], b[28], select),
		mx29(out[29], a[29], b[29], select),
		mx30(out[30], a[30], b[30], select),
		mx31(out[31], a[31], b[31], select);
		

endmodule
