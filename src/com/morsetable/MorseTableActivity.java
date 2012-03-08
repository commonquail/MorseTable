package com.morsetable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MorseTableActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
	public void setMorseCode(View view) {
		TextView t = (TextView)findViewById(R.id.string);
		t.setText(((MorseKey)view).code());
    }
}