package dim.kinders.DogsPooGolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Water extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.water);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//zero for water--------------
        Array.arrPlayersSum[5] = 0;

        final ImageView imgwater = (ImageView)findViewById(R.id.water);
        final ImageView imgdrinking = (ImageView)findViewById(R.id.imgdrinking);

        final ImageView imgyes = (ImageView)findViewById(R.id.img_yes);
        final ImageView imgno = (ImageView)findViewById(R.id.img_no);

        //set chosen dog img STANDART BLOCK
        imgyes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgyes.setImageResource(R.drawable.img_yeschosen);
                    imgno.setEnabled(false);

                    Array.arrPlayersSum[5] = 1;

                    }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        //set chosen dog img STANDART BLOCK
        imgno.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgno.setImageResource(R.drawable.img_nochosen);
                    imgyes.setEnabled(false);

                    Array.arrPlayersSum[5] = 2;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });


        Button btnContinue = (Button)findViewById(R.id.btncontinuewater);
//        //code if you push the btnContinue -> to the
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(Array.arrPlayersSum[5] > 0) {
                        Intent intent = new Intent(Water.this, Summary.class);
                        startActivity(intent);
                        finish();
                    }
                } catch(Exception e) {
                }
            }
        });

    //button Back -> to the previous Activity
        Button button_back_food = (Button)findViewById(R.id.button_back_water);
        button_back_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                try {
                        Intent intent = new Intent(Water.this, Food.class);
                        startActivity(intent); // open a new window
                        finish();              // close the old one
                }catch(Exception e) {

                }
            }
        });
    }

    //System button "Back" - start
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Water.this, Food.class);
            startActivity(intent); // open a new window
            finish();              // close the old one
        }catch(Exception e) {

        }
    }
    //System button "Back" - finish



}
