package com.example.awpagerindicatorview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MainActivity extends Activity {

	private ViewPager mPager;
	private MyPagerAdapter mAdapter;
	private AWPagerIndicatorView mIndicator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		mPager = (ViewPager)findViewById(R.id.pager);

		mAdapter = new MyPagerAdapter();
		ArrayList<Integer> photos = new ArrayList<Integer>();
		photos.add(R.drawable.youna);
		photos.add(R.drawable.youn1);
		photos.add(R.drawable.youn2);
		photos.add(R.drawable.youn3);
		photos.add(R.drawable.youn4);
		
		mAdapter.setPhotoList(photos);
		mPager.setAdapter(mAdapter);

		mIndicator = (AWPagerIndicatorView)findViewById(R.id.indicator);
		mIndicator.setMarginNextButton(3);
		mIndicator.setViewPager(mPager, R.drawable.selector_paging_icon);
	}
	
	class MyPagerAdapter extends PagerAdapter{

		private ArrayList<Integer> mPhotoList;

		public void setPhotoList(ArrayList<Integer> photos) {
			// TODO Auto-generated method stub
			mPhotoList = photos;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mPhotoList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view==object;
		}

		@Override
		public void destroyItem(final ViewGroup container, final int position, final Object object) {
			((ViewPager) container).removeView((View) object);
		}

		@Override
		public Object instantiateItem(final ViewGroup container, final int position) {

			ImageView imageView = new ImageView(getApplicationContext());
			imageView.setImageResource(mPhotoList.get(position));
			imageView.setScaleType(ScaleType.CENTER_CROP);
			((ViewPager) container).addView(imageView, 0);
			return imageView;
		}
	}
}
