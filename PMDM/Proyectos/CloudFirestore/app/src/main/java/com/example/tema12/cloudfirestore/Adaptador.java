package com.example.tema12.cloudfirestore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class Adaptador extends FirestoreRecyclerAdapter<Ciudad,Holder> implements View.OnClickListener, View.OnLongClickListener{

    private View.OnClickListener clickListener;
    private View.OnLongClickListener longClickListener;

    public Adaptador(@NonNull FirestoreRecyclerOptions<Ciudad> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int i, @NonNull Ciudad ciudad) {
        holder.bind(ciudad);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        view.setOnLongClickListener(this);
        view.setOnClickListener(this);
        return new Holder(view);
    }

    @Override
    public void onClick(View v) {
        if(clickListener != null) {
            clickListener.onClick(v);
        }
    }
    void onClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        if(longClickListener != null) {
            longClickListener.onLongClick(v);
        }
        return true;
    }
    void onLongClickListener(View.OnLongClickListener listener) {
        this.longClickListener= listener;
    }
}
