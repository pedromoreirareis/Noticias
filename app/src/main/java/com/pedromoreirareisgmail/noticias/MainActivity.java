package com.pedromoreirareisgmail.noticias;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pedromoreirareisgmail.noticias.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Cria refrencia ao toolbar
        // Coloca o toolbar no lugar do ActionBar
        Toolbar toolbar = mBinding.toolbar;
        setSupportActionBar(toolbar);

        // Cria o AdapterPage
        // Cria um suporte ao gerenciador de fragmentos para pagina
        AdapterPage adapterPage = new AdapterPage(this,getSupportFragmentManager());

        // Cria refrencia a pagina
        ViewPager pagina = mBinding.vpPagina;
        pagina.setAdapter(adapterPage);

        // Cria referecia ao TabLayout
        //
        TabLayout tabLayout = mBinding.tbTab;
        tabLayout.setupWithViewPager(pagina);

    }
}
