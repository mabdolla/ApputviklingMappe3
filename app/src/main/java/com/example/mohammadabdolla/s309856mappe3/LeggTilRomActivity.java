package com.example.mohammadabdolla.s309856mappe3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class LeggTilRomActivity extends Activity {
    EditText romnrInn;
    EditText beskrivelseInn;
    EditText latitudeInn;
    EditText longitudeInn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leggtilrom);
        romnrInn = (EditText) findViewById(R.id.Skrivromnr);
        beskrivelseInn = (EditText) findViewById(R.id.SkrivBeskrivelse);
        latitudeInn = (EditText) findViewById(R.id.SkrivLatitude);
        longitudeInn = (EditText) findViewById(R.id.SkrivLongtitude);

        init();
    }

    public void init() {

        MapAsyncTask JsonRom = new MapAsyncTask();
        try {
            String resultat = JsonRom.execute("http://student.cs.hioa.no/~s309856/jsonut.php").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public void VisError(String warning) {
        final AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setCancelable(false);
        TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setText(warning);
        builder.setView(textView);
        builder.setPositiveButton("OK", null)
                .show();
    }

    public void leggRom(View v) {

        String Romnr = romnrInn.getText().toString();
        String Beskrivelse = beskrivelseInn.getText().toString();
        String Latitude = latitudeInn.getText().toString();
        String Longtitude = longitudeInn.getText().toString();

        MapAsyncTask task = new MapAsyncTask();

        if (Romnr.isEmpty() || Beskrivelse.isEmpty() || Latitude.isEmpty() || Longtitude.isEmpty()) {
            VisError("Fyll inn alle feltene!");
        } else {
            task.execute(
                    "http://student.cs.hioa.no/~s309856/jsonin.php?" +
                            "Romnr=" + Romnr +
                            "&beskrivelse=" + Beskrivelse +
                            "&latitude=" + Latitude +
                            "&longtitude=" + Longtitude);


            Toast.makeText(this, "Rom lagt inn", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
