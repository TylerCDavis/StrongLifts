package ca.mohawkcollege.tyler.stronglifts;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    public int menuNum;
    public Bundle b = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button train = (Button) findViewById(R.id.btnTrain);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b.putString("Workouts", "Select");
                Intent i = new Intent(v.getContext(), Workouts.class);
                i.putExtras(b);
                startActivity(i);

            }
        });

        Log.d("log", "log started for testing");

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);

        //Change the home button to 3hr
        DrawerLayout navDrawer = (DrawerLayout) findViewById(R.id.activity_main);

        ActionBarDrawerToggle navToggle = new ActionBarDrawerToggle(this, navDrawer, (R.string.open), (R.string.close));
        navDrawer.addDrawerListener(navToggle);
        navToggle.syncState();

//////////////// NAV DRAWER /////////////

        String[] navList = {"Workout History", "Charts", "Settings"};
        ArrayAdapter<String> adptr =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navList);


        ListView nav = (ListView) findViewById(R.id.nav);
        nav.setAdapter(adptr);

        nav.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                menuNum = position;

                DrawerLayout navDrawer = (DrawerLayout) findViewById(R.id.activity_main);

                boolean isOpen = navDrawer.isDrawerOpen(GravityCompat.START);

                if(isOpen){
                    navDrawer.closeDrawer(GravityCompat.START);
                }else {
                    navDrawer.openDrawer(GravityCompat.START);
                }
                Intent i = new Intent();
                switch(position){
                    case 0:
                        i = new Intent(view.getContext(), History.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(view.getContext(), Charts.class);
                        startActivity(i);
                        break;
                    case 2:
                        DBWorkouts helper = new DBWorkouts(view.getContext());
                        SQLiteDatabase dbb = helper.getWritableDatabase();

                        dbb.execSQL("DROP TABLE IF EXISTS Workouts");
                        dbb.execSQL("DROP TABLE IF EXISTS Exercises");
                        dbb.execSQL("DROP TABLE IF EXISTS Sets");
                        dbb.execSQL("DROP TABLE IF EXISTS History");
                        dbb.execSQL("DROP TABLE IF EXISTS s5X5");
                        dbb.execSQL("DROP TABLE IF EXISTS Chest_Day");
                        Log.d("log", "Database Deleted");
dbb.close();
                        finish();
                        startActivity(getIntent());
                        //i = new Intent(view.getContext(), Settings.class);
                        //startActivity(i);
                }
            }

        });
//////////////// END NAV DRAWER ///////////////////

/////////////// HOME MENU /////////////////////////
        String[] homeList = {"Workout Routines", "Exercises", "Body Measurements"};
        ArrayAdapter<String> adpter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, homeList);


        ListView homeNav = (ListView) findViewById(R.id.homeNav);
        homeNav.setAdapter(adpter);

        homeNav.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch(position){
                    case 0:
                        b.putString("Workouts", "View");
                        Intent i = new Intent(view.getContext(), Workouts.class);
                        i.putExtras(b);
                        startActivity(i);
                        break;
                    case 1:
                        b.putString("exercise", "view");
                        Intent in = new Intent(view.getContext(), Exercises.class);
                        in.putExtras(b);
                        startActivity(in);
                        break;
                    case 2:
                        Intent inte = new Intent(view.getContext(), Measurements.class);
                        startActivity(inte);
                }
            }
        });
////////////// END HOME MENU //////////////////

        // Check for Database

        DBWorkouts dbhelper = new DBWorkouts(this);
        SQLiteDatabase db = dbhelper.getReadableDatabase();

    try {
        Cursor cursor = db.rawQuery("SELECT * FROM Workouts", null);

        Context cont = getApplicationContext();
        CharSequence text = "database exists";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(cont, text, duration);
        toast.show();

    }catch(Exception e){

        Context cont = getApplicationContext();
        CharSequence text = "Creating Database";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(cont, text, duration);
        toast.show();
    //This needs to be commented out for new devices.
        dbhelper.onCreate(db);

        popDB newDB = new popDB();
        newDB.populateDB(this);
        db.close();
    }
    }

    //Simple function to open and close nav drawer
    public boolean onOptionsItemSelected(MenuItem item){

        DrawerLayout navDrawer = (DrawerLayout) findViewById(R.id.activity_main);

        boolean isOpen = navDrawer.isDrawerOpen(GravityCompat.START);

        if(isOpen){
            navDrawer.closeDrawer(GravityCompat.START);
        }else{
            navDrawer.openDrawer(GravityCompat.START);
        }
        return true;
    }

}
