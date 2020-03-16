library verilog;
use verilog.vl_types.all;
entity \_1bit_alu\ is
    port(
        r               : out    vl_logic;
        c_out           : out    vl_logic;
        a               : in     vl_logic;
        b               : in     vl_logic;
        less            : in     vl_logic;
        c_in            : in     vl_logic;
        op              : in     vl_logic_vector(2 downto 0)
    );
end \_1bit_alu\;
