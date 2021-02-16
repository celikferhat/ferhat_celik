/**
 * Implemented for read write testing
 */
public class reader_thread implements Runnable{
    ITable table;
    public reader_thread(ITable table) {
        this.table = table;
    }

    /**
     * Calls getElementAt
     */
    @Override
    public void run() {
        table.getElementAt(0,0);

    }
}
