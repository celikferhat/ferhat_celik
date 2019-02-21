package connect4.connect4;

/**
 * Created by acer on 27.01.2018.
 */

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



public class Draw extends View {

    public static rules r1 = new rules();
    public Integer wonwhite=0;
    public Integer wonblack=0;
    public Integer color=1;
    public Integer deneme=0;
    Paint paint = new Paint();



    public Draw(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        String s = MainActivity.spinner1.getSelectedItem().toString();
        Integer siz = Integer.valueOf(s);
        r1.size=siz;
        r1.zero();
        siz=siz*150;
        int width = 500+ siz;//(size*3)/5;//MeasureSpec.getSize(widthMeasureSpec);
        int height = 1000+siz;//(size*9)/10; // Since 3000 is bottom of last Rect to be drawn added and 50 for padding.
        setMeasuredDimension(width, height);
    }

    @Override
    public void onDraw(Canvas canvas) {



       paint.setColor(Color.WHITE);

       canvas.drawRect(30,50,120,70,paint);
       canvas.drawRect(30,50,50,120,paint);
       canvas.drawRect(30,120,120,140,paint);
        canvas.drawRect(100,120,120,190,paint);
        canvas.drawRect(30,190,120,210,paint);

        canvas.drawRect(140,50,230,70,paint);
        canvas.drawRect(140,50,160,210,paint);
        canvas.drawRect(140,190,230,210,paint);


        canvas.drawRect(250,50,340,70,paint);
        canvas.drawRect(250,50,270,210,paint);
        canvas.drawRect(250,190,340,210,paint);
        canvas.drawRect(320,50,340,210,paint);


        canvas.drawRect(360,50,380,210,paint);
        canvas.drawRect(360,50,430,70,paint);
        canvas.drawRect(430,50,450,130,paint);
        canvas.drawRect(360,110,450,130,paint);
        canvas.drawRect(360,130,400,160,paint);
        canvas.drawRect(400,160,430,190,paint);
        canvas.drawRect(430,190,450,210,paint);


        canvas.drawRect(470,50,560,70,paint);
        canvas.drawRect(470,50,490,210,paint);
        canvas.drawRect(470,120,560,140,paint);
        canvas.drawRect(470,190,560,210,paint);

        canvas.drawRect(580,50,584,210,paint);


         canvas.drawOval(600,50,670,120,paint);
        paint.setColor(Color.BLACK);
         canvas.drawOval(600,150,670,220,paint);



         paint.setColor(Color.WHITE);
         paint.setTextSize(50);


        canvas.drawText(": "+wonwhite.toString(),680,95,paint);

        canvas.drawText(": "+wonblack.toString(),680,200,paint);



        if(color == 2)
        {   paint.setColor(Color.BLACK);
            canvas.drawRect(30,250,560,280,paint);
        }
        if(color == 1)
        {   paint.setColor(Color.WHITE);
            canvas.drawRect(30,250,560,280,paint);
        }


        if(deneme == 1)
        {canvas.drawRect(30,280,560,500,paint);}


        paint.setColor(Color.GREEN);
        String s = MainActivity.spinner1.getSelectedItem().toString();
        Integer siz = Integer.valueOf(s);



        for (int i = 0; siz> i; i++) {
            for (int j = 0; siz > j; j++) {
                if (r1.array[i][j] == 0)
                    paint.setColor(Color.GREEN);
                if (r1.array[i][j] == 1)
                    paint.setColor(Color.WHITE);
                if (r1.array[i][j] == 2)
                    paint.setColor(Color.BLACK);

                canvas.drawOval(30 + (j * 130 ), 400  + (i * 130), 150 + (j * 130 ), 520 + (i * 130), paint);


            }
        }







    }
}
