package controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import tools.PostRequest;

public class UserIDScreen extends AppCompatActivity {

    //Connection
    PostRequest postR;
    private URL url;
    private HttpURLConnection con;

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

//                try {
//
//                    url = new URL("http://localhost:8080/API_Scoreboard/webapi/myresource/score");
//                    con = (HttpURLConnection)url.openConnection();
//                    con.setRequestMethod("POST");
//                    con.setRequestProperty("Content-Type", "application/json; utf-8");
//                    con.setRequestProperty("Accept", "application/json");
//
//                    String jsonString = "{'ranking': " + "'1', " + "'score': '" + bundle.getIntArray("standing").toString() + "', 'userid': '" + userid.getText().toString() + "'}";
//                    Log.e("Testing", "JsonString: " + jsonString);
//
//                    OutputStream os = con.getOutputStream();
//                    byte[] input = jsonString.getBytes("utf-8");
//                    os.write(input, 0, input.length);
//
//                    BufferedReader br = new BufferedReader(
//                            new InputStreamReader(con.getInputStream(), "utf-8"){
//                        StringBuilder response = new StringBuilder();
//                        String responseLine = null;
//                        while((responseLine = br.readLine()) != null){
//                            response.append(responseLine.trim());
//                                }
//                    });
//
//                } catch (MalformedURLException e) {
//
//                    e.printStackTrace();
//                } catch (IOException e) {
//
//                    e.printStackTrace();
//                }

                String url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/score";
                String jsonString = "{\"score\": " + bundle.getDouble("finalscore") + ", \"userid\": \"" + userid.getText().toString() + "\"}";
                Log.e("Testing", "JsonString: " + jsonString);

                new PostRequest().execute(url, jsonString);
            }
        });
    }
}
