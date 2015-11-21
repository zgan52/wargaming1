package com.example.euj.wargaming.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.euj.wargaming.R;
import com.example.euj.wargaming.entites.Joueurtanks;
import com.example.euj.wargaming.entites.ListJeux;
import com.example.euj.wargaming.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Bouba on 19/11/2015.
 */
public class ListJoueurTankCustomAdapter extends ArrayAdapter<Joueurtanks> {
    private int resourceId = 0;
    private LayoutInflater inflater;
    public Context mContext;

    public ListJoueurTankCustomAdapter(Context context, int resourceId, List<Joueurtanks> mediaItems) {
        super(context, 0, mediaItems);
        this.resourceId = resourceId;
        this.mContext = context;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    //ViewHolder Design Pattern
    static class ViewHolder {
        public TextView textTitle, textvictoire,textbataille;
        public ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        ViewHolder holder = new ViewHolder();
        //Réutiliser les Views
        if (rowView == null) {
            rowView = inflater.inflate(resourceId, parent, false);
            //Configuration du ViewHolder
            holder.image = (ImageView) rowView.findViewById(R.id.tankjoueurimage);
            holder.textTitle = (TextView) rowView.findViewById(R.id.tankjoueurtag);
            holder.textvictoire = (TextView) rowView.findViewById(R.id.tankjoueurvictoire);
            holder.textbataille = (TextView) rowView.findViewById(R.id.tankjoueurbatailles);
            Utils.changeFont(mContext.getAssets(), holder.textTitle);
            rowView.setTag(holder);
        }else {
            //Affecter les données aux Views
            holder = (ViewHolder) rowView.getTag();
        }
        Joueurtanks article = getItem(position);
        holder.textvictoire.setText(article.getVictoires());
        holder.textbataille.setText(article.getBatailles());
        holder.textTitle.setText(article.getTag());
        Picasso.with(mContext).load(article.getImage())
                .into(holder.image);

        return rowView;
    }

}
