`define DELAY 20
module _4mux_testbench(); 
reg a, b,c,d ,select1,select2;
wire out;

_4mux mymux (out, a, b,c,d ,select1,select2);

initial begin
a = 1'b0; b = 1'b1; c = 1'b1; d = 1'b0; select1 = 1'b0; select2 = 1'b0;
#`DELAY;
a = 1'b0; b = 1'b1; c = 1'b1; d = 1'b0; select1 = 1'b0; select2 = 1'b1;
#`DELAY;
a = 1'b0; b = 1'b1; c = 1'b1; d = 1'b0; select1 = 1'b1; select2 = 1'b0;
#`DELAY;
a = 1'b0; b = 1'b1; c = 1'b1; d = 1'b0; select1 = 1'b1; select2 = 1'b1;
#`DELAY;
a = 1'b1; b = 1'b0; c = 1'b1; d = 1'b0; select1 = 1'b0; select2 = 1'b0;
#`DELAY;
a = 1'b1; b = 1'b0; c = 1'b1; d = 1'b0; select1 = 1'b0; select2 = 1'b1;
#`DELAY;
a = 1'b1; b = 1'b0; c = 1'b1; d = 1'b0; select1 = 1'b1; select2 = 1'b0;
#`DELAY;
a = 1'b1; b = 1'b0; c = 1'b1; d = 1'b0; select1 = 1'b1; select2 = 1'b1;
end
 
 
initial
begin
$monitor("time = %2d, a =%1b, b=%1b,c=%1b,d=%1b,  out=%1b, select1=%1b , select2=%1b", $time, a, b,c,d, out,  select1,select2);
end
 
endmodule