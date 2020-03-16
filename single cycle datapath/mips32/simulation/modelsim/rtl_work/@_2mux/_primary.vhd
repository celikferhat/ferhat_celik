library verilog;
use verilog.vl_types.all;
entity \_2mux\ is
    port(
        \out\           : out    vl_logic;
        a               : in     vl_logic;
        b               : in     vl_logic;
        \select\        : in     vl_logic
    );
end \_2mux\;
