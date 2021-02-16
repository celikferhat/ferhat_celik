import java.util.Scanner;

public class Main {
    public static void main(String [] args){

        if(args.length > 0){
            System.out.println("1 for DCT\n2 for DFT\nChoose:");
            Scanner sc = new Scanner(System.in);
            int ch = sc.nextInt();
            if(ch == 1){
                DCT dct = new DCT();
                dct.Calculate(args[0]);
            }else if(ch == 2){
                System.out.println("Would you like to see time of execution ? \n 1 for Yes \n 2 for No \nChoose:");
                DFT dft = new DFT();
                int sch = sc.nextInt();
                if(sch == 1)
                    dft.setPt(true);
                else if(sch != 2)
                    System.out.println("Wrong Choose.Setted false");


                dft.Calculate(args[0]);
            }else{
                System.out.println("Wrong Choose!");
            }




        }
        else {
            System.out.println("Filename should must given as parameter!");
        }

    }
}
