package com.javanajautc.theba.java_naja;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class chap8_4Activity extends AppCompatActivity {

    float x1, y1;
    float x2, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap8_4);

        TextView CopHead = (TextView) findViewById(R.id.Header);
        CopHead.setTextIsSelectable(true);

        TextView CopBODY = (TextView) findViewById(R.id.Body1);
        CopBODY.setTextIsSelectable(true);

        TextView CopBODY2 = (TextView) findViewById(R.id.Body2);
        CopBODY2.setTextIsSelectable(true);

        TextView CopBODY3 = (TextView) findViewById(R.id.Body3);
        CopBODY3.setTextIsSelectable(true);

        TextView CopBODY4 = (TextView) findViewById(R.id.Body4);
        CopBODY4.setTextIsSelectable(true);

        TextView CopBODY5 = (TextView) findViewById(R.id.Body5);
        CopBODY5.setTextIsSelectable(true);

        TextView CopBODY6 = (TextView) findViewById(R.id.Body6);
        CopBODY6.setTextIsSelectable(true);

        TextView CopBODY7 = (TextView) findViewById(R.id.Body7);
        CopBODY7.setTextIsSelectable(true);

        TextView CopBODY8 = (TextView) findViewById(R.id.Body8);
        CopBODY8.setTextIsSelectable(true);

        TextView CopBODY9 = (TextView) findViewById(R.id.Body9);
        CopBODY9.setTextIsSelectable(true);

        TextView CopBODY10 = (TextView) findViewById(R.id.Body10);
        CopBODY10.setTextIsSelectable(true);

        TextView CopBODY11 = (TextView) findViewById(R.id.Body11);
        CopBODY11.setTextIsSelectable(true);

        TextView CopBODY12 = (TextView) findViewById(R.id.Body12);
        CopBODY12.setTextIsSelectable(true);

        TextView CopBODY13 = (TextView) findViewById(R.id.Body13);
        CopBODY13.setTextIsSelectable(true);

        TextView CopBODY14 = (TextView) findViewById(R.id.Body14);
        CopBODY14.setTextIsSelectable(true);

        TextView CopBODY15 = (TextView) findViewById(R.id.Body15);
        CopBODY15.setTextIsSelectable(true);

        TextView CopBODY16 = (TextView) findViewById(R.id.Body16);
        CopBODY16.setTextIsSelectable(true);

        TextView CopBODY17 = (TextView) findViewById(R.id.Body17);
        CopBODY17.setTextIsSelectable(true);

        TextView CopBODY18 = (TextView) findViewById(R.id.Body18);
        CopBODY18.setTextIsSelectable(true);

        TextView CopBODY19 = (TextView) findViewById(R.id.Body19);
        CopBODY19.setTextIsSelectable(true);

        TextView CopBODY20 = (TextView) findViewById(R.id.Body20);
        CopBODY20.setTextIsSelectable(true);

        Button Nextpage = (Button) findViewById(R.id.Next_Button);
        Nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Next = new Intent(chap8_4Activity.this, Chap8_5Activity.class);
                startActivity(Next);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00000000")));

    }

    @Override
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
// when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                x2 = touchevent.getX();
                y2 = touchevent.getY();
//if left to right sweep event on screen
                if (x1 < x2) {
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(chap8_4Activity.this, Chap8_3Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
// if right to left sweep event on screen
                if (x1 > x2) {
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(chap8_4Activity.this, Chap8_5Activity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
// if UP to Down sweep event on screen
                if (y1 < y2) {
//Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_LONG).show();
                }
//if Down to UP sweep event on screen
                if (y1 > y2) {
// Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //do whatever
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}