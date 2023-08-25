Note: This is only for Specifying the code language and not for other purpose, all the code are inside the folder 



package com.gujarex.towntour.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.gujarex.towntour.R;
import com.gujarex.towntour.User.UserDashboard;

public class SplashScreen extends AppCompatActivity {

    // for splash screen time
    private static int SPLASH_TIMER = 5000;

    //Variables
    ImageView backgroundImage;
    TextView poweredByLine;

    // Animation Variables
    Animation sideAnim, bottomAnim;

    //sharedPreference Variables
    SharedPreferences onBoardingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        //Hooks for Variables
        backgroundImage = findViewById(R.id.background_image);
        poweredByLine = findViewById(R.id.powered_by_line);

        //hooks for animation
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        // fro set the animation
        backgroundImage.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);
                Boolean isFirstTime = onBoardingScreen.getBoolean("firstTime", true);
                if (isFirstTime) {

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), Onboarding.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreen.this, UserDashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIMER);

    }

}
