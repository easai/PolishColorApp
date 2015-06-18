package polish.easai.polishcolorapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Extend ArrayAdapter, override getView()
 */
public class PolishAdapter extends ArrayAdapter<PolishWord> {
    private LayoutInflater inflater;
    private int listLayoutId;
    private List<PolishWord> itemList;

    public PolishAdapter(Context context, int resource, List<PolishWord> objects) {
        super(context, resource, objects);
        listLayoutId=resource;
        itemList=objects;
        inflater=(LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if(convertView!=null){
            view=convertView;
        }
        else{
            view=inflater.inflate(listLayoutId,null);
        }
        PolishWord item=itemList.get(position);

        TextView textView=(TextView)view.findViewWithTag("text");
        textView.setText(item.getPl());

        TextView colorView=(TextView)view.findViewWithTag("color");
        colorView.setBackgroundColor(item.getColor());

        return view;
    }
}
