package com.pedromoreirareisgmail.noticias;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.pedromoreirareisgmail.noticias.databinding.ActivityConfiguracaoBinding;

public class ConfiguracaoActivity extends AppCompatActivity {

    private static final Boolean SIM = true;
    private ActivityConfiguracaoBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_configuracao);


        Toolbar toolbar = mBinding.tbToolbarFrag;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(SIM);
    }

    public static class ConfiguracoesFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {

        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {

            String novoValor = newValue.toString();

            if (preference instanceof ListPreference) {

                ListPreference lista = (ListPreference) preference;

                int indice = lista.findIndexOfValue(novoValor);

                if (indice >= 0) {

                    CharSequence[] charSequences = lista.getEntries();

                    preference.setSummary(charSequences[indice]);
                }

            }
            return true;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.configuracoes);

            Preference pagesize = findPreference(getString(R.string.config_pagesize_key));
            ligarPreferencias(pagesize);

            Preference orderby = findPreference(getString(R.string.config_orderby_key));
            ligarPreferencias(orderby);
        }

        private void ligarPreferencias(Preference preference) {

            preference.setOnPreferenceChangeListener(this);

            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(preference.getContext());

            String newValor = sharedPreferences.getString(preference.getKey(), "");

            onPreferenceChange(preference, newValor);
        }
    }
}
