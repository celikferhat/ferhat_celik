module alu32( r ,v,znot ,carry_out, a , b  ,op ,all_op);

input  [31:0]  a , b  ;
input  [2:0]   op;
input  [5:0] all_op;
output [31:0]  r ;
output v,znot,carry_out;    // v 1 if over flow znot == z if result 0 znot =1
wire [31:0] c_in , c_out ;

wire  set;

wire r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12,r13,r14,r15,r16;

wire s1,s2,s3,s4,s5,s6,s7,s8;

wire k1,k2,k3,k4;

wire j1,j2,z;


wire [31:0] xor_r,alu_r,shift_left_r,shift_right_r;


wire xor_select , shift_left_select,shift_right_select;


reg zero = 1'b0 ;

_1bit_alu a0(alu_r[0] , c_out[0] , a[0] , b[0] , set , op[2] , op  );

_1bit_alu a1(alu_r[1] ,  c_out[1] ,  a[1] ,  b[1] , zero , c_out[0]   , op  );
_1bit_alu a2(alu_r[2] ,  c_out[2] ,  a[2] ,  b[2] , zero , c_out[1]   , op  );
_1bit_alu a3(alu_r[3] ,  c_out[3] ,  a[3] ,  b[3] , zero , c_out[2]   , op  );
_1bit_alu a4(alu_r[4] ,  c_out[4] ,  a[4] ,  b[4] , zero , c_out[3]   , op  );
_1bit_alu a5(alu_r[5] ,  c_out[5] ,  a[5] ,  b[5] , zero , c_out[4]   , op  );
_1bit_alu a6(alu_r[6] ,  c_out[6] ,  a[6] ,  b[6] , zero , c_out[5]   , op  );
_1bit_alu a7(alu_r[7] ,  c_out[7] ,  a[7] ,  b[7] , zero , c_out[6]   , op  );
_1bit_alu a8(alu_r[8] ,  c_out[8] ,  a[8] ,  b[8] , zero , c_out[7]   , op  );
_1bit_alu a9(alu_r[9] ,  c_out[9] ,  a[9] ,  b[9] , zero , c_out[8]   , op  );
_1bit_alu a10(alu_r[10] , c_out[10] , a[10] , b[10] , zero , c_out[9]  , op  );
_1bit_alu a11(alu_r[11] , c_out[11] , a[11] , b[11] , zero , c_out[10] , op  );
_1bit_alu a12(alu_r[12] , c_out[12] , a[12] , b[12] , zero , c_out[11] , op  );
_1bit_alu a13(alu_r[13] , c_out[13] , a[13] , b[13] , zero , c_out[12] , op  );
_1bit_alu a14(alu_r[14] , c_out[14] , a[14] , b[14] , zero , c_out[13] , op  );
_1bit_alu a15(alu_r[15] , c_out[15] , a[15] , b[15] , zero , c_out[14] , op  );
_1bit_alu a16(alu_r[16] , c_out[16] , a[16] , b[16] , zero , c_out[15] , op  );
_1bit_alu a17(alu_r[17] , c_out[17] , a[17] , b[17] , zero , c_out[16] , op  );
_1bit_alu a18(alu_r[18] , c_out[18] , a[18] , b[18] , zero , c_out[17] , op  );
_1bit_alu a19(alu_r[19] , c_out[19] , a[19] , b[19] , zero , c_out[18] , op  );
_1bit_alu a20(alu_r[20] , c_out[20] , a[20] , b[20] , zero , c_out[19] , op  );
_1bit_alu a21(alu_r[21] , c_out[21] , a[21] , b[21] , zero , c_out[20] , op  );
_1bit_alu a22(alu_r[22] , c_out[22] , a[22] , b[22] , zero , c_out[21] , op  );
_1bit_alu a23(alu_r[23] , c_out[23] , a[23] , b[23] , zero , c_out[22] , op  );
_1bit_alu a24(alu_r[24] , c_out[24] , a[24] , b[24] , zero , c_out[23] , op  );
_1bit_alu a25(alu_r[25] , c_out[25] , a[25] , b[25] , zero , c_out[24] , op  );
_1bit_alu a26(alu_r[26] , c_out[26] , a[26] , b[26] , zero , c_out[25] , op  );
_1bit_alu a27(alu_r[27] , c_out[27] , a[27] , b[27] , zero , c_out[26] , op  );
_1bit_alu a28(alu_r[28] , c_out[28] , a[28] , b[28] , zero , c_out[27] , op  );
_1bit_alu a29(alu_r[29] , c_out[29] , a[29] , b[29] , zero , c_out[28] , op  );
_1bit_alu a30(alu_r[30] , c_out[30] , a[30] , b[30] , zero , c_out[29] , op  );

_1bit_msb_alu a31(alu_r[31] , carry_out , v , set , a[31] , b[31], zero , c_out[30] , op  );






or or1(r1, alu_r[0],alu_r[1]);
or or2(r2, alu_r[2],alu_r[3]);
or or3(r3, alu_r[4],alu_r[5]);
or or4(r4, alu_r[6],alu_r[7]);
or or5(r5, alu_r[8],alu_r[9]);
or or6(r6, alu_r[10],alu_r[11]);
or or7(r7, alu_r[12],alu_r[13]);
or or8(r8, alu_r[14],alu_r[15]);
or or9(r9, alu_r[16],alu_r[17]);
or or10(r10, alu_r[18],alu_r[19]);
or or11(r11, alu_r[20],alu_r[21]);
or or12(r12, alu_r[22],alu_r[23]);
or or13(r13, alu_r[24],alu_r[25]);
or or14(r14, alu_r[26],alu_r[27]);
or or15(r15, alu_r[28],alu_r[29]);
or or16(r16, alu_r[30],alu_r[31]);



or sor1(s1 , r1  ,r2);
or sor2(s2 , r3  ,r4);
or sor3(s3 , r5  ,r6);
or sor4(s4 , r7  ,r8);
or sor5(s5 , r9  ,r10);
or sor6(s6 , r11 ,r12);
or sor7(s7 , r13 ,r14);
or sor8(s8 , r15 ,r16);



or (k1,s1,s2);
or (k2,s3,s4);
or (k3,s5,s6);
or (k4,s7,s8);

or (j1,k1,k2);
or (j2,k3,k4);

or (z,j1,j2);
not (znot,z);



and (xor_select,op[0],op[1],~op[2]);
and (shift_left_select,op[0],~op[1],op[2]);
and (shift_right_select,~op[0],~op[1],op[2]);

_32bit_xor x1(xor_r,a,b);

_32bit_shift_left sl(shift_left_r,a,b);

_32bit_shift_right sr(shift_right_r,a,b);


wire [31:0] shift_result;
_32bit_2mux sson(shift_result,shift_left_r,shift_right_r,shift_right_select);


wire shift_select;
or (shift_select,shift_left_select,shift_right_select);


wire[31:0] temp_result,celik_result;
_32bit_4mux m1(temp_result , alu_r , xor_r , shift_result , shift_right_r , xor_select,shift_select  );


and (celik , all_op[5],all_op[4],all_op[3],all_op[2],all_op[1],~all_op[0]);

_32bit_2mux cmux1(celik_result,temp_result,xor_r,temp_result[0]);
_32bit_2mux cmux2(r,temp_result,celik_result,celik);









endmodule