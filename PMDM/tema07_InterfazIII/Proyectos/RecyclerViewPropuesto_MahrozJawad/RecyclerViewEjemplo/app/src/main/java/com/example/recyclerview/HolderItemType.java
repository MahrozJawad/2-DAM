package com.example.recyclerview;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewejemplo.R;

public class HolderItemType extends RecyclerView.ViewHolder implements View.OnClickListener {

    Context context;
    View itemView;
    ImageView itemTypeImage;
    TextView itemTypeTextView;
    ImageView favorito;
    ImageView turned_in;
    ImageView social;
    Button bShare;
    Button bExplore;
    MainActivity.OnImagenClickListener listener;
    Dato dato;
    
    

    public HolderItemType(View itemView, Context context, int tipo) {
        super(itemView);
        this.context = context;
        this.itemView = itemView;
        
        itemTypeImage = itemView.findViewById(R.id.imageView);
        itemTypeTextView = itemView.findViewById(R.id.txt_title);
        itemTypeImage.setOnClickListener(this);

        switch (tipo) {
            case 0:

                break;
            case 1:
                favorito = itemView.findViewById(R.id.imageView1);
                turned_in = itemView.findViewById(R.id.imageView2);
                social = itemView.findViewById(R.id.imageView3);

                favorito.setOnClickListener(this);
                turned_in.setOnClickListener(this);
                social.setOnClickListener(this);
                break;
            case 2:
                bShare = itemView.findViewById(R.id.bShare);
                bExplore = itemView.findViewById(R.id.bExplore);


                bShare.setOnClickListener(this);
                bExplore.setOnClickListener(this);
                break;

        }

    }
    public  void bind(Dato dato, int pos){
        //itemView.setBackgroundColor(ContextCompat.getColor(context,R.color.colorAccent));
        itemTypeImage.setImageBitmap(dato.getFoto());
        itemTypeTextView.setText(dato.getTextoCorto());
        this.dato = dato;
    }

    public void setClickAImagenListener(MainActivity.OnImagenClickListener listener){
        if(listener!=null)
            this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null) listener.OnImageClick(dato, view);
    }

}
