package com.pedromoreirareisgmail.noticias;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class StatusInternet {

    private Context mContext;

    public StatusInternet(Context context) {
        mContext = context;
    }

    public Boolean temInternet() {

        // Cria o gerenciador de conectividade
        // Nos serviços do sistema verifica o serviço de conectividade
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Informações da rede
        // Pega o status da conexão das redes (WI-FI, GPRS e etc )
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // Se tiver dados em networkInfo = true
        // Se tiver rede ativa e conectada = true
        // return true
        return (networkInfo != null && networkInfo.isConnected());

    }

}
