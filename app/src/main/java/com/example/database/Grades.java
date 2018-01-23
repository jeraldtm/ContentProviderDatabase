package com.example.database;

/**
 * Created by jerald on 1/22/18.
 */

public class Grades extends Object {
    private int _id;
    private String _subject;
    private int _grade;

    public Grades(){

    }

    public Grades(int id, String subject, int grade){
        this._id = id;
        this._subject = subject;
        this._grade = grade;
    }

    public Grades(String subject, int grade){
        this._subject = subject;
        this._grade = grade;
    }

    public void setID(int id){
        this._id = id;
    }

    public int getID(){
        return this._id;
    }

    public void setSubjectName(String subject){
        this._subject = subject;
    }

    public String getSubjectName(){
        return this._subject;
    }

    public void setGrades(int grade){
        this._grade = grade;
    }

    public int getGrades(){
        return this._grade;
    }
}

