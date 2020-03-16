library verilog;
use verilog.vl_types.all;
entity sign_zero_mux is
    port(
        result          : out    vl_logic_vector(31 downto 0);
        sign_extend     : in     vl_logic_vector(31 downto 0);
        zero_extend     : in     vl_logic_vector(31 downto 0);
        \signal\        : in     vl_logic
    );
end sign_zero_mux;
