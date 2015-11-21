package com.example.euj.wargaming.Avtivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.euj.wargaming.Avtivity.Fragments.HomeFragment;
import com.example.euj.wargaming.Avtivity.Fragments.ListTanks;
import com.example.euj.wargaming.Avtivity.Fragments.TankFragment;
import com.example.euj.wargaming.Avtivity.PageViewer.ProfilePageViewer;
import com.example.euj.wargaming.R;

/**
 * Created by Bouba on 15/11/2015.
 *   //
 */
public class worldoftanksassistant extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener{
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    SharedPreferences user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worldoftanksassistant);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        user = getSharedPreferences("user", MODE_PRIVATE);
        drawerFragment.Name.setText(user.getString("name",""));
    }
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);

    }
    private void displayView(int position) {
        String title = getString(R.string.app_name);
        Fragment fragment=null;
        switch (position) {
            case 0:
                fragment =  new ProfilePageViewer();
                title ="Profile";
                break;
            case 1:
                fragment = new ListTanks();
                title = getString(R.string.title_home);
                break;
            case 2:
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
}
