package com.example.izicount;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.izicount.tables.Weather;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Meteo extends AppCompatActivity {
    EditText cityText;
    //ProgressBar progressBar;
    ListView mListView;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private List<Weather> weathers = new ArrayList<Weather>();
    private WeatherAdapter adapter;
    //private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meteo);

        mListView = (ListView) findViewById(R.id.listview);

        //weathers=DatabaseHelper.getInstance().getMeteoDAO().loadWeather();
        //weathers.add(new Weather("Paris,FR", "Nuageux","01d"));
        adapter = new WeatherAdapter(Meteo.this, weathers);
        new LoadWeathers().execute();
        mListView.setAdapter(adapter);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.v("app", "IN ON LOCATION CHANGE)");
                if(location!=null) {
                    locationManager.removeUpdates(this);
                    Toast.makeText(Meteo.this, location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();
                    Log.d("app",location.getLatitude() + " " + location.getLongitude());
                    new RetrieveWeatherByCoordinates().execute(location.getLongitude(),location.getLatitude());
                    locationManager.removeUpdates(this);

                }
                //Toast.makeText(Meteo.this, "nope", 10000).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };


        Double latitude=null;
        Double longitude=null;

        cityText = (EditText) findViewById(R.id.cityText);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        ImageView locationImage = (ImageView) findViewById(R.id.location);

        final Button queryButton = (Button) findViewById(R.id.queryButton);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RetrieveWeatherByCity().execute();
            }
        });
        cityText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)){
                    new RetrieveWeatherByCity().execute();
                }
                return false;
            }
        });

        locationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestPermissions();

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 10:
                if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                    updateLocation();
                else {
                    Toast.makeText(Meteo.this, "Merci d'accepter les permisions afin de vous localiser", Toast.LENGTH_LONG).show();
                }

                return;
        }
    }
    public void RequestPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET},10);
                return;
            }
            else {
                updateLocation();
            }
        }
        else {
            updateLocation();
        }

    }

    public void updateLocation() {
        locationManager.requestLocationUpdates("gps", 0, 0, locationListener);
        //locationManager.getLastKnownLocation("gps");
    }



    class RetrieveWeatherByCity extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Void... urls) {
            String city = cityText.getText().toString();
            String response=null;
            // Do some validation here

            try {
                URL url = new URL(getString(R.string.API_URL) + "q=" + city + "&appid=" + getString(R.string.API_KEY));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    response= stringBuilder.toString();
                }
                catch(FileNotFoundException e) {
                    Log.e("ERROR", e.getMessage(), e);
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(IOException e) {
                Log.e("ERROR", e.getMessage(), e);

            }

            JSONParser parser=new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(response);
                String icon=((JSONObject)((JSONArray) json.get("weather")).get(0)).get("icon").toString();
                city=json.get("name").toString();
                String country=((JSONObject) json.get("sys")).get("country").toString();
                String weather=((JSONObject)((JSONArray) json.get("weather")).get(0)).get("main").toString();
                String tempk=((JSONObject) json.get("main")).get("temp").toString();
                String tempc=new DecimalFormat("#.0").format(Double.parseDouble(tempk)- 273.15);

                Log.d("tag", icon);
                Weather meteo=new Weather(city+","+country,weather+","+tempc+"°C",icon);
                //adapter.add(meteo);
                DatabaseHelper.getInstance().getMeteoDAO().AddWeather(meteo);

            }
            catch(ParseException e) {
                Log.e("MYAPP", "JSONparse exception", e);

            }
            catch(Exception e) {
                Log.e("MYAPP", "Exception", e);
            }
            //progressBar.setVisibility(View.GONE);
            return response;
        }

        protected void onPostExecute(String response) {
            if(response!=null) {
                new LoadWeathers().execute();
            }
            else {
                Toast.makeText(Meteo.this, "La ville que vous recherchez n'a pas été trouvée. Merci de réessayer", Toast.LENGTH_SHORT);
            }
        }
    }

    class RetrieveWeatherByCoordinates extends AsyncTask<Double, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(Double... coordinates) {
            String city = cityText.getText().toString();
            String response = null;
            Double longitude = coordinates[0];
            Double latitude = coordinates[1];

            try {
                URL url = new URL(getString(R.string.API_URL) + "lat=" + latitude + "&lon=" + longitude + "&appid=" + getString(R.string.API_KEY));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    response=stringBuilder.toString();
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
            try {
                JSONObject json = (JSONObject) parser.parse(response);
                String icon = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("icon").toString();
                city = json.get("name").toString();
                String country = ((JSONObject) json.get("sys")).get("country").toString();
                String weather = ((JSONObject) ((JSONArray) json.get("weather")).get(0)).get("main").toString();
                String tempk = ((JSONObject) json.get("main")).get("temp").toString();
                String tempc = new DecimalFormat("#.0").format(Double.parseDouble(tempk) - 273.15);
                Log.d("tag", icon);
                Weather meteo = new Weather(city + "," + country, weather + "," + tempc + "°C", icon);
                DatabaseHelper.getInstance().getMeteoDAO().AddWeather(meteo);


            } catch (ParseException e) {
                Log.e("MYAPP", "JSONparse exception", e);

            }
            return null;
        }

        protected void onPostExecute(String response) {

            new LoadWeathers().execute();
            //progressBar.setVisibility(View.GONE);
        }
    }
    class LoadWeathers extends AsyncTask<Void, Void, List<Weather>> {

        private Exception exception;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
        }

        protected List<Weather> doInBackground(Void... param) {

            //DatabaseHelper.getInstance().getMeteoDAO().AddWeather(new Weather("Paris,FR", "Nuageux","01d"));
            weathers=DatabaseHelper.getInstance().getMeteoDAO().loadWeather();

            //mListView.setAdapter(adapter);
            return weathers;
        }

        protected void onPostExecute(List<Weather> weathers) {
            adapter.clear();
            adapter.addAll(weathers);

        }

    }
}