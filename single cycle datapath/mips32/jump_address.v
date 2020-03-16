module jump_address(address,instr , pc , jump_opcode );

input[3:0] pc;
input jump_opcode;
input [25:0]instr;
output[31:0]address;



buf b1(address[0],instr[0]);
buf b2(address[1],instr[1]);
buf b3(address[2],instr[2]);
buf b4(address[3],instr[3]);
buf b5(address[4],instr[4]);
buf b6(address[5],instr[5]);
buf b7(address[6],instr[6]);
buf b8(address[7],instr[7]);
buf b9(address[8],instr[8]);
buf b10(address[9],instr[9]);
buf b11(address[10],instr[10]);
buf b12(address[11],instr[11]);
buf b13(address[12],instr[12]);
buf b14(address[13],instr[13]);
buf b15(address[14],instr[14]);
buf b16(address[15],instr[15]);
buf b17(address[16],instr[16]);
buf b18(address[17],instr[17]);
buf b19(address[18],instr[18]);
buf b20(address[19],instr[19]);
buf b21(address[20],instr[20]);
buf b22(address[21],instr[21]);
buf b23(address[22],instr[22]);
buf b24(address[23],instr[23]);
buf b25(address[24],instr[24]);
buf b26(address[25],instr[25]);
buf b27(address[26],jump_opcode);
buf b28(address[27],jump_opcode);
buf b29(address[28],pc[0]);
buf b30(address[29],pc[1]);
buf b31(address[30],pc[2]);
buf b32(address[31],pc[3]);

endmodule
