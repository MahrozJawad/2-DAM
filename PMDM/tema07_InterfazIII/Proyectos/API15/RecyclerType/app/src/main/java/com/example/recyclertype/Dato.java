package com.example.recyclertype;

import android.graphics.Bitmap;

public class Dato {
    private String textoCorto;
    private String textoLargo;
    private Bitmap foto;
    private int tipo;

    public Dato(String textoCorto, String textoLargo, Bitmap foto, int tipo) {
        this.textoCorto = textoCorto;
        this.textoLargo = textoLargo;
        this.foto = foto;
        this.tipo = tipo;
    }

}
