package com.example.menucontextualrecyclerview;

import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Holder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

    TextView textView;

    public Holder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        itemView.setOnCreateContextMenuListener(this);

    }
    public void Bind(String item) {
        textView.setText(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        MenuInflater menuInflater = new MenuInflater(view.getContext());
        menuInflater.inflate(R.menu.menucontextual_texto, contextMenu);
    }
}
