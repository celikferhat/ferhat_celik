import java.text.DecimalFormat;

public class DFT extends CalculateSum{
    boolean pt = false;

    /**
     *
     * @param pt if true prints executed time
     */
    public void setPt(boolean pt) {
        this.pt = pt;
    }

    /**
     *
     * @return Print choose
     */
    @Override
    public boolean printTime() {
        return pt;
    }

    /**
     * only complete the transform method
     */
    @Override
    void transform() {
        int N = inputNumbers.size();
        DecimalFormat df = new DecimalFormat("#.##");
        for(int k = 0 ; N > k ; k++ ){
            Double sumr = 0.;
            Double sumi = 0.;
            for(int i = 0 ; N > i ; i++){
                double degrees = (Math.PI * 2 * k * i / N);
                sumr += inputNumbers.get(i) * Math.cos(degrees) + 0 * Math.sin(degrees);
                sumi += -1 * inputNumbers.get(i) * Math.sin(degrees) + 0 * Math.cos(degrees);
            }
            output.add(df.format(sumr) +"+("+ df.format(sumi) +")j"  );

        }



    }
}
