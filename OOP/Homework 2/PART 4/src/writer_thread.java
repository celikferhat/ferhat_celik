/**
 * Implemented for read write testing
 */
public class writer_thread implements Runnable{
    ITable table;
    public writer_thread(ITable table) {
        this.table = table;
    }
    /**
     * Calls setElementAt
     */
    @Override
    public void run() {
        table.setElementAt(0,0,null);
    }
}
