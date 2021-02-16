import java.util.Stack;

public class EFT implements Command {
    /**
     * EFT operaion , send money from person to person
     */
    Stack<Command> commands;
    int from_id;
    int to_id;
    int amount;
    Database database;

    /**
     *
     * @param from_id The account the money will be withdrawn from
     * @param to_id   The account the money will be sent to
     * @param amount  Amount of money
     * @param database Database
     */
    public EFT(int from_id, int to_id, int amount , Database database) {
        this.database = database;
        this.from_id = from_id;
        this.to_id = to_id;
        this.amount = amount;
        commands = new Stack<>();
    }

    /**
     * Select from account id , update this account balance , select to account id , update this account balance
     * @return operation result
     */
    @Override
    public boolean execute() {
        Command select = new Select(database,from_id);
        Command update ;
        if(!select.execute())
            return false;
        commands.push(select);
        update = new Update(database,amount,2);
        if(!update.execute())
            return false;
        commands.push(update);
        select = new Select(database,to_id);
        if(!select.execute())
            return false;
        commands.push(select);
        update = new Update(database,amount,1);
        if( !update.execute())
            return false;
        commands.push(update);
        return true;

    }

    /**
     * Undo EFT operation.
     */
    @Override
    public void undo() {

        while(commands.size()>0){
            commands.pop().undo();
        }


    }
}
