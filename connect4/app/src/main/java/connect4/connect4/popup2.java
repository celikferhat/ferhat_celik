package connect4.connect4;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;

/**
 * Created by acer on 28.01.2018.
 */

public class popup2 extends Activity {

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow2);

        DisplayMetrics d1 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(d1);
        int width =  d1.widthPixels;
        int height =  d1.heightPixels;

        getWindow().setLayout(    (int)(width*.5) , (int)(height*.2)     );


    }
}
