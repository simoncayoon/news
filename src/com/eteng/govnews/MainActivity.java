package com.eteng.govnews;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class MainActivity extends FragmentActivity implements
		OnPageChangeListener {

	/** 常量 **/
	@SuppressWarnings("unused")
	private static final String TAG = "MainActivity";
	private ImageView titleImpo, titleGzdynamic, titleGovNews;

	/** 控件类 **/
	private List<Fragment> fragmentList;
	private ViewPager funcViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView() {

		titleImpo = (ImageView) findViewById(R.id.title_impo_notice);
		titleImpo.setSelected(true);// 设置默认选中效果
		titleGzdynamic = (ImageView) findViewById(R.id.title_gz_dynamic);
		titleGovNews = (ImageView) findViewById(R.id.title_gov_news);
		initPager();
	}

	private FragmentStatePagerAdapter pagerAdapter = new FragmentStatePagerAdapter(
			getSupportFragmentManager()) {

		@Override
		public int getCount() {
			return fragmentList.size();
		}

		@Override
		public Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return fragmentList.get(position);
		}
	};

	private void initPager() {
		funcViewPager = (ViewPager) findViewById(R.id.viewpager);
		Fragment fragment1 = new ImpNoticFragment();
		Fragment fragment2 = new GovFragment();
		Fragment fragment3 = new GuiZhouFragment();
		fragmentList = new ArrayList<Fragment>();
		fragmentList.add(fragment1);
		fragmentList.add(fragment2);
		fragmentList.add(fragment3);
		funcViewPager.setOffscreenPageLimit(3);
		funcViewPager.setAdapter(pagerAdapter);
		funcViewPager.setOnPageChangeListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int index) {
		if(index == 0){
			titleImpo.setSelected(true);
			titleGzdynamic.setSelected(false);
			titleGovNews.setSelected(false);
		}else if(index == 1){
			titleGzdynamic.setSelected(true);
			titleGovNews.setSelected(false);
			titleImpo.setSelected(false);
		}else if(index ==2){
			titleGovNews.setSelected(true);
			titleImpo.setSelected(false);
			titleGzdynamic.setSelected(false);
		}
	}


}
