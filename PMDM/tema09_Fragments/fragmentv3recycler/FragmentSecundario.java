package com.xusa.tema7.fragmentv3recycler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentSecundario extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saveInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_secundario, container, false);

        Bundle b=getArguments();
        String inf= (String) b.get("str");
        ((TextView) rootView.findViewById(R.id.texto)).setText(inf);

        return rootView;

    }
}

