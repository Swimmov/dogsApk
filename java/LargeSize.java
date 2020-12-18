package dim.kinders.DogsPooGolf;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LargeSize extends AppCompatActivity {
    Array arrayLarge = new Array();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//--------- zero for breed size - start
        for (int i = 0; i<Array.arrPlayersSum.length; i++) {
            Array.arrPlayersSum[i] = 0;
        }
//--------- zero for breed size - start

        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_for_size);

        //variable text_sizes_uni "Dog size:"
        TextView text_sizes_uni = findViewById(R.id.text_sizes_uni);
        text_sizes_uni.setText(R.string.sizelarge); //set the text for dog size for Large size

        //variable text_left for Large Dog size
        TextView text_left = findViewById(R.id.text_left);
        text_left.setText(R.string.largedogone); // Large Dog one

        //variable text_leftbut for Large Dog size
        TextView text_leftbut = findViewById(R.id.text_leftbut);
        text_leftbut.setText(R.string.largedogtwo); // Large Dog two

        //variable text_right for Large Dog size
        TextView text_right = findViewById(R.id.text_right);
        text_right.setText(R.string.largedogthree); // Large Dog three

        //variable text_right for Large Dog size
        TextView text_rightbut = findViewById(R.id.text_rightbut);
        text_rightbut.setText(R.string.largedogfour); // Large Dog four


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

// kin code
img_left.setImageResource(arrayLarge.arrLargeDogs[0]);
img_right.setImageResource(arrayLarge.arrLargeDogs[1]);
img_leftbut.setImageResource(arrayLarge.arrLargeDogs[2]);
img_rightbut.setImageResource(arrayLarge.arrLargeDogs[3]);

        //set chosen dog img STANDART BLOCK
        img_left.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //touch the img - start
if (event.getAction() == MotionEvent.ACTION_DOWN) {
    img_left.setImageResource(arrayLarge.arrLargeDogs[4]);
    img_leftbut.setEnabled(false);
    img_right.setEnabled(false);
    img_rightbut.setEnabled(false);

    img_left.setEnabled(false); //unable to push the left top img button again
    arrayLarge.arrPlayersSum[0] = 11;

}else
if (event.getAction() == MotionEvent.ACTION_UP) {
}
        //touch the img - finish
        return true;
    }
});
        //set chosen dog img STANDART BLOCK
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_right.setImageResource(arrayLarge.arrLargeDogs[5]);
                    img_leftbut.setEnabled(false);
                    img_left.setEnabled(false);
                    img_rightbut.setEnabled(false);

                    img_right.setEnabled(false); //unable to push the left top img button again
                    arrayLarge.arrPlayersSum[0] = 12;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });
        //set chosen dog img STANDART BLOCK
        img_leftbut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_leftbut.setImageResource(arrayLarge.arrLargeDogs[6]);
                    img_right.setEnabled(false);
                    img_left.setEnabled(false);
                    img_rightbut.setEnabled(false);

                    img_leftbut.setEnabled(false); //unable to push the left top img button again
                    arrayLarge.arrPlayersSum[0] = 13;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });
        //set chosen dog img STANDART BLOCK
        img_rightbut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    img_rightbut.setImageResource(arrayLarge.arrLargeDogs[7]);
                    img_leftbut.setEnabled(false);
                    img_left.setEnabled(false);
                    img_right.setEnabled(false);

                    img_rightbut.setEnabled(false); //unable to push the left top img button again
                    arrayLarge.arrPlayersSum[0] = 14;

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

     final Button btnContinue = (Button)findViewById(R.id.btnContinue);
//        //code if you push the btnContinue -> to the
          btnContinue.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  try {
                      if(summArray(Array.arrPlayersSum) > 0 ) {
                          Intent intent = new Intent(LargeSize.this, Food.class);
                          startActivity(intent);
                          finish();
                      }
                  } catch (Exception e) {
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
                    Intent intent = new Intent(LargeSize.this, Size.class);
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
            Intent intent = new Intent(LargeSize.this, Size.class);
            startActivity(intent); // open a new window
            finish();              // close the old one
        }catch(Exception e) {

        }
    }
    //System button "Back" - finish

}
