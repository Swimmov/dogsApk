package dim.kinders.dogspoogolf;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LevelOneDogsAndFood extends AppCompatActivity {
    Map<Integer, Integer> imgMapFood = new LinkedHashMap<>(); // pare K <-> V => RadioButton <-> FoodImg
    Map<Integer, Integer> imgMapWater = new LinkedHashMap<>(); // pare K <-> V => RadioButton <-> FoodImg
    List<Integer> listOfIndexesFood; // list of Keys imgMapFoods = id's RadioButtons for Dogs
    static int imgMapsIdDogs;
    static int imgMapsIdFoodMorning;
    static int imgMapsIdFoodEvening;
    static int imgMapsIdWater;
    static int indexForSize;
    static int indexForFood;
    boolean checkedDogsGroups = true;
    boolean checkedFoodMorning;
    boolean checkedFoodEvening;
    boolean checkedWater;
    Button btnNewNext;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_one_dogs_and_food);
// ----------------- Banner Level 1 -------------------------------------------------
        MobileAds.initialize(this, "ca-app-pub-1502973434730162~8709421420");
        adView = findViewById(R.id.adView_level1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
// ----------------------------------------------------------------------------------
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // --------------- Food HashMap ------------------------------------
        {
            imgMapFood.put(R.id.radio_dry_1_morning, R.drawable.img_dry_food_1);
            imgMapFood.put(R.id.radio_dry_1_evening, R.drawable.img_dry_food_1);
            imgMapFood.put(R.id.radio_dry_2_morning, R.drawable.img_dry_food_2);
            imgMapFood.put(R.id.radio_dry_2_evening, R.drawable.img_dry_food_2);
            imgMapFood.put(R.id.radio_dry_3_morning, R.drawable.img_dry_food_3);
            imgMapFood.put(R.id.radio_dry_3_evening, R.drawable.img_dry_food_3);

            imgMapFood.put(R.id.radio_cooked_1_morning, R.drawable.img_cooked_food_1);
            imgMapFood.put(R.id.radio_cooked_1_evening, R.drawable.img_cooked_food_1);
            imgMapFood.put(R.id.radio_cooked_2_morning, R.drawable.img_cooked_food_2);
            imgMapFood.put(R.id.radio_cooked_2_evening, R.drawable.img_cooked_food_2);
            imgMapFood.put(R.id.radio_cooked_3_morning, R.drawable.img_cooked_food_3);
            imgMapFood.put(R.id.radio_cooked_3_evening, R.drawable.img_cooked_food_3);
            listOfIndexesFood = new ArrayList<>(imgMapFood.keySet());
        }

        // --------------- Water HashMap ------------------------------------
        {
            imgMapWater.put(R.id.radio_water_yes, R.drawable.water);
            imgMapWater.put(R.id.radio_water_no, R.drawable.imgtest);
        }

        RadioButton radioLageGolden = (RadioButton) findViewById(R.id.radio_large_golden);
        radioLageGolden.setChecked(true);
        RadioGroup radioGroupFoodMorning = findViewById(R.id.radio_group_food_morning);
        RadioGroup radioGroupFoodEvening = findViewById(R.id.radio_group_food_evening);
        RadioGroup radioGroupWater = (RadioGroup) findViewById(R.id.radio_group_water) ;

        btnNewNext = (Button)findViewById(R.id.btn_new_next);
        btnNewNext.setEnabled(false);
        //------------------------------------

        btnNewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedTypeId;
                                imgMapsIdDogs = R.drawable.large_golden;  //SetUp Img for chosen Dog
                                indexForSize = R.id.radio_large_golden;
// ---------------- img SetUp Players Choice for MorningFood----------------------------------
                selectedTypeId = radioGroupFoodMorning.getCheckedRadioButtonId();
                if (selectedTypeId != -1) {
                    for (Map.Entry<Integer, Integer> foodMorning : imgMapFood.entrySet()) {
                        if (foodMorning.getKey().equals(selectedTypeId)) {
                            imgMapsIdFoodMorning = foodMorning.getValue(); //SetUp Img for chosen Morning Food
                            indexForFood = listOfIndexesFood.indexOf(foodMorning.getKey());
                            Array.arr3[0] = indexesCaseMethod (indexForFood);
                        }
                    }
                }
// ---------------- img SetUp Players Choice for EveningFood----------------------------------
                selectedTypeId = radioGroupFoodEvening.getCheckedRadioButtonId();
                if (selectedTypeId != -1){
                    for (Map.Entry<Integer, Integer> foodEvening : imgMapFood.entrySet()){
                        if(foodEvening.getKey().equals(selectedTypeId)){
                            imgMapsIdFoodEvening = foodEvening.getValue();  //SetUp Img for chosen Evening Food
                            indexForFood = listOfIndexesFood.indexOf(foodEvening.getKey());
                            Array.arr3[1] = indexesCaseMethod (indexForFood);
                        }
                    }
                }
// ---------------- img SetUp Players Choice for Water----------------------------------
                selectedTypeId = radioGroupWater.getCheckedRadioButtonId();
                if (selectedTypeId != -1){
                    for (Map.Entry<Integer, Integer> water : imgMapWater.entrySet()){
                        if(water.getKey().equals(selectedTypeId)){
                            imgMapsIdWater = water.getValue();
                            Array.arr3[2] = 1;  // water Yes
                        }
                    }
                }else {
                    Array.arr3[2] = 2;   // water No
                }
                try {
                    Intent intent = new Intent(LevelOneDogsAndFood.this, LevelOne.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
//                    }
            }
        });

        Button btnNewBack = (Button)findViewById(R.id.btn_new_back);
        btnNewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(LevelOneDogsAndFood.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch(Exception e) {
                }
            }
        });

    }

    //-------------- indexForFood - MapIndex for pair K,V. ari = value for arr3[] ----------------
    //---------------- ar3[0] - Morning Food; ar3[0] - Evening Food; ar3[2] - Water; -------------
    private int indexesCaseMethod(int indexForFood) {
        int ari;
        switch (indexForFood+1) {
            case 1:
            case 2:
                ari = 11;
                break;
            case 3:
            case 4:
                ari = 12;
                break;
            case 5:
            case 6:
                ari = 13;
                break;
            case 7:
            case 8:
                ari = 14;
                break;
            case 9:
            case 10:
                ari = 15;
                break;
            case 11:
            case 12:
                ari = 16;
                break;
            default:
                ari = 0;
        }
        return ari;
    }


    //-------- One dog chosen from the nine of the tree RadioGroup ---------------------------------
    @SuppressLint("NonConstantResourceId")
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch(view.getId()) {
            //--------------- checking  radioFoodMorning  ----------------
            case R.id.radio_dry_1_morning:
            case R.id.radio_dry_2_morning:
            case R.id.radio_dry_3_morning:
            case R.id.radio_cooked_1_morning:
            case R.id.radio_cooked_2_morning:
            case R.id.radio_cooked_3_morning:
                if (checked){
                    checkedFoodMorning = true;
                }
                break;
            //--------------- checking  radioFoodEvening  ----------------
            case R.id.radio_dry_1_evening:
            case R.id.radio_dry_2_evening:
            case R.id.radio_dry_3_evening:
            case R.id.radio_cooked_1_evening:
            case R.id.radio_cooked_2_evening:
            case R.id.radio_cooked_3_evening:
                if (checked){
                    checkedFoodEvening = true;
                }
                break;
            //--------------- checking  radioWater  ----------------
            case R.id.radio_water_yes:
            case R.id.radio_water_no:
                if (checked){
                    checkedWater= true;
                }
                break;
        }
        if (checkedDogsGroups && checkedFoodMorning && checkedFoodEvening && checkedWater) {
            btnNewNext.setEnabled(true);
        }
    }

}
