package com.example.simplecursoradapter;

import android.database.Cursor;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class CursorRecyclerAdapterAbs extends RecyclerView.Adapter {

    Cursor mCursor;

    public CursorRecyclerAdapterAbs(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(mCursor==null)
            throw new IllegalStateException("Error, cursor vacio");
        if(!mCursor.moveToPosition(position))
            throw  new IllegalStateException("Error, no se puede encontrar la posicion: " + position);

            onBindViewHolder(holder, mCursor);
    }

    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, Cursor cursor);

    @Override
    public int getItemCount() {
        if(mCursor != null) {
            return mCursor.getCount();
        } else
            return 0;

    }

    @Override
    public long getItemId(int position) {
        if(hasStableIds() && mCursor != null) {
            if(mCursor.moveToPosition(position)) {
                return  mCursor.getLong(mCursor.getColumnIndexOrThrow("_id"));
            }
        }
        return  RecyclerView.NO_ID;
    }
}
