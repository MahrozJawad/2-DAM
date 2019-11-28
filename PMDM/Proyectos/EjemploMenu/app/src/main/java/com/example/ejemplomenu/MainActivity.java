package com.example.ejemplomenu;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnClic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClic = findViewById(R.id.cmdMostrar);
        btnClic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getApplicationContext(), v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        String s = "";
                        switch (menuItem.getItemId()) {
                            case R.id.popmenu1:
                                s= "opcion1 del menu popup";
                                break;
                            case R.id.popmenu2:
                                s= "opcion2 del menu popup";
                                break;
                        }
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                        return  true;
                    }
                });
                popupMenu.show();
            }
        });
    }



}
