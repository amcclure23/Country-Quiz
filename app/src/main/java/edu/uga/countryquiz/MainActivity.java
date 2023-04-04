package edu.uga.countryquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    public String[] continents = new String[]{"Africa","Antarctica", "Asia", "Australia", "Europe", "North America", "South America"};

    @Override

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ViewPager2 pager = findViewById( R.id.viewpager);
       QuestionPagerAdapter qpAdapter = new
               QuestionPagerAdapter(
              getSupportFragmentManager(), getLifecycle() );
        pager.setOrientation(
                ViewPager2.ORIENTATION_HORIZONTAL );
        pager.setAdapter( qpAdapter );

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