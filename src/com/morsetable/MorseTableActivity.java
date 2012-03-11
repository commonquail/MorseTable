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
        allKeys = new ArrayList<MorseKey>(40);
        activeKeys = new ArrayList<MorseKey>(5);
        
        // Find all the MorseKey objects and store them in allKeys. We know how
        // many LinearLayouts there are so we can hardcode the limit.
        RelativeLayout rl = ((RelativeLayout)findViewById(R.id.relativeLayout1)); 
        LinearLayout ll;
        for (int i = 0; i < 4; i++) {
            ll = (LinearLayout)rl.getChildAt(i);
            for (int j = 0; j < ll.getChildCount(); j++) {
                allKeys.add((MorseKey)ll.getChildAt(j));
            }
        }
    }
    
    /**
     * This method makes the necessary UI changes when a Morse key is pressed,
     * including setting the code and updating the table hierarchy.
     * @param view the <code>MorseKey</code> that was pressed.
     */
    public void setMorseCode(View view) {
        for (MorseKey m : activeKeys) {
            m.setState(MorseKey.State.DEFAULT);
        }
        activeKeys.clear();
        
        MorseKey m0 = (MorseKey)view;
        MorseKey m1 = m0.parent();
        code.setText(m0.code());
        do {
            activeKeys.add(m0);
            m1 = m0;
            m0 = m0.parent();
        } while (m1 != m0);
        
        activeKeys.get(0).setState(MorseKey.State.PRESSED);
        for (int i = 1; i < activeKeys.size(); i++) {
            activeKeys.get(i).setState(MorseKey.State.PARENT);
        }
    }
    
    private ArrayList<MorseKey> allKeys;
    
    /**
     * Contains the most recent pressed key and all its ancestors for easier
     * updating.
     */
    private ArrayList<MorseKey> activeKeys;
    
    private TextView code;
}