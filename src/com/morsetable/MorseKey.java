package com.morsetable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MorseKey extends Button {

    public MorseKey(Context context, AttributeSet attrs) {
        super(context, attrs);
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
            case R.styleable.MorseKey_parentKey:
            	// Calling this.getParent() here is too soon; the view 
            	// hierarchy hasn't been created yet. Make it a lazy
            	// assignment instead.
            	parentKey = a.getResourceId(attr, -1);
                break;
            case R.styleable.MorseKey_parentState:
            	parentState = a.getResourceId(attr, -1);
            	break;
            case R.styleable.MorseKey_pressedState:
            	pressedState = a.getResourceId(attr, -1);
            	break;
            }
        }
        a.recycle();
        
        background = this.getBackground();
    }

    public String code()
    {
        if (parent == null) {
        	parent = (MorseKey)((RelativeLayout)this.getParent().getParent()).findViewById(parentKey);
        }
        if (parent == this) {
        	return code;
        }
        return parent.code() + code;
    }
    
    public void colorPressed() {
    	this.setBackgroundResource(pressedState);
    }
    public void colorParent() {
    	this.setBackgroundResource(parentState);
    }
    
    public MorseKey parent()
    {
    	return parent;
    }
    
    public void reset()
    {
    	this.setBackgroundDrawable(background);
    }
    
    private int pressedState;
    
    private int parentState;
    
    private Drawable background;
    
    private int parentKey;
    
    private MorseKey parent;
    
    private String code;
}
