package ca.mohawkcollege.tyler.stronglifts;

import android.content.Intent;
import android.database.Cursor;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Arrays;

public class Exercises extends AppCompatActivity {

    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);
try {
    String[] chestResults = Exercises("Chest");
    String[] backResults = Exercises("Back");
    String[] legResults = Exercises("Legs");
    String[] shoulderResults = Exercises("Shoulders");
    String[] armsResults = Exercises("Arms");
    String[] coreResults = Exercises("Core");

    Arrays.sort(chestResults);
    Arrays.sort(backResults);
    Arrays.sort(legResults);
    Arrays.sort(shoulderResults);
    Arrays.sort(armsResults);
    Arrays.sort(coreResults);

    //android.R.layout.simple_list_item_1
    //chest
    ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chestResults);
    final ListView chestRoutines = (ListView) findViewById(R.id.listChestRoutines);
    chestRoutines.setAdapter(adpter);


    //back
    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, backResults);
    final ListView backRoutines = (ListView) findViewById(R.id.listBackRoutines);
    backRoutines.setAdapter(adpter);

    //shoulders
    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shoulderResults);
    final ListView shoulderRoutines = (ListView) findViewById(R.id.listShouldersRoutines);
    shoulderRoutines.setAdapter(adpter);

    //Legs
    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, legResults);
    final ListView legRoutines = (ListView) findViewById(R.id.listLegsRoutines);
    legRoutines.setAdapter(adpter);

    //Arms
    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, armsResults);
    final ListView armRoutines = (ListView) findViewById(R.id.listArmsRoutines);
    armRoutines.setAdapter(adpter);

    //core
    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, coreResults);
    final ListView coreRoutines = (ListView) findViewById(R.id.listCoreRoutines);
    coreRoutines.setAdapter(adpter);

    listViewHeight(chestRoutines);
    listViewHeight(backRoutines);
    listViewHeight(legRoutines);
    listViewHeight(shoulderRoutines);
    listViewHeight(armRoutines);
    listViewHeight(coreRoutines);

    //ON CLICK LISTENERS to pass exercises back to create routine activity
    Bundle b = getIntent().getExtras();
    //If bundle b.exercise is add
    if(b.getString("exercise").equals("add")){
        chestRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = chestRoutines.getItemAtPosition(position).toString();
                exerciseSelected(exercise);

            }
        });
        backRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = backRoutines.getItemAtPosition(position).toString();
                exerciseSelected(exercise);
            }
        });
        legRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = legRoutines.getItemAtPosition(position).toString();
                exerciseSelected(exercise);
            }
        });
        shoulderRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = shoulderRoutines.getItemAtPosition(position).toString();
                exerciseSelected(exercise);
            }
        });
        armRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = armRoutines.getItemAtPosition(position).toString();
                exerciseSelected(exercise);
            }
        });
        coreRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String exercise = coreRoutines.getItemAtPosition(position).toString();
                exerciseSelected(exercise);
            }
        });
    }

    }catch(Exception e){}
    }

    //used to create a menu with a trash can icon
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_routines, menu);
        return true;
    }

    //returns a string array with all exercises for specific muscle group
    public String[] Exercises(String muscle){

        DBWorkouts workouts = new DBWorkouts(this);
        SQLiteDatabase db = workouts.getReadableDatabase();
        Cursor c = db.rawQuery("Select * FROM Exercises WHERE MuscleGroup = '" + muscle +"';", null);

        int x =c.getCount();
        String[] results = new String[x];

        int z=0;

        while(c.moveToNext()){
            results[z] = c.getString(c.getColumnIndex("Exercise") );
            z++;
        }
        db.close();
        return results;
    }

//this method is used when creating a new routine
//exercises selected by the user are saved in the bundle and returned to the createRoutine Activity
    public void exerciseSelected(String exercise){
        //get selected exercise, put in bundle, return to create routine
        Bundle b = getIntent().getExtras();
        Intent i = new Intent(this, createRoutine.class);

        if(b.getStringArray("exerciseArray") == null){
            String[] exerciseArray = {exercise};
            b.putStringArray("exerciseArray", exerciseArray);
        }else{
            //get all user selected exercises
            String[] exerciseArray = b.getStringArray("exerciseArray");
            //get the total number of exercises
            int x = exerciseArray.length;
            int y =0;
            //create a new array with one extra spot
            String[] userExercise = new String[x+1];
            //for each item in exerciseArray, add that item to userExercise
            for (String s: exerciseArray){
                userExercise[y] = s;
                y++;
            }
            // add the new user selected exercise to the new array
            userExercise[x] = exercise;
            //replace old exercise array with new array including users newest selection
            b.putStringArray("exerciseArray", userExercise);

        }


        i.putExtras(b);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(MenuItem item){


        ListView chestRoutines = (ListView) findViewById(R.id.listChestRoutines);
        ListView backRoutines = (ListView) findViewById(R.id.listBackRoutines);
        ListView shoulderRoutines = (ListView) findViewById(R.id.listShouldersRoutines);
        ListView legRoutines = (ListView) findViewById(R.id.listLegsRoutines);
        ListView armRoutines = (ListView) findViewById(R.id.listArmsRoutines);
        ListView coreRoutines = (ListView) findViewById(R.id.listCoreRoutines);

        switch(item.getItemId()){
            case R.id.action_delete:
                if(flag){
                    String[] chestResults = Exercises("Chest");
                    String[] backResults = Exercises("Back");
                    String[] legResults = Exercises("Legs");
                    String[] shoulderResults = Exercises("Shoulders");
                    String[] armsResults = Exercises("Arms");
                    String[] coreResults = Exercises("Core");

                    Arrays.sort(chestResults);
                    Arrays.sort(backResults);
                    Arrays.sort(legResults);
                    Arrays.sort(shoulderResults);
                    Arrays.sort(armsResults);
                    Arrays.sort(coreResults);

                    //chest
                    ArrayAdapter adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, chestResults);
                    chestRoutines.setAdapter(adpter);
                    chestRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    //back
                    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, backResults);
                     backRoutines.setAdapter(adpter);
                    backRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    //shoulders
                    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, shoulderResults);
                     shoulderRoutines.setAdapter(adpter);
                    shoulderRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    //Legs
                    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, legResults);
                    legRoutines.setAdapter(adpter);
                    legRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    //Arms
                    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, armsResults);
                     armRoutines.setAdapter(adpter);
                    armRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                    //core
                    adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, coreResults);
                    coreRoutines.setAdapter(adpter);
                    coreRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


                    flag=false;
                }else{

                    delete(chestRoutines);
                    delete(backRoutines);
                    delete(shoulderRoutines);
                    delete(legRoutines);
                    delete(armRoutines);
                    delete(coreRoutines);

                    finish();
                    startActivity(getIntent());
                }


                break;
            default:
                Intent i = new Intent(this, MainActivity.class);

                if(getIntent().getExtras() != null){

                    Bundle b = getIntent().getExtras();

                    //if this activity is loaded from the create routine activity, return to
                    //that activity with the bundle
                    if(b.getString("exercise").equals("add")){
                        i = new Intent(this, createRoutine.class);
                        i.putExtras(b);
                    }

                }

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

                    DBWorkouts workouts = new DBWorkouts(this);
                    SQLiteDatabase db = workouts.getWritableDatabase();
                    String sqlDelete = "DELETE FROM Exercises where Exercise = '"+sup+"'";
                    db.execSQL(sqlDelete);
                    //Log.i("log",sup + " was selected");
                }
            }
        }
    }

    //sets each listview height within the scrollView
    public void listViewHeight(ListView listView){

        ListAdapter listAdapter = listView.getAdapter();

        int numberOfItems = listAdapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        // Get total height of all item dividers.
        int totalDividersHeight = listView.getDividerHeight() *
                (numberOfItems - 1);

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();

    }

    //creates a dialog. Dialog creates new exercise
    public void create(View view) {
        exerciseDialog dialog = new exerciseDialog();

        dialog.show(getSupportFragmentManager(), "dialog");

    }
}
