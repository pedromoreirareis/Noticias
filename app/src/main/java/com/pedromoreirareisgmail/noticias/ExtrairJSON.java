package com.pedromoreirareisgmail.noticias;


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

        // Verifica se a String com dados JSON esta vazia
        if (TextUtils.isEmpty(dadosJSON)) {
            return null;
        }

        // Cira a lista onde os conjuntos de dados extraidos serão armazenados
        List<Noticias> noticiasList = new ArrayList<>();

        // A extração de dados JSON requer tratamento de exceções do tipo JSONExcepetion
        try {

            /* Pega a String dadosJSON como um objetoJSON
               Aqui esta a resposta obtida na solicitação
               ao The Guardian
             */
            JSONObject root = new JSONObject(dadosJSON);

            // Obtendo o objeto response
            JSONObject responseObj = root.getJSONObject("response");

            /* Inicinado alguns tratamentos */

            // verificando se a solicitação foi atendida ou teve algum erro
            String status = responseObj.getString("status");
            if (status.equals("ok")) {

                /* Obtendo o array com os dados da noticia

                    nesse array, cada objeto representa os dados
                    refrentes a uma noticia. Logo devemos percorrer
                    cada objeto do array para extrair os dados
                 */
                JSONArray resultsArray = responseObj.getJSONArray("results");

                // Laço que percorre cada objeto do array - For - sabemos a quantidade de itens
                for (int i = 0; i < resultsArray.length(); i++) {

                    // especificando com qual objeto estamos trabalhando no momento
                    JSONObject noticiaAtual = resultsArray.getJSONObject(i);

                    // Titulo
                    String webTitle = noticiaAtual.getString("webTitle");

                    // Url da noticia
                    String webUrl = noticiaAtual.getString("webUrl");

                    /*
                        Objeto com dados complementares
                        - resumo
                        - imagem noticia
                     */
                    JSONObject fieldsObj = noticiaAtual.getJSONObject("fields");

                    //TODO para html
                    // Resumo da noticia
                    String trailText = fieldsObj.getString("trailText");

                    // Url da imagem da noticia
                    String thumbnail = fieldsObj.getString("thumbnail");


                    // Adicionando dados coletado no objeto Noticia
                    // Adicionando o ojeto na lista de noticias
                    noticiasList.add(new Noticias(webTitle, webUrl, trailText, thumbnail));
                }


            } else {

                // Caso os dados retorne com erro de solicitação
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
