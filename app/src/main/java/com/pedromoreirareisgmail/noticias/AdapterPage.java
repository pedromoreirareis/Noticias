package com.pedromoreirareisgmail.noticias;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterPage extends FragmentPagerAdapter {

    private Context mContext;

    /**
     * Construtor
     *
     * @param context contexto onde a pagina sera implementada
     * @param fm      gerenciador de paginas
     */
    public AdapterPage(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }


    /**
     * Esse método pode ser chamado pelo ViewPager para obter uma
     * seqüência de título para descrever a página especificada.
     * Este método pode retornar null indicando nenhum título para
     * esta página. A implementação padrão retorna null.
     *
     * @param position A posição do título solicitado
     * @return Um título para a página solicitada
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.frag_ultimas);
        } else {
            return mContext.getString(R.string.frag_tecnologia);
        }
    }


    /**
     * Atribui uma pagina a uma posição especifica
     *
     * @param position posição de uma pagina
     * @return Retorna o Fragmento associado a uma posição especificada.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UltimasFragment();
        } else {
            return new TecnologiaFragment();
        }
    }


    /**
     * Especifica a quantidade de pagina a ser visualizada
     *
     * @return Retorna o número de visualizações disponíveis
     */
    @Override
    public int getCount() {
        return 2;
    }
}
