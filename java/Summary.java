package dim.kinders.DogsPooGolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Summary extends AppCompatActivity {
    Array arrSumm = new Array();
    Random random = new Random();
    int index;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView imgPlayresDog = (ImageView) findViewById(R.id.imgplayersdog);
        final ImageView imgPlayresMorningFood = (ImageView) findViewById(R.id.imgsummplfoodmorning);
        final ImageView imgPlayresEveningFood = (ImageView) findViewById(R.id.imgsummplfoodevening);
        final ImageView imgPlayresWater = (ImageView) findViewById(R.id.imgsummplwater);

//        final ImageView imgAppsDog = (ImageView) findViewById(R.id.imgappsdog);
//        final ImageView imgAppMorningFood = (ImageView) findViewById(R.id.imgsummappfoodmorning);
//        final ImageView imgAppEveningFood = (ImageView) findViewById(R.id.imgsummappfoodevening);
//        final ImageView imgAppWater = (ImageView) findViewById(R.id.imgsummappwater);

        //----------------------------------- BREED ------------------------------------------------
        //players choice - Large Size Breed
        if (Array.arrPlayersSum[0] == 11) {imgPlayresDog.setImageResource(arrSumm.arrLargeDogs[0]);}
        if (Array.arrPlayersSum[0] == 12) {imgPlayresDog.setImageResource(arrSumm.arrLargeDogs[1]);}
        if (Array.arrPlayersSum[0] == 13) {imgPlayresDog.setImageResource(arrSumm.arrLargeDogs[2]);}
        if (Array.arrPlayersSum[0] == 14) {imgPlayresDog.setImageResource(arrSumm.arrLargeDogs[3]);}
        //players choice - Medium Size Breed
        if (Array.arrPlayersSum[0] == 21) {imgPlayresDog.setImageResource(arrSumm.arrMediumDogs[0]);}
        if (Array.arrPlayersSum[0] == 22) {imgPlayresDog.setImageResource(arrSumm.arrMediumDogs[1]);}
        if (Array.arrPlayersSum[0] == 23) {imgPlayresDog.setImageResource(arrSumm.arrMediumDogs[2]);}
        if (Array.arrPlayersSum[0] == 24) {imgPlayresDog.setImageResource(arrSumm.arrMediumDogs[3]);}
        //players choice - Small Size Breed
        if (Array.arrPlayersSum[0] == 31) {imgPlayresDog.setImageResource(arrSumm.arrSmallDogs[0]);}
        if (Array.arrPlayersSum[0] == 32) {imgPlayresDog.setImageResource(arrSumm.arrSmallDogs[1]);}
        if (Array.arrPlayersSum[0] == 33) {imgPlayresDog.setImageResource(arrSumm.arrSmallDogs[2]);}
        if (Array.arrPlayersSum[0] == 34) {imgPlayresDog.setImageResource(arrSumm.arrSmallDogs[3]);}

        //----------------------------------- MORNING FOOD -----------------------------------------
        //players choice - Dry MorningFood
        if (Array.arrPlayersSum[1] == 11) {imgPlayresMorningFood.setImageResource(arrSumm.arrDryFood[0]);}
        if (Array.arrPlayersSum[1] == 12) {imgPlayresMorningFood.setImageResource(arrSumm.arrDryFood[1]);}
        if (Array.arrPlayersSum[1] == 13) {imgPlayresMorningFood.setImageResource(arrSumm.arrDryFood[2]);}
        //players choice - Cooked MorningFood
        if (Array.arrPlayersSum[2] == 14) {imgPlayresMorningFood.setImageResource(arrSumm.arrCookedFood[0]);}
        if (Array.arrPlayersSum[2] == 15) {imgPlayresMorningFood.setImageResource(arrSumm.arrCookedFood[1]);}
        if (Array.arrPlayersSum[2] == 16) {imgPlayresMorningFood.setImageResource(arrSumm.arrCookedFood[2]);}

        if (Array.arrPlayersSum[1] == 0) {Array.arr3[0] = Array.arrPlayersSum[2];} //taken dry food
        else {Array.arr3[0] = Array.arrPlayersSum[1];} //taken cooked food

        //----------------------------------- EVENING FOOD -----------------------------------------
        //players choice - Dry Evening Food
        if (Array.arrPlayersSum[3] == 11) {imgPlayresEveningFood.setImageResource(arrSumm.arrDryFood[0]);}
        if (Array.arrPlayersSum[3] == 12) {imgPlayresEveningFood.setImageResource(arrSumm.arrDryFood[1]);}
        if (Array.arrPlayersSum[3] == 13) {imgPlayresEveningFood.setImageResource(arrSumm.arrDryFood[2]);}
        //players choice - Cooked Evening Food
        if (Array.arrPlayersSum[4] == 14) {imgPlayresEveningFood.setImageResource(arrSumm.arrCookedFood[0]);}
        if (Array.arrPlayersSum[4] == 15) {imgPlayresEveningFood.setImageResource(arrSumm.arrCookedFood[1]);}
        if (Array.arrPlayersSum[4] == 16) {imgPlayresEveningFood.setImageResource(arrSumm.arrCookedFood[2]);}

        if (Array.arrPlayersSum[3] == 0) {Array.arr3[1] = Array.arrPlayersSum[4];} //taken dry food
        else {Array.arr3[1] = Array.arrPlayersSum[3];}  //taken cooked food

        //----------------------------------- WATER -----------------------------------------
        //players choice - Dry Evening Food
        if (Array.arrPlayersSum[5] == 2) {imgPlayresWater.setImageResource(R.drawable.img_no);}
        if (Array.arrPlayersSum[5] == 1) {imgPlayresWater.setImageResource(R.drawable.img_yes);}
            Array.arr3[2] = Array.arrPlayersSum[5];

        //button Back -> to the previous Activity
        Button button_back = (Button)findViewById(R.id.button_back_summ);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                try {
                    Intent intent = new Intent(Summary.this, Water.class);
                    startActivity(intent); // open a new window
                    finish();              // close the old one
                }catch(Exception e) {

                }
            }
        });

        Button btnContinue = (Button)findViewById(R.id.btncontinuefood);
//        //code if you push the btnContinue -> to the
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Summary.this, Result.class);
                    startActivity(intent);  finish();
                } catch(Exception e) {

                }
            }
        });
    }
}
