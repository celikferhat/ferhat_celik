module _32bit_4mux(out, a,b,c,d, select0, select1);
input [31:0] a,b,c,d;
input select0, select1;
output [31:0] out;
wire [31:0] out1, out2;

_32bit_2mux mx1(out1, a,b,select0);
_32bit_2mux mx2(out2, c,d,select0);
_32bit_2mux mx3(out, out1, out2, select1);

endmodule
