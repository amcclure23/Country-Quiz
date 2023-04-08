package edu.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private String descriptionText = "This app provides quizzes where you are prompted with a country and must select the continent" +
            " continent that it exists in. There are 6 questions. You must swipe right-to-left to move onto the next question. Upon" +
            " completion you will be able to view previous quiz results or start a new quiz.";
    List<String[]> countries;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(descriptionText);
        ReadCSV readCSV = new ReadCSV(this);
        readCSV.execute();
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        final CountDownLatch latch = new CountDownLatch(1);
        Fragment fragment = new QuizView();
        db = new DatabaseHelper(this);
        db.getMultipleCountriesAsync(6, new DatabaseHelper.MultipleCountriesCallback() {
            @Override
            public void onMultipleCountriesLoaded(List<String[]> rows) {
                if (rows.size() >= 6) {
                    countries = rows;
                    // Count down the latch to release the blocked thread
                    latch.countDown();
                }
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
        bundle.putSerializable("countriesList", (Serializable) countries);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.splash_screen, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}