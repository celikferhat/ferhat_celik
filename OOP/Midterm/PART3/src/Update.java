public class Update implements Command {
    /**
     * Update command , changes balance on database.
     */
    Database local;
    int amount;
    int iord ;

    /**
     *
     * @param local Takes database object
     * @param amount Takes amount
     * @param iord Takes a choose (increase or decrease)
     */
    public Update(Database local , int amount , int iord) {
        this.local = local;
        this.amount = amount;
        this.iord = iord;
    }

    /**
     * It increase or decrese amount of selected id.
     * @return Operation result
     */
    @Override
    public boolean execute() {
        if(iord == 1)
            local.IncreaseBalance(amount);
        else{
            return local.DecreaseBalance(amount);
        }
        return true;
    }

    /**
     * It revoke previously update operation.İf balance increased , it decreases and if balace decreased it increases.
     */
    @Override
    public void undo() {
        if(iord == 1)
            local.DecreaseBalance(amount);
        else
            local.IncreaseBalance(amount);
    }
}
