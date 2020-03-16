library verilog;
use verilog.vl_types.all;
entity next_pc is
    port(
        pc              : in     vl_logic_vector(31 downto 0);
        nextPc          : out    vl_logic_vector(31 downto 0);
        jump            : in     vl_logic;
        branch_signal   : in     vl_logic;
        frht_signal     : in     vl_logic;
        clock           : in     vl_logic
    );
end next_pc;
