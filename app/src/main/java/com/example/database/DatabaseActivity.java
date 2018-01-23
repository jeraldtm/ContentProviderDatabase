package com.example.database;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DatabaseActivity extends Activity {
    TextView idView;
    EditText subjectBox;
    EditText gradeBox;

    private MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
         dbHandler = new MyDBHandler(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
        idView = (TextView) findViewById(R.id.gradeID);
        subjectBox = (EditText) findViewById(R.id.subject_name);
        gradeBox = (EditText) findViewById(R.id.subject_grade);
    }

    public void newGrades(View view){
        int grade = Integer.parseInt(gradeBox.getText().toString());
        Log.d("Android:", subjectBox.getText().toString());
        Log.d("Android:", String.valueOf(grade));
        Grades grades = new Grades(subjectBox.getText().toString(), grade);
        dbHandler.addGrades(grades);
        subjectBox.setText("");
        gradeBox.setText("");
    }

    public void lookupGrades (View view){
        Grades grades = dbHandler.findGrades(subjectBox.getText().toString());

        if (grades != null) {
            idView.setText(String.valueOf(grades.getID()));

            gradeBox.setText(String.valueOf(grades.getGrades()));
        } else {
            idView.setText("No Match Found");
        }
    }

    public void removeGrades (View view){
        boolean result = dbHandler.deleteGrades(subjectBox.getText().toString());

        if(result){
            idView.setText("Record Deleted");
            subjectBox.setText("");
            gradeBox.setText("");
        } else {
            idView.setText("No Match Found");
        }
    }

    public void updateGrades (View view){
        boolean result = dbHandler.deleteGrades(subjectBox.getText().toString());

        if(result){
            int grade = Integer.parseInt(gradeBox.getText().toString());
            Log.d("Android:", subjectBox.getText().toString());
            Log.d("Android:", String.valueOf(grade));
            Grades grades = new Grades(subjectBox.getText().toString(), grade);
            dbHandler.addGrades(grades);
            idView.setText("Record Updated");
            subjectBox.setText("");
            gradeBox.setText("");
        } else {
            idView.setText("No Match Found");
        }
    }
    public void removeAllGrades (View view){
        dbHandler.deleteAllGrades();
    }

}
