package edu.uga.countryquiz;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Strings for naming database table and attributes
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_COUNTRIES = "Countries";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_CONTINENT = "Continent";
    private static final String TABLE_RESULTS = "Results";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_SCORE = "Score";

    public DatabaseHelper(Context context) {
        super(context, "countries.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCountriesTable = "CREATE TABLE " + TABLE_COUNTRIES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_CONTINENT + " TEXT)";

        String createResultsTable = "CREATE TABLE " + TABLE_RESULTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_DATE + " TEXT," +
                COLUMN_SCORE + " INTEGER)";

        db.execSQL(createCountriesTable);
        db.execSQL(createResultsTable);
    }

    public void recordResults (String date, int score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_SCORE, score);
        db.insert(TABLE_RESULTS, null, values);
        db.close();
    }

    public interface MultipleCountriesCallback {
        void onMultipleCountriesLoaded(List<String[]> rows);
    }

    public void getMultipleCountriesAsync(final int numRows, final MultipleCountriesCallback callback) {
        new Thread(() -> {
            boolean hasDuplicates;
            List<String[]> countries;
            do {
                hasDuplicates = false;
                countries = new ArrayList<>();
                SQLiteDatabase db = getReadableDatabase();
                String countQuery = "SELECT COUNT(*) FROM " + TABLE_COUNTRIES;
                Cursor cursor = db.rawQuery(countQuery, null);
                int rowCount = 0;
                if (cursor.moveToFirst()) {
                    rowCount = cursor.getInt(0);
                }
                cursor.close();
                Random rand = new Random();
                for (int i = 0; i < numRows; i++) {
                    int row = rand.nextInt(rowCount) + 1;
                    cursor = db.rawQuery("select * from Countries where ID='" + row + "'",null);
                    if (cursor.moveToFirst()) {
                        do {
                            String[] country = new String[2];
                            country[0] = cursor.getString(1);
                            country[1] = cursor.getString(2);
                            countries.add(country);
                        } while (cursor.moveToNext());
                    }
                }
                cursor.close();
                db.close();
                for (int i = 0; i < countries.size() - 1; i++) {
                    String[] row1 = countries.get(i);
                    for (int j = i + 1; j < countries.size(); j++) {
                        String[] row2 = countries.get(j);
                        if (Arrays.equals(row1, row2)) {
                            hasDuplicates = true;
                            break;
                        }
                    }
                    if (hasDuplicates) {
                        break;
                    }
                }
                if (!hasDuplicates) {
                    callback.onMultipleCountriesLoaded(countries);
                }
            } while (hasDuplicates);
        }).start();
    }


    public interface ResultsCallback {
        void onResultsLoaded(List<String[]> rows);
    }

    public void getResultsAsync(final ResultsCallback callback) {
        new Thread(() -> {
            List<String[]> results = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            String selectQuery = "SELECT Date, Score FROM " + TABLE_RESULTS;
            Cursor cursor = db.rawQuery(selectQuery, null);
            while (cursor.moveToNext()) {
                String[] row = new String[2];
                row[0] = cursor.getString(0);
                row[1] = cursor.getString(1);
                results.add(row);
            }
            cursor.close();
            db.close();

            callback.onResultsLoaded(results);
        }).start();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESULTS);
        onCreate(db);
    }
}
