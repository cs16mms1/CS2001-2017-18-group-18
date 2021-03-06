package brunel.csgroup18.squadlink;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private String username;
    public static String userid ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get Extras from login
        Intent intentExtras = getIntent();
        Bundle extrasBundle = intentExtras.getExtras();

        //Reference the navigation header to change header text
        NavigationView nav = (NavigationView) findViewById(R.id.nav_view);
        View header = nav.getHeaderView(0);
        TextView headerText = (TextView) header.findViewById(R.id.usernameHeader);

        //If the bundle contains items...
        if(!extrasBundle.isEmpty()){

            //If it contains the username
            if(extrasBundle.containsKey("username")){
                username= extrasBundle.getString("username");
                Log.i("Username passed",username);
                //Set the header to welcome that user
                headerText.setText("Welcome, "+username);
            }
            if(extrasBundle.containsKey("id")){
                userid = extrasBundle.getString("id");
                Log.i("ID Passed", userid);
            }
        }


        //Using our own action bar as default was disabled when creating nav drawer
        mToolbar = (Toolbar) findViewById(R.id.nav_action_bar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.closed);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Starts the overview fragment as default(overview fragment is at index 0 of menu list)
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Handles the event of when someone clicks a menu item. Starts the relevant fragment
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        //Depending on the menu item clicked, a case will be triggered starting a fragment
        switch (item.getItemId()){

            case(R.id.nav_overview):{
                OverviewFragment overviewFragment = new OverviewFragment();
                ft.replace(R.id.fragment_container, overviewFragment);
                ft.commit();
                mToolbar.setTitle("Overview");
            }
            break;
            case(R.id.nav_my_matches):{
                MatchesFragment matchesFragment = new MatchesFragment();
                ft.replace(R.id.fragment_container, matchesFragment);
                ft.commit();
                mToolbar.setTitle("My Matches");
            }

            break;
            case(R.id.nav_training_plans):{
                TrainingPlansFragment trainingFragment = new TrainingPlansFragment();
                ft.replace(R.id.fragment_container, trainingFragment);
                ft.commit();
                mToolbar.setTitle("Training Plans");
            }

            break;
            case(R.id.nav_nutrition_plans):{
                NutritionPlansFragment nutritionFragment = new NutritionPlansFragment();
                ft.replace(R.id.fragment_container, nutritionFragment);
                ft.commit();
                mToolbar.setTitle("Nutrition Plans");
            }

            break;
            case(R.id.nav_profile):{
                ProfileFragment profileFragment = new ProfileFragment();
                ft.replace(R.id.fragment_container, profileFragment);
                ft.commit();
                mToolbar.setTitle("My Profile");
            }
            break;
            case(R.id.nav_teamfinder):{
                TeamFinderFragment teamFinderFragment = new TeamFinderFragment();
                ft.replace(R.id.fragment_container, teamFinderFragment);
                ft.commit();
                mToolbar.setTitle("Team Finder");
            }

            break;
            case(R.id.nav_settings):{
                SettingsFragment settingsFragment = new SettingsFragment();
                ft.replace(R.id.fragment_container, settingsFragment);
                ft.commit();
                mToolbar.setTitle("Settings");
            }

            break;

        }

        //Closes naavigation drawer once an item has been selected
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    public static String getUserid() {
        return userid;
    }
}
