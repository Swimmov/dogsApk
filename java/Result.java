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

import java.util.Random;

public class Result extends AppCompatActivity {
    Random random = new Random();
    float distPlayer, disTrash;
    String dis;
    int count = 1;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final Button btnReady = (Button) findViewById(R.id.btnpushtothrow);
        final TextView playerDistance = (TextView) findViewById(R.id.txtrezplayer);
        final TextView trashDistance = (TextView) findViewById(R.id.txtreztrash);

        final ImageView imgPodiumBackgr = (ImageView) findViewById(R.id.imgwinner);
        final ImageView imgBoyNoBag = (ImageView) findViewById(R.id.img_boy);

        final ImageView imgCan_1R = (ImageView) findViewById(R.id.img_can1_right);
        final ImageView imgCan_1L = (ImageView) findViewById(R.id.img_can1_left);
        final ImageView imgCan_1 = (ImageView) findViewById(R.id.img_can1);

        final ImageView imgCan_2R = (ImageView) findViewById(R.id.img_can2_right);
        final ImageView imgCan_2L = (ImageView) findViewById(R.id.img_can2_left);
        final ImageView imgCan_2 = (ImageView) findViewById(R.id.img_can2);

        final ImageView imgCan_3R = (ImageView) findViewById(R.id.img_can3_right);
        final ImageView imgCan_3L = (ImageView) findViewById(R.id.img_can3_left);
        final ImageView imgCan_3 = (ImageView) findViewById(R.id.img_can3);

//switch on animation - start
           final Animation a = AnimationUtils.loadAnimation(Result.this, R.anim.alpha);
           final Animation my = AnimationUtils.loadAnimation(Result.this, R.anim.myalpha);
//switch on animation - finish

           disTrash = random.nextFloat() * 10.25f + 65.75f;
           distPlayer = foodBank(Array.arr3[0], Array.arr3[1], Array.arr3[2]);

           //player's chose - Can_1 (Lage size)
           imgCan_1.setOnTouchListener(new View.OnTouchListener() {
             @Override
              public boolean onTouch(View v, MotionEvent event) {
                   if (event.getAction() == MotionEvent.ACTION_DOWN) {
                       dis = toStringMethod(disTrash);
                       trashDistance.setText("Distance to the can: " + dis + " feet");

                       btnReady.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {

                               imgBoyNoBag.setImageResource(R.drawable.imgwalking_no_bag);  // set the img boy and no bag
                        imgCan_1.setEnabled(false);
                        imgCan_2.setEnabled(false);
                        imgCan_3.setEnabled(false);

                               // if player has Large Size breed
                               if (Array.arrPlayersSum[0] > 10 && Array.arrPlayersSum[0] < 20) {
                                   if (distanceCentrLeftRight(disTrash, distPlayer) == 0) {
                                       btnReady.setText("WINNER");
                                       imgCan_1.setImageResource(R.drawable.img_can_and_bag);
                                       imgCan_1.startAnimation(a);
                                       imgPodiumBackgr.setImageResource(R.drawable.podium);
                                       imgPodiumBackgr.startAnimation(my);
                                   }
                                   if (distanceCentrLeftRight(disTrash, distPlayer) > 0) {
                                       imgCan_1L.setImageResource(R.drawable.img_bag_70x70);
                                       imgCan_1L.startAnimation(a);
                                       btnReady.setText("TOO FAR");
                                       imgPodiumBackgr.setImageResource(R.drawable.podium2);
                                       imgPodiumBackgr.startAnimation(my);
                                   }
                                   if (distanceCentrLeftRight(disTrash, distPlayer) < 0) {
                                       imgCan_1R.setImageResource(R.drawable.img_bag_70x70);
                                       imgCan_1R.startAnimation(a);
                                       btnReady.setText("TOO CLOSE");
                                       imgPodiumBackgr.setImageResource(R.drawable.podium2);
                                       imgPodiumBackgr.startAnimation(my);
                                   }
                                   dis = toStringMethod(distPlayer);
                                   playerDistance.setText("your RESULT: " + dis + " feet");

                               }

                               // if player has Medium Size breed
                               if (Array.arrPlayersSum[0] > 20 && Array.arrPlayersSum[0] < 30) {

                                   imgCan_2L.setImageResource(R.drawable.img_bag_70x70);
                                   imgCan_2L.startAnimation(a);
                                   btnReady.setText("TOO TOO CLOSE");
                                   imgPodiumBackgr.setImageResource(R.drawable.podium2);
                                   imgPodiumBackgr.startAnimation(my);

                                   dis = toStringMethod(distPlayer * 0.75f);
                                   playerDistance.setText("your RESULT: " + dis + " feet");

                               }

                               // if player has Small Size breed
                               if (Array.arrPlayersSum[0] > 30 && Array.arrPlayersSum[0] < 40) {

                                   imgCan_3L.setImageResource(R.drawable.img_bag_70x70);
                                   imgCan_3L.startAnimation(a);
                                   btnReady.setText("TOO TOO CLOSE");
                                   imgPodiumBackgr.setImageResource(R.drawable.podium2);
                                   imgPodiumBackgr.startAnimation(my);

                                   dis = toStringMethod(distPlayer * 0.5f);
                                   playerDistance.setText("your RESULT: " + dis + " feet");

                               }
                           }
                       });
                      // ---


              } else
              if (event.getAction() == MotionEvent.ACTION_UP) {
                }
               return true;
               }
           });

//-----------------------------------------------------------------------------CAN 2-----------------------------------------------------------------------------------------

           //player's chose - Can_2 (Medium size)
           imgCan_2.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View v, MotionEvent event)  {
                   if (event.getAction() == MotionEvent.ACTION_DOWN) {

                       dis = toStringMethod(disTrash*0.75f);
                       trashDistance.setText("Distance to the can: " + dis + " feet");

                       btnReady.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               imgBoyNoBag.setImageResource(R.drawable.imgwalking_no_bag);  // set the img boy and no bag
                               imgCan_1.setEnabled(false);
                               imgCan_2.setEnabled(false);
                               imgCan_3.setEnabled(false);


                       // if player has Medium Size breed
                       if (Array.arrPlayersSum[0] > 20 && Array.arrPlayersSum[0] < 30){

                         // in to the can - BINGO
                          if (distanceCentrLeftRight(disTrash*0.75f, distPlayer*0.75f) == 0) {
                               btnReady.setText("WINNER");
                               imgCan_2.setImageResource(R.drawable.img_can_and_bag);
                               imgCan_2.startAnimation(a);
                               imgPodiumBackgr.setImageResource(R.drawable.podium);
                               imgPodiumBackgr.startAnimation(my);
                           }
                           // FAR
                           if (distanceCentrLeftRight(disTrash*0.75f, distPlayer*0.75f) > 0) {
                               imgCan_2L.setImageResource(R.drawable.img_bag_70x70);
                               imgCan_2L.startAnimation(a);
                               btnReady.setText("TOO FAR");
                               imgPodiumBackgr.setImageResource(R.drawable.podium2);
                               imgPodiumBackgr.startAnimation(my);
                           }
                           // BEFORE
                           if (distanceCentrLeftRight(disTrash*0.75f, distPlayer*0.75f) < 0) {
                               imgCan_2R.setImageResource(R.drawable.img_bag_70x70);
                               imgCan_2R.startAnimation(a);
                               btnReady.setText("TOO CLOSE");
                               imgPodiumBackgr.setImageResource(R.drawable.podium2);
                               imgPodiumBackgr.startAnimation(my);
                           }

                           dis = toStringMethod(distPlayer*0.75f);
                           playerDistance.setText("your RESULT: " + dis + " feet");

                       }

                       // if player has Large Size breed
                       if (Array.arrPlayersSum[0] > 10 && Array.arrPlayersSum[0] < 20) {

                           imgCan_1L.setImageResource(R.drawable.img_bag_70x70);
                           imgCan_1L.startAnimation(a);
                           btnReady.setText("TOO TOO FAR");
                           imgPodiumBackgr.setImageResource(R.drawable.podium2);
                           imgPodiumBackgr.startAnimation(my);

                           dis = toStringMethod(distPlayer);
                           playerDistance.setText("your RESULT: " + dis + " feet");

                       }

                       // if player has Small Size breed
                       if (Array.arrPlayersSum[0] > 30 && Array.arrPlayersSum[0] < 40) {

                           imgCan_3L.setImageResource(R.drawable.img_bag_70x70);
                           imgCan_3L.startAnimation(a);
                           btnReady.setText("TOO TOO CLOSE");
                           imgPodiumBackgr.setImageResource(R.drawable.podium2);
                           imgPodiumBackgr.startAnimation(my);

                           dis = toStringMethod(distPlayer*0.5f);
                           playerDistance.setText("your RESULT: " + dis + " feet");

                       }

                           }
                       });
                   }else
                   if (event.getAction() == MotionEvent.ACTION_UP) {
                   }
                   return true;
               }
           });

//-----------------------------------------------------------------------------CAN 3-----------------------------------------------------------------------------------------

           //player's chose - Can_3 (Small size)
           imgCan_3.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View v, MotionEvent event)  {
                   if (event.getAction() == MotionEvent.ACTION_DOWN) {

                       dis = toStringMethod(disTrash*0.5f);
                       trashDistance.setText("Distance to the can: " + dis + " feet");

                       btnReady.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               imgBoyNoBag.setImageResource(R.drawable.imgwalking_no_bag);  // set the img boy and no bag
                               imgCan_1.setEnabled(false);
                               imgCan_2.setEnabled(false);
                               imgCan_3.setEnabled(false);

                       // if player has Small Size breed
                       if (Array.arrPlayersSum[0] > 30 && Array.arrPlayersSum[0] < 40){

                           // in to the can - BINGO
                           if (distanceCentrLeftRight(disTrash*0.5f, distPlayer*0.5f) == 0) {
                               btnReady.setText("WINNER");
                               imgCan_3.setImageResource(R.drawable.img_can_and_bag);
                               imgCan_3.startAnimation(a);
                               imgPodiumBackgr.setImageResource(R.drawable.podium);
                               imgPodiumBackgr.startAnimation(my);
                           }
                           // FAR
                           if (distanceCentrLeftRight(disTrash*0.5f, distPlayer*0.5f) > 0) {
                               imgCan_3L.setImageResource(R.drawable.img_bag_70x70);
                               imgCan_3L.startAnimation(a);
                               btnReady.setText("TOO FAR");
                               imgPodiumBackgr.setImageResource(R.drawable.podium2);
                               imgPodiumBackgr.startAnimation(my);
                           }
                           // BEFORE
                           if (distanceCentrLeftRight(disTrash*0.5f, distPlayer*0.5f) < 0) {
                               imgCan_3R.setImageResource(R.drawable.img_bag_70x70);
                               imgCan_3R.startAnimation(a);
                               btnReady.setText("TOO CLOSE");
                               imgPodiumBackgr.setImageResource(R.drawable.podium2);
                               imgPodiumBackgr.startAnimation(my);
                           }

                           dis = toStringMethod(distPlayer*0.5f);
                           playerDistance.setText("your RESULT: " + dis + " feet");
                       }

                       // if player has Large Size breed
                       if (Array.arrPlayersSum[0] > 10 && Array.arrPlayersSum[0] < 20) {

                           imgCan_1L.setImageResource(R.drawable.img_bag_70x70);
                           imgCan_1L.startAnimation(a);
                           btnReady.setText("TOO TOO TOO FAR");
                           imgPodiumBackgr.setImageResource(R.drawable.podium2);
                           imgPodiumBackgr.startAnimation(my);

                           dis = toStringMethod(distPlayer);
                           playerDistance.setText("your RESULT: " + dis + " feet");
                       }

                       // if player has Medium Size breed
                       if (Array.arrPlayersSum[0] > 20 && Array.arrPlayersSum[0] < 30) {

                           imgCan_2L.setImageResource(R.drawable.img_bag_70x70);
                           imgCan_2L.startAnimation(a);
                           btnReady.setText("TOO TOO TOO FAR");
                           imgPodiumBackgr.setImageResource(R.drawable.podium2);
                           imgPodiumBackgr.startAnimation(my);

                           dis = toStringMethod(distPlayer*0.75f);
                           playerDistance.setText("your RESULT: " + dis + " feet");
                    }
                           }
                       });
                   }else
                   if (event.getAction() == MotionEvent.ACTION_UP) {
                   }
                   return true;
               }
           });

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
           // button try one more
           Button button_try = findViewById(R.id.btn_try);

           button_try.setOnClickListener(new View.OnClickListener() {

                   @Override
                   public void onClick (View v){

                   try {
                           Intent intent = new Intent(Result.this, Result.class);
                           startActivity(intent); // open a new window
                           finish();              // close the old one
 // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                   } catch (Exception e) {
                   }
               }
           });

           //button Finish
           Button button_finish = findViewById(R.id.btnfinish);
           button_finish.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                   try {
                       Intent intent = new Intent(Result.this, MainActivity.class);
                       startActivity(intent); // open a new window
                       finish();              // close the old one
                   }catch(Exception e) {

                   }
               }
           });
    }



    String toStringMethod(float dstFloat) {
           return String.valueOf(dstFloat).substring(0,5);
    }

    int distanceCentrLeftRight (float distCan, float distPl) {
           int  disCLR = 0;
        if (Float.compare(distPl, (distCan + 0.5f)) > 0 )  {  // behind can
            disCLR = 1;
        }
        if (Float.compare(distPl, (distCan - 0.5f)) < 0 )  {  // before can
            disCLR = -1;
        }
                                                              //else into the can
        return disCLR;
    }


    float foodBank (int ari1, int ari2, int ari3){
        float fb = 0.0f;
            if ((ari1 == 11 && ari2 == 15 && ari3 == 2) || (ari1 == 12 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 16 && ari3 == 2) || (ari1 == 14 && ari2 == 12 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 12 && ari3 == 2) || (ari1 == 16 && ari2 == 12 && ari3 == 1) ||
                (ari1 == 12 && ari2 == 12 && ari3 == 1) || (ari1 == 15 && ari2 == 15 && ari3 == 2) ||
                (ari1 == 11 && ari2 == 13 && ari3 == 2) || (ari1 == 14 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 11 && ari3 == 2) || (ari1 == 16 && ari2 == 14 && ari3 == 2))
        {fb = random.nextFloat() * (75.50f - 71.75f) + 71.75f;}

            if ((ari1 == 11 && ari2 == 14 && ari3 == 2) || (ari1 == 12 && ari2 == 15 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 15 && ari3 == 2) || (ari1 == 14 && ari2 == 11 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 11 && ari3 == 2) || (ari1 == 16 && ari2 == 13 && ari3 == 2) ||
                (ari1 == 11 && ari2 == 11 && ari3 == 2) || (ari1 == 16 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 12 && ari2 == 11 && ari3 == 1) || (ari1 == 15 && ari2 == 16 && ari3 == 2) ||
                (ari1 == 14 && ari2 == 15 && ari3 == 1) || (ari1 == 13 && ari2 == 11 && ari3 == 1))
        {fb = random.nextFloat() * (73.50f - 70.75f)+ 70.75f;}

            if ((ari1 == 11 && ari2 == 16 && ari3 == 2) || (ari1 == 12 && ari2 == 14 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 14 && ari3 == 2) || (ari1 == 14 && ari2 == 13 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 13 && ari3 == 2) || (ari1 == 16 && ari2 == 11 && ari3 == 2) ||
                (ari1 == 12 && ari2 == 12 && ari3 == 2) || (ari1 == 15 && ari2 == 15 && ari3 == 1) ||
                (ari1 == 11 && ari2 == 12 && ari3 == 1) || (ari1 == 14 && ari2 == 15 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 12 && ari3 == 1) || (ari1 == 16 && ari2 == 15 && ari3 == 1))
        {fb = random.nextFloat() * (71.50f - 68.75f)+ 68.75f;}

            if ((ari1 == 11 && ari2 == 15 && ari3 == 1) || (ari1 == 12 && ari2 == 16 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 16 && ari3 == 1) || (ari1 == 14 && ari2 == 13 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 11 && ari3 == 1) || (ari1 == 16 && ari2 == 12 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 13 && ari3 == 2) || (ari1 == 14 && ari2 == 14 && ari3 == 2) ||
                (ari1 == 12 && ari2 == 13 && ari3 == 2) || (ari1 == 11 && ari2 == 13 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 14 && ari3 == 1) || (ari1 == 16 && ari2 == 14 && ari3 == 1))
        {fb = random.nextFloat() * (70.50f - 67.75f)+ 67.75f;}

            if ((ari1 == 11 && ari2 == 16 && ari3 == 1) || (ari1 == 12 && ari2 == 15 && ari3 == 2) ||
                (ari1 == 13 && ari2 == 15 && ari3 == 1) || (ari1 == 14 && ari2 == 12 && ari3 == 1) ||
                (ari1 == 15 && ari2 == 13 && ari3 == 1) || (ari1 == 16 && ari2 == 11 && ari3 == 1) ||
                (ari1 == 11 && ari2 == 11 && ari3 == 1) || (ari1 == 16 && ari2 == 16 && ari3 == 1) ||
                (ari1 == 12 && ari2 == 13 && ari3 == 1) || (ari1 == 15 && ari2 == 16 && ari3 == 1) ||
                (ari1 == 14 && ari2 == 16 && ari3 == 1) || (ari1 == 13 && ari2 == 12 && ari3 == 2))
        {fb = random.nextFloat() * (69.50f - 66.75f)+ 66.75f;}

            if ((ari1 == 11 && ari2 == 14 && ari3 == 1) || (ari1 == 12 && ari2 == 14 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 14 && ari3 == 1) || (ari1 == 14 && ari2 == 11 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 12 && ari3 == 1) || (ari1 == 16 && ari2 == 13 && ari3 == 1) ||
                (ari1 == 13 && ari2 == 13 && ari3 == 1) || (ari1 == 14 && ari2 == 14 && ari3 == 1) ||
                (ari1 == 12 && ari2 == 11 && ari3 == 2) || (ari1 == 11 && ari2 == 12 && ari3 == 2) ||
                (ari1 == 15 && ari2 == 14 && ari3 == 2) || (ari1 == 16 && ari2 == 15 && ari3 == 2))
        {fb = random.nextFloat() * (68.50f - 65.75f)+ 65.75f;}

        return fb;
    }

}
