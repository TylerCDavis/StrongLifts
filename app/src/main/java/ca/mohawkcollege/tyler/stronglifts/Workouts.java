package ca.mohawkcollege.tyler.stronglifts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Arrays;

public class Workouts extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        final Bundle b = getIntent().getExtras();

        //Hide button when user wants to start a workout
        //Workouts.class is always started with a bundle
        if (b.get("Workouts").equals("Select")) {
            setTitle("Start A Workout");
        }

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);
        try {


            String[] bodyResults = Muscles("Full Body");
            String[] chestResults = Muscles("Chest");
            String[] backResults = Muscles("Back");
            String[] legResults = Muscles("Legs");
            String[] shoulderResults = Muscles("Shoulders");
            String[] armsResults = Muscles("Arms");
            String[] coreResults = Muscles("Core");


            //Full body workouts listview set up
            ArrayAdapter<String> adpter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bodyResults);
            final ListView bodyRoutines = (ListView) findViewById(R.id.listBodyRoutines);
            bodyRoutines.setAdapter(adpter);

            //chest
            adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, chestResults);
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

            //set listview height
            // (Inside a scrollview, listviews show 1 result and have a scroll bar.
            // This method shows all results in the listview and extends the scrollview instead )
            listViewHeight(bodyRoutines);
            listViewHeight(chestRoutines);
            listViewHeight(backRoutines);
            listViewHeight(legRoutines);
            listViewHeight(shoulderRoutines);
            listViewHeight(armRoutines);
            listViewHeight(coreRoutines);

            //intent must be declared outside adapterview constructors
            if (b.getString("Workouts").equals("View")) {

                    bodyRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = bodyRoutines.getItemAtPosition(position).toString();
                                //Start ViewRoutine activity and pass routineName for DB read
                                viewRoutine(routineName);
                            }

                        }
                    });
                    chestRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = chestRoutines.getItemAtPosition(position).toString();
                            //Start ViewRoutine activity and pass routineName for DB read
                                viewRoutine(routineName);
                            }
                        }
                    });
                    backRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = backRoutines.getItemAtPosition(position).toString();
                                viewRoutine(routineName);
                            }
                        }
                    });
                    legRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = legRoutines.getItemAtPosition(position).toString();
                                viewRoutine(routineName);
                            }
                        }
                    });
                    shoulderRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = shoulderRoutines.getItemAtPosition(position).toString();
                                viewRoutine(routineName);
                            }
                        }
                    });
                    armRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = armRoutines.getItemAtPosition(position).toString();
                                viewRoutine(routineName);
                            }
                        }
                    });
                    coreRoutines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if(flag){
                                String routineName = coreRoutines.getItemAtPosition(position).toString();
                                viewRoutine(routineName);
                            }
                        }
                    });
                }

        } catch (Exception e) {

        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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

    public void viewRoutine(String name){
        Intent i = new Intent(this, ViewRoutine.class);
        Bundle b = getIntent().getExtras();
        b.putString("routine", name);
        i.putExtras(b);
        startActivity(i);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        ListView fbRoutines = (ListView) findViewById(R.id.listBodyRoutines);
    ListView chestRoutines = (ListView) findViewById(R.id.listChestRoutines);
    ListView backRoutines = (ListView) findViewById(R.id.listBackRoutines);
    ListView shoulderRoutines = (ListView) findViewById(R.id.listShouldersRoutines);
    ListView legRoutines = (ListView) findViewById(R.id.listLegsRoutines);
    ListView armRoutines = (ListView) findViewById(R.id.listArmsRoutines);
    ListView coreRoutines = (ListView) findViewById(R.id.listCoreRoutines);

    switch(item.getItemId()){
        case R.id.action_delete:
            if(flag){
                String[] fbResults = Muscles("Full Body");
                String[] chestResults = Muscles("Chest");
                String[] backResults = Muscles("Back");
                String[] legResults = Muscles("Legs");
                String[] shoulderResults = Muscles("Shoulders");
                String[] armsResults = Muscles("Arms");
                String[] coreResults = Muscles("Core");

                //full body
                ArrayAdapter adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, fbResults);
                fbRoutines.setAdapter(adpter);
                fbRoutines.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                //chest
                adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, chestResults);
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

                delete(fbRoutines);
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
        case R.id.action_add:
            routineDialog dialog = new routineDialog();

            dialog.show(getSupportFragmentManager(), "dialog");
            break;
        default:
            Intent i = new Intent(this, MainActivity.class);
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
                    sup=sup.replace(" ", "_");
                    String sqlDelete = "DELETE FROM Workouts where Routine = '"+sup+"'";
                    String dropWorkout = "DROP TABLE IF EXISTS "+ sup;

                    db.execSQL(sqlDelete);
                    db.execSQL(dropWorkout);
                    db.close();
                    //Log.i("log",sup + " was selected");
                }
            }
        }
    }


    public String[] Muscles(String muscle) {

        DBWorkouts workouts = new DBWorkouts(this);
        SQLiteDatabase db = workouts.getReadableDatabase();

        Cursor c = db.rawQuery("Select Routine, MuscleGroup FROM Workouts WHERE MuscleGroup = '" + muscle + "';", null);

        int x = c.getCount();
        String[] results = new String[x];

        int z = 0;

        while (c.moveToNext()) {
            results[z] = c.getString(c.getColumnIndex("Routine"));
            results[z] = results[z].replace("_"," ");
            z++;
        }
        db.close();
        return results;
    }

    public void listViewHeight(ListView listView) {

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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Workouts Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
