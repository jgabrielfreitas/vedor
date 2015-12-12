package hackpuc.vedor.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.objects.Politic;

/**
 * Created by Cristian on 12/12/2015.
 */
public class CandidateAdapter extends BaseAdapter {

    Context context;
    List<Politic> politicList;

    public CandidateAdapter(Context context, List<Politic> politicList) {
        this.context = context;
        this.politicList = politicList;
    }

    public int getCount() {
        return politicList.size();
    }

    public Politic getItem(int position) {
        return politicList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Politic politic = politicList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.expandable_item, parent, false);

        ImageView partyImageView  = (ImageView) view.findViewById(R.id.partyImageView);
        TextView nameTextView     = (TextView) view.findViewById(R.id.nameTextView);

        TextView partyTextView    = (TextView) view.findViewById(R.id.partyTextView);
        CheckBox favoriteCheckBox = (CheckBox) view.findViewById(R.id.favoriteCheckBox);

        TextView statusTextView   = (TextView) view.findViewById(R.id.statusTextView);


//        partyImageView.setImageResource(politic.getCandidatePartyInitials());
		nameTextView.setText(politic.getCandidateName());

		partyTextView.setText(politic.getCandidatePartyNumber() + " - " + politic.getCandidatePartyName());

//		favoriteCheckBox.setChecked();

		partyTextView.setText(politic.getCandidateTurnDescription());

        return view;
    }
}