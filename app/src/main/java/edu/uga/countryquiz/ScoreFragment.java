package edu.uga.countryquiz;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * this it the score fragment
 * it will display the score of the quiz
 * it has a button to take another quiz
 */
public class ScoreFragment extends Fragment {
    private static final String TAG = "scoreFragment";
    private int score;

    public ScoreFragment() {
        // Required empty public constructor
    }

    public static ScoreFragment newInstance( int score ) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putInt("score", score);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }
    @Override
    public void onViewCreated( View view, Bundle savedInstanceState ) {
        Log.d( TAG, "onActivityCreated()" );
        super.onViewCreated(view,savedInstanceState);
        if (getArguments() != null) {
            score = getArguments().getInt("score");
            System.out.println(score);
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
        Date today = new Date();
        String dateString = dateFormat.format(today);
        view.setBackgroundColor(Color.argb(255, 255, 253, 208));
        TextView textView = getView().findViewById( R.id.textView );
        String result = "Score: " + score + "/6";
        textView.setText(result);
        TextView date = getView().findViewById( R.id.label );
        date.setText(dateString);
        Button restartButton = getView().findViewById( R.id.button );
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
            Fragment fragment = new QuizView();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.splash_screen, fragment);
            fragmentTransaction.commit();
    }

}