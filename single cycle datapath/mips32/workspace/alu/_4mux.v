module _4mux(out , i0 , i1 , i2 , i3 , s0 , s1);
input s1,s0,i3,i2,i1,i0;
output out;

wire mux1_to_mux3 , mux2_to_mux3;


_2mux mux2(mux2_to_mux3,i2,i3,s1);
_2mux mux1(mux1_to_mux3,i0,i1,s1);

_2mux mux3(out,mux1_to_mux3,mux2_to_mux3,s0);




endmodule