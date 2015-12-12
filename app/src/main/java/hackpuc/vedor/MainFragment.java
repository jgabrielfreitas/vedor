package hackpuc.vedor;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements AdapterView.OnItemClickListener{

    private View view;
    private ListView listView;

    private List<StateItem> stateItemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);

        listView = (ListView) view.findViewById(R.id.listView);

        listView.setOnItemClickListener(this);

        StateAdapter stateAdapter = new StateAdapter(getActivity(), stateItemList);
        listView.setAdapter(stateAdapter);

        return view;
    }

    public void setStateItemList(List<StateItem> stateItemList) {
        this.stateItemList = stateItemList;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getActivity(), StateActivity.class);
        intent.putExtra("name", stateItemList.get(position).getNameState());
        intent.putExtra("code", stateItemList.get(position).getCodeState());
        startActivity(intent);
    }
}
