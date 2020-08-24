package controller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    //Typeface
    private Typeface typeface;

    private Bundle bundle;
    private Intent nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userid);

        //Typeface / Font
        typeface = Typeface.createFromAsset(getAssets(), "Cairo-SemiBold.ttf");

        //Buttons
        submitName = (Button) findViewById(R.id.button_submit_userid);
        submitName.setTypeface(typeface);
        backHome = (Button) findViewById(R.id.button_home_userid);
        backHome.setTypeface(typeface);

        //EditText
        userid = (EditText) findViewById(R.id.text_userid);
        userid.setTypeface(typeface);

        nextActivity = getIntent();
        bundle = nextActivity.getExtras();

        submitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tempUXID = generateUID(userid.getText().toString());
                setUrlDifficulty();
                String jsonString = "{\"ux_id\": \"" + tempUXID + "\", \"score\": " + bundle.getDouble("finalscore") + ", \"userid\": \"" + userid.getText().toString() + "\"}";
                Log.e("Testing", "JsonString: " + jsonString);

                new PostRequest().execute(url, jsonString);

                nextActivity = new Intent(UserIDScreen.this, Scoreboard.class);
                bundle.putString("ux_id", tempUXID);
                nextActivity.putExtras(bundle);
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

    private String generateUID(String in) {

        int ux_id = new Random().nextInt(1000000000);
        in += ux_id;

        List<Character> chars = new ArrayList<>();
        for(char c : in.toCharArray()){

            chars.add(c);
        }

        StringBuilder sb = new StringBuilder(in.length());
        while(chars.size() != 0){

            int random = (int) (Math.random() * chars.size());
            sb.append(chars.remove(random));
        }

        return sb.toString();
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
