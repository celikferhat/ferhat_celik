/**
 * Implemented for testing
 * Real class
 */
public class DataBaseTable implements ITable{
    @Override
    public Object getElementAt(int row, int column) {
        System.out.println("real get element");
        return null;
    }

    @Override
    public void setElementAt(int row, int column, Object o) {

        System.out.println("real set element");
    }

    @Override
    public int getNumberOfRows() {
        System.out.println("real get row");
        return 0;
    }

    @Override
    public int getNumberOfColumns() {
        System.out.println("real get column");
        return 0;
    }
}
