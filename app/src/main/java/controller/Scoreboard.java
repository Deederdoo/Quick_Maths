package controller;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quickmaths.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import adapters.ScoreListAdapter;
import models.Score;

import static controller.DifficultyScreen.isEasy;
import static controller.DifficultyScreen.isHard;
import static controller.DifficultyScreen.isInter;
import static controller.DifficultyScreen.isSavant;
import static controller.PlayScreen.MODE_EASY;
import static controller.PlayScreen.MODE_HARD;
import static controller.PlayScreen.MODE_INTERMEDIATE;
import static controller.PlayScreen.MODE_SAVANT;

public class Scoreboard extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //URL
    private String url;

    //Progress
    private ProgressBar progressBar;

    //ListView
    private ListView scoreList;

    //Spinner/Dropdown
    private Spinner dropdown;

    //ArrayList / Adapter
    private List<Score> sList;
    private ScoreListAdapter slAdapt;
    private ArrayAdapter<String> dropAdapt;
    private List<String> diffList;

    //Score
    private Score score;

    //Button
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //ListView
        scoreList = (ListView) findViewById(R.id.list_scoreboard);

        //Progressbar
        progressBar = (ProgressBar) findViewById(R.id.progress_scoreboard);

        //ArrayList
        sList = new ArrayList<>();
        diffList = new ArrayList<>();
        diffList.add(MODE_EASY);
        diffList.add(MODE_INTERMEDIATE);
        diffList.add(MODE_HARD);
        diffList.add(MODE_SAVANT);

        //Adapter
        dropAdapt = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, diffList);

        //Spinner
        dropdown = (Spinner) findViewById(R.id.spinner_scoreboard_difficulty);
        dropdown.setAdapter(dropAdapt);
        dropdown.setOnItemSelectedListener(this);

        //Buttons
        backButton = (Button) findViewById(R.id.button_scoreboard_back);

        showCurrentDifficulty();
        setUrlDifficulty();
        new GetRequest().execute(url);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nextActivity = new Intent(Scoreboard.this, MenuController.class);
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

    public void showCurrentDifficulty() {

        if(isEasy){

            dropdown.setSelection(0);

        }else if(isInter){

            dropdown.setSelection(1);

        }else if(isHard){

            dropdown.setSelection(2);

        }else if(isSavant){

            dropdown.setSelection(3);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i == 0){

            isEasy = true;
            isInter = false;
            isHard = false;
            isSavant = false;
            sList.clear();
            setUrlDifficulty();
            new GetRequest().execute(url);

        }else if (i == 1){

            isEasy = false;
            isInter = true;
            isHard = false;
            isSavant = false;
            sList.clear();
            setUrlDifficulty();
            new GetRequest().execute(url);

        }else if(i == 2) {

            isEasy = false;
            isInter = false;
            isHard = true;
            isSavant = false;
            sList.clear();
            setUrlDifficulty();
            new GetRequest().execute(url);

        }else if(i == 3){

            isEasy = false;
            isInter = false;
            isHard = false;
            isSavant = true;
            sList.clear();
            setUrlDifficulty();
            new GetRequest().execute(url);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setUrlDifficulty() {

        if(isEasy){

            url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/scores_easy";

        }else if(isInter){

            url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/scores_intermediate";

        }else if(isHard){

            url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/scores_hard";

        }else if(isSavant){

            url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/scores_savant";
        }
    }

    private class GetRequest extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {

            String myURL = strings[0];
            HttpURLConnection con = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(myURL);
                con = (HttpURLConnection) url.openConnection();
                con.connect();

                InputStream in = con.getInputStream();

                reader = new BufferedReader(new InputStreamReader(in));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                return  buffer.toString();

            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            } finally {

                if(con != null) {

                    con.disconnect();
                }
                try {

                    if(reader != null) {

                        reader.close();
                    }
                }catch (IOException e) {

                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                sList.clear();
                JSONArray jsonArray = new JSONArray(s);

                for(int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    score = new Score();

                    score.setId(jsonObject.getInt("id"));
                    score.setRanking(jsonObject.getInt("ranking"));
                    score.setScore(jsonObject.getDouble("score"));
                    score.setUserid(jsonObject.getString("userid"));

                    sList.add(score);
                }

            } catch (JSONException e) {

                e.printStackTrace();
            }

            slAdapt = new ScoreListAdapter(getApplicationContext(), sList);
            scoreList.setAdapter(slAdapt);
            progressBar.setProgress(100);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
