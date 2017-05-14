package com.pedromoreirareisgmail.noticias;

import android.content.Context;

import java.util.List;


public class LoaderTask extends android.support.v4.content.AsyncTaskLoader {

    private String mUrl;

    //TODO - construtor ira receber parte da url
    public LoaderTask(Context context, String url) {
        super(context);
        mUrl = url;
    }


    @Override
    public List<Noticias> loadInBackground() {
        return DownloadUtils.DownloadASyncTask(mUrl);
    }

    // Inciar o Loader
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
