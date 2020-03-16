library verilog;
use verilog.vl_types.all;
entity memtoWriteData is
    port(
        \out\           : out    vl_logic_vector(31 downto 0);
        \in\            : in     vl_logic_vector(31 downto 0);
        load_select     : in     vl_logic_vector(1 downto 0)
    );
end memtoWriteData;
