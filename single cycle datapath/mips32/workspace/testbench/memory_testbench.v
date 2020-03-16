`timescale 10ps/10ps
module memory_testbench();

wire [31:0] read_data;
reg [31:0] mem_address, write_data;
reg mem_read, mem_write,clk;
reg [1:0] store_signal;
data_memory test(read_data, mem_address,write_data ,mem_write,mem_read, store_signal,clk );



initial begin
$readmemb("data.mem",test.data_mem);
end

initial clk = 0;


initial begin
clk = ~clk; #20;
mem_address = 32'b00000000000000000000000000000001;
write_data  = 32'b11111101111111111111111111111111;
mem_read = 1'b1;
mem_write = 1'b0; 

clk = ~clk; #20;

store_signal = 2'b00;
mem_address = 32'b00000000000000000000000000000001;
write_data  = 32'b11111101111111111111111111111111;
mem_read = 1'b0;
mem_write = 1'b1; 

clk = ~clk; #20;

mem_address = 32'b00000000000000000000000000000001;
write_data  = 32'b11111101111111111111111111111111;
mem_read = 1'b1;
mem_write = 1'b0; 

clk = ~clk; #20;

mem_address = 32'b00000000000000000000000000000001;
write_data  = 32'b11111101111111111111111111111111;
mem_read = 1'b0;
mem_write = 1'b0; 
 
clk = ~clk; #20; 
end

initial begin

$monitor("MEMADRESS: %p----WRITEDATA: %p\nMEMREAD: %p--MEMWRITE: %p------READDATA: %p\n AAA: %p \n",mem_address,write_data,mem_read,mem_write,read_data,test.data_mem[mem_address]);

end

endmodule