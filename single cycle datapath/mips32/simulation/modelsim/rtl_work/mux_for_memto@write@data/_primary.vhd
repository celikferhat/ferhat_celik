library verilog;
use verilog.vl_types.all;
entity mux_for_memtoWriteData is
    port(
        \out\           : out    vl_logic_vector(31 downto 0);
        a               : in     vl_logic_vector(31 downto 0);
        b               : in     vl_logic_vector(15 downto 0);
        \select\        : in     vl_logic
    );
end mux_for_memtoWriteData;