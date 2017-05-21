package com.pedromoreirareisgmail.noticias;


import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExtrairJSON {

    private static final String TAG = ExtrairJSON.class.getSimpleName();

    public ExtrairJSON() {
    }

    public static List<Noticias> executarExtrairJSON(String dadosJSON) {

        if (TextUtils.isEmpty(dadosJSON)) {
            return null;
        }

        List<Noticias> noticiasList = new ArrayList<>();

        try {

            JSONObject root = new JSONObject(dadosJSON);

            JSONObject responseObj = root.getJSONObject("response");
            String status = responseObj.getString("status");

            if (status.equals("ok")) {

                int total = responseObj.getInt("total");
                if (total > 0) {
                    JSONArray resultsArray = responseObj.getJSONArray("results");

                    for (int i = 0; i < resultsArray.length(); i++) {

                        JSONObject noticiaAtual = resultsArray.getJSONObject(i);

                        String sectionName = noticiaAtual.getString("sectionName");

                        String webPublicationDate = noticiaAtual.getString("webPublicationDate");

                        String webTitle = Html.fromHtml(noticiaAtual.getString("webTitle")).toString().replaceAll("\'", "");

                        String webUrl = noticiaAtual.getString("webUrl");

                        JSONObject fieldsObj = noticiaAtual.getJSONObject("fields");

                        String trailText = Html.fromHtml(fieldsObj.getString("trailText")).toString().replaceAll("\'", "");

                        String thumbnail = fieldsObj.optString("thumbnail");

                        noticiasList.add(new Noticias(sectionName, webPublicationDate, webTitle, webUrl, trailText, thumbnail));
                    }
                }

            } else {

                String message = responseObj.optString("message");
                Log.e(TAG, "Erro no retorno de dados JSON: " + message);
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return noticiasList;
    }
}
