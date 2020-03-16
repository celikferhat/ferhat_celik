library verilog;
use verilog.vl_types.all;
entity data_memory is
    port(
        data            : out    vl_logic_vector(31 downto 0);
        address         : in     vl_logic_vector(31 downto 0);
        write_data      : in     vl_logic_vector(31 downto 0);
        memWrite        : in     vl_logic;
        memRead         : in     vl_logic;
        store_signal    : in     vl_logic_vector(1 downto 0);
        clk             : in     vl_logic
    );
end data_memory;
