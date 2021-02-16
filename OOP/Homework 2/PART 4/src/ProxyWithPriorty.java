import static java.lang.Thread.sleep;

/**
 * My second proxy class using my priorwritelock class
 */
public class ProxyWithPriorty implements ITable{
    /**
     * real class
     */
    private ITable table = new DataBaseTable();
    /**
     * prior read write lock
     */
    private PrioriWriteLock pr = new PrioriWriteLock();

    /**
     * Same as proxytable
     * @param row gets row
     * @param column gets column
     * @return returns and object
     */
    @Override
    public Object getElementAt(int row, int column) {

        try {
            pr.ReadLock();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to read lock");
        }
        try{

            System.out.println("start proxy get");
            Object o = table.getElementAt(row,column);
            System.out.println("end proxy get\n");
            return o;
        }catch (Exception e){

        }finally {
            pr.ReadUnlock();
        }

        return null;
    }

    /**
     * Same as proxytable but if any writer thread waits for locking it priotize the writer.
     * @param row gets row
     * @param column gets column
     * @param o new object for write operation
     */
    @Override
    public  void setElementAt(int row, int column, Object o) {

        try {
            pr.WriteLock();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Failed to write lock");
        }
        try {
           // sleep(50);
            System.out.println("start proxy set");
            table.setElementAt(row,column,o);
            System.out.println("end proxy set\n");
        }catch (Exception e){

        }finally {
            try {
                pr.WriteUnlock();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Failed to write unlock");
            }
        }


    }
    /**
     * There is no problem of this method only returns row count
     * @return row count
     */
    @Override
    public int getNumberOfRows() {
        return table.getNumberOfRows();
    }
    /**
     * There is no problem of this method only returns column count
     * @return column count
     */
    @Override
    public int getNumberOfColumns() {
        return table.getNumberOfColumns();
    }
}
