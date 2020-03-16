module xor_module(r,a,b);
input a,b;
output r;

wire  anot,bnot ,first_logic , second_logic;



not (anot , a);
not (bnot , b);

and first(first_logic , a , bnot);
and second(second_logic , anot , b);
or result(r,first_logic,second_logic);



endmodule