package com.example.mymultiplebackstack.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mymultiplebackstack.MyApplication;
import com.example.mymultiplebackstack.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment2 extends Fragment {
    private static final String TAG = "HomeFragment2";
    private TextView counterTv;
    private int counter = 0;
    public HomeFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.addToBackStack(R.id.navigation_home3);
                Navigation.findNavController(v).navigate(R.id.navigation_home3);
            }
        });

        counterTv = view.findViewById(R.id.counter);
        counterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterTv.setText(String.valueOf(++counter));
            }
        });
    }
}
