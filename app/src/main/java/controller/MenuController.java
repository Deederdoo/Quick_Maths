package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.quickmaths.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MenuController extends AppCompatActivity {

    //Typeface
    private Typeface typeface;

    //AdView
    private AdView mAdView;

    //Buttons
    private Button playButton, scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

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
        typeface = Typeface.createFromAsset(getAssets(), "Cairo-SemiBold.ttf");

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