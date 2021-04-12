package com.anikei.percents;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    Toolbar my_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        my_toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(my_toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, my_toolbar,
                R.string.navigation_draw_open, R.string.navigation_draw_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Operation1Fragment()).commit();
            navigationView.setCheckedItem(R.id.operation1);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.operation1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Operation1Fragment()).commit();

                break;
            case R.id.operation2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Operation2Fragment()).commit();
                break;
            case R.id.operation3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Operation3Fragment()).commit();
                break;
            case R.id.operation4:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Operation4Fragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
