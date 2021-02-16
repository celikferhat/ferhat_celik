public class Main {
    public static void main(String [] args){
        Integer[][] a = {
                {1, 2,  3,   4},
                {5, 6,  7,   8},
                {9, 10, 11 , 12},
                {13, 14, 15 ,16}
        };

        System.out.println("Printing 2D Array\n");
        for (Integer[] integers : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(integers[j] + " ");
            }
            System.out.println();

        }
        System.out.println("\nPrinting 2D Array with iterator");
       SatalliteIterator si = new SatalliteIterator(a);
       while (si.hasNext())
           System.out.print(si.next() + ", ");



    }
}
