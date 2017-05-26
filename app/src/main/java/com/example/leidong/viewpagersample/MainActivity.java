package com.example.leidong.viewpagersample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by leidong on 2017/5/22
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private ViewPager viewPager;
    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private FragmentAdapter mFragmentAdapter;

    private TextView main, moment, settings;
    private ImageView tabLine;

    //三个Fragmnet
    private MainFragment mainFragment;
    private MomentFragment momentFragment;
    private SettingsFragment settingsFragment;

    private int currentIndex;

    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }

    /**
     * 初始化控件
     */
    private void initWidgets() {
        main = (TextView) findViewById(R.id.mainTv);
        moment = (TextView)findViewById(R.id.momentTv);
        settings = (TextView) findViewById(R.id.settingTv);
        tabLine = (ImageView) findViewById(R.id.iv_tabline);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        mainFragment = new MainFragment();
        momentFragment = new MomentFragment();
        settingsFragment = new SettingsFragment();

        mFragmentList.add(mainFragment);
        mFragmentList.add(momentFragment);
        mFragmentList.add(settingsFragment);

        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), mFragmentList);
        viewPager.setAdapter(mFragmentAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tabLine.getLayoutParams();
                //0->1
                if(currentIndex == 0 && position == 0){
                    lp.leftMargin = (int) (positionOffset * (screenWidth*1.0/3) + currentIndex * (screenWidth/3));

                }
                //1->0
                else if(currentIndex == 1 && position == 0){
                    lp.leftMargin = (int) (-(1-positionOffset) * (screenWidth*1.0/3) + currentIndex * (screenWidth*1.0/3));
                }
                //1->2
                else if(currentIndex == 1 && position == 2){
                    lp.leftMargin = (int) (positionOffset * (screenWidth*1.0/3) + currentIndex * (screenWidth/3));
                }
                //2->1
                else{
                    lp.leftMargin = (int) (-(1-positionOffset) * (screenWidth*1.0/3) + currentIndex * (screenWidth/3));
                }
                tabLine.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch(position){
                    case 0:
                        main.setTextColor(Color.GREEN);
                        break;
                    case 1:
                        moment.setTextColor(Color.GREEN);
                        break;
                    case 2:
                        settings.setTextColor(Color.GREEN);
                        break;
                    default:
                        break;
                }
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(TAG, "onPageScrollStateChanged: " + state);
            }
        });
        initTabLineWidth();
    }

    private void resetTextView() {
        main.setTextColor(Color.BLACK);
        moment.setTextColor(Color.BLACK);
        settings.setTextColor(Color.BLACK);
    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) tabLine.getLayoutParams();
        lp.width = screenWidth / 3;
        tabLine.setLayoutParams(lp);
    }
}
