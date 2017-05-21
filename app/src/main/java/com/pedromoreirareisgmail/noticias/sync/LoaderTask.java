package com.pedromoreirareisgmail.noticias.sync;

import android.content.Context;

import com.pedromoreirareisgmail.noticias.noticia.Noticias;

import java.util.List;


public class LoaderTask extends android.support.v4.content.AsyncTaskLoader {

    private String mUrl;

    public LoaderTask(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Noticias> loadInBackground() {
        return DownloadUtils.downloadASyncTask(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
