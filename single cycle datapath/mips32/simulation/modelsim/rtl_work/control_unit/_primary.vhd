library verilog;
use verilog.vl_types.all;
entity control_unit is
    port(
        opcode          : in     vl_logic_vector(5 downto 0);
        funct           : in     vl_logic_vector(5 downto 0);
        memRead         : out    vl_logic;
        memWrite        : out    vl_logic;
        signal_regWrite : out    vl_logic;
        zero_or_sign    : in     vl_logic;
        load_select     : out    vl_logic_vector(1 downto 0);
        store_signal    : out    vl_logic_vector(1 downto 0);
        lui_or_other    : out    vl_logic;
        alusrc          : out    vl_logic;
        write_select    : out    vl_logic;
        jump_signal     : out    vl_logic;
        branch_signal   : out    vl_logic;
        frht_signal     : out    vl_logic
    );
end control_unit;
