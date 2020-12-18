package dim.kinders.DogsPooGolf;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Size extends AppCompatActivity {

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.size);

        Window w = getWindow();
        //window for all screen size
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //popUp the Dialog Window in the begining of the GAME
        dialog = new Dialog(this); // new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // NO header for our dialog window
        dialog.setContentView(R.layout.previewdialog); //path to the maket of our dialog window
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // transparent background
        dialog.setCancelable(false); // cant close the dialog window by typing the System button "Back"
        //dialog close button "X" - start
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pushing the button
                try{
                    // back to the main Activity ("Start")
                    Intent intent = new Intent(Size.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // close this class
                    //
                }catch (Exception e) {

                }
                dialog.dismiss(); // close the dialog window
                //
            }
        });
        //dialog close button "X" - finish

        // button "Continue" - start
        Button btncontinue = (Button)dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        // button "Continue" - finish

        dialog.show();
        //

        //button Back -> to the previous Activity
        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                try {
                    Intent intent = new Intent(Size.this, MainActivity.class);
                    startActivity(intent); // open a new window
                    finish();              // close the old one
                }catch(Exception e) {

                }
            }
        });

        // Button "Large Size" -> to the Activity Large Dogs
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Size.this, LargeSize.class);
                    startActivity(intent); finish();
                }catch(Exception e){

                }
            }
        });

        // Button "Medium Size" -> to the Activity Medium Dogs
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Size.this, MediumSize.class);
                    startActivity(intent); finish();
                }catch(Exception e){

                }
            }
        });

        // Button "Small Size" -> to the Activity Small Dogs
        TextView textView3 = (TextView)findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Size.this, SmallSize.class);
                    startActivity(intent); finish();
                }catch(Exception e){

                }
            }
        });

    }

    //System button "Back" - start
    @Override
    public void onBackPressed(){
        try {
            Intent intent = new Intent(Size.this, MainActivity.class);
            startActivity(intent); // open a new window
            finish();              // close the old one
        }catch(Exception e) {

        }
    }
    //System button "Back" - finish
}
