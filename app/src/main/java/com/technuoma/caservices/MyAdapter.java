package com.technuoma.caservices;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class MyAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;
    public MyAdapter(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DefinitionFragment footballFragment = new DefinitionFragment();
                return footballFragment;
            case 1:
                BenefitsFragment cricketFragment = new BenefitsFragment();
                return cricketFragment;
            case 2:
                FormActivity nbaFragment = new FormActivity();
                return nbaFragment;

            case 3:
                ImageUploadActivity imgFragment = new ImageUploadActivity();
                return imgFragment;
            case 4:
                FaqFragment faqFragment = new FaqFragment();
                return faqFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}