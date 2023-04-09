package edu.uga.countryquiz;
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

    /**
     * constructor
     */
    public ScoreFragment() {
        // Required empty public constructor
    }

    /**
     * creates a new instance of score fragment
     * @param score
     * @return ScoreFragment
     */
    public static ScoreFragment newInstance( int score ) {
        ScoreFragment fragment = new ScoreFragment();
        Bundle args = new Bundle();
        args.putInt("score", score);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * on create
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *  Inflates the layout for this fragment
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    /**
     * sets up functionality to items in the layout.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
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