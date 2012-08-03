package com.example.stepper;

import android.widget.ToggleButton;

public class Playhead {
	
	private ToggleButton prevToggle;
	
	private void swapBackground(ToggleButton tb) {
		
		if (prevToggle != null) {		
			prevToggle.setBackgroundResource(R.drawable.disabled);
		}
		
		tb.setBackgroundResource(R.drawable.play_head);
		
		prevToggle = tb;
	}

}
