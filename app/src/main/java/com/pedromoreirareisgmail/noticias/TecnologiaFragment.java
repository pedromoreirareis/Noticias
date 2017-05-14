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

import com.pedromoreirareisgmail.noticias.databinding.ContainerRecyclerviewBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TecnologiaFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Noticias>> {

    private static final int LOADER_ID = 1;
    private ContainerRecyclerviewBinding mBinding;

    private List<Noticias> mNoticias;
    private AdapterToViews mAdapter;
    private RecyclerView mRecyclerView;

    public TecnologiaFragment() {
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


        getLoaderManager().initLoader(LOADER_ID, null, this);

        // retorna o layout inflado
        return mBinding.getRoot();
    }

    @Override
    public Loader<List<Noticias>> onCreateLoader(int id, Bundle args) {
        return new LoaderTask(getContext(), Utils.preparaUrlPesquisa(getContext(), getString(R.string.frag_tecnologia)));
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
