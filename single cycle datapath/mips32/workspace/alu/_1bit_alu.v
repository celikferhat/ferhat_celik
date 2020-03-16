module _1bit_alu( r , c_out , a , b ,less , c_in , op );

input a , b , c_in,less;
input [2:0] op;
output r,c_out;

wire xor_sonucu,a1,a1n,a2,a3,a4,a5,a4n,a6; 

xor_module x1(xor_sonucu,b,op[2]);
and (a1,a,xor_sonucu);

not(a1n,a1);

or  (a2,a,xor_sonucu);
and (a3,a1n,a2);
and (a4,a3,c_in);
or  (a5,a3,c_in);
not (a4n,a4);
and (a6,a4n, a5);
or (c_out,a1,a4);

_4mux m1(r,a1,a2,a6,less,op[1],op[0]);



endmodule