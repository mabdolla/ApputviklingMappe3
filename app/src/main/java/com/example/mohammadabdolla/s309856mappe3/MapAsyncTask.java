package com.example.mohammadabdolla.s309856mappe3;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mohammadabdolla.s309856mappe3.model.Reservasjon;
import com.example.mohammadabdolla.s309856mappe3.model.Rom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


class MapAsyncTask extends AsyncTask<String, Void, String> {
    JSONObject jsonObject;
    List<Rom> rooms = new ArrayList<>();
    List<Reservasjon> reservations = new ArrayList<>();



    @Override
    protected String doInBackground(String... urls) {
        String retur = "";
        String s = "";
        String output = "";
        for (String url : urls) {
            try {
                URL urlen = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)
                        urlen.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept",
                        "application/json");
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        (conn.getInputStream())));
                System.out.println("Output from Server .... \n");
                while ((s = br.readLine()) != null) {
                    output = output + s;
                }
                conn.disconnect();

                if(!output.isEmpty()) {
                    try {

                        JSONArray json = new JSONArray(output);
                        if (urls[0].equals("http://student.cs.hioa.no/~s309856/jsonout.php")) {
                            for (int j = 0; j < json.length(); j++) {
                                JSONObject jsonobject = json.getJSONObject(j);
                                String Romnr = jsonobject.getString("Romnr");
                                String beskrivelse = jsonobject.getString("beskrivelse");
                                String latitude = jsonobject.getString("latitude");
                                String longtitude = jsonobject.getString("longtitude");
                                Rom rom = new Rom();
                                rom.setRomNr(Romnr);
                                rom.setBeskrivelse(beskrivelse);
                                rom.setLatitude(latitude);
                                rom.setLongitude(longtitude);
                                rooms.add(rom);

                            }
                        }else if (urls[0].equals("http://student.cs.hioa.no/~s309856/jsonoutb.php")) {
                            for (int k = 0; k < json.length(); k++) {
                                JSONObject jsonobject = json.getJSONObject(k);
                                String Romnr = jsonobject.getString("Romnr");
                                String dato = jsonobject.getString("dato");
                                String tid = jsonobject.getString("tid");

                                Reservasjon reservasjon = new Reservasjon();
                                reservasjon.setRomnr(Romnr);
                                reservasjon.setDato(dato);
                                reservasjon.setTid(tid);
                                reservations.add(reservasjon);

                            }
                        }
                            {
                            return retur;
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                return "Noe gikk feil";
            }
            output = "";
        }
        return retur;
    }

    public  List<String> RoomsNrs() {

        List<String> roomsNrs = new ArrayList<>();
        for(Rom r:rooms){
            roomsNrs.add(r.getRomNr());
        }
        return roomsNrs;
    }

    public List<Reservasjon> getReservations() {
        return reservations;
    }

    public List<Rom> getRooms() {

        return rooms;
    }

    protected void onPostExecute(String ss) {
        Log.i("bestilling", ss);
        //String arr = String.valueOf(ss.split("_"));


        //textView.setText(ss);

    }


}
