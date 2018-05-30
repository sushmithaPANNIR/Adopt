package com.example.sushm.demo;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Process;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txt1 = (TextView) findViewById(R.id.text1);
        TextView btn1 = (TextView) findViewById(R.id.btn1);
        TextView btn2 = (TextView) findViewById(R.id.btn2);
        TextView btn3 = (TextView) findViewById(R.id.btn3);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.sym_action_call)
                        .setContentTitle("Numéro de Contact de Adopt")
                        .setContentText("123456789");
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                notificationManager.notify(0, mBuilder.build());

            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showdialog(v);
            }
        });

    }

    public void clickButton1(View view) {
        Intent i = new Intent(this, SecondActivity.class);
        this.startActivity(i);
    }


    public void Showdialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Attention");
        builder.setMessage("Voulez vous quitter l'application?");
        builder
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);
                        Process.killProcess(Process.myPid());
                        System.exit(1);
                    }
                })
                .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();

                    }
                });
        builder.create().show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.propos:
                Intent intent1 = new Intent(this, propos.class);
                this.startActivity(intent1);
                return true;
            case R.id.contact:
                Intent intent2 = new Intent(this, contact.class);
                this.startActivity(intent2);
                return true;
            case R.id.call:
                Intent intent3 = new Intent(this, call.class);
                this.startActivity(intent3);
                return true;
            case R.id.quitter:
                moveTaskToBack(true);
                Process.killProcess(Process.myPid());
                System.exit(1);
            default:
                return super.onOptionsItemSelected(item);

        }


    }
}
