package com.example.sushm.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.sushm.demo.adapters.RvAdapter;
import com.example.sushm.demo.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private String URL_JSON = "https://gist.githubusercontent.com/sushmithaPANNIR/ae4cd67d22f624b6b637c559ce3c99f2/raw/63f1ec3d78cdbf2120a15982f2783b6b7f0cd827/Adopt.json";
    private JsonArrayRequest ArrayRequest;
    private RequestQueue requestQueue;
    private List<Anime> lstAnime = new ArrayList<>();
    private RecyclerView myrv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myrv = findViewById(R.id.rv);
        jsoncall();

    }


    public void jsoncall() {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0; i < response.length(); i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Anime anime = new Anime();

                        anime.setName(jsonObject.getString("name"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setImage_url(jsonObject.getString("img"));
                        anime.setStudio(jsonObject.getString("studio"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        lstAnime.add(anime);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                Toast.makeText(SecondActivity.this, "Veuillez patienter... Le chargement peut prendre quelques secondes ", Toast.LENGTH_SHORT).show();
                //Toast.makeText(SecondActivity.this,lstAnime.get(1).toString(),Toast.LENGTH_SHORT).show();

                setRvadapter(lstAnime);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(SecondActivity.this);
        requestQueue.add(ArrayRequest);
    }


    public void setRvadapter(List<Anime> lst) {

        RvAdapter myAdapter = new RvAdapter(this, lst);
        myrv.setLayoutManager(new LinearLayoutManager(this));
        myrv.setAdapter(myAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.retour, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:
                Intent intent1 = new Intent(this, MainActivity.class);
                this.startActivity(intent1);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}

