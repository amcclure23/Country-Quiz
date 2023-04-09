package edu.uga.countryquiz;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * this is the fragment that displays the past quizzes.
 * reads from the database
 */
public class HistoryFragment extends Fragment {
    private DatabaseHelper db;
    private TextView tv;
    private TextView qty;
    List<String[]> results = new ArrayList<>();

    /**
     * constructor
     */
    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * creates a nes instance of historyfragment
     *
     */
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

    /**
     * on create the databased gets the past results.
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final CountDownLatch latch = new CountDownLatch(1);
        db = new DatabaseHelper(getContext());
        db.getResultsAsync(new DatabaseHelper.ResultsCallback() {
            @Override
            public void onResultsLoaded(List<String[]> rows) {
                results = rows;
                // Count down the latch to release the blocked thread
                latch.countDown();
            }
        });
        db.close();

        try {
            // Block the thread until the latch has been counted down
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("countriesList", (Serializable) results);
    }

    /**
     * inflates layout
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
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        LinearLayout myLinearLayout = getActivity().findViewById(R.id.splash_content);
        myLinearLayout.setVisibility(View.GONE);
        return view;
    }

    /**
     * makes the table of data from past quizzes
     * @param view
     */
    public void init(View view){
        TableLayout ll = view.findViewById(R.id.table_layout);

        // Add data rows
        for (int i = results.size() - 1; i >= 0; i--) {
            String[] row = results.get(i);
            TableRow dataRow = new TableRow(getContext());
            TextView date = new TextView(getContext());
            date.setText(row[0]); // Assumes the date is in the first column of the row
            date.setGravity(Gravity.CENTER);// Assumes the date is in the first column of the row
            date.setPadding(16, 16, 16, 16); // add some padding
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                date.setTextAppearance(android.R.style.TextAppearance_Medium); // set the text appearance
            }
            TextView score = new TextView(getContext());
            score.setText(row[1]); // Assumes the date is in the first column of the row
            score.setGravity(Gravity.CENTER);// Assumes the date is in the first column of the row
            score.setPadding(16, 16, 16, 16); // add some padding
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                score.setTextAppearance(android.R.style.TextAppearance_Medium);
            }
            dataRow.addView(date);
            dataRow.addView(score);
            ll.addView(dataRow);
        }

        FloatingActionButton fab = view.findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // return to the main activity
                    Intent intent = new Intent(getContext(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    /**
     * when the view is created it calls init
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
}