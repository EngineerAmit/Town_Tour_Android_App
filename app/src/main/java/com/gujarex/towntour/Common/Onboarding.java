package com.gujarex.towntour.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gujarex.towntour.R;
import com.gujarex.towntour.HelperClasses.SliderAdapter;
import com.gujarex.towntour.User.UserDashboard;

public class Onboarding extends AppCompatActivity {

    //Variables fro viewing 4 to 5 pages
    ViewPager viewPager;

    //variables at which page you are namely there is a dot show
    LinearLayout dotsLayout;

    //slide karne ke liye
    SliderAdapter sliderAdapter;

    //dots kis page par use dekhne ke liye
    TextView[] dots;

    // variables for button
    Button letGetStarted;

    //last page animation ke liye
    Animation animation;

    //integer value hai ki kis page par hoon example 2 par ya teen par
    int currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_onboarding);
        //hooks according what you have given id
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        letGetStarted = findViewById(R.id.get_started_btn);

        //view pager me slide adapter set karne ke liye
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);


        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    //xml layout me skip button me onClick lagaya hoon ye setonclicklistener ki tarah hi kaam karta hai
    public void skip(View view){
        startActivity(new Intent(getApplicationContext(), UserDashboard.class));
        finish();

    }


    //next button par click karne par current page se +1 page aage move hoga
    public void next(View view){
        viewPager.setCurrentItem(currentPos+1);
        finish();
    }


    //dots ko mave karne par uske color aur page change hone ka code
    private void addDots(int position){

        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i =0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }

        if (dots.length >0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }


    //on page changed listener ke baare me hain ki kab page change hoga aur baki sab kya hoga
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //letgetstarted button ki visibility ke baare me
        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;

            if(position== 0){
                letGetStarted.setVisibility(View.INVISIBLE);
            }
            else if(position == 1){
                letGetStarted.setVisibility(View.INVISIBLE);
            }
            else if (position== 2){
                letGetStarted.setVisibility(View.INVISIBLE);
            }
            else {
                animation = AnimationUtils.loadAnimation(Onboarding.this,R.anim.bottom_anim);
                letGetStarted.setAnimation(animation);
                letGetStarted.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
