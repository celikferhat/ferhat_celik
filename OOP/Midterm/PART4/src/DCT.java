import java.text.DecimalFormat;
import java.util.ArrayList;

public class DCT extends CalculateSum{
    /**
     * only complete the transform method
     */
    @Override
    void transform() {
        int N = inputNumbers.size();
        DecimalFormat df = new DecimalFormat("#.##");
        for(int k = 0 ; N > k ; k++ ){
            double sum = 0.;
            double s = (k == 0) ? Math.sqrt(.5) : 1.;
            for(int i = 0 ; N > i ; i++){
                double degrees = (Math.PI * (i + .5) * k / N);
                sum += s * inputNumbers.get(i) * Math.cos(degrees);
            }
            output.add(df.format(sum * Math.sqrt(2. / N)) );
        }

    }
}
