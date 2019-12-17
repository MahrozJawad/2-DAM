package com.example.simplecursoradapter;

import android.database.Cursor;
import android.graphics.Bitmap;

import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerAdapter extends CursorRecyclerAdapterAbs {
    private int mLayout;
    private int[] mFrom;
    private int[] mTo;

    public MyRecyclerAdapter(int layout, Cursor mCursor, String[] from, int[] to) {
        super(mCursor);
        this.mLayout = layout;
        this.mTo = to;
        findColumns(mCursor,from);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor) {
        ((SimpleViewHolder) holder).bind(0, cursor.getString(mFrom[0]));
        ((SimpleViewHolder) holder).bind(1, cursor.getString(mFrom[1]));
        Bitmap theImage = MainActivity.convertirStringbitmap(cursor.getString(mFrom[2]));
        ((SimpleViewHolder) holder).bind(theImage);
    }
    private void findColumns(Cursor c, String[] from) {
        if(c != null) {
            int i;
            int count = from.length;
            if(mFrom == null || mFrom.length != count) {
                mFrom = new int[count];
            }
            for (i=0; i < count; i++) {
                mFrom[i] = c.getColumnIndexOrThrow(from[i]);
            }
        } else {
            mFrom = null;
        }
    }
}
