package com.pedromoreirareisgmail.noticias;


import android.app.LoaderManager;
import android.content.Loader;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedromoreirareisgmail.noticias.databinding.ContainerRecyclerviewBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UltimasFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Noticias>> {

    private static final String URL_ULTIMAS = "https://content.guardianapis.com/search?api-key=717bb387-af84-40dd-ab02-4cbc8ceec742";
    private static final int LOADER_ID = 0;
    private ContainerRecyclerviewBinding mBinding;

    public UltimasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // infla o layout container
        mBinding = DataBindingUtil.inflate(inflater,R.layout.container_recyclerview,container,false);


        //TODO: implementar recyclerView



        // retorna o layout inflado
        return mBinding.getRoot();
    }


    @Override
    public Loader<List<Noticias>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Noticias>> loader, List<Noticias> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Noticias>> loader) {

    }
}
