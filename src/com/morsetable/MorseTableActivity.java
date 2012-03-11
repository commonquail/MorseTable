package com.morsetable;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MorseTableActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		code = (TextView)findViewById(R.id.string);
        allKeys = new ArrayList<MorseKey>();
        activeKeys = new ArrayList<MorseKey>();
        RelativeLayout rl = ((RelativeLayout)findViewById(R.id.relativeLayout1)); 
		LinearLayout ll;
		for (int i = 0; i < 4; i++)
		{
			ll = (LinearLayout)rl.getChildAt(i);
			for (int j = 0; j < ll.getChildCount(); j++) {
				allKeys.add((MorseKey)ll.getChildAt(j));
			}
		}
    }
    
	public void setMorseCode(View view) {
		for (MorseKey m : activeKeys) {
			m.reset();
		}
		
		activeKeys.clear();
		MorseKey m0 = (MorseKey)view;
		code.setText(m0.code());
		MorseKey m1 = m0.parent();
		do {
			activeKeys.add(m0);
			m1 = m0;
			m0 = m0.parent();
		} while (m1 != m0);
		
		activeKeys.get(0).colorPressed();
		for (int i = 1; i < activeKeys.size(); i++) {
			activeKeys.get(i).colorParent();
		}
    }
	
	private ArrayList<MorseKey> allKeys;
	
	private ArrayList<MorseKey> activeKeys;
	
	private TextView code;
}