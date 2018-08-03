package com.ama.righthajj.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ama.righthajj.MainActivity;
import com.ama.righthajj.R;
import com.ama.righthajj.model.User;
import com.ama.righthajj.tasks.Task;

public class NavigationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ImageView tawaf ;
    ImageView jamarat;
    ImageView saffa;
    Task task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Task status = new Task(getApplicationContext());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ProgressBar bar = findViewById(R.id.progress_nbr_hajj);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        User user = (User)getIntent().getSerializableExtra("user");
        Toast.makeText(this, ""+user.getFullname(), Toast.LENGTH_SHORT).show();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);

        TextView fullName  = hView.findViewById(R.id.full_name);
        TextView duration = findViewById(R.id.duration);
        TextView availability = findViewById(R.id.nbr_hajj);


        duration.append("\t"+convert(user.getDuration()));
        fullName.setText(user.getFullname());
        status.getStatus("https://assiwad.000webhostapp.com/v1/status",availability,bar);

        tawaf = findViewById(R.id.Tawaf);
//        jamarat = findViewById(R.id.jamarat);
 //       saffa = findViewById(R.id.saffa);
        tawaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.getGatesStatus("https://assiwad.000webhostapp.com/v1/gate");
            }
        });
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_pref) {
            Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_wofoodi) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    String convert (int amount){
        int result = (int) amount/60;
        int rest = amount - (result * 60);
        if (rest != 0)
        return result+" H"+rest + " M";

        return result+" H";
    }
}
