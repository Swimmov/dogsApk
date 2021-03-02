package dim.kinders.dogspoogolf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5500;
    ImageView nameAppImage, logoAppImage;
    Animation topAnimation, bottomAnimation;
    long backPressedTime;
    Toast backToast;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.main_top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.main_bottom_animation);

        MobileAds.initialize(this, "ca-app-pub-1502973434730162~8709421420");
        adView = findViewById(R.id.adView_level1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        nameAppImage = findViewById(R.id.image_main_text);
        logoAppImage = findViewById(R.id.main_img_dog);

        nameAppImage.setAnimation(topAnimation);
        logoAppImage.setAnimation(bottomAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LevelOneDogsAndFood.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);

    }

    public void startAndGo(View v) {
        Intent intent = new Intent(this, LevelOneDogsAndFood.class);
        startActivity(intent);
        finish();
    }

    //System button "Back" - start
    @Override
    public void onBackPressed(){    // Ctr + O -> find the method

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();  // close the game
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(),  R.string.btn_system_msg, Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    //System button "Back" - finish

}