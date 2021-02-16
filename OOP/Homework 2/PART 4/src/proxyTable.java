import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import static java.lang.Thread.sleep;

/**
 * My proxy pattern
 */
public class proxyTable implements ITable{
    /**
     * I use real class for all operations
     */
    private ITable table = new DataBaseTable();
    /**
     * When write lock locked only one writer thread can perform , reader threads can works parallel there is no problem
     */
    private ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * get method uses read lock and threads can only calls when any writer thread does not work.
     * @param row gets row
     * @param column gets column
     * @return returns and object
     */
    @Override
    public Object getElementAt(int row, int column) {

        lock.readLock().lock();
        try{
        //sleep(100);
        System.out.println("start proxy get");
        Object o = table.getElementAt(row,column);
        System.out.println("end proxy get\n");
            return o;
        }catch (Exception e){

        }finally {
            lock.readLock().unlock();
        }

        return null;
    }

    /**
     * set method uses write lock .When set method working any other thread should not work.
     * @param row gets row
     * @param column gets column
     * @param o new object for write operation
     */
    @Override
    public  void setElementAt(int row, int column, Object o) {
        lock.writeLock().lock();
        try{
            System.out.println("start proxy set");
            table.setElementAt(row,column,o);
            System.out.println("end proxy set\n");
        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();
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
