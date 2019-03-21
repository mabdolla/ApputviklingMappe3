package com.example.mohammadabdolla.s309856mappe3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.mohammadabdolla.s309856mappe3.ListAdapter.ReservasjonListAdapter;
import com.example.mohammadabdolla.s309856mappe3.model.Reservasjon;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ReservasjonerActivity extends Activity {

    private ListView listView;
    List<Reservasjon> reservasjoner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservasjon);

        listView = findViewById(R.id.reservasjon_listview);

        MapAsyncTask reservasjonGetJSON = new MapAsyncTask();

        try {
            reservasjonGetJSON.execute("http://student.cs.hioa.no/~s309856/jsonoutb.php").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        reservasjoner = reservasjonGetJSON.getReservations();

        List<Reservasjon> reservasjonList =  reservasjoner;

        ReservasjonListAdapter adapter = new ReservasjonListAdapter(this, R.layout.reservasjon_listview, reservasjonList);
        listView.setAdapter(adapter);
    }

    public void Reserver(View view) {
        Intent intent = new Intent(this, BestillActivity.class);
        startActivity(intent);
        finish();
    }

    /*public void visReservasjoner(View v) {
        String tekst = "";
        List<Reservasjon> reservasjoner = HentReservasjonGetJSON.getReservations();
        for (Reservasjon reservasjon: reservasjoner) {
            tekst = tekst +
                    "Romnr: " + reservasjon.getRomnr() +
                    ", Dato: " +reservasjon.getDato() +
                    " ,Tid: " +reservasjon.getTid();

        Log.newbutton("Navn: ", tekst);
        }

        visReservasjoner.setText(tekst);
    }*/
}
