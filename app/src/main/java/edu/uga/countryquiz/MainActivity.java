package edu.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    public String[] continents = new String[]{"Africa","Antarctica", "Asia", "Australia", "Europe", "North America", "South America"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // In your application's code:
        dbHelper = new DatabaseHelper(this);
        ReadCSV readCSV = new ReadCSV(this);
        readCSV.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database when the activity is destroyed
        dbHelper.close();
    }
}