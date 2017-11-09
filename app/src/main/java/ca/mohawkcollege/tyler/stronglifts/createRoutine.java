package ca.mohawkcollege.tyler.stronglifts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class createRoutine extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();

        //if exerciseArray exists, Display items
        if(b.getStringArray("exerciseArray") != null){
            for(String s: b.getStringArray("exerciseArray")){
                Log.d("log", s);
            }
        }else{
            Log.d("log", "No exercises yet");
        }

    }

    public void add(View view){
        Bundle b = getIntent().getExtras();
        b.putString("exercise", "add");
        Intent i = new Intent(view.getContext(), Exercises.class);
        i.putExtras(b);
        startActivity(i);

    }

    public void save(View view){

        Bundle b = getIntent().getExtras();
        DBWorkouts dbhelper = new DBWorkouts(view.getContext());
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        //Insert routine in workouts
        ContentValues v = new ContentValues();
        v.put("Routine", b.getString("routine"));
        v.put("MuscleGroup", b.getString("group"));
        db.insert("Workouts", null, v);
        db.close();

        //create the table to hold all user selected exercises
        db = dbhelper.getWritableDatabase();
        v = new ContentValues();
        String n =  b.getString("routine");
        n=n.replace(" ", "_");
        String newTable ="CREATE TABLE "+ n + " ( _id INTEGER PRIMARY KEY, Exercise TEXT, sets INTEGER)";
        db.execSQL(newTable);
        db.close();

        for(String s: b.getStringArray("exerciseArray")){
            db = dbhelper.getWritableDatabase();
            v.put("Exercise", s);
            v.put("Sets", 0);
            db.insert(b.getString("routine"), null, v);
            db.close();
        }

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        return true;
    }
}
