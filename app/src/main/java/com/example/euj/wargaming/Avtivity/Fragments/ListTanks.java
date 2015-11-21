package com.example.euj.wargaming.Avtivity.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.euj.wargaming.InputStreamOperations;
import com.example.euj.wargaming.R;
import com.example.euj.wargaming.adapters.ListJeuxCustomAdapter;
import com.example.euj.wargaming.adapters.ListTankCustomAdapter;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Euj on 21/11/2015.
 */
public class ListTanks extends Fragment {
    ListView Vehl;
    ArrayList<com.example.euj.wargaming.entites.ListTanks> vehls = new ArrayList<>();
    ListTankCustomAdapter VehlAdapter;
    TextView victoire,bataille,tag;
    public ListTanks() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wvehl, container, false);
        Vehl = (ListView) rootView.findViewById(R.id.Vehl);
        victoire = (TextView) rootView.findViewById(R.id.tankjoueurvictoire);
        bataille = (TextView) rootView.findViewById(R.id.tankjoueurbatailles);
        tag = (TextView) rootView.findViewById(R.id.tankjoueurtag);

        new Asycjoueurstanks().execute();
        Vehl.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), vehls.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;
    }
    public

    class Asycjoueurstanks extends AsyncTask<String, Void, String> {
        ProgressDialog barProgressDialog = new ProgressDialog(getActivity());


        @Override
        protected void onPreExecute() {

            barProgressDialog.setTitle("Loading ...");
            barProgressDialog.setMessage("Load Data in progress ...");
            barProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            barProgressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if(isAdded()) {
                try {
                    String myurl= "http://api.worldoftanks.eu/wot/encyclopedia/tanks/?application_id=7ae23772426dd2b4d758769f65850f26&fields=name%2Clevel%2Cimage%2Ctank_id";

                    URL url1 = new URL(myurl);
                    HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
                    connection1.connect();
                    InputStream inputStream1 = connection1.getInputStream();
                    String result1 = InputStreamOperations.InputStreamToString(inputStream1);
                    JSONObject jsonObject = new JSONObject(result1);
                    JSONObject res=jsonObject.getJSONObject("data").getJSONObject("1");
                    com.example.euj.wargaming.entites.ListTanks veh = new com.example.euj.wargaming.entites.ListTanks();

                    veh.setName(res.getString("name"));
                    veh.setLevel(res.getString("level"));
                    veh.setImage(res.getString("image"));
                    veh.setId(res.getString("tank_id"));

                    vehls.add(veh);
                /*    String tanksarray = jsonObject1.getJSONObject("data").getString("518153743");
                    parseJsonTeachers(vehls, tanksarray);*/
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            VehlAdapter = new ListTankCustomAdapter(getActivity(), R.layout.veh_details_row,vehls);
            Vehl.setAdapter(VehlAdapter);
            barProgressDialog.dismiss();
            super.onPostExecute(result);
        }
    }



/*
    void parseJsonTeachers(ArrayList<Joueurtanks> tanks,String result) {
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);
                Joueurtanks joueurtanks = new Joueurtanks();

                joueurtanks.setId((j.optString("tank_id")));
                joueurtanks.setVictoires((j.getJSONObject("statistics").optString("wins")));
                joueurtanks.setBatailles((j.getJSONObject("statistics").optString("battles")));

/**------------------------------------------Recuperation de l'image et du tag de chaque tank !-----------------------------------------*/
       /*         try {
                    String myURL2 = "http://api.worldoftanks.eu/wot/encyclopedia/vehicles/?application_id=7ae23772426dd2b4d758769f65850f26&tank_id="+joueurtanks.getId();
                    URL url2 = new URL(myURL2);
                    HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
                    connection2.connect();
                    InputStream inputStream2 = connection2.getInputStream();
                    String result2 = InputStreamOperations.InputStreamToString(inputStream2);
                    JSONObject jsonObject2 = new JSONObject(result2);

                    JSONObject tag=jsonObject2.getJSONObject("data").getJSONObject(joueurtanks.getId());
                    JSONObject image=jsonObject2.getJSONObject("data").getJSONObject(joueurtanks.getId()).getJSONObject("images");

                    joueurtanks.setTag(tag.getString("tag"));
                    joueurtanks.setImage(image.getString("big_icon"));

                }catch (Exception e) {
                    e.printStackTrace();
                }
/**----------------------------------------------------------------------------------------------------------------------------------*/
              /*  tanks.add(joueurtanks);
            }
            joueur.setJoueurtanks(tanks);
        } catch (JSONException e) {
            e.printStackTrace();


    }*/


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}