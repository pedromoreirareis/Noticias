package com.pedromoreirareisgmail.noticias;

import android.databinding.DataBindingUtil;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class UltimasFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Noticias>> {

    private static final int LOADER_ID = 0;
    public static ContainerRecyclerviewBinding mBinding;

    private List<Noticias> mNoticias;
    private AdapterToViews mAdapter;
    private RecyclerView mRecyclerView;


    public UltimasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // infla o layout container
        mBinding = DataBindingUtil.inflate(inflater, R.layout.container_recyclerview, container, false);

        Utils.progressBarEstado(getContext(), true);

        mNoticias = new ArrayList<>();
        mAdapter = new AdapterToViews(getContext(), mNoticias);

        mRecyclerView = mBinding.rvList;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        // Verifica se tem internet
        if (Utils.temInternet(getContext())) {
            getLoaderManager().initLoader(LOADER_ID, null, this);
        } else {
            Utils.progressBarEstado(getContext(), false);
            TextView tvMensagem = mBinding.tvMensagem;
            tvMensagem.setText("Sem Internet");
        }


        // TODO se pesquisa tiver dados apresenta se nao mostrar mensagem que não tem dados


        // TODO Metodo separado para fazer update da tela


        return mBinding.getRoot();
    }


    @Override
    public Loader<List<Noticias>> onCreateLoader(int id, Bundle args) {

        return new LoaderTask(getContext(), Utils.preparaUrlPesquisa(getContext(), getString(R.string.frag_ultimas)));
    }

    @Override
    public void onLoadFinished(Loader<List<Noticias>> loader, List<Noticias> data) {

        mNoticias.addAll(data);
        mAdapter.notifyDataSetChanged();
        Utils.progressBarEstado(getContext(), false);
    }

    @Override
    public void onLoaderReset(Loader<List<Noticias>> loader) {
    }


}
