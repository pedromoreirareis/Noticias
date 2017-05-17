package com.pedromoreirareisgmail.noticias;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class AdapterPage extends FragmentPagerAdapter {

    private final Context mContext;

    // Construtor
    public AdapterPage(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // Recebe caracteres e passa como titulo para uma pagina
    @Override
    public CharSequence getPageTitle(int position) {

        String titulo = "";
        switch (position) {
            case 0:
                titulo = mContext.getString(R.string.frag_ultimas_tab);
                break;
            case 1:
                titulo = mContext.getString(R.string.frag_tecnologia_tab);
                break;
            case 2:
                titulo = mContext.getString(R.string.frag_money_tab);
                break;
            case 3:
                titulo = mContext.getString(R.string.frag_business_tab);
                break;
            case 4:
                titulo = mContext.getString(R.string.frag_banking_tab);
                break;
            case 5:
                titulo = mContext.getString(R.string.frag_brasil_tab);
                break;
            case 6:
                titulo = mContext.getString(R.string.frag_football_tab);
                break;
        }
        return titulo;
    }

    // Cria um fragmento em uma posição especificada
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new UltimasFragment();
        } else if (position == 1) {
            return new TecnologiaFragment();
        } else if (position == 2) {
            return new MoneyFragment();
        } else if (position == 3) {
            return new BusinessFragment();
        } else if (position == 4) {
            return new BankingFragment();
        } else if (position == 5) {
            return new BrasilFragment();
        } else {
            return new FootBallFragment();
        }
    }

    // Retorna a quantidade de paginas a ser criada
    @Override
    public int getCount() {
        return 7;
    }
}
