package com.example.leidong.viewpagersample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leidong on 2017/5/22
 */

class FragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> lists = new ArrayList<>();

    FragmentAdapter(FragmentManager supportFragmentManager, ArrayList<Fragment> mFragmentList) {
        super(supportFragmentManager);
        this.lists = mFragmentList;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Fragment getItem(int position) {
        return lists.get(position);
    }
}
