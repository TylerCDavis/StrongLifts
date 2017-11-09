package ca.mohawkcollege.tyler.stronglifts;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class routineDialog extends DialogFragment {


    public routineDialog() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("New Routine");

        LayoutInflater inflate = getActivity().getLayoutInflater();
        final View mLayout = inflate.inflate(R.layout.fragment_routine_dialog, null);

        final EditText routine = (EditText) mLayout.findViewById(R.id.editText);
        final Spinner group = (Spinner) mLayout.findViewById(R.id.spinner);
        routine.getText();

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("Create", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                // 1)Insert into Workouts Table
                // 2) Start activity that selects exercises


                if (routine.getText().toString().equals("")){
                    Context cont = getActivity();
                    CharSequence text = "Routine needs a Name";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(cont, text, duration);
                    toast.show();

                }else{

                    //DBWorkouts dbhelper = new DBWorkouts(getActivity());
                    //SQLiteDatabase db = dbhelper.getWritableDatabase();

                   // ContentValues v = new ContentValues();
                    //v.put("Routine", routine.getText().toString());
                   // v.put("MuscleGroup", group.getSelectedItem().toString());
                   // db.insert("Workouts", null, v);

                    Bundle b = new Bundle();
                    String r = routine.getText().toString();
                    r=r.replace(" ", "_");
                    b.putString("routine", r);
                    b.putString("group", group.getSelectedItem().toString());
                    //!!for testing
                    Intent i = new Intent(getActivity(), createRoutine.class);
                    i.putExtras(b);
                    startActivity(i);

                }

            }
        });

        builder.setView(mLayout);
        return builder.create();
    }

}
