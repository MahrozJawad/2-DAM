package com.xusa.tema7.fragmentv3recycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MyActivity extends FragmentActivity implements OnSelectedItemListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        FragmentManager FM=getSupportFragmentManager();
        FragmentTransaction FT=FM.beginTransaction();

        Fragment fragmentLista=new MyListFragment();
        FT.replace(R.id.fragment_container,fragmentLista);
        FT.commit();
    }

    @Override
    public void onSelectedItem(String str) {

            Fragment fragmentSecundario=new FragmentSecundario();
            Bundle args = new Bundle();
            args.putString("str", str);
            fragmentSecundario.setArguments(args);

            FragmentManager FM = getSupportFragmentManager();
            FragmentTransaction FT = FM.beginTransaction();


            FT.replace(R.id.fragment_container, fragmentSecundario);
            FT.addToBackStack(null);
            FT.commit();

    }


}
