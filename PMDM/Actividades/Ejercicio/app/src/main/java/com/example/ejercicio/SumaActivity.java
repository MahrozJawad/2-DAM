package com.example.ejercicio;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SumaActivity extends AppCompatActivity {

    private static final String TAG = SumaActivity.class.getSimpleName();
    private static final String STATE_SUM_OUTPUT = "sumOutput";

    private EditText mNumberAInput;
    private EditText mNumberBInput;
    private TextView mSumTotalLabel;
    private Button mAddButton;
    private Button mGetRootButton;

    private String sumOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);
        Log.d(TAG, "onCreate()");

        // Instancias
        mNumberAInput = findViewById(R.id.number_a_input);
        mNumberBInput = findViewById(R.id.number_b_input);
        mSumTotalLabel = findViewById(R.id.sum_label);
        mAddButton = findViewById(R.id.add_button);
        mGetRootButton = findViewById(R.id.get_root_button);

        // Actualización
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserNumbers();
            }
        });
    }

    private void addUserNumbers() {
        String inputA = mNumberAInput.getText().toString();
        String inputB = mNumberBInput.getText().toString();
        int numberA = inputA.isEmpty() ? 0 : Integer.parseInt(inputA);
        int numberB = inputB.isEmpty() ? 0 : Integer.parseInt(inputB);

        sumOutput = String.format(Locale.getDefault(), "= %d", numberA + numberB);
        mSumTotalLabel.setText(sumOutput);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStrat()");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
