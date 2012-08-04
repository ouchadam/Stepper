package com.example.stepper;

import android.content.Context;
import android.widget.ToggleButton;

import java.util.List;

public class Playhead {

	private ToggleButton prevToggle;
    private List<ToggleButton> buttonContainer;
    private Context context;

    public Playhead(List<ToggleButton> buttonContainer, Context context) {
           this.buttonContainer = buttonContainer;
        this.context = context;
    }

    public void updatePlayHead(int position) {

        if (buttonContainer != null) {
            swapBackground(buttonContainer.get(position));
        }
    }

    private void swapBackground(ToggleButton tb) {
		if (prevToggle != null) {
            prevToggle.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toggle_background));
        }

        tb.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toggle_playhead_background));
		prevToggle = tb;
	}

    public void resetBackground(int position) {
        ToggleButton tb = buttonContainer.get(position);
        tb.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.toggle_background));
    }

}
