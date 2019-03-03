package com.example.izicount.ui.fragments;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.izicount.CompteursAdapter;
import com.example.izicount.R;
import com.example.izicount.async.RetrieveCompteursAsyncTask;
import com.example.izicount.interfaces.CompteurListener;
import com.example.izicount.interfaces.CompteursLoad;
import com.example.izicount.pojo.Compteur;

import java.util.ArrayList;
import java.util.List;

public class CompteursFragment extends Fragment implements CompteursLoad {

    private RetrieveCompteursAsyncTask task;
    private CompteurListener mListener;
    private ListView mListView;
    private String mTheme;//Thème des compteurs choisi
    public CompteursFragment(){

    }

    public static CompteursFragment newinstance(String theme){ //permet de passer le thème du compteur entre l'activité et le fragment
        final CompteursFragment compteursFragment = new CompteursFragment();
        final Bundle arguments = new Bundle();
        arguments.putString("theme",theme);
        compteursFragment.setArguments(arguments);
        return compteursFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View RootView = inflater.inflate(R.layout.compteurs_fragment, container, false);
        //if (getArguments().getParcelable("theme") != null)
        if (getArguments().getString("theme") != null){
            //On récupère le thème des compteurs à afficher
            mTheme = getArguments().getString("theme");
        }
        Log.d("Fragment", "Theme received :"+ mTheme);
        mListView= (ListView) RootView.findViewById(R.id.compteursList);
        mListView.setAdapter(
                new ArrayAdapter<Compteur>(getActivity(),
                        android.R.layout.simple_list_item_1, new ArrayList<Compteur>()));
        return RootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        task = new RetrieveCompteursAsyncTask(this);
        if(mTheme!=null) {
            task.execute(mTheme);
        }
        //on va chercher les compteurs dans la bdd à afficher pour le thème choisi
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        task.cancel(true);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        Log.w("TweetListener", "onAttach called");
        if (activity instanceof CompteurListener){
            mListener =(CompteurListener) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener=null;
    }

    public void onPlus(Compteur compteur) {
        mListener.onPlus(compteur);
    }

    public void onMoins(Compteur compteur) {
        mListener.onMoins(compteur);
    }

    @Override
    public void onCompteursRetrieved(List<Compteur> compteurs) {
        final CompteursAdapter compteursAdapter =new CompteursAdapter(compteurs,mListener);
        mListView.setAdapter(compteursAdapter);
    }
}
