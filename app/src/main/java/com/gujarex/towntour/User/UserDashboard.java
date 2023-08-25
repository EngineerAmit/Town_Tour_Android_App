package com.gujarex.towntour.User;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.gujarex.towntour.Common.LoginSignup.RetailerStartUpScreen;
import com.gujarex.towntour.HelperClasses.HomeAdapter.CatAdapter;
import com.gujarex.towntour.HelperClasses.HomeAdapter.CatHelperClass;
import com.gujarex.towntour.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.gujarex.towntour.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.gujarex.towntour.HelperClasses.HomeAdapter.MostViewAdapter;
import com.gujarex.towntour.HelperClasses.HomeAdapter.MostViewHelper;
import com.gujarex.towntour.R;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * variables for animation and this is the initialization
     **/
    static final float END_SCALE = 0.7f;


    /**
     * RecyclerView banane ke baad unke variable declare karna
     */
    RecyclerView featuredRecycler, mostViewRecycler, CatRecycler;

    /**
     * ye recycler view adapter teeno
     * view yani featured, mostview and catrecycler me use
     * hota hai
     */
    RecyclerView.Adapter adapter;

    /**
     * gradient layout color ke liye
     **/
    private GradientDrawable gradient1;

    /**
     * hooks for drawer layout and navigation view
     **/
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    /**
     * hooks for menu button
     **/
    ImageView menuIcon;

    /**
     * animation ke liye poore content view ka hooks
     **/
    LinearLayout contentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_dashboard);

        /** hooks for Recycler view **/
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewRecycler = findViewById(R.id.mv_recycler);
        CatRecycler = findViewById(R.id.cat_recycler);

        /** hooks for Navigation Menu icon **/
        menuIcon = findViewById(R.id.menu_icon);

        /** content view yani poore screen ke animation ke liye **/
        contentView = findViewById(R.id.content);

        /** user dashboard ka poora first screen drawer layout hai
         * drawer layout ko animate karne aur use, close hai nahi check karne ke
         * liye
         */
        drawerLayout = findViewById(R.id.drawer_layout);

        /** hooks for navigation view **/
        navigationView = findViewById(R.id.navigation_view);

        /** navigationDrawer ke liye method **/
        navigationDrawer();

        /** teeno recycler view kw liye method**/
        featuredRecycler();
        mostViewRecycler();
        CatRecycler();

    }
    /**
     * Navigation drawer ke andar animation navigation drawer ka method create kiya
     **/

    private void navigationDrawer() {
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        /** Method for animation **/
        animateNavigationDrawer();
    }

    private void animateNavigationDrawer() {
        drawerLayout.setScrimColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);

            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_all_categories:
                Intent intent = new Intent(getApplicationContext(), Allcategories.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void CatRecycler() {
        CatRecycler.setHasFixedSize(true);
        CatRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<CatHelperClass> CatViewLocation = new ArrayList<>();
        CatViewLocation.add(new CatHelperClass(R.drawable.shopping_image, "welcome to my city"));
        CatViewLocation.add(new CatHelperClass(R.drawable.macdonal, "hi this is amit"));
        CatViewLocation.add(new CatHelperClass(R.drawable.education_image, "The world is beautiful"));
        CatViewLocation.add(new CatHelperClass(R.drawable.car_image, "welcome to my fantasy city"));


        adapter = new CatAdapter(CatViewLocation);
        CatRecycler.setAdapter(adapter);

    }

    private void mostViewRecycler() {
        mostViewRecycler.setHasFixedSize(true);
        mostViewRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MostViewHelper> MostViewLocation = new ArrayList<>();
        MostViewLocation.add(new MostViewHelper(R.drawable.city3, " The black City", "Mumbai, previously known as Bombay is one of the most populous and biggest cities of Maharashtra. Also the biggest metropolis of this state"));
        MostViewLocation.add(new MostViewHelper(R.drawable.city2, "The Engineer's City", "The world is beautiful,Mumbai is also popular as the entertainment and financial capital. It is the largest city of India and fondly called as the city of dreams."));
        MostViewLocation.add(new MostViewHelper(R.drawable.macdonal, "Mc Donald", "hi welcome to macdonal"));
        MostViewLocation.add(new MostViewHelper(R.drawable.city1, "city tour", "welcome to my city"));

        adapter = new MostViewAdapter(MostViewLocation);
        mostViewRecycler.setAdapter(adapter);

    }

    private void featuredRecycler() {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocation = new ArrayList<>();

        featuredLocation.add(new FeaturedHelperClass(R.drawable.city2, "The Engineer's City", "The world is beautiful,“Mumbai is like a giant piece of soul. Every time a new person comes to Mumbai and attach oneself to this city regardless of wherever you go after ..."));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.macdonal, "Mc Donald", "hi welcome to macdonald,“Few delights can equal the mere presence of one whom we trust utterly.” “Her heart - like every heart"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.city1, "city tour", "welcome to my city"));
        featuredLocation.add(new FeaturedHelperClass(R.drawable.city3, " The black City", "welcome to my fanticy city"));

        adapter = new FeaturedAdapter(featuredLocation);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600});


    }

    /**
     * retailer screen intent ke liye
     **/
    public void callRetailerScreens(View view) {
        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));
    }


    /**
     * categories me se vapas aane ke liye
     **/
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}