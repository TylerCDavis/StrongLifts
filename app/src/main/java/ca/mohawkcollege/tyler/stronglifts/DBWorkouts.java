package ca.mohawkcollege.tyler.stronglifts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tyler on 2017-10-03.
 */

public class DBWorkouts extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Database.db";
    public static final int DATABASE_VERSION = 1;

    public DBWorkouts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Workouts ="CREATE TABLE Workouts ( _id INTEGER PRIMARY KEY, Routine TEXT, MuscleGroup TEXT)";
        String Exercises =  "CREATE TABLE Exercises ( _id INTEGER PRIMARY KEY, Exercise TEXT, MuscleGroup TEXT, Completed INTEGER)";
        String Sets="CREATE TABLE Sets ( _id INTEGER PRIMARY KEY, Set_num INTEGER, Exercise TEXT, Weight INTEGER, Reps INTEGER, Date DATE) ";
        String History=" CREATE TABLE History ( _id INTEGER PRIMARY KEY, Date DATE, Routine TEXT, Exercise TEXT) ";

        String routine = "CREATE TABLE s5X5 (_id INTEGER PRIMARY KEY, Exercise TEXT, Sets INTEGER)";


        db.execSQL(Workouts);
        db.execSQL(Exercises);
        db.execSQL(Sets);
        db.execSQL(History);
        db.execSQL(routine);
db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        //db.execSQL("DROP TABLE IF EXISTS Workouts");
       // db.execSQL("DROP TABLE IF EXISTS Exercises");
        // Creating tables again
        onCreate(db);
    }


}
