package com.xusa.tema7.fragmentv3recycler;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListFragment extends Fragment{
    private OnSelectedItemListener listener;
    RecyclerView recyclerView;
    Adapter adapter;
    ArrayList<String> valores;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        valores=new ArrayList<>();
        rellenarValores();
    }

    private void rellenarValores() {
        String[] array= new String[]{"item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8"};
        for(String x:array) {
            valores.add(x);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        recyclerView=(RecyclerView)rootView.findViewById(R.id.recyclerX);
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(valores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter.miOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSelectedItem(valores.get(recyclerView.getChildAdapterPosition(v)));
            }
        });
         return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener=(OnSelectedItemListener) context;
        } catch (ClassCastException e){}
    }
}

