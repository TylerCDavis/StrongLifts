package ca.mohawkcollege.tyler.stronglifts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewRoutine extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_routine);

        ActionBar topBar = getSupportActionBar();
        topBar.setDisplayHomeAsUpEnabled(true);

        Bundle b = getIntent().getExtras();
        setTitle(b.getString("routine"));

        DBWorkouts workouts = new DBWorkouts(this);
        SQLiteDatabase db = workouts.getReadableDatabase();

        Cursor c = db.rawQuery("Select Exercise from " + b.getString("routine")+";", null);

        int x = c.getCount();
        String[] results = new String[x];

        int z = 0;

        while (c.moveToNext()) {
            results[z] = c.getString(c.getColumnIndex("Exercise"));
            z++;
        }
        ArrayAdapter<String> adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);
        ListView display = (ListView) findViewById(R.id.exerciseDisplay);
        display.setAdapter(adpter);


    }

    public boolean onOptionsItemSelected(MenuItem item){
        Bundle b =getIntent().getExtras();
        Intent i = new Intent(this, Workouts.class);
        i.putExtras(b);
        startActivity(i);
        return true;
    }
}
