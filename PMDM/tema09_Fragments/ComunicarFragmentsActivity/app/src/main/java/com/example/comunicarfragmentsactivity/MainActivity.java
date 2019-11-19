package com.example.comunicarfragmentsactivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends FragmentActivity implements OnSelectedItemListener {

    private OnSelectedItemListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        Fragment fragmentLista = new MyListFragment();
        ft.replace(R.id.fragment_container, fragmentLista);
        ft.commit();

    }

    @Override
    public void OnItemSelected(String str) {

        Fragment fragmentSecundario =new FragmentSecundario();

        Bundle args = new Bundle();
        args.putString("str", str);
        fragmentSecundario.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fragment_container, fragmentSecundario);
        ft.addToBackStack(null);
        ft.commit();
    }



}

