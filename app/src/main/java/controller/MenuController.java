package controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quickmaths.R;

public class MenuController extends AppCompatActivity {

    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        playButton = (Button) findViewById(R.id.button_play);

        playButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent nextActivity = new Intent(MenuController.this, PlayScreen.class);
                startActivity(nextActivity);
            }
        });
    }
}