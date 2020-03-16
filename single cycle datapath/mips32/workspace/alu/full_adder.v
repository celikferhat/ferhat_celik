module full_adder(sum, carry_out, a, b, carry_in);

input a, b, carry_in;
output sum, carry_out;

wire first_sum, first_carry_out, second_carry_out;

half_adder first(first_sum, first_carry_out, a, b);
half_adder second(sum, second_carry_out, first_sum, carry_in);

or (carry_out, second_carry_out, first_carry_out);

endmodule