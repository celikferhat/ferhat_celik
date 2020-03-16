`define DELAY 20
module sign_extender_testbench(); 
reg [15:0] a;

wire [31:0] result;


sign_extender s1(a,result);

initial begin
a = 16'b0000000000000001; 

#`DELAY;
a = 16'b1000000000000001; 

end
 
 
initial
begin
$monitor("time = %2d, a =%b,  result=%b", $time, a, result);
end
 
endmodule