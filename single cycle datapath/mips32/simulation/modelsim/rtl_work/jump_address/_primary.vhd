library verilog;
use verilog.vl_types.all;
entity jump_address is
    port(
        address         : out    vl_logic_vector(31 downto 0);
        instr           : in     vl_logic_vector(25 downto 0);
        pc              : in     vl_logic_vector(3 downto 0);
        jump_opcode     : in     vl_logic
    );
end jump_address;
