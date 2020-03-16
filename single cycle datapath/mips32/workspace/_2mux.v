module _2mux(out,a,b, select);
input a,b, select;
output out;
wire out1, out2, select1;

not n1(select1, select);
and a1(out1, a, select1);
and a2(out2, b, select);
or o1(out, out2, out1);

endmodule



