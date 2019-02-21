package connect4.connect4;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.Spinner;
import java.util.ArrayList;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static boolean computer = false;
    private String[] boardSize={"5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40"};
    public static Spinner spinner1;
    private ArrayAdapter<String> dataAdapter;
    private Button playerButton;
    private Button computerButton;
    public static Draw draw;
    private ScrollView scrollView;
    private HorizontalScrollView horizontalScrollView;
    private Integer p = 1;
    public static Integer spinsize;
    private TextView poptext;
    private float[] coordinates = new float[2];





    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        spinner1 = (Spinner) findViewById(R.id.sizeselect);
        dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,boardSize);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        playerButton = (Button) findViewById(R.id.button2);
        computerButton = (Button) findViewById(R.id.button3);



        draw = new Draw(this);
        draw.setBackgroundColor(Color.GRAY);
        scrollView = new ScrollView(this);
        horizontalScrollView = new HorizontalScrollView(this);









        playerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = MainActivity.spinner1.getSelectedItem().toString();
                spinsize= Integer.valueOf(s);




                computer = false;


                horizontalScrollView.addView(draw);
                scrollView.addView(horizontalScrollView);

                setContentView(scrollView);



                draw.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {


                        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                            coordinates[0] = motionEvent.getX();
                            coordinates[1] = motionEvent.getY();










                        }

                        return false;
                    }
                });

                draw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        float x = coordinates[0];
                        float y = coordinates[1];



                        String s = MainActivity.spinner1.getSelectedItem().toString();
                        Integer sizee = Integer.valueOf(s);

                        for(int i = 0 ; sizee >i;i++)
                        {

                            if(y > 400)
                            {
                                if(x > 30+(i*130) && x<150+(i*130))
                                {

                                    if(!draw.r1.isColumnfull(i))
                                    {

                                        //whowin.setText("asdasd");

                                        draw.r1.ata(i,p);
                                        draw.invalidate();
                                        p = (p % 2)+1;
                                        draw.color=p;
                                        if(draw.r1.win == true){

                                            if(p == 1) {
                                                startActivity(new Intent(MainActivity.this, popup.class));
                                                draw.wonblack++;
                                            }


                                            if(p == 2) {
                                                startActivity(new Intent(MainActivity.this, popup2.class));
                                                draw.wonwhite++;
                                            }

                                            draw.r1.win = false;

                                        }
                                        if(draw.r1.fullBoard == true)
                                        {
                                            startActivity(new Intent(MainActivity.this, popupdraw.class));
                                        }







                                    }




                                }
                            }

                        }

                    }
                });




            }
        });


        computerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                computer = true;
                setContentView(R.layout.layout);



                horizontalScrollView.addView(draw);
                scrollView.addView(horizontalScrollView);

                setContentView(scrollView);


                draw.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {


                        if (motionEvent.getActionMasked() == MotionEvent.ACTION_DOWN) {
                            coordinates[0] = motionEvent.getX();
                            coordinates[1] = motionEvent.getY();










                        }

                        return false;
                    }
                });


                draw.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        float x = coordinates[0];
                        float y = coordinates[1];



                        String s = MainActivity.spinner1.getSelectedItem().toString();
                        Integer sizee = Integer.valueOf(s);

                        for(int i = 0 ; sizee >i;i++)
                        {

                            if(y > 400)
                            {
                                if(x > 30+(i*130) && x<150+(i*130))
                                {

                                    if(!draw.r1.isColumnfull(i))
                                    {

                                        //whowin.setText("asdasd");

                                        draw.r1.ata(i,2);
                                        draw.invalidate();

                                        draw.color=1;

                                        if(draw.r1.win == true){

                                            if(draw.r1.computerwin == true) {
                                                startActivity(new Intent(MainActivity.this, popup.class));
                                                draw.wonblack++;

                                            }


                                            if(draw.r1.playerwin == true) {
                                                startActivity(new Intent(MainActivity.this, popup2.class));
                                                draw.wonwhite++;

                                            }

                                            draw.r1.win = false;

                                        }
                                        if(draw.r1.fullBoard)
                                        {
                                            startActivity(new Intent(MainActivity.this, popupdraw.class));
                                            draw.r1.fullBoard=false;
                                        }







                                    }




                                }
                            }

                        }

                    }
                });






            }
        });




    }













}
