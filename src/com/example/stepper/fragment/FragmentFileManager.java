package com.example.stepper.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.stepper.Grid;
import com.example.stepper.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FragmentFileManager extends Fragment implements AdapterView.OnItemClickListener {

    private List<String> item = null;
    private List<String> path = null;
    private String root = null;

    private TextView myPath;
    private ListView listView;

    public FragmentFileManager() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file_manager, null);

        listView = (ListView) view.findViewById(R.id.file_list_view);
        listView.setOnItemClickListener(this);
        myPath = (TextView) view.findViewById(R.id.path);
        root = Environment.getExternalStorageDirectory().getPath();

        getDir(root);

        return view;
    }

    private void getDir(String dirPath) {
        myPath.setText("Location: " + dirPath);

        item = new ArrayList<String>();
        path = new ArrayList<String>();

        File f = new File(dirPath);
        File[] files = f.listFiles();

        if (!dirPath.equals(root)) {
            item.add(root);
            path.add(root);
            item.add("../");
            path.add(f.getParent());
        }

        for (int i = 0; i < files.length; i++) {

            File file = files[i];
            path.add(file.getPath());
            if (file.isDirectory()) {
                item.add(file.getName() + "/");
            } else {
                item.add(file.getName());
            }
        }

        ArrayAdapter<String> fileList = new ArrayAdapter<String>(getActivity(), R.layout.file_explorer_item, item);
        listView.setAdapter(fileList);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        File file = new File(path.get(position));
        if (file.isDirectory()) {
            if (file.canRead()) {
                getDir(path.get(position));
            } else {
                new AlertDialog.Builder(getActivity())
                        .setTitle("[" + file.getName() + "] folder can't be read!")
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                    }
                                }).show();
            }
        } else {
            new AlertDialog.Builder(getActivity())
                    .setTitle("[" + file.getName() + "]")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // TODO Auto-generated method stub
                                }
                            }).show();
        }

    }
}