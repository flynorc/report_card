package com.flynorc.a05_reportcard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //make the report card instance
        ReportCard reportCard = new ReportCard();

        //add some stuff
        reportCard.addGrade("math", 5);
        reportCard.addGrade("lang", 4);
        reportCard.addGrade("comp", 4);
        reportCard.addGrade("lang", 3);
        reportCard.addGrade("a", 2);
        reportCard.addGrade("nomeoj", 30);
        reportCard.addGrade("failed course", 2);

        String debugString = reportCard.toString();

        debugString += "average: " + reportCard.getAverage() + "\n";
        debugString += "passed: " + reportCard.allGradesPositive() + "\n";
        debugString += "get grade for math: " + reportCard.getGradeForSubject("math") + "\n";
        debugString += "get grade for math2: " + reportCard.getGradeForSubject("math2") + "\n";

        Log.d("test", reportCard + "");

        System.out.println(reportCard);


        //call tostring method and update the debug view
        TextView debugView = (TextView) findViewById(R.id.debugView);
        debugView.setText(debugString);
    }
}
