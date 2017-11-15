package ca.mohawkcollege.tyler.stronglifts;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class createRoutine extends AppCompatActivity {

    boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        setTitle(b.getString("routine").replace("_", " "));

        //if exerciseArray exists, Display items

        if(b.getStringArray("exerciseArray") != null){
            Log.d("log", "exerciseArray length: " + b.getStringArray("exerciseArray").length);
            Log.d("log", "All exercises in bundle:");
            for(String s: b.getStringArray("exerciseArray")){
                Log.d("log", s);
            }
            ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, b.getStringArray("exerciseArray"));
            ListView display = (ListView) findViewById(R.id.exerciseDisplay);
            display.setAdapter(adpter);
        }else{
            Log.d("log", "No exercises yet");
        }

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

    //used to create a menu with a trash can icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_routines, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){

        Bundle b = getIntent().getExtras();
        Intent i = new Intent(this, Exercises.class);
        ListView display = (ListView) findViewById(R.id.exerciseDisplay);
        String[] results = b.getStringArray("exerciseArray");

        switch(item.getItemId()) {

            case R.id.action_delete:
                if (flag && b.getStringArray("exerciseArray") != null) {

                    ArrayAdapter adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, results);
                    display.setAdapter(adpter);
                    display.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

                    flag=false;
                }else if (flag == false){
                    String[] deletes= new String[0];
//Long walk for a drink of water dude
//switch to arrayList
                    SparseBooleanArray checkedItems = display.getCheckedItemPositions();
                    if (checkedItems != null) {
                        Log.d("log", "Items seleced for delete:");
                        Log.d("log", "Checked Items size:" + checkedItems.size());
                        //array to hold the string value of items to delete
                        deletes= new String[checkedItems.size()];

                        for (int q = 0; q<checkedItems.size(); q++) {

                            Log.d("log", q + " " + String.valueOf(checkedItems.valueAt(q)));
                            if (checkedItems.valueAt(q)) {
                              deletes[q] = display.getAdapter().getItem(
                                        checkedItems.keyAt(q)).toString();
                                Log.d("log", deletes[q]);
                            }
                        }
                    }

                    String[] exerciseArray = b.getStringArray("exerciseArray");

                    String[] newArray = new String[exerciseArray.length - checkedItems.size()];

                    int y = 0;
                    int z=0;
//for loop to go through all the exercises and skip the selected ones for delete
                    for(int x =0; x< exerciseArray.length; x++){
                        if (exerciseArray[x].equals(deletes[y]) ){
//only increment y if the deletes array is long enough
                            if(y+1 != checkedItems.size()){

                                y++;
                            }

                        }else{
                            Log.d("log", "z = " + z);
                            newArray[z] = exerciseArray[x];
                            Log.d("log", "newArray[z] = " + newArray[z]);
                            z++;

                        }
                        Log.d("log", " ");
                    }
                    b.putStringArray("exerciseArray", newArray);
                    i = new Intent(this, createRoutine.class);
                    i.putExtras(b);
                    startActivity(i);
                }
                break;

            case R.id.action_add:

                b.putString("exercise", "add");
                i.putExtras(b);
                startActivity(i);
                break;

            default:
                i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }
}
