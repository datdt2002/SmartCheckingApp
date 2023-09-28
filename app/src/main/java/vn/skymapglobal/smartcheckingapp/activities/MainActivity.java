package vn.skymapglobal.smartcheckingapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import io.realm.Realm;
import vn.skymapglobal.smartcheckingapp.R;
import vn.skymapglobal.smartcheckingapp.adapters.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        // lấy thông tin userId từ login
        Realm realm = Realm.getDefaultInstance();
        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("username");
            Bundle bundleMain = new Bundle();
            bundleMain.putString("username", userName);
            viewPagerAdapter.setDataBundel(bundleMain);
        }
    }
}

