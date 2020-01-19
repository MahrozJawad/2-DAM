package com.example.recyclercursor;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class MiRecyclerAdapter extends  CursorRecyclerAdapterAbs {
    private int mLayout;
    private int[] mFrom;
    private int[] mTo;

    public MiRecyclerAdapter(int layout, Cursor c, String[] from, int[] to) {
        super(c);
        mLayout = layout;
        mTo = to;
        findColumns(c, from);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(mLayout, parent, false);
        return new SimpleViewHolder(v,mTo);}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
            ((SimpleViewHolder) holder).bind(0, cursor.getString(mFrom[0]));
            ((SimpleViewHolder) holder).bind(1, cursor.getString(mFrom[1]));
             Bitmap theImage=LibreriaImagenes.convertirStringBitmap(cursor.getString(mFrom[2]));
            ((SimpleViewHolder) holder).bind( theImage); }

    private void findColumns(Cursor c, String[] from) {
        if (c != null) {
            int i;
            int count = from.length;
            if (mFrom == null || mFrom.length != count) {
                mFrom = new int[count]; }
            for (i = 0; i < count; i++) {
                mFrom[i] = c.getColumnIndexOrThrow(from[i]); }
        } else { mFrom = null; }
    }}

class SimpleViewHolder extends RecyclerView.ViewHolder
{
    TextView[] view=new TextView[2];
    ImageView imagen;

    public SimpleViewHolder (View itemView, int[]to)
    {
        super(itemView);
        view[0]= (TextView) itemView.findViewById(to[0]);
        view[1] = (TextView) itemView.findViewById(to[1]);
        imagen=(ImageView)itemView.findViewById(to[2]);
    }

    public void bind(int pos, String dato)
    {
        view[pos].setText(dato);
    }

    public void bind(Bitmap dato)
    {
      imagen.setImageBitmap(dato);
    }
}
