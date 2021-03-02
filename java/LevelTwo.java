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

public class LevelTwo extends AppCompatActivity {
    Random random = new Random();
    int randomBagPlace;
    float distPlayer, distPlayerTotal, disTrash;
    int count = 3;                // for power parameters using
    int attempt = 0;              // for throws quantity
    int throwsCounting;
    int wRnd, changeTheWind;      // for wind parameters using
    boolean flagBingo = false;    // is the next throw bingo?
    int animDirection;
    public InterstitialAd interstitialAd;

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_two);

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
                    Intent intent = new Intent(LevelTwo.this, LevelThreeDogsAndFood.class);
                    startActivity(intent); finish();
                }catch (Exception e){
                    // --- empty ----
                }
            }
        });

        // -------------------------------------------------

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView imgBoyNoBag = (ImageView) findViewById(R.id.img_boy);
        final ImageView imgCanAndBag = (ImageView) findViewById(R.id.img_can1);
        final TextView txt_now_throw = (TextView) findViewById(R.id.txt_now_throw);
        //------------ img breed & food chosen DogAndFood.class ------------------------------------
        final ImageView img_result_breed = (ImageView) findViewById(R.id.img_result_breed);
        img_result_breed.setImageResource(LevelTwoDogsAndFood.imgMapsIdDogs);
        final ImageView img_result_morning = (ImageView) findViewById(R.id.img_result_morning);
        img_result_morning.setImageResource(LevelTwoDogsAndFood.imgMapsIdFoodMorning);
        final ImageView img_result_evening = (ImageView) findViewById(R.id.img_result_evening);
        img_result_evening.setImageResource(LevelTwoDogsAndFood.imgMapsIdFoodEvening);

        //------------- set up water img --------------------------------------------------
        final ImageView img_result_water = (ImageView) findViewById(R.id.img_result_water);
        img_result_water.setImageResource(LevelTwoDogsAndFood.imgMapsIdWater);

        //-------------------switch on animation - start--------------------------
        final Animation myAnimationScale = AnimationUtils.loadAnimation(this, R.anim.scale);
        final Animation myAnimationBottomToTop = AnimationUtils.loadAnimation(this, R.anim.alpha_buttom_to_top);
        final Animation myAnimationTopToBottom = AnimationUtils.loadAnimation(this, R.anim.alpha_top_to_buttom);
        final Animation myAnimationAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //-------------------switch on animation - finish-------------------------

        Button btnNextLevel = findViewById(R.id.btn_next_level);
        btnNextLevel.setEnabled(false);

        //----------------wind SetUP------------------------------------------------
        {
            final ImageView wind = (ImageView) findViewById(R.id.img_wind_back);
            wRnd = windRandom(wind);

       //----------------dist to the trash can SetUP----------------------
            final TextView trashDistance = (TextView) findViewById(R.id.can_distance3);
            disTrash = 3 * (random.nextFloat() * 10.25f + 65.75f);
            trashDistance.setText(toStringMethod(disTrash));
        }
//--------------------------bags places on the field - block----------------------------
        final ImageView[] bagPlace = {null};
        // players distance total
        TextView plTotalDistance = (TextView) findViewById(R.id.txt_pl_total_dist);

//----------------clickOnListener SetUP for power----------------------
        final ImageView pwr5 = (ImageView) findViewById(R.id.img_pwr_5);
        final ImageView pwr4 = (ImageView) findViewById(R.id.img_pwr_4);
        final ImageView pwr3 = (ImageView) findViewById(R.id.img_pwr_3);
        final ImageView pwr2 = (ImageView) findViewById(R.id.img_pwr_2);
        final ImageView pwr1 = (ImageView) findViewById(R.id.img_pwr_1);
        final ImageView pwrUp = (ImageView) findViewById(R.id.btn_pwr_up);
        final ImageView pwrDown = (ImageView) findViewById(R.id.btn_pwr_down);

        TextView numberOfThrows = (TextView) findViewById(R.id.number_of_throws3);

//--------------- button power DOWN ---------------------------------
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

//----------------button power UP----------------------
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

//--------------------------Push The Throw Method-----------------------------------------
        Button btnDoIt = (Button) findViewById(R.id.btn_more);
        btnDoIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throwsCounting++;
                numberOfThrows.setText(Integer.toString(throwsCounting));
                attempt++;   // next throw (counting)
                if (attempt > 10) {attempt = 11;}
// check if flagBingo == true && min pwr (count == 1), means -> Next attempt is BINGO!!!
                if (flagBingo && count == 1) {
                    distPlayer = Math.abs(disTrash - distPlayerTotal);
                    distPlayerTotal = disTrash;  // BINGO any way
                } else
                //------------- GAME -----------------
                {
                    imgBoyNoBag.setImageResource(R.drawable.img_walking_no_bag);
                    //--- Players distance. Depends on arr3[] (food & water).------------------------
// Not adjustable yet for Dog size, wind & power of throw.------------------------
                    distPlayer = foodBank(Array.arr3[0], Array.arr3[1], Array.arr3[2]);
// players distance depending on the size of the dog
//                    distPlayer = whatSizeDog(distPlayer);
// Change the wind direction if the distance to the bag is more then to the trash can
                    if (distPlayerTotal > disTrash) {changeTheWind = -1;}
                    else {changeTheWind = 1;}
//--- Players distance adjustable yet for Dog size, wind & power of throw.------------------------
                    distPlayer = distanceParam(changeTheWind, distPlayer, count, wRnd);
                    //----- check if the players result more than dist to can + .5f-----------
                    if (distPlayerTotal > disTrash + .5f){
                        distPlayerTotal = distPlayerTotal - distPlayer;
                    } else {
                        distPlayerTotal = distPlayerTotal + distPlayer;
                    }
                }
                // ----each throw result------------
                playersLoops(attempt, distPlayer);
                plTotalDistance.setText(toStringMethod(distPlayerTotal));
//---------place the bags on the golf field--------------------------------------------------------
//- 8 -------------------------Bingo --------------------------------------------------------------
                if (distPlayerTotal >=  disTrash - .5f && distPlayerTotal <= disTrash + .5f) {
     methodBingo(imgBoyNoBag, btnDoIt, btnNextLevel, imgCanAndBag,  txt_now_throw, myAnimationScale);
                }else {
                    randomBagPlace = random.nextInt(4);

//- 1 -------------------------0 - 30 -----------------------------------------------
                    if (distPlayerTotal > 0.0f && distPlayerTotal <= 30.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_1_01);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_1_02);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_1_03);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_1_04);
                                break;
                        }

                    }
//- 2 -------------------------31 - 60 -----------------------------------------------
                    if (distPlayerTotal > 30.0f && distPlayerTotal <= 60.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_2_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_2_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_2_3);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_2_4);
                                break;
                        }
                    }
//- 3 ------------------------- 61 - 90 -----------------------------------------------
                    if (distPlayerTotal > 60.0f && distPlayerTotal <= 90.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_3_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_3_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_3_3);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_3_4);
                                break;
                        }
                    }
//- 4 ------------------------- 91 - 120 -----------------------------------------------
                    if (distPlayerTotal > 90.0f && distPlayerTotal <= 120.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_4_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_4_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_4_3);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_4_4);
                                break;
                        }
                    }
//- 5 ------------------------- 121 - 150 -----------------------------------------------
                    if (distPlayerTotal > 120.0f && distPlayerTotal <= 150.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_5_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_5_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_5_3);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_5_4);
                                break;
                        }
                    }
//- 6 -------------------------151 - 180-----------------------------------------------
                    if (distPlayerTotal > 150.0f && distPlayerTotal <= 180.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_6_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_6_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_6_3);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_6_4);
                                break;
                        }
                    }
//- 7 -------------------------181 - (disTrash - 10.0f)---------------------------------------------
                    if (distPlayerTotal > 180.0f && distPlayerTotal <= disTrash - 10.0f) {
                        switch (randomBagPlace) {
                            case 0:
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_7_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_7_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_7_3);
                                break;
                        }
                    }
//- 7.1. -------------------------(disTrash - 10.0f)) - (disTrash - .5f)----------------------------
                    if (distPlayerTotal >= disTrash - 10.0f && distPlayerTotal < disTrash - .5f) {
                        flagBingo = true;                                      // next throw Bingo
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_7_1);
                                break;
                            case 1:
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_7_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_7_3);
                                break;
                        }
                    }
//- 9 -------------------------More than trash distance && less than isTrash + 10.0f ---------------
                    if (distPlayerTotal <= disTrash + 10.0f && distPlayerTotal > disTrash + .5f) {
                        flagBingo = true;                                      // next throw Bingo
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_8_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_8_2);
                                break;
                            case 2:
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_8_3);
                                break;
                        }
                    }
//- 10 -------------------------More than trash distance && more than disTrash + 10.0f ---------------
                    if (distPlayerTotal > disTrash + 10.0f) {
                        switch (randomBagPlace) {
                            case 0:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_9_1);
                                break;
                            case 1:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_9_2);
                                break;
                            case 2:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_9_3);
                                break;
                            case 3:
                                bagPlace[0] = (ImageView) findViewById(R.id.img_bag_9_4);
                                break;
                        }
                    }
                    bagPlace[0].setImageResource(R.drawable.img_bag_70x70);
                    switch (animDirection) {
                        case 0:
                            bagPlace[0].startAnimation(myAnimationBottomToTop);
                            break;
                        case 2:
                            bagPlace[0].startAnimation(myAnimationTopToBottom);
                            animDirection = 0;
                            break;
                    }
                    if ((distPlayerTotal - disTrash) > 0.5f) {
                        animDirection = 2;
                    }
                }
            }
        });
    }
//----- distance depends on Dog Size ---------------------------------------------------------------
//    private float whatSizeDog(float distPlayer) {
//// index from the dogsAndFood Map
//        if (DogsAndFood.indexForSize >= 3 && DogsAndFood.indexForSize < 6) {
//            distPlayer = distPlayer * 0.75f;
//        }
//        if (DogsAndFood.indexForSize >= 6 && DogsAndFood.indexForSize < 8) {
//            distPlayer = distPlayer * 0.5f;
//        }
//        return distPlayer;
//    }

//-- distance depends on power of throw. distPlayer - Players distance; count --> power parameter:
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

    //----------------------- Method shows all throws distance for the player ----------------------
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
        plLoops.setText(toStringMethod(distPlayer));
    }
    //---------------------- Randomly plaice bags on the field depends on distance -----------

    private void methodBingo(ImageView imgBoyNoBag, Button btnDoIt, Button btnNextLevel,
                             ImageView imgCanAndBag, TextView txt_now_throw, Animation myAnimationScale)
    {
        txt_now_throw.setText(R.string.bongo);
        txt_now_throw.setSelected(true);
        imgCanAndBag.setImageResource(R.drawable.img_can_and_bag);
        imgBoyNoBag.setImageResource(R.drawable.bingo_);
        imgBoyNoBag.startAnimation(myAnimationScale);
        btnDoIt.setEnabled(false);
        btnNextLevel.setEnabled(true);
    }

    //----------------------------- WIND randomly ------------------------------
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

 //------- Players distance depends on the set of MorningFood+EveningFood+Water (72 combinations)---
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
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent); // open a new window
            finish();              // close the old one
        }catch(Exception e) {

        }
    }

    public void startNextLevel(View view) {
        // --- if Ads are loaded -> show it ---- if not -> go ahead-----
        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {

            try {
                Intent intent = new Intent(this, LevelThreeDogsAndFood.class);
                startActivity(intent); // open a new window
                finish();              // close the old one
            } catch (Exception e) {
            }
        }
    }
}
