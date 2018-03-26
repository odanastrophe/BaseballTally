package com.odanastrophe.baseballtally;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int firstBase = 0, secondBase = 0, thirdBase = 0, home = 0, atBat = 0, ball = 0, strike = 0, out = 0, runScore = 0, teamA = 0, teamB = 0;
    int inningCount = 2, inningCheck = 2;
    String inningBanner, statusFirst = "", statusSecond = "", statusThird = "";

    //Button functionality
    public void strikes(View view){
        strike = strike + 1;
        stateCheck();
    }
    public void balls(View view){
        ball = ball + 1;
        if (ball > 3){
            ball = 0;
            strike = 0;
            atBat = 1;
            baseRotation(1);
        }
        stateCheck();
    }
    public void outs(View view){
        out = out + 1;
        strike = 0;
        ball = 0;
        stateCheck();
    }
    public void baseHit(View view){
        atBat = 1;
        strike = 0;
        ball = 0;
        baseRotation(1);
        stateCheck();
    }
    public void twoHit(View view){
        atBat = 1;
        strike = 0;
        ball = 0;
        baseRotation(2);
        stateCheck();
    }
    public void threeHit(View view){
        atBat = 1;
        strike = 0;
        ball = 0;
        baseRotation(3);
        stateCheck();
    }
    public void homeRun(View view){
        atBat = 1;
        strike = 0;
        ball = 0;
        baseRotation(4);
        stateCheck();
    }

    //Base Buttons
    public void onFirst(View view){
        if (firstBase == 0){
            firstBase = 1;
            statusFirst = "On ";
        }else{
            firstBase = 0;
            statusFirst = "";
        }
        stateCheck();
    }
    public void onSecond(View view){
        if (secondBase == 0){
            secondBase = 1;
            statusSecond = "On ";
        }else{
            secondBase = 0;
            statusSecond = "";
        }
        stateCheck();
    }
    public void onThird(View view){
        if (thirdBase == 0){
            thirdBase = 1;
            statusThird = "On ";
        }else{
            thirdBase = 0;
            statusThird = "";
        }
        stateCheck();
    }


    //Reset Button
    public void reset (View view){
        inningCheck = 2;
        home = 0;
        atBat = 0;
        ball = 0;
        strike = 0;
        out = 0;
        runScore = 0;
        inningCount = 2;
        teamA = 0;
        teamB = 0;
        firstBase = 0;
        secondBase = 0;
        thirdBase = 0;
        statusFirst = "";
        statusSecond = "";
        statusThird = "";
        stateCheck();
    }

    //Method for rotating the bases and adding score.
    public void baseRotation(int runs){
        for (int i = runs; i > 0; i--) {
            home = thirdBase;
            thirdBase = secondBase;
            secondBase = firstBase;
            firstBase = atBat;
            atBat = 0;
            runScore = runScore + home;
            home = 0;
        }
        if (firstBase == 1){
            statusFirst = "On ";
        }else{
            statusFirst = "";
        }
        if (secondBase == 1){
            statusSecond = "On ";
        }else{
            statusSecond = "";
        }
        if (thirdBase == 1){
            statusThird = "On ";
        }else{
            statusThird = "";
        }

        stateCheck();
    }
    //State Check method for checking, making, and displaying updates.
    public void stateCheck (){

        //if statements for at-bat buttons
        if (strike > 2){
            out = out + 1;
            strike = 0;
            ball = 0;
        }
        if (out > 2) {
            inningCount = inningCount + 1;
            out = 0;
        }

        //Resetting values at inning turnover
        if (inningCheck != inningCount){
            home = 0;
            atBat = 0;
            ball = 0;
            strike = 0;
            out = 0;
            runScore = 0;
            firstBase = 0;
            secondBase = 0;
            thirdBase = 0;
            inningCheck = inningCount;
            statusFirst = "";
            statusSecond = "";
            statusSecond = "";
        }

        //setting the inning banner
        int inning = inningCount / 2;
        int topBottom = inningCount % 2;
        String bottomTop;
        if (topBottom == 1){
            bottomTop = "Bottom";
        }else{
            bottomTop = "Top";
        }
        inningBanner = "It's the " + bottomTop + " of inning " + inning;

        //setting the at-bat
        String whosUp;
        if (topBottom == 0){
            whosUp= "Team A";
        }else{
            whosUp = "Team B";
        }

        //Checking which team is at bat and adding their score.
        if (topBottom == 0){
            teamA = teamA + runScore;
            runScore = 0;
        }else{
            teamB = teamB + runScore;
            runScore = 0;
        }

        //updating all text fields
        TextView inningDisplay = (TextView) findViewById(R.id.inning);
        inningDisplay.setText(String.valueOf(inningBanner));

        TextView scoreViewA = (TextView) findViewById(R.id.team_a_score);
        scoreViewA.setText(String.valueOf(teamA));

        TextView scoreViewB = (TextView) findViewById(R.id.team_b_score);
        scoreViewB.setText(String.valueOf(teamB));

        TextView atBatView = (TextView) findViewById(R.id.home);
        atBatView.setText(String.valueOf(whosUp));

        TextView strikeButton = (TextView) findViewById(R.id.strike_text);
        strikeButton.setText(String.valueOf(strike));

        TextView ballButton = (TextView) findViewById(R.id.ball_text);
        ballButton.setText(String.valueOf(ball));

        TextView outButton = (TextView) findViewById(R.id.out_text);
        outButton.setText(String.valueOf(out));

        //Updating button text fields.
        TextView first = (TextView) findViewById(R.id.first_base);
        first.setText(String.valueOf(statusFirst + "1st"));

        TextView second = (TextView) findViewById(R.id.second_base);
        second.setText(String.valueOf(statusSecond + "2nd"));

        TextView third = (TextView) findViewById(R.id.third_base);
        third.setText(String.valueOf(statusThird + "3rd"));

    }
}
