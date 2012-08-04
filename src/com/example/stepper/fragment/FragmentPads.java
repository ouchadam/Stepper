package com.example.stepper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.stepper.Grid;
import com.example.stepper.R;

public class FragmentPads extends Fragment {

    private Grid grid;

    public FragmentPads() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pads, null);

        return view;
    }
}