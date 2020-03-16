module full_adder(sum, carry_out, a, b, carry_in);

input a,b,carry_in;
output sum,carry_out;
wire first_xor,up_and,down_and;

xor_module x1(first_xor,a,b);
xor_module x2(sum,first_xor,carry_in);

and a1(up_and,carry_in,first_xor);
and a2(down_and,a,b);
or o1(carry_out,up_and,down_and);




endmodule