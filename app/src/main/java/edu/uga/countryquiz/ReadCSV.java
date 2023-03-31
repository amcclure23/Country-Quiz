package edu.uga.countryquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadCSV extends AsyncTask<Void, Void, Void> {

    private Context context;

    public ReadCSV(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Open the CSV file from assets folder
            InputStream inputStream = context.getResources().openRawResource(R.raw.country_continent);
            // Create a BufferedReader object to read the file line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            // Create a new SQLite database and get a reference to it
            DatabaseHelper dbHelper = new DatabaseHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            // Read the file line by line and insert the data into the database
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(","); // Split the line into columns using comma delimiter

                // Insert the data into the database table
                ContentValues values = new ContentValues();
                values.put("Name", columns[0].trim());
                values.put("Continent", columns[1].trim());
                db.insert("Countries", null, values);
            }

            // Close the database and file reader
            db.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
