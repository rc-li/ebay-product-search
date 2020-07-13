package com.example.ebaysearch;

import androidx.annotation.NonNull;
import androidx.legacy.app.FragmentPagerAdapter;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
import android.app.Fragment;
import android.app.FragmentManager;

public class PageAdapter extends FragmentPagerAdapter {

    private int numTabs;

    public PageAdapter(@NonNull FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProductFrag();
            case 1:
                return new SellerInfoFrag();
            case 2:
                return new ShippingFrag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }
}
