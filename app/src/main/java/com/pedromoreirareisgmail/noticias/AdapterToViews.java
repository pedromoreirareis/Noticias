package com.pedromoreirareisgmail.noticias;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedromoreirareisgmail.noticias.databinding.ItensRecyclerviewBinding;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterToViews extends RecyclerView.Adapter<AdapterToViews.MyViewHolder> {

    public ItensRecyclerviewBinding mBinding;
    private Context mContext;
    private List<Noticias> mLista;

    public AdapterToViews(Context context, List<Noticias> lista) {
        mContext = context;
        mLista = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // Infla o layout
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.itens_recyclerview, parent, false);

        // Coloca a View inflada dentro do MyViewHolder
        return new MyViewHolder(mBinding.getRoot());
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Noticias noticiaAtual = mLista.get(position);

        holder.tvTitulo.setText(noticiaAtual.getmWebTitle());
        holder.tvResumo.setText(noticiaAtual.getmTrailText());
        Picasso.with(mContext).load(noticiaAtual.getmThumbnail()).into(holder.ivImagem);


        // TODO teste abertura url dentro do ViewHolder

        holder.cvContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = noticiaAtual.getmWebUrl();

                Uri webUrl = Uri.parse(url);

                Intent webIntent = new Intent(Intent.ACTION_VIEW, webUrl);

                if (webIntent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(webIntent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mLista.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivImagem;
        TextView tvTitulo;
        TextView tvResumo;
        CardView cvContainer;

        public MyViewHolder(View v) {
            super(v);

            ivImagem = mBinding.ivImagem;
            tvTitulo = mBinding.tvTitulo;
            tvResumo = mBinding.tvResumo;
            cvContainer = mBinding.cvContainer;
        }
    }
}
