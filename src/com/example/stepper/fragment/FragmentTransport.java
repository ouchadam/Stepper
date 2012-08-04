package com.example.stepper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.stepper.CanStep;
import com.example.stepper.R;

public class FragmentTransport extends Fragment implements View.OnClickListener {

    private CanStep stepper;
    private Button button;

    public FragmentTransport() {
    }

    public FragmentTransport(CanStep stepper) {
        this.stepper = stepper;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transport, null);

        button = (Button) view.findViewById(R.id.start);
        button.setOnClickListener(this);

        Button buttonSave = (Button) view.findViewById(R.id.save);
        buttonSave.setOnClickListener(this);
        Button buttonRestore = (Button) view.findViewById(R.id.restore);
        buttonRestore.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                updateButtonText();
                stepper.startStepper();
                break;
            case R.id.save:
                stepper.saveGrid();
                break;
            case R.id.restore:
                stepper.restoreGrid();
                break;
        }
    }

    private void updateButtonText() {
        if (button.getText() == "Start!") {
            button.setText("Stop!");
        } else {
            button.setText("Start!");
        }
    }

}