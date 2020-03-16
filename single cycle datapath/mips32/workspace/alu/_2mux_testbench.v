`define DELAY 20
module _2mux_testbench(); 
reg a, b, select;
wire out;

_2mux mymux (out, a, b, select);

initial begin
a = 1'b0; b = 1'b0; select = 1'b0;
#`DELAY;
a = 1'b0; b = 1'b0; select = 1'b1;
#`DELAY;
a = 1'b0; b = 1'b1; select= 1'b0;
#`DELAY;
a = 1'b0; b = 1'b1; select = 1'b1;
#`DELAY;
a = 1'b1; b = 1'b0; select = 1'b0;
#`DELAY;
a = 1'b1; b = 1'b0; select = 1'b1;
#`DELAY;
a = 1'b1; b = 1'b1; select = 1'b0;
#`DELAY;
a = 1'b1; b = 1'b1; select = 1'b1;
end
 
 
initial
begin
$monitor("time = %2d, a =%1b, b=%1b,  out=%1b, select=%1b", $time, a, b, out,  select);
end
 
endmodule