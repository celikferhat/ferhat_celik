module _2mux(out,a,b, select);
input a,b, select;
output out;
wire out1, out2, select1;

not (select1, select);
and (out1, a, select1);
and (out2, b, select);
or (out, out2, out1);

endmodule