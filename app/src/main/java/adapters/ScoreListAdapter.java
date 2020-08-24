package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.quickmaths.R;

import java.util.List;

import models.Score;

public class ScoreListAdapter extends ArrayAdapter<Score> {

    private Context sContext;
    private List<Score> scoreList;
    private Typeface typeface;

    public ScoreListAdapter(Context context, List<Score> objects) {
        super(context, 0, objects);

        sContext = context;
        scoreList = objects;

        //Typeface / Font
        typeface = Typeface.createFromAsset(sContext.getAssets(), "Cairo-SemiBold.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View score = convertView;

        score = LayoutInflater.from(sContext).inflate(R.layout.list_scoreboard, parent, false);

        TextView textRank = (TextView) score.findViewById(R.id.text_list_ranking);
        textRank.setText(String.valueOf(scoreList.get(position).getRanking()));
        textRank.setTypeface(typeface);

        TextView textUserid = (TextView) score.findViewById(R.id.text_list_userid);
        textUserid.setText(scoreList.get(position).getUserid());
        textUserid.setTypeface(typeface);

        TextView textScore = (TextView) score.findViewById(R.id.text_list_score);
        textScore.setText(String.valueOf(scoreList.get(position).getScore()));
        textScore.setTypeface(typeface);

        return score;
    }
}
