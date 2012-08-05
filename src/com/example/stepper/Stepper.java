package com.example.stepper;

import android.content.Context;

import java.util.ArrayList;

public class Stepper {

    public static int tempoInMs = 250;

    private int stepPosition;
    ArrayList<Playhead> playheads;
    private long startTime;

    public Stepper(Grid grid, Context context) {
        stepPosition = 0;
        startTime = 0;
        playheads = new ArrayList<Playhead>();

        for (int i = 0; i < Grid.MAX_CHANNELS; i ++) {
            playheads.add(new Playhead(grid.buttonContainer, context));
        }
        updateUi();
    }

    public void updateUi() {
        updatePlayHead();     // + other ui related stuff
    }

    private void updatePlayHead() {
        for (int i = 0; i < Grid.MAX_CHANNELS; i ++) {
            playheads.get(i).updatePlayHead(stepPosition + (i * Grid.MAX_BUTTONS_ROW));
        }
    }

    public void moveToNextStep() {
        startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < (startTime + tempoInMs)) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stepPosition = (stepPosition < (Grid.MAX_BUTTONS_ROW - 1)) ? ++stepPosition : 0;
    }

    public void reset() {
        for (int i = 0; i < Grid.MAX_CHANNELS; i ++) {
            playheads.get(i).resetBackground(stepPosition + (i * Grid.MAX_BUTTONS_ROW));
        }
        stepPosition = 0;
    }
}

