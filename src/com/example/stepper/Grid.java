package com.example.stepper;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class Grid {

    public final static int MAX_BUTTONS_ROW = 16;
    public final static int MAX_CHANNELS = 4;
    private final static int TOTAL_BUTTONS = MAX_BUTTONS_ROW * MAX_CHANNELS;

    private final Activity activity;

    public List<ToggleButton> buttonContainer;

    public Grid(Activity activity) {
        this.activity = activity;
    }

    public void createGrid(View view) {
        buttonContainer = new ArrayList<ToggleButton>();

        LinearLayout container = (LinearLayout) view.findViewById(R.id.buttonParent);
        container.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, 1f);

        for (int i = 0; i < MAX_CHANNELS; i++) {

            LinearLayout linearLayout = new LinearLayout(activity);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(lp);
            linearLayout.setPadding(0, 0, 0, 5);

            for (int j = 0; j < MAX_BUTTONS_ROW; j++) {
                ToggleButton button = new ToggleButton(activity);
                button.setId(j + (i * MAX_BUTTONS_ROW));
                button.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.toggle_background));
                button.setLayoutParams(lp);
                clearButtonText(button);
                buttonContainer.add(button.getId(), button);
                linearLayout.addView(button);
            }
            container.addView(linearLayout, i);
        }
    }

    private void clearButtonText(ToggleButton button) {
        button.setText("");
        button.setTextOff("");
        button.setTextOn("");
    }

    public boolean[] saveGrid() {
        boolean[] toggleState = new boolean[TOTAL_BUTTONS];

        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            toggleState[i] = buttonContainer.get(i).isChecked();
        }
        return toggleState;
    }

    public void restoreGrid(boolean[] toggleState) {
        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            buttonContainer.get(i).setChecked(toggleState[i]);
        }
    }

    private void resetGrid() {
        for (int i = 0; i < TOTAL_BUTTONS; i++) {
            buttonContainer.get(i).setChecked(false);
        }
    }
}

