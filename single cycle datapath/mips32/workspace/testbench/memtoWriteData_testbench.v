`define DELAY 20
module memtoWriteData_testbench();
reg [31:0] a;
reg [15:0] b;
reg [1:0] select;
wire [31:0] s,out;

mux_for_memtoWriteData m0(out,a,b,1'b1);
memtoWriteData m1(s,out,select);

initial begin


a = 32'b11111111111111111111111111111111; b= 16'b1111111111111111; select = 2'b11;
end
 
 
initial
begin
$monitor("time = %2d, out=%b,muxout=%b", $time, s,out);
end
 
endmodule