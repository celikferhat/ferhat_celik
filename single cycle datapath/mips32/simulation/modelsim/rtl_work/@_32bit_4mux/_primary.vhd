library verilog;
use verilog.vl_types.all;
entity \_32bit_4mux\ is
    port(
        \out\           : out    vl_logic_vector(31 downto 0);
        a               : in     vl_logic_vector(31 downto 0);
        b               : in     vl_logic_vector(31 downto 0);
        c               : in     vl_logic_vector(31 downto 0);
        d               : in     vl_logic_vector(31 downto 0);
        select0         : in     vl_logic;
        select1         : in     vl_logic
    );
end \_32bit_4mux\;
