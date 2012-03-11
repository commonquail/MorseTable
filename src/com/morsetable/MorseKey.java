package com.morsetable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MorseKey extends Button {

	/**
	 * Possible states for a MorseKey.
	 * - DEFAULT: the default state.
	 * - PARENT: this key is a parent of a pressed or parent key.
	 * - PRESSED: this key was pressed. 
	 * @author Mikkel Kjeldsen
	 */
	public static enum State {DEFAULT, PARENT, PRESSED}
	
    public MorseKey(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMorseKey(attrs);
    }
    
    /**
     * Initialises custom attributes.
     * @param attrs the set of attributes passed to the constructor.
     */
    private void initMorseKey(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.MorseKey);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
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
            	parentKeyID = a.getResourceId(attr, -1);
                break;
                
            case R.styleable.MorseKey_parentState:
            	parentStateBackground = a.getResourceId(attr, -1);
            	break;
            	
            case R.styleable.MorseKey_pressedState:
            	pressedStateBackground = a.getResourceId(attr, -1);
            	break;
            }
        }
        a.recycle();
        
        background = this.getBackground();
    }

    /**
     * Returns the canonical Morse code for this Morse key.
     * The canonical Morse code of a given key is the <code>code</code> of that key
     * prepended to the <code>code</code> of that key's parent. 
     * @return the canonical Morse code for this key.
     */
    public String code() {
        if (parent == null) {
        	parent = (MorseKey)((RelativeLayout)this.getParent().getParent()).findViewById(parentKeyID);
        }
        if (parent == this) {
        	return code;
        }
        return parent.code() + code;
    }
    
    /**
     * Changes the state of this MorseKey.
     * @param s the new state.
     */
    public void setState(State s) {
    	switch (s)
    	{
    	case DEFAULT:
        	this.setBackgroundDrawable(background);
        	break;
        	
    	case PRESSED:
        	this.setBackgroundResource(pressedStateBackground);
        	break;
        	
    	case PARENT:
        	this.setBackgroundResource(parentStateBackground);
        	break;
    	}
    }
    
    /**
     * @return this Morse key's parent key.
     */
    public MorseKey parent() {
    	return parent;
    }
    
    /**
     * A reference to the background ID to use for the key that was pressed.
     */
    private int pressedStateBackground;
    
    /**
     * A reference to the background ID to use for all parent keys of the key
     * that was pressed.
     */
    private int parentStateBackground;
    
    private Drawable background;
    
    /**
     * We have to store the ID, then find the object later, since the hierarchy
     * isn't finalised when the constructor finishes.
     */
    private int parentKeyID;
    
    private MorseKey parent;
    
    /**
     * A single dit or dah character.
     */
    private String code;
}
