module control_unit(opcode,funct, memRead, memWrite, signal_regWrite,zero_or_sign,load_select,store_signal,lui_or_other,alusrc,write_select,jump_signal,branch_signal,frht_signal);
input zero_or_sign;
output [1:0] load_select,store_signal;
input [5:0] opcode,funct;
wire lb, lbu, lh, lhu, lui, lw, sb, sh, sw, i_type,j,jr,beq,bne;
output memRead, memWrite, signal_regWrite,lui_or_other,alusrc,write_select,jump_signal,branch_signal,frht_signal;


and (frht_signal,opcode[5], opcode[4], opcode[3], opcode[2], opcode[1], opcode[0]);

//lb
and a1(lb,  opcode[5], ~opcode[4], ~opcode[3], ~opcode[2], ~opcode[1], ~opcode[0]);
//lbu
and a2(lbu, opcode[5], ~opcode[4], ~opcode[3], opcode[2], ~opcode[1], ~opcode[0]);
//lh
and a3(lh,  opcode[5], ~opcode[4], ~opcode[3], ~opcode[2], ~opcode[1], opcode[0]);
//lhu
and a4(lhu, opcode[5], ~opcode[4], ~opcode[3], opcode[2], ~opcode[1], opcode[0]);
//lui
and a5(lui, ~opcode[5], ~opcode[4], opcode[3], opcode[2], opcode[1], opcode[0]);
//lw
and a6(lw,  opcode[5], ~opcode[4], ~opcode[3], ~opcode[2], opcode[1], opcode[0]);
//sb
and a7(sb,  opcode[5], ~opcode[4], opcode[3], ~opcode[2], ~opcode[1], ~opcode[0]);
//sh
and a8(sh,  opcode[5], ~opcode[4], opcode[3], ~opcode[2], ~opcode[1], opcode[0]);
//sw
and a9(sw,  opcode[5], ~opcode[4], opcode[3], ~opcode[2], opcode[1], opcode[0]);


and ( j , opcode[0],~opcode[1],~opcode[2],~opcode[3],opcode[4],~opcode[5]);

and ( jr, ~funct[0] ,funct[1],~funct[2],funct[3],~funct[4],~funct[5]);

and ( beq , ~opcode[0],opcode[1],opcode[2],opcode[3],~opcode[4],~opcode[5]);    

and ( bne , ~opcode[0],~opcode[1],~opcode[2],~opcode[3],opcode[4],~opcode[5]); 
//add

   and ( addi , ~opcode[0],~opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]); //
	and ( addiu , opcode[0],~opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]); //
	and ( slti , ~opcode[0],opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]);  //
	and ( sltiu , opcode[0],opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]);  //



wire not_signal_reg_write;
or (not_signal_reg_write,sb,sh,sw,j,jr,beq,bne);
not (signal_regWrite,not_signal_reg_write);







or o1( memRead, lb, lbu, lh, lhu, lui,lw);
or o2( memWrite, sb, sh, sw);

or o4( zero_or_sign , lb,lh,addi,addiu,slti,sltiu);

or o5( load_select[0] , lb , lbu , lui );
or o6( load_select[1] , lh , lhu , lui );

or o7(store_signal[0],sh,sh);
or o8(store_signal[1],sw,sw);
or o9(lui_or_other,lui,lui);



and (i_type, ~opcode[0],~opcode[1],~opcode[2],~opcode[3],~opcode[4],~opcode[5]);
not (alusrc,i_type);




or (write_select,lb,lbu,lh,lhu,lui,lw,sb,sh,sw);

or (jump_signal , j,jr);
or (branch_signal,beq,bne);




endmodule
