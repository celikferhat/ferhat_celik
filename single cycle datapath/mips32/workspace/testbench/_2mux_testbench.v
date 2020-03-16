`define DELAY 20
module _2mux_testbench(); 
reg  select;
reg [15:0] a ; 

wire [31:0] out1,out2,out3;

sign_extender s1(a,out1);
zero_extender s2(a,out2);

sign_zero_mux s3(out3,out1,out2,select);

initial begin
a = 16'b1111111111111111;  select = 1'b0;
#`DELAY;
a = 16'b1111111111111111;  select = 1'b1;

end
 
 
initial
begin
$monitor("time = %2d,out=%b, select=%1b", $time,  out3,  select);
end
 
endmodule