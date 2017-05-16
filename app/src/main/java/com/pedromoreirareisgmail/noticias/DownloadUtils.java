package com.pedromoreirareisgmail.noticias;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

public class DownloadUtils {

    private static final String TAG = DownloadUtils.class.getSimpleName();

    public static List<Noticias> DownloadASyncTask(String urlSolicitacao) {

        if (urlSolicitacao == null) {
            return null;
        }

        URL urlDownload = createUrl(urlSolicitacao);

        String dadosJSON = "";

        try {
            dadosJSON = fazerDownload(urlDownload);
        } catch (IOException e) {
            Log.e(TAG, "Erro no Download de dados");
        }

        return ExtrairJSON.executarExtrairJSON(dadosJSON);
    }

    private static URL createUrl(String preUrl) {

        URL url = null;

        try {
            url = new URL(preUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Erro na criação da Url", e);
        }

        return url;
    }

    private static String fazerDownload(URL urlDownload) throws IOException {
        String dadosJSON = "";

        if (urlDownload == null) {
            return null;
        }

        HttpURLConnection conexao = null;
        InputStream inputStream = null;

        try {

            conexao = (HttpURLConnection) urlDownload.openConnection();
            conexao.setConnectTimeout(15000);
            conexao.setReadTimeout(15000);
            conexao.setRequestMethod("GET");
            conexao.connect();

            if (conexao.getResponseCode() == 200) {

                inputStream = conexao.getInputStream();
                dadosJSON = leiaInput(inputStream);
            } else {
                Log.e(TAG, "Erro ao fazer a conexão. Codigo da Resposta: " + conexao.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(TAG, "Erro ao fazer o download", e);
        } finally {

            if (conexao != null) {
                conexao.disconnect();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        return dadosJSON;
    }

    private static String leiaInput(InputStream inputStream) {

        StringBuilder saida = new StringBuilder();

        if (inputStream != null) {

            try {

                InputStreamReader decodificador = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader cache = new BufferedReader(decodificador);
                String linha = cache.readLine();

                while (linha != null) {
                    saida.append(linha);
                    linha = cache.readLine();
                }
            } catch (IOException e) {
                Log.e(TAG, "Erro na leitura do InputStream ", e);
            }
        }

        return saida.toString();
    }
}
