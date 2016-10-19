package com.example.gio.threadsexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ArrayList<Country> countryList=new ArrayList<>();
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getDataUsingOkHttp();

    }

    public void getDataUsingOkHttp(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url(Constants.URL_STRING)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response){
                try {
                    result = response.body().string();
                    countryList = parsejson(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new CustomAdapter(countryList);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });


    }
    private ArrayList<Country> parsejson(String jsonString) {

        try {
            JSONObject jsonObject=new JSONObject(jsonString);
            String arrayString=jsonObject.getJSONObject("RestResponse").getJSONArray("result").toString();
            Type collection=new TypeToken<ArrayList<Country>>(){}.getType();
            return new Gson().fromJson(arrayString,collection);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
