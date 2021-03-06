module _32bit_shift_right(out, a,b);
input [31:0] a, b;
output [31:0] out;
wire [31:0] tmp, tmp1, tmp2, tmp3;

_2mux 		mx0(tmp[0], a[0], a[1], b[0]),
				mx1(tmp[1], a[1], a[2], b[0]),
				mx2(tmp[2], a[2], a[3], b[0]),
				mx3(tmp[3], a[3], a[4], b[0]),
				mx4(tmp[4], a[4], a[5], b[0]),
				mx5(tmp[5], a[5], a[6], b[0]),
				mx6(tmp[6], a[6], a[7], b[0]),
				mx7(tmp[7], a[7], a[8], b[0]),
				mx8(tmp[8], a[8], a[9], b[0]),
				mx9(tmp[9], a[9], a[10], b[0]),
				mx10(tmp[10], a[10], a[11], b[0]),
				mx11(tmp[11], a[11], a[12], b[0]),
				mx12(tmp[12], a[12], a[13], b[0]),
				mx13(tmp[13], a[13], a[14], b[0]),
				mx14(tmp[14], a[14], a[15], b[0]),
				mx15(tmp[15], a[15], a[16], b[0]),
				mx16(tmp[16], a[16], a[17], b[0]),
				mx17(tmp[17], a[17], a[18], b[0]),
				mx18(tmp[18], a[18], a[19], b[0]),
				mx19(tmp[19], a[19], a[20], b[0]),
				mx20(tmp[20], a[20], a[21], b[0]),
				mx21(tmp[21], a[21], a[22], b[0]),
				mx22(tmp[22], a[22], a[23], b[0]),
				mx23(tmp[23], a[23], a[24], b[0]),
				mx24(tmp[24], a[24], a[25], b[0]),
				mx25(tmp[25], a[25], a[26], b[0]),
				mx26(tmp[26], a[26], a[27], b[0]),
				mx27(tmp[27], a[27], a[28], b[0]),
				mx28(tmp[28], a[28], a[29], b[0]),
				mx29(tmp[29], a[29], a[30], b[0]),
				mx30(tmp[30], a[30], a[31], b[0]),
				mx31(tmp[31], a[31], 0, b[0]);
				
_2mux			mxa0(tmp1[0], tmp[0], tmp[2], b[1]),
				mxa1(tmp1[1], tmp[1], tmp[3], b[1]),
				mxa2(tmp1[2], tmp[2], tmp[4], b[1]),
				mxa3(tmp1[3], tmp[3], tmp[5], b[1]),
				mxa4(tmp1[4], tmp[4], tmp[6], b[1]),
				mxa5(tmp1[5], tmp[5], tmp[7], b[1]),
				mxa6(tmp1[6], tmp[6], tmp[8], b[1]),
				mxa7(tmp1[7], tmp[7], tmp[9], b[1]),
				mxa8(tmp1[8], tmp[8], tmp[10], b[1]),
				mxa9(tmp1[9], tmp[9], tmp[11], b[1]),
				mxa10(tmp1[10], tmp[10], tmp[12], b[1]),
				mxa11(tmp1[11], tmp[11], tmp[13], b[1]),
				mxa12(tmp1[12], tmp[12], tmp[14], b[1]),
				mxa13(tmp1[13], tmp[13], tmp[15], b[1]),
				mxa14(tmp1[14], tmp[14], tmp[16], b[1]),
				mxa15(tmp1[15], tmp[15], tmp[17], b[1]),
				mxa16(tmp1[16], tmp[16], tmp[18], b[1]),
				mxa17(tmp1[17], tmp[17], tmp[19], b[1]),
				mxa18(tmp1[18], tmp[18], tmp[20], b[1]),
				mxa19(tmp1[19], tmp[19], tmp[21], b[1]),
				mxa20(tmp1[20], tmp[20], tmp[22], b[1]),
				mxa21(tmp1[21], tmp[21], tmp[23], b[1]),
				mxa22(tmp1[22], tmp[22], tmp[24], b[1]),
				mxa23(tmp1[23], tmp[23], tmp[25], b[1]),
				mxa24(tmp1[24], tmp[24], tmp[26], b[1]),
				mxa25(tmp1[25], tmp[25], tmp[27], b[1]),
				mxa26(tmp1[26], tmp[26], tmp[28], b[1]),
				mxa27(tmp1[27], tmp[27], tmp[29], b[1]),
				mxa28(tmp1[28], tmp[28], tmp[30], b[1]),
				mxa29(tmp1[29], tmp[29], tmp[31], b[1]),
				mxa30(tmp1[30], tmp[30], 0, b[1]),
				mxa31(tmp1[31], tmp[31], 0, b[1]);
				
_2mux 		mxb0(tmp2[0], tmp1[0], tmp1[4], b[2]),
				mxb1(tmp2[1], tmp1[1], tmp1[5], b[2]),
				mxb2(tmp2[2], tmp1[2], tmp1[6], b[2]),
				mxb3(tmp2[3], tmp1[3], tmp1[7], b[2]),
				mxb4(tmp2[4], tmp1[4], tmp1[8], b[2]),
				mxb5(tmp2[5], tmp1[5], tmp1[9], b[2]),
				mxb6(tmp2[6], tmp1[6], tmp1[10], b[2]),
				mxb7(tmp2[7], tmp1[7], tmp1[11], b[2]),
				mxb8(tmp2[8], tmp1[8], tmp1[12], b[2]),
				mxb9(tmp2[9], tmp1[9], tmp1[13], b[2]),
				mxb10(tmp2[10], tmp1[10], tmp1[14], b[2]),
				mxb11(tmp2[11], tmp1[11], tmp1[15], b[2]),
				mxb12(tmp2[12], tmp1[12], tmp1[16], b[2]),
				mxb13(tmp2[13], tmp1[13], tmp1[17], b[2]),
				mxb14(tmp2[14], tmp1[14], tmp1[18], b[2]),
				mxb15(tmp2[15], tmp1[15], tmp1[19], b[2]),
				mxb16(tmp2[16], tmp1[16], tmp1[20], b[2]),
				mxb17(tmp2[17], tmp1[17], tmp1[21], b[2]),
				mxb18(tmp2[18], tmp1[18], tmp1[22], b[2]),
				mxb19(tmp2[19], tmp1[19], tmp1[23], b[2]),
				mxb20(tmp2[20], tmp1[20], tmp1[24], b[2]),
				mxb21(tmp2[21], tmp1[21], tmp1[25], b[2]),
				mxb22(tmp2[22], tmp1[22], tmp1[26], b[2]),
				mxb23(tmp2[23], tmp1[23], tmp1[27], b[2]),
				mxb24(tmp2[24], tmp1[24], tmp1[28], b[2]),
				mxb25(tmp2[25], tmp1[25], tmp1[29], b[2]),
				mxb26(tmp2[26], tmp1[26], tmp1[30], b[2]),
				mxb27(tmp2[27], tmp1[27], tmp1[31], b[2]),
				mxb28(tmp2[28], tmp1[28], 0, b[2]),
				mxb29(tmp2[29], tmp1[29], 0, b[2]),
				mxb30(tmp2[30], tmp1[30], 0, b[2]),
				mxb31(tmp2[31], tmp1[31], 0, b[2]);
				
_2mux 		mxc0(tmp3[0], tmp2[0], tmp2[8], b[3]),
				mxc1(tmp3[1], tmp2[1], tmp2[9], b[3]),
				mxc2(tmp3[2], tmp2[2], tmp2[10], b[3]),
				mxc3(tmp3[3], tmp2[3], tmp2[11], b[3]),
				mxc4(tmp3[4], tmp2[4], tmp2[12], b[3]),
				mxc5(tmp3[5], tmp2[5], tmp2[13], b[3]),
				mxc6(tmp3[6], tmp2[6], tmp2[14], b[3]),
				mxc7(tmp3[7], tmp2[7], tmp2[15], b[3]),
				mxc8(tmp3[8], tmp2[8], tmp2[16], b[3]),
				mxc9(tmp3[9], tmp2[9], tmp2[17], b[3]),
				mxc10(tmp3[10], tmp2[10], tmp2[18], b[3]),
				mxc11(tmp3[11], tmp2[11], tmp2[19], b[3]),
				mxc12(tmp3[12], tmp2[12], tmp2[20], b[3]),
				mxc13(tmp3[13], tmp2[13], tmp2[21], b[3]),
				mxc14(tmp3[14], tmp2[14], tmp2[22], b[3]),
				mxc15(tmp3[15], tmp2[15], tmp2[23], b[3]),
				mxc16(tmp3[16], tmp2[16], tmp2[24], b[3]),
				mxc17(tmp3[17], tmp2[17], tmp2[25], b[3]),
				mxc18(tmp3[18], tmp2[18], tmp2[26], b[3]),
				mxc19(tmp3[19], tmp2[19], tmp2[27], b[3]),
				mxc20(tmp3[20], tmp2[20], tmp2[28], b[3]),
				mxc21(tmp3[21], tmp2[21], tmp2[29], b[3]),
				mxc22(tmp3[22], tmp2[22], tmp2[30], b[3]),
				mxc23(tmp3[23], tmp2[23], tmp2[31], b[3]),
				mxc24(tmp3[24], tmp2[24], 0, b[3]),
				mxc25(tmp3[25], tmp2[25], 0, b[3]),
				mxc26(tmp3[26], tmp2[26], 0, b[3]),
				mxc27(tmp3[27], tmp2[27], 0, b[3]),
				mxc28(tmp3[28], tmp2[28], 0, b[3]),
				mxc29(tmp3[29], tmp2[29], 0, b[3]),
				mxc30(tmp3[30], tmp2[30], 0, b[3]),
				mxc31(tmp3[31], tmp2[31], 0, b[3]);
				
_2mux			mxd0(out[0], tmp3[0], tmp3[16], b[4]),
				mxd1(out[1], tmp3[1], tmp3[17], b[4]),
				mxd2(out[2], tmp3[2], tmp3[18], b[4]),
				mxd3(out[3], tmp3[3], tmp3[19], b[4]),
				mxd4(out[4], tmp3[4], tmp3[20], b[4]),
				mxd5(out[5], tmp3[5], tmp3[21], b[4]),
				mxd6(out[6], tmp3[6], tmp3[22], b[4]),
				mxd7(out[7], tmp3[7], tmp3[23], b[4]),
				mxd8(out[8], tmp3[8], tmp3[24], b[4]),
				mxd9(out[9], tmp3[9], tmp3[25], b[4]),
				mxd10(out[10], tmp3[10], tmp3[26], b[4]),
				mxd11(out[11], tmp3[11], tmp3[27], b[4]),
				mxd12(out[12], tmp3[12], tmp3[28], b[4]),
				mxd13(out[13], tmp3[13], tmp3[29], b[4]),
				mxd14(out[14], tmp3[14], tmp3[30], b[4]),
				mxd15(out[15], tmp3[15], tmp3[31], b[4]),
				mxd16(out[16], tmp3[16], 0, b[4]),
				mxd17(out[17], tmp3[17], 0, b[4]),
				mxd18(out[18], tmp3[18], 0, b[4]),
				mxd19(out[19], tmp3[19], 0, b[4]),
				mxd20(out[20], tmp3[20], 0, b[4]),
				mxd21(out[21], tmp3[21], 0, b[4]),
				mxd22(out[22], tmp3[22], 0, b[4]),
				mxd23(out[23], tmp3[23], 0, b[4]),
				mxd24(out[24], tmp3[24], 0, b[4]),
				mxd25(out[25], tmp3[25], 0, b[4]),
				mxd26(out[26], tmp3[26], 0, b[4]),
				mxd27(out[27], tmp3[27], 0, b[4]),
				mxd28(out[28], tmp3[28], 0, b[4]),
				mxd29(out[29], tmp3[29], 0, b[4]),
				mxd30(out[30], tmp3[30], 0, b[4]),
				mxd31(out[31], tmp3[31], 0, b[4]);
				
endmodule