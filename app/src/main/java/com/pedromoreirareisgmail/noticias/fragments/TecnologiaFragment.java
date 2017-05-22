package com.pedromoreirareisgmail.noticias.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pedromoreirareisgmail.noticias.R;
import com.pedromoreirareisgmail.noticias.adapters.AdapterToViews;
import com.pedromoreirareisgmail.noticias.databinding.ContainerRecyclerviewBinding;
import com.pedromoreirareisgmail.noticias.noticia.Noticias;
import com.pedromoreirareisgmail.noticias.sync.LoaderTask;
import com.pedromoreirareisgmail.noticias.utilidades.Utils;

import java.util.ArrayList;
import java.util.List;

public class TecnologiaFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Noticias>>, AdapterToViews.RecyclerViewOnClick {

    private static final int LOADER_ID = 1;
    private ContainerRecyclerviewBinding mBinding;
    private int mPaginaAtual = 1;
    private List<Noticias> mNoticias;
    private AdapterToViews mAdapter;
    private RecyclerView mRecyclerView;

    public TecnologiaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.container_recyclerview, container, false);

        Utils.progressBarEstado(true, mBinding);

        mNoticias = new ArrayList<>();
        mAdapter = new AdapterToViews(getContext(), mNoticias);

        mRecyclerView = mBinding.rvList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                LinearLayoutManager llm = (LinearLayoutManager) mRecyclerView.getLayoutManager();

                if (mNoticias.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {

                    if (Utils.temInternet(getContext())) {
                        mPaginaAtual = mPaginaAtual + 1;
                        restartLoader();
                        Utils.progressBarEstado(true, mBinding);
                    } else {
                        semInternet();
                    }
                }
            }
        });

        mAdapter.setRecyclerViewOnClick(this);
        mRecyclerView.setAdapter(mAdapter);

        if (Utils.temInternet(getContext())) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            semInternet();
        }

        return mBinding.getRoot();
    }

    @Override
    public Loader<List<Noticias>> onCreateLoader(int id, Bundle args) {
        String page = String.valueOf(mPaginaAtual);
        return new LoaderTask(getContext(), Utils.preparaUrlPesquisa(page, getContext(), getString(R.string.frag_tecnologia)));
    }

    @Override
    public void onLoadFinished(Loader<List<Noticias>> loader, List<Noticias> data) {
        if (data != null) {
            mNoticias.addAll(data);
            mAdapter.notifyDataSetChanged();
            Utils.progressBarEstado(false, mBinding);
        } else {
            TextView tvMensagem = mBinding.tvMensagem;
            tvMensagem.setText(getString(R.string.sem_dados));
        }
    }

    public void restartLoader() {

        if (Utils.temInternet(getContext())) {
            getLoaderManager().restartLoader(LOADER_ID, null, this);
        } else {
            semInternet();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Noticias>> loader) {

    }

    @Override
    public void OnClickListener(int position) {
        if (Utils.temInternet(getContext())) {

            final Noticias noticiaAtual = mNoticias.get(position);
            String url = noticiaAtual.getmWebUrl();
            Uri webUrl = Uri.parse(url);

            Intent webIntent = new Intent(Intent.ACTION_VIEW, webUrl);
            if (webIntent.resolveActivity(getContext().getPackageManager()) != null) {
                getContext().startActivity(webIntent);
            }
        } else {
            Toast.makeText(getContext(), getString(R.string.sem_internet), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Utils.temInternet(getContext())) {
            comInternet();
        } else {
            semInternet();
        }
    }

    private void semInternet() {
        Utils.progressBarEstado(false, mBinding);
        mNoticias = new ArrayList<>();
        mAdapter = new AdapterToViews(getContext(), mNoticias);
        mRecyclerView.setAdapter(mAdapter);

        TextView tvMensagem = mBinding.tvMensagem;
        tvMensagem.setText(getString(R.string.sem_internet));
        tvMensagem.setVisibility(View.VISIBLE);
    }

    private void comInternet() {
        Utils.progressBarEstado(false, mBinding);
        TextView tvMensagem = mBinding.tvMensagem;
        tvMensagem.setVisibility(View.GONE);
    }
}
