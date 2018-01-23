package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GradesDB";
    public static final String TABLE_GRADES = "grades";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SUBJECTNAME = "name";
    public static final String COLUMN_GRADE  = "marks";

    public MyDBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_GRADES_TABLE = "CREATE TABLE " + TABLE_GRADES + "(" + COLUMN_ID +
                " INTEGER PRIMARY KEY," + COLUMN_SUBJECTNAME + " TEXT,"
                + COLUMN_GRADE + " INTEGER" + ");";
        db.execSQL(CREATE_GRADES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GRADES);
        onCreate(db);
    }

    public void addGrades(Grades grade){
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBJECTNAME, grade.getSubjectName());
        Log.d("Android:", "put subject");
        values.put(COLUMN_GRADE, grade.getGrades());
        Log.d("Android:", "put grade");
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_GRADES, null, values);
        db.close();
    }

    public Grades findGrades(String subjectname){
        String query = "Select * FROM " + TABLE_GRADES + " WHERE " + COLUMN_SUBJECTNAME
                + " = \"" + subjectname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Grades grade = new Grades();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            grade.setID(Integer.parseInt(cursor.getString(0)));
            grade.setSubjectName(cursor.getString(1));
            grade.setGrades(Integer.parseInt(cursor.getString(2)));
        } else {
            grade = null;
        }
        db.close();
        return grade;
    }

    public boolean deleteGrades(String subjectname){

        boolean result = false;

        String query = "Select * FROM " + TABLE_GRADES + " WHERE " + COLUMN_SUBJECTNAME
                + " = \"" + subjectname + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Grades grade = new Grades();

        if (cursor.moveToFirst()){
            grade.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_GRADES, COLUMN_ID + " = ?", new String[] {String.valueOf(grade.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void deleteAllGrades(){
        String query = "Select * FROM " + TABLE_GRADES;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Grades grade = new Grades();

        if (cursor.moveToFirst()){
            grade.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_GRADES, COLUMN_ID + " = ?", new String[] {String.valueOf(grade.getID())});
            cursor.close();
        }
        db.close();
    }


}
