package com.example.euj.wargaming.Avtivity.PageViewer;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.euj.wargaming.Avtivity.Fragments.ProfileglobaleFragment;
import com.example.euj.wargaming.Avtivity.Fragments.HomeFragment;
import com.example.euj.wargaming.Avtivity.Fragments.TankFragment;
import com.example.euj.wargaming.R;
import com.example.euj.wargaming.utils.CustomViewPager;

/**
 * Created by Bouba on 19/11/2015.
 */
public class ProfilePageViewer extends Fragment {
    ProfileglobaleFragment ProfileglobaleFragment;
    HomeFragment Clan;
    TankFragment Hotfaits;
    public static CustomViewPager pager;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_pageviewer, container, false);
        pager = (CustomViewPager) rootView.findViewById(R.id.viewpager);
        TitleAdapter titleAdapter = new TitleAdapter(getChildFragmentManager());
        pager.setAdapter(titleAdapter);
        pager.setCurrentItem(0);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) rootView.findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setDrawFullUnderline(true);
        return rootView;

    }
    class TitleAdapter extends FragmentPagerAdapter {
        private String titles[] = new String[]{"Profile","Achivement", "Tanks"};
        public TitleAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public CharSequence getPageTitle (int position){
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return  ProfileglobaleFragment = new ProfileglobaleFragment();
                case 1:
                    return  Clan = new HomeFragment();
                case 2:
                    return  Hotfaits = new TankFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }









}
