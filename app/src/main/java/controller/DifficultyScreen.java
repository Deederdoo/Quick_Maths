package controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DifficultyScreen extends AppCompatActivity {

    //AdView
    private AdView mAdView;

    //Booleans
    public static boolean isEasy = true;
    public static boolean isInter = false;
    public static boolean isHard = false;
    public static boolean isSavant = false;

    //TextView
    private TextView titleText;

    //Buttons
    private Button buttonEasy, buttonInter, buttonHard, buttonSavant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        //Ad Setup
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView_menu);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Typeface / Font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "Cairo-SemiBold.ttf");

        //Buttons
        buttonEasy = (Button) findViewById(R.id.button_difficulty_easy);
        buttonEasy.setTypeface(typeface);
        buttonInter = (Button) findViewById(R.id.button_difficulty_intermediate);
        buttonInter.setTypeface(typeface);
        buttonHard = (Button) findViewById(R.id.button_difficulty_hard);
        buttonHard.setTypeface(typeface);
        buttonSavant = (Button) findViewById(R.id.button_difficulty_savant);
        buttonSavant.setTypeface(typeface);

        //TextView
        titleText = (TextView) findViewById(R.id.text_difficulty_select);
        titleText.setTypeface(typeface);

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
