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
    private static final String URL_SOLICITACAO =
            "http://content.guardianapis.com/search?show-fields=trailText%2Cthumbnail&page-size=20&api-key=717bb387-af84-40dd-ab02-4cbc8ceec742";

    // TODO: URL REQUISICAO - parte é aqui parte é do fragmento solicitante - DownloadASyncTaks entrada Url Resquisicao

    public static List<Noticias> DownloadASyncTask() {

        URL urlDownload = createUrl(URL_SOLICITACAO);

        // Vai receber os dados da solicitação
        String dadosJSON = "";

        try {
            dadosJSON = fazerDownload(urlDownload);
        } catch (IOException e) {
            Log.e(TAG, "Erro no Download de dados");
        }


        return ExtrairJSON.executarExtrairJSON(dadosJSON);
    }

    /*
        Após a junção de parte de uma url de solicitação, mais parametro
        e chave, cria um URL, verificando a dos dados da url passada
     */
    private static URL createUrl(String preUrl) {

        // Cria URL null
        URL url = null;

        // A criação de um URL solicita o tratamento de exceção
        // com MalformedURLException
        try {
            url = new URL(preUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Erro na criação da Url", e);
        }

        return url;
    }

    /*
        Faz o download dos dados. Caso tenha erro faz o tratamento em DownloadASyncTasK
     */
    private static String fazerDownload(URL urlDownload) throws IOException {
        String dadosJSON = "";

        if (urlDownload == null) {
            return null;
        }

        // Cria o metodo de conexao com a internet
        HttpURLConnection conexao = null;

        // Cria um Stream para receber os dados byte a byte
        InputStream inputStream = null;

        try {
            // abre a conexao com a internet pela url passada
            conexao = (HttpURLConnection) urlDownload.openConnection();

            // Parametro de tempo de espera para a conexao - 15 segundos
            conexao.setConnectTimeout(15000);

            // Parametro tempo de espera para leitura dos dados - 15 segundos
            conexao.setReadTimeout(15000);

            // Tipo de metodo de requisição - GET
            conexao.setRequestMethod("GET");

            // Se conecta
            conexao.connect();

            // Verifica se a conexao teve sucesso ou algum tipo de erro
            if (conexao.getResponseCode() == 200) {

                // Recebe os dados byte a byte
                inputStream = conexao.getInputStream();

                // Faz a leitura byte a byte e converte para String
                dadosJSON = leiaInput(inputStream);
            } else {
                Log.e(TAG, "Erro ao fazer a conexão. Codigo da Resposta: " + conexao.getResponseCode());
            }

        } catch (IOException e) {
            Log.e(TAG, "Erro ao fazer o download", e);
        } finally {

            // Se a conexao estiver conectada, desconecta
            if (conexao != null) {
                conexao.disconnect();
            }


            // Se o InputStream estiver aberto, fecha
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return dadosJSON;
    }

    /*
        Faz a leitura byte a byte, coberte os stream de bytes para stream de caracteres
        guarda os stream de caractesres em um cache(aumentando a performance),para fazer a
        conversão de stream de caracteres para para String, utilizando um StringBuilder
        evitando a repetição da criação novas instancias de Strings
     */
    private static String leiaInput(InputStream inputStream) throws IOException {

        // Cria uma String que pode acrescentar novos dados sem criar novas instancias
        StringBuilder saida = new StringBuilder();

        if (inputStream != null) {

            try {
                /* Cria um decodificador de InputStream
                 *  Recebe os dados de stream de bytes e decodifica para stream
                 *  de caracteres usando o charset especifico "UFT-8"
                 */
                InputStreamReader decodificador = new InputStreamReader(inputStream, Charset.forName("UTF-8"));

                /* Cria o chache para receber os dados de stream de caracteres do tipo "UFT-8"
                 * Com o chache a performance de conversão de stream de caracteres para String aumenta
                 */
                BufferedReader cache = new BufferedReader(decodificador);

                // String para leitura linha por linha dentro do chache
                String linha = cache.readLine();

                // Equanto houver linhas no chache para ser lidas
                while (linha != null) {
                    // Acrescenta os dados da linha que foi lida no StringBuilder
                    saida.append(linha);

                    // Lê uma nova linha
                    linha = cache.readLine();
                }
            } catch (IOException e) {
                Log.e(TAG, "Erro na leitura do InputStream ", e);
            }
        }

        // Retorna os dados do do StringBuilder saida em forma de String
        return saida.toString();
    }
}
