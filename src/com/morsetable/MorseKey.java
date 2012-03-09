package com.morsetable;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

public class MorseKey extends Button {

	public MorseKey(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		initMorseKey(attrs);
	}
	
	private void initMorseKey(AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MorseKey);
		final int N = a.getIndexCount();
		for (int i = 0; i < N; i++)
		{
			int attr = a.getIndex(i);
			switch (attr)
			{
			case R.styleable.MorseKey_code:
				code = a.getString(attr);
				break;
			}
		}
		a.recycle();
	}

	public String code()
	{
		return code;
	}
	
	private String code;
}
