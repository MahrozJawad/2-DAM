package com.example.toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toolbar;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v7);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();

        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Toolbar tbCard = (Toolbar) findViewById(R.id.TbCard);
        tbCard.setTitle("Mi Tarjeta");

        tbCard.setOnMenuItemClickListener(
                new Toolbar.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch(item.getItemId()){
                            case R.id.action_1:
                                Log.i("Toolbar 2", "Acción Tarjeta 1");
                                break;
                            case R.id.action_2:
                                Log.i("Toolbar 2","Acción TArjeta 2");
                        }

                        return true;
                    }
                }
        );

    tbCard.inflateMenu(R.xml.menu_tarjeta);
    }
}
