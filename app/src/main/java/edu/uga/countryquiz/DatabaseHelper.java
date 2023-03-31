package edu.uga.countryquiz;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Implement database upgrade logic here
    }
}
