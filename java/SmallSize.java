package dim.kinders.DogsPooGolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SmallSize extends AppCompatActivity {
    Array arraySmall = new Array();

    @Override
    public void onCreate(Bundle savedInstanceState) {

//--------- zero for breed size - start
        for (int i = 0; i<Array.arrPlayersSum.length; i++) {
            Array.arrPlayersSum[i] = 0;
        }
//--------- zero for breed size - start

        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_for_size);

        //variable text_sizes_uni "Dog size:"
        TextView text_sizes_uni = findViewById(R.id.text_sizes_uni);
        text_sizes_uni.setText(R.string.sizesmall); //set the text for dog size for Small size

        //variable text_left for Small Dog size
        final TextView text_left = findViewById(R.id.text_left);
        text_left.setText(R.string.smalldogone); // Small Dog one

        //variable text_leftbut for Small Dog size
        TextView text_leftbut = findViewById(R.id.text_leftbut);
        text_leftbut.setText(R.string.smalldogtwo); // Small Dog two

        //variable text_right for Small Dog size
        TextView text_right = findViewById(R.id.text_right);
        text_right.setText(R.string.smalldogthree); // Small Dog three

        //variable text_right for Small Dog size
        TextView text_rightbut = findViewById(R.id.text_rightbut);
        text_rightbut.setText(R.string.smalldogfour); // Small Dog four

        final ImageView img_left = (ImageView)findViewById(R.id.img_left);
        // making corners not sharp
        img_left.setClipToOutline(true);

        final ImageView img_right = (ImageView)findViewById(R.id.img_right);
        // making corners not sharp
        img_right.setClipToOutline(true);

                final ImageView img_leftbut = (ImageView)findViewById(R.id.img_leftbut);
        // making corners not sharp
       img_leftbut.setClipToOutline(true);

                final ImageView img_rightbut = (ImageView)findViewById(R.id.img_rightbut);
       // making corners not sharp
       img_rightbut.setClipToOutline(true);

        //hall screen window for game - no String power and time
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        img_left.setImageResource(arraySmall.arrSmallDogs[0]);
        img_right.setImageResource(arraySmall.arrSmallDogs[1]);
        img_leftbut.setImageResource(arraySmall.arrSmallDogs[2]);
        img_rightbut.setImageResource(arraySmall.arrSmallDogs[3]);

//switch animation - start
        final Animation a = AnimationUtils.loadAnimation(SmallSize.this, R.anim.alpha);
//switch animation - finish

        //set chosen dog img STANDART BLOCK
        img_left.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                  img_left.setImageResource(arraySmall.arrSmallDogs[4]);
                  img_leftbut.setEnabled(false);
                  img_right.setEnabled(false);
                  img_rightbut.setEnabled(false);

                    img_left.setEnabled(false); //unable to push the left top img button again
                    arraySmall.arrPlayersSum[0] = 31;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                   }
                //touch the img - finish
                return true;
            }

        });

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setImageResource(arraySmall.arrSmallDogs[5]);
                    img_leftbut.setEnabled(false);
                    img_left.setEnabled(false);
                    img_rightbut.setEnabled(false);

                    img_right.setEnabled(false);
                    arraySmall.arrPlayersSum[0] = 32;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                //touch the img - finish
                return true;
            }
        });

        img_leftbut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_leftbut.setImageResource(arraySmall.arrSmallDogs[6]);
                    img_left.setEnabled(false);
                    img_right.setEnabled(false);
                    img_rightbut.setEnabled(false);

                    img_leftbut.setEnabled(false);
                    arraySmall.arrPlayersSum[0] = 33;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                //touch the img - finish
                return true;
            }
        });

        img_rightbut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_rightbut.setImageResource(arraySmall.arrSmallDogs[7]);
                    img_leftbut.setEnabled(false);
                    img_right.setEnabled(false);
                    img_left.setEnabled(false);

                    img_rightbut.setEnabled(false);
                    arraySmall.arrPlayersSum[0] = 34;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                //touch the img - finish
                return true;
            }
        });

        Button btnContinue = (Button)findViewById(R.id.btnContinue);
//        //code if you push the btnContinue -> to the
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(summArray(Array.arrPlayersSum) > 0 ) {
                        Intent intent = new Intent(SmallSize.this, Food.class);
                        startActivity(intent);
                        finish();
                    }
                } catch(Exception e) {

                }
            }
        });

        //button Back -> to the previous Activity
        Button button_back = (Button)findViewById(R.id.button_back_uni);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                try {
                    Intent intent = new Intent(SmallSize.this, Size.class);
                    startActivity(intent); // open a new window
                    finish();              // close the old one
                }catch(Exception e) {

                }
            }
        });
    }

    static int summArray (int arr[]){
        int summ=0;
        for (int i = 0; i <arr.length ; i++) {
            summ = summ + arr[i];
        }
        return summ;
    }

    //System button "Back" - start
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(SmallSize.this, Size.class);
            startActivity(intent); // open a new window
            finish();              // close the old one
        }catch(Exception e) {

        }
    }
    //System button "Back" - finish

}
