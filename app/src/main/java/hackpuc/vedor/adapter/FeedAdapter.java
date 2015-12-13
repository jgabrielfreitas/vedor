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
import hackpuc.vedor.objects.FeedItem;

/**
 * Created by JGabrielFreitas on 13/12/15.
 */
public class FeedAdapter extends BaseAdapter {

    Context context;
    List<FeedItem> feedItems;

    public FeedAdapter(Context context, List<FeedItem> feedItems) {
        this.context = context;
        this.feedItems = feedItems;
    }

    public int getCount() {
        return feedItems.size();
    }

    public FeedItem getItem(int position) {
        return feedItems.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        FeedItem item = feedItems.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.feed_row, parent, false);

        TextView feedTitle  = (TextView) view.findViewById(R.id.feedTitle);
        feedTitle.setText(item.getTitle());

        return view;
    }
}
