package tools;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostRequest extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {

        String myURL = strings[0];
        String myJsonString = strings[1];
        OutputStream out = null;

        try{

            URL url = new URL(myURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            out = new BufferedOutputStream(urlConnection.getOutputStream());

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(myJsonString);
            writer.flush();
            writer.close();
            out.close();

            urlConnection.connect();

            Log.e("", "Response code: " + urlConnection.getResponseCode());

        }catch(IOException e){

            e.printStackTrace();
        }

        return "";
    }
}
