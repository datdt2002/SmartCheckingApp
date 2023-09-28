package vn.skymapglobal.smartcheckingapp.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import vn.skymapglobal.smartcheckingapp.fragments.HistoryFragment;
import vn.skymapglobal.smartcheckingapp.fragments.HomeFragment;
import vn.skymapglobal.smartcheckingapp.fragments.SettingFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    private Bundle dataBundel;

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                homeFragment.setArguments(dataBundel);
                return homeFragment;
            case 1:
                HistoryFragment historyFragment = new HistoryFragment();
                historyFragment.setArguments(dataBundel);
                return historyFragment;
            case 2:
                SettingFragment settingFragment = new SettingFragment();
                settingFragment.setArguments(dataBundel);
                return settingFragment;
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Home";
                break;
            case 1:
                title = "History";
                break;
            case 2:
                title = "Setting";
                break;
        }
        return title;
    }

    public void setDataBundel(Bundle bundle) {this.dataBundel = bundle;};

}
