package controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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

public class Scoreboard extends AppCompatActivity {

    //URL
    String url = "http://192.168.2.129:8080/API_Scoreboard/webapi/myresource/scores";

    //ListView
    private ListView scoreList;

    //ArrayList / Adapter
    private List<Score> sList;
    private ScoreListAdapter slAdapt;

    //Score
    private Score score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        //ListView
        scoreList = (ListView) findViewById(R.id.list_scoreboard);

        //ScoreList
        sList = new ArrayList<>();

        new GetRequest().execute(url);

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
        }
    }
}
