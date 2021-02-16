public class Select implements Command {
    /**
     * Select command , selects an ID on database
     */
    Database local;
    int id;
    int prev_id = -1;

    /**
     * Takes database and id and selects an ID on database
     * @param database Takes database object for making select operation on it.
     * @param id for selection id
     */
    public Select(Database database,int id) {
        this.local = database;
        this.id = id;
        this.prev_id = database.getSelectedId();
    }

    /**
     *  Selects an ID on database
     * @return Returns operation result.
     */
    @Override
    public boolean execute() {
        return local.setSelectedId(id);
    }

    /**
     * Reassigns the previously selected id.
     */
    @Override
    public void undo() {
        if(prev_id > 0)
            local.setSelectedId(prev_id);
    }
}
