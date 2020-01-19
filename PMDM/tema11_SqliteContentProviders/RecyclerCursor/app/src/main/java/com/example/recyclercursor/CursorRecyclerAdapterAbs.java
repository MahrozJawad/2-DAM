package com.example.recyclercursor;

import android.database.Cursor;
import androidx.recyclerview.widget.RecyclerView;


public abstract class CursorRecyclerAdapterAbs extends RecyclerView.Adapter {
    Cursor mCursor;

    public CursorRecyclerAdapterAbs(Cursor cursor) { mCursor=cursor;}

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mCursor==null) { throw new IllegalStateException("ERROR, cursos vacio");}
        if (!mCursor.moveToPosition(position)) {
            throw new IllegalStateException("ERROR, no se puede encontrar la posicion:  " + position);}
        onBindViewHolder(holder, mCursor);}

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor);

    @Override
    public int getItemCount () {
        if (mCursor != null) { return mCursor.getCount();}
        else { return 0; }}

    @Override
    public long getItemId (int position) {
        if(hasStableIds() && mCursor != null){
            if (mCursor.moveToPosition(position)) {
                return mCursor.getLong(mCursor.getColumnIndexOrThrow("_id"));}}
        return RecyclerView.NO_ID;}
}
