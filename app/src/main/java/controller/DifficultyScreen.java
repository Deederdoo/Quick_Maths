package controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;

public class DifficultyScreen extends AppCompatActivity {

    //Booleans
    public static boolean isEasy = true;
    public static boolean isInter = false;
    public static boolean isHard = false;
    public static boolean isSavant = false;

    //Buttons
    private Button buttonEasy, buttonInter, buttonHard, buttonSavant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        //Buttons
        buttonEasy = (Button) findViewById(R.id.button_difficulty_easy);
        buttonInter = (Button) findViewById(R.id.button_difficulty_intermediate);
        buttonHard = (Button) findViewById(R.id.button_difficulty_hard);
        buttonSavant = (Button) findViewById(R.id.button_difficulty_savant);

        buttonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isEasy = true;
                isInter = false;
                isHard = false;
                isSavant = false;

                Intent nextActivity = new Intent(DifficultyScreen.this, PlayScreen.class);
                startActivity(nextActivity);
            }
        });

        buttonInter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isEasy = false;
                isInter = true;
                isHard = false;
                isSavant = false;

                Intent nextActivity = new Intent(DifficultyScreen.this, PlayScreen.class);
                startActivity(nextActivity);
            }
        });

        buttonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isEasy = false;
                isInter = false;
                isHard = true;
                isSavant = false;

                Intent nextActivity = new Intent(DifficultyScreen.this, PlayScreen.class);
                startActivity(nextActivity);
            }
        });

        buttonSavant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isEasy = false;
                isInter = false;
                isHard = false;
                isSavant = true;

                Intent nextActivity = new Intent(DifficultyScreen.this, PlayScreen.class);
                startActivity(nextActivity);
            }
        });
    }
}
