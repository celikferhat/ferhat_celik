module sign_zero_mux(result, sign_extend, zero_extend, signal);
input [31:0] sign_extend , zero_extend;
input signal;
output [31:0] result;

_32bit_2mux m1(result,sign_extend,zero_extend, signal);





endmodule