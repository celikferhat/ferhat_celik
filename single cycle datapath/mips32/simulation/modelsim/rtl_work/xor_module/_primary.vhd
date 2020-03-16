library verilog;
use verilog.vl_types.all;
entity xor_module is
    port(
        r               : out    vl_logic;
        a               : in     vl_logic;
        b               : in     vl_logic
    );
end xor_module;
