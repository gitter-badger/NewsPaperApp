package com.example.android.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SecTabFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SecTabFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new BusinessFragment();
        } else if(position == 1){
            return new TechNewsFragment();
        } else if(position == 2){
            return new EntertainmentNewsFragment();
        } else if (position == 3){
            return new SportsNewsFragment();
        } else if(position ==4){
            return new ScienceNewsFragment();
        } else {
            return new HealthNewsFragment();
        }
    }

    @Override
    public int getCount() {
        return 6;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.business);
        }else if(position ==1){
            return mContext.getString(R.string.technology);
        }else if(position == 2){
            return mContext.getString(R.string.entertainment);
        }else if(position == 3){
            return mContext.getString(R.string.sports);
        }else if(position == 4){
            return mContext.getString(R.string.science);
        }else {
            return mContext.getString(R.string.health);
        }
    }
}
