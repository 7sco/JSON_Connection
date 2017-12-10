package com.example.franciscoandrade.starwarssearch;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BASE_URL = "https://swapi.co/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        JSONObject jsonObject = new JSONObject();
       connectWithRetrofit();
        connect();

    }



    public void connectWithRetrofit(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        RetrofitApiService service = retrofit.create(RetrofitApiService.class);

        Call<PeopleResponse> call = service.getPeople();

        call.enqueue(new Callback<PeopleResponse>() {
            @Override
            public void onResponse(Call<PeopleResponse> call, Response<PeopleResponse> response) {
                Log.d("RESULTS ", "onResponse: "+ response.body().getResults().length);
                People[] people = response.body().getResults();
                final HashMap<String,People> peopleHashMap = new HashMap<>();
                for (People person : people) {
                    peopleHashMap.put(person.getName(),person);
                }

                Button btn= (Button)findViewById(R.id.btn);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        EditText nameET= (EditText) findViewById(R.id.nameET);


                        String searchName = nameET.getText().toString();

                        TextView resultTV= (TextView)findViewById(R.id.resultTV);


                        People newPep= new People();
                        newPep= findPerson(peopleHashMap, searchName);



                        if(newPep != null && !searchName.isEmpty()){

                            resultTV.setText(newPep.getSkinColor()+"\n"+newPep.getEyeColor());

                        }
                        else{
                            resultTV.setText("Error Please enter valid name");

                        }
                    }
                });

            }

            @Override
            public void onFailure(Call<PeopleResponse> call, Throwable t) {

            }


        });


    }

    private People findPerson(HashMap<String, People> peopleHashMap, String searchName) {
        if(peopleHashMap.get(searchName) != null){
            return peopleHashMap.get(searchName);
        }else if(peopleHashMap.get(searchName) == null){
            return null;
        }
            return null;
    }



    public void connect() {

        String url="https://swapi.co/api/people/4/";

        WebConnection webConnection=new WebConnection();
        webConnection.execute(url);



    }




    private class WebConnection extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... voids) {

            try {
                URL url = new URL(voids[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                    //stringBuilder.append(line);


                }

                Log.d("DATA", "doInBackground: "+stringBuilder);

                reader.close();
                return stringBuilder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Error";
        }


        @Override
        protected void onProgressUpdate(String... values) {


        }

        @Override
        protected void onPostExecute(String s) {

            Log.d("", "onPostExecute: " + s);
        }
    }


}
