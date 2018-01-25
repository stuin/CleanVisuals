package com.stuin.cleanvisuals;

import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stuart on 2/11/2017.
 */
public class Request {
    public static String address;
    public static boolean error;

    private static boolean running;

    public void start(String query) {
        String start = "http://";

        if(!running) new serverRequest().execute(query, start + address);
        running = true;
    }

    public void run(List<String> s) {

    }

    private class serverRequest extends AsyncTask<String, String, List<String>> {
        @Override
        protected List<String> doInBackground(String... params) {
            BufferedReader reader;
            List<String> out = new ArrayList<>();
            try {
                URL url = new URL(params[1] + params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(500);
                connection.setConnectTimeout(500);

                try {
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    String s = reader.readLine();
                    while(s != null) {
                        out.add(s);
                        s = reader.readLine();
                    }
                } finally {
                    connection.disconnect();
                }
                return out;
            } catch(Exception e) {
                //No connection
                error = true;
            }
            return out;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            running = false;
            error = false;
            if(!strings.isEmpty()) run(strings);
            else error = true;
        }
    }
}
