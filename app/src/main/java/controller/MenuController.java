package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.quickmaths.R;

public class MenuController extends AppCompatActivity {

    //Buttons
    private Button playButton, scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Typeface / Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Cairo-SemiBold.ttf");

        //Buttons
        playButton = (Button) findViewById(R.id.button_play);
        playButton.setTypeface(typeface);
        scoreButton = (Button) findViewById(R.id.button_home_scores);
        scoreButton.setTypeface(typeface);

        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent nextActivity = new Intent(MenuController.this, DifficultyScreen.class);
                startActivity(nextActivity);
            }
        });

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity = new Intent(MenuController.this, Scoreboard.class);
                startActivity(nextActivity);
            }
        });
    }
}