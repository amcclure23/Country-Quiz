package edu.uga.countryquiz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;


/**
 * this it the score fragment
 * it will display the score of the quiz
 * it has a button to take another quiz
 */
public class ScoreFragment extends Fragment {
    private static final String TAG = "scoreFragment";
    private int score;
    private TextView textView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault());
    Date today = new Date();
    String dateString = dateFormat.format(today);
    private QuestionPagerAdapter adapter;
    private View view;


    /**
     * constructor
     */
    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * creates a new instance of score fragment
     *
     * @param score
     * @return ScoreFragment
     */
    public static ScoreFragment newInstance(int score) {
        ScoreFragment fragment = new ScoreFragment();
        fragment.score = score;
        return fragment;
    }

    /**
     * on create
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Inflates the layout for this fragment
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_score, container, false);
        textView = view.findViewById(R.id.textView);
        return view;
    }

    /**
     * sets up functionality to items in the layout.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated()");
        super.onViewCreated(view, savedInstanceState);
        view.setBackgroundColor(Color.argb(255, 255, 253, 208));
        textView = getView().findViewById(R.id.textView);
        String result = "Score: " + score + "/6";
        textView.setText(result);
        TextView date = getView().findViewById(R.id.label);
        date.setText(dateString);
        Button restartButton = getView().findViewById(R.id.button);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        Button viewHistoryButton = getView().findViewById(R.id.button2);
        viewHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the parent ViewPager2
                ViewPager2 viewPager2 = requireActivity().findViewById(R.id.viewpager);

                // Create a new instance of the fragment you want to show
                Fragment newFragment = new HistoryFragment();

                // Get the current adapter of the ViewPager2
                adapter = (QuestionPagerAdapter) viewPager2.getAdapter();

                // Get the position of the current fragment being shown
                int currentPosition = viewPager2.getCurrentItem();

                // Remove the current fragment from the adapter
                adapter.notifyItemRemoved(currentPosition);

                // Add the new fragment to the adapter at the same position as the old fragment
                adapter.addFragment(newFragment);

                // Set the new fragment as the current item in the ViewPager2
                viewPager2.setCurrentItem(currentPosition + 1);
            }
        });
    }

    public void updateScore(int newScore) {
        ;
        score = newScore;
        String result = "Score: " + score + "/6";
        if (textView != null) {
            textView.setText(result);
        }
        System.out.println(score);
        DatabaseHelper db = new DatabaseHelper(getContext());
        db.recordResults(dateString, score);
        db.close();
    }

    private void startQuiz() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        requireActivity().finish();
    }

}