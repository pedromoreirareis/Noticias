package com.pedromoreirareisgmail.noticias;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;

import com.pedromoreirareisgmail.noticias.databinding.ContainerRecyclerviewBinding;

import static android.view.View.GONE;

public class Utils {


    private static final String URL_THE_GUARDIAN = "http://content.guardianapis.com/search";
    private static final String CHAVE_THE_GUARDIAN = "717bb387-af84-40dd-ab02-4cbc8ceec742";
    private static final String SHOW_FIELDS = "trailText,thumbnail";  // %2C = , - virgula
    private static final String SECTION_TECHNOOGY = "technology";
    private static ContainerRecyclerviewBinding mBindingContainer;

    public Utils() {

    }

    public static Boolean temInternet(Context context) {

        // Cria o gerenciador de conectividade
        // Nos serviços do sistema verifica o serviço de conectividade
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Informações da rede
        // Pega o status da conexão das redes (WI-FI, GPRS e etc )
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // Se tiver dados em networkInfo = true
        // Se tiver rede ativa e conectada = true
        // return true
        return (networkInfo != null && networkInfo.isConnected());

    }

    public static void progressBarEstado(Context context, Boolean ativo) {

        mBindingContainer = UltimasFragment.mBinding;

        if (ativo) {
            ProgressBar progress = mBindingContainer.pbProgresso;
            progress.setVisibility(View.VISIBLE);
        } else {
            ProgressBar progress = mBindingContainer.pbProgresso;
            progress.setVisibility(GONE);
        }

    }

    /**
     * Prepara a url de pesquisa de acordo com a Activity que chama o método
     *
     * @param pesquisar String com nome da Activity que esta chamando o método
     * @return String co url de pesquisa
     */
    public static String preparaUrlPesquisa(Context context, String pesquisar) {



        /*  SharedPrefrences Interface para acessar e modificar as preferencias do usuario
            getDefaultSharedPreferences - aponta para o arquivo padrao que é usado pela estrutura de prefencias

            Atraves de uma instancia de SharedPreferences, verifica se existe um arquivo com as preferencias
            do usuário. Caso não tenha, pega a preferencia indica como padrão e depois cria o arquivo
            com as preferencias do usuario
         */
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // Preferencia - Quantidade de noticias por pagina
        String pagesize = sharedPreferences.getString(
                context.getString(R.string.config_pagesize_key),
                context.getString(R.string.config_pagesize_default));

        // Preferencia - Ordem da apresentação das noticias
        String ordem = sharedPreferences.getString(
                context.getString(R.string.config_orderby_key),
                context.getString(R.string.config_orderby_defalut));

        // Cria um Uri com a url Basica do The Guardian
        Uri uriBase = Uri.parse(URL_THE_GUARDIAN);

        //  Cria o Uri Builder onde podemos acrescentar os parametros de pesquisa
        //  no uriBase e chave de autenticação
        Uri.Builder uriBuilder = uriBase.buildUpon();


        if (pesquisar.equals(context.getString(R.string.frag_ultimas))) {
            //uriBuilder.appendQueryParameter("q", "sexy");
            uriBuilder.appendQueryParameter("show-fields", SHOW_FIELDS);
        }


        // Se o fragmento que chamou foi o de tecnologia
        if (pesquisar.equals(context.getString(R.string.frag_tecnologia))) {
            uriBuilder.appendQueryParameter("section", SECTION_TECHNOOGY);
            uriBuilder.appendQueryParameter("show-fields", SHOW_FIELDS);
        }
        uriBuilder.appendQueryParameter("page-size", pagesize);
        uriBuilder.appendQueryParameter("order-by", ordem);
        uriBuilder.appendQueryParameter("api-key", CHAVE_THE_GUARDIAN);


        return uriBuilder.toString();

    }


}
