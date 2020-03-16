module _32bit_adder(sum,a,b);
input [31:0] a, b;
output [31:0] sum;

wire [30:0] carry_out; 
wire c;



full_adder f1( sum[0] ,carry_out[0],a[0],b[0],1'b0),
				f2( sum[1] ,carry_out[1],a[1],b[1],carry_out[0] ),
				f3( sum[2] ,carry_out[2],a[2],b[2],carry_out[1] ),
				f4( sum[3] ,carry_out[3],a[3],b[3],carry_out[2] ),
				f5( sum[4] ,carry_out[4],a[4],b[4],carry_out[3] ),
				f6( sum[5] ,carry_out[5],a[5],b[5],carry_out[4] ),
				f7( sum[6] ,carry_out[6],a[6],b[6],carry_out[5] ),
				f8( sum[7] ,carry_out[7],a[7],b[7],carry_out[6] ),
				f9( sum[8] ,carry_out[8],a[8],b[8],carry_out[7] ),
				f10( sum[9] ,carry_out[9],a[9],b[9],carry_out[8] ),
				f11( sum[10] ,carry_out[10],a[10],b[10],carry_out[9] ),
				f12( sum[11] ,carry_out[11],a[11],b[11],carry_out[10] ),
				f13( sum[12] ,carry_out[12],a[12],b[12],carry_out[11] ),
				f14( sum[13] ,carry_out[13],a[13],b[13],carry_out[12] ),
				f15( sum[14] ,carry_out[14],a[14],b[14],carry_out[13] ),
				f16( sum[15] ,carry_out[15],a[15],b[15],carry_out[14] ),
				f17( sum[16] ,carry_out[16],a[16],b[16],carry_out[15] ),
				f18( sum[17] ,carry_out[17],a[17],b[17],carry_out[16] ),
				f19( sum[18] ,carry_out[18],a[18],b[18],carry_out[17] ),
				f20( sum[19] ,carry_out[19],a[19],b[19],carry_out[18] ),
				f21( sum[20] ,carry_out[20],a[20],b[20],carry_out[19] ),
				f22( sum[21] ,carry_out[21],a[21],b[21],carry_out[20] ),
				f23( sum[22] ,carry_out[22],a[22],b[22],carry_out[21] ),
				f24( sum[23] ,carry_out[23],a[23],b[23],carry_out[22] ),
				f25( sum[24] ,carry_out[24],a[24],b[24],carry_out[23] ),
				f26( sum[25] ,carry_out[25],a[25],b[25],carry_out[24] ),
				f27( sum[26] ,carry_out[26],a[26],b[26],carry_out[25] ),
				f28( sum[27] ,carry_out[27],a[27],b[27],carry_out[26] ),
				f29( sum[28] ,carry_out[28],a[28],b[28],carry_out[27] ),
				f30( sum[29] ,carry_out[29],a[29],b[29],carry_out[28] ),
           f31( sum[30] ,carry_out[30],a[30],b[30],carry_out[29] ),
			  f32( sum[31] ,c            ,a[31],b[31],carry_out[30] );




endmodule