
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Templete method design pattern
 */
public abstract class CalculateSum {
    /**
     * Input numbers
     */
    ArrayList<Integer> inputNumbers;
    /**
     * Output numbers
     */
    ArrayList<String> output;

    /**
     *  Calculates the 1D transform
     * @param filename input file name
     */
    final void Calculate(String filename){
        long startTime = System.nanoTime();
        inputNumbers = new ArrayList<Integer>();
        output = new ArrayList<String>();
        getNumbers(filename);
        transform();
        writeOutput();
        long stopTime = System.nanoTime();
        if(printTime())
            System.out.println("Execution Time : " + (stopTime - startTime) );
    }

    /**
     * Takes numbers from file
     * @param filename input file name
     */
    public void getNumbers(String filename){
        Path filePath = Paths.get(filename);
        try{
        Scanner scanner = new Scanner(filePath);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    inputNumbers.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    /**
     * Calculate method should write this method
     */
    abstract void transform();

    /**
     * output numbers to file named 'output.txt'
     */
    public void writeOutput(){
        try{
        FileWriter writer = new FileWriter("output.txt");
        for(String str: output) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

    /**
     *
     * @return print choose
     */
    public boolean printTime(){
        return false;
    }


}
