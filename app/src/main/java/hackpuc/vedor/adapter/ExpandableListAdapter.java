package hackpuc.vedor.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.objects.Politic;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private List<String> listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<String, List<Politic>> listDataChild;

	public ExpandableListAdapter(Context context, List<String> listDataHeader,
			HashMap<String, List<Politic>> listChildData) {
		this.context = context;
		this.listDataHeader = listDataHeader;
		this.listDataChild = listChildData;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.listDataChild.get(this.listDataHeader.get(groupPosition)).get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final Politic politic = (Politic) getChild(groupPosition, childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.expandable_item, null);
		}

//		CardView cardView =  (CardView) convertView.findViewById(R.id.cardView);

		ImageView partyImageView   = (ImageView) convertView.findViewById(R.id.partyImageView);
		TextView nameTextView     = (TextView) convertView.findViewById(R.id.nameTextView);

		TextView partyTextView    = (TextView) convertView.findViewById(R.id.partyTextView);
		CheckBox favoriteCheckBox = (CheckBox) convertView.findViewById(R.id.favoriteCheckBox);

		TextView statusTextView   = (TextView) convertView.findViewById(R.id.statusTextView);


		/*nameImageView.setImageResource(politic.getCandidateUf());
		nameTextView.setText(politic.getCandidateName());

		partyImageView.setImageResource(politic.getCandidatePartyName());
		partyTextView.setText(politic.getCandidatePartyNumber() + " - " + politic.getCandidatePartyName());

		favoriteCheckBox.setChecked();

		statusImageView.setImageResource(politic.getCandidateTurnDescription());
		partyTextView.setText(politic.getCandidateTurnDescription());*/

		/*cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.w("Dentro adapter", ""+childPosition);
			}
		});*/

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.listDataChild.get(this.listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.expandable_group, null);
		}

		TextView headerTextView = (TextView) convertView.findViewById(R.id.headerTextView);
		headerTextView.setTypeface(null, Typeface.BOLD);
		headerTextView.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
