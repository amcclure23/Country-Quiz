package edu.uga.countryquiz;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.View;
import androidx.viewpager2.widget.ViewPager2;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


// This fragment presents one buttons for the user to start the quiz

public class StartPageFragment extends Fragment {

    private static final String TAG = "startPageFragment";
    // Store instance variables
    private String title;
    private int page;
    // Required public default constructor
    public StartPageFragment() {
    }
    public static StartPageFragment newInstance(int page, String title) {
        StartPageFragment fragment = new StartPageFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_page, container, false);
        TextView label = (TextView) view.findViewById(R.id.label);
        label.setText(page + " -- " + title);
        return view;
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {
        Log.d( TAG, "onActivityCreated()" );

        super.onViewCreated(view,savedInstanceState);

        Button quizViewButton = getView().findViewById( R.id.button );
        //quizViewButton.setOnClickListener( new ButtonClickListener() );

    }
/*
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick( View v ) {

        }
    }
*/
}