module mips32(clk);

//I type OP  RS  RT  16BIT
//I type  6   5   5   16

// Load Byte					100000
// Load Byte Unsigned		100100
// Load Halfword				100001
// Load Halfword Unsigned	100101
// Load Upper Imm				001111
// Load Word					100011
// Stroe Byte					101000
// Store Halfword				101001
// Store Word					101011


input clk;
 

wire memRead , memWrite , signal_regWrite , zero_or_sign,lui_or_other;
wire [1:0] load_select , store_signal;
wire [31:0] sign_extended , zero_extended, extended  , instruction ,pc_in, nextPc;
wire [31:0] read_data_1,read_data_2,write_data_for_reg;
wire [31:0] address,readData,mux_out,mem_result,result;
wire [2:0] aluop;
wire [5:0] rt_or_rd;			// rd or rt for lw lh lb sw sb instruction
wire v,z,carry;
wire alusrc,write_select , jump , branch,branch_signal,frht_signal;
wire [31:0] rt_reg,jAddress;

_2mux rd1(rt_or_rd[0],instruction[11],instruction[16],write_select);
_2mux rd2(rt_or_rd[1],instruction[12],instruction[17],write_select);
_2mux rd3(rt_or_rd[2],instruction[13],instruction[18],write_select);
_2mux rd4(rt_or_rd[3],instruction[14],instruction[19],write_select);
_2mux rd5(rt_or_rd[4],instruction[15],instruction[20],write_select);




next_pc n1(pc_in, nextPc , jump,branch_signal ,frht_signal,clk);

and (branch_signal,branch,z);

jump_address ja(jAddress,instruction[25:0],nextPc[31:28],instruction[31]);
wire [31:0] temp;
_32bit_2mux mux1(temp,nextPc,sign_extended,branch_signal);
_32bit_2mux mux2(pc_in,temp,jAddress,jump);


instruction_memory i1(instruction, nextPc);
control_unit c1(instruction[31:26],instruction[5:0], memRead, memWrite, signal_regWrite,zero_or_sign,load_select,store_signal,lui_or_other,alusrc,write_select,jump,branch,frht_signal);
mips_registers m2( read_data_1, read_data_2, result, instruction[25:21],  instruction[20:16], rt_or_rd , signal_regWrite, clk );




sign_extender s1(instruction[15:0],sign_extended);
zero_extender z1(instruction[15:0],zero_extended);
sign_zero_mux m1(extended,sign_extended,zero_extended,zero_or_sign);








alu_control ac(aluop,instruction[5:0],instruction[31:26]);


_32bit_2mux m5(rt_reg,read_data_2,extended,alusrc);



alu32 a1(address,v,z,carry,read_data_1,rt_reg,aluop,instruction[31:26]);
data_memory d1( readData,address,read_data_2,memWrite,memRead,store_signal,clk);
mux_for_memtoWriteData m3(mux_out,readData,instruction[15:0],lui_or_other);
memtoWriteData m4(mem_result,mux_out,load_select);

_32bit_2mux m6(result,address,mem_result,write_select);





endmodule