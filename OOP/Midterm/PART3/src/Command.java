public interface Command {
    /**
     *
     * @return Execute returns operation results.So if operation fail ,I can undo all operations which worked before this operation.
     */
    public boolean execute();

    /**
     * Undo for rollback
     */
    public void undo();
}
