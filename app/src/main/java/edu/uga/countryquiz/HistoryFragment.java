package edu.uga.countryquiz;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    /**
     * makes the table of data from past quizzes
     * @param view
     */
    public void init(View view){
        TableLayout ll = view.findViewById(R.id.displaylinear);


        for (int i = 0; i <results.size(); i++) {

            TableRow row= new TableRow(getContext());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            String[] quiz =results.get(i);
            tv = new TextView(getContext());
            tv.setText("date: "+quiz[0]);
            qty = new TextView(getContext());
            qty.setText(" score: "+quiz[1]);
            row.addView(qty);
            row.addView(tv);
            ll.addView(row,i);
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