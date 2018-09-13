package com.japangly.android.iwontdie;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.japangly.android.iwontdie.HomeTab.HomeFragment;
import com.japangly.android.iwontdie.PostTab.PostFragment;
import com.japangly.android.iwontdie.SettingTab.SettingFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabs);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home_fragment));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.post_fragment));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.setting_fragment));

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mainPagerAdapter.addFragment(new HomeFragment());
        mainPagerAdapter.addFragment(new PostFragment());
        mainPagerAdapter.addFragment(new SettingFragment());

        viewPager.setAdapter(mainPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
    }
}

