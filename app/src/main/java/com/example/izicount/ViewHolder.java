package com.example.izicount;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
    public TextView name;
    public TextView count;
    public Button moins;
    public Button plus;

    public ViewHolder (View view){
        name = (TextView) view.findViewById(R.id.nomCompteur);
        count = (TextView) view.findViewById(R.id.nbcount);
        moins = (Button) view.findViewById(R.id.moins);
        plus = (Button) view.findViewById(R.id.plus);
    }
}
