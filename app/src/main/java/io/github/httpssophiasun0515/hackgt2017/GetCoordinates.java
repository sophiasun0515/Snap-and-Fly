package io.github.httpssophiasun0515.hackgt2017;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nancy on 10/14/17.
 */

class GetCoordinates extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String response;
        try {
            String address = strings[0];
            HTTPDataHandler http = new HTTPDataHandler();
            String url = String.format("https://maps.googleapis.com/maps/api/geocode/json?address=%s", address);
            response = http.getHTTPData(url);
            return response;
        } catch (Exception ex) {

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray addressComp = (JSONArray)(((JSONArray)jsonObject.get("results")).getJSONObject(0).get("address_components"));
            for (int i = 0; i < addressComp.length(); i++) {
                boolean hasLocality = false;
                JSONObject cur = (JSONObject) addressComp.get(i);
                JSONArray types = (JSONArray) cur.get("types");
                for (int j = 0; j < types.length(); j++) {
                    if (types.get(j).equals("locality")) {
                        hasLocality = true;
                        break;
                    }
                }
                if (hasLocality) {
                    ImageActivity.cityName = cur.get("short_name").toString();
                }
            }
            Log.d("nancy debug", ImageActivity.cityName);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}