`define DELAY 20
module _1bit_alu_testbench(); 

reg a, b, c_in ;

reg [2:0] op;

wire less , c_out , out;


_1bit_alu a1(out, c_out, a, b, less, c_in , op);

initial begin
a = 1'b0; b = 1'b0; c_in = 1'b0; op = 3'b000;
#`DELAY;
a = 1'b0; b = 1'b0; c_in = 1'b0; op = 3'b001; 
#`DELAY;
a = 1'b0; b = 1'b0; c_in = 1'b0; op = 3'b010; 
#`DELAY;
a = 1'b0; b = 1'b0; c_in = 1'b0; op = 3'b110; 
#`DELAY;
a = 1'b0; b = 1'b0; c_in = 1'b0; op = 3'b111; 
#`DELAY;
a = 1'b0; b = 1'b1; c_in = 1'b0; op = 3'b000; 
#`DELAY;
a = 1'b0; b = 1'b1; c_in = 1'b0; op = 3'b001; 
#`DELAY;
a = 1'b0; b = 1'b1; c_in = 1'b0; op = 3'b010; 
#`DELAY;
a = 1'b0; b = 1'b1; c_in = 1'b0; op = 3'b110; 
#`DELAY;
a = 1'b0; b = 1'b1; c_in = 1'b0; op = 3'b111; 
#`DELAY;
a = 1'b1; b = 1'b0; c_in = 1'b0; op = 3'b000; 
#`DELAY;
a = 1'b1; b = 1'b0; c_in = 1'b0; op = 3'b001; 
#`DELAY;
a = 1'b1; b = 1'b0; c_in = 1'b0; op = 3'b010; 
#`DELAY;
a = 1'b1; b = 1'b0; c_in = 1'b0; op = 3'b110; 
#`DELAY;
a = 1'b1; b = 1'b0; c_in = 1'b0; op = 3'b111; 
#`DELAY;
a = 1'b1; b = 1'b1; c_in = 1'b0; op = 3'b000; 
#`DELAY;
a = 1'b1; b = 1'b1; c_in = 1'b0; op = 3'b001; 
#`DELAY;
a = 1'b1; b = 1'b1; c_in = 1'b0; op = 3'b010; 
#`DELAY;
a = 1'b1; b = 1'b1; c_in = 1'b0; op = 3'b110; 
#`DELAY;
a = 1'b1; b = 1'b1; c_in = 1'b0; op = 3'b111; 
end
 
 
initial
begin
$monitor("time = %2d, a =%1b, b=%1b, c_in=%1b,  c_out=%1b ,  out=%1b , op=%3b", $time, a, b, c_in, c_out , out  , op);
end
 
endmodule