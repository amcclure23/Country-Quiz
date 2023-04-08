package edu.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    public String[] continents = new String[]{"Africa","Antarctica", "Asia", "Australia", "Europe", "North America", "South America"};
    private String descriptionText = "This app provides quizzes where you are prompted with a country and must select the continent" +
            " continent that it exists in. There are 6 questions. You must swipe right-to-left to move onto the next question. Upon" +
            " completion you will be able to view previous quiz results or start a new quiz.";

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        TextView description = (TextView) findViewById(R.id.description);
        description.setText(descriptionText);
        dbHelper = new DatabaseHelper(this);
        ReadCSV readCSV = new ReadCSV(this);
        readCSV.execute();
        dbHelper.close();
        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }

    private void startQuiz() {
        Fragment fragment = new QuizView();
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