library verilog;
use verilog.vl_types.all;
entity alu32 is
    port(
        r               : out    vl_logic_vector(31 downto 0);
        v               : out    vl_logic;
        znot            : out    vl_logic;
        carry_out       : out    vl_logic;
        a               : in     vl_logic_vector(31 downto 0);
        b               : in     vl_logic_vector(31 downto 0);
        op              : in     vl_logic_vector(2 downto 0);
        all_op          : in     vl_logic_vector(5 downto 0)
    );
end alu32;
