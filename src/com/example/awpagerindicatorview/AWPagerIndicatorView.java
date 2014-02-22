package com.example.awpagerindicatorview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class AWPagerIndicatorView extends LinearLayout implements ViewPager.OnPageChangeListener{

	private ViewPager mViewPager; 
	private int mSelectedIndex;
	private int mMarginNextButton;
	
	public AWPagerIndicatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setViewPager(ViewPager pager, int selectorResource){
		mViewPager = pager;
		mViewPager.setOnPageChangeListener(this);
		
		removeAllViews();
		PagerAdapter adapter = (PagerAdapter) mViewPager.getAdapter();
		int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			ImageView view = new ImageView(getContext());
			view.setPadding(mMarginNextButton, mMarginNextButton, mMarginNextButton, mMarginNextButton);
			view.setImageResource(selectorResource);
			addView(view);
		}
         
		if (mSelectedIndex > count) {
			mSelectedIndex = count - 1;
		}
		setCurrentItem(mSelectedIndex);
        
		requestLayout();
	}

	private void setCurrentItem(int item) {
		if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedIndex = item;
        mViewPager.setCurrentItem(item);

        int tabCount = getChildCount();
        for (int i = 0; i < tabCount; i++) {
            ImageView child = (ImageView) getChildAt(i);
            boolean isSelected = (i == item);
            child.setSelected(isSelected);
        }
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
	}

	public void setMarginNextButton(int marginNextButton) {
		DisplayMetrics dm =  getResources().getDisplayMetrics();
		mMarginNextButton = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, marginNextButton, dm);
	}

}
