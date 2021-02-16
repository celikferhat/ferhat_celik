package ModelandStores;

import java.util.Scanner;

public class Main {


    static void order(PhoneStore store,int i){
    if(i == 1){
        store.orderPhone("MaximumEfford");
    }else if(i == 2){
        store.orderPhone("IflasDeluxe");
    }
    else if(i == 3){
        store.orderPhone("I_I_Aman_Iflas");
    }
    }


    public static void main(String [] args){

        PhoneStore phoneStore ;
        System.out.println("Select Phone Store\n1-Turkey Phone Store\n2-Eu Phone Store\n3-Global Phone Store");
        int i;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
       if(i == 1){
           System.out.println("Select Phone Model\n1-MaximumEfford\n2-IflasDeluxe\n3-I_I_Aman_Iflas");
           int j = sc.nextInt();
           phoneStore = new TurkeyPhoneStore();
           order(phoneStore,j);

       }else if(i == 2){
           System.out.println("Select Phone Model\n1-MaximumEfford\n2-IflasDeluxe\n3-I_I_Aman_Iflas");
           phoneStore = new EuPhoneStore();
           int j = sc.nextInt();
           order(phoneStore,j);

       }else if(i == 3){
           System.out.println("Select Phone Model\n1-MaximumEfford\n2-IflasDeluxe\n3-I_I_Aman_Iflas");
           phoneStore = new GlobalPhoneStore();
           int j = sc.nextInt();
           order(phoneStore,j);

       }

    }

}
