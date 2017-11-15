package ca.mohawkcollege.tyler.stronglifts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ViewRoutine extends AppCompatActivity {

    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_routine);

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        setTitle(b.getString("routine").replace("_"," "));

        String[] results = Exercises(b.getString("routine"));


        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
        ListView display = (ListView) findViewById(R.id.exerciseDisplay);
        display.setAdapter(adpter);

    }

    public String[] Exercises(String routine){

        DBWorkouts workouts = new DBWorkouts(this);
        SQLiteDatabase db = workouts.getReadableDatabase();

        Cursor c = db.rawQuery("Select Exercise from " + routine+";", null);

        int x = c.getCount();
        String[] results = new String[x];

        int z = 0;

        while (c.moveToNext()) {
            results[z] = c.getString(c.getColumnIndex("Exercise"));
            z++;
        }
        return results;
    }
    //used to create a menu with a trash can icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Bundle b = getIntent().getExtras();


        if (b.get("Workouts").equals("View")) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_routines, menu);

        }
        return true;
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

        Bundle b =getIntent().getExtras();
        Intent i = new Intent(this, Workouts.class);
        ListView display = (ListView) findViewById(R.id.exerciseDisplay);
        String[] results = Exercises(b.getString("routine"));

        switch(item.getItemId()) {

            case R.id.action_delete:
                if (flag) {

                    ArrayAdapter adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, results);
                    display.setAdapter(adpter);
                    display.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                    flag=false;
                }else{
                    delete(display);

                   finish();
                    startActivity(getIntent());
                }
                break;
            case R.id.action_add:
                b = getIntent().getExtras();
                b.putString("exercise", "addToView");
                i = new Intent(getApplicationContext(), Exercises.class);
                i.putExtras(b);
                startActivity(i);
                break;
            default:
                i.putExtras(b);
                startActivity(i);
        }

        return true;
    }
    //Deletes multiple selected exercises
    public void delete(ListView routine){
        SparseBooleanArray checkedItems = routine.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i)) {
                    String sup = routine.getAdapter().getItem(
                            checkedItems.keyAt(i)).toString();
                    //change this to delete statement
                    Bundle b =getIntent().getExtras();


                    DBWorkouts workouts = new DBWorkouts(this);
                    SQLiteDatabase db = workouts.getWritableDatabase();
                    String n = b.getString("routine");
                    n = n.replace(" ", "_");
                    String sqlDelete = "DELETE FROM " + n +" where Exercise = '"+sup+"'";

                    db.execSQL(sqlDelete);
                    db.close();
                    //Log.i("log",sup + " was selected");
                }
            }
        }
    }

}
