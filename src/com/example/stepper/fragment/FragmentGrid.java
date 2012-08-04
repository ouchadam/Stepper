package com.example.stepper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.stepper.Grid;
import com.example.stepper.R;
import com.example.stepper.Stepper;

public class FragmentGrid extends Fragment {

    private Grid grid;

    public FragmentGrid() {
    }

    public FragmentGrid(Grid grid) {
        this.grid = grid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid, null);
        if (grid != null) {
            grid.createGrid(view);
        }
        return view;
    }

    public Grid getGrid() {
        return grid;
    }
}
