package controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.quickplus.R;

public class ResultScreen extends AppCompatActivity {

    private int[] standing = new int[2];
    private double finalScore = 0;

    //Typeface
    private Typeface typeface;

    //TextView
    private TextView correctAnswers, incorrectAnswers, score;

    //Buttons
    private Button submitScore, playAgain, buttonHome;

    private Bundle myBundle;
    private Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Typeface / Font
        typeface = Typeface.createFromAsset(getAssets(), "Cairo-SemiBold.ttf");

        //TextView
        correctAnswers = (TextView) findViewById(R.id.text_right_answer);
        correctAnswers.setTypeface(typeface);
        incorrectAnswers = (TextView) findViewById(R.id.text_wrong_answer);
        incorrectAnswers.setTypeface(typeface);
        score = (TextView) findViewById(R.id.text_score);
        score.setTypeface(typeface);

        //Intent
        nextActivity = getIntent();

        //Bundle
        myBundle = nextActivity.getExtras();

        //Buttons
        submitScore = (Button) findViewById(R.id.button_submit_score);
        submitScore.setTypeface(typeface);
        playAgain = (Button) findViewById(R.id.button_play_again);
        playAgain.setTypeface(typeface);
        buttonHome = (Button) findViewById(R.id.button_result_home);
        buttonHome.setTypeface(typeface);

        standing = myBundle.getIntArray("standing");
        correctAnswers.setText(String.valueOf(standing[0]));
        incorrectAnswers.setText(String.valueOf(standing[1]));

        finalScore = myBundle.getDouble("finalscore");
        score.setText(String.valueOf(finalScore));

        /**
         *
         * Button click to go to the next page and carry
         * the data to be put into the database scoreboarddb
        *
        * */
        submitScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextActivity = new Intent(ResultScreen.this, UserIDScreen.class);
                nextActivity.putExtras(myBundle);
                startActivity(nextActivity);
            }
        });

        /**
         *
         * Button click to allow the user to play another game, skips
         * the score submission
         *
        *
        * */
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextActivity = new Intent(ResultScreen.this, PlayScreen.class);
                startActivity(nextActivity);
            }
        });
    }

    /**
     *
     * I use this method so if the user presses back on
     * their device it will send them back to the menu
     *
     * */
    @Override
    public void onBackPressed(){

        Intent nextActivity = new Intent(getApplicationContext(), MenuController.class);
        startActivity(nextActivity);
    }
}
