package com.pedromoreirareisgmail.noticias;

import android.animation.AnimatorSet;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pedromoreirareisgmail.noticias.databinding.ItensRecyclerviewBinding;
import com.squareup.picasso.Picasso;

import java.util.List;


public class AdapterToViews extends RecyclerView.Adapter<AdapterToViews.MyViewHolder> {

    private static final String SEPARATOR_INICIO = "T";
    private static final String SEPARATOR_DATA = "-";
    private static final String SEPARATOR_Z = "Z";
    private static final String SEPARATOR_PONTOS = ":";
    private ItensRecyclerviewBinding mBinding;
    private List<Noticias> mLista;
    private Context mContext;

    private AnimatorSet mAnimatorSet = new AnimatorSet();


    private RecyclerViewOnClick mRecyclerViewOnClick;

    public AdapterToViews(Context context, List<Noticias> lista) {
        mContext = context;
        mLista = lista;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.itens_recyclerview, parent, false);
        return new MyViewHolder(mBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final Noticias noticiaAtual = mLista.get(position);

        String data = formatarData(noticiaAtual.getmWebPublicationDate());
        String hora = formatarHora(noticiaAtual.getmWebPublicationDate());

        holder.tvTitulo.setText(noticiaAtual.getmWebTitle());
        holder.tvResumo.setText(noticiaAtual.getmTrailText());
        holder.tvData.setText(data);
        holder.tvHora.setText(hora);
        holder.tvSection.setText(noticiaAtual.getmSectionName());

        if (TextUtils.isEmpty(noticiaAtual.getmThumbnail())) {
            holder.ivImagem.setScaleType(ImageView.ScaleType.FIT_CENTER);
            holder.ivImagem.setImageResource(R.drawable.sem_imagem);

        } else {
            holder.ivImagem.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(mContext).load(noticiaAtual.getmThumbnail()).into(holder.ivImagem);
        }
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }

    // Recebe uma string "2014-02-17T12:05:47Z" e retira a Data 17/02/2014
    private String formatarData(String dataHora) {
        String[] dataArray = dataHora.split(SEPARATOR_INICIO);
        String[] dataFormatando = dataArray[0].split(SEPARATOR_DATA);
        return dataFormatando[2] + "/" + dataFormatando[1] + "/" + dataFormatando[0];
    }

    // Recebe uma string "2014-02-17T12:05:47Z" e retira a Hora 12:05:47
    private String formatarHora(String dataHora) {
        String[] horaArray = dataHora.split(SEPARATOR_INICIO);
        String[] horaFormatando = horaArray[1].split(SEPARATOR_Z);
        return horaFormatando[0];
    }

    public void setRecyclerViewOnClick(RecyclerViewOnClick recyclerViewOnClick) {
        mRecyclerViewOnClick = recyclerViewOnClick;
    }

    public interface RecyclerViewOnClick {
        void OnClickListener(int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivImagem;
        TextView tvTitulo;
        TextView tvResumo;
        TextView tvData;
        TextView tvHora;
        TextView tvSection;
        CardView cvContainer;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivImagem = mBinding.ivImagem;
            tvTitulo = mBinding.tvTitulo;
            tvResumo = mBinding.tvResumo;
            tvData = mBinding.tvData;
            tvHora = mBinding.tvHora;
            tvSection = mBinding.tvSection;
            cvContainer = mBinding.cvContainer;
            cvContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {

            if (itemView.getId() == R.id.cv_container) {
                mRecyclerViewOnClick.OnClickListener(getAdapterPosition());
            }

        }
    }
}
