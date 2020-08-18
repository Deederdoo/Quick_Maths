package controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;

import tools.PostRequest;

import static controller.DifficultyScreen.isEasy;
import static controller.DifficultyScreen.isHard;
import static controller.DifficultyScreen.isInter;
import static controller.DifficultyScreen.isSavant;

public class UserIDScreen extends AppCompatActivity {

    //Connection
    private String url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/score";

    //Buttons
    private Button submitName, backHome;

    //EditText
    private EditText userid;

    private Bundle bundle;
    private Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userid);

        //Buttons
        submitName = (Button) findViewById(R.id.button_submit_userid);
        backHome = (Button) findViewById(R.id.button_home_userid);

        //EditText
        userid = (EditText) findViewById(R.id.text_userid);

        nextActivity = getIntent();
        bundle = nextActivity.getExtras();

        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setUrlDifficulty();
                String jsonString = "{\"score\": " + bundle.getDouble("finalscore") + ", \"userid\": \"" + userid.getText().toString() + "\"}";
                Log.e("Testing", "JsonString: " + jsonString);

                new PostRequest().execute(url, jsonString);

                nextActivity = new Intent(UserIDScreen.this, Scoreboard.class);
                startActivity(nextActivity);
            }
        });

        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nextActivity = new Intent(UserIDScreen.this, MenuController.class);
                startActivity(nextActivity);
            }
        });
    }

    public void setUrlDifficulty() {

        if(isEasy){

            url += "_easy";

        }else if(isInter){

            url += "_intermediate";

        }else if(isHard){

            url += "_hard";

        }else if(isSavant){

            url += "_savant";
        }
    }
}
