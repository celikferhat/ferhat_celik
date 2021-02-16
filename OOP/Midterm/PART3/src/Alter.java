public class Alter implements Command {
    /**
     * Alter command , changes column names.
     */
    Database local;
    String newName;
    String prevName;
    int cid ;

    /**
     *
     * @param local Takes database
     * @param cid   Takes column id
     * @param newName   Takes new column name
     */
    public Alter(Database local,int cid,String newName) {
        this.local = local;
        this.cid = cid;
        this.newName = newName;
    }

    /**
     * Changes selected column name.
     * @return Operation result
     */
    @Override
    public boolean execute() {
        prevName = local.getCName(cid);
        return local.ChangeColumnName(cid,newName);
    }

    /**
     * Reassign previous coloumn name
     */
    @Override
    public void undo() {
        local.ChangeColumnName(cid,prevName);
    }
}
