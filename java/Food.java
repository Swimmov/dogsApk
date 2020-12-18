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

public class Food extends AppCompatActivity {
    Array arrFood = new Array();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button btnContinue = (Button)findViewById(R.id.btncontinuefood);

//--------- zero for food - start
        for (int i = 1; i<Array.arrPlayersSum.length; i++) {
            Array.arrPlayersSum[i] = 0;
        }
//--------- zero for food - finish

        final ImageView imgdryfood1 = (ImageView) findViewById(R.id.imgdryfood1);
        final ImageView imgdryfood2 = (ImageView) findViewById(R.id.imgdryfood2);
        final ImageView imgdryfood3 = (ImageView) findViewById(R.id.imgdryfood3);
        final ImageView imgcookdfood1 = (ImageView) findViewById(R.id.imgcookdfood1);
        final ImageView imgcookdfood2 = (ImageView) findViewById(R.id.imgcookdfood2);
        final ImageView imgcookdfood3 = (ImageView) findViewById(R.id.imgcookdfood3);

        final ImageView imgdryfood11 = (ImageView) findViewById(R.id.imgdryfood11);
        final ImageView imgdryfood22 = (ImageView) findViewById(R.id.imgdryfood22);
        final ImageView imgdryfood33 = (ImageView) findViewById(R.id.imgdryfood33);
        final ImageView imgcookdfood11 = (ImageView) findViewById(R.id.imgcookdfood11);
        final ImageView imgcookdfood22 = (ImageView) findViewById(R.id.imgcookdfood22);
        final ImageView imgcookdfood33 = (ImageView) findViewById(R.id.imgcookdfood33);

        //set chosen dog img STANDART BLOCKS => MORNING dry
        imgdryfood1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgdryfood1.setImageResource(arrFood.arrDryFood[3]);
                    imgdryfood2.setEnabled(false);
                    imgdryfood3.setEnabled(false);
                    imgcookdfood1.setEnabled(false);
                    imgcookdfood2.setEnabled(false);
                    imgcookdfood3.setEnabled(false);

                    Array.arrPlayersSum[1] = 11;
                    Array.arrPlayersSum[2] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

               imgdryfood2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgdryfood2.setImageResource(arrFood.arrDryFood[4]);

                    imgdryfood1.setEnabled(false);
                    imgdryfood3.setEnabled(false);
                    imgcookdfood1.setEnabled(false);
                    imgcookdfood2.setEnabled(false);
                    imgcookdfood3.setEnabled(false);

                    Array.arrPlayersSum[1] = 12;
                    Array.arrPlayersSum[2] = 0;


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgdryfood3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgdryfood3.setImageResource(arrFood.arrDryFood[5]);

                    imgdryfood2.setEnabled(false);
                    imgdryfood1.setEnabled(false);
                    imgcookdfood1.setEnabled(false);
                    imgcookdfood2.setEnabled(false);
                    imgcookdfood3.setEnabled(false);

                    Array.arrPlayersSum[1] = 13;
                    Array.arrPlayersSum[2] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        //set chosen dog img STANDART BLOCKS => MORNING cooked
        imgcookdfood1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgcookdfood1.setImageResource(arrFood.arrCookedFood[3]);

                    imgdryfood2.setEnabled(false);
                    imgdryfood3.setEnabled(false);
                    imgdryfood1.setEnabled(false);
                    imgcookdfood2.setEnabled(false);
                    imgcookdfood3.setEnabled(false);

                    Array.arrPlayersSum[2] = 14;
                    Array.arrPlayersSum[1] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgcookdfood2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgcookdfood2.setImageResource(arrFood.arrCookedFood[4]);

                    imgdryfood2.setEnabled(false);
                    imgdryfood3.setEnabled(false);
                    imgdryfood1.setEnabled(false);
                    imgcookdfood1.setEnabled(false);
                    imgcookdfood3.setEnabled(false);

                    Array.arrPlayersSum[2] = 15;
                    Array.arrPlayersSum[1] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgcookdfood3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgcookdfood3.setImageResource(arrFood.arrCookedFood[5]);

                    imgdryfood2.setEnabled(false);
                    imgdryfood3.setEnabled(false);
                    imgdryfood1.setEnabled(false);
                    imgcookdfood2.setEnabled(false);
                    imgcookdfood1.setEnabled(false);

                    Array.arrPlayersSum[2] = 16;
                    Array.arrPlayersSum[1] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });
//----------------------------------------------------------------------------------------------------------------
        //food for morning was chosen
//----------------------------------------------------------------------------------------------------------------
        //set chosen dog img STANDART BLOCKS => EVENING dry
        imgdryfood11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgdryfood11.setImageResource(arrFood.arrDryFood[3]);

                    imgdryfood22.setEnabled(false);
                    imgdryfood33.setEnabled(false);
                    imgcookdfood11.setEnabled(false);
                    imgcookdfood22.setEnabled(false);
                    imgcookdfood33.setEnabled(false);

                    Array.arrPlayersSum[3] = 11;
                    Array.arrPlayersSum[4] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgdryfood22.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgdryfood22.setImageResource(arrFood.arrDryFood[4]);

                    imgdryfood11.setEnabled(false);
                    imgdryfood33.setEnabled(false);
                    imgcookdfood11.setEnabled(false);
                    imgcookdfood22.setEnabled(false);
                    imgcookdfood33.setEnabled(false);

                    Array.arrPlayersSum[3] = 12;
                    Array.arrPlayersSum[4] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgdryfood33.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgdryfood33.setImageResource(arrFood.arrDryFood[5]);

                    imgdryfood22.setEnabled(false);
                    imgdryfood11.setEnabled(false);
                    imgcookdfood11.setEnabled(false);
                    imgcookdfood22.setEnabled(false);
                    imgcookdfood33.setEnabled(false);

                    Array.arrPlayersSum[3] = 13;
                    Array.arrPlayersSum[4] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        //set chosen dog img STANDART BLOCKS => EVENING COOKED
        imgcookdfood11.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgcookdfood11.setImageResource(arrFood.arrCookedFood[3]);

                    imgdryfood22.setEnabled(false);
                    imgdryfood33.setEnabled(false);
                    imgdryfood11.setEnabled(false);
                    imgcookdfood22.setEnabled(false);
                    imgcookdfood33.setEnabled(false);

                    Array.arrPlayersSum[4] = 14;
                    Array.arrPlayersSum[3] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgcookdfood22.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgcookdfood22.setImageResource(arrFood.arrCookedFood[4]);

                    imgdryfood22.setEnabled(false);
                    imgdryfood33.setEnabled(false);
                    imgdryfood11.setEnabled(false);
                    imgcookdfood11.setEnabled(false);
                    imgcookdfood33.setEnabled(false);

                    Array.arrPlayersSum[4] = 15;
                    Array.arrPlayersSum[3] = 0;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        imgcookdfood33.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //touch the img - start
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imgcookdfood33.setImageResource(arrFood.arrCookedFood[5]);

                    imgdryfood22.setEnabled(false);
                    imgdryfood33.setEnabled(false);
                    imgdryfood11.setEnabled(false);
                    imgcookdfood22.setEnabled(false);
                    imgcookdfood11.setEnabled(false);

                    Array.arrPlayersSum[4] = 16; // set up food for evening
                    Array.arrPlayersSum[3] = 0;  // preveuos set up to zero

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                }
                //touch the img - finish
                return true;
            }
        });

        //----------------------------------------------------------------------------------------------------------------

//        //code if you push the btnContinue -> to the
        btnContinue.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                try {

                    if(arrPartSumm(Array.arrPlayersSum) > 21) {

                        Intent intent = new Intent(Food.this, Water.class);
                        startActivity(intent);
                        finish();

                    }
                } catch(Exception e) {

               }

            }
        });

    //button Back -> to the previous Activity
        Button button_back_food = (Button)findViewById(R.id.button_back_food);
        button_back_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
     // shot down the window (activity - Size.java) and go to another activity. Here go to start window (MainActivity.java)
                try {
                        Intent intent = new Intent(Food.this, Size.class);
                        startActivity(intent); // open a new window
                        finish();              // close the old one
                }catch(Exception e) {
                }
            }
        });
 }


    static int arrPartSumm (int arr[]){
        int summ=0;
        for (int i = 1; i <arr.length ; i++) {
            summ = summ + arr[i];
        }

        return summ;
    }

        //System button "Back" - start
        @Override
        public void onBackPressed () {
            try {
                Intent intent = new Intent(Food.this, Size.class);
                startActivity(intent); // open a new window
                finish();              // close the old one
            } catch (Exception e) {

            }
        }
        //System button "Back" - finish

}
