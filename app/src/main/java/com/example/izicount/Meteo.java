package com.example.izicount;

import android.Manifest;
import android.app.Activity;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Meteo extends Activity {
    EditText cityText;
    TextView responseView;
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

        //weathers.add(new Weather("Paris,FR", "Nuageux","01d"));
        adapter = new WeatherAdapter(Meteo.this, weathers);
        mListView.setAdapter(adapter);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.v("app", "IN ON LOCATION CHANGE)");
                    if(location!=null) {
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
        responseView = (TextView) findViewById(R.id.responseView);
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
        cityText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityText.selectAll();
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
            //responseView.setText("");
        }

        protected String doInBackground(Void... urls) {
            String city = cityText.getText().toString();
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
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            JSONParser parser=new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(response);
                String icon=((JSONObject)((JSONArray) json.get("weather")).get(0)).get("icon").toString();
                String city=json.get("name").toString();
                String country=((JSONObject) json.get("sys")).get("country").toString();
                String weather=((JSONObject)((JSONArray) json.get("weather")).get(0)).get("main").toString();
                String tempk=((JSONObject) json.get("main")).get("temp").toString();
                String tempc=new DecimalFormat("#.0").format(Double.parseDouble(tempk)- 273.15);


                Log.d("tag", icon);
                adapter.add(new Weather(city+","+country,weather+","+tempc+"°C",icon));


            }
            catch(ParseException e) {
                Log.e("MYAPP", "JSONparse exception", e);

            }
            //progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText("Found");
        }
    }

    class RetrieveWeatherByCoordinates extends AsyncTask<Double, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            //progressBar.setVisibility(View.VISIBLE);
            //responseView.setText("");
        }

        protected String doInBackground(Double... coordinates) {
            String city = cityText.getText().toString();
            Double longitude=coordinates[0];
            Double latitude=coordinates[1];

            try {
                URL url = new URL(getString(R.string.API_URL) + "lat="+latitude+"&lon="+longitude+ "&appid=" + getString(R.string.API_KEY));
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            JSONParser parser=new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(response);
                String icon=((JSONObject)((JSONArray) json.get("weather")).get(0)).get("icon").toString();
                String city=json.get("name").toString();
                String country=((JSONObject) json.get("sys")).get("country").toString();
                String weather=((JSONObject)((JSONArray) json.get("weather")).get(0)).get("main").toString();
                String tempk=((JSONObject) json.get("main")).get("temp").toString();
                String tempc=new DecimalFormat("#.0").format(Double.parseDouble(tempk)- 273.15);
                Log.d("tag", icon);
                adapter.add(new Weather(city+","+country,weather+","+tempc+"°C",icon));


            }
            catch(ParseException e) {
                Log.e("MYAPP", "JSONparse exception", e);

            }

            /*try {
                URL url = new URL("http://openweathermap.org/img/w/10d.png");
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ImageView weatherimage = (ImageView) findViewById(R.id.imageView2);
                weatherimage.setImageBitmap(bmp);
            }
            catch(Exception e) {
                Log.e("MYAPP", "URL or bitmap exception", e);
            }
*/
            Log.i("INFO", response);
            responseView.setText("Found");
            //progressBar.setVisibility(View.GONE);
        }
    }
}
