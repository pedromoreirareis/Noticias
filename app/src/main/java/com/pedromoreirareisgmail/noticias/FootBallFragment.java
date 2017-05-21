package com.pedromoreirareisgmail.noticias;


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

import com.pedromoreirareisgmail.noticias.databinding.ContainerRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

public class FootBallFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Noticias>>, AdapterToViews.RecyclerViewOnClick {

    private static final int LOADER_ID = 4;
    private ContainerRecyclerviewBinding mBinding;
    private int mPaginaAtual = 1;
    private List<Noticias> mNoticias;
    private AdapterToViews mAdapter;

    public FootBallFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.container_recyclerview, container, false);

        Utils.progressBarEstado(true, mBinding);

        mNoticias = new ArrayList<>();
        mAdapter = new AdapterToViews(getContext(), mNoticias);

        final RecyclerView recyclerView = mBinding.rvList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (mNoticias.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {

                    mPaginaAtual = mPaginaAtual + 1;
                    restartLoader();
                    Utils.progressBarEstado(true, mBinding);
                }
            }
        });

        mAdapter.setRecyclerViewOnClick(this);
        recyclerView.setAdapter(mAdapter);

        getLoaderManager().initLoader(LOADER_ID, null, this);

        return mBinding.getRoot();
    }

    @Override
    public Loader<List<Noticias>> onCreateLoader(int id, Bundle args) {
        String page = String.valueOf(mPaginaAtual);
        return new LoaderTask(getContext(), Utils.preparaUrlPesquisa(page, getContext(), getString(R.string.frag_football)));
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
        getLoaderManager().restartLoader(LOADER_ID, null, this);
    }


    @Override
    public void onLoaderReset(Loader<List<Noticias>> loader) {

    }

    @Override
    public void OnClickListener(int position) {
        final Noticias noticiaAtual = mNoticias.get(position);
        String url = noticiaAtual.getmWebUrl();
        Uri webUrl = Uri.parse(url);

        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUrl);
        if (webIntent.resolveActivity(getContext().getPackageManager()) != null) {
            getContext().startActivity(webIntent);
        }
    }
}
