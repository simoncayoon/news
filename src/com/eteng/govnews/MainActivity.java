package com.eteng.govnews;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener, OnTabChangeListener {

	/** 常量 **/
	private static final String TAG = "MainActivity";
	/** 控件类 **/
	private FragmentTabHost mTabHost;
	@SuppressWarnings("rawtypes")
	private final Class[] fragmentArray = { ImpNoticFragment.class,
			GovFragment.class, GuiZhouFragment.class };
	private List<Fragment> fragmentList;
	private ViewPager funcViewPager;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
	}

	private void initView() {
		initPager();
		// fragment tabhost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		
		/* 添加tab的图标 */
		// 得到fragment的个数
		int count = fragmentArray.length;
		int[] drawableID = { R.drawable.impo_notice_navi_selector,
				R.drawable.gov_news_navi_selector,
				R.drawable.gz_dynamic_navi_selector };
		for (int i = 0; i < count; i++) {
			TabSpec tabSpec = mTabHost.newTabSpec(TAG)
					.setIndicator(
							getIndicatorView(R.layout.tab_image_view,
									drawableID[i]));
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			// 设置Tab按钮的背景
		}
		mTabHost.setOnTabChangedListener(this);
	}

	private void initPager() {
		funcViewPager = (ViewPager) findViewById(R.id.viewpager);
		Fragment fragment1 = new ImpNoticFragment();
		Fragment fragment2 = new GovFragment();
		Fragment fragment3 = new GuiZhouFragment();
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(fragment1);
		fragmentList.add(fragment2);
		fragmentList.add(fragment3);
		funcViewPager.setAdapter(new FragmentPagerAdapter(
				getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return fragmentList.size();
			}

			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return fragmentList.get(position);
			}
		});
		funcViewPager.setOnPageChangeListener(this);
	}

	private View getIndicatorView(int layoutID, int imageID) {
		View view = getLayoutInflater().inflate(layoutID, null);
		ImageView navImg = (ImageView) view.findViewById(R.id.tab_navi_img);
		navImg.setImageResource(imageID);
		return view;
	}

	@Override
	public void onTabChanged(String tabId) {
		int position = mTabHost.getCurrentTab();
		funcViewPager.setCurrentItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int index) {
		TabWidget widget = mTabHost.getTabWidget();
		int oldFocusability = widget.getDescendantFocusability();
		widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		mTabHost.setCurrentTab(index);
		widget.setDescendantFocusability(oldFocusability);
	}
}
