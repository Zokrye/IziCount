package com.example.izicount;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.izicount.interfaces.CompteurListener;
import com.example.izicount.pojo.Compteur;

import org.w3c.dom.Text;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class CompteursAdapter extends BaseAdapter implements View.OnClickListener  {
    List<Compteur> mCompteurs;
    LayoutInflater mInflater =LayoutInflater.from(IziCountApplication.getContext());

    public CompteursAdapter(List<Compteur> compteurs, CompteurListener listener){
        mCompteurs=compteurs;
        setListener(listener);
    }

    private CompteurListener mListener;
    public void setListener(CompteurListener listener){
        mListener=listener;
    }
    @Override
    public int getCount() {
        return null != mCompteurs ? mCompteurs.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != mCompteurs ? mCompteurs.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(null == convertView){
            convertView = mInflater.inflate(R.layout.custom_compteur,null);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder) convertView.getTag();
        }

        final Compteur compteur = (Compteur) getItem(position);
        if(compteur!=null) {
            holder.name.setText(compteur.nom);
            holder.name.setTag(position);
            holder.name.setOnClickListener(this);
            holder.count.setText(String.valueOf(compteur.getNombre()));
            holder.moins.setTag(position);
            holder.moins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    if (null != mListener) {
                        final Compteur compteur1 = (Compteur) getItem(position);
                        //Log.d("Button moins", compteur1.nom);
                        mListener.onMoins(compteur1);
                        notifyDataSetChanged();
                    }
                }
            });
            holder.plus.setTag(position);
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    if (null != mListener) {
                        final Compteur compteur1 = (Compteur) getItem(position);
                        //Log.d("Button plus", compteur1.nom);
                        mListener.onPlus(compteur1);
                        notifyDataSetChanged();
                    }
                }
            });
        }
        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        if(null != mListener){
            final Compteur compteur = (Compteur) getItem(position);
            Log.d("Clicked Button", compteur.nom);
            //mListener.onRetweet(tweet);
            //mListener.onViewTweet(tweet);
        }
    }
}
