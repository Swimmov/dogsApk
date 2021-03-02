package dim.kinders.dogspoogolf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class LevelThree extends AppCompatActivity {
    Random random = new Random();
    float distPlayer, distPlayerTotal;
    float disTrash, disTrash1, disTrash2, disTrash3, interval;
    int count = 3;                // for power parameters using
    int attempt = 0;              // for throws quantity
    int cansCount = 0;            // for can's checking
    int arrCansVerify = 1;
    int throwsCounting = 0;       // for throws quantity
    int wRnd, changeTheWind;      // for wind parameters using
    boolean flagBingo = false;    // is the next throw bingo?
    int animDirection;
    public InterstitialAd interstitialAd;

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_three);

        // ------------ Ads block starts ------------------
        MobileAds.initialize(this, "ca-app-pub-1502973434730162~8709421420"); // App Id
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-1502973434730162/4511156111");
        AdRequest adRequest = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest);
        // ------------ Ads block finish -------------------

        // ----- Close Ad block ---- clock on 'x'-----------

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                try {
                    Intent intent = new Intent(LevelThree.this, LevelOneDogsAndFood.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                    // --- empty ----
                }
            }
        });

        // -------------------------------------------------

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
//--- Running Text setUp ---------------------------------------------------------------------------
        TextView runningText = findViewById(R.id.txt_now_throw);
        runningText.setText(R.string.txt_throw_to_the_can1);
        runningText.setSelected(true);

        final ImageView imgBoyNoBag = (ImageView) findViewById(R.id.img_boy);
        final ImageView[] imgCanAndBag = {(ImageView) findViewById(R.id.img_can1)};
//------------ img breed & food chosen DogAndFood.class --------------------------------------------
        final ImageView img_result_breed = (ImageView) findViewById(R.id.img_result_breed);
        img_result_breed.setImageResource(LevelThreeDogsAndFood.imgMapsIdDogs);
        final ImageView img_result_morning = (ImageView) findViewById(R.id.img_result_morning);
        img_result_morning.setImageResource(LevelThreeDogsAndFood.imgMapsIdFoodMorning);
        final ImageView img_result_evening = (ImageView) findViewById(R.id.img_result_evening);
        img_result_evening.setImageResource(LevelThreeDogsAndFood.imgMapsIdFoodEvening);
//------------- set up water img -------------------------------------------------------------------
        final ImageView img_result_water = (ImageView) findViewById(R.id.img_result_water);
        img_result_water.setImageResource(LevelThreeDogsAndFood.imgMapsIdWater);

//-------------------switch on animation - start----------------------------------------------------
        final Animation myAnimationBottomToTop = AnimationUtils.loadAnimation(this, R.anim.alpha_buttom_to_top);
        final Animation myAnimationTopToBottom = AnimationUtils.loadAnimation(this, R.anim.alpha_top_to_buttom);
        final Animation myAnimationAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

//----------------wind SetUP------------------------------------------------------------------------
        {
            final ImageView wind = (ImageView) findViewById(R.id.img_wind_back);
            wRnd = windRandom(wind);
//----------------dist to the trash cans SetUP -----------------------------------------------------
            final TextView trashDistance3 = (TextView) findViewById(R.id.can_distance3);
            final TextView trashDistance2 = (TextView) findViewById(R.id.can_distance2);
            final TextView trashDistance1 = (TextView) findViewById(R.id.can_distance1);
            disTrash3 = 4.25f * (random.nextFloat() * 10.25f + 65.75f);
            interval = disTrash3 / 14;
            disTrash2 = interval * 8;
            disTrash1 = interval * 4;
            disTrash = disTrash1;
            trashDistance3.setText(toStringMethod(disTrash3));
            trashDistance2.setText(toStringMethod(disTrash2));
            trashDistance1.setText(toStringMethod(disTrash1));

        }
//--------------------------bags places on the field -----------------------------------------------
         ImageView[] bagPlace = {null};
        // players distance total
        TextView plTotalDistance = (TextView) findViewById(R.id.txt_pl_total_dist);

//----------------clickOnListener SetUP for power---------------------------------------------------
        final ImageView pwr5 = (ImageView) findViewById(R.id.img_pwr_5);
        final ImageView pwr4 = (ImageView) findViewById(R.id.img_pwr_4);
        final ImageView pwr3 = (ImageView) findViewById(R.id.img_pwr_3);
        final ImageView pwr2 = (ImageView) findViewById(R.id.img_pwr_2);
        final ImageView pwr1 = (ImageView) findViewById(R.id.img_pwr_1);
        final ImageView pwrUp = (ImageView) findViewById(R.id.btn_pwr_up);
        final ImageView pwrDown = (ImageView) findViewById(R.id.btn_pwr_down);

        final TextView[] numberOfThrows = {(TextView) findViewById(R.id.number_of_throws1)};
//--------------- button power DOWN ----------------------------------------------------------------
        pwrDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    if (count == 5) {
                        pwr5.setImageResource(R.drawable.img_pwr_empty_max_80x40);
                    }
                    if (count == 4) {
                        pwr4.setImageResource(R.drawable.img_pwr_empty_80x40);
                    }
                    if (count == 3) {
                        pwr3.setImageResource(R.drawable.img_pwr_empty_80x40);
                    }
                    if (count == 2) {
                        pwr2.setImageResource(R.drawable.img_pwr_empty_80x40);
                    }
                    if (count == 1) {
//                            pwr1.setImageResource(R.drawable.img_pwr_empty_min_80x40);
                    }

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if(count <= 5 && count >1) {
                        count--;}

                }
                return true;
            }
        });

//----------------button power UP-------------------------------------------------------------------
        pwrUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (count <5 ) { count++; }

                }else
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (count == 1) {
                        pwr1.setImageResource(R.drawable.img_pwr_min_80x40);
                    }
                    if (count == 2) {
                        pwr2.setImageResource(R.drawable.img_pwr_green_80x40);
                    }
                    if (count == 3) {
                        pwr3.setImageResource(R.drawable.img_pwr_green_80x40);
                    }
                    if (count == 4) {
                        pwr4.setImageResource(R.drawable.img_pwr_green_80x40);
                    }
                    if (count == 5) {
                        pwr5.setImageResource(R.drawable.img_pwr_max_80x40);
                    }
                }
                return true;
            }
        });

//--------------------------Push The Throw Method---------------------------------------------------
        Button btnDoIt = (Button) findViewById(R.id.btn_more);
        btnDoIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throwsCounting++;
                numberOfThrows[0].setText(Integer.toString(throwsCounting));
                attempt++;   // next throw (counting)

                if (attempt > 10) {attempt = 11;}
// check if flagBingo == true && min pwr (count == 1), means -> Next attempt is BINGO!!!------------
                if (flagBingo && count == 1) {
                    distPlayer = Math.abs(disTrash - distPlayerTotal);
                    distPlayerTotal = disTrash;  // BINGO any way
                    flagBingo = false;
                } else
//====================================== GAME ======================================================
                {
                    imgBoyNoBag.setImageResource(R.drawable.img_walking_no_bag);
//--- Players distance. Depends on arr3[] (food & water).-------------------------------------------
//--- Players distance is not adjustable yet for Dog size, wind & power of throw.-------------------
                    distPlayer = foodBank(Array.arr3[0], Array.arr3[1], Array.arr3[2]);
//--- Players distance  depending on the size of the dog -------------------------------------------
                    distPlayer = whatSizeDog(distPlayer);

//--- Change the wind direction if the distance to the bag is more then to the trash can -----------
                    if (distPlayerTotal > disTrash) {changeTheWind = -1;}
                    else {changeTheWind = 1;}
//--- Players distance is adjustable for Dog size, wind & power of throw.---------------------------
                    distPlayer = distanceParam(changeTheWind, distPlayer, count, wRnd);

//----- check if the players result more than dist to the can + .5f---------------------------------
                    if (distPlayerTotal > disTrash + .5f){
                        distPlayerTotal = distPlayerTotal - distPlayer;
                    } else {
                        distPlayerTotal = distPlayerTotal + distPlayer;
                    }
                }
// -------------------------- each throw result ----------------------------------------------------
                playersLoops(attempt, distPlayer);
                plTotalDistance.setText(toStringMethod(distPlayerTotal));

//------------------------place the bags on the golf field------------------------------------------
//- 8 -------------------------Bingo ---------------------------------------------------------------
                if (distPlayerTotal >=  disTrash - .5f && distPlayerTotal <= disTrash + .5f) {
                    imgCanAndBag[0].setImageResource(R.drawable.img_can_and_bag);
                    cansCount++;
// --------------------------- Trash Can 1  is done SetUp for Trash can 2 --------------------------
                    if (disTrash == disTrash1) {
                        numberOfThrows[0] = (TextView) findViewById(R.id.number_of_throws2);
                        imgCanAndBag[0] = (ImageView) findViewById(R.id.img_can2);
                        runningText.setText(R.string.txt_throw_to_the_can2);
                        imgBoyNoBag.setImageResource(R.drawable.imgwalking);
                        zeroLoopsAndMaps(Array.arrCanOneInterval);
                        disTrash = disTrash2;
                        animDirection = 0;
                    }
// --------------------------- Trash Can 2  is done SetUp for Trash can 3 --------------------------
                    if (disTrash == disTrash2 && cansCount == 2) {
                        numberOfThrows[0] = (TextView) findViewById(R.id.number_of_throws3);
                        imgCanAndBag[0] = (ImageView) findViewById(R.id.img_can3);
                        runningText.setText(R.string.txt_throw_to_the_can3);
                        imgBoyNoBag.setImageResource(R.drawable.imgwalking);
                        zeroLoopsAndMaps(Array.arrCanTwoInterval);
                        disTrash = disTrash3;
                        animDirection = 0;
                    }
// --------------------------- Trash Can 3  is done ------Game Finish-------------------------------
                    if (disTrash == disTrash3 && cansCount == 3)  {
                        runningText.setText(R.string.bongo);
                        btnDoIt.setEnabled(false);
                        imgBoyNoBag.setImageResource(R.drawable.bingo_balloons);
                        imgBoyNoBag.startAnimation(myAnimationAlpha);
                    }
                    throwsCounting = 0;
                    attempt = 0;
                } else {
                    if (disTrash == disTrash1) {
                        spotFromArray(Array.arrCanOneInterval, arrCansVerify, distPlayerTotal,
                                disTrash, myAnimationBottomToTop, myAnimationTopToBottom);
                    }
                    if (disTrash == disTrash2) {
                        arrCansVerify = 5;
                        spotFromArray(Array.arrCanTwoInterval, arrCansVerify, distPlayerTotal,
                                disTrash, myAnimationBottomToTop, myAnimationTopToBottom);
                    }
                    if (disTrash == disTrash3) {
                        arrCansVerify = 9;
                        spotFromArrayMap3(arrCansVerify, distPlayerTotal, disTrash,
                                myAnimationBottomToTop, myAnimationTopToBottom);
                    }
                }
            }
//------------------- Zero for each loop result ----------------------------------------------------
            private void zeroLoopsAndMaps(int[][] arrCansInterval) {
//------------------------------- reset loops ------------------------------------------------------
                for (int i = 0; i < Array.arrLoops.length ; i++) {
                    TextView zeroLoops = (TextView) findViewById(Array.arrLoops[i]);
                    zeroLoops.setText("");
                }
//------------------------ clean bags places -------------------------------------------------------
                for (int i = 4; i < 6 ; i++) {
                    for (int j = 0; j < 8 ; j++) {
                        bagPlace[0] =(ImageView) findViewById(arrCansInterval[i][j]);
                        bagPlace[0].setImageResource(R.drawable.trans_50x50);
                    }
                }
            }
        });
    }

    private float whatSizeDog(float distPlayer) {
// index from the dogsAndFood Map
        if (LevelThreeDogsAndFood.indexForSize >= 3 && LevelThreeDogsAndFood.indexForSize < 6) {
            distPlayer = distPlayer * 0.75f;
        }
        if (LevelThreeDogsAndFood.indexForSize >= 6 && LevelThreeDogsAndFood.indexForSize < 8) {
            distPlayer = distPlayer * 0.5f;
        }
        return distPlayer;
    }

// --- changeTheWind --> Wind detection; distPlayer - Players distance; count --> power parameter:
// --- wRnd --> wind power Randomly ------------------------------------------ //
    private float distanceParam(int changeTheWind, float distPlayer, int count, int wRnd) {
        switch (count) {
            case 1:
                distPlayer = distPlayer * .15f; // MIN power
                break;
            case 2:
                distPlayer = distPlayer * .50f; // MID power
                break;
            case 3:
                distPlayer = distPlayer * 1.00f; //  power
                break;
            case 4:
                distPlayer = distPlayer * 1.15f; // MID power
                break;
            case 5:
                distPlayer = distPlayer * 1.35f; // MAX power
                break;
        }
        switch (wRnd) {
            case 0:
                if (changeTheWind == -1) {distPlayer = distPlayer * 1.15f; } // tail wind -> +15% add to distance
                distPlayer = distPlayer * .85f; // headwind -> -15% loss distance
                break;
            case 1:
                if (changeTheWind == -1) {distPlayer = distPlayer * 1.35f; } // tail wind -> +35% add to distance
                distPlayer = distPlayer * .65f; // headwind -> -35% loss distance
                break;
            case 2:
                if (changeTheWind == -1) {distPlayer = distPlayer * 1.65f; } // tail wind -> +65% add to distance
                distPlayer = distPlayer * 0.35f; // headwind -> -65% loss distance
                break;
            case 3:
                distPlayer = distPlayer * 1.0f; // no wind
                break;
            case 4:
                if (changeTheWind == -1) {distPlayer = distPlayer * .85f;} // headwind -> -15% loss distance
                distPlayer = distPlayer * 1.15f; // tail wind -> +15% add to distance
                break;
            case 5:
                if (changeTheWind == -1) { distPlayer = distPlayer * .65f;} // headwind -> -35% loss distance
                distPlayer = distPlayer * 1.35f; // tail wind -> +35% add to distance
                break;
            case 6:
                if (changeTheWind == -1) {distPlayer = distPlayer * 0.35f; } // headwind -> -65% loss distance
                distPlayer = distPlayer * 1.65f; // tail wind -> +65% add to distance
                break;
        }
        return distPlayer;
    }

//----------------------- Method shows all throw distances for player ------------------------------
    private void playersLoops(int attempt, float distPlayer) {
        TextView plLoops = null;
        switch (attempt) {
            case 1:
                plLoops = (TextView) findViewById(R.id.txt_throw_1);
                break;
            case 2:
                plLoops = (TextView) findViewById(R.id.txt_throw_2);
                break;
            case  3:
                plLoops = (TextView) findViewById(R.id.txt_throw_3);
                break;
            case  4:
                plLoops = (TextView) findViewById(R.id.txt_throw_4);
                break;
            case  5:
                plLoops = (TextView) findViewById(R.id.txt_throw_5);
                break;
            case  6:
                plLoops = (TextView) findViewById(R.id.txt_throw_6);
                break;
            case  7:
                plLoops = (TextView) findViewById(R.id.txt_throw_7);
                break;
            case  8:
                plLoops = (TextView) findViewById(R.id.txt_throw_8);
                break;
            case  9:
                plLoops = (TextView) findViewById(R.id.txt_throw_9);
                break;
            case  10:
                plLoops = (TextView) findViewById(R.id.txt_throw_10);
                break;
            case  11:
                plLoops = (TextView) findViewById(R.id.txt_throw_11);
                break;
        }
        assert plLoops != null;
        plLoops.setText(toStringMethod(distPlayer));
    }

//------ Randomly plaice bags on the field depends on distance and Trash Can number-----------------

    private void spotFromArray(int[][] arrCansInterval,  int arrCansVerify, float totalDistPlayer,
         float disTrash, Animation myAnimationBottomToTop, Animation myAnimationTopToBottom)
    {
        ImageView bagPlace = null;
        int i;                     //raw on the fields
        int randomBagPlace = random.nextInt(8);
        if ((totalDistPlayer > disTrash + 0.5f && totalDistPlayer <= disTrash + 8.0f) ||
                (totalDistPlayer < disTrash - 0.5f && totalDistPlayer >= disTrash - 8.0f)) {
            flagBingo = true;      // next throw Bingo
        }
//------------------------------------------ 1 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify-1) && totalDistPlayer <= interval * arrCansVerify) {
             i = 0;
                for (int j = 0; j < 8; j++) {
                    if(randomBagPlace == j) {
                        bagPlace =(ImageView) findViewById(arrCansInterval[i][j]);
                    }
                }

        }
//------------------------------------------ 2 ----------------------------------------------------
        if (totalDistPlayer > interval * arrCansVerify && totalDistPlayer <= interval * (arrCansVerify+1)) {
             i = 1;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(arrCansInterval[i][j]);
                }
            }
        }
//------------------------------------------ 3 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify+1) && totalDistPlayer <= interval * (arrCansVerify+2)) {
             i = 2;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(arrCansInterval[i][j]);
                }
            }
        }
//------------------------------------------ 4 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify+2) && totalDistPlayer <= interval * (arrCansVerify+3)) {
             i = 3;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(arrCansInterval[i][j]);
                }
            }
        }
//--------------------------------- more then TrashDist --------------------------------------------
        if (totalDistPlayer > disTrash + 0.5f && totalDistPlayer <= disTrash + 8.0f) {
             i = 4;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(arrCansInterval[i][j]);
                }
            }
        }
        if (totalDistPlayer > disTrash + 8.0f ) {
             i = 5;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(arrCansInterval[i][j]);
                }
            }
        }
//        assert bagPlace != null;
        bagPlace.setImageResource(R.drawable.img_bag_70x70);

        switch (animDirection) {
            case 0 :
                bagPlace.startAnimation(myAnimationBottomToTop);
                break;
            case 2 :
                bagPlace.startAnimation(myAnimationTopToBottom);
                animDirection = 0;
                break;
        }
        if ((totalDistPlayer - disTrash) > 0.5f) {
            animDirection = 2;
        }
    }
//-------------------------------- Trash Can 3 BAGs-FIELD ------------------------------------------
    private void spotFromArrayMap3(int arrCansVerify, float totalDistPlayer, float disTrash,
                    Animation myAnimationBottomToTop, Animation myAnimationTopToBottom)
    {
        ImageView bagPlace = null;
        int i;
        int randomBagPlace = random.nextInt(8);
        if ((totalDistPlayer > disTrash + 0.5f && totalDistPlayer <= disTrash + 8.0f) ||
                (totalDistPlayer < disTrash - 0.5f && totalDistPlayer >= disTrash - 8.0f)) {
            flagBingo = true;         // next throw Bingo
        }
//------------------------------------------ 1 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify-1) && totalDistPlayer <= interval * arrCansVerify) {
            i = 0;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//------------------------------------------ 2 ----------------------------------------------------
        if (totalDistPlayer > interval * arrCansVerify && totalDistPlayer <= interval * (arrCansVerify+1)) {
            i = 1;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//------------------------------------------ 3 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify+1) && totalDistPlayer <= interval * (arrCansVerify+2)) {
            i = 2;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//------------------------------------------ 4 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify+2) && totalDistPlayer <= interval * (arrCansVerify+3)) {
            i = 3;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//------------------------------------------ 5 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify+3) && totalDistPlayer <= interval * (arrCansVerify+4)) {
            i = 4;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//------------------------------------------ 6 ----------------------------------------------------
        if (totalDistPlayer > interval * (arrCansVerify+4) && totalDistPlayer <= interval * (arrCansVerify+5)) {
            i = 5;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//--------------------------------- more then TrashDist --------------------------------------------
        if (totalDistPlayer > disTrash + 0.5f && totalDistPlayer <= disTrash + 8.0f) {
            i = 6;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
        if (totalDistPlayer > disTrash + 8.0f ) {
            i = 7;
            for (int j = 0; j < 8; j++) {
                if(randomBagPlace == j) {
                    bagPlace =(ImageView) findViewById(Array.arrCanThreeInterval[i][j]);
                }
            }
        }
//        assert bagPlace != null;
        bagPlace.setImageResource(R.drawable.img_bag_70x70);
        switch (animDirection) {
            case 0 :
                bagPlace.startAnimation(myAnimationBottomToTop);
                break;
            case 2 :
                bagPlace.startAnimation(myAnimationTopToBottom);
                animDirection = 0;
                break;
        }

        if ((totalDistPlayer - disTrash) > 0.5f) {
            animDirection = 2;
        }
    }

//----------------------------- WIND randomly ------------------------------------------------------
    private int windRandom(ImageView wB) {
        int wind;
        wind = random.nextInt(6);
        switch (wind){
            case 0:
                wB.setImageResource(R.drawable.img_wind_slow_head_80x120);
                break;
            case 1:
                wB.setImageResource(R.drawable.img_wind_mid_head_80x120);
                break;
            case 2:
                wB.setImageResource(R.drawable.img_wind_strong_head_80x120);
                break;
            case 3:
                wB.setImageResource(R.drawable.img_no_wind_80x120);
                break;
            case 4:
                wB.setImageResource(R.drawable.img_wind_slow_tail_80x120);
                break;
            case 5:
                wB.setImageResource(R.drawable.img_wind_mid_tail_80x120);
                break;
            case 6:
                wB.setImageResource(R.drawable.img_wind_strong_tail_80x120);
                break;
        }
        return wind;
    }

    String toStringMethod(float dstFloat) {
        return String.valueOf(dstFloat).substring(0, 5);
    }

//------- Players distance depends on the set of MorningFood+EveningFood+Water (72 combinations)----
    float foodBank(int ari1, int ari2, int ari3) {
        float fb = 0.0f;
        if ((ari1 == 11 && ari2 == 15 && ari3 == 2) || (ari1 == 12 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 16 && ari3 == 2) || (ari1 == 14 && ari2 == 12 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 12 && ari3 == 2) || (ari1 == 16 && ari2 == 12 && ari3 == 1) ||
                (ari1 == 12 && ari2 == 12 && ari3 == 1) || (ari1 == 15 && ari2 == 15 && ari3 == 2) ||
                (ari1 == 11 && ari2 == 13 && ari3 == 2) || (ari1 == 14 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 11 && ari3 == 2) || (ari1 == 16 && ari2 == 14 && ari3 == 2)) {
            fb = random.nextFloat() * 5.25f + 55.75f;
        }

        if ((ari1 == 11 && ari2 == 14 && ari3 == 2) || (ari1 == 12 && ari2 == 15 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 15 && ari3 == 2) || (ari1 == 14 && ari2 == 11 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 11 && ari3 == 2) || (ari1 == 16 && ari2 == 13 && ari3 == 2) ||
                (ari1 == 11 && ari2 == 11 && ari3 == 2) || (ari1 == 16 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 12 && ari2 == 11 && ari3 == 1) || (ari1 == 15 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 14 && ari2 == 15 && ari3 == 1) || (ari1 == 13 && ari2 == 11 && ari3 == 1)) {
            fb = random.nextFloat() * 5.25f + 50.75f;
        }

        if ((ari1 == 11 && ari2 == 16 && ari3 == 2) || (ari1 == 12 && ari2 == 14 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 14 && ari3 == 2) || (ari1 == 14 && ari2 == 13 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 13 && ari3 == 2) || (ari1 == 16 && ari2 == 11 && ari3 == 2) ||
                (ari1 == 12 && ari2 == 12 && ari3 == 2) || (ari1 == 15 && ari2 == 15 && ari3 == 1) ||
                (ari1 == 11 && ari2 == 12 && ari3 == 1) || (ari1 == 14 && ari2 == 15 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 12 && ari3 == 1) || (ari1 == 16 && ari2 == 15 && ari3 == 1)) {
            fb = random.nextFloat() * 5.25f + 45.75f;
        }

        if ((ari1 == 11 && ari2 == 15 && ari3 == 1) || (ari1 == 12 && ari2 == 16 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 16 && ari3 == 1) || (ari1 == 14 && ari2 == 13 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 11 && ari3 == 1) || (ari1 == 16 && ari2 == 12 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 13 && ari3 == 2) || (ari1 == 14 && ari2 == 14 && ari3 == 2) ||
                (ari1 == 12 && ari2 == 13 && ari3 == 2) || (ari1 == 11 && ari2 == 13 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 14 && ari3 == 1) || (ari1 == 16 && ari2 == 14 && ari3 == 1)) {
            fb = random.nextFloat() * 5.25f + 40.75f;
        }

        if ((ari1 == 11 && ari2 == 16 && ari3 == 1) || (ari1 == 12 && ari2 == 15 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 15 && ari3 == 1) || (ari1 == 14 && ari2 == 12 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 13 && ari3 == 1) || (ari1 == 16 && ari2 == 11 && ari3 == 1) ||
                (ari1 == 11 && ari2 == 11 && ari3 == 1) || (ari1 == 16 && ari2 == 16 && ari3 == 1) ||
                (ari1 == 12 && ari2 == 13 && ari3 == 1) || (ari1 == 15 && ari2 == 16 && ari3 == 1) ||
                (ari1 == 14 && ari2 == 16 && ari3 == 1) || (ari1 == 13 && ari2 == 12 && ari3 == 2)) {
            fb = random.nextFloat() * 5.25f + 35.75f;
        }

        if ((ari1 == 11 && ari2 == 14 && ari3 == 1) || (ari1 == 12 && ari2 == 14 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 14 && ari3 == 1) || (ari1 == 14 && ari2 == 11 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 12 && ari3 == 1) || (ari1 == 16 && ari2 == 13 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 13 && ari3 == 1) || (ari1 == 14 && ari2 == 14 && ari3 == 1) ||
                (ari1 == 12 && ari2 == 11 && ari3 == 2) || (ari1 == 11 && ari2 == 12 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 14 && ari3 == 2) || (ari1 == 16 && ari2 == 15 && ari3 == 2)) {
            fb = random.nextFloat() * 3.25f + 30.75f;
        }

        return fb;

    }

    public void startNewGame(View view) {
        // --- if Ads are loaded -> show it ---- if not -> go ahead-----
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {

            try {
                Intent intent = new Intent(this, LevelOneDogsAndFood.class);
                startActivity(intent); // open a new window
                finish();              // close the old one
            } catch (Exception e) {
            }
        }
    }
}

