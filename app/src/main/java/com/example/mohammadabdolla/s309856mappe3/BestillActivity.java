package com.example.mohammadabdolla.s309856mappe3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class BestillActivity extends Activity {
    EditText romnrInn;
    EditText datoInn;
    EditText tidInn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestill);
        romnrInn= (EditText) findViewById(R.id.Skrivromnr);
        datoInn= (EditText) findViewById(R.id.SkrivDato);
        tidInn= (EditText) findViewById(R.id.SkrivTid);

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



    public void leggBestilling(View v) {

        String Romnr = romnrInn.getText().toString();
        String Dato = datoInn.getText().toString();
        String Tid = tidInn.getText().toString();

        MapAsyncTask task = new MapAsyncTask();

        if (Romnr.isEmpty() || Dato.isEmpty() || Tid.isEmpty()) {
            VisError("Fyll inn alle feltene!");
        } else {
            task.execute(
                    "http://student.cs.hioa.no/~s309856/jsoninb.php?" +
                            "Romnr=" + Romnr +
                            "&dato=" + Dato +
                            "&tid=" + Tid );


            Toast.makeText(this, "Bestilling lagt inn", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }
    }
}
