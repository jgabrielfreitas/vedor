package hackpuc.vedor.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.database.Favorite;
import hackpuc.vedor.objects.Politic;
import hackpuc.vedor.utils.Utils;

/**
 * Created by Cristian on 12/12/2015.
 */
public class CandidateAdapter extends BaseAdapter {

    Context context;
    List<Politic> politicList;
    Select select = new Select();
    List<Favorite> favorites = select.all().from(Favorite.class).execute();

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
        final Politic politic = politicList.get(position);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.expandable_item, parent, false);

        ImageView partyImageView  = (ImageView) view.findViewById(R.id.partyImageView);
        TextView nameTextView     = (TextView) view.findViewById(R.id.nameTextView);

        TextView partyTextView    = (TextView) view.findViewById(R.id.partyTextView);
        final CheckBox favoriteCheckBox = (CheckBox) view.findViewById(R.id.favoriteCheckBox);

        for (Favorite favorite : favorites)
            if(politic.getObjectId().equals(favorite.getObjectId()))
                favoriteCheckBox.setChecked(true);

        favoriteCheckBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(favoriteCheckBox.isChecked() == false) {
                    if(select.from(Favorite.class).where("ObjectID =?", politic.getObjectId()).executeSingle() != null) {
                        Toast.makeText(context, "Político removido dos favoritos", Toast.LENGTH_SHORT).show();
                        select.from(Favorite.class).where("ObjectID =?", politic.getObjectId()).executeSingle().delete();
                    }
                } else {

                    if(select.from(Favorite.class).where("ObjectID =?", politic.getObjectId()).executeSingle() == null) {
                        Toast.makeText(context, "Político adicionado aos favoritos", Toast.LENGTH_SHORT).show();
                        Favorite favorite = new Favorite();
                        favorite.setPolitic(politic);
                        favorite.setObjectId(politic.getObjectId());
                        favorite.save();
                    }
                }

                for (Model model : select.all().from(Favorite.class).execute()) {
                    Log.e("Model", ((Favorite) model).getObjectId());
                }
            }
        });

        TextView statusTextView   = (TextView) view.findViewById(R.id.statusTextView);

        partyImageView.setImageResource(Utils.inputBrandParty(politic.getCandidatePartyInitials()));

        if (politic.getCandidateName().length() <= 28)
            nameTextView.setText(politic.getCandidateName());
        else
            nameTextView.setText(politic.getCandidateName().substring(0, 28) + ".");

        String party = politic.getCandidatePartyNumber() + " - " + politic.getCandidatePartyInitials();
        if (party.length() <= 28)
		    partyTextView.setText(party);
        else
            partyTextView.setText(party.substring(0, 28) + ".");

//		favoriteCheckBox.setChecked();

        if (politic.getCandidateTurnDescription().equals("NULL") == true)
            statusTextView.setText("NÃO ELEITO");
        else
            statusTextView.setText(politic.getCandidateTurnDescription());

        return view;
    }
}