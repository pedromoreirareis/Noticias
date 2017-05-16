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
    private static final String SHOW_FIELDS = "trailText,thumbnail";
    private static final String SECTION_TECHNOOGY = "technology";
    private static final String FORMAT = "json";

    public Utils() {

    }

    public static Boolean temInternet(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static void progressBarEstado(Boolean ativo, ContainerRecyclerviewBinding mBinding) {

        if (ativo) {
            ProgressBar progress = mBinding.pbProgresso;
            progress.setVisibility(View.VISIBLE);
        } else {
            ProgressBar progress = mBinding.pbProgresso;
            progress.setVisibility(GONE);
        }
    }

    public static String preparaUrlPesquisa(String page, Context context, String pesquisar) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        String pagesize = sharedPreferences.getString(
                context.getString(R.string.config_pagesize_key),
                context.getString(R.string.config_pagesize_default));

        String ordem = sharedPreferences.getString(
                context.getString(R.string.config_orderby_key),
                context.getString(R.string.config_orderby_defalut));

        Uri uriBase = Uri.parse(URL_THE_GUARDIAN);

        Uri.Builder uriBuilder = uriBase.buildUpon();

        if (pesquisar.equals(context.getString(R.string.frag_ultimas))) {
            uriBuilder.appendQueryParameter("show-fields", SHOW_FIELDS);
            uriBuilder.appendQueryParameter("page", page);
        }

        if (pesquisar.equals(context.getString(R.string.frag_tecnologia))) {
            uriBuilder.appendQueryParameter("section", SECTION_TECHNOOGY);
            uriBuilder.appendQueryParameter("show-fields", SHOW_FIELDS);
        }
        uriBuilder.appendQueryParameter("order-by", ordem);
        uriBuilder.appendQueryParameter("format", FORMAT);
        uriBuilder.appendQueryParameter("page-size", pagesize);
        uriBuilder.appendQueryParameter("api-key", CHAVE_THE_GUARDIAN);

        return uriBuilder.toString();
    }
}
