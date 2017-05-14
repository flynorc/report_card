package com.flynorc.a05_reportcard;

import java.util.ArrayList;

/**
 * Created by Flynorc on 30-Apr-17.
 * Class ReportCard is representing a report card for a highschool education system in Slovenia
 * Possible grades for each subject are 1-5, 1 beeing the worst (fail) and 5 beeing the best
 * A student passed the study year if ALL grades are positive (larger than 1)
 *
 * In an actual app implementation it would most likely make sense to just have an ArrayList of custom objects (Grade object for example),
 * And Grade object would then contain the name of the subject, the grade itself (and other possible needed variables)
 * That way we would simplify the accessing the variables from multiple lists and improve code readability
 * In this small demo class with just 2 ArrayLists that is not a big problem but with more variables it would be cumbersome to maintain
 *
 * Since the project requires to only use one class, 2 ArrayLists it is.
 */

public class ReportCard {
    //constants
    private static final int MIN_GRADE = 1;
    private static final int MAX_GRADE = 5;
    private static final int MIN_SUBJECT_LEN = 3;
    private static final int MAX_SUBJECT_LEN = 30;
    private static final int MIN_PASSED_GRADE = 2;
    private static final int GRADE_NOT_EXISTS = -1;

    //variables
    private ArrayList<String> mCourses;
    private ArrayList<Integer> mGrades;

    public ReportCard() {
        //initialize the variables
        mCourses = new ArrayList<>();
        mGrades = new ArrayList<>();

    }

    /*
     * simple implementation of adding a grade to the report card
     * function returns true on success or false if the basic validation fails
     */
    public boolean addGrade(String subjectName, int grade) {
        int subjectLen = subjectName.length();
        //basic validation - just check the grade is between the MIN and MAX values, and that subject length is as expected
        if(grade > MAX_GRADE || grade < MIN_GRADE || subjectLen > MAX_SUBJECT_LEN || subjectLen < MIN_SUBJECT_LEN) {
            return false;
        }

        //check if grade already exists, if it does, update the existing value, otherwise add a new grade (and subject)
        int subjectIndex = getIndexForSubject(subjectName);
        if(subjectIndex == GRADE_NOT_EXISTS) {
            mCourses.add(subjectName);
            mGrades.add(grade);
        }
        else {
            mGrades.set(subjectIndex, grade);
        }


        return true;
    }

    /*
     * function to determine if student can go to next year (if he passed all the classes)
     * loop over all the grades and as soon one is lower than min required to pass, return false
     * if all are positive, return true
     */
    public boolean allGradesPositive() {
        for(int i=0; i<mGrades.size(); i++) {
            if(mGrades.get(i) < MIN_PASSED_GRADE) {
                return false;
            }
        }

        return true;
    }

    /*
     * Override the default toString method
     * format all grades
     */
    @Override
    public String toString() {
        String output = "";
        for(int i=0; i<mCourses.size(); i++) {
            output += mCourses.get(i) + ": " + mGrades.get(i) + "\n";
        }

        return output;
    }


    /*
     * get the average grade
     * it is actually the arithmetic mean but in our education system they refer to it as average grade
     */
    public float getAverage() {
        int sum = 0;
        for(int i=0; i<mGrades.size(); i++) {
            sum += mGrades.get(i);
        }

        return (float) sum / mGrades.size();
    }

    /*
     * getter method to retrieve the grade of a certain class
     * in an actual implementation, this would be done with IDs instead of searching for an element where strings match
     * because... performance ... and stuff
     * if the report card does not contain a grade for a certain class, the value returned is a not valid grade constant that can be checked and handeled by the code that is calling this function
     */
    public int getGradeForSubject(String subjectName) {
        int subjectIndex = getIndexForSubject(subjectName);

        if(subjectIndex != GRADE_NOT_EXISTS) {
            return mGrades.get(subjectIndex);
        }

        return GRADE_NOT_EXISTS;
    }

    /*
     * helper function to check if a subject already exists in the report card
     * if it does, it returns the index in the arrayList where the grade can be found
     * otherwise it returns GRADE_NOT_EXISTS constant (-1)
     */
    private int getIndexForSubject(String subjectName) {
        for(int i=0; i<mCourses.size(); i++) {
            if(mCourses.get(i).equals(subjectName)) {
                return i;
            }
        }

        return GRADE_NOT_EXISTS;
    }
}
