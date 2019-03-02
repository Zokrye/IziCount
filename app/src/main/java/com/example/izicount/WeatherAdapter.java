package com.example.izicount;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.izicount.tables.Weather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;


public class WeatherAdapter extends ArrayAdapter<Weather> {



    public WeatherAdapter(Context context, List<Weather> weathers) {
        super(context, 0, weathers);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.meteo_fragment,parent, false);
        }

        WeatherViewHolder viewHolder= (WeatherViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new WeatherViewHolder();
            viewHolder.place = (TextView) convertView.findViewById(R.id.place);
            viewHolder.weather = (TextView) convertView.findViewById(R.id.weather);
            viewHolder.image_weather = (ImageView) convertView.findViewById(R.id.image_weather);
            viewHolder.sync=(ImageView) convertView.findViewById(R.id.sync);
            viewHolder.delete=(ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(viewHolder);
        }


        final Weather weather = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.place.setText(weather.getPlace());
        viewHolder.weather.setText(weather.getWeather());
        viewHolder.sync.setImageResource(R.drawable.sync);
        viewHolder.delete.setImageResource(R.drawable.delete);
        Glide.with(getContext())
                .load("http://openweathermap.org/img/w/"+weather.getIcon()+".png")
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(viewHolder.image_weather);

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Your Toast message
                new DeleteWeather().execute(weather.getPlace());
                //weathers.remove(position);
                remove(weather);
            }
        }) ;
        viewHolder.sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                remove(weather);

                new UpdateWeather(position).execute(weather.getPlace());


            }
        }) ;
        return convertView;
    }
    class DeleteWeather extends AsyncTask<String, Void, Void> {


        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(String... place) {
            String city = place[0];
            DatabaseHelper.getInstance().getMeteoDAO().DeleteWeather(city);
            return null;
        }

        protected void onPostExecute() {


        }
    }
    class UpdateWeather extends AsyncTask<String, Void, Weather> {
        public UpdateWeather(int position) {
            this.position = position;
        }

        private Exception exception;
        private int position;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
        }

        protected Weather doInBackground(String... place) {
            String city = place[0];
            String response = null;
            // Do some validation here

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?" + "q=" + city + "&appid=" + "4317248e9e674fa7a416ecc8afe7acaa");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    response = stringBuilder.toString();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                Log.e("ERROR", e.getMessage(), e);
            }

            if (response == null) {
                response = "THERE WAS AN ERROR";
            }
            JSONParser parser = new JSONParser();
            Weather new_weather=null;
            try {
                JSONObject json = (JSONObject) parser.parse(response);
                String icon = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("icon").toString();
                city = json.get("name").toString();
                String country = ((JSONObject) json.get("sys")).get("country").toString();
                String weather = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("main").toString();
                String tempk = ((JSONObject) json.get("main")).get("temp").toString();
                String tempc = new DecimalFormat("#.0").format(Double.parseDouble(tempk) - 273.15);
                new_weather= new Weather(city + "," + country, weather + "," + tempc + "°C", icon);

                Log.d("tag", icon);
                DatabaseHelper.getInstance().getMeteoDAO().UpdateWeather(city + "," + country, weather + "," + tempc + "°C", icon);

                //new Meteo.LoadWeathers().execute();


            } catch (ParseException e) {
                Log.e("MYAPP", "JSONparse exception", e);

            }
            //progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            return new_weather;
        }

        protected void onPostExecute(Weather new_weather) {
            if (new_weather != null) {
                insert(new Weather(new_weather.getPlace(), new_weather.getWeather(), new_weather.getIcon()), position);
            }
        }
    }
    public class WeatherViewHolder {

        public TextView place;
        public TextView weather;
        public ImageView image_weather;
        public ImageView sync;
        public ImageView delete;

    }

}
