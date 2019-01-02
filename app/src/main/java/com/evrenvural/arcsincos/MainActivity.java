package com.evrenvural.arcsincos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private String array_spinner[];

    Spinner spFonkiyonlar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerEkle();
    }

    void spinnerEkle(){
        array_spinner = new String[4];
        array_spinner = getResources().getStringArray(R.array.fonksiyonlar);
        spFonkiyonlar = findViewById(R.id.spFonksiyonlar);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array_spinner);
        spFonkiyonlar.setAdapter(arrayAdapter);
    }
}
