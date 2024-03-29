package com.example.android.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainTabFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public MainTabFragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new HeadlinesFragment();
        } else if (position == 1){
            return new LatestNewsFragment();
        } else {
            return new FavoriteNewsFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.headlines);
        }else if(position ==1){
            return mContext.getString(R.string.Latest);
        }else  {
            return mContext.getString(R.string.favorite);
        }
    }
}
