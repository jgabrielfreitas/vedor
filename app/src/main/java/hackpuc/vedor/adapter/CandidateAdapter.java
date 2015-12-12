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

    @Override
    public int getCount() {
        return politicList.size();
    }

    @Override
    public Politic getItem(int position) {
        return politicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Politic politic = politicList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.expandable_item, parent, false);

        ImageView partyImageView   = (ImageView) convertView.findViewById(R.id.partyImageView);
        TextView nameTextView     = (TextView) convertView.findViewById(R.id.nameTextView);

        TextView partyTextView    = (TextView) convertView.findViewById(R.id.partyTextView);
        CheckBox favoriteCheckBox = (CheckBox) convertView.findViewById(R.id.favoriteCheckBox);

        TextView statusTextView   = (TextView) convertView.findViewById(R.id.statusTextView);


        partyImageView.setImageResource(politic.getCandidatePartyInitials());
		nameTextView.setText(politic.getCandidateName());

		partyTextView.setText(politic.getCandidatePartyNumber() + " - " + politic.getCandidatePartyName());

//		favoriteCheckBox.setChecked();

		partyTextView.setText(politic.getCandidateTurnDescription());

        return view;
    }
}