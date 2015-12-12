package hackpuc.vedor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cristian on 12/12/2015.
 */
public class OfficeAdapter extends BaseAdapter {

    Context context;
    List<String> officeItemList;

    public OfficeAdapter(Context context, List<String> officeItemList) {
        this.context = context;
        this.officeItemList = officeItemList;
    }

    @Override
    public int getCount() {
        return officeItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return officeItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String officeItem = officeItemList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.office_row, parent, false);

        TextView officeTextView  = (TextView) view.findViewById(R.id.officeTextView);

        officeTextView.setText(officeItem);

        return view;
    }
}
