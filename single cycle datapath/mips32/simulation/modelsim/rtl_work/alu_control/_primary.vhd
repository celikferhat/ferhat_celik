library verilog;
use verilog.vl_types.all;
entity alu_control is
    port(
        aluop           : out    vl_logic_vector(2 downto 0);
        funct           : in     vl_logic_vector(5 downto 0);
        opcode          : in     vl_logic_vector(5 downto 0)
    );
end alu_control;
