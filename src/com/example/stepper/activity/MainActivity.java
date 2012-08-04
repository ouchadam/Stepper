package com.example.stepper.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.stepper.CanStep;
import com.example.stepper.Grid;
import com.example.stepper.R;
import com.example.stepper.Stepper;
import com.example.stepper.fragment.*;

public class MainActivity extends FragmentActivity implements CanStep {

    private final Handler myHandler = new Handler();
    final Runnable updateUIThread = new Runnable() {
        public void run() {
            stepper.updateUi();
        }
    };

    private FragmentGrid grid;
    private FragmentTransport transport;
    private FragmentPads pads;
    private FragmentFileManager fileManager;
    private FragmentMixer mixer;

    private Stepper stepper;
    private boolean isRunning = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initStepper();
    }

    @Override
    protected void onPause() {
        super.onPause();
        isRunning = false;
        stepper.reset();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_transport:
                replaceFragment(transport);
                break;
            case R.id.menu_pad:
                replaceFragment(pads);
                break;
            case R.id.menu_file_manager:
                replaceFragment(fileManager);
                break;
            case R.id.menu_mixer:
                replaceFragment(mixer);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.details, fragment);
        transaction.commit();
    }

    private void initFragments() {
        grid = new FragmentGrid(new Grid(this));
        transport = new FragmentTransport(this);

        pads = new FragmentPads();
        fileManager = new FragmentFileManager();
        mixer = new FragmentMixer();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.grid, grid);
        transaction.replace(R.id.details, transport);
        transaction.commit();
    }

    private void initStepper() {
        stepper = new Stepper(grid.getGrid(), this);
    }

    private void mainLoop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    // play sound
                    stepper.moveToNextStep();
                    myHandler.post(updateUIThread);
                }
            }
        }).start();
    }

    private boolean[] savedGrid;

    @Override
    public void startStepper() {
        isRunning = !isRunning;
        mainLoop();
    }

    @Override
    public void saveGrid() {
        savedGrid = grid.getGrid().saveGrid();
    }

    @Override
    public void restoreGrid() {
        grid.getGrid().restoreGrid(savedGrid);
    }

    @Override
    public void resetGrid() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
