module instruction_memory(instruction, program_counter);
	input [31:0] program_counter;
	output reg [31:0] instruction;
	reg [31:0] instruction_mem[31:0];
	

	
	always @(program_counter) begin
		instruction = instruction_mem[program_counter];
	end
	
	
	

	
endmodule
	
	