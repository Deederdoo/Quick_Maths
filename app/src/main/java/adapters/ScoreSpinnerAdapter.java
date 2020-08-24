package adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreSpinnerAdapter extends ArrayAdapter<String> {

    private Typeface typeface;

    public ScoreSpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        typeface = Typeface.createFromAsset(context.getAssets(), "Cairo-SemiBold.ttf");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView text = (TextView) super.getView(position, convertView, parent);
        text.setTypeface(typeface);

        return text;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView text = (TextView) super.getDropDownView(position, convertView, parent);
        text.setTypeface(typeface);

        return text;
    }
}
