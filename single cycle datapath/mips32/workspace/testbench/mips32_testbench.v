module mips32_testbench();

	//I type OP  RS  RT  16BIT
//I type  6   5   5   16

// Load Byte					100000
// Load Byte Unsigned			100100
// Load Halfword				100001
// Load Halfword Unsigned		100101
// Load Upper Imm				001111
// Load Word					100011
// Stroe Byte					101000
// Store Halfword				101001
// Store Word					101011

	reg clk;
	
	reg [7:0] index;
	mips32 a(clk);
	
		initial begin
			$readmemb("instruction.mem", a.i1.instruction_mem);
			$readmemb("registers.mem", a.m2.registers);
			$readmemb("data.mem", a.d1.data_mem);			
			clk = 0;
			//a.n1.nextPc= 32'b0;
			index = 0;	
		end
		
			always	begin
			#20 clk=~clk;
			end
	

	

	always @(posedge clk)begin
			
			if(index==8'd20)
			begin
			$writememb("Processed_registers.mem", a.m2.registers);
			$writememb("Processed_data.mem", a.d1.data_mem);
			end
			
			if(index==8'd25)
			begin
			$display("son");
			
				$finish;
			end
			index <= index +1;
			
			
	end
	
	always @(a.instruction)begin
	if(a.jump == 1  || a.frht_signal == 1) begin
	
	$display("J Type Instruction ");
	$display("opcode: %b | target address: %b ",a.instruction[31:26],a.instruction[25:0]);
	end
	else if(a.alusrc == 1) begin
	$display("I Type Instruction ");
	$display("opcode: %b | rs: %b | rt: %b | imm: %b   ",a.instruction[31:26],a.instruction[25:21],a.instruction[20:16],a.instruction[15:0]);
	$display("RS: %b",a.read_data_1);
	
	end
	else begin 
	$display("R Type Instruction ");
	$display("opcode: %b | rs: %b | rt: %b | rd: %b  | shamt: %b  | funct: %b  ",a.instruction[31:26],a.instruction[25:21],a.instruction[20:16],a.instruction[15:11],a.instruction[10:6],a.instruction[5:0]);
	$display("RS: %b",a.read_data_1);
	$display("RT: %b",a.read_data_2);
	end
	$display("Result:%b           index: %d \n" ,a.result,index);
	end

endmodule