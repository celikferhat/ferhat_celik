module alu_control(aluop,funct , opcode);
	
	input [5:0] funct,opcode;
	output [2:0] aluop;
	wire r_type;
	
	wire _and , _or , _xor , _add , _sub , _slt , _sra , _srl , _sll , _sltu , _jr ; //r type 
	
	wire _andi , _ori , _xori, _addi , _addiu , _slti ,_sltiu, _lw , _lh , _lb , _lui , _sw , _sb , _beq , _bne ; // I type
	
	wire _j , _jal ; // j type
	
	
	and a1(r_type, ~opcode[0],~opcode[1],~opcode[2],~opcode[3],~opcode[4],~opcode[5]);
	
	//r types
	and ( _and, ~funct[0] ,~funct[1],~funct[2],~funct[3],~funct[4],~funct[5]); //
	and ( _or, funct[0] ,~funct[1],~funct[2],~funct[3],~funct[4],~funct[5]);   //
	and ( _xor, ~funct[0] ,funct[1],~funct[2],~funct[3],~funct[4],~funct[5]);  //
	and ( _add, funct[0] ,funct[1],~funct[2],~funct[3],~funct[4],~funct[5]);   //
	and ( _sub, ~funct[0] ,~funct[1],funct[2],~funct[3],~funct[4],~funct[5]);  //
	and ( _slt, funct[0] ,~funct[1],funct[2],~funct[3],~funct[4],~funct[5]);   //
	and ( _sra, ~funct[0] ,funct[1],funct[2],~funct[3],~funct[4],~funct[5]);   //
	and ( _srl, funct[0] ,funct[1],funct[2],~funct[3],~funct[4],~funct[5]);    //
	and ( _sll, ~funct[0] ,~funct[1],~funct[2],funct[3],~funct[4],~funct[5]);  //
	and ( _sltu, funct[0] ,~funct[1],~funct[2],funct[3],~funct[4],~funct[5]);  //
	and ( _jr, ~funct[0] ,funct[1],~funct[2],funct[3],~funct[4],~funct[5]);    // rs to pc
	
	// i types
	and ( _andi , opcode[0],~opcode[1],~opcode[2],~opcode[3],~opcode[4],~opcode[5]); //
	and ( _ori , ~opcode[0],opcode[1],~opcode[2],~opcode[3],~opcode[4],~opcode[5]);  //
	and ( _xori , opcode[0],opcode[1],~opcode[2],~opcode[3],~opcode[4],~opcode[5]);  //
	and ( _addi , ~opcode[0],~opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]); //
	and ( _addiu , opcode[0],~opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]); //
	and ( _slti , ~opcode[0],opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]);  //
	and ( _sltiu , opcode[0],opcode[1],opcode[2],~opcode[3],~opcode[4],~opcode[5]);  //
	and ( _lw , opcode[0],opcode[1],~opcode[2],~opcode[3],~opcode[4],opcode[5]);   //
	and ( _lh , opcode[0],~opcode[1],~opcode[2],~opcode[3],~opcode[4],opcode[5]);    //
	and ( _lb , ~opcode[0],~opcode[1],~opcode[2],~opcode[3],~opcode[4],opcode[5]);    //
	and ( _lui , opcode[0],opcode[1],opcode[2],opcode[3],~opcode[4],~opcode[5]);    
	and ( _sw , opcode[0],opcode[1],~opcode[2],opcode[3],~opcode[4],opcode[5]);    //
	and ( _sb , ~opcode[0],~opcode[1],~opcode[2],opcode[3],~opcode[4],opcode[5]);     //
	and ( _beq , ~opcode[0],opcode[1],opcode[2],opcode[3],~opcode[4],~opcode[5]);    //
	and ( _bne , ~opcode[0],~opcode[1],~opcode[2],~opcode[3],opcode[4],~opcode[5]);     //
	
	// j types
	
	and ( _j , opcode[0],~opcode[1],~opcode[2],~opcode[3],opcode[4],~opcode[5]);
	and ( _jal , ~opcode[0],opcode[1],~opcode[2],~opcode[3],opcode[4],~opcode[5]);
	
	
	
	
	// and | or | add | xor | shift right | shift left | sub | slt
	
	//i
	wire and_ , or_ , add_ , xor_ , shiftr_ , shiftl_ , sub_ , slt_ ;
	
	or (and_    , _andi);
	or (or_      , _ori);
	or (add_    ,_addi , _addiu , _lb,_lh,_lw,_sb,_sw );
	or (xor_    ,_xori );
	or (sub_    ,_beq,_bne);
	or (slt_    , _slti , _sltiu);
	
	
	wire [2:0] i_type_op;
	
	and (i_type_op[0],~and_,~add_,~sub_);
	and (i_type_op[1],~and_,~or_);
	and (i_type_op[2],~and_,~or_,~add_,~xor_);
	
	
	
	
	

	
	//r
	wire r_and , r_or,r_add,r_xor,r_shiftr,r_shiftl,r_sub,r_slt;
	
	or (r_and    , _and );
	or (r_or    , _or );
	or (r_add   , _add );
	or (r_xor   ,_xor );
	or (r_shiftr ,_sra);
	or (r_shiftl , _srl,_sll);
	or (r_sub   ,_sub);
	or (r_slt   , _slt , _sltu);
	
	wire [2:0] r_type_op;
	
	
	
	
	
	
	and (r_type_op[0],~r_and,~r_add,~r_shiftr,~r_sub);
	and (r_type_op[1],~r_and,~r_or,~r_shiftr,~r_shiftl);
	and (r_type_op[2],~r_and,~r_or,~r_add,~r_xor);
	
	_2mux m1(aluop[0],i_type_op[0],r_type_op[0],r_type);
	_2mux m2(aluop[1],i_type_op[1],r_type_op[1],r_type);
	_2mux m3(aluop[2],i_type_op[2],r_type_op[2],r_type);
	
	
	
	
	
endmodule	