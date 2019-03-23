package edu.citadel.android.hackathon;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.util.Random;

public class Activity2 extends AppCompatActivity{
    private static final long START_TIME_IN_MILLIS = 10000;
    private Button mButton;
    TextView textLocations;
    TextView textTasks;
    TextView textCountDown;

    String[] locations = new String[5];
    String[] tasks = new String[5];
    private Random rand = new Random();

    private CountDownTimer countdowntime;
    private long timeLeftInMiliseconds = START_TIME_IN_MILLIS; //10 min
    private boolean timeRunning;
    int i = rand.nextInt(locations.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        textLocations = findViewById(R.id.textView00);
        textTasks = findViewById(R.id.task);
        textCountDown = findViewById(R.id.countdown);
        mButton = findViewById(R.id.restartButton);
        mButton.setOnClickListener((View v) -> openActivity());

        }





    public String GenerateLocation(){
        // Generates a random event in the location section
        locations[0] = "Starbucks";
        locations[1] = "The Park";
        locations[2] = "Cafe";
        locations[3] = "Arcade";
        locations[4] = "Mall";


        return locations[i];
    }

    public String GenerateTasks(){
        // Generates a random event in the tasks section
        tasks[0] = "Ask someone what they ordered.";
        tasks[1] = "Compliment someone's dog.";
        tasks[2] = "Ask someone how their day is going.";
        tasks[3] = "Challenge someone to a friendly game.";
        tasks[4] = "Shake someone's hand and introduce yourself.";

        return tasks[i];
    }

    public void StartStop(){
        if(timeRunning){
            StopTimer();
        }else{
            StartTimer();
        }
    }

    public void StartTimer(){
        countdowntime = new CountDownTimer(timeLeftInMiliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMiliseconds = l;
                UpdateTimer();
            }

            @Override
            public void onFinish() {

            }

        }.start();
        timeRunning = true;
    }

    public void StopTimer(){
        countdowntime.cancel();
        timeRunning = false;
    }

    public void UpdateTimer(){
        int minutes = (int) timeLeftInMiliseconds / 60000;
        int seconds = (int) timeLeftInMiliseconds % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + minutes;
        timeLeftText += ":";

        if(seconds< 10) timeLeftText += "0";
        timeLeftText += seconds;

        textCountDown.setText(timeLeftText);
        if(seconds == 0){
            textTasks.setText(GenerateTasks());
        }
    }

    public void onClickLocation(View v){
        textLocations.setText("Location: " +  GenerateLocation());
        StartStop();
    }

    public void openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
