package com.example.comunicarfragmentsactivity;

import androidx.fragment.app.ListFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MyListFragment extends ListFragment {

    private String[] valores = {"item1","item2","item3","item4","item5","item6","item7","item8"};
    private OnSelectedItemListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, valores));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l,v,position,id);
        listener.OnItemSelected(valores[position]);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnSelectedItemListener) context;
        } catch (ClassCastException e) {}
    }

}
