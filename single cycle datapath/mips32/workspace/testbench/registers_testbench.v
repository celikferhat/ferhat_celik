`timescale 10ps/10ps
module registers_testbench();

wire [31:0] read_data_1, read_data_2;
reg [31:0] write_data;
reg [4:0] read_reg_1, read_reg_2, write_reg;
reg signal_reg_write, clk;

mips_registers m1( read_data_1, read_data_2, write_data, read_reg_1, read_reg_2, write_reg, signal_reg_write, clk );



initial begin
$readmemb("registers.mem",m1.registers);
end

initial clk = 0;




initial begin
clk = ~clk; #15;
signal_reg_write = 1'b1;
write_reg = 5'b00000;
write_data = 32'b00000000000000000000000011111111;

clk = ~clk; #15;
clk = ~clk; #15;
signal_reg_write = 1'b0;
read_reg_1 = 5'b00000;


clk = ~clk; #15;
clk = ~clk; #15;
signal_reg_write = 1'b0;
read_reg_1 = 5'b00000;


clk = ~clk; #15;
clk = ~clk; #15;

 
clk = ~clk; #15; 
end

initial begin

$monitor("reg1: %p\nRead1: %p \n",read_reg_1,read_data_1);

end

endmodule