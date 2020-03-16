module next_pc(pc, nextPc, jump,branch_signal ,frht_signal ,clock);
	input [31:0] pc;	
	output reg [31:0] nextPc;
	input clock,jump,branch_signal,frht_signal;
	
	
	initial begin
		nextPc = -1;
	end
	
	always@(posedge clock) begin
			
			if(jump == 1) begin
				nextPc = pc;
			end
			else if(branch_signal == 1) begin
				nextPc = nextPc + pc; 
			end
			else if(frht_signal == 1)begin
				nextPc = nextPc + 2;
			end
			else begin 
				nextPc = nextPc+1;
			end
	end

endmodule
