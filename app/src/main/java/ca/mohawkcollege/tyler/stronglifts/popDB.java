package ca.mohawkcollege.tyler.stronglifts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

/**
 * Created by Tyler on 2017-10-24.
 */

public class popDB {

    public void populateDB(Context cont){

        DBWorkouts dbhelper = new DBWorkouts(cont);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        ContentValues v = new ContentValues();
//*********   CREATE WORKOUTS TABLE ******************
        v.put("Routine", "s5X5");
        v.put("MuscleGroup", "Full Body");
        db.insert("Workouts", null, v);
        db.close();
 //********** CREAT 5X5 TABLE *****************
        dbhelper = new DBWorkouts(cont);
        db = dbhelper.getWritableDatabase();

        v = new ContentValues();
        v.put("Exercise", "Bench Press");
        v.put("Sets", 5);
        db.insert("s5X5", null, v);

        v.put("Exercise", "Squat");
        v.put("Sets", 5);
        db.insert("s5X5", null, v);

        v.put("Exercise", "Stiff Leg Deadlift");
        v.put("Sets", 5);
        db.insert("s5X5", null, v);

        v.put("Exercise", "Barbell Row");
        v.put("Sets", 5);
        db.insert("s5X5", null, v);

        v.put("Exercise", "Overhead Press");
        v.put("Sets", 5);
        db.insert("s5X5", null, v);
        db.close();
//************ CREATE EXERCISE TABLE **************
         dbhelper = new DBWorkouts(cont);
         db = dbhelper.getWritableDatabase();

        v = new ContentValues();
//****** Chest ******
        v.put("Exercise", "Bench Press");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Incline Bench Press");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Dips");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Chest Flies");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Chest Press");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Incline Chest Press");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "One arm Flies");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Incline Dumbbell Flies");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "High Cable Crossover");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Low Cable Crossover");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Incline Hammer Strength Press");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Decline Hammer Strength Press");
        v.put("MuscleGroup", "Chest");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

//******** Back *******
        v.put("Exercise", "Dumbbell Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Close grip Cable Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Lat Pull Down");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Reverse Grip Lat Pull Down");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Barbell Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Wide Neutral Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Underhand Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Dumbbell Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Pullups");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "T Bar Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Machine Row");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Rack Pull");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Straight Arm Pull Down");
        v.put("MuscleGroup", "Back");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);


//******** Legs *******
        v.put("Exercise", "Squat");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Front Squat");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Stiff Leg Deadlift");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Romanian Deadlift");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Leg Extensions");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Leg Curls");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Leg Press");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Dumbbell Front Squat");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Standing Calf Raise");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Seated Calf Raise");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Lunges");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Hack Squat");
        v.put("MuscleGroup", "Legs");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

 //****** Shoulders *****
        v.put("Exercise", "Dumbbell Shoulder Press");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Arnold Press");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Overhead Press");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Behind the Neck Press");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Neutral Shoulder Press");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Lateral Raise");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Cable Lateral Raise");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Machine Lateral Raise");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Front Raise");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Upright Row");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Shrugs");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Machine Reverse Flies");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Face Pulls");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Dumbbell Reverse Flies");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Arnold Press");
        v.put("MuscleGroup", "Shoulders");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

//********* Arms *******
        v.put("Exercise", "Barbell Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Seated Dumbbell Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Hammer Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Concentration Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Cable Hammer Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Cable Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "High Cable Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Forearm Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Reverse Grip Curls");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Tricep Dips");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Close Grip Bench Press");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Tricep Extension");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Skull Crushers");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Overhead Extensions");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Reverse Grip Extensions");
        v.put("MuscleGroup", "Arms");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

//****** Core ******
        v.put("Exercise", "Crunches");
        v.put("MuscleGroup", "Core");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Weighted Crunches");
        v.put("MuscleGroup", "Core");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Leg Raises");
        v.put("MuscleGroup", "Core");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Hyper Extensions");
        v.put("MuscleGroup", "Core");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Russian Twist");
        v.put("MuscleGroup", "Core");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        v.put("Exercise", "Decline Crunches");
        v.put("MuscleGroup", "Core");
        v.put("Completed", 0);
        db.insert("Exercises", null, v);

        db.close();
    }
}
