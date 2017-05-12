package com.pedromoreirareisgmail.noticias;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pedromoreirareisgmail.noticias.databinding.ContainerRecyclerviewBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TecnologiaFragment extends Fragment {

    private ContainerRecyclerviewBinding mBinding;

    public TecnologiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // infla o Layout container
        mBinding = DataBindingUtil.inflate(inflater, R.layout.container_recyclerview, container, false);


        // Retorna uma view inflada
        return mBinding.getRoot();
    }

}
