package priv.mymemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by caese_000 on 24.11.2015.
 */
public class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final int resourceID;

    private ArrayList<String> array;

    public CustomAdapter(Context context, int resource, ArrayList<String> bah) {
        super(context, resource, bah);
        this.context = context;
        this.resourceID = resource;
        array = bah;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(resourceID, parent, false);


        TextView tt = (TextView) v.findViewById(R.id.textView1);
        tt.setText(array.get(position));

        final View v2 = v;
        final ImageButton image = (ImageButton) v2.findViewById(R.id.imageButton1);
        image.setTag(position);

        image.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer index = (Integer) v.getTag();
                        ((MainActivity) context).delete(index.intValue());

                    }
                }
        );
        return v;

    }
}