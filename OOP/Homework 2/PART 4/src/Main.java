import java.util.ArrayList;

public class Main {
    public static void main(String [] args){
        ITable table = new proxyTable();


        System.out.println("ReentrantReadWriteLock kullanılarak implement edilen proxy pattern");
        System.out.println("Sırasıyla birer birer 10'ar adet read ve write threadleri çalıştırılıyor\n");
        ArrayList<Thread> thread_list = new ArrayList<>();

        for (int i = 0; i < 10 ; i++) {
            Thread t1 = new Thread( new  reader_thread(table));
            Thread t2 = new Thread( new  writer_thread(table));
            thread_list.add(t1);
            thread_list.add(t2);
        }
        for (int i = 0; i < 20 ; i++) {

            thread_list.get(i).start();
        }
        for (int i = 0; i < 20 ; i++) {

            try {
                thread_list.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Write threadleri öncelikli olacak şekilde implement edilen proxy pattern");
        System.out.println("Sırasıyla birer birer 10'ar adet read ve write threadleri çalıştırılıyor\n");
        ITable table2 = new ProxyWithPriorty();
        ArrayList<Thread> thread_list_2 = new ArrayList<>();

        for (int i = 0; i < 10 ; i++) {
            Thread t1 = new Thread( new  reader_thread(table2));
            Thread t2 = new Thread( new  writer_thread(table2));
            thread_list_2.add(t1);
            thread_list_2.add(t2);
        }
        for (int i = 0; i < 20 ; i++) {

            thread_list_2.get(i).start();
        }
        for (int i = 0; i < 20 ; i++) {

            try {
                thread_list_2.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
