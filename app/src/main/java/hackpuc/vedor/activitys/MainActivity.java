package hackpuc.vedor.activitys;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.ArrayList;
import java.util.List;

import hackpuc.vedor.R;
import hackpuc.vedor.adapter.OfficeAdapter;
import hackpuc.vedor.fragment.MainFragment;
import hackpuc.vedor.interfaces.ParseCallback;
import hackpuc.vedor.item.StateItem;
import hackpuc.vedor.objects.Politic;
import hackpuc.vedor.utils.ParseFields;
import hackpuc.vedor.utils.ParseManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static List<Politic> politicList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle("Brasil");
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new StateFragment()).commit();
    }

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        }

        final SearchView finalSearchView = searchView;

        final ImageView mCloseButton = (ImageView) searchView.findViewById(R.id.search_close_btn);

        SearchView.OnQueryTextListener searchListener = new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String arg0) {
                return false;
            }
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.w("TAG", query);
                mCloseButton.performClick();
                finalSearchView.clearFocus();

                // create request here and sent
                ParseManager.createCustomParserRequest(MainActivity.this).setWorkInBackground(false)
                        .setDialogMessage("Carregando...")
                        .addWhereStartWith(ParseFields.POLITIC_NAME, query.toUpperCase())
                        .setParseCallback(new ParseCallback() {
                            public void onSuccess(List<ParseObject> parseObjects) {

                                politicList = new ArrayList<>();

                                for (ParseObject parseObject : parseObjects) {
                                    Log.e("Response", "Name:" + new Politic(parseObject));
                                    politicList.add(new Politic(parseObject));
                                }
//                                  Log.e("Response", "Total of rows: " + parseObjects.size());
                                Intent intent = new Intent(MainActivity.this, CandidateActivity.class);
                                intent.putExtra("from", "main");
                                startActivity(intent);
                            }

                            public void onError(ParseException e) {

                            }
                        }).doRequest();

                return true;
            }
        };
        searchView.setOnQueryTextListener(searchListener);

        return true;
    }

   /* public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        MainFragment mainFragment = new MainFragment();
        List<StateItem> stateItemList = new ArrayList<>();

        switch (item.getItemId()){

            case R.id.nav_br:
                getSupportActionBar().setTitle("Brasil");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new StateFragment()).commit();
                break;
            case R.id.nav_n:
                getSupportActionBar().setTitle("Norte");
                stateItemList.add(new StateItem(R.drawable.ic_acre, "Acre", "AC"));
                stateItemList.add(new StateItem(R.drawable.ic_amapa, "Amapá", "AP"));
                stateItemList.add(new StateItem(R.drawable.ic_amazonas, "Amazonas", "AM"));
                stateItemList.add(new StateItem(R.drawable.ic_para, "Pará", "PA"));
                stateItemList.add(new StateItem(R.drawable.ic_rondonia, "Rondônia", "RO"));
                stateItemList.add(new StateItem(R.drawable.ic_roraima, "Roraima", "RR"));
                stateItemList.add(new StateItem(R.drawable.ic_tocantins, "Tocantins", "TO"));
                break;
            case R.id.nav_ne:
                getSupportActionBar().setTitle("Nordeste");
                stateItemList.add(new StateItem(R.drawable.ic_alagoas, "Alagoas", "AL"));
                stateItemList.add(new StateItem(R.drawable.ic_bahia, "Bahia", "BA"));
                stateItemList.add(new StateItem(R.drawable.ic_ceara, "Ceará", "CE"));
                stateItemList.add(new StateItem(R.drawable.ic_maranhao, "Maranhão","MA"));
                stateItemList.add(new StateItem(R.drawable.ic_paraiba, "Paraíba", "PB"));
                stateItemList.add(new StateItem(R.drawable.ic_pernambuco, "Pernambuco", "PE"));
                stateItemList.add(new StateItem(R.drawable.ic_piaui, "Piauí", "PI"));
                stateItemList.add(new StateItem(R.drawable.ic_rio_grande_do_norte, "Rio Grande do Norte", "RN"));
                stateItemList.add(new StateItem(R.drawable.ic_sergipe, "Sergipe", "SE"));
                break;
            case R.id.nav_co:
                getSupportActionBar().setTitle("Centro-Oeste");
                stateItemList.add(new StateItem(R.drawable.ic_distrito_federal, "Distrito Federal", "DF"));
                stateItemList.add(new StateItem(R.drawable.ic_goias, "Goiás", "GO"));
                stateItemList.add(new StateItem(R.drawable.ic_mato_grosso, "Mato Grosso", "MT"));
                stateItemList.add(new StateItem(R.drawable.ic_mato_grosso_do_sul, "Mato Grosso do Sul", "MS"));
                break;
            case R.id.nav_se:
                getSupportActionBar().setTitle("Sudeste");
                stateItemList.add(new StateItem(R.drawable.ic_espirito_santo, "Espírito Santo", "ES"));
                stateItemList.add(new StateItem(R.drawable.ic_minas_gerais, "Minas Gerais", "MG"));
                stateItemList.add(new StateItem(R.drawable.ic_rio_de_janeiro, "Rio de Janeiro", "RJ"));
                stateItemList.add(new StateItem(R.drawable.ic_sao_paulo, "São Paulo", "SP"));
                break;
            case R.id.nav_s:
                getSupportActionBar().setTitle("Sul");
                stateItemList.add(new StateItem(R.drawable.ic_parana, "Paraná", "PR"));
                stateItemList.add(new StateItem(R.drawable.ic_rio_grande_do_sul, "Rio Grande do Sul", "RS"));
                stateItemList.add(new StateItem(R.drawable.ic_santa_catarina, "Santa Catarina", "SC"));
                break;
            case R.id.nav_check:

                break;
            case R.id.close:
                ActivityCompat.finishAffinity(this);
                break;
        }

        if (stateItemList.isEmpty() == false) {
            mainFragment.setStateItemList(stateItemList);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, mainFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // fragments to inflate
    public class StateFragment extends Fragment {

        private View view;
        private ListView listView;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_state, container, false);
            instanceViewsFragmentAll(view);

            return view;
        }

        /**
         * GET ALL ORDERS AND SHOW
         * */
        private void instanceViewsFragmentAll(View view) {

            List<String> offStringList = new ArrayList<>();
            offStringList.add("Presidente");
            offStringList.add("Vice-Presidente");
            OfficeAdapter officeAdapter = new OfficeAdapter(getActivity(), offStringList);
            listView = (ListView) view.findViewById(R.id.listView);
            listView.setAdapter(officeAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // create request here and sent
                    ParseManager.createCustomParserRequest(getActivity()).setWorkInBackground(false)
                            .setDialogMessage("Carregando...")
                            .addWhereEqualsTo(ParseFields.POLITIC_CARGO, ((OfficeAdapter) listView.getAdapter()).getItem(position).toUpperCase())
                            .setParseCallback(new ParseCallback() {
                                public void onSuccess(List<ParseObject> parseObjects) {

                                    politicList = new ArrayList<>();

                                    for (ParseObject parseObject : parseObjects) {
                                        Log.e("Response", "Name:" + new Politic(parseObject));
                                        politicList.add(new Politic(parseObject));
                                    }
//                                  Log.e("Response", "Total of rows: " + parseObjects.size());
                                    Intent intent = new Intent(getActivity(), CandidateActivity.class);
                                    intent.putExtra("from", "main");
                                    startActivity(intent);
                                }

                                public void onError(ParseException e) {

                                }
                            }).doRequest();
                }
            });

        }
    }
}
