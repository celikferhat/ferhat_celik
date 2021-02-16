import java.util.ArrayList;
import java.util.List;

public class Database {
    /**
     * Database for making operations
     */
    ArrayList<String>  ColumnNames = new ArrayList<String>(3);
    String[] People = {"Ahmet","Ayşe","Mehmet"};
    Integer[][] Table = {   {1,24,3000} ,
                            {2,35,5500},
                            {3,18,800}};

    int SelectedId=-1;


    public Database() {
        ColumnNames.add("ID");
        ColumnNames.add("Age");
        ColumnNames.add("Balance");
    }

    /**
     *
     * @return Selected account id
     */
    public int getSelectedId() {
        return SelectedId;
    }

    /**
     * Sets selected account id
     * @param selectedId Gets account id for parameter
     * @return returns operation result
     */
    public boolean setSelectedId(int selectedId) {
        if(selectedId < 1 || selectedId > 3 )
            return false;
        SelectedId = selectedId;
        return true;
    }

    /**
     *  This method decreases the balance which selected account
     * @param amount Money amount
     * @return  Returns operation result
     */
    public boolean DecreaseBalance(int amount){
        if(Table[getSelectedId() - 1][2] - amount >= 0 )
                Table[getSelectedId() - 1][2] = Table[getSelectedId() - 1][2] - amount;
        else
          return false;

        return true;
    }

    /**
     *  This method increases the balance which selected account
     * @param amount Money amount
     * @return  Returns true
     */
    public boolean IncreaseBalance(int amount){
        Table[getSelectedId() - 1][2] = Table[getSelectedId() - 1][2] + amount;
        return true;
    }

    /**
     * This method returns coulmn name that selected column
     * @param cid Column ID
     * @return Returns column name
     */
    public String getCName(int cid){
        return ColumnNames.get(cid - 1);
    }

    /**
     * This method changes column name which selected column
     * @param cid Column ID
     * @param newName New Column Name
     * @return Operation Result
     */
    public boolean ChangeColumnName(int cid,String newName){
        if(cid < 1 || cid > 3)
            return false;
        else{
            ColumnNames.set(cid-1,newName);
        }
        return true;
    }

    /**
     * Prints database
     */
    public void PrintDatabse(){
        System.out.print("           ");
        for(int i = 0 ; i < 3 ; i++)
            System.out.print(ColumnNames.get(i)+"   " );

        for(int i = 0 ; i < 3 ; i++){
            System.out.print(String.format("\n%" + 8 + "s", People[i]));
            for(int j = 0 ; j<3;j++){
                System.out.print("    "+Table[i][j]);
            }
        }
        System.out.println("\n------------------------------------");
    }

}
