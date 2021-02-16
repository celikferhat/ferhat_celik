import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String [] args){
       /* Database database = new Database();
        EFT eft = new EFT(1,2,300,database);
        database.PrintDatabse();
        eft.execute();
        database.PrintDatabse();
        eft.undo();
        database.PrintDatabse();
        Command command = new Alter(database,2,"test");
        command.execute();
        database.PrintDatabse();
        command.undo();
        database.PrintDatabse();*/
        Database database = new Database();
        Stack<Command> stack = new Stack<>();
        while(true) {
            System.out.println("1- EFT \n2- ALTER column name\n3- Undo operation\n4- Exit");
            int i;
            Scanner sc = new Scanner(System.in);
            i = sc.nextInt();
            if (i == 1) {
                database.PrintDatabse();
                System.out.println("Enter sender and receiver account id and amount of money (Exp. 1 2 560)");
                int fr, to, amount;
                fr = sc.nextInt();
                to = sc.nextInt();
                amount = sc.nextInt();
                Command command = new EFT(fr, to, amount, database);
                if(!command.execute()){
                    System.out.println("Somethink is wrong! All EFT operations have been undone.");
                    command.undo();
                }

                database.PrintDatabse();
                stack.push(command);
            }
            else if(i == 2){
                System.out.println("Enter column id and new column name (Exp. 2 test)");
                int cid;String newname;
                cid = sc.nextInt();
                newname = sc.nextLine();
                Command command = new Alter(database,cid,newname);
                command.execute();
                database.PrintDatabse();
                stack.push(command);
            }
            else if(i == 3){
                if(stack.size()>0)
                    stack.pop().undo();
                database.PrintDatabse();
            }
            else if(i == 4)
                return;
        }

    }
}
