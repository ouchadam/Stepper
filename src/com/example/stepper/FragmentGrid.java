package com.example.stepper;

import java.util.ArrayList;
import java.util.List;

import com.example.stepper.R;
import com.example.stepper.R.layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FragmentGrid extends Fragment implements OnClickListener{
	
	private final static int MAX_BUTTONS_ROW = 16;	
	private final static int MAX_CHANNELS = 4;	
	private final static int TOTAL_BUTTONS = MAX_BUTTONS_ROW * MAX_CHANNELS;	
	
	private List<ToggleButton> buttonContainer;
	
	private Playhead playHead;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {		
		
		View view = inflater.inflate(R.layout.fragment_grid, null);
		
		createGrid(view);
		
		return view;
	}
		
	private void createGrid(View view) {
		
		buttonContainer = new ArrayList<ToggleButton>();
		
		LinearLayout container = (LinearLayout) view.findViewById(R.id.buttonParent);
		container.setOrientation(LinearLayout.VERTICAL);
		
		LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1f); 
		
		for (int i = 0; i < MAX_CHANNELS; i ++) {
		
			LinearLayout linearLayout = new LinearLayout(getActivity());
			linearLayout.setOrientation(LinearLayout.HORIZONTAL);
			linearLayout.setLayoutParams(lp);
			
			for(int j = 0; j < MAX_BUTTONS_ROW; j++) {	
				ToggleButton button = new ToggleButton(getActivity());
				button.setId(j + (i * MAX_BUTTONS_ROW));
			    button.setOnClickListener(this);
			    button.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_background));
			    button.setLayoutParams(lp);			    

			    button.setText("");
			    button.setTextOff("");
			    button.setTextOn("");
			    
			    buttonContainer.add(button.getId(), button);
			    linearLayout.addView(button);
			}			
			container.addView(linearLayout, i);
		}
					
	}

	private boolean[] getToggleState() {
		
		boolean[] toggleState = new boolean[TOTAL_BUTTONS];
		
		for(int i = 0; i < TOTAL_BUTTONS; i ++) {
			toggleState[i] = buttonContainer.get(i).isChecked();
		}
		return toggleState;
	}
	
	public void onClick(View view) {		
		Toast.makeText(getActivity(), Integer.toString(view.getId()) + " " + Boolean.toString(
				buttonContainer.get(view.getId()).isChecked()), Toast.LENGTH_SHORT).show();		
	}

}
