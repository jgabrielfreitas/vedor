package hackpuc.vedor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.item.StateItem;

/**
 * Created by Cristian on 11/12/2015.
 */
public class StateAdapter extends BaseAdapter {

    Context context;
    List<StateItem> stateItemList;

    public StateAdapter(Context context, List<StateItem> stateItemList) {
        this.context = context;
        this.stateItemList = stateItemList;
    }

    @Override
    public int getCount() {
        return stateItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return stateItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StateItem stateItem = stateItemList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.state_row, parent, false);

        ImageView stateImageView = (ImageView) view.findViewById(R.id.stateImageView);
        TextView  stateTextView  = (TextView) view.findViewById(R.id.stateTextView);

        stateImageView.setImageResource(stateItem.getImageState());
        stateTextView.setText(stateItem.getNameState());

        return view;
    }
}
