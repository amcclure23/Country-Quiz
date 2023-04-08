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

 */
public class HistoryFragment extends Fragment {
    private DatabaseHelper db;
    private TextView tv;
    private TextView qty;
    List<String[]> results = new ArrayList<>();
    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        return fragment;
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
}