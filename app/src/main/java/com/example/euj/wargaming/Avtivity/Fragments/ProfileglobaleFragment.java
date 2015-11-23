package com.example.euj.wargaming.Avtivity.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.euj.wargaming.Avtivity.AllStatistiques;
import com.example.euj.wargaming.Avtivity.MainActivity;
import com.example.euj.wargaming.InputStreamOperations;
import com.example.euj.wargaming.R;
import com.example.euj.wargaming.entites.Joueur;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ProfileglobaleFragment extends Fragment {

    Joueur joueur = new Joueur();
    private TextView cote,batailles,arbres,degat,frags,exrerience;
    public ProfileglobaleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profileglobal, container, false);

        cote=(TextView) rootView.findViewById(R.id.Cote);
        batailles=(TextView) rootView.findViewById(R.id.batailles);
        arbres=(TextView) rootView.findViewById(R.id.arbres);
        degat=(TextView) rootView.findViewById(R.id.degat);
        frags=(TextView) rootView.findViewById(R.id.frags);
        exrerience=(TextView) rootView.findViewById(R.id.exrerience);
        Button allstat = (Button) rootView.findViewById(R.id.buttonstatistiques);


        new Asycjoueurs().execute();

        allstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AllStatistiques.class);
                startActivity(intent);


            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    class Asycjoueurs extends AsyncTask<String, Void, String> {
       // ProgressDialog barProgressDialog = new ProgressDialog(getActivity());


        @Override
        protected void onPreExecute() {

            //barProgressDialog.setTitle("Loading ...");
            //barProgressDialog.setMessage("Load Data in progress ...");
            //barProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //barProgressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

                parseJsonTeachers();

            return null;
        }

        @Override
        protected void onPostExecute(String result) {


            cote.setText(joueur.getCote());
            batailles.setText(joueur.getBatailles());
            exrerience.setText(joueur.getExrerience());
            frags.setText(joueur.getFrags());
            arbres.setText(joueur.getArbres());
            degat.setText(joueur.getDegat());

            // barProgressDialog.dismiss();

            super.onPostExecute(result);
        }
    }




    void parseJsonTeachers() {
        try {

            String myURL = "http://api.worldoftanks.eu/wot/account/info/?application_id=7ae23772426dd2b4d758769f65850f26&account_id=518153743";
            URL url = new URL(myURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            String result = InputStreamOperations.InputStreamToString(inputStream);
            JSONObject jsonObject = new JSONObject(result);

            JSONObject globalRating=jsonObject.getJSONObject("data").getJSONObject("518153743");
            JSONObject statistics=jsonObject.getJSONObject("data").getJSONObject("518153743").getJSONObject("statistics");
            JSONObject all=jsonObject.getJSONObject("data").getJSONObject("518153743").getJSONObject("statistics").getJSONObject("all");
            joueur.setCote(globalRating.getString("global_rating"));
            joueur.setBatailles(all.getString("battles"));
            joueur.setExrerience(all.getString("xp"));
            joueur.setFrags(all.getString("frags"));
            joueur.setArbres(statistics.getString("trees_cut"));
            joueur.setDegat(all.getString("damage_dealt"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}