package com.pedromoreirareisgmail.noticias;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pedromoreirareisgmail.noticias.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return abrirConfiguracoes();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Toolbar toolbar = mBinding.tbToolbar;
        setSupportActionBar(toolbar);

        AdapterPage adapterPage = new AdapterPage(this,getSupportFragmentManager());

        ViewPager pagina = mBinding.vpPagina;
        pagina.setAdapter(adapterPage);

        TabLayout tabLayout = mBinding.tbTab;
        tabLayout.setupWithViewPager(pagina);
    }

    private Boolean abrirConfiguracoes() {

        Intent intentConfig = new Intent(MainActivity.this, ConfiguracaoActivity.class);
        startActivity(intentConfig);
        return true;
    }
}
