package com.example.gestionarfragmentos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean bol = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Fragment fragment1 = new Fragment1();
        final Fragment fragment2 = new Fragment2();

        Button button = findViewById(R.id.bottom);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (bol) {
                    ft.replace(R.id.fragment_container, fragment1);
                }
                else {
                    ft.replace(R.id.fragment_container, fragment2);
                }
                ft.addToBackStack(null);
                ft.commit();
                bol = (bol) ? false:true;
            }
        });
    }
}
